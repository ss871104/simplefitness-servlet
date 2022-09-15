package com.mem.dao.sql;

public class MemDaoSQL {
	
	public static final String INSERT = "insert into mem (mem_name, username, pass, email) "
			+ "values (?, ?, ?, ?);";
	
	public static final String UPDATE = "update mem set mem_name = ?, nickname = ?, username = ?, pass = ?, phone = ?, email = ?, gender = ?, birth = ?, start_date = ?, expire_date = ?, status = ? where mem_id = ?;";
	
	public static final String DELETE = "delete from mem where mem_id = ?;";
	
	public static final String SELECT_BY_ID = "select * from mem where mem_id = ?;";
	
	public static final String SELECT_ALL = "select * from mem order by mem_id;";
	
	public static final String SELECT_BY_USERNAME = "select * from mem where username = ?;";

	public static final String SELECT_BY_EMAIL = "select * from mem where email = ?;";
	
	public static final String SELECT_FOR_LOGIN = "select * from mem where username = ? and pass = ?;";
	
	public static final String UPDATE_BY_MEM = "update mem set mem_name = ?, nickname = ?, phone = ?, email = ?, gender = ?, birth = ? where username = ?;";
	
	public static final String UPDATE_PASS_BY_USERNAME = "update mem set pass = ? where username = ?;";

}
