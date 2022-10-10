package com.idvproduct.service.impl;

import java.util.List;
import java.util.Set;

import com.idvproduct.dao.impl.IdvProductDaoImpl;
import com.idvproduct.dao.intf.IdvProductDaoIntf;
import com.idvproduct.service.intf.IdvProductServiceIntf;
import com.idvproduct.vo.IdvProduct;
import com.product.vo.Product;

public class IdvProductServiceImpl implements IdvProductServiceIntf{

	private IdvProductDaoIntf dao;

	public IdvProductServiceImpl() {
		dao = new IdvProductDaoImpl();
	}


	@Override
	public List<IdvProduct> selectByGym(Integer gymId) {
		return dao.selectByGym(gymId);
	}


	@Override
	public IdvProduct selectCount(Integer gymId, Integer prodId) {
		return dao.selectCount(gymId, prodId);
	}


	


	
	
}

