package com.courbooking.dao.impl;

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

import com.courbooking.dao.intf.CourBookingDaoIntf;
import com.courbooking.dao.sql.CourBookingDaoSQL;
import com.courbooking.vo.CourBooking;

public class CourBookingDaoImpl implements CourBookingDaoIntf {

	private static DataSource ds = null;
	private static CourBookingDaoSQL SQL = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Test");
			SQL = new CourBookingDaoSQL();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(CourBooking courbookVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.INSERT);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courbookVo.getMemId());
			pstmt.setInt(2, courbookVo.getCourId());
			pstmt.setString(3, courbookVo.getCourbookStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean update(CourBooking courbookVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.UPDATE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courbookVo.getMemId());
			pstmt.setInt(2, courbookVo.getCourId());
			pstmt.setString(3, courbookVo.getCourbookStatus());
			pstmt.setInt(4, courbookVo.getCourbookId());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean delete(Integer courbookId) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.DELETE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courbookId);

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public CourBooking selectById(Integer courbookId) {
		CourBooking courbook = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courbookId);

			try (ResultSet rs = pstmt.executeQuery()) {

				courbook = new CourBooking();

				while (rs.next()) {
					courbook = new CourBooking();
					courbook.setCourbookId(rs.getInt("courbookId"));
					courbook.setMemId(rs.getInt("memId"));
					courbook.setCourId(rs.getInt("courId"));
					courbook.setCourbookTime(rs.getTimestamp("courbookTime"));
					courbook.setCourbookStatus(rs.getString("courbookStatus"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courbook;
	}

	@Override
	public List<CourBooking> selectAll() {
		List<CourBooking> list = new ArrayList<CourBooking>();
		CourBooking courbook = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_ALL);) {

			System.out.println("連線成功");

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					courbook = new CourBooking();
					courbook.setCourbookId(rs.getInt("courbookId"));
					courbook.setMemId(rs.getInt("memId"));
					courbook.setCourId(rs.getInt("courId"));
					courbook.setCourbookTime(rs.getTimestamp("courbookTime"));
					courbook.setCourbookStatus(rs.getString("courbookStatus"));
					list.add(courbook);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public CourBooking selectByMem(Integer memId) {
		CourBooking courbook = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_BY_MEM);) {

			System.out.println("連線成功");

			pstmt.setInt(1, memId);

			try (ResultSet rs = pstmt.executeQuery()) {

				courbook = new CourBooking();

				while (rs.next()) {
					courbook = new CourBooking();
					courbook.setCourbookId(rs.getInt("courbookId"));
					courbook.setMemId(rs.getInt("memId"));
					courbook.setCourId(rs.getInt("courId"));
					courbook.setCourbookTime(rs.getTimestamp("courbookTime"));
					courbook.setCourbookStatus(rs.getString("courbookStatus"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courbook;
	}

	@Override
	public boolean updateStatus(CourBooking courbookVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.UPDATE_STATUS);) {

			System.out.println("連線成功");

			pstmt.setString(1, courbookVo.getCourbookStatus());
			pstmt.setInt(2, courbookVo.getCourbookId());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	
	
}
