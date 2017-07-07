package com.guojihua;

import java.util.Locale;

public class LocaleExam {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Locale locale=new Locale("德文","CH");
		System.out.println("德文地区的ISO语言代码："+locale.getLanguage());
		System.out.println("德文中的“德文”:"+locale.getDisplayLanguage(locale));
		System.out.println("德文（瑞士）的Locale对象按操作系统的默认本地方式显示的名称为："+locale.getDisplayName());
		System.out.println("德文（瑞士）的Locale对象按德文（瑞士）的本地方式显示的信息为："+locale.getDisplayName(locale));
	}

}
