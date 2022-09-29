package com.course.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.course.dao.impl.CourseDaoImpl;
import com.course.dao.intf.CourseDaoIntf;
import com.course.service.intf.CourseServiceIntf;
import com.course.vo.Course;

public class CourseServiceImpl implements CourseServiceIntf {

	private CourseDaoIntf _courseDao;
	public CourseServiceImpl() {
		_courseDao = new CourseDaoImpl();
	}
	
	@Override
	public List<Course> selectCourseByGymIdAndStartTime(Course course) {
		final Integer gymId = course.getGymId();
		final LocalDate selectedDate = course.getSelectedDate();
		
		// 拿到所選日期當週第一天 & 最後一天
		course.setDayOne(selectedDate.with(DayOfWeek.MONDAY));
		course.setDaySeven(selectedDate.with(DayOfWeek.SUNDAY));
		
		List<Course> list = new ArrayList<Course>();
		
		try {
			System.out.println(course.getGymId());
			if (gymId == 0) {
				course.setMessage("請選擇場館");
				course.setSuccessful(false);
				list.add(course);
				return list;
			}
			
			
			List<Course> courseList = _courseDao.selectCourseByGymIdAndStartTime(course.getGymId(), course.getDayOne(), course.getDaySeven());	
			return courseList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Course addCourse(Course course) {
		final Integer gymId = course.getGymId();
		final Integer empId = course.getEmpId();
		final LocalDateTime startDate = course.getStartTime();
		final LocalDateTime startTime = course.getStartTime();
		final LocalDateTime endTime = course.getEndTime();
		final String courseName = course.getCourseName();
		final String status = course.getStatus();
		final String pubStatus = course.getPubStatus();
		
		
			try {
				// 判斷項目都要選擇到
				if (gymId == 0) {
					course.setMessage("請選擇場館");
					return course;
				}
				
				if ("".equals(empId)) {
					course.setMessage("請選擇教練");
					return course;
				}
				
				if ("".equals(startDate)) {
					course.setMessage("請選擇日期");
					return course;
				}
				
				if ("".equals(startTime)) {
					course.setMessage("請選擇上課時間");
					return course;
				}
				
				if ("".equals(endTime)) {
					course.setMessage("請選擇下課時間");
					return course;
				}
				
				
				if ("".equals(courseName)) {
					course.setMessage("請選擇課程名稱");
					return course;
				}
				
				if ("".equals(status)) {
					course.setMessage("請選擇預約狀態");
					return course;
				}
				
				if ("".equals(pubStatus)) {
					course.setMessage("請選擇公開狀態");
					return course;
				}
				
				// 新增選擇項目
				_courseDao.insert(course);
				return course;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
	}
	
	@Override
	public Course editCourse(Course course) {
		
		// 
		
		
		return null;
	}

	@Override
	public List<Course> findAll() {
		return _courseDao.selectAll();
	}

	

	
	
}