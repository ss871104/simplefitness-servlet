package com.coach.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.coach.dao.impl.CoachDaoImpl;
import com.coach.dao.intf.CoachDaoIntf;
import com.coach.service.intf.CoachServiceIntf;
import com.coach.vo.Coach;
import com.course.dao.impl.CourseDaoImpl;
import com.course.dao.intf.CourseDaoIntf;
import com.course.vo.Course;

import net.bytebuddy.utility.dispatcher.JavaDispatcher.IsConstructor;

public class CoachServiceImpl implements CoachServiceIntf {

	private CourseDaoIntf _courseDao;
	private CoachDaoIntf _coachDao;
	
	public CoachServiceImpl() {
		_courseDao = new CourseDaoImpl();
		_coachDao = new CoachDaoImpl();
	}

	@Override
	public Coach addCoach(Coach coach) {
		final Integer gymId = coach.getGymId();
		final Integer empId = coach.getEmpId();
		final LocalDateTime startTime = coach.getStartTime();
		final String status = coach.getStatus();
		final String pubStatus = coach.getPubStatus();
		
		
			try {
				// 判斷項目都要選擇到
				if (gymId == 0) {
					coach.setMessage("請選擇場館");
					coach.setSuccessful(false);
					return coach;
				}
				
				if (empId == 0) {
					coach.setMessage("請選擇教練");
					coach.setSuccessful(false);
					return coach;
				}
			
				
				if ("".equals(startTime)) {
					coach.setMessage("請選擇上課時間");
					coach.setSuccessful(false);
					return coach;
				}
				
				
				if ("".equals(status)) {
					coach.setMessage("請選擇預約狀態");
					coach.setSuccessful(false);
					return coach;
				}
				
				if ("".equals(pubStatus)) {
					coach.setMessage("請選擇公開狀態");
					coach.setSuccessful(false);
					return coach;
				}
				
				coach.setEndTime(coach.getStartTime().plusMinutes(60));

				// 確認sql拿到的資料是否有值
				List<Course> listCourse = new ArrayList<Course>();
				listCourse = _courseDao.selectCourseByEmpIdAndStartTime(coach.getEmpId(), coach.getStartTime());
				List<Coach> listCoach = new ArrayList<Coach>();
				listCoach = _coachDao.selectCoachByEmpIdAndStartTime(coach.getEmpId(), coach.getStartTime());
				
				// 有值 = 已有課，不可insert
				if (!listCourse.isEmpty() || !listCoach.isEmpty()) {
					coach.setMessage("教練不會影分身啦!這個時間有課了!");
					coach.setSuccessful(false);
					return coach;
				} 
				
				// 新增選擇項目
				_coachDao.insert(coach);
				coach.setSuccessful(true);
				return coach;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
	}
	
	@Override
	public Coach editCoach(Coach coach) {
		
		try {
			if (coach.getGymId() == 0) {
				coach.setMessage("請選擇場館");
				coach.setSuccessful(false);
				return coach;
			}
			coach.setEndTime(coach.getStartTime().plusMinutes(60));
			_coachDao.update(coach);
			coach.setMessage("編輯成功");
			coach.setSuccessful(true);
			return coach;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	@Override
	public List<Coach> findAll() {
		return _coachDao.selectAll();
	}

	@Override
	public List<Coach> selectCoach(Coach coach) {
		final Integer gymId = coach.getGymId();
		final LocalDate selectedDate = coach.getSelectedDate();
		final Integer empId = coach.getEmpId();
		
		// 拿到所選日期當週第一天 & 最後一天
		coach.setDayOne(selectedDate.with(DayOfWeek.MONDAY));
		coach.setDaySeven(selectedDate.with(DayOfWeek.SUNDAY));
		
		List<Coach> list = new ArrayList<Coach>();
		
		try {
			if (gymId == 0) {
				coach.setMessage("請選擇場館");
				coach.setSuccessful(false);
				list.add(coach);
				return list;
			}
			System.out.println(empId);
			// 條件沒選教練跑沒教練的dao
			if(empId == null || empId == 0) {
				list = _coachDao.selectCoachByGymIdAndStartTime(coach.getGymId(), coach.getDayOne(), coach.getDaySeven());				
			} else {
				list = _coachDao.selectCoachByGymIdAndStartTimeAndEmpId(coach.getGymId(), coach.getDayOne(), coach.getDaySeven(), coach.getEmpId());
			}
			
			return list;	
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Coach findById(Coach coach) {
		return _coachDao.selectById(coach.getCoaId());
	}

	@Override
	public Coach deteleCoach(Coach coach) {

		// 更新公開狀態 = 2:下架
		try {
			coach.setCoaId(coach.getCoaId());
			coach.setPubStatus("2");
			_coachDao.updateCoachPublicStatus(coach);
			coach.setSuccessful(true);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return coach;
		
	}
	
	

	
	
}