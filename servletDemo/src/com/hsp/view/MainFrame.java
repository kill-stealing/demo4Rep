package com.hsp.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
		String userNameSe=(String)request.getSession().getAttribute("userName");
		
		ServletContext servletContext=this.getServletContext();
		int callCounts=(int)servletContext.getAttribute("callCounts");
		
		if(userNameSe==null||userNameSe.length()<=0){
			response.sendRedirect("SessionErrorServlet");
			return ;
		}
		
		String userName=request.getParameter("userName");
		String passWord=request.getParameter("passWord");
		String ifSaveUser=request.getParameter("ifSaveUser");
		String ifNoSaveUser=request.getParameter("ifNoSaveUser");
		if(null!=ifSaveUser&&"on".equals(ifSaveUser)){
			Cookie uCookie=new Cookie("userName", URLEncoder.encode(userName,"utf-8"));
			uCookie.setMaxAge(3600*24*7);
			response.addCookie(uCookie);
			
			Cookie uCookie1=new Cookie("passWord", passWord);
			uCookie1.setMaxAge(3600*24*7);
			response.addCookie(uCookie1);
		}else if(null!=ifNoSaveUser&&"on".equals(ifNoSaveUser)){
			Cookie[] cookies=request.getCookies();
			if(null!=cookies){
				for (Cookie cookie : cookies) {
					if(cookie.getName().equals("userName")){
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}else if(cookie.getName().equals("passWord")){
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
		}
		boolean ifHaveLastTime=false;
		String messgae="";
		Cookie[] cookies=request.getCookies();
		if(null!=cookies){
			for (Cookie cookie : cookies) {
				if("lastTime1".equals(cookie.getName())){
					messgae="上次登录时间是 "+cookie.getValue();
					
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String lastTime=sf.format(new Date());
					cookie.setValue(lastTime);
					cookie.setMaxAge(3600*24*7);
					response.addCookie(cookie);
					
					ifHaveLastTime=true;
					break;
				}
			}
		}
		
		if(!ifHaveLastTime){
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String lastTime=sf.format(new Date());
			Cookie c=new Cookie("lastTime1", lastTime);
			c.setMaxAge(3600*24*7);
			response.addCookie(c);
			messgae="您是第一次登录";
		}
		
		out.println("<img src='images/1.png' />欢迎:<b>"+request.getSession().getAttribute("userName")+"</b> 登录,"+messgae); 
		out.println("<a href='LoginServlet' >返回重新登录</a><hr >");
		out.println("网站访问次数"+callCounts+"<br />");
		out.println("<h3>请选择你要进行的操作</h3>");
		out.println("<a href='ManageUsers'>管理用户</a><br />");
		out.println("<a href=''>添加用户</a><br />");
		out.println("<a href=''>查找用户</a><br />");
		out.println("<a href='ManageProduct?type=all'>查看商品</a><br />");
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
