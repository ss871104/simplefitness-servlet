package com.mem.controller;

import static com.common.util.GsonUtil.json2Pojo;
import static com.common.util.GsonUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.service.impl.MemServiceImpl;
import com.mem.service.intf.MemServiceIntf;
import com.mem.vo.Member;

@WebServlet("/member/changeforgetpass")
public class ChangeForgetPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemServiceIntf service = new MemServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final HttpSession session = request.getSession();
		
		final String username = ((Member) session.getAttribute("forget")).getMemUsername();
		
		Member member = json2Pojo(request, Member.class);
		
        member.setMemUsername(username);
        
        member = service.forgetPassChange(member);
        
        writePojo2Json(response, member);
	}

}
