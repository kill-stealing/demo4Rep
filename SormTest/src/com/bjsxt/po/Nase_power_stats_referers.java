 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_power_stats_referers {

	private String id ;
	private Integer count ;
	private String referer ;


	public String getId (){
		return id;
	}
	public Integer getCount (){
		return count;
	}
	public String getReferer (){
		return referer;
	}
	public void setId(String id ){
		this.id=id;
	}
	public void setCount(Integer count ){
		this.count=count;
	}
	public void setReferer(String referer ){
		this.referer=referer;
	}
}
