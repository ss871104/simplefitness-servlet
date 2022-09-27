package com.courselist.service.intf;

import java.util.List;

import com.courselist.vo.CourseList;

public interface CourseListServiceIntf {
	
	CourseList add(CourseList cList);
	
	CourseList edit(CourseList cList);
	
	CourseList findById(CourseList cList);
	
	List<CourseList> findAll();

}
