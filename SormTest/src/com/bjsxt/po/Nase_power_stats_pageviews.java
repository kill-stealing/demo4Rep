 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_power_stats_pageviews {

	private String id ;
	private String hits ;
	private java.sql.Date date ;


	public String getId (){
		return id;
	}
	public String getHits (){
		return hits;
	}
	public java.sql.Date getDate (){
		return date;
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
}
