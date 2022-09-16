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

import com.common.util.JavaMail;
import com.common.util.VerificationCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mem.service.impl.MemServiceImpl;
import com.mem.service.intf.MemServiceIntf;
import com.mem.vo.Member;

@WebServlet("/member/forgetpass")
public class ForgetPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemServiceIntf SERVICE = new MemServiceImpl();
	private Gson gson = new GsonBuilder().create();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		BufferedReader br = request.getReader();
        String json = br.readLine();
        Member member = gson.fromJson(json, Member.class);
        
        member = SERVICE.forgetPass(member);
		
		if (member.isSuccessful()) {
			String to = member.getMemEmail();

			String subject = "忘記密碼確認信";

			String ch_name = member.getMemName();
			VerificationCode code = new VerificationCode();
			String passRandom = code.getRandom();
			member.setMemVerification(passRandom);
			String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" + "(30分鐘後過期)";
	        
	        JavaMail mailService = new JavaMail();
			mailService.sendMail(to, subject, messageText);
			
			final HttpSession session = request.getSession();
			session.setAttribute("forget", member);
		}
        
        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(member));
        
	}
	
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
