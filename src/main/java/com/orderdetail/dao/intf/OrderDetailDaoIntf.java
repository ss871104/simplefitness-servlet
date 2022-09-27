package com.orderdetail.dao.intf;

import java.util.List;

import com.common.dao.CommonDao;
import com.orderdetail.vo.OrderDetail;

public interface OrderDetailDaoIntf extends CommonDao<OrderDetail, Integer>{
	
	public List<OrderDetail> SelectByOrderId(Integer orderId);
	public boolean UpdateStatus(OrderDetail ordetailVo);
	public boolean UpdatePickup(OrderDetail ordetailVo);
	public boolean UpdateReturn(OrderDetail ordetailVo);
}
