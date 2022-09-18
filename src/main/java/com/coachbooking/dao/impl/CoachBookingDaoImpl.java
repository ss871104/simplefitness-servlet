package com.coachbooking.dao.impl;

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

import com.coachbooking.dao.intf.CoachBookingDaoIntf;
import com.coachbooking.dao.sql.CoachBookingDaoSQL;
import com.coachbooking.vo.CoachBooking;

public class CoachBookingDaoImpl implements CoachBookingDaoIntf {

	private static DataSource ds = null;
	private static CoachBookingDaoSQL SQL = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Test");
			SQL = new CoachBookingDaoSQL();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(CoachBooking coachbookVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.INSERT);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coachbookVo.getMemId());
			pstmt.setInt(2, coachbookVo.getCoachId());
			pstmt.setString(3, coachbookVo.getCoachbookStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean update(CoachBooking coachbookVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.UPDATE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coachbookVo.getMemId());
			pstmt.setInt(2, coachbookVo.getCoachId());
			pstmt.setString(3, coachbookVo.getCoachbookStatus());
			pstmt.setInt(4, coachbookVo.getCoachbookId());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean delete(Integer coachbookId) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.DELETE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coachbookId);

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public CoachBooking selectById(Integer coachbookId) {
		CoachBooking coachbook = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coachbookId);

			try (ResultSet rs = pstmt.executeQuery()) {

				coachbook = new CoachBooking();

				while (rs.next()) {
					coachbook = new CoachBooking();
					coachbook.setCoachbookId(rs.getInt("coachbookId"));
					coachbook.setMemId(rs.getInt("memId"));
					coachbook.setCoachId(rs.getInt("coachId"));
					coachbook.setCoachbookTime(rs.getTimestamp("coachbookTime"));
					coachbook.setCoachbookStatus(rs.getString("coachbookStatus"));
					coachbook.setCheckTime(rs.getTimestamp("checkTime"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coachbook;
	}

	@Override
	public List<CoachBooking> selectAll() {
		List<CoachBooking> list = new ArrayList<CoachBooking>();
		CoachBooking coachbook = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_ALL);) {
			
			System.out.println("連線成功");
			
			try (ResultSet rs = pstmt.executeQuery()) {
				
				while (rs.next()) {
					coachbook = new CoachBooking();
					coachbook.setCoachbookId(rs.getInt("coachbookId"));
					coachbook.setMemId(rs.getInt("memId"));
					coachbook.setCoachId(rs.getInt("coachId"));
					coachbook.setCoachbookTime(rs.getTimestamp("coachbookTime"));
					coachbook.setCoachbookStatus(rs.getString("coachbookStatus"));
					coachbook.setCheckTime(rs.getTimestamp("checkTime"));
					list.add(coachbook);	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

}
