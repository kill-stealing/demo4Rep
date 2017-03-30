 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class List {

	private Integer id ;
	private java.sql.Blob headImg ;
	private java.sql.Date born_date ;
	private java.sql.Time born_date2 ;
	private java.sql.Timestamp born_date1 ;
	private String name ;
	private String myInfo ;


	public Integer getId (){
		return id;
	}
	public java.sql.Blob getHeadImg (){
		return headImg;
	}
	public java.sql.Date getBorn_date (){
		return born_date;
	}
	public java.sql.Time getBorn_date2 (){
		return born_date2;
	}
	public java.sql.Timestamp getBorn_date1 (){
		return born_date1;
	}
	public String getName (){
		return name;
	}
	public String getMyInfo (){
		return myInfo;
	}
	public void setId(Integer id ){
		this.id=id;
	}
	public void setHeadImg(java.sql.Blob headImg ){
		this.headImg=headImg;
	}
	public void setBorn_date(java.sql.Date born_date ){
		this.born_date=born_date;
	}
	public void setBorn_date2(java.sql.Time born_date2 ){
		this.born_date2=born_date2;
	}
	public void setBorn_date1(java.sql.Timestamp born_date1 ){
		this.born_date1=born_date1;
	}
	public void setName(String name ){
		this.name=name;
	}
	public void setMyInfo(String myInfo ){
		this.myInfo=myInfo;
	}
}
