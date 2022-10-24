package com.idvproduct.controller;

import static com.common.util.Constants.GSON;

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

import com.idvproduct.service.impl.IdvProductServiceImpl;
import com.idvproduct.service.intf.IdvProductServiceIntf;
import com.idvproduct.vo.IdvProduct;
import com.product.service.impl.ProductServiceImpl;
import com.product.service.intf.ProductServiceIntf;
import com.product.vo.Product;

@WebServlet("/product/selectByGym")
public class SelectByGymServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IdvProductServiceIntf IDV_SERVICE =new IdvProductServiceImpl();
	private ProductServiceIntf PRODUCT_SERVICE =new ProductServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		
        BufferedReader br = request.getReader();
        String json = br.readLine();
        IdvProduct idvProduct = GSON.fromJson(json, IdvProduct.class);
        
        List<IdvProduct> idvProducts = IDV_SERVICE.selectByGym(idvProduct.getGymId());
        
        List<Product> products = new ArrayList<Product>();
      
        
        for (int i = 0; i < idvProducts.size(); i++) {
        	products.add(PRODUCT_SERVICE.selectById(idvProducts.get(i).getProdId()));
        }
        
        for (int i = 0; i < idvProducts.size(); i++) {
        	products.get(i).setCount(idvProducts.get(i).getCount());
        }
        
        PrintWriter pw = response.getWriter();
        pw.print(GSON.toJson(products));
	}
		
}
