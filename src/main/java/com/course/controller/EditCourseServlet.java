package com.course.controller;

import static com.common.util.Constants.GSON;
import static com.common.util.GsonUtil.writePojo2Json;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.service.impl.CourseServiceImpl;
import com.course.service.intf.CourseServiceIntf;
import com.course.vo.Course;

@WebServlet("/course/editCourse")
public class EditCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CourseServiceIntf _courseService = new CourseServiceImpl();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		 		
        
		BufferedReader br = request.getReader();
        String json = br.readLine();
        Course course = GSON.fromJson(json, Course.class);
        
        
        course = _courseService.editCourse(course);
        
        writePojo2Json(response, course);
		
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
