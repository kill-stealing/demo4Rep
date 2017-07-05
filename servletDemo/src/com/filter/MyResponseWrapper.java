package com.filter;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MyResponseWrapper extends HttpServletResponseWrapper{

	private MyPrintWriter tmpWriter;
	private ByteArrayOutputStream output;
	
	public MyResponseWrapper(HttpServletResponse response) {
		super(response);
		output=new ByteArrayOutputStream();
		tmpWriter=new MyPrintWriter(output);
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		output.close();
		tmpWriter.close();
	}
	
	public String getContent(){
		try {
			tmpWriter.flush();//刷新该流的缓冲
			String string= tmpWriter.getByteArrayOutputStream().toString("utf-8");
			//此处可根据需要进行对输出流以及writer的重置操作
			//比如
			//tmpWriter.getByteArrayOutputStream().reset();
			return string;
		} catch (UnsupportedEncodingException e) {
			return "UnsupportedEncoding";
		}
	}
	
	public PrintWriter getWriter(){
		return tmpWriter;
	}
	
	public void close(){
		tmpWriter.close();
	}
	
	//自定义PrintWriter，为的是把response流写到自己指定的输入流当中
	//而非默认的ServletOutputStream
	private static class MyPrintWriter extends PrintWriter{
		ByteArrayOutputStream myOutput;
		public MyPrintWriter(ByteArrayOutputStream output){
			super(output);
			myOutput=output;
		}
		public ByteArrayOutputStream getByteArrayOutputStream(){
			return myOutput;
		}
	}
}
