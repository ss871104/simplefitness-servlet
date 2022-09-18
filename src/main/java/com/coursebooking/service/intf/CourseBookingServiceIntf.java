package com.coursebooking.service.intf;

import java.util.List;

import com.courlist.vo.CourList;
import com.coursebooking.vo.CourseBooking;

public interface CourseBookingServiceIntf {

	CourseBooking booking(CourseBooking coursebook);

	CourseBooking memCancel(CourseBooking coursebook);

	CourseBooking empCancel(CourseBooking coursebook);

	CourseBooking check(CourseBooking coursebook);

	List<CourseBooking> findAll();
	
	//測試取得清單
	List<CourList> getCourseList(CourList courseList);

}
