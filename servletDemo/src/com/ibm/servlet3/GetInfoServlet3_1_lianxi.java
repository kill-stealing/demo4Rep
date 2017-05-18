package com.ibm.servlet3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.utils.MyTools;

/**
 * Servlet implementation class GetInfoServlet3_1
 */
public class GetInfoServlet3_1_lianxi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		StringBuilder sb=new StringBuilder();
		Enumeration<String> paramters=request.getParameterNames();
		response.setCharacterEncoding("utf-8");
		while(paramters.hasMoreElements()){
			String pName=paramters.nextElement();
			sb.append(pName+"="+MyTools.getNewString(request.getParameter(pName)));
			sb.append("<br />");
		}
		out.println(sb);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
