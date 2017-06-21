package com.ibm.demo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestCountServlet
 */
public class TestCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

//		response.setHeader(arg0, arg1);
		
		ServletContext sc=this.getServletContext();
		
		BufferedInputStream bis=new BufferedInputStream(sc.getResourceAsStream("images/1.png"));
		OutputStream os=response.getOutputStream();
		int len=0;
		byte[] flush=new byte[1024];
		while(-1!=(len=bis.read(flush))){
			os.write(flush,0,len);
		}
		os.flush();
		os.close();
		bis.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
