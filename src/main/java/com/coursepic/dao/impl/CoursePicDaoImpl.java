package com.coursepic.dao.impl;

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

import com.coursepic.dao.intf.CoursePicDaoIntf;
import com.coursepic.dao.sql.CoursePicDaoSQL;
import com.coursepic.vo.CoursePic;

public class CoursePicDaoImpl implements CoursePicDaoIntf {

	private static DataSource ds = null;
	private static CoursePicDaoSQL SQL = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Test");
			SQL = new CoursePicDaoSQL();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(CoursePic coursepicVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.INSERT);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coursepicVo.getCourselistId());
			pstmt.setBytes(2, coursepicVo.getCoursePic());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean update(CoursePic coursepicVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.UPDATE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coursepicVo.getCourselistId());
			pstmt.setBytes(2, coursepicVo.getCoursePic());
			pstmt.setInt(3, coursepicVo.getCoursepicId());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean delete(Integer coursepicId) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.DELETE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coursepicId);

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public CoursePic selectById(Integer coursepicId) {
		CoursePic coursepic = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coursepicId);

			try (ResultSet rs = pstmt.executeQuery()) {

				coursepic = new CoursePic();

				while (rs.next()) {
					coursepic = new CoursePic();
					coursepic.setCoursePic(rs.getBytes("coursePic"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coursepic;
	}

	@Override
	public List<CoursePic> selectAll() {
		List<CoursePic> list = new ArrayList<CoursePic>();
		CoursePic coursepic = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_ALL);) {

			System.out.println("連線成功");

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					coursepic = new CoursePic();
					coursepic.setCoursepicId(rs.getInt("coursepicId"));
					coursepic.setCourselistId(rs.getInt("courselistId"));
					coursepic.setCoursePic(rs.getBytes("coursePic"));
					list.add(coursepic);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
