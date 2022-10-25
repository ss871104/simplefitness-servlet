package com.rent.dao.intf;

import java.util.List;

import javax.persistence.criteria.CommonAbstractCriteria;

import com.common.dao.CommonDao;
import com.order.vo.Order;

public interface RentDaoIntf extends CommonDao<Order, Integer>{

	public List<Order> selectByMemId(Integer memId);
	
}
