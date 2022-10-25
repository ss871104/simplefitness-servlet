package com.product.service.intf;

import java.util.List;

import com.product.vo.Product;



public interface ProductServiceIntf {
//	第二種寫法
//	public Product selectById(Product product);
	
	public Product prodEdit(Product prod);
	
	public Product prodAddItem(Product prod);
	
	public Product selectById(Integer prodId);
	
	public List<Product> getAll();
}
