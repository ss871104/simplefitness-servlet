package com.coach.dao.intf;

import java.time.LocalDateTime;
import java.util.List;

import com.coach.vo.Coach;
import com.common.dao.CommonDao;


public interface CoachDaoIntf extends CommonDao<Coach, Integer> {

	/* *
	 *  Function:  取得會員可預約教練課
	 *  CreateBy: Iris
	 *  CreateDate: 2022/09/23
	 * */
	public List<Coach> selectCoachByGymIdAndEmpId(Integer gymId, Integer empId);

	/* *
	 *  Function:修改教練課清單狀態
	 *  CreateBy: Iris
	 *  CreateDate: 2022/09/24
	 * */
	public boolean updateStatus(Coach coach);
	
	/*
	 * * Function: 確認此時間此教練是否已有教練課 
	 *   CreateBy: Natalie
	 *   CreateDate: 2022/10/06
	 */
	public List<Coach> selectCoachByEmpIdAndStartTime(Integer empId, LocalDateTime startTime);

}
