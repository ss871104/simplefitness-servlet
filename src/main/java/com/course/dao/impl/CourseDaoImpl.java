package com.course.dao.impl;

import static com.course.dao.sql.CourseDaoSQL.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.course.dao.intf.CourseDaoIntf;
import com.course.vo.Course;

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
					course.setStartTime(rs.getObject("start_time",LocalDateTime.class));
					course.setEndTime(rs.getObject("end_time",LocalDateTime.class));
					course.setUploadTime(rs.getObject("upload_time",LocalDateTime.class));
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
					course.setStartTime(rs.getObject("start_time",LocalDateTime.class));
					course.setEndTime(rs.getObject("end_time",LocalDateTime.class));
					course.setUploadTime(rs.getObject("upload_time",LocalDateTime.class));
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
					course.setStartTime(rs.getObject("start_time",LocalDateTime.class));
					course.setEndTime(rs.getObject("end_time",LocalDateTime.class));
					course.setUploadTime(rs.getObject("upload_time",LocalDateTime.class));
					course.setStatus(rs.getString("status"));
					course.setPubStatus(rs.getString("public"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}
	
	
	/* *
	 *  Function: 取得會員可預約團課
	 *  CreateBy: Iris
	 *  CreateDate: 2022/09/21
	 * */
	@Override
	public List<Course> selectCourseByGymIdAndCourseListId(Integer gymId,Integer courseListId) {
		
		Course course = null;
		var sqlStr ="select cour_id,gym_id,courseType.cour_list_id,cour_name,start_time,end_time from course course join cour_list courseType on course.cour_list_id=courseType.cour_list_id where course.`status`='1' and public='1' and gym_id=? and courseType.cour_list_id=?;";
		
		List<Course> canCourseList = new ArrayList<Course>();
		
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sqlStr);) {

			System.out.println("連線成功");

			pstmt.setInt(1, gymId);
			pstmt.setInt(2, courseListId);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					course = new Course();
					course.setCourseId(rs.getInt("cour_id"));
					course.setGymId(rs.getInt("gym_id"));
					course.setCourseListId(rs.getInt("cour_list_id"));
					course.setCourseName(rs.getString("cour_name"));
					course.setStartTime(rs.getObject("start_time",LocalDateTime.class));
					course.setEndTime(rs.getObject("end_time",LocalDateTime.class));
					
					canCourseList.add(course);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return canCourseList;
	}

	
	// 取得已安排團課
	public List<Course> selectCourseByGymIdAndStartTime(Integer gymId, LocalDateTime startTime) {
		
		List<Course> list = new ArrayList<Course>();
		Course course = null;

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(SELECT_BY_GYMID_AND_STARTTIME);) {

			System.out.println("連線成功");
			
			pstmt.setInt(1, gymId);
			pstmt.setObject(2, startTime);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					course = new Course();
					course.setGymId(rs.getInt("gym_id"));
					course.setStartTime(rs.getObject("start_time", LocalDateTime.class));
					course.setCourseListId(rs.getInt("cour_list_id"));
					course.setEmpId(rs.getInt("emp_id"));
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
	
}
