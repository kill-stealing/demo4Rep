package com.newdemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class AuthenticateServlet
 */
public class AuthenticateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		System.out.println("-----------------begin");
		Enumeration headNames=request.getHeaderNames();
		while (headNames.hasMoreElements()) {
			String name = (String) headNames.nextElement();
			System.out.println(name+": "+request.getHeader(name));
		}
		System.out.println("-----------------end");
		String encodedAuth=request.getHeader("Authorization");
		
		if(encodedAuth==null||!encodedAuth.toUpperCase().startsWith("BASIC")){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setHeader("WWW-Authenticate", "BASIC realm=\"it315\"");
			out.println("没有传递身份");
			return;
		}
		
		BASE64Decoder decoder=new BASE64Decoder();
		byte[] decodeBytes=decoder.decodeBuffer(encodedAuth.substring(6));
		
		String decodeInfo=new String(decodeBytes);
		int idx=decodeInfo.indexOf(":");
		if(idx<0){
			out.println("信息格式不完整");
			return;
		}
		String user=decodeInfo.substring(0,idx);
		String pwd=decodeInfo.substring(idx+1);
		if("123".equals(user)&&"456".equals(pwd)){
			out.println("这就是你要看的信息");
		}else{
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setHeader("WWW-Authenticate", "BASIC realm=\"it315\"");
			out.println("你无权访问此信息");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
