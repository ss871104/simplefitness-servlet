package com.coachbooking.service.intf;

import java.util.List;

import com.coach.vo.Coach;
import com.coachbooking.vo.CoachBooking;
import com.emp.vo.Employee;

public interface CoachBookingServiceIntf {
	
	public List<Employee> searchCoachByJob();

	public List<Coach> searchCoachByGymIdAndEmpId(CoachBooking coachbook);

	public boolean createCoachBooking(CoachBooking coachbook);
	
	public boolean setCoachStatusUnableBooking(Coach coach);
	
	public boolean cancelCoachByCoachBookingId(CoachBooking coachbook);
	
	public List<CoachBooking> checkBookingCoachByMemberId(CoachBooking coachbook);
	
	public void setCoachBookingEnable(Integer coachId);
	
	
	//寄送預約確認信給教練
	public void sendCheckBookingMail(Integer empId);
	
	//寄送取消預約信給教練
	public void sendCancelMailToCaoch(Integer empId);

	//寄送預約成功信給會員
	public void sendBookingSuccessMail(Integer memberId);
	
	//寄送取消預約信給會員
	public void sendCancelMailToMember(Integer memberId);

	public List<CoachBooking> checkBookingCoachByEmpId(CoachBooking coachBooking);

	public boolean acceptCoachByCoachBookingId(CoachBooking coachBooking);
}
