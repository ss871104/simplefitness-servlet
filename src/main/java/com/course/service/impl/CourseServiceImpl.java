package com.course.service.impl;

import java.io.Console;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.result.NoMoreReturnsException;

import com.coach.dao.impl.CoachDaoImpl;
import com.coach.dao.intf.CoachDaoIntf;
import com.coach.vo.Coach;
import com.course.dao.impl.CourseDaoImpl;
import com.course.dao.intf.CourseDaoIntf;
import com.course.service.intf.CourseServiceIntf;
import com.course.vo.Course;

import lombok.experimental.var;
import net.bytebuddy.utility.dispatcher.JavaDispatcher.IsConstructor;

public class CourseServiceImpl implements CourseServiceIntf {

	private CourseDaoIntf _courseDao;
	private CoachDaoIntf _coachDao;
	public CourseServiceImpl() {
		_courseDao = new CourseDaoImpl();
		_coachDao = new CoachDaoImpl();
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
			if (gymId == 0) {
				course.setMessage("請選擇場館");
				course.setSuccessful(false);
				list.add(course);
				return list;
			}
					
			list = _courseDao.selectCourseByGymIdAndStartTime(course.getGymId(), course.getDayOne(), course.getDaySeven());
			
			return list;	
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Course addCourse(Course course) {
		final Integer gymId = course.getGymId();
		final Integer empId = course.getEmpId();
		final LocalDateTime startTime = course.getStartTime();
		final String courseName = course.getCourseName();
		final String status = course.getStatus();
		final String pubStatus = course.getPubStatus();
		
		
			try {
				// 判斷項目都要選擇到
				if (gymId == 0) {
					course.setMessage("請選擇場館");
					course.setSuccessful(false);
					return course;
				}
				
				if (empId == 0) {
					course.setMessage("請選擇教練");
					course.setSuccessful(false);
					return course;
				}
			
				
				if ("".equals(startTime)) {
					course.setMessage("請選擇上課時間");
					course.setSuccessful(false);
					return course;
				}
				
				if ("".equals(courseName)) {
					course.setMessage("請選擇課程名稱");
					course.setSuccessful(false);
					return course;
				}
				
				if ("".equals(status)) {
					course.setMessage("請選擇預約狀態");
					course.setSuccessful(false);
					return course;
				}
				
				if ("".equals(pubStatus)) {
					course.setMessage("請選擇公開狀態");
					course.setSuccessful(false);
					return course;
				}
				course.setEndTime(course.getStartTime().plusMinutes(60));

				// 確認sql拿到的資料是否有值
				List<Course> listCourse = new ArrayList<Course>();
				listCourse = _courseDao.selectCourseByEmpIdAndStartTime(course.getEmpId(), course.getStartTime());
				List<Coach> listCoach = new ArrayList<Coach>();
				listCoach = _coachDao.selectCoachByEmpIdAndStartTime(course.getEmpId(), course.getStartTime());
				
				// 有值 = 已有課，不可insert
				if (!listCourse.isEmpty() || !listCoach.isEmpty()) {
					course.setMessage("教練不會影分身啦!這個時間有課了!");
					course.setSuccessful(false);
					return course;
				} 
				
				// 新增選擇項目
				_courseDao.insert(course);
				course.setSuccessful(true);
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

//	@Override
//	public List<Course> checkCourseExists(Course course) {
//		
//		try {
//			// 確認sql拿到的資料是否有值
//			List<Course> list = _courseDao.selectCourseByEmpIdAndStartTime(course.getEmpId(),course.getStartTime());
//			System.out.println(list);
//			
//			// 沒值=可insert
//			if (list.isEmpty()) {
//				course.setSuccessful(true);
//			} else {
//				course.setSuccessful(false);
//			}
//			return list;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

	

	
	
}