package com.ibm.demo.test.demo.designpattern.prototype;

import java.util.Date;

public class Sheep3 implements Cloneable{
	private String name;
	private Date birthday;
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Object obj=super.clone();//直接调用object对象的clone方法
		return obj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Sheep3(String name, Date birthday) {
		super();
		this.name = name;
		this.birthday = birthday;
	}
	
	public Sheep3() {
	}
}
