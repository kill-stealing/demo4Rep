package com.hsp.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsp.entity.User;

/**
 * Servlet implementation class MainFrame
 */
public class MainFrame extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
//		String userName=(String)request.getSession().getAttribute("username");
		String userName=request.getParameter("username");
		/*out.println("<style type=\"text/css\">"+
					"body{"+
					"	background-color: #C9D1FF;"+
					"}"+
					"</style>");*/
		out.println("<img src='images/1.png' />欢迎:<b>"+userName+"</b> 登录"); 
		out.println("<a href='LoginServlet' >返回重新登录</a><hr >");
		out.println("<h3>请选择你要进行的操作</h3>");
		out.println("<a href='ManageUsers'>管理用户</a><br />");
		out.println("<a href=''>添加用户</a><br />");
		out.println("<a href=''>查找用户</a><br />");
		out.println("<a href=''>退出系统</a><br />");
		out.println("<hr ><img src='images/2.png' />"); 	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
