package com.rent.dao.impl;

import static com.idvproduct.dao.sql.IdvProductDaoSQL.UPDATE_GYM;
import static com.order.dao.sql.OrderDaoSQL.DELETE;

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

import com.order.vo.Order;
import com.rent.dao.intf.RentDaoIntf;
import static com.rent.dao.sql.RentDaoSQL.*;

public class RentDaoImpl implements RentDaoIntf{
	
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
	public List<Order> selectByMemId(Integer memId){
		List<Order> list = new ArrayList<Order>();
		Order order = null;

		try (Connection con = ds.getConnection();

				PreparedStatement pstmt = con.prepareStatement(SELECT_GET_ORDERID);) {

			System.out.println("連線成功");
			
			pstmt.setInt(1, memId);
			
			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					order = new Order();
					order.setOrderId(rs.getInt("order_id"));
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
	public boolean insert(Order vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Order vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Order selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateStatus(String status, Integer id) {
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE_STATUS);) {

			System.out.println("連線成功");

			pstmt.setString(1, status);
			pstmt.setInt(2, id);

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}
}
