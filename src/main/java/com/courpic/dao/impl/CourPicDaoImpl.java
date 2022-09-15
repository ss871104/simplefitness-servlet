package com.courpic.dao.impl;

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

import com.courpic.dao.intf.CourPicDaoIntf;
import com.courpic.dao.sql.CourPicDaoSQL;
import com.courpic.vo.CourPic;

public class CourPicDaoImpl implements CourPicDaoIntf {

	private static DataSource ds = null;
	private static CourPicDaoSQL SQL = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Test");
			SQL = new CourPicDaoSQL();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(CourPic courpicVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.INSERT);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courpicVo.getCourlistId());
			pstmt.setBytes(2, courpicVo.getCourPic());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean update(CourPic courpicVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.UPDATE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courpicVo.getCourlistId());
			pstmt.setBytes(2, courpicVo.getCourPic());
			pstmt.setInt(3, courpicVo.getCourpicId());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean delete(Integer courpicId) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.DELETE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courpicId);

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public CourPic selectById(Integer courpicId) {
		CourPic courpic = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courpicId);

			try (ResultSet rs = pstmt.executeQuery()) {

				courpic = new CourPic();

				while (rs.next()) {
					courpic = new CourPic();
					courpic.setCourpicId(rs.getInt("courpicId"));
					courpic.setCourlistId(rs.getInt("courlistId"));
					courpic.setCourPic(rs.getBytes("courPic"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courpic;
	}

	@Override
	public List<CourPic> selectAll() {
		List<CourPic> list = new ArrayList<CourPic>();
		CourPic courpic = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_ALL);) {

			System.out.println("連線成功");

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					courpic = new CourPic();
					courpic.setCourpicId(rs.getInt("courpicId"));
					courpic.setCourlistId(rs.getInt("courlistId"));
					courpic.setCourPic(rs.getBytes("courPic"));
					list.add(courpic);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
