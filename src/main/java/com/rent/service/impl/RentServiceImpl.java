package com.rent.service.impl;

import java.util.List;

import com.order.vo.Order;
import com.orderdetail.vo.OrderDetail;
import com.rent.dao.impl.RentDaoImpl;
import com.rent.service.intf.RentServiceIntf;

public class RentServiceImpl implements RentServiceIntf {
	
	private RentDaoImpl dao;
	
	public RentServiceImpl() {
		dao = new RentDaoImpl();
	}
	
	public List<Order> selectByMemId(Integer memId){
		
		return dao.selectByMemId(memId);
		
	}

	@Override
	public OrderDetail orderStatusEdit(OrderDetail orderStatus) {
		if (dao.updateStatus(orderStatus.getStatus(), orderStatus.getOrderCode()) == false) {
			orderStatus.setMessage("更新失敗");
			orderStatus.setSuccessful(false);
		}
		orderStatus.setMessage("更新成功");
		orderStatus.setSuccessful(true);
		return orderStatus;
	}
	
	
	
}
