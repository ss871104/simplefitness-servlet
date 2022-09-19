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
import static com.common.util.Constants.BASE64;
import com.mem.service.impl.MemServiceImpl;
import com.mem.service.intf.MemServiceIntf;
import com.mem.vo.Member;

@WebServlet("/member/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemServiceIntf SERVICE = new MemServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		final HttpSession session = request.getSession();
		
		final String username = ((Member) session.getAttribute("member")).getMemUsername();
		
		BufferedReader br = request.getReader();
        String json = br.readLine();
        Member member = GSON.fromJson(json, Member.class);
        member.setMemUsername(username);
        
//        已有替代方案
//        try {
//        	 // 日期格式
//            String birthStr = member.getMemBirthStr();
//            Date birth = Date.valueOf(birthStr);
//            member.setMemBirth(birth);
//		} catch (Exception e) {
//			member.setMemBirth(null);
//		}
        
        
        if (!member.getMemPicBase64().equals("")) {
        	member = SERVICE.updateImg(member);
        } 
        member = SERVICE.memEdit(member);
        
        if (member.isSuccessful()) {
			session.setAttribute("member", member);
		}
        
        PrintWriter pw = response.getWriter();
        pw.print(GSON.toJson(member));
		
	}
	
//	@Override
//	protected void doPut(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		setHeaders(response);
//
//		Member member = GSON.fromJson(request.getReader().readLine(), Member.class);
//		System.out.println(member.getMemPic());
//		System.out.println(member.getMemPicBase64());
//
//		response.getWriter().print(GSON.toJson(SERVICE.updateImg(member)));
//		System.out.println(member.getMemPic());
//		System.out.println(member.getMemPicBase64());
//		
//	}
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
	}
	
	private void setHeaders(HttpServletResponse response) {

		response.setContentType("application/json;charset=UTF-8"); // 重要
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");

		response.addHeader("Access-Control-Allow-Origin", "*"); // 重要
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", "*");
		response.addHeader("Access-Control-Max-Age", "86400");
	}

}
