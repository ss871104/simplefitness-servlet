package com.coursebooking.dao.intf;

import java.util.List;

import com.common.dao.CommonDao;
import com.coursebooking.vo.CourseBooking;

public interface CourseBookingDaoIntf extends CommonDao<CourseBooking, Integer> {

	public boolean updateStatus(CourseBooking coursebookVo);

	public int getCourseBookedCount(Integer courseId);
	
	public List<CourseBooking> selectBookedCourseByMemberIdAndGymId(Integer memId, Integer gymId);

}
