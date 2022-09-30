package com.coursebooking.service.intf;

import java.util.List;

import com.course.vo.Course;
import com.coursebooking.vo.CourseBooking;

public interface CourseBookingServiceIntf {
	
	public List<Course> searchCourseByGymIdAndCourseListId(CourseBooking coursebook);

	public Boolean bookCourse(CourseBooking coursebook);

	public void setCourseBookingFull(Integer courseId);
	
	public Boolean cancelCourseByMemberId(CourseBooking coursebook);

	public List<Course> checkBookingCourseByMemberId(CourseBooking coursebook);

	public void setCourseBookingEnable(Integer courseId);


}
