package com.course.dao.sql;

public class CourseDaoSQL {
	
	public static final String INSERT = "insert into course (emp_id, gym_id, cour_list_id, start_time, end_time, status, public) "
			+ "values (?, ?, ?, ?, ?, ?, ?);";
	
	public static final String UPDATE = "update course set emp_id = ?, gym_id = ?, cour_list_id = ?,  start_time = ?, end_time = ?, status = ?, public = ? where cour_id = ?;";
	
	public static final String DELETE = "delete from course where cour_id = ?;";
	
	public static final String SELECT_BY_ID = "select * from course where cour_id = ?;";
	
	public static final String SELECT_ALL = "select * from course order by cour_id;";
	
	public static final String SELECT_BY_GYMID_AND_STARTTIME = "select cour_id, gym_id, start_time, cour_list_id, emp_id, status, public"
																+ "	from course"
																+ "	where public != 2 and gym_id = ? and start_time between ? and date_add(?,interval 1 day)"
																+ "	order by start_time;";

	public static final String SELECT_COURSE_BY_EMPID_AND_STARTTIME = "select emp_id, start_time, end_time from course where public != 2 and emp_id = ? and ? >= start_time and ? < end_time;";
	
	public static final String UPDATE_PUBLIC_STATUS_BY_ID = "update course set public = ? where cour_id = ?;";
	
	public static final String SELECT_BOOKED_MEMBER_BY_ID = "select c.cour_id, c.emp_id, c.gym_id, cl.cour_name, c.start_time, c.end_time, m.mem_name, m.gender, m.phone, cb.booking_time from course c"
															+ " join cour_list cl on cl.cour_list_id = c.cour_list_id"
															+ " join cour_booking cb on c.cour_id = cb.cour_id"
															+ " join mem m on m.mem_id = cb.mem_id"
															+ " where cb.status = 1 and c.cour_id = ?;";
}
