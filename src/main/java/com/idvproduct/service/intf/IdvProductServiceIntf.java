package com.idvproduct.service.intf;

import java.util.List;
import java.util.Set;

import com.idvproduct.vo.IdvProduct;
import com.product.vo.Product;

public interface IdvProductServiceIntf {
	
	public List<IdvProduct> selectByGym(Integer gymId);
	public IdvProduct selectCount(Integer gymId, Integer prodId);
}