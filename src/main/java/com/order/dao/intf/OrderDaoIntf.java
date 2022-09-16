package com.order.dao.intf;

import com.common.dao.CommonDao;

import com.order.vo.Order;

public interface OrderDaoIntf extends CommonDao<Order, Integer>{
	
    public Order SelectByMem(Integer memId);
    public Order SelectByGym(Integer gymId);
    public boolean UpdateStatus(Order orderVo);
}
