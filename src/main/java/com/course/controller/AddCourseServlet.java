package com.course.controller;

import static com.common.util.Constants.GSON;
import static com.common.util.GsonUtil.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

import com.course.service.impl.CourseServiceImpl;
import com.course.service.intf.CourseServiceIntf;
import com.course.vo.Course;

@WebServlet("/course/addCourse")
public class AddCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CourseServiceIntf SERVICE = new CourseServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 		
        BufferedReader br = request.getReader();
        String json = br.readLine();
        Course course = GSON.fromJson(json, Course.class);
        
        course = SERVICE.addCourse(course);
        
        writePojo2Json(response, course);
	}
	
	

}
