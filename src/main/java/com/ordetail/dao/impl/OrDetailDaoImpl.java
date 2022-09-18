package com.ordetail.dao.impl;

import static com.ordetail.dao.sql.OrDetailDaoSQL.*;

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

import com.ordetail.dao.intf.OrDetailDaoIntf;
import com.ordetail.vo.OrDetail;

public class OrDetailDaoImpl implements OrDetailDaoIntf {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Test");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(OrDetail ordetailVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT);) {

			System.out.println("連線成功");

			pstmt.setInt(1, ordetailVo.getOrderId());
			pstmt.setInt(2, ordetailVo.getIdvId());
			pstmt.setString(3, ordetailVo.getStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean update(OrDetail vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<OrDetail> selectAll() {
		List<OrDetail> list = new ArrayList<OrDetail>();
		OrDetail ordetail = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_ALL);) {

			System.out.println("連線成功");

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					ordetail = new OrDetail();
					ordetail.setOrderCode(rs.getInt("order_code"));
					ordetail.setOrderId(rs.getInt("order_id"));
					ordetail.setIdvId(rs.getInt("idv_id"));
					ordetail.setPickupTime(rs.getTimestamp("pickup_time"));
					ordetail.setReturnTime(rs.getTimestamp("return_time"));
					ordetail.setStatus(rs.getString("status"));
					list.add(ordetail);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public OrDetail selectById(Integer orderCode) {
		OrDetail ordetail = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, orderCode);

			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {
					ordetail = new OrDetail();
					ordetail.setOrderCode(rs.getInt("order_code"));
					ordetail.setOrderId(rs.getInt("order_id"));
					ordetail.setIdvId(rs.getInt("idv_id"));
					ordetail.setPickupTime(rs.getTimestamp("pickup_time"));
					ordetail.setReturnTime(rs.getTimestamp("return_time"));
					ordetail.setStatus(rs.getString("status"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordetail;
	}

	@Override
	public OrDetail SelectByOrderId(Integer orderId) {
		OrDetail ordetail = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ORDERID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, orderId);

			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {
					ordetail = new OrDetail();
					ordetail.setOrderCode(rs.getInt("order_code"));
					ordetail.setOrderId(rs.getInt("order_id"));
					ordetail.setIdvId(rs.getInt("idv_id"));
					ordetail.setPickupTime(rs.getTimestamp("pickup_time"));
					ordetail.setReturnTime(rs.getTimestamp("return_time"));
					ordetail.setStatus(rs.getString("status"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordetail;
	}

	@Override
	public boolean UpdateStatus(OrDetail ordetailVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE_STATUS);) {

			System.out.println("連線成功");

			pstmt.setString(1, ordetailVo.getStatus());
			pstmt.setInt(2, ordetailVo.getOrderCode());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean UpdatePickup(OrDetail ordetailVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE_PICKUP);) {

			System.out.println("連線成功");

			pstmt.setTimestamp(1, ordetailVo.getPickupTime());
			pstmt.setString(2, ordetailVo.getStatus());
			pstmt.setInt(3, ordetailVo.getOrderCode());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean UpdateReturn(OrDetail ordetailVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE_RETURN);) {

			System.out.println("連線成功");

			pstmt.setTimestamp(1, ordetailVo.getReturnTime());
			pstmt.setString(2, ordetailVo.getStatus());
			pstmt.setInt(3, ordetailVo.getOrderCode());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

}
