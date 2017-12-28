package com.citi.demo;


/**
 * 创建了一个实体类
 * 
 * 如何持久化呢
 * 
 * 1.使用@entity进行实体类的持久化操作，当JPA检测到我们的实体类当中有
 * 
 * @Entity 注解的时候，会在数据库中生成对应的表结构信息
 * 
 * 如何指定主键以及主键的生成策略？
 * 
 * 2.使用@Id 指定主键
 * @author Administrator
 *
 */
public class Cat {
	private int id;
	private String catName;
	private int catAge;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public int getCatAge() {
		return catAge;
	}
	public void setCatAge(int catAge) {
		this.catAge = catAge;
	}
}
