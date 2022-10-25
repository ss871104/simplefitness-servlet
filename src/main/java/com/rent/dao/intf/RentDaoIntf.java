package com.rent.dao.intf;

import java.util.List;

import javax.persistence.criteria.CommonAbstractCriteria;

import com.common.dao.CommonDao;
import com.order.vo.Order;
import com.orderdetail.vo.OrderDetail;

public interface RentDaoIntf extends CommonDao<Order, Integer>{

	public List<Order> selectByMemId(Integer memId);
	public boolean updateStatus(OrderDetail orderDetail);
	
}
