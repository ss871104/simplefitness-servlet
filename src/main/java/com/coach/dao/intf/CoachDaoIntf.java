package com.coach.dao.intf;

import com.common.dao.CommonDao;

import java.util.List;

import com.coach.vo.Coach;


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
	


}
