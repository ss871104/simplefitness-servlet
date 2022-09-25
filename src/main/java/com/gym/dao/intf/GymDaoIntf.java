package com.gym.dao.intf;

import java.util.List;

import com.common.dao.CommonDaoHibernate;
import com.gym.vo.Gym;
import com.gym.vo.GymPic;

public interface GymDaoIntf extends CommonDaoHibernate<Gym, Integer>{
	
	public List<GymPic> selectPicById(Integer id);
	
	public List<Gym> joinSelectByGymId(Integer gymId);
	
	public List<GymPic> updatePicByGymId(Integer gymId);

}
