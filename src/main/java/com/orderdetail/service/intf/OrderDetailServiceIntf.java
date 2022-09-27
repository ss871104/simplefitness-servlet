package com.orderdetail.service.intf;

import java.util.List;

import com.orderdetail.vo.OrderDetail;

public interface OrderDetailServiceIntf {
	
	public List<OrderDetail> findAll();
	public List<OrderDetail> SelectByOrderId(Integer orderId);
	
}
