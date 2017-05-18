package com.ibm.demo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadImgServlet
 */
public class DownloadImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;chatset=utf-8");
		
		//演示下载文件
		response.setHeader("Content-Disposition", "attachment; filename=test.jpg");
		
		//打开文件，说明一下web站点下载文件的原理
		//1.获取到要下载文件的路径
		String path=this.getServletContext().getRealPath("/images/test.jpg");
		
		//2.创建输入流
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(path));
		OutputStream os=response.getOutputStream();
		byte[] flush=new byte[1024];
		int len=0;
		while(-1!=(len=bis.read(flush))){
			os.write(flush, 0, len);
		}
		os.flush();
		os.close();
		bis.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
