package com.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrossOriginFilter extends HttpFilter {

private static final long serialVersionUID = 1L;
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		res.setContentType("application/json;charset=UTF-8");
		res.setHeader("Cache-control", "no-cache, no-store");
	    res.setHeader("Pragma", "no-cache");
	    res.setHeader("Expires", "-1");
		
		res.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		res.setHeader("Access-Control-Max-Age", "3600");
		res.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, Accept");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		
		chain.doFilter(req, res);
        
	}
}


// 實驗階段 勿動