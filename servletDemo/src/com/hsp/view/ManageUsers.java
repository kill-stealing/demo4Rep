package com.hsp.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsp.dao.Dao;
import com.hsp.dao.DaoImpl;
import com.hsp.entity.User;

/**
 * Servlet implementation class ManageUsers
 */
public class ManageUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script type='text/javascript' language='javascript'>");
		out.println("function gotoPageNum(){"+
					"var pageNum=document.getElementById('pageNum').value;"+
					"window.location.href='ManageUsers?pageNum='+pageNum;"+
				"}");
		out.println("</script>");
		try {
//			PageNow 表示第几页 ，该变量是由用户来决定，因此变化
//			pageSize  每页显示几条记录，由程序指定，也可以由用户定制
//			pageCount 表示共有多少页，该变量是计算出来的 思考，怎样确定
//			rowCount  共有多少条记录。该变量是查询数据库得到的。
			int pageNum=Integer.parseInt(request.getParameter("pageNum")!=null?request.getParameter("pageNum"):"1");
			int pageSize=Integer.parseInt(request.getParameter("pageSize")!=null?request.getParameter("pageSize"):"3");
			int pageCount=0;
			int rowCount=0;
			Dao dao=new DaoImpl();
			List<User> list=dao.getUser(pageNum,pageSize);
			List<User> list1=dao.getUser();
			
			rowCount=list1.size();
			pageCount=(rowCount-1)/pageSize+1;
			
			out.println("<img src='images/1.png' />欢迎:<b>XX</b> 登录"); 
			out.println("<a href='MainFrame' >返回主界面</a>");
			out.println("<a href='LoginServlet' >安全退出</a><hr >");
			out.println("<table border='1' cellpadding='0' cellspacing='0' >");
			out.println("<tr><td>id</td><td>用户名</td><td>pwd</td></tr>");
			for(User user:list){
				out.println("<tr><td>"+user.getUserId()+"</td><td>"
				+user.getUserName()+"</td><td>"+user.getPwd()+"</td></tr>");
			}
			out.println("</table><br />");
			if(pageNum-1>0){
				out.println("<a href='ManageUsers?pageNum="+(pageNum-1)+"&pageSize="+pageSize+"' >上一页</a>");
			}
			
			for(int i=1;i<=pageCount;i++){
				out.println("<a href='ManageUsers?pageNum="+(i)+"&pageSize="+pageSize+"' ><"+i+"></a>");
			}
			
			if(pageNum<pageCount){
				out.println("<a href='ManageUsers?pageNum="+(pageNum+1)
						+"&pageSize="+pageSize+"' >下一页</a> ");
			}
			out.println("当前页 "+pageNum+"/总页数"+pageCount+"<br /><br />");
//			out.println("<form action=");
			out.println("跳转到<input type='text' name='pageNum' id='pageNum'/>页");
			out.println("<input type='button' onclick='gotoPageNum()' value='跳'/>");
//			out.println("<a href='ManageUsers?pageNum="+(i)+"&pageSize="+pageSize+"' ></a>");
			
			out.println("<hr ><img src='images/2.png' />"); 	
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			
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
