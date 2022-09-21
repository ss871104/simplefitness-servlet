package com.gym.dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import com.gym.dao.intf.GymDaoIntf;
import com.gym.vo.Gym;

public class GymDaoImpl implements GymDaoIntf {

	@Override
	public boolean insert(Gym gym) {
		getSession().persist(gym);
		return true;
	}

	@Override
	public boolean deleteById(Integer id) {
		Gym gym = new Gym();
		gym.setGymId(id);
		getSession().remove(gym);
		return true;
	}

	@Override
	public boolean update(Gym gym) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Gym selectById(Integer id) {
		return getSession().load(Gym.class, id);
	}

	@Override
	public List<Gym> selectAll() {
		Query<Gym> query = getSession().createQuery("FROM Gym", Gym.class);
		List<Gym> list = (List<Gym>) query.list();
		return list;
	}

}
