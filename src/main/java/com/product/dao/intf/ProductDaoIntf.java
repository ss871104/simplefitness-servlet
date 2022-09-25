package com.product.dao.intf;

import java.util.List;

import com.common.dao.CommonDao;
import com.product.vo.Product;

public interface ProductDaoIntf extends CommonDao<Product, Integer>{
	
	List<Product> selectListById(Integer prodId);
}
