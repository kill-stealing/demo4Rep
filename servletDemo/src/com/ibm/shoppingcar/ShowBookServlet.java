package com.ibm.shoppingcar;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsp.dao.Dao;
import com.hsp.dao.DaoImpl;
import com.hsp.entity.Product;

/**
 * Servlet implementation class ShowBookServlet
 */
public class ShowBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<h1>欢迎购买</h1><br />");
		out.println("<table border='1' cellpadding='0' cellspacing='0' >");
		out.println("<tr><td>id</td><td>商品名称</td><td>价格</td></tr>");
		Dao dao=new DaoImpl();
		List<Product> list=dao.getProd();
		for(Product prod:list){
			out.println("<tr><td>"+prod.getId()+"</td><td>" +
					"<a href='BuyBookServlet?id="+prod.getId()+"'>"
			+prod.getProdName()+"</a></td><td>"+prod.getPrice()+"</td></tr>");
		}
		out.println("</table><br />");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
