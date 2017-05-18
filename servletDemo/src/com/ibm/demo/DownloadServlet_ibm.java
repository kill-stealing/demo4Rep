package com.ibm.demo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet_ibm
 */
public class DownloadServlet_ibm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String fileName=URLEncoder.encode("传奇.mp3","utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+fileName); 
		OutputStream out=response.getOutputStream();
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream("C:/传奇1.mp3"));
		int len=0;
		byte[] flush=new byte[1024]; 
		while(-1!=(len=bis.read(flush))){
			out.write(flush, 0, len);
		}
		out.flush();
		bis.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
