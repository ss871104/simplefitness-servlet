package com.gym.controller;

import static com.common.util.GsonUtil.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gym.service.impl.GymServiceImpl;
import com.gym.service.intf.GymServiceIntf;
import com.gym.vo.Gym;

@WebServlet("/gym/selectById")
public class GetOneGymServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GymServiceIntf SERVICE = new GymServiceImpl();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Gym gym = json2Pojo(request, Gym.class);
        
        gym = SERVICE.findById(gym);
            
        writePojo2Json(response, gym);
	}
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
	}
	
	private void setHeaders(HttpServletResponse response) {

		response.setContentType("application/json;charset=UTF-8"); // 重要
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");

		response.addHeader("Access-Control-Allow-Origin", "*"); // 重要
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", "*");
		response.addHeader("Access-Control-Max-Age", "86400");
	}

}
