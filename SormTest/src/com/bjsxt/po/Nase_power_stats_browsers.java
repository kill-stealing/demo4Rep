 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_power_stats_browsers {

	private String id ;
	private Integer count ;
	private String browser ;


	public String getId (){
		return id;
	}
	public Integer getCount (){
		return count;
	}
	public String getBrowser (){
		return browser;
	}
	public void setId(String id ){
		this.id=id;
	}
	public void setCount(Integer count ){
		this.count=count;
	}
	public void setBrowser(String browser ){
		this.browser=browser;
	}
}
