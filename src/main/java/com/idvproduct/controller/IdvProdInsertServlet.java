package com.idvproduct.controller;

import static com.common.util.GsonUtil.json2Pojo;
import static com.common.util.GsonUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.idvproduct.service.impl.IdvProductServiceImpl;
import com.idvproduct.service.intf.IdvProductServiceIntf;
import com.idvproduct.vo.IdvProduct;
import com.product.vo.Product;

@WebServlet("/idvProduct/idvProdInsert")
public class IdvProdInsertServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IdvProductServiceIntf service = new IdvProductServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws SecurityException, IOException{
				
		IdvProduct idvproduct = json2Pojo(request, IdvProduct.class);
		
		idvproduct = service.idvProdInsert(idvproduct);
		
		writePojo2Json(response, idvproduct);
	}	
	
}
