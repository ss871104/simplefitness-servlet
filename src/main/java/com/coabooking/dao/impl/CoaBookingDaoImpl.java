package com.coabooking.dao.impl;

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

import com.coabooking.dao.intf.CoaBookingDaoIntf;
import com.coabooking.dao.sql.CoaBookingDaoSQL;
import com.coabooking.vo.CoaBooking;

public class CoaBookingDaoImpl implements CoaBookingDaoIntf {

	private static DataSource ds = null;
	private static CoaBookingDaoSQL SQL = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Test");
			SQL = new CoaBookingDaoSQL();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(CoaBooking coabookVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.INSERT);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coabookVo.getMemId());
			pstmt.setInt(2, coabookVo.getCoaId());
			pstmt.setString(3, coabookVo.getCoabookStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean update(CoaBooking coabookVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.UPDATE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coabookVo.getMemId());
			pstmt.setInt(2, coabookVo.getCoaId());
			pstmt.setString(3, coabookVo.getCoabookStatus());
			pstmt.setInt(4, coabookVo.getCoabookId());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean delete(Integer coabookId) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.DELETE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coabookId);

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public CoaBooking selectById(Integer coabookId) {
		CoaBooking coabook = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coabookId);

			try (ResultSet rs = pstmt.executeQuery()) {

				coabook = new CoaBooking();

				while (rs.next()) {
					coabook = new CoaBooking();
					coabook.setCoabookId(rs.getInt("coabookId"));
					coabook.setMemId(rs.getInt("memId"));
					coabook.setCoaId(rs.getInt("coaId"));
					coabook.setCoabookTime(rs.getTimestamp("coabookTime"));
					coabook.setCoabookStatus(rs.getString("coabookStatus"));
					coabook.setCheckTime(rs.getTimestamp("checkTime"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coabook;
	}

	@Override
	public List<CoaBooking> selectAll() {
		List<CoaBooking> list = new ArrayList<CoaBooking>();
		CoaBooking coabook = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_ALL);) {
			
			System.out.println("連線成功");
			
			try (ResultSet rs = pstmt.executeQuery()) {
				
				while (rs.next()) {
					coabook = new CoaBooking();
					coabook.setCoabookId(rs.getInt("coabookId"));
					coabook.setMemId(rs.getInt("memId"));
					coabook.setCoaId(rs.getInt("coaId"));
					coabook.setCoabookTime(rs.getTimestamp("coabookTime"));
					coabook.setCoabookStatus(rs.getString("coabookStatus"));
					coabook.setCheckTime(rs.getTimestamp("checkTime"));
					list.add(coabook);	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

}
