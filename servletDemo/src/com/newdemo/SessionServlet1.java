package com.newdemo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet1
 */
public class SessionServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Integer sessionCount=(Integer)session.getAttribute("count");
		int count=0;
		if(sessionCount!=null){
			count=sessionCount.intValue();
		}
		out.println("当前会话中发生了"+(++count)+"次访问<br>");
		session.setAttribute("count", count);
		
		count=0;
		ServletContext application=getServletContext();
		Integer applicationCount=(Integer) application.getAttribute("count");
		if(applicationCount!=null){
			count=applicationCount.intValue();
		}
		out.println("web应用程序中发生了"+(++count)+"次访问<br>");
		application.setAttribute("count", new Integer(count));
		
		out.println("<a href='SessionServlet2'>访问SessionServlet2</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
