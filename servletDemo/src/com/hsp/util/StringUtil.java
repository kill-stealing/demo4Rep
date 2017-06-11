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
	
	public static int getStrLength(String string,String str){
		int l1=string.length();
		int l2=string.replace(str, "").length();
		return l1-l2;
	}
	
	public static String removeHeng(String str,String includeStr){
		int _length=getStrLength(str,"-");
		if(str.indexOf(includeStr)!=-1){
			//是否有#号
			if(str.indexOf("#")!=-1){
				str=str.split(includeStr)[1];
				str=str.replace("-", "");
			}else{
				str=str.split(includeStr)[1];
				//4-1-25-4 4#2504
				int _length1=getStrLength(str,"-");
				if(_length1!=3){
					StringBuilder sb = new StringBuilder(str);//构造一个StringBuilder对象
			        sb.insert(str.indexOf("-")+1, "1-");//在指定的位置1，插入指定的字符串
			        str = sb.toString();
				}
				str=str.replace("-1-", "#1");
				str=str.replace("-", "");
			}
		}else{
			str=str.replace("-", "");
		}
		
		if(_length==4){
			StringBuilder sb = new StringBuilder(str);//构造一个StringBuilder对象
	        sb.insert(str.length()-1, "0");//在指定的位置1，插入指定的字符串
	        str = sb.toString();
		}
		
		str=str.replace("#1", "#");
        System.out.println(str);
        return str;
		
	}
	
	public static void main(String[] args) {
		
		removeHeng("A4-13#-11-4", "A4-");
		
		removeHeng("A4-8-20-1", "A4-");
		
		removeHeng("A4-4-1-25-4", "A4-");
		removeHeng("A4-13#-1-13-1", "A4-");
		
		String str=handleCheWeiHao("");
		System.out.println(str);
	}
	
	//B262-1
	public static String handleCheWeiHao(String str){
		if(str.indexOf("-")!=-1){
			str=str.replace("-1", "");
		}
		return str;
	}
}
