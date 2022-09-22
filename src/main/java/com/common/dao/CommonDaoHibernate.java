package com.common.dao;

import static com.common.util.HibernateUtil.*;

import java.util.List;

import org.hibernate.Session;

public interface CommonDaoHibernate<P, I> {
	
	boolean insert(P pojo);

	boolean deleteById(I id);

	boolean update(P pojo);

	P selectById(I id);

	List<P> selectAll();
	
	default Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
}
