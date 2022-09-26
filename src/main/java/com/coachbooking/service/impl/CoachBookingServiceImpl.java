package com.coachbooking.service.impl;

import java.util.List;

import com.coach.dao.impl.CoachDaoImpl;
import com.coach.dao.intf.CoachDaoIntf;
import com.coach.vo.Coach;
import com.coachbooking.dao.impl.CoachBookingDaoImpl;
import com.coachbooking.dao.intf.CoachBookingDaoIntf;
import com.coachbooking.service.intf.CoachBookingServiceIntf;
import com.coachbooking.vo.CoachBooking;





public class CoachBookingServiceImpl implements CoachBookingServiceIntf {

	private CoachBookingDaoIntf _coachBookingDao;
	private CoachDaoIntf _coachDao;

	public CoachBookingServiceImpl() {
		_coachBookingDao = new CoachBookingDaoImpl();
		_coachDao = new CoachDaoImpl();
	}

	// 獲取可預約教練課課程清單
	public List<Coach> searchCoachByGymIdAndEmpId(CoachBooking coachbook) {
		
		var coachlist = _coachDao.selectCoachByGymIdAndEmpId(coachbook.getGymId(),coachbook.getEmpId());

		return coachlist;
	}
	
	
	
	//成立教練課預約(coachbooking呼叫insert)
	public boolean createCoachBooking(CoachBooking coachbook) {
		return _coachBookingDao.insert(coachbook);
	}
	
	//修改教練課清單狀態為不可預約(coach status="2")
	public boolean setCoachStatusUnableBooking(Coach coach) {
		coach.setStatus("2");
		return _coachDao.updateStatus(coach);
	}
	
	
	
	//寄送預約邀請(目前沒用到，等要加寄信功能在使用)
	public boolean sendInvitationToCoach(CoachBooking coachbook) {		
		//預約狀態為待審核(status="1")
		coachbook.setCoachbookStatus("1");
		return _coachBookingDao.updateStatus(coachbook); 
		
	}
	
	//會員取消教練課
	public boolean cancelCoachByMemberId(CoachBooking coachbook) {
		coachbook.setCoachbookStatus("0");
		return _coachBookingDao.updateStatus(coachbook);
	}
	//TODO:後面要在加通知教練課程取消的method
	
	
	//取出該會員的預約清單
	public List<CoachBooking> checkBookingCourseByMemberId(CoachBooking coachbook) {

		return _coachBookingDao.selectByMemberId(coachbook.getMemId());
	}

}
