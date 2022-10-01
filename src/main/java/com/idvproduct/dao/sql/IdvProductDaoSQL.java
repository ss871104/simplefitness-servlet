package com.idvproduct.dao.sql;

public class IdvProductDaoSQL {
	public static final String SELECT_COUNT_BY_GYM = "select prod_id, count(*) as count from idv_prod where status=\"1\" and gym_id=? group by prod_id ;";
	
//	public static final String FIND_ALL_PRODUCT_BY_GYM ="select * from product where prod_id in (select distinct prod_id from idv_prod where status= \"1\" and gym_id=?);";

	public static final String FIND_ALL_PRODUCT_BY_GYM ="select a.*, b.count from product a join (select prod_id, count(*) as count from idv_prod where status=\"1\" and gym_id=? group by prod_id)b on a.prod_id = b.prod_id;";

	public static final String FIND_COUNT ="select count(*) as count from idv_prod where status=\"1\" and prod_id=? group by prod_id ;";

}
