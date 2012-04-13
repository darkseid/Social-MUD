package com.smud.web.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smud.model.User;
import com.smud.util.SecurityContext;

public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		HttpSession session = httpServletRequest.getSession();
		
		// TODO melhorar isso
		if (SecurityContext.userSignedIn()) {
			session.setAttribute("authenticated_user", SecurityContext.getCurrentUser());
			chain.doFilter(request, response);
		}
		
		
		User authenticatedUser = (User) session.getAttribute("authenticated_user");
		if (authenticatedUser == null) {
			httpServletResponse.sendRedirect("/login.do");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
