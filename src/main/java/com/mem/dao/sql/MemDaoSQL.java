package com.mem.dao.sql;

public class MemDaoSQL {
	
	public static final String INSERT = "insert into mem (mem_name, username, pass, email) "
			+ "values (?, ?, ?, ?);";  
	
	public static final String UPDATE = "update mem set phone = ?, email = ?, start_date = ?, expire_date = ?, status = ? where mem_id = ?;";
	
	public static final String DELETE = "delete from mem where mem_id = ?;";
	
	public static final String SELECT_BY_ID = "select mem_id, mem_name, nickname, username, phone, email, gender, birth, register_date, start_date, expire_date, last_login, status, pic, qr_code, current_login from mem where mem_id = ?;";
	
	public static final String SELECT_ALL = "select mem_id, mem_name, nickname, username, phone, email, gender, birth, register_date, start_date, expire_date, last_login, status, pic, qr_code, current_login from mem order by mem_id;";
	
	public static final String SELECT_BY_USERNAME = "select mem_id, mem_name, nickname, username, phone, email, gender, birth, register_date, start_date, expire_date, last_login, status, pic, qr_code, current_login from mem where username = ?;";

	public static final String SELECT_BY_EMAIL = "select mem_id, mem_name, nickname, username, phone, email, gender, birth, register_date, start_date, expire_date, last_login, status, pic, qr_code, current_login from mem where email = ?;";
	
	public static final String SELECT_FOR_LOGIN = "select mem_id, mem_name, nickname, username, phone, email, gender, birth, register_date, start_date, expire_date, last_login, status, pic, qr_code, current_login from mem where username = ? and pass = ?;";
	
	public static final String UPDATE_BY_MEM = "update mem set mem_name = ?, nickname = ?, phone = ?, email = ?, gender = ?, birth = ? where username = ?;";
	
	public static final String UPDATE_PASS_BY_USERNAME = "update mem set pass = ? where username = ?;";
	
	public static final String SELECT_FOR_PASS = "select mem_id, mem_name, nickname, username, phone, email, gender, birth, register_date, start_date, expire_date, last_login, status, pic, qr_code, current_login from mem where username = ? and email = ?;";
	
	public static final String UPDATE_LAST_LOGIN = "update mem set last_login = ?, current_login = ? where username = ?;";
	
	public static final String UPDATE_IMG = "update mem set pic = ? where username = ?;";
	
	public static final String SELECT_PASS_BY_USERNAME = "select pass from mem where username = ?;";

}
