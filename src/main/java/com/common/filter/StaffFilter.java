package com.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {
		"/html/staff/*"
	})
	public class StaffFilter extends HttpFilter {

		private static final long serialVersionUID = 1L;

		@Override
		protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
			res.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
			res.addHeader("Pragma", "no-cache"); 
			res.addDateHeader ("Expires", 0);
			
			final HttpSession session = req.getSession();
			if (session.getAttribute("emp") != null) {
				chain.doFilter(req, res);
			} else {
				req.getRequestDispatcher("../guest/emp_login.html").forward(req, res);
			}
		}
	}
