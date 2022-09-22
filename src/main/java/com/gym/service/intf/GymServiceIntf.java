package com.gym.service.intf;

import java.util.List;

import com.common.service.CommonServiceHibernate;
import com.gym.vo.Gym;

public interface GymServiceIntf extends CommonServiceHibernate{
	
	Gym add(Gym gym);
	
	Gym edit(Gym gym);
	
	Gym findById(Gym gym);
	
	List<Gym> findAll();
	
}
