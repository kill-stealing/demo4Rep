package com.ibm.servletinfo.demo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.ibm.demo.test.demo.netdevelop.chat.demo02.CloseUtil;

public class MyWebServer {
	public static void main(String[] args) {
		OutputStream os=null;
		BufferedInputStream bis=null;
		Socket client=null;
		try {
			ServerSocket ss=new ServerSocket(80);
			client=ss.accept();
			//提示一句话
			System.out.println("在9999上等待链接。。。");
			os=client.getOutputStream();
			bis=new BufferedInputStream(new FileInputStream(new File("C:/test.html")));
//				BufferedReader br=new BufferedReader(new FileReader(new File("C:/test.html")));
//				String str="";
//				while((str=br.readLine())!=null){
//					os.write(str.getBytes());
//				}
//				os.flush();
			int len=0;
			byte[] flush=new byte[1024];
			while(-1!=(len=bis.read(flush))){
				os.write(flush,0,len);
			}
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			CloseUtil.closeAll(os,bis);
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
