package com.product.dao.sql;

import org.hibernate.sql.Select;

public class ProductDaoSQL {
	
	public static final String INSERT = "insert into product (prod_name, price, intro, pic) "
			+ "values (?, ?, ?, ?);";
	
	public static final String UPDATE = "update product set prod_name = ?, price = ?, intro = ? where prod_id = ?;";
	
	public static final String DELETE = "delete from product where prod_id = ?;";
	
	public static final String SELECT_BY_ID = "select * from product where prod_id = ?";
	
	public static final String SELECT_ALL = "select * from product ;"; 
	
}
