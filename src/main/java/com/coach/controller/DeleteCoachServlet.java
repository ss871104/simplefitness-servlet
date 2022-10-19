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

@WebServlet("/coach/deleteCoach")
public class DeleteCoachServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CoachServiceIntf _coachService = new CoachServiceImpl();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 		
        
		BufferedReader br = request.getReader();
        String json = br.readLine();
        Coach coach = GSON.fromJson(json, Coach.class);
        
        
        coach = _coachService.deteleCoach(coach);
        
        writePojo2Json(response, coach);
		
	}

}
