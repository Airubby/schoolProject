package com.loncom.ismac.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/** 
 * Project: lonweb
 * Version:1.0
 * Package: com.lon.filter
 * File: NoCacheFilter.java

 * Functional Description: 
 *	   
 * Notes
 *	   
 * Revision History
 *	create by leijun, 2013-12-30 下午02:37:45 
 * 
 * lon, Inc. Copyright (C): 2013 
 */
public class NoCacheFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//		System.out.println("缓存过滤器");
		//页面不缓存
		((HttpServletResponse) response).setHeader("Cache-Control", "no-cache");
		((HttpServletResponse) response).setHeader("Pragma", "no-cache");
		((HttpServletResponse) response).setDateHeader("Expires", -1);
		chain.doFilter(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
