package com.emp.service.impl;

import java.util.List;

import com.common.adapter.Base64Adapter;
import com.emp.dao.impl.EmployeeDaoImpl;
import com.emp.dao.intf.EmployeeDaoIntf;
import com.emp.service.intf.EmployeeServiceIntf;
import com.emp.vo.Employee;

public class EmployeeServiceImpl implements EmployeeServiceIntf{
	private EmployeeDaoIntf dao;
	private Base64Adapter base64;
	
	public EmployeeServiceImpl() {
		dao = new EmployeeDaoImpl();
		base64 = new Base64Adapter();
	}

	@Override
	public Employee login(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee addEmp(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee editEmp(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee editMember(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findById(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAll(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}
	



}
