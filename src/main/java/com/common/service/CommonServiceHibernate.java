package com.common.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import static com.common.util.HibernateUtil.*;

public interface CommonServiceHibernate {

//	default Transaction beginTransaction() {
//		return getSession().beginTransaction();
//	}
//	
//	default void commit() {
//		getTransaction().commit();;
//	}
//	
//	default void rollback() {
//		getTransaction().rollback();
//	}
	
	default Transaction getTransaction() {
		return getSession().getTransaction();
	}
	
	default Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
	
}
