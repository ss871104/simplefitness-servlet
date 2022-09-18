package com.coursebooking.service.impl;

import java.util.List;

import com.course.vo.Course;
import com.courlist.vo.CourList;
import com.coursebooking.dao.impl.CourseBookingDaoImpl;
import com.coursebooking.dao.intf.CourseBookingDaoIntf;
import com.coursebooking.service.intf.CourseBookingServiceIntf;
import com.coursebooking.vo.CourseBooking;
import com.courlist.dao.impl.CourListDaoImpl;
import com.courlist.dao.intf.CourListDaoIntf;
public class CourseBookingServiceImpl implements CourseBookingServiceIntf {

	private CourseBookingDaoIntf dao;
	private CourListDaoIntf _courseListDao;

	public CourseBookingServiceImpl() {
		dao = new CourseBookingDaoImpl();
		_courseListDao = new CourListDaoImpl();
	}

	
	@Override
	public CourseBooking booking(CourseBooking coursebook) {
		final CourseBooking booking=dao.selectById(coursebook.getCoursebookId());
		
		return null;
	}

	@Override
	public CourseBooking memCancel(CourseBooking coursebook) {
		final CourseBooking mCancel=dao.selectById(coursebook.getCoursebookId());
		coursebook.setCoursebookStatus(mCancel.getCoursebookStatus());
//		if(dao.updateStatus) {
//			return null;
//		}
		return null;
	}
	
	
	
	@Override
	public CourseBooking empCancel(CourseBooking coursebook) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CourseBooking check(CourseBooking coursebook) {
		
		return null;
	}

	@Override
	public List<CourseBooking> findAll() {
		return dao.selectAll();
	}


	@Override
	public List<CourList> getCourseList(CourList courseType) {
		// TODO Auto-generated method stub
		
		return _courseListDao.selectAll();
	}

	
}
