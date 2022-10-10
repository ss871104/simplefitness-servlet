package com.idvproduct.dao.sql;

public class IdvProductDaoSQL {
	public static final String SELECT_COUNT_BY_GYM = "select prod_id, count(*) as count from idv_prod where status=? and gym_id=? group by prod_id ;";
//	public static final String FIND_ALL_PRODUCT_BY_GYM ="select a.*, b.count from product a join (select prod_id, count(*) as count from idv_prod where status=? and gym_id=? group by prod_id)b on a.prod_id = b.prod_id;";
	public static final String FIND_COUNT ="select count(*) as count from idv_prod where status=? and gym_id=? and prod_id=?;";
	public static final String UPDATE_STATUS ="update idv_prod set status=? where idv_id = ?";
}
