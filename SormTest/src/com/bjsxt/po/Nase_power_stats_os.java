 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_power_stats_os {

	private String id ;
	private String os ;
	private Integer count ;


	public String getId (){
		return id;
	}
	public String getOs (){
		return os;
	}
	public Integer getCount (){
		return count;
	}
	public void setId(String id ){
		this.id=id;
	}
	public void setOs(String os ){
		this.os=os;
	}
	public void setCount(Integer count ){
		this.count=count;
	}
}
