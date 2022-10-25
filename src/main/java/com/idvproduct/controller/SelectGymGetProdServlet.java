package com.idvproduct.controller;

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

import com.idvproduct.service.impl.IdvProductServiceImpl;
import com.idvproduct.service.intf.IdvProductServiceIntf;
import com.idvproduct.vo.IdvProduct;

@WebServlet("/idvProduct/SelectGymGetProd")
public class SelectGymGetProdServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IdvProductServiceIntf service = new IdvProductServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		setHeaders(response);
		
		IdvProduct idvProduct = json2Pojo(request, IdvProduct.class);
		
		List<IdvProduct> list = new ArrayList<IdvProduct>();
		
		list = service.selectGymGetProd(idvProduct.getProdId(),idvProduct.getGymId());
		
		writePojo2Json(response, list);
		
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