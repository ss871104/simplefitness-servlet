package com.gym.controller;

import static com.common.util.GsonUtil.json2Pojo;
import static com.common.util.GsonUtil.writePojo2Json;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gym.service.impl.GymServiceImpl;
import com.gym.service.intf.GymServiceIntf;
import com.gym.vo.Gym;

@WebServlet("/gym/edit")
public class EditGymServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GymServiceIntf service = new GymServiceImpl();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Gym gym = json2Pojo(request, Gym.class);
		
		gym = service.edit(gym);
        
        writePojo2Json(response, gym);
	}

}
