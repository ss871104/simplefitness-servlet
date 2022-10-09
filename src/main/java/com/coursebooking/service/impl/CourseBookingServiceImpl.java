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
		var courseBookedCount = _courseBookingDao.getCourseBookedCount(courseId);

		// 執行判斷
		// 人數跟上限比較
		// 人數<上限 => return false;
		// else => 執行修改額滿狀況。 => (course Status="3");
		if (courseBookedCount >= courseList.getCourseMaxP()) {
			Course course = new Course();
			course.setCourseId(courseId);
			course.setStatus("3");
			_courseDao.updateCourseStatus(course);
		}
	}

	// 會員取消已預約課程(coursebook Status="0")
	public Boolean cancelCourseByMemberId(CourseBooking coursebook) {

		coursebook.setCoursebookStatus("0");
		return _courseBookingDao.updateStatus(coursebook);

	}

	// 取出該會員的預約清單(List)
	public List<CourseBooking> checkBookingCourseByMemberId(CourseBooking coursebook) {
		List<CourseBooking> courseBookingList = _courseBookingDao
				.selectBookedCourseByMemberIdAndGymId(coursebook.getMemId(), coursebook.getGymId());
		
		if(!courseBookingList.isEmpty()) {
			courseBookingList.forEach(courseBooking -> {
				courseBooking.setCourseDetail(_courseDao.selectCourseClassDetailByCourseId(courseBooking.getCourseId()));
			});
		}		
		return courseBookingList;
	}

	// 獲取可預約團課課程清單
	public List<Course> searchCourseByGymIdAndCourseListId(CourseBooking coursebook) {

		return _courseDao.selectCourseByGymIdAndCourseListId(coursebook.getGymId(), coursebook.getCourseListId());

	}

	// 開放課程狀態為可預約
	public void setCourseBookingEnable(Integer courseId) {
		_courseDao.setCourseEnable(courseId);

	}

}
