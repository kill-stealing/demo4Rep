package com.ibm.demo;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFirstServlet implements Servlet{

	//销毁该servlet,从内存中清除，该函数被调用一次
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	//得到servletConfig对象
	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	//该函数是得到servlet配置信息
	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	//该函数用于初始化servlet，就是把该servlet装载到内存中
	//该函数只会被调用一次
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		
	}

	//该函数是服务函数，我们的业务逻辑代码就是写在这里。
	//该函数每次都会被调用
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		arg1.getWriter().println("hello");
	}

}
