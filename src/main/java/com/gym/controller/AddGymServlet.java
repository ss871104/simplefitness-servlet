package com.gym.controller;

import static com.common.util.Constants.GSON;
import static com.common.util.GsonUtil.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gym.service.impl.GymServiceImpl;
import com.gym.service.intf.GymServiceIntf;
import com.gym.vo.Gym;

@WebServlet("/gym/addGym")
public class AddGymServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GymServiceIntf SERVICE = new GymServiceImpl();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Gym gym = json2Pojo(request, Gym.class);
        
        gym = SERVICE.add(gym);
            
        writePojo2Json(response, gym);
	}

}
