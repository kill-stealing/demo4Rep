package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("name", "hello1");
		String str=jsonObject.toString();
		str="data("+str+")";
		//response.setHeader("Access-Control-Allow-Origin", "*");
		out.println(str);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("name", "hello2");
		String str=jsonObject.toString();
		str="data("+str+")";
		//response.setHeader("Access-Control-Allow-Origin", "*");
		out.println(str);
	}
	
	public static void main(String[] args) {
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("name", "hello");
		String str=jsonObject.toString();
		System.out.println(str);
	}
}
