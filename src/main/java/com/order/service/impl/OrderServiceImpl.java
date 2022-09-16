package com.order.service.impl;

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

}
