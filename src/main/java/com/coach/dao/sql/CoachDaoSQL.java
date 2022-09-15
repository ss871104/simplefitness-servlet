package com.coach.dao.sql;

public class CoachDaoSQL {
	
	public static final String INSERT = "insert into coach (emp_id, start_time, end_time, upload_time, status, public) "
			+ "values (?, ?, ?, ?, ?, ?);";
	
	public static final String UPDATE = "update coach set emp_id = ?, start_time = ?, end_time = ?, upload_time = ?, status = ?, public = ? where coa_id = ?;";
	
	public static final String DELETE = "delete from coach where coa_id = ?;";
	
	public static final String SELECT_BY_ID = "select * from coach where coa_id = ?;";
	
	public static final String SELECT_ALL = "select * from coach order by coa_id;";

}
