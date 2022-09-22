package com.orderdetail.service.intf;

import java.util.List;

import com.orderdetail.vo.OrderDetail;

public interface OrderDetailServiceIntf {
	List<OrderDetail> findAll();
}
