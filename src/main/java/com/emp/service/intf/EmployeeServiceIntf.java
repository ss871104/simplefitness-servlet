package com.emp.service.intf;

import java.util.List;

import com.emp.vo.Employee;

public interface EmployeeServiceIntf {
	
	Employee login(Employee emp);
	
	Employee addEmp(Employee emp);
	
	Employee editEmp(Employee emp);
	
	Employee editMember(Employee emp);
	
	Employee findById(Employee emp);
	
	Employee passChange(Employee emp);
	
	List<Employee> findAll();

}
