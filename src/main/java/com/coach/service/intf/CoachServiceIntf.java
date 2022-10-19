package com.coach.service.intf;

import java.util.List;

import com.coach.vo.Coach;

public interface CoachServiceIntf {
	
	Coach addCoach(Coach coach);
	
	Coach editCoach(Coach coach);
	
	List<Coach> findAll();
	
	List<Coach> selectCoach(Coach coach);
	
	Coach findById(Coach coach);
	
	Coach deteleCoach(Coach coach);
	
}