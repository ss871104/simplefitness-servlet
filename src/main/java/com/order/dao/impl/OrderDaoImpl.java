package com.order.dao.impl;

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

import com.order.dao.intf.OrderDaoIntf;
import com.order.vo.Order;

import static com.order.dao.sql.OrderDaoSQL.*;

public class OrderDaoImpl implements OrderDaoIntf {
	
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
	public boolean insert(Order orderVo) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT);) {

			System.out.println("連線成功");

			pstmt.setInt(1, orderVo.getMemId());
			pstmt.setInt(2, orderVo.getGymId());
			pstmt.setInt(3, orderVo.getAmount());
			pstmt.setTimestamp(4, orderVo.getOrderDate());
			pstmt.setString(5, orderVo.getStatus());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean update(Order orderVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, orderVo.getMemId());
			pstmt.setInt(2, orderVo.getGymId());
			pstmt.setInt(3, orderVo.getAmount());
			pstmt.setObject(4, orderVo.getOrderDate());
			pstmt.setString(5, orderVo.getStatus());
			pstmt.setInt(6, orderVo.getOrderId());

			rowCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean delete(Integer orderId) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(DELETE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, orderId);

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public Order selectById(Integer orderId) {
		Order order = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, orderId);

			try (ResultSet rs = pstmt.executeQuery()) {

				order = new Order();

				if (rs.next()) {
					order.setOrderId(rs.getInt("order_id"));
					order.setMemId(rs.getInt("mem_id"));
					order.setGymId(rs.getInt("gym_id"));
					order.setAmount(rs.getInt("amount"));
					order.setOrderDate(rs.getTimestamp("order_date"));
					order.setStatus(rs.getString("status"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public List<Order> selectAll() {
		List<Order> list = new ArrayList<Order>();
		Order order = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_ALL);) {

			System.out.println("連線成功");

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					order = new Order();
					order.setOrderId(rs.getInt("order_id"));
					order.setMemId(rs.getInt("mem_id"));
					order.setGymId(rs.getInt("gym_id"));
					order.setAmount(rs.getInt("amount"));
					order.setOrderDate(rs.getTimestamp("order_date"));
					order.setStatus(rs.getString("status"));
					list.add(order);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Order> SelectByMem(Integer memId) {
		List<Order> list = new ArrayList<Order>();
		Order order = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_BY_MEM);) {

			System.out.println("連線成功");

			pstmt.setInt(1, memId);

			try (ResultSet rs = pstmt.executeQuery()) {


				while (rs.next()) {
					order = new Order();
					order.setOrderId(rs.getInt("order_id"));
					order.setMemId(rs.getInt("mem_id"));
					order.setGymId(rs.getInt("gym_id"));
					order.setAmount(rs.getInt("amount"));
					order.setOrderDate(rs.getTimestamp("order_date"));
					order.setStatus(rs.getString("status"));
					list.add(order);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Order> SelectByGym(Integer gymId) {
		List<Order> list = new ArrayList<Order>();
		Order order = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_BY_GYM);) {

			System.out.println("連線成功");

			pstmt.setInt(1, gymId);

			try (ResultSet rs = pstmt.executeQuery()) {

				order = new Order();

				while (rs.next()) {
					order.setOrderId(rs.getInt("order_id"));
					order.setMemId(rs.getInt("mem_id"));
					order.setGymId(rs.getInt("gym_id"));
					order.setAmount(rs.getInt("amount"));
					order.setOrderDate(rs.getTimestamp("order_date"));
					order.setStatus(rs.getString("status"));
					list.add(order);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean UpdateStatus(Order orderVo) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE_STATUS);) {

			System.out.println("連線成功");

			pstmt.setString(1, orderVo.getStatus());
			pstmt.setInt(2, orderVo.getOrderId());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

}
