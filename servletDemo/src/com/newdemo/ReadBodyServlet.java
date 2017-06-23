package com.newdemo;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReadBodyServlet
 */
public class ReadBodyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method 
		request.setAttribute("", "");
		ServletInputStream sis=request.getInputStream();
		ServletContext sc=getServletContext();
		System.out.println(sc.getRealPath("/"));
		String path=this.getServletContext().getRealPath("/body.out");
		
		FileOutputStream fos=new FileOutputStream(path);
		int len=0;
		byte[] flush=new byte[1024];
		while (-1!=(len=sis.read(flush))) {
			fos.write(flush, 0, len);
		}
		fos.flush();
		fos.close();
		sis.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
