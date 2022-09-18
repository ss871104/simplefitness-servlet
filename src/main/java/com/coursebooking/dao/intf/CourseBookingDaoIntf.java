package com.coursebooking.dao.intf;

import com.common.dao.CommonDao;
import com.coursebooking.vo.CourseBooking;

public interface CourseBookingDaoIntf extends CommonDao<CourseBooking, Integer>{

	public CourseBooking selectByMem(Integer memId);
	public boolean updateStatus(CourseBooking coursebookVo);
	
}
