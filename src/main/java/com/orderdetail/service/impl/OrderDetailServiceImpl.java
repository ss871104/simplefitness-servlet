package com.orderdetail.service.impl;

import java.util.List;

import com.orderdetail.dao.impl.OrderDetailDaoImpl;
import com.orderdetail.dao.intf.OrderDetailDaoIntf;
import com.orderdetail.service.intf.OrderDetailServiceIntf;
import com.orderdetail.vo.OrderDetail;

public class OrderDetailServiceImpl implements OrderDetailServiceIntf {

	private OrderDetailDaoIntf dao;
	
	public OrderDetailServiceImpl() {
		dao = new OrderDetailDaoImpl();
	}
	
	@Override
	public List<OrderDetail> findAll() {
		return dao.selectAll();
	}

	@Override
	public List<OrderDetail> SelectByOrderId(Integer orderId) {
		return dao.SelectByOrderId(orderId);
	}

}
