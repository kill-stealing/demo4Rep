package com.ibm.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreateSessionServlet
 */
public class CreateSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		session.setAttribute("uname", "宋江");
		String sessionId=session.getId();
		Cookie cookie=new Cookie("JSESSIONID", sessionId);
		cookie.setMaxAge(3600*24*7);
		response.addCookie(cookie);
		out.println("session 创建成功");
//		session.setMaxInactiveInterval(20);
		
//		System.out.println(" CreateSessionServlet session.getId() "+session.getId());
//		session.invalidate();
		//
//		session.setMaxInactiveInterval(arg0);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
