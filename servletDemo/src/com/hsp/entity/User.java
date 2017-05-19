package com.hsp.entity;

public class User {
/*	create TABLE atest_user(
			user_id int PRIMARY key,
		    user_name varchar(32),
		    pwd varchar(32)
		)
		
		alter table atest_user MODIFY user_id int AUTO_INCREMENT
		
		INSERT into atest_user (user_id,user_name,pwd) values(DEFAULT,'abc','123');
		*/
	private int userId;
	private String userName;
	private String pwd;
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(int userId, String userName, String pwd) {
		this.userId = userId;
		this.userName = userName;
		this.pwd = pwd;
	}
	
	public User(String userName, String pwd) {
		this.userName = userName;
		this.pwd = pwd;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
