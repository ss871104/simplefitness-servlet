package com.rent.service.intf;

import java.util.List;

import com.idvproduct.vo.IdvProduct;
import com.order.vo.Order;
import com.orderdetail.vo.OrderDetail;

public interface RentServiceIntf {
	
	public List<Order> selectByMemId(Integer memId);
	public OrderDetail orderDetailStatusEdit(OrderDetail orderStatus);
	public Order orderStatusEdit(Order order);
	
}
