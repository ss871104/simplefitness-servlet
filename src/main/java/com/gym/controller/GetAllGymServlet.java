package com.gym.controller;

import static com.common.util.GsonUtil.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gym.service.impl.GymServiceImpl;
import com.gym.service.intf.GymServiceIntf;
import com.gym.vo.Gym;

@WebServlet("/gym/getAllGym")
public class GetAllGymServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GymServiceIntf service = new GymServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Gym> gym = service.findAll();
		
		writePojo2Json(response, gym);
	}

}
