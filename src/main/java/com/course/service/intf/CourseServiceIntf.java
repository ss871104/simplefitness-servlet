package com.course.service.intf;

import java.util.List;

import com.course.vo.Course;
import com.coursebooking.vo.CourseBooking;

public interface CourseServiceIntf {
	
	public Course addCourse(Course course);
	
	public Course editCourse(Course course);
	
	public List<Course> findAll();
	
	public List<Course> selectCourseByGymIdAndStartTime(Course course);
	
	public Course findById(Course course);
	
	public Course deteleCourse(Course course);
	
	public List<Course> selectBookedMemberByCourseId(Course course);
	
}