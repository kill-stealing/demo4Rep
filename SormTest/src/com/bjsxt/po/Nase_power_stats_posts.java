 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_power_stats_posts {

	private String id ;
	private String hits ;
	private java.sql.Date date ;
	private Integer post_id ;


	public String getId (){
		return id;
	}
	public String getHits (){
		return hits;
	}
	public java.sql.Date getDate (){
		return date;
	}
	public Integer getPost_id (){
		return post_id;
	}
	public void setId(String id ){
		this.id=id;
	}
	public void setHits(String hits ){
		this.hits=hits;
	}
	public void setDate(java.sql.Date date ){
		this.date=date;
	}
	public void setPost_id(Integer post_id ){
		this.post_id=post_id;
	}
}
