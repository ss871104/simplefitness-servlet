package com.rent.service.impl;

import java.util.List;

import com.order.dao.impl.OrderDaoImpl;
import com.order.vo.Order;
import com.orderdetail.vo.OrderDetail;
import com.rent.dao.impl.RentDaoImpl;
import com.rent.service.intf.RentServiceIntf;

public class RentServiceImpl implements RentServiceIntf {
	
	private RentDaoImpl dao;
	private OrderDaoImpl orderDao;
	
	public RentServiceImpl() {
		dao = new RentDaoImpl();
		orderDao = new OrderDaoImpl();
	}
	
	public List<Order> selectByMemId(Integer memId){
		
		return dao.selectByMemId(memId);
		
	}

	@Override
	public OrderDetail orderDetailStatusEdit(OrderDetail orderStatus) {
		if (dao.updateStatus(orderStatus) == false) {
			orderStatus.setMessage("更新失敗");
			orderStatus.setSuccessful(false);
		}
		orderStatus.setMessage("更新成功");
		orderStatus.setSuccessful(true);
		return orderStatus;
	}

	@Override
	public Order orderStatusEdit(Order order) {
		if (orderDao.updateStatus(order) == false) {
			order.setMessage("更新失敗");
			order.setSuccessful(false);
		}
		order.setMessage("更新成功");
		order.setSuccessful(true);
		return order;
	}
	
	
	
	
	
}
