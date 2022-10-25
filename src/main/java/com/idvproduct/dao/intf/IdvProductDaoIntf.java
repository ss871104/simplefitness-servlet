package com.idvproduct.dao.intf;

import java.util.List;
import java.util.Set;

import com.common.dao.CommonDao;
import com.idvproduct.vo.IdvProduct;
import com.order.vo.Order;
import com.product.vo.Product;


public interface IdvProductDaoIntf extends CommonDao<IdvProduct, Integer> {
	
	public List<IdvProduct> selectByGym(Integer gymId);
//	public List<IdvProduct> selectByGym2(Integer gymId, Integer prodId);
	public IdvProduct selectCount(Integer prodId);
	public boolean updateStatus(String status, Integer id);
	public boolean updateGym(Integer gymId, Integer idvId);
	public boolean editProdGym(IdvProduct idvprod);
	public List<IdvProduct> selectGymGetProd(Integer prodId,Integer gymId);
	
}
