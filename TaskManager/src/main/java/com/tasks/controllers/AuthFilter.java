package com.tasks.controllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/pages/*")
public class AuthFilter implements Filter {

	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {    
        HttpServletRequest req = (HttpServletRequest) request;
    	String loginURL = req.getContextPath() + "/pages/login.jsf";
    	System.out.println(loginURL);
    	System.out.println(req.getRequestURI());

        UserController auth = (UserController) req.getSession().getAttribute("userc");

        if ((auth != null && auth.isIslogged())| req.getRequestURI().equals(loginURL)) {
            // User is logged in, so just continue request.
            chain.doFilter(request, response);
        } else {
            // User is not logged in, so redirect to index.
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect(req.getContextPath() + "/pages/login.jsf");
        }
    }
@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}



