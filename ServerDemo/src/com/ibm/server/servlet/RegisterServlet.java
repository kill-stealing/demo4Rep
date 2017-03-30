package com.ibm.server.servlet;

import com.ibm.server.Request;
import com.ibm.server.Response;

public class RegisterServlet extends Servlet{

	@Override
	public void doGet(Request req,Response res) throws Exception {
		
	}

	@Override
	public void doPost(Request req, Response res) throws Exception {
		res.println("<html><head><title>返回注册</title></head>");
		res.println("<body>");
		res.println("你的用户名为：").println(req.getParameter("userName"));
		res.println("</body></html>");
	}

}
