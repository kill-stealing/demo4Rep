package com.newdemo.test;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class TestURLEncode {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String utf8_url=URLEncoder.encode("中国","utf-8");
		String gb2312_url=URLEncoder.encode("中国","gb2312");
		System.out.println("中国的utf-8码的URL编码为："+utf8_url);
		System.out.println("中国的gb2312码的URL编码为："+gb2312_url);
		System.out.println("将"+gb2312_url+"按GB2312码进行URL编码的结果为："+URLDecoder.decode(gb2312_url,"gb2312"));
		System.out.println("将"+utf8_url+"按iso8859-1码进行URL编码的结果为："+URLDecoder.decode(utf8_url,"iso-8859-1"));
		System.out.println("将"+utf8_url+"按iso8859-1码进行URL编码的结果为："+URLDecoder.decode(utf8_url,"utf-8"));
	}

}
