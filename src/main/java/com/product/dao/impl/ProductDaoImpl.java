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

public class ProductDaoImpl implements ProductDaoIntf {

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
	public boolean insert(Product prodVo) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT);) {

			System.out.println("連線成功");

			pstmt.setString(1, prodVo.getProdName());
			pstmt.setInt(2, prodVo.getPrice());
			pstmt.setString(3, prodVo.getIntro());
			pstmt.setBytes(4, prodVo.getProdPic());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;

	}

	@Override
	public boolean update(Product prodVo) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE);) {

			System.out.println("連線成功");

			pstmt.setString(1, prodVo.getProdName());
			pstmt.setInt(2, prodVo.getPrice());
			pstmt.setString(3, prodVo.getIntro());
			pstmt.setInt(4, prodVo.getProdId());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;

	}

	@Override
	public boolean delete(Integer prodId) {

		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(DELETE);) {

			System.out.println("連線成功");

			pstmt.setInt(1, prodId);

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;

	}

	@Override
	public Product selectById(Integer prodId) {
		Product product = null;

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
		List<Product> list = new ArrayList<Product>();
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_ALL);) {
			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					Product product = new Product();
					product.setProdId(rs.getInt("prod_id"));
					product.setProdName(rs.getString("prod_name"));
					product.setPrice(rs.getInt("price"));
					product.setIntro(rs.getString("intro"));
					product.setProdPic(rs.getBytes("pic"));
					list.add(product);

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public Product selectByProdName(String prodName) {
		// TODO Auto-generated method stub
		return null;
	}

}
