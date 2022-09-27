package com.idvproduct.dao.intf;

import java.util.List;
import java.util.Set;

import com.common.dao.CommonDao;
import com.idvproduct.vo.IdvProduct;
import com.product.vo.Product;


public interface IdvProductDaoIntf extends CommonDao<IdvProduct, Integer> {
	
	public List<IdvProduct> selectByGym(Integer gymId);
	public List<Product> getProdInfoByProdId(Integer prodId);
}
