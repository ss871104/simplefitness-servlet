package com.coursebooking.controller;

import static com.common.util.Constants.GSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coursebooking.service.impl.CourseBookingServiceImpl;
import com.coursebooking.service.intf.CourseBookingServiceIntf;
import com.coursebooking.vo.CourseBooking;

@WebServlet("/courseBooking/BookCourseServlet")
public class BookCourseServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private CourseBookingServiceIntf _courseBookingService = new CourseBookingServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
        BufferedReader br = request.getReader();
        String json = br.readLine();

        //Step.1 接值
        CourseBooking courseBooking = GSON.fromJson(json, CourseBooking.class);
        
        //Step.2 執行SVC
        Boolean courseBookingResult=_courseBookingService.bookCourse(courseBooking);


		PrintWriter pw = response.getWriter();
        pw.print(GSON.toJson(courseBookingResult));
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
