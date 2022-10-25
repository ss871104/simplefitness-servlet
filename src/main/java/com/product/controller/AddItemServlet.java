package com.product.controller;

import static com.common.util.GsonUtil.json2Pojo;
import static com.common.util.GsonUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.service.impl.ProductServiceImpl;
import com.product.service.intf.ProductServiceIntf;
import com.product.vo.Product;

@WebServlet("/product/addItem")
public class AddItemServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ProductServiceIntf service = new ProductServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException{
		
		setHeaders(response);
		
		Product product = json2Pojo(request, Product.class);

		product = service.prodAddItem(product);
		
		writePojo2Json(response, product);
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

