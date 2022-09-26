package com.course.dao.intf;

import java.sql.Date;
import java.util.List;

import com.common.dao.CommonDao;
import com.course.vo.Course;


public interface CourseDaoIntf extends CommonDao<Course, Integer> {
	
	public Course selectByGymDate(Integer gymId, Date courseDate);

	
	
	/* *
	 *  Function: 取得會員可預約團課
	 *  CreateBy: Iris
	 *  CreateDate: 2022/09/21
	 * */
	List<Course> selectCourseByGymIdAndCourseListId(Integer gymId,Integer courseListId);
	
	
	/*
	 * * Function: 更新團課預約狀態 
	 *   CreateBy: Iris 
	 *   CreateDate: 2022/09/26
	 */
	public boolean updateStatus(Course course);

}
