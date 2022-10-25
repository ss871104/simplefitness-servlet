package com.idvproduct.service.intf;

import java.util.List;
import java.util.Set;

import com.idvproduct.vo.IdvProduct;
import com.product.vo.Product;

public interface IdvProductServiceIntf {
	
	public IdvProduct idvProdCountEdit(IdvProduct prodCount);
	public IdvProduct idvProdStatusEdit(IdvProduct prodStatus);
	public List<IdvProduct> selectByGym(Integer gymId);
	public IdvProduct selectCount(Integer gymId, Integer prodId);
	public IdvProduct idvProdGymEdit(IdvProduct prodGym);
	public List<IdvProduct> selectGymGetProd(Integer prodId,Integer gymId);
	public IdvProduct editIdvProdGym(IdvProduct idvprodGym);
}