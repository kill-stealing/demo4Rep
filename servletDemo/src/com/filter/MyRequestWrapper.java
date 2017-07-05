package com.filter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequestWrapper extends HttpServletRequestWrapper{

	public MyRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String[] getParameterValues(String name) {
		String[] results=super.getParameterValues(name);
		if(results==null){
			return null;
		}
		int count=results.length;
		String[] trimResults=new String[count];
		for(int i=0;i<count;i++){
			trimResults[i]=results[i].trim();
		}
		return trimResults;
	}
	
	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		String value=super.getParameter(name);
		if(value==null){
			return null;
		}else{
			return super.getParameter(name).trim();
		}
		
	}

}
