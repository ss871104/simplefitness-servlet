package com.rent.service.intf;

import java.util.List;

import com.order.vo.Order;

public interface RentServiceIntf {
	
	public List<Order> SelectByMemId(Integer memId);

}
