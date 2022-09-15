package com.courlist.dao.impl;

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

import com.courlist.dao.intf.CourListDaoIntf;
import com.courlist.dao.sql.CourListDaoSQL;
import com.courlist.vo.CourList;
import com.course.vo.Course;

public class CourListDaoImpl implements CourListDaoIntf {

	private static DataSource ds = null;
	private static CourListDaoSQL SQL = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Test");
			SQL = new CourListDaoSQL();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(CourList courListVo) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.INSERT);) {

			System.out.println("連線成功");

			pstmt.setString(1, courListVo.getCourName());
			pstmt.setString(2, courListVo.getCourType());
			pstmt.setInt(3, courListVo.getCourMaxP());
			pstmt.setString(4, courListVo.getCourIntro());
			pstmt.setString(5, courListVo.getCourStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean update(CourList courListVo) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.UPDATE);) {

			System.out.println("連線成功");

			pstmt.setString(1, courListVo.getCourName());
			pstmt.setString(2, courListVo.getCourType());
			pstmt.setInt(3, courListVo.getCourMaxP());
			pstmt.setString(4, courListVo.getCourIntro());
			pstmt.setString(5, courListVo.getCourStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean delete(Integer courListId) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.DELETE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courListId);

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public CourList selectById(Integer courListId) {

		CourList courList = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, courListId);

			try (ResultSet rs = pstmt.executeQuery()) {

				courList = new CourList();

				if (rs.next()) {
					courList.setCourListId(rs.getInt("cour_list_id"));
					courList.setCourName(rs.getString("cour_name"));
					courList.setCourType(rs.getString("cour_type"));
					courList.setCourMaxP(rs.getInt("max_p"));
					courList.setCourIntro(rs.getString("intro"));
					courList.setCourStatus(rs.getString("status"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courList;
	}

	@Override
	public List<CourList> selectAll() {
		
		List<CourList> list = new ArrayList<CourList>();
		CourList courList = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL.SELECT_ALL);) {

			System.out.println("連線成功");

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					courList = new CourList();
					courList.setCourListId(rs.getInt("cour_list_id"));
					courList.setCourName(rs.getString("cour_name"));
					courList.setCourType(rs.getString("cour_type"));
					courList.setCourMaxP(rs.getInt("max_p"));
					courList.setCourIntro(rs.getString("intro"));
					courList.setCourStatus(rs.getString("status"));
					list.add(courList);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
