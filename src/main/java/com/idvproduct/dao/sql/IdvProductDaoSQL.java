package com.idvproduct.dao.sql;

public class IdvProductDaoSQL {

	public static final String SELECT_COUNT_BY_GYM = "select prod_id, count(*) as count from idv_prod where status=? and gym_id=? group by prod_id ;";
//	public static final String FIND_ALL_PRODUCT_BY_GYM ="select a.*, b.count from product a join (select prod_id, count(*) as count from idv_prod where status=? and gym_id=? group by prod_id)b on a.prod_id = b.prod_id;";
	public static final String FIND_COUNT ="select count(*) as count from idv_prod where status=? and gym_id=? and prod_id=?;";
	public static final String UPDATE_STATUS ="update idv_prod set status=? where idv_id = ?";
	
//	public static final String FIND_ALL_PRODUCT_BY_GYM ="select * from product where prod_id in (select distinct prod_id from idv_prod where status= \"1\" and gym_id=?);";

	public static final String FIND_ALL_PRODUCT_BY_GYM ="select a.*, b.count from product a join (select prod_id, count(*) as count from idv_prod where status=\"1\" and gym_id=? group by prod_id)b on a.prod_id = b.prod_id;";

	public static final String FIND_COUNT ="select count(*) as count from idv_prod where status=\"1\" and prod_id=? group by prod_id ;";
	
	public static final String UPDATE_COUNT = "update idv_prod set count = ? where = gym_id? ;";
	
	public static final String UPDATE_STATUS = "update idv_prod set status=? where idv_id = ? ;";
	
	public static final String UPDATE_PROD_GYM ="update idv_prod set status=?, gym_id=? where idv_id =? ;";
	
	public static final String UPDATE_GYM = "update idv_prod set gym_id=? where idv_id = ? ;";
		
	public static final String SELECT_GYM_GET_PROD = "select * from idv_prod where prod_id=? and gym_id=? ;";
	
}
