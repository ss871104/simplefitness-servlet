package com.mem.service.impl;

import java.util.List;

import com.mem.dao.impl.MemDaoImpl;
import com.mem.dao.intf.MemDaoIntf;
import com.mem.service.intf.MemServiceIntf;
import com.mem.vo.Member;

public class MemServiceImpl implements MemServiceIntf {
	
	private MemDaoIntf dao;
	public MemServiceImpl() {
		dao = new MemDaoImpl();
	}

	@Override
	public Member register(Member mem) {
		final String username = mem.getMemUsername();
		final String password = mem.getMemPassword();
		final String name = mem.getMemName();
		final String email = mem.getMemEmail();
		
		if ("".equals(username)) {
			mem.setMessage("帳號未輸入");
			mem.setSuccessful(false);
			return mem;
		}
		if ("".equals(email)) {
			mem.setMessage("信箱未輸入");
			mem.setSuccessful(false);
			return mem;
		}
		if (email.contains("@") == false) {
			mem.setMessage("未符合信箱格式");
			mem.setSuccessful(false);
			return mem;
		}
		if ("".equals(name)) {
			mem.setMessage("名稱未輸入");
			mem.setSuccessful(false);
			return mem;
		}
		if ("".equals(password)) {
			mem.setMessage("密碼未輸入");
			mem.setSuccessful(false);
			return mem;
		}
		if (dao.selectByUsername(username) != null) {
			mem.setMessage("此帳號已被註冊");
			mem.setSuccessful(false);
			return mem;
		}
		if (dao.selectByEmail(email) != null) {
			mem.setMessage("此信箱已被註冊");
			mem.setSuccessful(false);
			return mem;
		}
		if (dao.insert(mem) == false) {
			mem.setMessage("註冊錯誤，請聯絡管理員!");
			mem.setSuccessful(false);
			return mem;
		}
		
		mem.setSuccessful(true);
		return mem;
	}

	@Override
	public Member login(Member mem) {
		final String username = mem.getMemUsername();
		final String password = mem.getMemPassword();
		
		if ("".equals(username)) {
			mem.setMessage("帳號未輸入");
			mem.setSuccessful(false);
			return mem;
		}
		if ("".equals(password)) {
			mem.setMessage("密碼未輸入");
			mem.setSuccessful(false);
			return mem;
		}
		if (dao.selectForLogin(username, password) == null) {
			mem.setSuccessful(false);
			mem.setMessage("帳號或密碼錯誤！");
			return mem;
		}
		mem = dao.selectForLogin(username, password);
		mem.setSuccessful(true);
		return mem;	
	}

	@Override
	public Member memEdit(Member mem) {
		
		if (dao.updateByMem(mem) == false || dao.selectByUsername(mem.getMemUsername()) == null) {
			mem.setMessage("資料更改出現錯誤，請聯絡管理員!");
			mem.setSuccessful(false);
			return mem;
		}
		// 回會員編輯有完整session
		mem = dao.selectByUsername(mem.getMemUsername());
		
		mem.setMessage("資料更改成功");
		mem.setSuccessful(true);
		return mem;
		
	}

	@Override
	public Member empEdit(Member mem) {
		final Member eEdit = dao.selectById(mem.getMemId());
		mem.setMemName(eEdit.getMemName());
		mem.setMemNickname(eEdit.getMemNickname());
		mem.setMemUsername(eEdit.getMemUsername());
		mem.setMemPassword(eEdit.getMemPassword());
		mem.setMemPhone(eEdit.getMemPhone());
		mem.setMemEmail(eEdit.getMemEmail());
		mem.setMemGender(eEdit.getMemGender());
		mem.setMemBirth(eEdit.getMemBirth());
		mem.setMemStart(eEdit.getMemStart());
		mem.setMemExpire(eEdit.getMemExpire());
		mem.setMemStatus(eEdit.getMemStatus());
		mem.setMemId(eEdit.getMemId());
		if (dao.update(mem) == false) {
			mem.setMessage("資料更改出現錯誤");
			mem.setSuccessful(false);
			return mem;
		}
		
		mem.setMessage("資料更改成功");
		mem.setSuccessful(true);
		return mem;
	}
	
	@Override
	public Member passChange(Member mem) {
		final String password = mem.getMemPassword();
		final String newPass = mem.getMemNewPassword();
		final String username = mem.getMemUsername();
		final String pass = dao.selectByUsername(username).getMemPassword();
		if ("".equals(password)) {
			mem.setMessage("密碼未輸入");
			mem.setSuccessful(false);
			return mem;
		}
		if (!pass.equals(password)) {
			mem.setSuccessful(false);
			mem.setMessage("舊密碼輸入錯誤！");
			return mem;
		}
		if (password.equals(newPass)) {
			mem.setSuccessful(false);
			mem.setMessage("舊密碼與新密碼相同！");
			return mem;
		}
		mem.setMemPassword(newPass);
		if (dao.updatePassByUsername(mem) == false) {
			mem.setSuccessful(false);
			mem.setMessage("密碼更改出現錯誤，請聯絡管理員!");
			return mem;
		}
		// 回會員編輯有完整session
		mem = dao.selectByUsername(mem.getMemUsername());
		
		mem.setMessage("資料更改成功");
		mem.setSuccessful(true);
		return mem;
	}
	
	@Override
	public List<Member> findAll() {
		return dao.selectAll();
	}


}