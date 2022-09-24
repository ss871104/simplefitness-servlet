package com.coachbooking.service.intf;

import java.util.List;

import com.coach.vo.Coach;
import com.coachbooking.vo.CoachBooking;

public interface CoachBookingServiceIntf {

	public List<Coach> searchCoachByGymIdAndEmpId(CoachBooking coachbook);

	public boolean createCoachBooking(CoachBooking coachbook);
	
	public boolean setCoachStatusUnableBooking(Coach coach);
	
	public boolean sendInvitationToCoach(CoachBooking coachbook);
	
	public boolean cancelCoachByMemberId(CoachBooking coachbook);
	
	public List<CoachBooking> checkBookingCourseByMemberId(CoachBooking coachbook);
}
