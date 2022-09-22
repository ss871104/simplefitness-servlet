package com.coursebooking.service.intf;

import java.util.List;

import com.courselist.vo.CourseList;
import com.course.vo.Course;
import com.coursebooking.vo.CourseBooking;

public interface CourseBookingServiceIntf {
	
	List<Course> searchCourseByGymIdAndCourseListId(CourseBooking coursebook);

	Boolean bookCourse(CourseBooking coursebook);

	Boolean cancelCourseByMemberId(CourseBooking coursebook);

	List<CourseBooking> checkBookingCourseByMemberId(CourseBooking coursebook);


}
