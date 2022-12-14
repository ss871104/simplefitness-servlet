package com.emp.controller;

import static com.common.util.GsonUtil.writePojo2Json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.service.impl.EmployeeServiceImpl;
import com.emp.service.intf.EmployeeServiceIntf;
import com.emp.vo.Employee;

@WebServlet("/staff/getAllEmp")
public class GetAllEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public EmployeeServiceIntf service = new EmployeeServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Employee> emp = service.findAll();
		
		writePojo2Json(response, emp);
	}


}
