package com.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class FirstFilter implements Filter{

	private FilterConfig filterconfig=null;
	private String paramValue=null;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.filterconfig=null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		/* 下面这段代码在命令行窗口中打印出所有的请求头信息，以便确认Filter是否起作用和帮助分析问题*/
		System.out.println("-----------------begin headers----------");
		Enumeration headerNames=((HttpServletRequest)request).getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String name = (String) headerNames.nextElement();
			System.out.println(name+":"+request.getParameter(name));
		}
		System.out.println("-----------------end headers---------");
		
		String host=request.getRemoteHost();
		out.println("host:"+host+"<br>");
		
		chain.doFilter(request, response);
		
		String realPath=request.getRealPath("/");
		out.println("realPath:"+realPath+"<br>");
		out.println("InitParameter:"+paramValue+"<br>");
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterconfig=filterconfig;
		paramValue=filterconfig.getInitParameter("encoding");
	}
	
}
