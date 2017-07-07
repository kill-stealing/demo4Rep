package com.listener;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

public class MyBean implements HttpSessionBindingListener,HttpSessionActivationListener,Serializable{

	@Override
	public void sessionDidActivate(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("对象从文件系统中恢复了");
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("对象将被持久化到文件系统中");
	}
	
	//该方法被调用时，打印出对象被绑定的信息
	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("当前Session的ID标识为"+arg0.getSession().getId());
		System.out.println("对象被绑定到了这个session对象中的"+arg0.getName()+"属性上");
	}
	
	//该方法被调用时，打印出对象被解绑的信息
	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("当前Session的ID标识为"+arg0.getSession().getId());
		System.out.println("对象从这个session对象中的"+arg0.getName()+"属性上解除绑定");
	}

}
