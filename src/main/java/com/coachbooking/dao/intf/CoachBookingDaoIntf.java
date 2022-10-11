package com.coachbooking.dao.intf;

import java.util.List;

import com.coachbooking.vo.CoachBooking;
import com.common.dao.CommonDao;

public interface CoachBookingDaoIntf extends CommonDao<CoachBooking, Integer>{

	public Boolean updateCoachBookingStatus(CoachBooking coachbook);

	public List<CoachBooking> selectBookingCoachClassByMemIdAndGymId(Integer memberId, Integer gymId);

	public List<CoachBooking> selectBookingCoachClassByEmpId(Integer empId);
	
}
