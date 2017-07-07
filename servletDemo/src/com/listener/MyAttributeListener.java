package com.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MyAttributeListener implements ServletContextAttributeListener,
		HttpSessionAttributeListener, ServletRequestAttributeListener {

	@Override
	public void attributeAdded(ServletRequestAttributeEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("ServletRequest 对象中增加了一个名为"+arg0.getName()+"的属性，该属性值为"+arg0.getValue());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("ServletRequest 对象中的"+arg0.getName()+"被删除了");
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("ServletRequest 对象中的"+arg0.getName()
				+"属性值由"+arg0.getValue()+"替换成了"+arg0.getServletRequest().getAttribute(arg0.getName()));
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("HttpSession 对象中增加了一个名为"+arg0.getName()+"的属性，该属性值为"+arg0.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("HttpSession 对象中的"+arg0.getName()+"被删除了");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("HttpSession 对象中的"+arg0.getName()
				+"属性值由"+arg0.getValue()+"替换成了"+arg0.getSession().getAttribute(arg0.getName()));
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("ServletContext 对象中增加了一个名为"+arg0.getName()+"的属性，该属性值为"+arg0.getValue());
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("ServletContext 对象中的"+arg0.getName()+"被删除了");
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("ServletContext 对象中的"+arg0.getName()
				+"属性值由"+arg0.getValue()+"替换成了"+arg0.getServletContext().getAttribute(arg0.getName()));
	}

}
