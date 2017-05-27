package com.hsp.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		Cookie[] cookies=request.getCookies();
		String userName="";
		String passWord="";
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("userName")){
					userName=cookie.getValue();
				}
				else if(cookie.getName().equals("passWord")){
					passWord=cookie.getValue();
				}
			}
		}
		PrintWriter out=response.getWriter();
		out.println("<img src='images/1.png' /><hr >"); 
		out.println("<h1>用户登录1</h1>");
		out.println("<form action='LoginClServlet' method='post' >");
		if(!"".equals(userName)&&!"".equals(passWord)){
			out.println("用户名:<input type='text' name='userName' value='"+URLDecoder.decode(userName,"utf-8")+"'/><br />");
			out.println("密码:<input type='password' name='passWord' value='"+passWord+"'/><br />");
			out.println("<input type='checkbox' name='ifSaveUser' checked/>是否保存用户名密码");
			out.println("<input type='checkbox' name='ifNoSaveUser'/>是否不保存用户名密码");
		}else{
			out.println("用户名:<input type='text' name='userName' /><br />");
			out.println("密码:<input type='password' name='passWord' /><br />");
			out.println("<input type='checkbox' name='ifSaveUser'/>是否保存用户名密码");
			out.println("<input type='checkbox' name='ifNoSaveUser'/>是否不保存用户名密码");
		}
		out.println("<input type='submit' value='登录' />");
		
		String error=(String) request.getAttribute("error");
		if(null!=error){
			out.println(error);
		}
		out.println("</form>");
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
