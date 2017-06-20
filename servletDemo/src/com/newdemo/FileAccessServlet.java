package com.newdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class FileAccessServlet
 */
public class FileAccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		// TODO Auto-generated method stub
//		FileInputStream fis=new FileInputStream("properties.properties");
		Properties p=ParseConfig.readConfigInfo();
		out.println("database="+p.getProperty("database"));
		out.println("username="+p.getProperty("username"));
		out.println("password="+p.getProperty("password"));
		
		ServletContext sc=this.getServletContext();
		URL url=sc.getResource("/WEB-INF/classes/properties.properties");
		
		InputStream is=sc.getResourceAsStream("/WEB-INF/classes/properties.properties");
		
		Properties pro=new Properties();
		pro.load(is);
		out.println("database="+pro.getProperty("database"));
		out.println("username="+pro.getProperty("username"));
		out.println("password="+pro.getProperty("password"));
		is.close();
		
//		fis.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
