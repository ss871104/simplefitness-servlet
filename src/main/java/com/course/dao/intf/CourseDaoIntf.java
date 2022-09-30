package com.course.dao.intf;

import java.sql.Date;
import java.time.LocalDate;
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


	/*
	 * * Function: 取得課程可預約狀態 
	 *   CreateBy: Iris 
	 *   CreateDate: 2022/09/27
	 */
	public String getCourseStatusByCourseId(Integer courseId);


	List<Course> selectCourseByGymIdAndStartTime(Integer gymId, LocalDate one, LocalDate seven);
	
	
	/*
	 * * Function: 取得會員已預約課程 
	 *   CreateBy: Iris 
	 *   CreateDate: 2022/09/27
	 */
	public List<Course> selectBookedCourseByMemberIdAndGymId(Integer memId,Integer gymId);


	/*
	 * * Function: 開放團課預約狀態為可預約 
	 *   CreateBy: Iris 
	 *   CreateDate: 2022/09/30
	 */
	public void setCourseEnable(Integer courseId);

}
