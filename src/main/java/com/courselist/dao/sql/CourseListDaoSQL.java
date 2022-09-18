package com.courselist.dao.sql;

public class CourseListDaoSQL {
	
	public static final String INSERT = "insert into cour_list (cour_name, cour_type, max_p, intro, status) "
			+ "values (?, ?, ?, ?, ?);";
	
	public static final String UPDATE = "update cour_list set cour_name = ?, cour_type = ?, max_p = ?, intro = ?, status = ? where cour_list_id = ?;";
	
	public static final String DELETE = "delete from cour_list where cour_list_id = ?;";
	
	public static final String SELECT_BY_ID = "select * from cour_list where cour_list_id = ?;";
	
	public static final String SELECT_ALL = "select * from cour_list order by cour_list_id;";
	
}
