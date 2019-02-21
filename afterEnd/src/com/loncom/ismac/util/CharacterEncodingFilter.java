package com.loncom.ismac.util;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * Project: lonweb Version:1.0 Package: com.lon.filter File:
 * CharacterEncodingFilter.java
 * 
 * Functional Description: 编码过滤器 Notes
 * 
 * Revision History create by leijun, 2013-12-19 下午02:38:25
 * 
 * lon, Inc. Copyright (C): 2013
 */
public class CharacterEncodingFilter implements Filter {
	private FilterConfig filterConfig = null;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// System.out.println("编码过滤器");
		if (request != null)
			request
					.setCharacterEncoding(filterConfig
							.getInitParameter("encoding"));
		if (chain != null && request != null && response != null)
			chain.doFilter(request, response);
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		this.filterConfig = arg0;
		// System.out.println("编码过滤器启动");
	}
}
