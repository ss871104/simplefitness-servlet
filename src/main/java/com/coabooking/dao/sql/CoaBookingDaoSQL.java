package com.coabooking.dao.sql;

public class CoaBookingDaoSQL {
	public static final String INSERT = "insert into coa_booking(mem_id,coa_id,status) values (?, ?, ?)";
	public static final String SELECT_ALL = "select coa_book_id ,mem_id,coa_id,booking_time,status,check_time from coa_booking order by coa_book_id ";
	public static final String SELECT_BY_ID = "select coa_book_id ,mem_id,coa_id,booking_time,status,check_time from coa_booking where coa_book_id  = ?";
	public static final String DELETE = "delete from coa_booking where coa_book_id  = ?";
	public static final String UPDATE = "update coa_booking set mem_id=?, coa_id=?, status=? where coa_book_id  = ?";

}
