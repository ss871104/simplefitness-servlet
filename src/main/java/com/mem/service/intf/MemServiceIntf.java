package com.mem.service.intf;

import java.util.List;

import com.mem.vo.Member;

public interface MemServiceIntf {
	
	Member register(Member mem);
	
	Member login(Member mem);
	
	Member memEdit(Member mem);
	
	Member empEdit(Member mem);
	
	Member passChange(Member mem);
	
	Member forgetPass(Member mem);
	
	Member checkCode(Member mem);
	
	Member forgetPassChange(Member mem);
	
	Member updateImg(Member mem);
	
	Member findById(Member mem);
	
	List<Member> findAll();	
	
}
