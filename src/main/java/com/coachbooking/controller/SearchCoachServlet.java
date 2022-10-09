package com.coachbooking.controller;

import static com.common.util.Constants.GSON;

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

import com.coach.vo.Coach;
import com.coachbooking.service.impl.CoachBookingServiceImpl;
import com.coachbooking.service.intf.CoachBookingServiceIntf;
import com.coachbooking.vo.CoachBooking;

@WebServlet("/coachBooking/SearchCoachServlet")
public class SearchCoachServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CoachBookingServiceIntf _coachBookingService = new CoachBookingServiceImpl();

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
		CoachBooking coachBooking = GSON.fromJson(json, CoachBooking.class);

		// Step.2 執行SVC
		// 取得可預約課程
		List<Coach> coachBookingResult = _coachBookingService.searchCoachByGymIdAndEmpId(coachBooking);
		// 取得已預約課程
		List<CoachBooking> coachBookedResult = _coachBookingService.checkBookingCoachByMemberId(coachBooking);

		// 轉換出已預約課程編號清單
		List<Integer> bookedCoachIdList = coachBookedResult.stream().map(CoachBooking::getCoachId)
				.collect(Collectors.toList());
		// 將課程中有出現在編號清單中的課程過濾掉
		coachBookingResult = coachBookingResult.stream()
				.filter(item -> !bookedCoachIdList.contains(item.getCoaId())).toList();

		PrintWriter pw = response.getWriter();
		pw.print(GSON.toJson(coachBookingResult));
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
