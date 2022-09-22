package com.mem.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.common.adapter.Base64Adapter;
import com.common.util.JavaMailThread;
import com.common.util.VerificationCode;
import com.mem.dao.impl.MemDaoImpl;
import com.mem.dao.intf.MemDaoIntf;
import com.mem.service.intf.MemServiceIntf;
import com.mem.vo.Member;

public class MemServiceImpl implements MemServiceIntf {
	
	private MemDaoIntf dao;
	private Base64Adapter base64;
	public MemServiceImpl() {
		dao = new MemDaoImpl();
		base64 = new Base64Adapter();
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
		
		// 登入後有完整資料
		mem = dao.selectForLogin(username, password);

		// 上一次登入變成上一次的這一次登入
		if (mem.getMemCurrentLogin() != null) {
			mem.setMemLogin(mem.getMemCurrentLogin());
		}
		// 新的這一次登入時間
		mem.setMemCurrentLogin(LocalDateTime.now());
		// 更新資料庫上一次登入和這一次登入
		dao.updateLastLogin(mem);
		
		// 圖片轉base64
		if (mem.getMemPic() != null) {
        	mem.setMemPicBase64(base64.Encoder(mem.getMemPic()));
        	mem.setMemPic(null);
        }
		
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
		
		if (mem.getMemPic() != null) {
        	mem.setMemPicBase64(base64.Encoder(mem.getMemPic()));
        	mem.setMemPic(null);
        }
		
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
		final String pass = dao.selectPassByUsername(username).getMemPassword();
		if ("".equals(password)) {
			mem.setMessage("舊密碼未輸入");
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
	public Member forgetPass(Member mem) {
		final String username = mem.getMemUsername();
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
		if (dao.selectForPass(username, email) == null) {
			mem.setSuccessful(false);
			mem.setMessage("帳號或信箱錯誤！");
			return mem;
		}
		// 讓信件可以抓到名字
		mem.setMemName(dao.selectForPass(username, email).getMemName());
		// JavaMail執行緒
		JavaMailThread.to = mem.getMemEmail();
		JavaMailThread.ch_name = mem.getMemName();
		VerificationCode code = new VerificationCode();
		JavaMailThread.passRandom = code.getRandom();
		mem.setMemVerification(JavaMailThread.passRandom);
		JavaMailThread.messageText = "Hello! " + JavaMailThread.ch_name + " 您的驗證碼為: " + JavaMailThread.passRandom + "\n" + "(30分鐘後過期)";
		JavaMailThread jmt = new JavaMailThread();
		jmt.start();
		
		mem.setSuccessful(true);
		return mem;
	}
	
	public Member checkCode(Member mem) {
		final String input = mem.getMemInputCode();
		final String verification = mem.getMemVerification();
		
		if ("".equals(input)) {
			mem.setMessage("驗證碼未輸入");
			mem.setSuccessful(false);
			return mem;
		}
		if (!input.equals(verification)) {
			mem.setMessage("驗證碼輸入錯誤");
			mem.setSuccessful(false);
			return mem;
		}
		
		mem.setSuccessful(true);
		return mem;
	}
	
	public Member forgetPassChange(Member mem) {
		final String newPass = mem.getMemNewPassword();
		System.out.println(newPass);
		
		mem.setMemPassword(newPass);

		if (dao.updatePassByUsername(mem) == false) {
			mem.setSuccessful(false);
			mem.setMessage("密碼更改出現錯誤，請聯絡管理員!");
			return mem;
		}
		
		mem.setMessage("資料更改成功");
		mem.setSuccessful(true);
		return mem;
	}
	
	public Member updateImg(Member mem) {

		mem.setMemPic(base64.Decoder(mem.getMemPicBase64()));
		
		if (dao.updateImg(mem) == false) {
			mem.setSuccessful(false);
			mem.setMessage("圖片更改出現錯誤，請聯絡管理員!");
		}
		
		return mem;
	}
	
//	public List<Member> getBase64(List<Member> list) {
//
//		for (Member mem : list) {
//			var img = mem.getMemPic();
//			if (img != null) {
//				mem.setMemPicBase64(BASE64 + Base64.getEncoder().encodeToString(img));
//				mem.setMemPic(null);
//			}
//		}
//
//		return list;
//	}
	
	@Override
	public List<Member> findAll() {
		return dao.selectAll();
	}


}
