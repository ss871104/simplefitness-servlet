package com.product.service.impl;

import java.util.List;

import com.product.dao.impl.ProductDaoImpl;
import com.product.dao.intf.ProductDaoIntf;
import com.product.service.intf.ProductServiceIntf;
import com.product.vo.Product;



public class ProductServiceImpl implements ProductServiceIntf{
	
	private ProductDaoIntf dao;

	public ProductServiceImpl() {
		dao = new ProductDaoImpl();
	}
	
	@Override
	public Product selectById(Integer prodId) {
		return dao.selectById(prodId);
	}

}
