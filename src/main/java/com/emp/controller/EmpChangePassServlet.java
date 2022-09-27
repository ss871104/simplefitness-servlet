package com.emp.controller;

import static com.common.util.GsonUtil.json2Pojo;
import static com.common.util.GsonUtil.writePojo2Json;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.service.impl.EmployeeServiceImpl;
import com.emp.service.intf.EmployeeServiceIntf;
import com.emp.vo.Employee;

@WebServlet("/staff/changePass")
public class EmpChangePassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public EmployeeServiceIntf SERVICE = new EmployeeServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Employee emp = json2Pojo(request, Employee.class);
		
		emp = SERVICE.passChange(emp);
        
        writePojo2Json(response, emp);
	}

}
