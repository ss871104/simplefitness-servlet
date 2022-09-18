package com.ordetail.service.impl;

import java.util.List;

import com.ordetail.dao.impl.OrDetailDaoImpl;
import com.ordetail.dao.intf.OrDetailDaoIntf;
import com.ordetail.service.intf.OrDetailServiceIntf;
import com.ordetail.vo.OrDetail;

public class OrDetailServiceImpl implements OrDetailServiceIntf {

	private OrDetailDaoIntf dao;
	
	public OrDetailServiceImpl() {
		dao = new OrDetailDaoImpl();
	}
	
	@Override
	public List<OrDetail> findAll() {
		return dao.selectAll();
	}

}
