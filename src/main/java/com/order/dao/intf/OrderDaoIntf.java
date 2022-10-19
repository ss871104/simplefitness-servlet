package com.order.dao.intf;

import java.util.List;

import com.common.dao.CommonDao;

import com.order.vo.Order;

public interface OrderDaoIntf extends CommonDao<Order, Integer>{
	
    public List<Order> selectByMem(Integer memId, Integer pageNo, Integer pageSize);
    public List<Order> SelectByGym(Integer gymId);
    public boolean UpdateStatus(Order orderVo);
    public Integer insertGetOrderId(Order orderVo);
    public Long findCountByMem(Integer memId);
}
