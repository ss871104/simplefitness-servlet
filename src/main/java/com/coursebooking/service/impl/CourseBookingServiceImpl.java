package com.coursebooking.service.impl;

import java.util.List;

import javax.servlet.jsp.tagext.VariableInfo;

import com.courselist.vo.CourseList;
import com.course.dao.impl.CourseDaoImpl;
import com.course.dao.intf.CourseDaoIntf;
import com.course.vo.Course;
import com.coursebooking.dao.impl.CourseBookingDaoImpl;
import com.coursebooking.dao.intf.CourseBookingDaoIntf;
import com.coursebooking.service.intf.CourseBookingServiceIntf;
import com.coursebooking.vo.CourseBooking;
import com.courselist.dao.intf.CourseListDaoIntf;
import com.courselist.dao.impl.CourseListDaoImpl;

public class CourseBookingServiceImpl implements CourseBookingServiceIntf {

	private CourseBookingDaoIntf _courseBookingDao;
	private CourseListDaoIntf _courseListDao;
	private CourseDaoIntf _courseDao;

	public CourseBookingServiceImpl() {
		_courseBookingDao = new CourseBookingDaoImpl();
		_courseListDao = new CourseListDaoImpl();
		_courseDao = new CourseDaoImpl();
	}
	
	




	@Override
	public Boolean bookCourse(CourseBooking coursebook) {

		// 取得人數上限
		// 裝在 CourseList =>max_p

		var courseList = _courseListDao.getCourseListByCourseId(coursebook.getCourseId());

		// 取得目前預約人數
		var courseBookedCount = _courseBookingDao.getcourseBookedCount(coursebook.getCourseId());
		// 執行判斷
		// 人數跟上限比較
		// 人數>上限 => return false;
		// else => 執行新增。 => return true ;
		if (courseBookedCount >= courseList.getCourseMaxP()) {
			return false;
		}

		var insertCourse = _courseBookingDao.insert(coursebook);

		if (insertCourse) {
			return true;
		}
		return false;

	}

	// 呼叫Dao 執行update (Status="0")
	// 回傳值(Boolean)
	@Override
	public Boolean cancelCourseByMemberId(CourseBooking coursebook) {

		coursebook.setCoursebookStatus("0");
		var cancelCourse = _courseBookingDao.updateStatus(coursebook);

		return cancelCourse;
	}

	// 呼叫Dao 取出該會員的預約清單(List)
	// 回傳值(List<CourseBooking>)
	@Override
	public List<CourseBooking> checkBookingCourseByMemberId(CourseBooking coursebook) {

		var checkBooking = _courseBookingDao.selectByMemberId(coursebook.getMemId());
		return checkBooking;
	}

	//獲取可預約課程清單 
	@Override
	public List<Course> searchCourseByGymIdAndCourseListId(CourseBooking coursebook) {
		
		var courseList=_courseDao.selectCourseByGymIdAndCourseListId(coursebook.getGymId(), coursebook.getCourseListId());

		
		return courseList;
	}


}
