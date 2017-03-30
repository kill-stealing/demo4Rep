 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_nls_subscribers {

	private Integer id ;
	private Integer flag ;
	private String l_name ;
	private String f_name ;
	private String email ;
	private String extra_detail ;
	private String act_code ;
	private java.sql.Timestamp date ;


	public Integer getId (){
		return id;
	}
	public Integer getFlag (){
		return flag;
	}
	public String getL_name (){
		return l_name;
	}
	public String getF_name (){
		return f_name;
	}
	public String getEmail (){
		return email;
	}
	public String getExtra_detail (){
		return extra_detail;
	}
	public String getAct_code (){
		return act_code;
	}
	public java.sql.Timestamp getDate (){
		return date;
	}
	public void setId(Integer id ){
		this.id=id;
	}
	public void setFlag(Integer flag ){
		this.flag=flag;
	}
	public void setL_name(String l_name ){
		this.l_name=l_name;
	}
	public void setF_name(String f_name ){
		this.f_name=f_name;
	}
	public void setEmail(String email ){
		this.email=email;
	}
	public void setExtra_detail(String extra_detail ){
		this.extra_detail=extra_detail;
	}
	public void setAct_code(String act_code ){
		this.act_code=act_code;
	}
	public void setDate(java.sql.Timestamp date ){
		this.date=date;
	}
}
