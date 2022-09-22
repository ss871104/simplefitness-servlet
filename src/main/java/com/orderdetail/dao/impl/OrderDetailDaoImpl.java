package com.orderdetail.dao.impl;

import static com.orderdetail.dao.sql.OrderDetailDaoSQL.*;

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

import com.orderdetail.dao.intf.OrderDetailDaoIntf;
import com.orderdetail.vo.OrderDetail;

public class OrderDetailDaoImpl implements OrderDetailDaoIntf {

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
	public boolean insert(OrderDetail orderDetailVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT);) {

			System.out.println("連線成功");

			pstmt.setInt(1, orderDetailVo.getOrderId());
			pstmt.setInt(2, orderDetailVo.getIdvId());
			pstmt.setString(3, orderDetailVo.getStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean update(OrderDetail vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<OrderDetail> selectAll() {
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		OrderDetail orderdetail = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_ALL);) {

			System.out.println("連線成功");

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					orderdetail = new OrderDetail();
					orderdetail.setOrderCode(rs.getInt("order_code"));
					orderdetail.setOrderId(rs.getInt("order_id"));
					orderdetail.setIdvId(rs.getInt("idv_id"));
					orderdetail.setPickupTime(rs.getTimestamp("pickup_time"));
					orderdetail.setReturnTime(rs.getTimestamp("return_time"));
					orderdetail.setStatus(rs.getString("status"));
					list.add(orderdetail);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public OrderDetail selectById(Integer orderCode) {
		OrderDetail orderdetail = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, orderCode);

			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {
					orderdetail = new OrderDetail();
					orderdetail.setOrderCode(rs.getInt("order_code"));
					orderdetail.setOrderId(rs.getInt("order_id"));
					orderdetail.setIdvId(rs.getInt("idv_id"));
					orderdetail.setPickupTime(rs.getTimestamp("pickup_time"));
					orderdetail.setReturnTime(rs.getTimestamp("return_time"));
					orderdetail.setStatus(rs.getString("status"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderdetail;
	}

	@Override
	public OrderDetail SelectByOrderId(Integer orderId) {
		OrderDetail orderdetail = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ORDERID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, orderId);

			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {
					orderdetail = new OrderDetail();
					orderdetail.setOrderCode(rs.getInt("order_code"));
					orderdetail.setOrderId(rs.getInt("order_id"));
					orderdetail.setIdvId(rs.getInt("idv_id"));
					orderdetail.setPickupTime(rs.getTimestamp("pickup_time"));
					orderdetail.setReturnTime(rs.getTimestamp("return_time"));
					orderdetail.setStatus(rs.getString("status"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderdetail;
	}

	@Override
	public boolean UpdateStatus(OrderDetail orderDetailVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE_STATUS);) {

			System.out.println("連線成功");

			pstmt.setString(1, orderDetailVo.getStatus());
			pstmt.setInt(2, orderDetailVo.getOrderCode());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean UpdatePickup(OrderDetail orderDetailVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE_PICKUP);) {

			System.out.println("連線成功");

			pstmt.setTimestamp(1, orderDetailVo.getPickupTime());
			pstmt.setString(2, orderDetailVo.getStatus());
			pstmt.setInt(3, orderDetailVo.getOrderCode());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean UpdateReturn(OrderDetail orderDetailVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE_RETURN);) {

			System.out.println("連線成功");

			pstmt.setTimestamp(1, orderDetailVo.getReturnTime());
			pstmt.setString(2, orderDetailVo.getStatus());
			pstmt.setInt(3, orderDetailVo.getOrderCode());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

}
