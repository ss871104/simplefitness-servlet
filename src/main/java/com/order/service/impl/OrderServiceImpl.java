package com.order.service.impl;

import java.util.Date;
import java.util.List;

import com.order.dao.impl.OrderDaoImpl;
import com.order.dao.intf.OrderDaoIntf;
import com.order.service.intf.OrderServiceIntf;
import com.order.vo.Order;

public class OrderServiceImpl implements OrderServiceIntf {

	private OrderDaoIntf dao;

	public OrderServiceImpl() {
		dao = new OrderDaoImpl();
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
	public Order addOrder(Order order) {
		order.setStatus("2");
		order.setOrderDate(new java.sql.Timestamp(new Date().getTime()));
		dao.insertGetOrderId(order);
		return order;
	}

	@Override
	public boolean CancelOrder(Order order) {
		order.setStatus("0");
		return dao.UpdateStatus(order);
	}

	
	
	

}
