package com.hsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.hsp.dao.Dao;
import com.hsp.dao.DaoImpl;
import com.hsp.entity.User;

/**
 * Servlet implementation class LoginClServlet
 */
public class LoginClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String userName=request.getParameter("userName");
		String passWord=request.getParameter("passWord");
		String checkCodeInput=request.getParameter("checkCode");
		String checkcode=(String)request.getSession().getAttribute("checkCode");
		if(checkCodeInput!=null&&!"".equals(checkCodeInput)&&checkCodeInput.equals(checkcode)){
			System.out.println("userName "+userName);
			System.out.println("password "+passWord);
			User user=new User(userName, passWord);
			Dao dao=new DaoImpl();
			int ifExit=dao.ifExit(user);
			if(ifExit>0){
				ServletContext servletContext=this.getServletContext();
				Object object=servletContext.getAttribute("callCounts");
				int callCounts=0;
				if(object!=null){
					callCounts=(int)object;
				}
				callCounts++;
				servletContext.setAttribute("callCounts", callCounts);
				HttpSession session=request.getSession();
				session.setAttribute("userName", userName);
				request.getRequestDispatcher("MainFrame").forward(request, response);
//				response.sendRedirect("MainFrame");
			}else{
				request.setAttribute("error", "用户id或者密码有误");
				request.getRequestDispatcher("LoginServlet").forward(request, response);
			}
		}else{
			request.setAttribute("error", "验证码错误 ，请重新登录");
			request.getRequestDispatcher("LoginServlet").forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
