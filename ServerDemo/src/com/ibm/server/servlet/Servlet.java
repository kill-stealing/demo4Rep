package com.ibm.server.servlet;

import com.ibm.server.Request;
import com.ibm.server.Response;

/**
 * 抽象为一个父类
 * @author liumy
 *
 */
public abstract class Servlet {
	public void service(Request req,Response res) throws Exception{
		this.doGet(req,res);
		this.doPost(req,res);
	}
	
	public abstract void doGet(Request req,Response res) throws Exception;
	public abstract void doPost(Request req,Response res) throws Exception;
}
