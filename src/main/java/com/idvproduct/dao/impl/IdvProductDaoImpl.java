package com.idvproduct.dao.impl;

import static com.idvproduct.dao.sql.IdvProductDaoSQL.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.idvproduct.dao.intf.IdvProductDaoIntf;
import com.idvproduct.vo.IdvProduct;
import com.product.vo.Product;



public class IdvProductDaoImpl  implements IdvProductDaoIntf{
	
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
	public boolean insert(IdvProduct vo) {
		return false;
	}

	@Override
	public boolean update(IdvProduct vo) {
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		return false;
	}

	@Override
	public IdvProduct selectById(Integer id) {
		return null;
	}

	@Override
	public List<IdvProduct> selectAll() {
		return null;
	}

	@Override
	public List<IdvProduct> selectByGym(Integer gymId) {
		
		List<IdvProduct> list = new ArrayList<IdvProduct>();
		IdvProduct idvProduct = null;
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_COUNT_BY_GYM);) {

			System.out.println("連線成功");
			
			pstmt.setString(1, "1");
			pstmt.setInt(2, gymId);
			
			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					idvProduct = new IdvProduct();
					idvProduct.setProdId(rs.getInt("prod_id"));
					idvProduct.setCount(rs.getInt("count"));
					list.add(idvProduct);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public IdvProduct selectCount(Integer gymId, Integer prodId) {
		IdvProduct idvProduct = null;
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(FIND_COUNT);) {

			System.out.println("連線成功");
			
			pstmt.setString(1, "1");
			pstmt.setInt(2, gymId);
			pstmt.setInt(3, prodId);
			
			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {
					idvProduct = new IdvProduct();
					idvProduct.setCount(rs.getInt("count"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idvProduct;
	}

	@Override
	public boolean UpdateStatus(String status, Integer id) {
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
