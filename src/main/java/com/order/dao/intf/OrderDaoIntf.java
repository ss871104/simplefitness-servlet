package com.order.dao.intf;

import java.util.List;

import com.common.dao.CommonDao;

import com.order.vo.Order;

public interface OrderDaoIntf extends CommonDao<Order, Integer>{
	
    public List<Order> SelectByMem(Integer memId);
    public List<Order> SelectByGym(Integer gymId);
    public boolean UpdateStatus(Order orderVo);
}
