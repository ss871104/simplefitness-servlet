package com.idvproduct.dao.intf;

import java.util.List;
import java.util.Set;

import com.common.dao.CommonDao;
import com.idvproduct.vo.IdvProduct;
import com.order.vo.Order;
import com.product.vo.Product;


public interface IdvProductDaoIntf extends CommonDao<IdvProduct, Integer> {
	
	public List<IdvProduct> selectByGym(Integer gymId);
	public IdvProduct selectCount(Integer prodId);
	public boolean UpdateStatus(String status, Integer id);
	
}
