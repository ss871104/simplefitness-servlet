package com.course.dao.sql;

public class CourseDaoSQL {
	
	public static final String INSERT = "insert into course (emp_id, gym_id, cour_list_id, start_time, end_time, status, public) "
			+ "values (?, ?, ?, ?, ?, ?, ?);";
	
	public static final String UPDATE = "update course set emp_id = ?, gym_id = ?, cour_list_id = ?, start_time = ?, end_time = ?, status = ?, public = ? where cour_id = ?;";
	
	public static final String DELETE = "delete from course where cour_id = ?;";
	
	public static final String SELECT_BY_ID = "select * from course where cour_id = ?;";
	
	public static final String SELECT_ALL = "select * from course order by cour_id;";

}
