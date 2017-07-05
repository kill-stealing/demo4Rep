package com.ibm.checkcode;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class LoginCServlet
 */
public class LoginCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		
		String userName=request.getParameter("userName");
		String passWord=request.getParameter("passWord");
		String checkCodeInput=request.getParameter("checkCode");
		String checkcode=(String)request.getSession().getAttribute("checkCode");
		if(checkCodeInput!=null&&!"".equals(checkCodeInput)&&checkCodeInput.equals(checkcode)){
			out.println("登录成功");
		}else{
			out.println("验证码错误 ，请重新登录");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
