package com.orderdetail.dao.sql;

public class OrderDetailDaoSQL {
	public static final String INSERT =
			"insert into order_detail (order_id, idv_id, status) values (?, ?, ?)";
	public static final String SELECT_ALL = 
			"select order_code, order_id, idv_id, pickup_time, return_time, status from order_detail order by order_code";
	public static final String SELECT_BY_ID = 
			"select order_code, order_id, idv_id, pickup_time, return_time, status from order_detail where order_code = ?";
	public static final String SELECT_BY_ORDERID = 
			"select order_code, order_id, idv_id, pickup_time, return_time, status from order_detail where order_id = ?";
	public static final String UPDATE_STATUS = 
			"update order_detail set status=? where order_code = ?";
	public static final String UPDATE_PICKUP = 
			"update order_detail set pickup_time=?, status=? where order_code = ?";
	public static final String UPDATE_RETURN = 
			"update order_detail set return_time=?, status=? where order_code = ?";

}
