package com.course.dao.impl;

import static com.course.dao.sql.CourseDaoSQL.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			pstmt.setDate(4, courseVo.getCourseDate());
			pstmt.setTimestamp(5, courseVo.getStartTime());
			pstmt.setTimestamp(6, courseVo.getEndTime());
			pstmt.setString(7, courseVo.getStatus());
			pstmt.setString(8, courseVo.getPubStatus());

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
			pstmt.setDate(4, courseVo.getCourseDate());
			pstmt.setTimestamp(5, courseVo.getStartTime());
			pstmt.setTimestamp(6, courseVo.getEndTime());
			pstmt.setString(7, courseVo.getStatus());
			pstmt.setString(8, courseVo.getPubStatus());

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
					course.setCourseDate(rs.getDate("course_Date"));
					course.setStartTime(rs.getTimestamp("start_time"));
					course.setEndTime(rs.getTimestamp("end_time"));
					course.setUploadTime(rs.getTimestamp("upload_time"));
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
					course.setCourseDate(rs.getDate("course_Date"));
					course.setStartTime(rs.getTimestamp("start_time"));
					course.setEndTime(rs.getTimestamp("end_time"));
					course.setUploadTime(rs.getTimestamp("upload_time"));
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
					course.setCourseDate(rs.getDate("course_Date"));
					course.setStartTime(rs.getTimestamp("start_time"));
					course.setEndTime(rs.getTimestamp("end_time"));
					course.setUploadTime(rs.getTimestamp("upload_time"));
					course.setStatus(rs.getString("status"));
					course.setPubStatus(rs.getString("public"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}
	

	
	
}
