package com.product.dao.impl;

import static com.product.dao.sql.ProductDaoSQL.*;


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

import com.product.dao.intf.ProductDaoIntf;
import com.product.vo.Product;

public class ProductDaoImpl implements ProductDaoIntf{
	
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
	public boolean insert(Product vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Product vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Product selectById(Integer prodId) {
		Product product =null;
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID);) {

			System.out.println("連線成功");

			pstmt.setInt(1, prodId);

			try (ResultSet rs = pstmt.executeQuery()) {

				product = new Product();

				if (rs.next()) {
					product.setProdId(rs.getInt("prod_id"));
					product.setProdName(rs.getString("prod_name"));
					product.setPrice(rs.getInt("price"));
					product.setIntro(rs.getString("intro"));
					product.setProdPic(rs.getBytes("pic"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	
	}

	@Override
	public List<Product> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	


}
