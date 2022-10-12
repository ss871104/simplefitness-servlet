package com.course.controller;

import static com.common.util.GsonUtil.writePojo2Json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.service.impl.CourseServiceImpl;
import com.course.service.intf.CourseServiceIntf;
import com.course.vo.Course;

@WebServlet("/course/getAllCourse")
public class GetAllCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseServiceIntf SERVICE = new CourseServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Course> course = SERVICE.findAll();
		
		writePojo2Json(response, course);
	}

}
