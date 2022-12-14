package com.mem.dao.impl;

import static com.mem.dao.sql.MemDaoSQL.DELETE;
import static com.mem.dao.sql.MemDaoSQL.INSERT;
import static com.mem.dao.sql.MemDaoSQL.SELECT_ALL;
import static com.mem.dao.sql.MemDaoSQL.SELECT_BY_EMAIL;
import static com.mem.dao.sql.MemDaoSQL.SELECT_BY_ID;
import static com.mem.dao.sql.MemDaoSQL.SELECT_BY_USERNAME;
import static com.mem.dao.sql.MemDaoSQL.SELECT_FOR_LOGIN;
import static com.mem.dao.sql.MemDaoSQL.SELECT_FOR_PASS;
import static com.mem.dao.sql.MemDaoSQL.SELECT_PASS_BY_USERNAME;
import static com.mem.dao.sql.MemDaoSQL.UPDATE;
import static com.mem.dao.sql.MemDaoSQL.UPDATE_BY_MEM;
import static com.mem.dao.sql.MemDaoSQL.UPDATE_IMG;
import static com.mem.dao.sql.MemDaoSQL.UPDATE_LAST_LOGIN;
import static com.mem.dao.sql.MemDaoSQL.UPDATE_PASS_BY_USERNAME;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mem.dao.intf.MemDaoIntf;
import com.mem.vo.Member;

public class MemDaoImpl implements MemDaoIntf {
	
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Test");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public boolean insert(Member memVo) {
		
		int rowCount = 0;
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT);) {
			
			System.out.println("連線成功");
			
			pstmt.setString(1, memVo.getMemName());
			pstmt.setString(2, memVo.getMemUsername());
			pstmt.setString(3, memVo.getMemPassword());
			pstmt.setString(4, memVo.getMemEmail());
			
			rowCount = pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rowCount != 0;
	}

	@Override
	public boolean update(Member memVo) {
		
		int rowCount = 0;
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE);) {
			
			System.out.println("連線成功");
			
			pstmt.setString(1, memVo.getMemPhone());
			pstmt.setString(2, memVo.getMemEmail());
			pstmt.setObject(3, memVo.getMemStart());
			pstmt.setObject(4, memVo.getMemExpire());
			pstmt.setString(5, memVo.getMemStatus());
			pstmt.setInt(6, memVo.getMemId());
			
			rowCount = pstmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean delete(Integer memId) {
		
		int rowCount = 0;
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(DELETE);) {
			
			System.out.println("連線成功");
			
			pstmt.setInt(1, memId);
			
			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public Member selectById(Integer memId) {
		
		Member mem = null;
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID);) {
			
			System.out.println("連線成功");
			
			pstmt.setInt(1, memId);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				
				mem = new Member();
				
				if (rs.next()) {
					mem.setMemId(rs.getInt("mem_id"));
					mem.setMemName(rs.getString("mem_name"));
					mem.setMemNickname(rs.getString("nickname"));
					mem.setMemUsername(rs.getString("username"));
					mem.setMemPhone(rs.getString("phone"));
					mem.setMemEmail(rs.getString("email"));
					mem.setMemGender(rs.getString("gender"));
					mem.setMemBirth(rs.getObject("birth", LocalDate.class));
					mem.setMemRegister(rs.getObject("register_date", LocalDate.class));
					mem.setMemStart(rs.getObject("start_date", LocalDate.class));
					mem.setMemExpire(rs.getObject("expire_date", LocalDate.class));
					mem.setMemLogin(rs.getObject("last_login", LocalDateTime.class));
					mem.setMemStatus(rs.getString("status"));
					mem.setMemPic(rs.getBytes("pic"));
					mem.setMemQrCode(rs.getString("qr_code"));
					mem.setMemCurrentLogin(rs.getObject("current_login", LocalDateTime.class));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mem;
	}

	@Override
	public List<Member> selectAll() {
		
		List<Member> list = new ArrayList<Member>();

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_ALL);) {
			
			System.out.println("連線成功");
			
			try (ResultSet rs = pstmt.executeQuery()) {
				
				while (rs.next()) {
					Member mem = new Member();
					mem.setMemId(rs.getInt("mem_id"));
					mem.setMemName(rs.getString("mem_name"));
					mem.setMemNickname(rs.getString("nickname"));
					mem.setMemUsername(rs.getString("username"));
					mem.setMemPhone(rs.getString("phone"));
					mem.setMemEmail(rs.getString("email"));
					mem.setMemGender(rs.getString("gender"));
					mem.setMemBirth(rs.getObject("birth", LocalDate.class));
					mem.setMemRegister(rs.getObject("register_date", LocalDate.class));
					mem.setMemStart(rs.getObject("start_date", LocalDate.class));
					mem.setMemExpire(rs.getObject("expire_date", LocalDate.class));
					mem.setMemLogin(rs.getObject("last_login", LocalDateTime.class));
					mem.setMemStatus(rs.getString("status"));
					mem.setMemPic(rs.getBytes("pic"));
					mem.setMemQrCode(rs.getString("qr_code"));
					mem.setMemCurrentLogin(rs.getObject("current_login", LocalDateTime.class));
					list.add(mem);	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public Member selectByUsername(String memUsername) {
		
		Member mem = null;
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_BY_USERNAME);) {
			
			System.out.println("連線成功");
			
			pstmt.setString(1, memUsername);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				
				mem = new Member();
				
				if (rs.next()) {
					mem.setMemId(rs.getInt("mem_id"));
					mem.setMemName(rs.getString("mem_name"));
					mem.setMemNickname(rs.getString("nickname"));
					mem.setMemUsername(rs.getString("username"));
					mem.setMemPhone(rs.getString("phone"));
					mem.setMemEmail(rs.getString("email"));
					mem.setMemGender(rs.getString("gender"));
					mem.setMemBirth(rs.getObject("birth", LocalDate.class));
					mem.setMemRegister(rs.getObject("register_date", LocalDate.class));
					mem.setMemStart(rs.getObject("start_date", LocalDate.class));
					mem.setMemExpire(rs.getObject("expire_date", LocalDate.class));
					mem.setMemLogin(rs.getObject("last_login", LocalDateTime.class));
					mem.setMemStatus(rs.getString("status"));
					mem.setMemPic(rs.getBytes("pic"));
					mem.setMemQrCode(rs.getString("qr_code"));
					mem.setMemCurrentLogin(rs.getObject("current_login", LocalDateTime.class));
				} else {
					mem = null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return mem;
	}
	
	@Override
	public Member selectByEmail(String memEmail) {
		
		Member mem = null;
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_BY_EMAIL);) {
			
			System.out.println("連線成功");
			
			pstmt.setString(1, memEmail);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				
				mem = new Member();
				
				if (rs.next()) {
					mem.setMemId(rs.getInt("mem_id"));
					mem.setMemName(rs.getString("mem_name"));
					mem.setMemNickname(rs.getString("nickname"));
					mem.setMemUsername(rs.getString("username"));
					mem.setMemPhone(rs.getString("phone"));
					mem.setMemEmail(rs.getString("email"));
					mem.setMemGender(rs.getString("gender"));
					mem.setMemBirth(rs.getObject("birth", LocalDate.class));
					mem.setMemRegister(rs.getObject("register_date", LocalDate.class));
					mem.setMemStart(rs.getObject("start_date", LocalDate.class));
					mem.setMemExpire(rs.getObject("expire_date", LocalDate.class));
					mem.setMemLogin(rs.getObject("last_login", LocalDateTime.class));
					mem.setMemStatus(rs.getString("status"));
					mem.setMemPic(rs.getBytes("pic"));
					mem.setMemQrCode(rs.getString("qr_code"));
					mem.setMemCurrentLogin(rs.getObject("current_login", LocalDateTime.class));
				} else {
					mem = null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return mem;
	}

	@Override
	public Member selectForLogin(String memUsername, String memPassword) {
		
		Member mem = null;
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_FOR_LOGIN);) {
			
			System.out.println("連線成功");
			
			pstmt.setString(1, memUsername);
			pstmt.setString(2, memPassword);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				
				mem = new Member();
				
				if (rs.next()) {
					mem.setMemId(rs.getInt("mem_id"));
					mem.setMemName(rs.getString("mem_name"));
					mem.setMemNickname(rs.getString("nickname"));
					mem.setMemUsername(rs.getString("username"));
					mem.setMemPhone(rs.getString("phone"));
					mem.setMemEmail(rs.getString("email"));
					mem.setMemGender(rs.getString("gender"));
					mem.setMemBirth(rs.getObject("birth", LocalDate.class));
					mem.setMemRegister(rs.getObject("register_date", LocalDate.class));
					mem.setMemStart(rs.getObject("start_date", LocalDate.class));
					mem.setMemExpire(rs.getObject("expire_date", LocalDate.class));
					mem.setMemLogin(rs.getObject("last_login", LocalDateTime.class));
					mem.setMemStatus(rs.getString("status"));
					mem.setMemPic(rs.getBytes("pic"));
					mem.setMemQrCode(rs.getString("qr_code"));
					mem.setMemCurrentLogin(rs.getObject("current_login", LocalDateTime.class));
				} else {
					mem = null;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mem;
	}

	@Override
	public boolean updateByMem(Member memVo) {
		
		int rowCount = 0;
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE_BY_MEM);) {
			
			System.out.println("連線成功");
			
			pstmt.setString(1, memVo.getMemName());
			pstmt.setString(2, memVo.getMemNickname());
			pstmt.setString(3, memVo.getMemPhone());
			pstmt.setString(4, memVo.getMemEmail());
			pstmt.setString(5, memVo.getMemGender());
			pstmt.setObject(6, memVo.getMemBirth());
			pstmt.setString(7, memVo.getMemUsername());
			
			rowCount = pstmt.executeUpdate();		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}
	
	@Override
	public boolean updatePassByUsername(Member memVo) {
		
	
		int rowCount = 0;
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE_PASS_BY_USERNAME);) {
			
			System.out.println("連線成功");
			
			pstmt.setString(1, memVo.getMemPassword());
			pstmt.setString(2, memVo.getMemUsername());
			
			rowCount = pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public Member selectForPass(String memUsername, String memEmail) {
		
		Member mem = null;
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_FOR_PASS);) {
			
			System.out.println("連線成功");
			
			pstmt.setString(1, memUsername);
			pstmt.setString(2, memEmail);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				
				mem = new Member();
				
				if (rs.next()) {
					mem.setMemId(rs.getInt("mem_id"));
					mem.setMemName(rs.getString("mem_name"));
					mem.setMemNickname(rs.getString("nickname"));
					mem.setMemUsername(rs.getString("username"));
					mem.setMemPhone(rs.getString("phone"));
					mem.setMemEmail(rs.getString("email"));
					mem.setMemGender(rs.getString("gender"));
					mem.setMemBirth(rs.getObject("birth", LocalDate.class));
					mem.setMemRegister(rs.getObject("register_date", LocalDate.class));
					mem.setMemStart(rs.getObject("start_date", LocalDate.class));
					mem.setMemExpire(rs.getObject("expire_date", LocalDate.class));
					mem.setMemLogin(rs.getObject("last_login", LocalDateTime.class));
					mem.setMemStatus(rs.getString("status"));
					mem.setMemPic(rs.getBytes("pic"));
					mem.setMemQrCode(rs.getString("qr_code"));
					mem.setMemCurrentLogin(rs.getObject("current_login", LocalDateTime.class));
				} else {
					mem = null;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mem;
	}
	
	@Override
	public boolean updateLastLogin(Member memVo) {
		
		int rowCount = 0;
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE_LAST_LOGIN);) {
			
			System.out.println("連線成功");
			
			pstmt.setObject(1, memVo.getMemLogin());
			pstmt.setObject(2, memVo.getMemCurrentLogin());
			pstmt.setString(3, memVo.getMemUsername());
			
			rowCount = pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}
	
	@Override
	public boolean updateImg(Member memVo) {
		
		int rowCount = 0;

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(UPDATE_IMG);) {
			
			System.out.println("連線成功");

			pstmt.setBytes(1, memVo.getMemPic());
			pstmt.setString(2, memVo.getMemUsername());

			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public Member selectPassByUsername(String memUsername) {
		Member mem = null;
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SELECT_PASS_BY_USERNAME);) {
			
			System.out.println("連線成功");
			
			pstmt.setString(1, memUsername);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				
				mem = new Member();
				
				if (rs.next()) {
					mem.setMemPassword(rs.getString("pass"));

				} else {
					mem = null;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mem;
	}


}
