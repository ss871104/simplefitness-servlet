package com.idvproduct.controller;

import java.io.BufferedReader;
import static com.common.util.Constants.GSON;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.idvproduct.service.impl.IdvProductServiceImpl;
import com.idvproduct.service.intf.IdvProductServiceIntf;
import com.idvproduct.vo.IdvProduct;

@WebServlet("/member/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IdvProductServiceIntf IDV_SERVICE =new IdvProductServiceImpl();
	Gson gson = new Gson(); 
	 
	Type idvProductListType = new TypeToken<ArrayList<IdvProduct>>(){}.getType();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObject respObject = new JsonObject();
		BufferedReader br = request.getReader();
		String json = br.readLine();
		ArrayList<IdvProduct> idvproducts = gson.fromJson(json, idvProductListType); 

		for (int i = 0; i < idvproducts.size(); i++) {
			IdvProduct count =IDV_SERVICE.selectCount(idvproducts.get(i).getProdId());
			Integer inCart = idvproducts.get(i).getInCart();
			if(count != null) {
				if(inCart <= count.getCount()) {
					respObject.addProperty("msg", "success");
				}
			}else {
				respObject.addProperty("msg", "failed");
			}	
		}

		PrintWriter pw = response.getWriter();
        pw.print(GSON.toJson(respObject));

	}

}
