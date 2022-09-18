package com.ordetail.dao.intf;

import com.common.dao.CommonDao;
import com.ordetail.vo.OrDetail;

public interface OrDetailDaoIntf extends CommonDao<OrDetail, Integer>{
	
	public OrDetail SelectByOrderId(Integer orderId);
	public boolean UpdateStatus(OrDetail ordetailVo);
	public boolean UpdatePickup(OrDetail ordetailVo);
	public boolean UpdateReturn(OrDetail ordetailVo);
}
