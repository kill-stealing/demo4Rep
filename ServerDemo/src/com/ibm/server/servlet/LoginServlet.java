package com.ibm.server.servlet;

import com.ibm.server.Request;
import com.ibm.server.Response;

public class LoginServlet extends Servlet{

	@Override
	public void doGet(Request req,Response res) throws Exception {
		String name=req.getParameter("userName");
		String pwd=req.getParameter("pwd");
		
		if(login(name, pwd)){
			res.println("登录成功");
		}else{
			res.println("登录失败");
		}
	}
	
	public boolean login(String name,String pwd){
		return name.equals("bjsxt")&&pwd.equals("123456");
	}

	@Override
	public void doPost(Request req, Response res) throws Exception {
	}

}
