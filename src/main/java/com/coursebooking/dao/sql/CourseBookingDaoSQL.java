package com.coursebooking.dao.sql;

public class CourseBookingDaoSQL {
	public static final String INSERT = "insert into cour_booking ( mem_id, cour_id, status) values (?, ?, ?)";
	public static final String SELECT_ALL = "select cour_book_id ,mem_id, cour_id,booking_time ,status from cour_booking order by cour_book_id ";
	public static final String SELECT_BY_ID = "select cour_book_id ,mem_id,cour_id,booking_time ,status from cour_booking where cour_book_id  = ?";
	public static final String DELETE = "delete from cour_booking where cour_book_id = ?";
	public static final String UPDATE = "update cour_booking set mem_id=?, cour_id=?, status=? where cour_book_id  = ?";
	public static final String SELECT_BY_MEMBER_ID_AND_GYM_ID = "select * from simple_fitness.cour_booking join simple_fitness.course on cour_booking.cour_id = course.cour_id where mem_id = ? and gym_id = ?;";
	public static final String UPDATE_STATUS = "update cour_booking set status=? where cour_book_id  = ?";
	
}
