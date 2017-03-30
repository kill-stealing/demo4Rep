 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_post_click_counts {

	private Integer id ;
	private Integer COUNT ;
	private String user_id ;
	private String post_id ;


	public Integer getId (){
		return id;
	}
	public Integer getCOUNT (){
		return COUNT;
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
	public void setCOUNT(Integer COUNT ){
		this.COUNT=COUNT;
	}
	public void setUser_id(String user_id ){
		this.user_id=user_id;
	}
	public void setPost_id(String post_id ){
		this.post_id=post_id;
	}
}
