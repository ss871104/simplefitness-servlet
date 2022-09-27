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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(IdvProduct vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IdvProduct selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IdvProduct> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IdvProduct> selectByGym(Integer gymId) {
		
		List<IdvProduct> list = new ArrayList<IdvProduct>();
		IdvProduct idvProduct = null;
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_COUNT_BY_GYM);) {

			System.out.println("連線成功");
			
			pstmt.setInt(1, gymId);
			
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
	public List<Product> getProdInfoByProdId(Integer prodId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
}
