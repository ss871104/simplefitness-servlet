package com.coachbooking.controller;

import static com.common.util.Constants.GSON;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coachbooking.service.impl.CoachBookingServiceImpl;
import com.coachbooking.service.intf.CoachBookingServiceIntf;
import com.emp.vo.Employee;

@WebServlet("/coachBooking/SearchCoachByJobServlet")
public class SearchCoachByJobServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private CoachBookingServiceIntf _coachBookingService = new CoachBookingServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
        
        //Step.1 執行SVC
        List<Employee> coachBookingResult=_coachBookingService.searchCoachByJob();


		PrintWriter pw = response.getWriter();
        pw.print(GSON.toJson(coachBookingResult));
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
