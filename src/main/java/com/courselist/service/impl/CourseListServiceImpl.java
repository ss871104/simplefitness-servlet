package com.courselist.service.impl;

import java.util.List;

import com.courselist.dao.impl.CourseListDaoImpl;
import com.courselist.dao.intf.CourseListDaoIntf;
import com.courselist.service.intf.CourseListServiceIntf;
import com.courselist.vo.CourseList;

public class CourseListServiceImpl implements CourseListServiceIntf {
	
	private CourseListDaoIntf dao;
	public CourseListServiceImpl() {
		dao = new CourseListDaoImpl();
	}

	@Override
	public CourseList add(CourseList cList) {
		final String name = cList.getCourseName();
		final Integer count = cList.getCourseMaxP();
		final String intro = cList.getCourseIntro();
		final String status = cList.getCourseStatus();
		
		if ("".equals(name)) {
			cList.setMessage("名稱未輸入");
			cList.setSuccessful(false);
			return cList;
		}
		if (count == null) {
			cList.setMessage("人數上限未輸入");
			cList.setSuccessful(false);
			return cList;
		}
		if ("".equals(intro)) {
			cList.setMessage("簡介未輸入");
			cList.setSuccessful(false);
			return cList;
		}
		if ("".equals(status)) {
			cList.setMessage("狀態未輸入");
			cList.setSuccessful(false);
			return cList;
		}
		
		if (dao.insert(cList) == false) {
			cList.setMessage("新增錯誤，請聯絡管理員!");
			cList.setSuccessful(false);
			return cList;
		}
		
		cList.setSuccessful(true);
		return cList;
	}

	@Override
	public CourseList edit(CourseList cList) {
		if (cList.getCourseName().isEmpty()) {
			cList.setMessage("團體課名稱未輸入");
			cList.setSuccessful(false);
			return cList;
		}
		if (cList.getCourseMaxP() == null) {
			cList.setMessage("人數上限未輸入");
			cList.setSuccessful(false);
			return cList;
		}
		if (cList.getCourseIntro().isEmpty()) {
			cList.setMessage("團體課簡介未輸入");
			cList.setSuccessful(false);
			return cList;
		}
		if (cList.getCourseStatus().isEmpty()) {
			cList.setMessage("狀態未輸入");
			cList.setSuccessful(false);
			return cList;
		}
		if (dao.update(cList) == false) {
			cList.setMessage("編輯發生錯誤!");
			cList.setSuccessful(false);
			return cList;
		}
		cList.setMessage("編輯成功");
		cList.setSuccessful(true);
		return cList;
	}

	@Override
	public CourseList findById(CourseList cList) {
		return dao.selectById(cList.getCourseListId());
	}

	@Override
	public List<CourseList> findAll() {
		return dao.selectAll();
	}
	
	

}
