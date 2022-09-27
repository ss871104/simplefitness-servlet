package com.courselist.controller;

import static com.common.util.GsonUtil.writePojo2Json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courselist.service.impl.CourseListServiceImpl;
import com.courselist.service.intf.CourseListServiceIntf;
import com.courselist.vo.CourseList;

@WebServlet("/courseList/getAllCourse")
public class GetAllCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseListServiceIntf SERVICE = new CourseListServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<CourseList> cList = SERVICE.findAll();
		
		writePojo2Json(response, cList);
	}


}
