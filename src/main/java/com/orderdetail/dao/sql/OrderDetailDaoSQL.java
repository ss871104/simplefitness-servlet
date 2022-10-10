package com.orderdetail.dao.sql;

public class OrderDetailDaoSQL {
	public static final String INSERT =
			"insert into order_detail (order_id, idv_id, status) values (?, (select idv_id from idv_prod where gym_id=? and prod_id=? and status =? limit 1), 0);";
	public static final String SELECT_ALL = 
			"select order_code, order_id, idv_id, pickup_time, return_time, status from order_detail order by order_code";
	public static final String SELECT_BY_ID = 
			"select order_code, order_id, idv_id, pickup_time, return_time, status from order_detail where order_code = ?";
	public static final String SELECT_BY_ORDERID = 
			"select od.*, p.prod_name\r\n"
			+ "from\r\n"
			+ "	order_detail od\r\n"
			+ "    join idv_prod i\r\n"
			+ "		on od.idv_id = i.idv_id\r\n"
			+ "	join product p\r\n"
			+ "		on i.prod_id = p.prod_id\r\n"
			+ "    where order_id = ?";
	public static final String UPDATE_STATUS = 
			"update order_detail set status=? where order_code = ?";
	public static final String UPDATE_PICKUP = 
			"update order_detail set pickup_time=?, status=? where order_code = ?";
	public static final String UPDATE_RETURN = 
			"update order_detail set return_time=?, status=? where order_code = ?";

}
