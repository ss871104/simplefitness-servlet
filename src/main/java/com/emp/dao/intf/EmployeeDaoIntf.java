package com.emp.dao.intf;

import com.common.dao.CommonDaoHibernate;
import com.emp.vo.Employee;

public interface EmployeeDaoIntf extends CommonDaoHibernate<Employee, Integer>{
	
	public Employee selectByUsername(String username);
	
	public Employee selectByEmail(String email);
	
	public Employee selectForLogin(String username, String password);
	
	public boolean updatePassById(Employee emp);

}
