package com.coach.controller;

import static com.common.util.Constants.GSON;
import static com.common.util.GsonUtil.writePojo2Json;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coach.service.impl.CoachServiceImpl;
import com.coach.service.intf.CoachServiceIntf;
import com.coach.vo.Coach;
import com.course.service.impl.CourseServiceImpl;
import com.course.service.intf.CourseServiceIntf;
import com.course.vo.Course;

@WebServlet("/coach/editCoach")
public class EditCoachServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CoachServiceIntf _coachService = new CoachServiceImpl();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		 		
        
		BufferedReader br = request.getReader();
        String json = br.readLine();
        Coach coach = GSON.fromJson(json, Coach.class);
        
        
        coach = _coachService.editCoach(coach);
        
        writePojo2Json(response, coach);
		
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
