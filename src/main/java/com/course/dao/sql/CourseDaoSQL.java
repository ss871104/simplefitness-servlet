package com.course.dao.sql;

public class CourseDaoSQL {
	
	public static final String INSERT = "insert into course course (emp_id, gym_id, cour_list_id,  start_time, end_time, status, public) "
			+ "values (?, ?, ?, ?, ?, ?, ?);";
	
	public static final String UPDATE = "update course set emp_id = ?, gym_id = ?, cour_list_id = ?,  start_time = ?, end_time = ?, status = ?, public = ? where cour_id = ?;";
	
	public static final String DELETE = "delete from course where cour_id = ?;";
	
	public static final String SELECT_BY_ID = "select * from course where cour_id = ?;";
	
	public static final String SELECT_ALL = "select * from course order by cour_id;";
	
	public static final String SELECT_BY_GYM_DATE = "select g.gym_name, c.start_time, c.end_time, cl.cour_type, cl.cour_name, e.emp_name, c.status, c.public"
													+ "from cour_list cl"
													+ "join course c "
													+ "	on cl.cour_list_id = c.cour_list_id"
													+ "join gym g"
													+ "	on c.gym_id = g.gym_id"
													+ "join emp e"
													+ "	on e.emp_id = c.emp_id"
													+ "where c.gym_id = ?"
													+ "order by c.start_time;";

}
