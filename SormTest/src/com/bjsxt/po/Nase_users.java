 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_users {

	private String user_pass ;
	private String display_name ;
	private String user_login ;
	private String user_nicename ;
	private String user_activation_key ;
	private String ID ;
	private Integer user_status ;
	private java.sql.Date user_registered ;
	private String user_url ;
	private String user_email ;


	public String getUser_pass (){
		return user_pass;
	}
	public String getDisplay_name (){
		return display_name;
	}
	public String getUser_login (){
		return user_login;
	}
	public String getUser_nicename (){
		return user_nicename;
	}
	public String getUser_activation_key (){
		return user_activation_key;
	}
	public String getID (){
		return ID;
	}
	public Integer getUser_status (){
		return user_status;
	}
	public java.sql.Date getUser_registered (){
		return user_registered;
	}
	public String getUser_url (){
		return user_url;
	}
	public String getUser_email (){
		return user_email;
	}
	public void setUser_pass(String user_pass ){
		this.user_pass=user_pass;
	}
	public void setDisplay_name(String display_name ){
		this.display_name=display_name;
	}
	public void setUser_login(String user_login ){
		this.user_login=user_login;
	}
	public void setUser_nicename(String user_nicename ){
		this.user_nicename=user_nicename;
	}
	public void setUser_activation_key(String user_activation_key ){
		this.user_activation_key=user_activation_key;
	}
	public void setID(String ID ){
		this.ID=ID;
	}
	public void setUser_status(Integer user_status ){
		this.user_status=user_status;
	}
	public void setUser_registered(java.sql.Date user_registered ){
		this.user_registered=user_registered;
	}
	public void setUser_url(String user_url ){
		this.user_url=user_url;
	}
	public void setUser_email(String user_email ){
		this.user_email=user_email;
	}
}
