package com.coursebooking.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.coursebooking.service.impl.CourseBookingServiceImpl;
import com.coursebooking.service.intf.CourseBookingServiceIntf;
import com.coursebooking.vo.CourseBooking;
import com.courlist.vo.CourList;

//這個地方是你路徑
@WebServlet("/coachBooking/GetCourseList")
public class GetCourseListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseBookingServiceIntf _courseBookingService = new CourseBookingServiceImpl();
	private Gson GSON = new GsonBuilder().create();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
        BufferedReader br = request.getReader();
        String json = br.readLine();
        
        //notice
        //命名不好 courseList=>courseType
        //CourseList應該為CourseList的清單
        //這種命名會模糊你的取直
        
        //Step.1
        //這邊接入DB_CourseList值
        //從JSon轉model後
        //利用傳入值取出對應課程清單
        //後面應用service使用多dao
        
        CourList courseType = GSON.fromJson(json, CourList.class);
        
        List<CourList> courseTypeList=_courseBookingService.getCourseList(courseType);

//		if (member.isSuccessful()) {
//			if (request.getSession(false) != null) {
//				request.changeSessionId();
//			}
//			final HttpSession session = request.getSession();
//			session.setAttribute("loggedin", true);
//			session.setAttribute("member", member);
//		}
		PrintWriter pw = response.getWriter();
        pw.print(GSON.toJson(courseTypeList));
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
