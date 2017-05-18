package com.ibm.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.utils.MyTools;

/**
 * Servlet implementation class GetInfoServlet1
 */
public class GetInfoServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.setContentType("text/html");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
//		request.setCharacterEncoding("utf-8"); post 请求
//		String userName=request.getParameter("userName");
		String userName=request.getParameter("userName");
//		String userName=new String(request.getParameter("userName").getBytes("iso-8859-1"),"utf-8");
		userName=MyTools.getNewString(userName);
		System.out.println("userName "+userName);
		String str="Wel?yy="+request.getParameter("userName")+"&pwd=aaa";
		System.out.println(str);
		response.sendRedirect(str);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
