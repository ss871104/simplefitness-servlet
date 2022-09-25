package com.emp.dao.impl;

import java.util.List;

import com.emp.dao.intf.EmployeeDaoIntf;
import com.emp.vo.Employee;

public class EmployeeDaoImpl implements EmployeeDaoIntf{

	@Override
	public boolean insert(Employee pojo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Employee pojo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee selectByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee selectByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee selectForLogin(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
