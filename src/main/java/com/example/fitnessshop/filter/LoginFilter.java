package com.example.fitnessshop.filter;


import java.io.IOException;

import javax.servlet.*;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
			chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
