package com.rent.dao.sql;

public class RentDaoSQL {

	public static final String UPDATE_STATUS = "update order_detail set status=? where order_code=?;";

	public static final String UPDATE_ORDERID_STATUS = "update orders set status=? where order_id=?;";

	public static final String SELECT_GET_DETAIL = "select * from order_detail where order_id=?;";

	public static final String SELECT_GET_ORDERID = "select order_id, status from orders where mem_id=?;";

}
