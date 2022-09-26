package com.coachbooking.dao.intf;

import java.util.List;

import com.coachbooking.vo.CoachBooking;
import com.common.dao.CommonDao;

public interface CoachBookingDaoIntf extends CommonDao<CoachBooking, Integer>{

	public Boolean updateStatus(CoachBooking coachbook);

	public List<CoachBooking> selectByMemberId(Integer memId);
	

}
