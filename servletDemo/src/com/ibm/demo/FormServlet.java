package com.ibm.demo;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormServlet
 */
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Enumeration<String> temp=request.getParameterNames();
		while(temp.hasMoreElements()){
			String name=temp.nextElement();
			System.out.println(name+" = "+transform(request.getParameter(name)));
		}
		
		InputStream is=request.getInputStream();
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("C:/传奇_out.mp3"));
		int len=0;
		byte[] flush=new byte[1024];
		while(-1!=(len=is.read(flush))){
			bos.write(flush, 0, len);
		}
		bos.flush();
		bos.close();
	}
	
	public static String transform(String temp){
		try {
			return new String(temp.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
