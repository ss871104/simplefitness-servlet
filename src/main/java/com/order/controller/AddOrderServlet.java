package com.order.controller;

import static com.common.util.GsonUtil.json2Pojo;
import static com.common.util.GsonUtil.writePojo2Json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.orderdetail.vo.OrderDetail;

@WebServlet("/order/addOrder")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderServiceIntf SERVICE = new OrderServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final HttpSession session = request.getSession();

		final Integer memId = ((Member) session.getAttribute("member")).getMemId();
		final String memName = ((Member) session.getAttribute("member")).getMemName();
		final String memEmail = ((Member) session.getAttribute("member")).getMemEmail();

		Order order = json2Pojo(request, Order.class);
		order.setMemId(memId);	
		order.setMemName(memName);
		order.setMemEmail(memEmail);
		
		order = SERVICE.addOrder(order,order.getOrderList().get(0).getProdId());
		
		order.setMemName(memName);
	
		writePojo2Json(response, order);

	}
}
