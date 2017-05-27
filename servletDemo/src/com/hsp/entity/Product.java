package com.hsp.entity;

public class Product {
	
//	CREATE TABLE atest_prod( id int PRIMARY key, prod_name varchar(64), price int )
//	alter table atest_prod MODIFY id int AUTO_INCREMENT
//	insert INTO atest_prod VALUES (0,'feng shan 2',3000)
	
	private int id;
	private String prodName;
	private float price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Product(int id, String prodName, float price) {
		this.id = id;
		this.prodName = prodName;
		this.price = price;
	}
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
}
