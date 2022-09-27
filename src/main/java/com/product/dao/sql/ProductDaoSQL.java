package com.product.dao.sql;

import org.hibernate.sql.Select;

public class ProductDaoSQL {
	
	static String SELECT_PROD_BY_GYMID = "select p.*,i.count\r\n"
			+ "from \r\n"
			+ "	product p \r\n"
			+ "  join (select prod_id, count(*) as count from idv_prod where status=\"1\" and gym_id=? group by prod_id) i\r\n"
			+ "		on p.prod_id = i.prod_id  ";
	
	public static final String SELECT_BY_ID = "select * from product where prod_id = ?";
}
