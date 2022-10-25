package com.idvproduct.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.taglibs.standard.tag.common.xml.IfTag;

import com.idvproduct.dao.impl.IdvProductDaoImpl;
import com.idvproduct.dao.intf.IdvProductDaoIntf;
import com.idvproduct.service.intf.IdvProductServiceIntf;
import com.idvproduct.vo.IdvProduct;
import com.product.vo.Product;

public class IdvProductServiceImpl implements IdvProductServiceIntf {

	private IdvProductDaoIntf dao;

	public IdvProductServiceImpl() {
		dao = new IdvProductDaoImpl();
	}

	@Override
	public IdvProduct idvProdGymEdit(IdvProduct prodGym) {
		if (dao.updateGym(prodGym.getGymId(), prodGym.getIdvId()) == false) {
			prodGym.setMessage("更新失敗");
			prodGym.setSuccessful(false);
		}
		prodGym.setMessage("更新成功");
		prodGym.setSuccessful(true);
		return prodGym;
	}
	
	@Override
	public IdvProduct idvProdStatusEdit(IdvProduct prodStatus) {
		if (dao.updateStatus(prodStatus.getStatus(), prodStatus.getIdvId()) == false) {
			prodStatus.setMessage("更新失敗");
			prodStatus.setSuccessful(false);
		}
		prodStatus.setMessage("更新成功");
		prodStatus.setSuccessful(true);
		return prodStatus;
	}

	@Override
	public List<IdvProduct> selectByGym(Integer gymId) {
		return dao.selectByGym(gymId);
	}

	@Override
	public IdvProduct selectCount(Integer prodId) {
		return dao.selectCount(prodId);
	}

	@Override
	public IdvProduct idvProdCountEdit(IdvProduct prodCount) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<IdvProduct> selectGymGetProd(Integer prodId,Integer gymId) {
		return dao.selectGymGetProd(prodId,gymId);
	}
	
	@Override
	public IdvProduct editIdvProdGym(IdvProduct idvprodGym) {
		
		final String status = idvprodGym.getStatus();
		
		if("".equals(status)) {
			idvprodGym.setMessage("狀態未輸入");
			idvprodGym.setSuccessful(false);
			return idvprodGym;
		}
		if(idvprodGym.getGymId() == null) {
			idvprodGym.setMessage("健身房未輸入");
			idvprodGym.setSuccessful(false);
			return idvprodGym;
		}
		dao.editProdGym(idvprodGym);
		idvprodGym.setSuccessful(true);
		return idvprodGym;
	}
}
