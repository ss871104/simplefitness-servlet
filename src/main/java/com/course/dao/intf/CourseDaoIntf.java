package com.course.dao.intf;

import java.sql.Date;

import com.common.dao.CommonDao;
import com.course.vo.Course;


public interface CourseDaoIntf extends CommonDao<Course, Integer> {
	
	public Course selectByGymDate(Integer gymId, Date courseDate);

}
