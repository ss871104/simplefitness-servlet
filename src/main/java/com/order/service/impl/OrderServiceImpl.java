package com.order.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.common.util.JavaMailThread;
import com.idvproduct.dao.impl.IdvProductDaoImpl;
import com.idvproduct.dao.intf.IdvProductDaoIntf;
import com.order.dao.impl.OrderDaoImpl;
import com.order.dao.intf.OrderDaoIntf;
import com.order.service.intf.OrderServiceIntf;
import com.order.vo.Order;
import com.order.vo.PageVo;
import com.orderdetail.dao.impl.OrderDetailDaoImpl;
import com.orderdetail.dao.intf.OrderDetailDaoIntf;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

public class OrderServiceImpl implements OrderServiceIntf {

	private OrderDaoIntf dao;
	private OrderDetailDaoIntf detailDao;
	private IdvProductDaoIntf idvDao;

	public OrderServiceImpl() {
		dao = new OrderDaoImpl();
		detailDao = new OrderDetailDaoImpl();
		idvDao = new IdvProductDaoImpl();
	}

	@Override
	public List<Order> findAll() {
		return dao.selectAll();
	}

	@Override
	public PageVo<Order> selectByMem(Integer memId, Integer pageNo, Integer pageSize) {
		List<Order> orders = dao.selectByMem(memId, pageNo, pageSize);

		PageVo<Order> po = new PageVo<Order>();
		po.setPageNo(pageNo);
		po.setPageSize(pageSize);
		po.setContent(orders);
		Long totalCount = dao.findCountByMem(memId);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
		po.setTotalPage(totalPage);
		po.setTotalCount(totalCount);
		return po;
	}

	@Override
	public Order addOrder(Order order) {
		if ("信用卡支付".equals(order.getPayfor())) {
			order.setStatus("1");
		}else {
			order.setStatus("2");
		}
		
		Integer orderId = dao.insertGetOrderId(order);
		Integer orderCode;

		for (int i = 0; i < order.getOrderList().size(); i++) {
			for (int j = 0; j < order.getOrderList().get(i).getInCart(); j++) {
				orderCode = detailDao.insert2(orderId, order.getGymId(), order.getOrderList().get(i).getProdId());
				idvDao.UpdateStatus("2", detailDao.selectById(orderCode).getIdvId());
			}
		}
		
		// JavaMail執行緒
		JavaMailThread.to = order.getMemEmail();
		JavaMailThread.subject = "訂單成立通知";
		JavaMailThread.ch_name = order.getMemName();
		JavaMailThread.messageText = "Hello! " + JavaMailThread.ch_name + "租借成功，租借金額 $" + order.getAmount() + "<br>"
				+ "請至會員中心查看" + "<br>"
				+ "<a href=\"/simplefitness-servlet/html/member/order.html\"> 查詢我的訂單 </a>";
		JavaMailThread jmt = new JavaMailThread();
		jmt.start();
		
		if ("信用卡支付".equals(order.getPayfor())) {
			order.setOrderId(orderId);
			ecpayValidation(order);
		}else {
			Order o = dao.selectById(orderId);
			return o;
		}
		return order;
	}

	@Override
	public boolean CancelOrder(Order order) {
		order.setStatus("0");
		return dao.UpdateStatus(order);
	}

	@Override
	public Order ecpayValidation(Order order) {
		List<String> productName = order.getOrderList().stream().map(e -> e.getProdName()).collect(Collectors.toList());
		
		Optional<String> reduce = productName.stream().reduce((String acc, String curr) -> {
			return acc + "#" + curr;
		});
		String name = reduce.get();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
		String orderDate = sdf.format(new Date());
		AllInOne allInOne = new AllInOne("");
		AioCheckOutALL aioCheckOutALL = new AioCheckOutALL();
		aioCheckOutALL.setMerchantTradeNo("SimpleFitness" + order.getOrderId());
		System.out.println("OrderID: " + order.getOrderId());
		aioCheckOutALL.setMerchantTradeDate(orderDate);
		aioCheckOutALL.setTotalAmount(order.getAmount() + ""); 
		aioCheckOutALL.setTradeDesc("test"); 
		aioCheckOutALL.setItemName(name); 
		aioCheckOutALL.setClientBackURL("http://localhost:8080/simplefitness-servlet/html/member/cart.html#tab3"); // 按個按鈕返回商城
		aioCheckOutALL.setReturnURL("http://localhost:8080/simplefitness-servlet/html/member/rentFront.html"); // 結束後回到自動商城
		aioCheckOutALL.setNeedExtraPaidInfo("N");

		String checkValue = allInOne.aioCheckOut(aioCheckOutALL, null);
		order.setAio(aioCheckOutALL);
		order.setMessage(checkValue);
		return order;
	}

}
