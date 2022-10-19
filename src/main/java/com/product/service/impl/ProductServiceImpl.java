package com.product.service.impl;


import java.util.List;

import com.common.adapter.Base64Adapter;
import com.product.dao.impl.ProductDaoImpl;
import com.product.dao.intf.ProductDaoIntf;
import com.product.service.intf.ProductServiceIntf;
import com.product.vo.Product;



public class ProductServiceImpl implements ProductServiceIntf{
	
	private ProductDaoIntf dao;
	private Base64Adapter base64;

	public ProductServiceImpl() {
		dao = new ProductDaoImpl();
		base64 = new Base64Adapter();
	}
	
	@Override
	public Product selectById(Integer prodId) {
		Product prod = dao.selectById(prodId);
		prod.setProdPicBase64(base64.Encoder(prod.getProdPic()));
		return prod;
	}

	@Override
	public List<Product> getAll() {
		return dao.selectAll();
	}

}
