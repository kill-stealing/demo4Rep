package com.hsp.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsp.dao.Dao;
import com.hsp.dao.DaoImpl;
import com.hsp.entity.Product;
import com.hsp.entity.User;

/**
 * Servlet implementation class ManageProduct
 */
public class ManageProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int MAX_SIZE=4;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String type=request.getParameter("type");
		out.println("<img src='images/1.png' />欢迎:<b>XX</b> 登录"); 
		out.println("<a href='MainFrame' >返回主界面</a>");
		out.println("<a href='LoginServlet' >安全退出</a><hr >");
		out.println("<table border='1' cellpadding='0' cellspacing='0' >");
		out.println("<tr><td>id</td><td>商品名称</td><td>价格</td></tr>");
		Dao dao=new DaoImpl();
		if(type.equals("all")){
			List<Product> list=dao.getProd();
			for(Product prod:list){
				out.println("<tr><td>"+prod.getId()+"</td><td><a href='ManageProduct?type=single&id="+prod.getId()+"'>"
				+prod.getProdName()+"</a></td><td>"+prod.getPrice()+"</td></tr>");
			}
			out.println("</table><br />");
		}else if(type.equals("single")){
			int id=request.getParameter("id")!=null?Integer.parseInt(request.getParameter("id")):0;
			Product prod=dao.getProd(id);
			out.println("<tr><td>"+prod.getId()+"</td><td>"
					+prod.getProdName()+"</td><td>"+prod.getPrice()+"</td></tr>");
			out.println("</table><br />");
			out.println("<hr ><img src='images/2.png' />"); 
			
			Cookie[] cookies=request.getCookies();
			if(cookies!=null){
				for (Cookie cookie : cookies) {
					if(cookie.getName().equals("prod")){
						String ids=cookie.getValue();
						cookie.setValue(ids+","+id);
						cookie.setMaxAge(3600*24*7);
						response.addCookie(cookie);
						break;
					}
				}
			}
		}
		
		String ids="";
		Cookie[] cookies=request.getCookies();
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("prod")){
					ids=cookie.getValue();
					break;
				}
			}
		}
		//处理id 逗号隔开
		if(!"".equals(ids)){
			out.println("<hr > 您浏览过的商品有：");
			out.println("<table border='1' cellpadding='0' cellspacing='0' >");
			out.println("<tr><td>id</td><td>商品名称</td><td>价格</td></tr>");
			if(ids.indexOf(",")!=-1){
				/*String[] id=ids.split(",");
				int[] idInt={};
				for (int i = 0; i < id.length; i++) {
					idInt[i]=Integer.parseInt(id[i]);
				}
				List<Product> list1=dao.getProd();
				for(Product prod:list1){
					out.println("<tr><td>"+prod.getId()+"</td><td><a href='ManageProduct?type=single&id="+prod.getId()+"'>"
					+prod.getProdName()+"</a></td><td>"+prod.getPrice()+"</td></tr>");
				}*/
				
				String[] id=ids.split(",");
				List<Product> list1=new ArrayList<Product>();
				for (int i = id.length-1; i >-1; i--) {
					int temp=Integer.parseInt(id[i]);
					Product prod=dao.getProd(temp);
					list1.add(prod);
				}
				
				for(Product prod:list1){
					out.println("<tr><td>"+prod.getId()+"</td><td><a href='ManageProduct?type=single&id="+prod.getId()+"'>"
					+prod.getProdName()+"</a></td><td>"+prod.getPrice()+"</td></tr>");
				}
			}else{
				Product prod=dao.getProd(Integer.parseInt(ids));
				out.println("<tr><td>"+prod.getId()+"</td><td><a href='ManageProduct?type=single&id="+prod.getId()+"'>"
						+prod.getProdName()+"</a></td><td>"+prod.getPrice()+"</td></tr>");
			}
			out.println("</table><br />");
			out.println("<hr ><img src='images/2.png' />"); 
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
