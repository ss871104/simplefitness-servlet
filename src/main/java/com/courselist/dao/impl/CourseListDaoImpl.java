package com.courselist.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.courselist.dao.intf.CourseListDaoIntf;
import com.courselist.dao.sql.CourseListDaoSQL;
import com.courselist.vo.CourseList;

public class CourseListDaoImpl implements CourseListDaoIntf {

	private static DataSource ds = null;
	private static CourseListDaoSQL SQL = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Test");
			SQL = new CourseListDaoSQL();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(CourseList courseListVo) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.INSERT);) {

			System.out.println("連線成功");

			pstmt.setString(1, courseListVo.getCourseName());
			pstmt.setInt(2, courseListVo.getCourseMaxP());
			pstmt.setString(3, courseListVo.getCourseIntro());
			pstmt.setString(4, courseListVo.getCourseStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean update(CourseList courseListVo) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.UPDATE);) {

			System.out.println("連線成功");

			pstmt.setString(1, courseListVo.getCourseName());
			pstmt.setInt(2, courseListVo.getCourseMaxP());
			pstmt.setString(3, courseListVo.getCourseIntro());
			pstmt.setString(4, courseListVo.getCourseStatus());
			pstmt.setInt(5, courseListVo.getCourseListId());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean delete(Integer courseListId) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.DELETE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courseListId);

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public CourseList selectById(Integer courseListId) {

		CourseList courseList = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courseListId);

			try (ResultSet rs = pstmt.executeQuery()) {

				courseList = new CourseList();

				if (rs.next()) {
					courseList.setCourseListId(rs.getInt("cour_list_id"));
					courseList.setCourseName(rs.getString("cour_name"));
					courseList.setCourseMaxP(rs.getInt("max_p"));
					courseList.setCourseIntro(rs.getString("intro"));
					courseList.setCourseStatus(rs.getString("status"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
	}

	@Override
	public List<CourseList> selectAll() {
		
		List<CourseList> list = new ArrayList<CourseList>();
		CourseList courseList = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_ALL);) {

			System.out.println("連線成功");

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					courseList = new CourseList();
					courseList.setCourseListId(rs.getInt("cour_list_id"));
					courseList.setCourseName(rs.getString("cour_name"));
					courseList.setCourseMaxP(rs.getInt("max_p"));
					courseList.setCourseIntro(rs.getString("intro"));
					courseList.setCourseStatus(rs.getString("status"));
					list.add(courseList);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/* *
	 *  Function: 取得課程報名上限人數
	 *  CreateBy: Iris
	 *  CreateDate: 2022/09/18
	 * */
	@Override
	public CourseList getCourseListByCourseId(Integer courseId) {
		
		
		CourseList courseList= null;

		var sqlStr= "select courseList.* from course course join cour_List courseList on course.cour_list_id = courseList.cour_list_id where cour_id=?";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sqlStr);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courseId);

			try (ResultSet rs = pstmt.executeQuery()) {

				courseList = new CourseList();

				if (rs.next()) {
					courseList.setCourseListId(rs.getInt("cour_list_id"));
					courseList.setCourseName(rs.getString("cour_name"));
					courseList.setCourseMaxP(rs.getInt("max_p"));
					courseList.setCourseIntro(rs.getString("intro"));
					courseList.setCourseStatus(rs.getString("status"));
				} 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
		
	}

}
