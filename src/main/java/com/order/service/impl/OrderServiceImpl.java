package com.order.service.impl;

import java.util.List;

import javax.accessibility.AccessibleStateSet;

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
		order.setStatus("2");
		Integer orderId = dao.insertGetOrderId(order);
		
		Integer orderCode;
		
		System.out.println(orderId);
		for (int i = 0; i < order.getOrderList().size(); i++) {
			for (int j = 0; j < order.getOrderList().get(i).getInCart(); j++) {
				orderCode =  detailDao.insert2(orderId, order.getGymId(), order.getOrderList().get(i).getProdId());
				idvDao.UpdateStatus("2", detailDao.selectById(orderCode).getIdvId());
			}
		}
		
		Order o = dao.selectById(orderId);
		
		// JavaMail執行緒
		JavaMailThread.to = order.getMemEmail();
		JavaMailThread.subject = "訂單成立通知";
		JavaMailThread.ch_name = order.getMemName();
		JavaMailThread.messageText = "Hello! " + JavaMailThread.ch_name +"租借成功，租借金額 $"+ o.getAmount()
		+ "<br>" +"請至會員中心查看"+ "<br>" +"<a href=\"http://localhost:8080/simplefitness-servlet/html/member/order.html\"> 查詢我的訂單 </a>";
		JavaMailThread jmt = new JavaMailThread();
		jmt.start();
		
		return o;
	}

	@Override
	public boolean CancelOrder(Order order) {
		order.setStatus("0");
		return dao.UpdateStatus(order);
	}

}
