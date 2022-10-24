package com.order.controller;

import static com.common.util.GsonUtil.json2Pojo;
import static com.common.util.GsonUtil.writePojo2Json;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.service.impl.OrderServiceImpl;
import com.order.service.intf.OrderServiceIntf;
import com.order.vo.Order;

@WebServlet("/order/CancelOrder")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderServiceIntf SERVICE = new OrderServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order = json2Pojo(request, Order.class);
		writePojo2Json(response, SERVICE.CancelOrder(order));
	}

}
