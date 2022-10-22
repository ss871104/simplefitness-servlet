package com.course.dao.intf;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.common.dao.CommonDao;
import com.course.vo.Course;
import com.coursebooking.vo.CourseBooking;


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
	public boolean updateCourseStatus(Course course);


	/*
	 * * Function: 取得課程可預約狀態 
	 *   CreateBy: Iris 
	 *   CreateDate: 2022/09/27
	 */
	public String getCourseStatusByCourseId(Integer courseId);


	List<Course> selectCourseByGymIdAndStartTime(Integer gymId, LocalDate one, LocalDate seven);


	/*
	 * * Function: 開放團課預約狀態為可預約 
	 *   CreateBy: Iris 
	 *   CreateDate: 2022/09/30
	 */
	public void setCourseEnable(Integer courseId);
	
	
	/*
	 * * Function: 確認此時間此教練是否已有團課  
	 *   CreateBy: Natalie
	 *   CreateDate: 2022/10/06
	 */
	public List<Course> selectCourseByEmpIdAndStartTime(Integer empId, LocalDateTime startTime);


	/* *
	 *  Function:取得會員團課程詳細資料
	 *  CreateBy: Iris
	 *  CreateDate: 2022/10/09
	 * */
	public Course selectCourseClassDetailByCourseId(Integer courseId);


	/* *
	 *  Function:取得團課課程清單詳細資料
	 *  CreateBy: Iris
	 *  CreateDate: 2022/10/11
	 * */
	public List<Course> selectCourseDetailList(Integer empId);

	/* *
	 *  Function: 變更團課公開狀態
	 *  CreateBy: Natalie
	 *  CreateDate: 2022/10/12
	 * */
	public boolean updateCoursePublicStatus(Course course);

	/* *
	 *  Function: 取得團課已預約會員清單
	 *  CreateBy: Natalie
	 *  CreateDate: 2022/10/21
	 * */
	public List<Course> selectBookedMemberByCourseId(Integer courseId);

}
