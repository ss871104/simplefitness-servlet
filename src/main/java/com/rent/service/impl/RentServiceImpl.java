package com.rent.service.impl;

import java.util.List;

import com.order.vo.Order;
import com.rent.dao.impl.RentDaoImpl;
import com.rent.service.intf.RentServiceIntf;

public class RentServiceImpl implements RentServiceIntf {
	
	private RentDaoImpl dao;
	
	public RentServiceImpl() {
		dao = new RentDaoImpl();
	}
	
	public List<Order> SelectByMemId(Integer memId){
		
		
		
		return dao.selectByMemId(memId);
		
	}
}
