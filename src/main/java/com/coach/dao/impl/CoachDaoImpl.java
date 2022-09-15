package com.coach.dao.impl;

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

import com.coach.dao.intf.CoachDaoIntf;
import com.coach.dao.sql.CoachDaoSQL;
import com.coach.vo.Coach;

public class CoachDaoImpl implements CoachDaoIntf {

	private static DataSource ds = null;
	private static CoachDaoSQL SQL = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Test");
			SQL = new CoachDaoSQL();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(Coach coaVo) {
		
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.INSERT);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coaVo.getEmpId());
			pstmt.setDate(2, coaVo.getStartTime());
			pstmt.setDate(3, coaVo.getEndTime());
			pstmt.setDate(4, coaVo.getUploadTime());
			pstmt.setString(5, coaVo.getStatus());
			pstmt.setString(6, coaVo.getPubStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
		
	}

	@Override
	public boolean update(Coach coaVo) {
		
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.UPDATE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coaVo.getEmpId());
			pstmt.setDate(2, coaVo.getStartTime());
			pstmt.setDate(3, coaVo.getEndTime());
			pstmt.setDate(4, coaVo.getUploadTime());
			pstmt.setString(5, coaVo.getStatus());
			pstmt.setString(6, coaVo.getPubStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
		
	}

	@Override
	public boolean delete(Integer coaId) {
		
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.DELETE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coaId);

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
		
	}

	@Override
	public Coach selectById(Integer coaId) {
		
		Coach coa = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, coaId);

			try (ResultSet rs = pstmt.executeQuery()) {

				coa = new Coach();

				if (rs.next()) {
					coa.setCoaId(rs.getInt("coa_id"));
					coa.setEmpId(rs.getInt("emp_id"));
					coa.setStartTime(rs.getDate("start_time"));
					coa.setEndTime(rs.getDate("end_time"));
					coa.setUploadTime(rs.getDate("upload_time"));
					coa.setStatus(rs.getString("status"));
					coa.setPubStatus(rs.getString("public"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coa;
		
	}

	@Override
	public List<Coach> selectAll() {
		
		List<Coach> list = new ArrayList<Coach>();
		Coach coa = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_ALL);) {

			System.out.println("連線成功");

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					coa = new Coach();
					coa.setCoaId(rs.getInt("coa_id"));
					coa.setEmpId(rs.getInt("emp_id"));
					coa.setStartTime(rs.getDate("start_time"));
					coa.setEndTime(rs.getDate("end_time"));
					coa.setUploadTime(rs.getDate("upload_time"));
					coa.setStatus(rs.getString("status"));
					coa.setPubStatus(rs.getString("public"));
					list.add(coa);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


}
