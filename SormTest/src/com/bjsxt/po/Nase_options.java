 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_options {

	private String option_name ;
	private String autoload ;
	private String option_value ;
	private String option_id ;


	public String getOption_name (){
		return option_name;
	}
	public String getAutoload (){
		return autoload;
	}
	public String getOption_value (){
		return option_value;
	}
	public String getOption_id (){
		return option_id;
	}
	public void setOption_name(String option_name ){
		this.option_name=option_name;
	}
	public void setAutoload(String autoload ){
		this.autoload=autoload;
	}
	public void setOption_value(String option_value ){
		this.option_value=option_value;
	}
	public void setOption_id(String option_id ){
		this.option_id=option_id;
	}
}
