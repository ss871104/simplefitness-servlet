package com.orderdetail.dao.intf;

import com.common.dao.CommonDao;
import com.orderdetail.vo.OrderDetail;

public interface OrderDetailDaoIntf extends CommonDao<OrderDetail, Integer>{
	
	public OrderDetail SelectByOrderId(Integer orderId);
	public boolean UpdateStatus(OrderDetail ordetailVo);
	public boolean UpdatePickup(OrderDetail ordetailVo);
	public boolean UpdateReturn(OrderDetail ordetailVo);
}
