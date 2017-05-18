package com.ibm.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyHttpServlet
 */
public class MyHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
//		String encodingString=this.getServletConfig().getInitParameter("encoding");
		String host=request.getHeader("Host");
		String refererString=request.getHeader("Referer");
		if(null==refererString||!refererString.startsWith("http://localhost:8080/servletDemo/")){
			response.sendRedirect("ErrorServlet");
			return ;
		}
		System.out.println("是否停止执行");
		PrintWriter out=response.getWriter();
//		out.println(encodingString);
		out.println(host);
		out.println("<br />");
		out.println(refererString);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
