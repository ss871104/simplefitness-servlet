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
	"/html/member_home.html",
	"/html/member_edit.html",
	"/html/change_pass.html"
})
public class MemberFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		final HttpSession session = req.getSession();
		if (session.getAttribute("member") != null) {
			chain.doFilter(req, res);
		} else {
			// TODO 登入頁
			req.getRequestDispatcher("login.html").forward(req, res);
		}
	}
}