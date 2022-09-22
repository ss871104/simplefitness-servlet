package com.mem.dao.intf;

import com.common.dao.CommonDao;
import com.mem.vo.Member;

public interface MemDaoIntf extends CommonDao<Member, Integer> {
	
	public Member selectByUsername(String memUsername);
	public Member selectByEmail(String memEmail);
	public Member selectForLogin(String memUsername, String memPassword);
	public boolean updateByMem(Member memVo);
	public boolean updatePassByUsername(Member memVo);
	public Member selectForPass(String memUsername, String memEmail);
	public boolean updateLastLogin(Member memVo);
	public boolean updateImg(Member memVo);
	public Member selectPassByUsername(String memUsername);

}
