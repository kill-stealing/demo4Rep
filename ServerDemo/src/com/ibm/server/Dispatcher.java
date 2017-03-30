package com.ibm.server;

import java.io.IOException;
import java.net.Socket;

import com.ibm.server.servlet.Servlet;
import com.ibm.util.CloseUtil;

/**
 * 一个请求与响应就一个此对象
 * @author liumy
 *
 */
public class Dispatcher implements Runnable{
	private Socket client;
	private Request req;
	private Response res;
	private int code=200;
	
	public Dispatcher(Socket client) {
		this.client=client;
		try {
			req=new Request(client.getInputStream());
			res=new Response(client.getOutputStream());
		} catch (IOException e) {
			code=500;
			return;
		}
	}
	
	@Override
	public void run() {
		try {
			Servlet serv=WebApp.getServlet(req.getUrl());
			if(null==serv){
				this.code=404;
			}else{
				serv.service(req, res);
			}
		}catch (Exception e) {
			this.code=500;
		}
		try {
			res.pushToClient(code);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		CloseUtil.closeAll(client);
	}

}
