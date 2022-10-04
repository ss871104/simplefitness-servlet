package com.order.service.intf;

import java.util.List;

import com.order.vo.Order;
import com.orderdetail.vo.OrderDetail;

public interface OrderServiceIntf {

	public List<Order> findAll();

	public List<Order> SelectByMem(Integer memId);

	public boolean CancelOrder(Order order);

	public Order addOrder(Order order, Integer prodId);
}
