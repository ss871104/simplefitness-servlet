package com.order.service.intf;

import java.util.List;

import com.order.vo.Order;

public interface OrderServiceIntf {
	
	public List<Order> findAll();
	public List<Order> SelectByMem(Integer memId);
	public Order addOrder(Order order);
	public boolean CancelOrder(Order order);
	}
