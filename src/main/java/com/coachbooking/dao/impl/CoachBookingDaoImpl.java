package com.coachbooking.dao.impl;

import java.sql.Connection;
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

import com.coach.vo.Coach;
import com.coachbooking.dao.intf.CoachBookingDaoIntf;
import com.coachbooking.dao.sql.CoachBookingDaoSQL;
import com.coachbooking.vo.CoachBooking;
import com.mysql.cj.xdevapi.Statement;

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
		boolean flag = true;
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.INSERT);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coachbookVo.getMemId());
			pstmt.setInt(2, coachbookVo.getCoachId());
			pstmt.setString(3, coachbookVo.getCoachbookStatus());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean update(CoachBooking coachbookVo) {
		boolean flag = true;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.UPDATE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coachbookVo.getMemId());
			pstmt.setInt(2, coachbookVo.getCoachId());
			pstmt.setString(3, coachbookVo.getCoachbookStatus());
			pstmt.setInt(4, coachbookVo.getCoachbookId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
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
					coachbook.setCoachbookId(rs.getInt("coa_book_id"));
					coachbook.setMemId(rs.getInt("mem_id"));
					coachbook.setCoachId(rs.getInt("coa_id"));
					coachbook.setCoachbookTime(rs.getObject("booking_time", LocalDateTime.class));
					coachbook.setCoachbookStatus(rs.getString("status"));
					coachbook.setCheckTime(rs.getObject("check_time", LocalDateTime.class));
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
					coachbook.setCoachbookId(rs.getInt("coa_book_id"));
					coachbook.setMemId(rs.getInt("mem_id"));
					coachbook.setCoachId(rs.getInt("coa_id"));
					coachbook.setCoachbookTime(rs.getObject("booking_time", LocalDateTime.class));
					coachbook.setCoachbookStatus(rs.getString("status"));
					coachbook.setCheckTime(rs.getObject("check_time", LocalDateTime.class));
					list.add(coachbook);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Boolean updateCoachBookingStatus(CoachBooking coachbook) {
		boolean flag = true;
		var sqlStr = "update coa_booking set status=? where coa_book_id  = ?";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sqlStr);) {

			System.out.println("連線成功");

			pstmt.setString(1, coachbook.getCoachbookStatus());
			pstmt.setInt(2, coachbook.getCoachbookId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	//取得會員預約教練課清單
	public List<CoachBooking> selectBookingCoachClassByMemIdAndGymId(Integer memberId, Integer gymId) {

		CoachBooking coachBooking = null;
		var sqlStr = "select coachBooking.* from coa_booking coachBooking\r\n"
				+ "join coach coa on coachBooking.coa_id=coa.coa_id \r\n"
				+ "join emp emp on coa.emp_id = emp.emp_id and gym_id = ?\r\n"
				+ "where mem_id = ? and coachBooking.status in (1,2);";
		List<CoachBooking> coachBookedList = new ArrayList<CoachBooking>();

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sqlStr);) {

			System.out.println("連線成功");

			pstmt.setInt(1, gymId);
			pstmt.setInt(2, memberId);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					coachBooking = new CoachBooking();
					coachBooking.setCoachbookId(rs.getInt("coa_book_id"));
					coachBooking.setMemId(rs.getInt("mem_id"));
					coachBooking.setCoachId(rs.getInt("coa_id"));
					coachBooking.setCoachbookTime(rs.getObject("booking_time", LocalDateTime.class));
					coachBooking.setCoachbookStatus(rs.getString("status"));
					coachBooking.setCheckTime(rs.getObject("check_time", LocalDateTime.class));

					coachBookedList.add(coachBooking);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coachBookedList;
	}

}
