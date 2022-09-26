package com.coursebooking.service.impl;

import java.util.List;

import com.course.dao.impl.CourseDaoImpl;
import com.course.dao.intf.CourseDaoIntf;
import com.course.vo.Course;
import com.coursebooking.dao.impl.CourseBookingDaoImpl;
import com.coursebooking.dao.intf.CourseBookingDaoIntf;
import com.coursebooking.service.intf.CourseBookingServiceIntf;
import com.coursebooking.vo.CourseBooking;
import com.courselist.dao.impl.CourseListDaoImpl;
import com.courselist.dao.intf.CourseListDaoIntf;

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
		// 取得課程狀態
		var courseStatus = _courseDao.getCourseStatusByCourseId(coursebook.getCourseId());
		// 若不為可預約狀態 回false
		if (!"1".equals(courseStatus)) {
			return false;
		}
		// 可預約狀態，將此預約課程狀態設為"1"後insert
		coursebook.setCoursebookStatus("1");
		return _courseBookingDao.insert(coursebook);
	}

	// 修改團課預約狀態為額滿(course Status="3")
	public void setCourseBookingFull(Integer courseId) {

		// 取得人數上限
		// 裝在 CourseList =>max_p

		var courseList = _courseListDao.getCourseListByCourseId(courseId);

		// 取得目前預約人數
		var courseBookedCount = _courseBookingDao.getcourseBookedCount(courseId);

		// 執行判斷
		// 人數跟上限比較
		// 人數<上限 => return false;
		// else => 執行修改額滿狀況。 => (course Status="3");
		if (courseBookedCount >= courseList.getCourseMaxP()) {
			Course course = new Course();
			course.setCourseId(courseId);
			course.setStatus("3");
			_courseDao.updateStatus(course);
		}
	}

	// 呼叫Dao 執行update (Status="0")
	// 回傳值(Boolean)
	@Override
	public Boolean cancelCourseByMemberId(CourseBooking coursebook) {

		coursebook.setCoursebookStatus("0");
		return _courseBookingDao.updateStatus(coursebook);

	}

	// 呼叫Dao 取出該會員的預約清單(List)
	// 回傳值(List<CourseBooking>)
	@Override
	public List<CourseBooking> checkBookingCourseByMemberId(CourseBooking coursebook) {

		return _courseBookingDao.selectByMemberId(coursebook.getMemId());
	}

	// 獲取可預約團課課程清單
	@Override
	public List<Course> searchCourseByGymIdAndCourseListId(CourseBooking coursebook) {

		return _courseDao.selectCourseByGymIdAndCourseListId(coursebook.getGymId(), coursebook.getCourseListId());

	}

}
