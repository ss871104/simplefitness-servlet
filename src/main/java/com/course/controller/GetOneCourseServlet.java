package com.course.controller;

import static com.common.util.GsonUtil.json2Pojo;
import static com.common.util.GsonUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.service.impl.CourseServiceImpl;
import com.course.service.intf.CourseServiceIntf;
import com.course.vo.Course;

@WebServlet("/course/getCourseById")
public class GetOneCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseServiceIntf _courseService = new CourseServiceImpl();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Course course = json2Pojo(request, Course.class); 
				
		course = _courseService.findById(course);
		
		System.out.println(course.getGymId());
		
		writePojo2Json(response, course);
	}

}
