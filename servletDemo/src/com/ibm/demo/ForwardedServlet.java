package com.ibm.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation classs ForwardedServlet
 */
public class ForwardedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		String basePath=request.getScheme()+""+request.getServerName()+":"+request.getServerPort()+request.getRequestURI();
		out.println("<base href=\""+basePath+"\">");
		
		out.println("<a href='ForwardedServlet'>访问自己</a><br>");
		out.println("URI:"+request.getRequestURI()+"<br>");
		out.println("QueryString:"+request.getQueryString()+"<br>");
		out.println("URL:"+request.getRequestURL()+"<br>");
		String p1=request.getParameter("p1");
		
		String chP1=null;
		if(p1!=null){
			chP1=new String(p1.getBytes("iso-8859-1"),"utf-8");
		}
		
		out.println("parameter p1:"+chP1+"<br>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
