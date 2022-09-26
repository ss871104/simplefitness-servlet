package com.course.dao.sql;

public class CourseDaoSQL {
	
	public static final String INSERT = "insert into course (emp_id, gym_id, cour_list_id, start_time, end_time, status, public) "
			+ "values (?, ?, ?, ?, ?, ?, ?);";
	
	public static final String UPDATE = "update course set emp_id = ?, gym_id = ?, cour_list_id = ?,  start_time = ?, end_time = ?, status = ?, public = ? where cour_id = ?;";
	
	public static final String DELETE = "delete from course where cour_id = ?;";
	
	public static final String SELECT_BY_ID = "select * from course where cour_id = ?;";
	
	public static final String SELECT_ALL = "select * from course order by cour_id;";
	
	public static final String SELECT_BY_GYMID_AND_STARTTIME = "select gym_id, start_time, cour_list_id, emp_id, status, public"
																+ "	from course"
																+ "	where gym_id = ? and start_time like concat( substr( ?, 1, 10), '%') "
																+ "	order by start_time;";

}
