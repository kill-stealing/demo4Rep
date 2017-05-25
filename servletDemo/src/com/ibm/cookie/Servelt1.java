package com.ibm.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servelt1
 */
public class Servelt1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		Cookie[] cookies=request.getCookies();
		boolean ifFirst=false;//假设没有lastTime cookie
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				String name=cookie.getName();
				if("lastTime".equals(name)){
					out.println("您上次登录的时间是 "+cookie.getValue());
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String lastTime=sf.format(new Date());
					Cookie cookie1=new Cookie("lastTime", lastTime);
					cookie1.setMaxAge(3600*24*7);
					response.addCookie(cookie1);
					ifFirst=true;
					break;
				}
			}
		}
		if(!ifFirst){
			out.println("您是第一次登录。。。");
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String lastTime=sf.format(new Date());
			Cookie cookie=new Cookie("lastTime", lastTime);
			cookie.setMaxAge(3600*24*7);
			response.addCookie(cookie);
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
