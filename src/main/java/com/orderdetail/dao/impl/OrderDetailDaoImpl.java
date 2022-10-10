package com.orderdetail.dao.impl;

import static com.orderdetail.dao.sql.OrderDetailDaoSQL.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public Integer insert2(Integer orderId, Integer gymId, Integer prodId) {
		Integer orderCode = null;
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);) {

			System.out.println("連線成功");

			pstmt.setInt(1, orderId);
			pstmt.setInt(2, gymId);
			pstmt.setInt(3, prodId);
			pstmt.setString(4, "1");

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				orderCode = rs.getInt(1);
				System.out.println("keyValue= " + orderCode );
			} 
			return orderCode;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(OrderDetail vo) {
		return false;
	}

	@Override
	public boolean delete(Integer id) {
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
	public List<OrderDetail> SelectByOrderId(Integer orderId) {
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		OrderDetail orderdetail = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ORDERID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, orderId);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					orderdetail = new OrderDetail();
					orderdetail.setOrderCode(rs.getInt("order_code"));
					orderdetail.setOrderId(rs.getInt("order_id"));
					orderdetail.setIdvId(rs.getInt("idv_id"));
					orderdetail.setPickupTime(rs.getTimestamp("pickup_time"));
					orderdetail.setReturnTime(rs.getTimestamp("return_time"));
					orderdetail.setStatus(rs.getString("status"));
					orderdetail.setProdName(rs.getString("prod_name"));
					list.add(orderdetail);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
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

	@Override
	public boolean insert(OrderDetail vo) {
		return false;
	}

}
