package com.loncom.ismac.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/** * @author  作者 E-mail: youtao
* @date 创建时间：2017年8月3日 下午5:52:18 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class LoadingFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest requests = (HttpServletRequest) request;  
        HttpServletResponse reponses = (HttpServletResponse) response;  
        System.out.println(requests.getHeader("User-Agent"));
        System.out.println("************************");
        String path=request.getServletContext().getContextPath();
// 	   String ip=request.getServerName();
// 	   int port=request.getServerPort();
//        if(requests.getHeader("User-Agent").indexOf("iPad")!=-1||requests.getHeader("User-Agent").indexOf("iPhone")!=-1||requests.getHeader("User-Agent").indexOf("Android")!=-1) {
//        	reponses.sendRedirect("http://"+ip+":"+port+""+path+"/mobile"); 
//        }else {
//        	System.out.println("http://"+ip+":"+port+""+path+"/index.html");
//        	reponses.sendRedirect("http://"+ip+":"+port+""+path+"/index.html"); 
//        }
        /*     System.out.println(requests.getSession().getId());
       if( requests.getRequestURI().indexOf("login.jsp") ==-1 && !requests.getRequestURI().equals("/ISmac")){  
           String userid=AppContext.getSID().get(requests.getSession().getId()) ;
           if(UtilTool.isNotNull(userid)){
        	   arg2.doFilter(requests, reponses); 
           }else{
        	   String path=request.getServletContext().getContextPath();
        	   String ip=request.getServerName();
        	   int port=request.getServerPort();
        	  
        	  // System.out.println(request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/"))；);
            	reponses.sendRedirect("http://"+ip+":"+port+""+path+"/login.jsp");   
            	
           }
          
        }else{
        	 arg2.doFilter(requests, reponses); 
        } */
       arg2.doFilter(requests, reponses); 
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		System.out.println("1111111111");
	}

}
