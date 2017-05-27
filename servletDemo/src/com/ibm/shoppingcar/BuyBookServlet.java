package com.ibm.shoppingcar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsp.dao.Dao;
import com.hsp.dao.DaoImpl;
import com.hsp.entity.Product;

/**
 * Servlet implementation class BuyBookServlet
 */
public class BuyBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		int id=request.getParameter("id")!=null?Integer.parseInt(request.getParameter("id")):0;
		Dao dao=new DaoImpl();
		Product prod1=dao.getProd(id);
		out.println("<a href='ShowBookServlet' >返回主界面</a>");
		out.println("<form action='ShowMyCarServlet' method='post' >");
		out.println("<table border='1' cellpadding='0' cellspacing='0' >");
		out.println("<tr><td>id</td><td>商品名称</td><td>价格</td></tr>");
		out.println("<tr><td>"+prod1.getId()+"</td><td>"
				+prod1.getProdName()+"</td><td>"+prod1.getPrice()+"</td></tr>");
		out.println("</table><br />");
		out.println("<input type='hidden' name='prodId' value='"+id+"' />");
		out.println("<input type='submit' value='购买' />");
		out.println("</form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
