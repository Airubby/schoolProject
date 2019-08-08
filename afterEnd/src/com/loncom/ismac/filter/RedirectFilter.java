package com.loncom.ismac.filter;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@SuppressWarnings("unused")

public class RedirectFilter implements Filter{

	private FilterConfig filterConfig = null;

	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// System.out.println("编码过滤器");
		if (arg0 != null)
			arg0.setCharacterEncoding(filterConfig
							.getInitParameter("encoding"));
		if (arg2 != null && arg0 != null && arg1 != null)
			arg2.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		this.filterConfig = arg0;
		// System.out.println("编码过滤器启动");
	}


}
