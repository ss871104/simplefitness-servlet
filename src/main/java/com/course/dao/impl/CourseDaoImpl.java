package com.course.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.course.dao.intf.CourseDaoIntf;
import com.course.dao.sql.CourseDaoSQL;
import com.course.vo.Course;

public class CourseDaoImpl implements CourseDaoIntf {

	private static DataSource ds = null;
	private static CourseDaoSQL SQL = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Test");
			SQL = new CourseDaoSQL();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public boolean insert(Course courVo) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.INSERT);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courVo.getEmpId());
			pstmt.setInt(2, courVo.getGymId());
			pstmt.setInt(3, courVo.getCourListId());
			pstmt.setDate(4, courVo.getStartTime());
			pstmt.setDate(5, courVo.getEndTime());
			pstmt.setString(6, courVo.getStatus());
			pstmt.setString(7, courVo.getPubStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean update(Course courVo) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.UPDATE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courVo.getEmpId());
			pstmt.setInt(2, courVo.getGymId());
			pstmt.setInt(3, courVo.getCourListId());
			pstmt.setDate(4, courVo.getStartTime());
			pstmt.setDate(5, courVo.getEndTime());
			pstmt.setString(6, courVo.getStatus());
			pstmt.setString(7, courVo.getPubStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean delete(Integer courId) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.DELETE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courId);

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;

	}

	@Override
	public Course selectById(Integer courId) {

		Course cour = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courId);

			try (ResultSet rs = pstmt.executeQuery()) {

				cour = new Course();

				if (rs.next()) {
					cour.setCourId(rs.getInt("cour_id"));
					cour.setEmpId(rs.getInt("emp_id"));
					cour.setGymId(rs.getInt("gym_id"));
					cour.setCourListId(rs.getInt("cour_list_id"));
					cour.setStartTime(rs.getDate("start_time"));
					cour.setEndTime(rs.getDate("end_time"));
					cour.setUploadTime(rs.getDate("upload_time"));
					cour.setStatus(rs.getString("status"));
					cour.setPubStatus(rs.getString("public"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cour;
	}

	@Override
	public List<Course> selectAll() {

		List<Course> list = new ArrayList<Course>();
		Course cour = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_ALL);) {

			System.out.println("連線成功");

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					cour = new Course();
					cour.setCourId(rs.getInt("cour_id"));
					cour.setEmpId(rs.getInt("emp_id"));
					cour.setGymId(rs.getInt("gym_id"));
					cour.setCourListId(rs.getInt("cour_list_id"));
					cour.setStartTime(rs.getDate("start_time"));
					cour.setEndTime(rs.getDate("end_time"));
					cour.setUploadTime(rs.getDate("upload_time"));
					cour.setStatus(rs.getString("status"));
					cour.setPubStatus(rs.getString("public"));
					list.add(cour);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
