package com.order.controller;

import static com.common.util.GsonUtil.json2Pojo;
import static com.common.util.GsonUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.vo.Member;
import com.order.service.impl.OrderServiceImpl;
import com.order.service.intf.OrderServiceIntf;
import com.order.vo.Order;

@WebServlet("/order/getMemOrder")
public class GetMemOrderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private OrderServiceIntf ORDER_SERVICE =new OrderServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		final Integer memId = ((Member) session.getAttribute("member")).getMemId();
		
		writePojo2Json(response, ORDER_SERVICE.SelectByMem(memId));
	}
	


	
}
