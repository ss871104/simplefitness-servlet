package com.order.dao.sql;

public class OrderDaoSQL {
	public static final String INSERT =
			"insert into orders (mem_id, gym_id, amount, order_time, status) values (?, ?, ?, ?, ?)";
	public static final String SELECT_ALL = 
			"select order_id, mem_id, gym_id, amount, order_time, status from orders order by order_id";
	public static final String SELECT_BY_ID = 
			"select order_id, mem_id, gym_id, amount, order_time, status from orders where order_id = ?";
	public static final String SELECT_BY_MEM = 
			"select order_id, mem_id, gym_id, amount, order_time, status from orders where mem_id = ?";
	public static final String SELECT_BY_GYM = 
			"select order_id, mem_id, gym_id, amount, order_time, status from orders where gym_id = ?";
	public static final String UPDATE_STATUS = 
			"update orders set status=? where order_id = ?";

	
	public static final String DELETE =
			"delete from orders where order_id = ?";
	public static final String UPDATE = 
			"update orders set mem_id=?, gym_id=?, amount=?, order_time=?, status=? where order_id = ?";
}
