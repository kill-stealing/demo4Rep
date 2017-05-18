package com.ibm.servlet3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetInfoServlet3_1
 */
public class GetInfoServlet3_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		//得到URL
		String url=request.getRequestURL().toString();
		System.out.println("url="+url);
		
		//得到uri
		String uri=request.getRequestURI().toString();
		System.out.println("uri="+uri);
		
		//QueryString 
		String queryString=request.getQueryString();
		System.out.println("queryString="+queryString);
		
		//getRemoteAddr 函数可以获取请求方的ip
		String addr=request.getRemoteAddr();
		if(addr.equals("192.168.2.1")){
			response.sendRedirect("ErrorServlet");
		}
		System.out.println("addr="+addr);
		
		//getRemoteHost 得到请求方的主机名，如果该客户机没有在dns上注册，则返回ip地址，
		//如果注册过，则返回机器名
		String host=request.getRemoteHost();
		System.out.println("host="+host);
		
		//获取客户机使用的端口
		int port=request.getRemotePort();
		int serverPort=request.getLocalPort();
		System.out.println("port=" +port+" serverPort="+serverPort);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
