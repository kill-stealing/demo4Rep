package com.ibm.shoppingcar;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsp.dao.Dao;
import com.hsp.dao.DaoImpl;
import com.hsp.entity.Book;
import com.hsp.entity.OrderInfo;
import com.hsp.entity.Product;

/**
 * Servlet implementation class ShowMyCarServlet
 */
public class ShowMyCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out=response.getWriter();
    	
    	//
    	String prodId=request.getParameter("prodId");
    	Dao dao=new DaoImpl();
    	HttpSession session=request.getSession();
    	HashMap<String, Book> map=(HashMap<String, Book>)session.getAttribute("prod");
    	if(prodId!=null){
    		Product prod1=dao.getProd(Integer.parseInt(prodId));
    		//判断session中是否有产品信息
    		String key=prodId+"";
    		if(map!=null){
    			Book book=map.get(key);
    			if(book!=null){
    				book.setCount(book.getCount()+1);
    			}else{
    				book=new Book();
    				book.setId(Integer.parseInt(prodId));
        			book.setName(prod1.getProdName());
        			book.setCount(1);
    			}
    			map.put(key, book);
    		}else{
    			Book book=new Book();
    			book.setId(Integer.parseInt(prodId));
    			book.setName(prod1.getProdName());
    			book.setCount(1);
    			map=new HashMap<>();
    			map.put(key, book);
    		}
    		session.setAttribute("prod", map);
    		Cookie cookie=new Cookie("JSESSIONID", session.getId());
    		cookie.setMaxAge(3600*24*7);
    		response.addCookie(cookie);
    	}
		out.println("<a href='ShowBookServlet' >返回主界面</a>");
		out.println("<table border='1' cellpadding='0' cellspacing='0' >");
		out.println("<tr><td>id</td><td>商品名称</td><td>数量</td></tr>");
		if(map!=null){
			Iterator<String> it=map.keySet().iterator();
			while (it.hasNext()) {
				String key=it.next();
				Book book=map.get(key);
				out.println("<tr><td>"+book.getId()+"</td><td>" +
						"<a href='BuyBookServlet?id="+book.getId()+"'>"
				+book.getName()+"</a></td><td>"+book.getCount()+"</td></tr>");
			}
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
