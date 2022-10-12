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
																+ "	where gym_id = ? and start_time between ? and date_add(?,interval 1 day)"
																+ "	order by start_time;";

	public static final String SELECT_COURSE_BY_EMPID_AND_STARTTIME = "select emp_id, start_time, end_time from course where emp_id = ? and ? >= start_time and ? < end_time;";
	

	
}
