package com.course.service.intf;

import java.util.List;

import com.course.vo.Course;

public interface CourseServiceIntf {
	
	Course addCourse(Course course);
	
	Course editCourse(Course course);
	
	List<Course> findAll();
	
	List<Course> selectCourseByGymIdAndStartTime(Course course);
	
	
}