package com.course.dao.impl;

import static com.course.dao.sql.CourseDaoSQL.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.course.dao.intf.CourseDaoIntf;
import com.course.vo.Course;
import com.coursebooking.vo.CourseBooking;

public class CourseDaoImpl implements CourseDaoIntf {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Test");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(Course courseVo) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courseVo.getEmpId());
			pstmt.setInt(2, courseVo.getGymId());
			pstmt.setInt(3, courseVo.getCourseListId());
			pstmt.setObject(4, courseVo.getStartTime());
			pstmt.setObject(5, courseVo.getEndTime());
			pstmt.setString(6, courseVo.getStatus());
			pstmt.setString(7, courseVo.getPubStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean update(Course courseVo) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courseVo.getEmpId());
			pstmt.setInt(2, courseVo.getGymId());
			pstmt.setInt(3, courseVo.getCourseListId());
			pstmt.setObject(4, courseVo.getStartTime());
			pstmt.setObject(5, courseVo.getEndTime());
			pstmt.setString(6, courseVo.getStatus());
			pstmt.setString(7, courseVo.getPubStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean delete(Integer courseId) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(DELETE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courseId);

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;

	}

	@Override
	public Course selectById(Integer courseId) {

		Course course = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courseId);

			try (ResultSet rs = pstmt.executeQuery()) {

				course = new Course();

				if (rs.next()) {
					course.setCourseId(rs.getInt("cour_id"));
					course.setEmpId(rs.getInt("emp_id"));
					course.setGymId(rs.getInt("gym_id"));
					course.setCourseListId(rs.getInt("cour_list_id"));
					course.setStartTime(rs.getObject("start_time", LocalDateTime.class));
					course.setEndTime(rs.getObject("end_time", LocalDateTime.class));
					course.setUploadTime(rs.getObject("upload_time", LocalDateTime.class));
					course.setStatus(rs.getString("status"));
					course.setPubStatus(rs.getString("public"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}

	@Override
	public List<Course> selectAll() {

		List<Course> list = new ArrayList<Course>();
		Course course = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_ALL);) {

			System.out.println("連線成功");

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					course = new Course();
					course.setCourseId(rs.getInt("cour_id"));
					course.setEmpId(rs.getInt("emp_id"));
					course.setGymId(rs.getInt("gym_id"));
					course.setCourseListId(rs.getInt("cour_list_id"));
					course.setStartTime(rs.getObject("start_time", LocalDateTime.class));
					course.setEndTime(rs.getObject("end_time", LocalDateTime.class));
					course.setUploadTime(rs.getObject("upload_time", LocalDateTime.class));
					course.setStatus(rs.getString("status"));
					course.setPubStatus(rs.getString("public"));
					list.add(course);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Course selectByGymDate(Integer gymId, Date courseDate) {

		Course course = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, gymId);
			pstmt.setDate(2, courseDate);

			try (ResultSet rs = pstmt.executeQuery()) {

				course = new Course();

				if (rs.next()) {
					course.setCourseId(rs.getInt("cour_id"));
					course.setEmpId(rs.getInt("emp_id"));
					course.setGymId(rs.getInt("gym_id"));
					course.setCourseListId(rs.getInt("cour_list_id"));
					course.setStartTime(rs.getObject("start_time", LocalDateTime.class));
					course.setEndTime(rs.getObject("end_time", LocalDateTime.class));
					course.setUploadTime(rs.getObject("upload_time", LocalDateTime.class));
					course.setStatus(rs.getString("status"));
					course.setPubStatus(rs.getString("public"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}

	/*
	 * * Function: 取得會員可預約團課 CreateBy: Iris CreateDate: 2022/09/21
	 */
	@Override
	public List<Course> selectCourseByGymIdAndCourseListId(Integer gymId, Integer courseListId) {

		Course course = null;
		var sqlStr = "select cour_id,gym.gym_id,gym_name,emp_name,courseType.cour_list_id,cour_name,start_time,end_time\r\n"
				+ "from course course \r\n"
				+ "join cour_list courseType on course.cour_list_id=courseType.cour_list_id \r\n"
				+ "join gym on course.gym_id = gym.gym_id\r\n" + "join emp on course.emp_id = emp.emp_id\r\n"
				+ "where course.start_time >now() and courseType.`status`='1' and course.`status`='1' and public='1' and gym.gym_id=? and courseType.cour_list_id=?;";

		List<Course> canBookCourseList = new ArrayList<Course>();

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sqlStr);) {

			System.out.println("連線成功");

			pstmt.setInt(1, gymId);
			pstmt.setInt(2, courseListId);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					course = new Course();
					course.setCourseId(rs.getInt("cour_id"));
					course.setGymId(rs.getInt("gym_id"));
					course.setGymName(rs.getString("gym_name"));
					course.setCourseListId(rs.getInt("cour_list_id"));
					course.setCourseName(rs.getString("cour_name"));
					course.setEmpName(rs.getString("emp_name"));
					course.setStartTime(rs.getObject("start_time", LocalDateTime.class));
					course.setEndTime(rs.getObject("end_time", LocalDateTime.class));

					canBookCourseList.add(course);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return canBookCourseList;
	}

	/*
	 * * Function: 更新團課預約狀態(Course) CreateBy: Iris CreateDate: 2022/09/26
	 */
	public boolean updateStatus(Course course) {
		boolean flag = true;
		var sqlstr = "update course set status=? where cour_id  = ?";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sqlstr);) {

			System.out.println("連線成功");

			pstmt.setString(1, course.getStatus());
			pstmt.setInt(2, course.getCourseId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}

	// 取得已安排團課
	public List<Course> selectCourseByGymIdAndStartTime(Integer gymId, LocalDate one, LocalDate seven) {

		List<Course> list = new ArrayList<Course>();
		Course course = null;

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(SELECT_BY_GYMID_AND_STARTTIME);) {

			System.out.println("連線成功");

			pstmt.setInt(1, gymId);
			pstmt.setObject(2, one);
			pstmt.setObject(3, seven);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					course = new Course();
					course.setGymId(rs.getInt("gym_id"));
					course.setStartTime(rs.getObject("start_time", LocalDateTime.class));
					course.setCourseListId(rs.getInt("cour_list_id"));
					course.setEmpId(rs.getInt("emp_id"));
					course.setStatus(rs.getString("status"));
					course.setPubStatus(rs.getString("public"));
					course.setSuccessful(true);

					list.add(course);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	/*
	 * * Function: 取得課程開放狀態 CreateBy: Iris CreateDate: 2022/09/27
	 */
	public String getCourseStatusByCourseId(Integer courseId) {
		var result = "";
		var sqlStr = "SELECT status FROM simple_fitness.course Where  cour_id=?;";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sqlStr);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courseId);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					result = rs.getString("status");

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return result;
	}

	/*
	 * * Function: 取得會員已預約課程 CreateBy: Iris CreateDate: 2022/09/27
	 */
	public List<Course> selectBookedCourseByMemberIdAndGymId(Integer memId, Integer gymId) {
		Course course = null;
		List<Course> courseBookedList = new ArrayList<Course>();
		var sqlStr = "select cour_book_id,mem_id,gym.gym_id,gym_name,cour_name,emp_name,cour_booking.cour_id,cour_booking.`status`,course.cour_list_id,start_time,end_time \r\n"
				+ "from cour_booking  \r\n" + "join course on cour_booking.cour_id = course.cour_id \r\n"
				+ "join cour_list on course.cour_list_id = cour_list.cour_list_id \r\n"
				+ "join gym on course.gym_id = gym.gym_id\r\n" + "join emp on course.emp_id = emp.emp_id\r\n"
				+ "where cour_booking.`status`='1' and mem_id = ? and gym.gym_id = ?;";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sqlStr);) {

			System.out.println("連線成功");

			pstmt.setInt(1, memId);
			pstmt.setInt(2, gymId);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					course = new Course();
					course.setCourseId(rs.getInt("cour_id"));
					course.setGymId(rs.getInt("gym_id"));
					course.setGymName(rs.getString("gym_name"));
					course.setCourseListId(rs.getInt("cour_list_id"));
					course.setCourseBookId(rs.getInt("cour_book_id"));
					course.setCourseName(rs.getString("cour_name"));
					course.setEmpName(rs.getString("emp_name"));
					course.setStartTime(rs.getObject("start_time", LocalDateTime.class));
					course.setEndTime(rs.getObject("end_time", LocalDateTime.class));

					courseBookedList.add(course);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseBookedList;
	}

	/*
	 * * Function: 開放團課預約狀態為可預約 CreateBy: Iris CreateDate: 2022/09/30
	 */
	public void setCourseEnable(Integer courseId) {
		var sqlStr = "Update simple_fitness.course " + "Set `status` ='1' " + "Where cour_id=?;";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sqlStr);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courseId);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
