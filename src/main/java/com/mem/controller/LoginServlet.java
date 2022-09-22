package com.mem.controller;

import static com.common.util.Constants.GSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.service.impl.MemServiceImpl;
import com.mem.service.intf.MemServiceIntf;
import com.mem.vo.Member;

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemServiceIntf SERVICE = new MemServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        BufferedReader br = request.getReader();
        String json = br.readLine();
        Member member = GSON.fromJson(json, Member.class);
        
        member = SERVICE.login(member);
        
		if (member.isSuccessful()) {
			if (request.getSession(false) != null) {
				request.changeSessionId();
			}
			final HttpSession session = request.getSession();
			session.setAttribute("loggedin", true);
			session.setAttribute("member", member);
		}
		PrintWriter pw = response.getWriter();
        pw.print(GSON.toJson(member));
	}

}
