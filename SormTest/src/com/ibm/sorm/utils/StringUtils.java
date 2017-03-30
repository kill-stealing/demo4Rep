package com.ibm.sorm.utils;

public class StringUtils {
	public static String first2UpperCase(String str){
		str=str.toUpperCase().substring(0, 1)+str.substring(1);
		return str;
	}
	
	public static void main(String[] args) {
		String aa="testa";
		System.out.println(first2UpperCase(aa));
	}
}
