package com.coach.dao.intf;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.coach.vo.Coach;
import com.common.dao.CommonDao;
import com.course.vo.Course;


public interface CoachDaoIntf extends CommonDao<Coach, Integer> {

	/* *
	 *  Function:  取得會員可預約教練課
	 *  CreateBy: Iris
	 *  CreateDate: 2022/09/23
	 * */
	public List<Coach> selectCoachByGymIdAndEmpId(Integer gymId, Integer empId);

	/* *
	 *  Function:取得會員已預約、待審核教練課
	 *  CreateBy: Iris
	 *  CreateDate: 2022/10/06
	 * */
	public Coach selectCoachClassDetailByCoachId(Integer coachId);

	/* *
	 *  Function:修改教練課清單狀態
	 *  CreateBy: Iris
	 *  CreateDate: 2022/09/24
	 * */
	public boolean updateCoachStatus(Coach coach);

	/*
	 * * Function: 開放教練課預約狀態為可預約 
	 *   CreateBy: Iris 
	 *   CreateDate: 2022/10/06
	 */
	public void setCoachEnable(Integer coaId);

	/*
	 * * Function: 確認此時間此教練是否已有教練課 
	 *   CreateBy: Natalie
	 *   CreateDate: 2022/10/06
	 */
	public List<Coach> selectCoachByEmpIdAndStartTime(Integer empId, LocalDateTime startTime);
	
	/*
	 * * Function: 取得已安排教練課
	 *   CreateBy: Natalie
	 *   CreateDate: 2022/10/14
	 */
	public List<Coach> selectCoachByGymIdAndStartTime(Integer gymId, LocalDate one, LocalDate seven);

	/* *
	 *  Function: 變更團課公開狀態
	 *  CreateBy: Natalie
	 *  CreateDate: 2022/10/18
	 * */
	public boolean updateCoachPublicStatus(Coach coach);
}
