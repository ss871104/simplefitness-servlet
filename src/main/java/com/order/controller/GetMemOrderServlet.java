package com.order.controller;

import static com.common.util.Constants.GSON;
import static com.common.util.GsonUtil.*;
import static com.common.util.GsonUtil.writePojo2Json;
import static com.common.util.GsonUtil.json2Pojo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mem.vo.Member;
import com.order.service.impl.OrderServiceImpl;
import com.order.service.intf.OrderServiceIntf;
import com.order.vo.Order;

@WebServlet("/order/getMemOrder")
public class GetMemOrderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private OrderServiceIntf ORDER_SERVICE =new OrderServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//        BufferedReader br = request.getReader();
//        String json = br.readLine();
//        Order order = GSON.fromJson(json, Order.class);

		Order order = json2Pojo(request, Order.class);

//		List<Order> orders = ORDER_SERVICE.SelectByMem(order.getMemId());
//		PrintWriter pw = response.getWriter();
//        pw.print(GSON.toJson(orders));
        
		writePojo2Json(response, ORDER_SERVICE.SelectByMem(order.getMemId()));

		 
	}

	
}
