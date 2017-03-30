 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_test_table {

	private Integer id ;
	private Integer count ;
	private String user_id ;
	private String post_id ;


	public Integer getId (){
		return id;
	}
	public Integer getCount (){
		return count;
	}
	public String getUser_id (){
		return user_id;
	}
	public String getPost_id (){
		return post_id;
	}
	public void setId(Integer id ){
		this.id=id;
	}
	public void setCount(Integer count ){
		this.count=count;
	}
	public void setUser_id(String user_id ){
		this.user_id=user_id;
	}
	public void setPost_id(String post_id ){
		this.post_id=post_id;
	}
}
