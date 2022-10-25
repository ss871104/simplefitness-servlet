package com.orderdetail.controller;

import static com.common.util.GsonUtil.json2Pojo;
import static com.common.util.GsonUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.orderdetail.service.impl.OrderDetailServiceImpl;
import com.orderdetail.service.intf.OrderDetailServiceIntf;
import com.orderdetail.vo.OrderDetail;

@WebServlet("/orderDetail/getOrderDetail")
public class GetOrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDetailServiceIntf SERVICE =new OrderDetailServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		setHeaders(response);
		
		OrderDetail orderDetail = json2Pojo(request, OrderDetail.class);

		writePojo2Json(response, SERVICE.SelectByOrderId(orderDetail.getOrderId()));

		 
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