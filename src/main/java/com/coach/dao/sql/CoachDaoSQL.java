package com.coach.dao.sql;

public class CoachDaoSQL {
	
	public static final String INSERT = "insert into coach (emp_id, start_time, end_time, status, public) "
			+ "values (?, ?, ?, ?, ?);";
	
	public static final String UPDATE = "update coach set emp_id = ?, start_time = ?, end_time = ?, status = ?, public = ? where coa_id = ?;";
	
	public static final String DELETE = "delete from coach where coa_id = ?;";
	
	public static final String SELECT_BY_ID = "select * from coach where coa_id = ?;";
	
	public static final String SELECT_ALL = "select * from coach order by coa_id;";

	public static final String SELECT_COACH_BY_EMPID_AND_STARTTIME = "select emp_id, start_time from coach where emp_id = ? and ? >= start_time and ? < end_time;";
	
	public static final String SELECT_BY_GYMID_AND_STARTTIME = "select c.coa_id, e.gym_id, c.start_time, c.emp_id, c.status, c.public"
																+ " from coach c"
																+ " join emp e on e.emp_id = c.emp_id"
																+ " where c.public != 2 and e.gym_id = ? and c.start_time between ? and date_add(? ,interval 1 day) order by c.start_time;";
	
	public static final String SELECT_BY_GYMID_AND_STARTTIME_AND_EMPID = "select c.coa_id, e.gym_id, c.start_time, c.end_time, c.emp_id, c.status, c.public"
																		+ " from coach c"
																		+ " join emp e on e.emp_id = c.emp_id"
																		+ " where c.public != 2 and e.gym_id = ? and c.emp_id = ? and (c.start_time between ? and date_add(?, interval 1 day)) order by c.start_time;";
	
	public static final String UPDATE_PUBLIC_STATUS_BY_ID = "update coach set public = ? where coa_id = ?;";
}
