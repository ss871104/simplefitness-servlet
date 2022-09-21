package com.mem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.common.util.Constants.GSON;
import com.mem.service.impl.MemServiceImpl;
import com.mem.service.intf.MemServiceIntf;
import com.mem.vo.Member;

@WebServlet("/member/changeforgetpass")
public class ChangeForgetPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemServiceIntf SERVICE = new MemServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final HttpSession session = request.getSession();
		
		final String username = ((Member) session.getAttribute("forget")).getMemUsername();
		
		BufferedReader br = request.getReader();
        String json = br.readLine();
        Member member = GSON.fromJson(json, Member.class);
        member.setMemUsername(username);
        
        member = SERVICE.forgetPassChange(member);
        
        PrintWriter pw = response.getWriter();
        pw.print(GSON.toJson(member));
	}

}
