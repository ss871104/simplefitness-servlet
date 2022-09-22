package com.coursebooking.dao.impl;

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

import com.coursebooking.dao.intf.CourseBookingDaoIntf;
import com.coursebooking.dao.sql.CourseBookingDaoSQL;
import com.coursebooking.vo.CourseBooking;

public class CourseBookingDaoImpl implements CourseBookingDaoIntf {

	private static DataSource ds = null;
	private static CourseBookingDaoSQL SQL = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Test");
			SQL = new CourseBookingDaoSQL();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(CourseBooking coursebookVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.INSERT);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coursebookVo.getMemId());
			pstmt.setInt(2, coursebookVo.getCourseId());
			pstmt.setString(3, coursebookVo.getCoursebookStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean update(CourseBooking coursebookVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.UPDATE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coursebookVo.getMemId());
			pstmt.setInt(2, coursebookVo.getCourseId());
			pstmt.setString(3, coursebookVo.getCoursebookStatus());
			pstmt.setInt(4, coursebookVo.getCoursebookId());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean delete(Integer coursebookId) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.DELETE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coursebookId);

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public CourseBooking selectById(Integer coursebookId) {
		CourseBooking coursebook = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coursebookId);

			try (ResultSet rs = pstmt.executeQuery()) {

				coursebook = new CourseBooking();

				while (rs.next()) {
					coursebook = new CourseBooking();
					coursebook.setCoursebookId(rs.getInt("cour_book_Id"));
					coursebook.setMemId(rs.getInt("mem_Id"));
					coursebook.setCourseId(rs.getInt("cour_Id"));
					coursebook.setCoursebookTime(rs.getTimestamp("booking_time"));
					coursebook.setCoursebookStatus(rs.getString("status"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coursebook;
	}

	@Override
	public List<CourseBooking> selectAll() {
		List<CourseBooking> list = new ArrayList<CourseBooking>();
		CourseBooking coursebook = null;

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_ALL);) {

			System.out.println("連線成功");

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					coursebook = new CourseBooking();
					coursebook.setCoursebookId(rs.getInt("cour_book_Id"));
					coursebook.setMemId(rs.getInt("mem_Id"));
					coursebook.setCourseId(rs.getInt("cour_Id"));
					coursebook.setCoursebookTime(rs.getTimestamp("booking_time"));
					coursebook.setCoursebookStatus(rs.getString("status"));
					list.add(coursebook);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<CourseBooking> selectByMemberId(Integer memId) {
		CourseBooking coursebook = null;
		List<CourseBooking> courseBookingList = new ArrayList<CourseBooking>();
		
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_BY_MEMBER_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, memId);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					coursebook = new CourseBooking();
					coursebook.setCoursebookId(rs.getInt("cour_book_Id"));
					coursebook.setMemId(rs.getInt("mem_Id"));
					coursebook.setCourseId(rs.getInt("cour_Id"));
					coursebook.setCoursebookTime(rs.getTimestamp("booking_time"));
					coursebook.setCoursebookStatus(rs.getString("status"));
					
					courseBookingList.add(coursebook);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseBookingList;
	}

	@Override
	public boolean updateStatus(CourseBooking coursebookVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.UPDATE_STATUS);) {

			System.out.println("連線成功");

			pstmt.setString(1, coursebookVo.getCoursebookStatus());
			pstmt.setInt(2, coursebookVo.getCoursebookId());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public int getcourseBookedCount(Integer courseId) {

		int courseBookedCount = 0;
		var sqlStr = "SELECT count(cour_Id) as count FROM simple_fitness.cour_booking Where status='1' and cour_id=? Group by cour_id;";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sqlStr);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courseId);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					courseBookedCount = rs.getInt("count");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseBookedCount;
	}

	
	
}
