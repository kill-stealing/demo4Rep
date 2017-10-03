package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class AngularHttp2
 */
public class AngularHttp2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		/*BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuffer sb=new StringBuffer("");
		String temp="";
		int length=0;
		byte[] flush=new byte[1024];
		while((temp=br.readLine())!=null){
			sb.append(temp);
		}
		br.close();
		String str=sb.toString();
		JSONParser parser=new JSONParser();
		JSONArray json=new JSONArray();
		try {
			Object obj=parser.parse(str);
			json=(JSONArray)obj;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(str);*/
		String name=request.getParameter("name");
		String title=request.getParameter("title");
		System.out.println(name+title);
		PrintWriter out = response.getWriter();
		out.println();
	}

}
