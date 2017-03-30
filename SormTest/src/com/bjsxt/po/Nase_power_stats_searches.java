 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_power_stats_searches {

	private String id ;
	private Integer count ;
	private String terms ;


	public String getId (){
		return id;
	}
	public Integer getCount (){
		return count;
	}
	public String getTerms (){
		return terms;
	}
	public void setId(String id ){
		this.id=id;
	}
	public void setCount(Integer count ){
		this.count=count;
	}
	public void setTerms(String terms ){
		this.terms=terms;
	}
}
