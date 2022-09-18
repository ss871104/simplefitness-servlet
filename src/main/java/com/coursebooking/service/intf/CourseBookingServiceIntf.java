package com.coursebooking.service.intf;

import java.util.List;

import com.courselist.vo.CourseList;
import com.coursebooking.vo.CourseBooking;

public interface CourseBookingServiceIntf {

	Boolean bookCourse(CourseBooking coursebook);

	Boolean cancelCourseByMemberId(CourseBooking coursebook);

	CourseBooking checkBookingCourseByMemberId(CourseBooking coursebook);


}
