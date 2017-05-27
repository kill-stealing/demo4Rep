package com.hsp.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateCheckCodeServlet1
 */
public class CreateCheckCodeServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=utf-8");
//		response.setCharacterEncoding("utf-8");
		//7.禁止浏览器缓存随机图片
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		
		//6.通知客户机以图片方式打开发送过去的数据
		response.setHeader("Content-Type", "image/jpeg");
		
		//1.在内存中创建一副图片
		BufferedImage image=new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
		
		//2.得到画笔
		Graphics g=image.getGraphics();
		
		//设置背景颜色
		g.setColor(Color.WHITE);
		g.fillRect(0,0,80,30);
		
		//3.设置写入数据的颜色和字体
		g.setColor(Color.BLACK);
		g.setFont(new Font(null,Font.BOLD,20));
		
		//4.向图片上写数据
		String num=makeNum();
		
		request.getSession().setAttribute("checkCode", num);
		g.drawString(num, 0, 20);
		
		//5.把写好数据的图片输出给浏览器
		ImageIO.write(image, "jpg", response.getOutputStream());
	}
	

	//该函数是随机生成7位数字
	public String makeNum(){
		Random r=new Random();
		String num=r.nextInt(9999999)+"";
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < 7-num.length(); i++) {
			sb.append("0");
		}
		num=sb.toString()+num;
		return num;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
