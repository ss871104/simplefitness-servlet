package com.order.service.impl;

import java.util.Date;
import java.util.List;

import com.idvproduct.dao.impl.IdvProductDaoImpl;
import com.idvproduct.dao.intf.IdvProductDaoIntf;
import com.idvproduct.vo.IdvProduct;
import com.order.dao.impl.OrderDaoImpl;
import com.order.dao.intf.OrderDaoIntf;
import com.order.service.intf.OrderServiceIntf;
import com.order.vo.Order;
import com.orderdetail.dao.impl.OrderDetailDaoImpl;
import com.orderdetail.dao.intf.OrderDetailDaoIntf;
import com.orderdetail.vo.OrderDetail;

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
	public List<Order> SelectByMem(Integer memId) {
		return dao.SelectByMem(memId);
	}

	@Override
	public Order addOrder(Order order,Integer prodId) {
		order.setStatus("2");
//		order.setOrderDate(new java.sql.Timestamp(new Date().getTime()));
		Integer orderId = dao.insertGetOrderId(order);
		Integer orderCode;
		
		System.out.println(orderId);
		for (int i = 0; i < order.getOrderList().size(); i++) {
			for (int j = 0; j < order.getOrderList().get(i).getInCart(); j++) {
				orderCode =  detailDao.insert2(orderId, order.getGymId(), order.getOrderList().get(i).getProdId());
				System.out.println(orderCode);
				System.out.println(detailDao.selectById(orderCode).getIdvId());
				idvDao.UpdateStatus("2", detailDao.selectById(orderCode).getIdvId());
			}
		}
		
		
		
//		if (orderId != null) {
//			for (Object orders : order.getOrderList()) {
//				detailDao.insert2(orderId, order.getGymId(), orders[0]);
//			}
//		}
//		OrderDetail orderDetail = new OrderDetail();
		
//		orderDetail.setOrderId(orderId);
		
		
//		for (int i = 0; i < list.size(); i++) {
//			OrderDetail orderDetail = new OrderDetail();
//			orderDetail.setOrderId(orderId);
//			detailDao.insert(orderDetail);
//		}
		
		return order;
	}

	@Override
	public boolean CancelOrder(Order order) {
		order.setStatus("0");
		return dao.UpdateStatus(order);
	}

	
	
	

}
