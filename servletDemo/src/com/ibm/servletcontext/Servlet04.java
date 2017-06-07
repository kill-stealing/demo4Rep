package com.ibm.servletcontext;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet04
 */
public class Servlet04 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		//请求转发和重定向的区别
		//1.转发发生在服务器端，浏览器地址不变，重定向是发生在浏览器端，地址会改变
		//2.如果request.setAttribute("","");希望下一个页面可以使用  那么就得用 转发，转发共享了一个request对象
		//3.如果session.setAttribute("","");希望下一个页面可以使用 两种方法都可以，但第二种方法比较好
		//4.如果我们希望跳转到web应用以外的一个url，应使用sendRedirect
		this.getServletContext().getRequestDispatcher("/url");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
