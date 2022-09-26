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
		final String username = emp.getUsername();
		final String password = emp.getPassword();
		
		try {
			if ("".equals(username)) {
				emp.setMessage("帳號未輸入");
				emp.setSuccessful(false);
				return emp;
			}
			if ("".equals(password)) {
				emp.setMessage("密碼未輸入");
				emp.setSuccessful(false);
				return emp;
			}
			if (dao.selectForLogin(username, password) == null) {
				emp.setSuccessful(false);
				emp.setMessage("帳號或密碼錯誤！");
				return emp;
			}
			
			// 登入後有完整資料
			emp = dao.selectForLogin(username, password);
			
			// 圖片轉base64
			if (emp.getPic() != null) {
				emp.setPicBase64(base64.Encoder(emp.getPic()));
				emp.setPic(null);
			}
					
			emp.setSuccessful(true);
			return emp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Employee addEmp(Employee emp) {
		try {
			if (emp.getEmpName().isEmpty()) {
				emp.setMessage("員工名稱未輸入");
				emp.setSuccessful(false);
				return emp;
			}
			if (emp.getUsername().isEmpty()) {
				emp.setMessage("帳號未輸入");
				emp.setSuccessful(false);
				return emp;
			}
			if (emp.getEmail().isEmpty()) {
				emp.setMessage("信箱未輸入");
				emp.setSuccessful(false);
				return emp;
			}
			if (!emp.getEmail().contains("@")) {
				emp.setMessage("信箱未符合格式");
				emp.setSuccessful(false);
				return emp;
			}
			if (emp.getJob() == null) {
				emp.setMessage("職位未選取");
				emp.setSuccessful(false);
				return emp;
			}
			if (emp.getGender().isEmpty()) {
				emp.setMessage("性別未選取");
				emp.setSuccessful(false);
				return emp;
			}
			if (emp.getBirth() == null) {
				emp.setMessage("生日未選取");
				emp.setSuccessful(false);
				return emp;
			}
			if (emp.getStatus().isEmpty()) {
				emp.setMessage("狀態未選取");
				emp.setSuccessful(false);
				return emp;
			}
			if (dao.insert(emp) == false) {
				emp.setMessage("新增發生錯誤!");
				emp.setSuccessful(false);
				return emp;
			}
			emp.setMessage("新增成功");
			emp.setSuccessful(true);
			return emp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
	public List<Employee> findAll() {
		try {
			return (List<Employee>) dao.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	



}
