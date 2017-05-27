package com.hsp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StringUtil {
//	public static String toDate(S){
//		
//	}
	
	public static String urlToString(String str){
		String result="";
		
		try {
			result=URLEncoder.encode(str,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String urlDecode(String str){
		String result="";
		
		try {
			result=URLDecoder.decode(str,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
