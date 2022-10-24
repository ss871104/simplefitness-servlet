package com.order.service.intf;

import java.util.List;

import com.order.vo.Order;
import com.order.vo.PageVo;
import com.orderdetail.vo.OrderDetail;

public interface OrderServiceIntf {

	public List<Order> findAll();
	public PageVo<Order> selectByMem(Integer memId, Integer pageNo, Integer pageSize);
	public boolean CancelOrder(Order order);
	public Order addOrder(Order order);
	public Order ecpayValidation(Order order);
}
