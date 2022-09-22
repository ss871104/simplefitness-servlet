package com.order.service.intf;

import java.util.List;

import com.order.vo.Order;

public interface OrderServiceIntf {
	List<Order> findAll();
	}
