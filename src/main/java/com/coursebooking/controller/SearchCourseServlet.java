package com.coursebooking.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.vo.Course;
import com.coursebooking.service.impl.CourseBookingServiceImpl;
import com.coursebooking.service.intf.CourseBookingServiceIntf;
import com.coursebooking.vo.CourseBooking;
import static com.common.util.Constants.GSON;

@WebServlet("/courseBooking/SearchCourseServlet")
public class SearchCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CourseBookingServiceIntf _courseBookingService = new CourseBookingServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		BufferedReader br = request.getReader();
		String json = br.readLine();

		// Step.1 接值
		CourseBooking courseBooking = GSON.fromJson(json, CourseBooking.class);

		// Step.2 執行SVC
		// 取得可預約課程
		List<Course> courseBookingResult = _courseBookingService.searchCourseByGymIdAndCourseListId(courseBooking);
		// 取得已預約課程
		List<CourseBooking> courseBookedResult = _courseBookingService.checkBookingCourseByMemberId(courseBooking);

		// 轉換出已預約課程編號清單
		List<Integer> bookedCourseIdList = courseBookedResult.stream().map(CourseBooking::getCourseId)
				.collect(Collectors.toList());
		// 將課程中有出現在編號清單中的課程過濾掉
		courseBookingResult = courseBookingResult.stream()
				.filter(item -> !bookedCourseIdList.contains(item.getCourseId())).toList();

		PrintWriter pw = response.getWriter();
		pw.print(GSON.toJson(courseBookingResult));
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
