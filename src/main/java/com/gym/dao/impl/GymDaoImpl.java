package com.gym.dao.impl;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;

import com.gym.dao.intf.GymDaoIntf;
import com.gym.vo.Gym;
import com.gym.vo.GymPic;

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
		final StringBuilder hql = new StringBuilder()
				.append("UPDATE Gym SET ");
			hql.append("gym_name = :gymName,")
				.append("address = :address,")
				.append("phone = :phone,")
				.append("open_date = :openDate, ")
				.append("open_time = :openTime, ")
				.append("close_time = :closeTime, ")
				.append("max_p = :maxPeople, ")
				.append("intro = :intro ")
				.append("where gym_id = :gymId");

			Query<?> query = getSession().createQuery(hql.toString());
			return query
					.setParameter("gymName", gym.getGymName())
					.setParameter("address", gym.getAddress())
					.setParameter("phone", gym.getPhone())
					.setParameter("openDate", gym.getOpenDate())
					.setParameter("openTime", gym.getOpenTime())
					.setParameter("closeTime", gym.getCloseTime())
					.setParameter("maxPeople", gym.getMaxPeople())
					.setParameter("intro", gym.getIntro())
					.setParameter("gymId", gym.getGymId())
					.executeUpdate() > 0;
	}

	@Override
	public Gym selectById(Integer id) {
		Query<Gym> query = getSession().createQuery("FROM Gym WHERE gym_id = :gymId", Gym.class);
		query.setParameter("gymId", id);	
		Gym gym = query.uniqueResult();
		return gym;
	}

	@Override
	public List<Gym> selectAll() {
		Query<Gym> query = getSession().createQuery("FROM Gym ", Gym.class);
		List<Gym> list = query.list();
		return list;
	}
	
	@Override
	public List<GymPic> selectPicById(Integer id) {
		Query<GymPic> query = getSession().createQuery("SELECT new com.gym.vo.GymPic(pic) FROM GymPic where gymId = :gymId ", GymPic.class);
		query.setParameter("gymId", id);
		List<GymPic> list = query.list();
		return list;
	}
	
	@Override
	public List<Gym> joinSelectByGymId(Integer gymId) {
		Query<Gym> query = getSession().createQuery("SELECT DISTINCT g from Gym g left join g.gymPics p where p.gymId = :gymId", Gym.class);
		query.setParameter("gymId", gymId);
		List<Gym> list = query.list();
		System.out.println(list);
		return list;
	}

	@Override
	public List<GymPic> updatePicByGymId(Integer gymId) {
		// TODO Auto-generated method stub
		return null;
	}

}
