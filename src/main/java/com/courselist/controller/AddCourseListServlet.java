package com.courselist.controller;

import static com.common.util.GsonUtil.json2Pojo;
import static com.common.util.GsonUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courselist.service.impl.CourseListServiceImpl;
import com.courselist.service.intf.CourseListServiceIntf;
import com.courselist.vo.CourseList;

@WebServlet("/courseList/addCourseList")
public class AddCourseListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseListServiceIntf service = new CourseListServiceImpl();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CourseList cList = json2Pojo(request, CourseList.class);
        
		cList = service.add(cList);
            
        writePojo2Json(response, cList);
	}

}
