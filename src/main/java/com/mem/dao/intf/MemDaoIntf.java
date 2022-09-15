package com.mem.dao.intf;

import com.common.dao.CommonDao;
import com.mem.vo.Member;

public interface MemDaoIntf extends CommonDao<Member, Integer> {
	
	public Member selectByUsername(String memUsername);
	public Member selectByEmail(String memEmail);
	public Member selectForLogin(String memUsername, String memPassword);
	public boolean updateByMem(Member memVo);
	public boolean updatePassByUsername(Member memVo);

}
