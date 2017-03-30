 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_usermeta {

	private String umeta_id ;
	private String meta_key ;
	private String user_id ;
	private String meta_value ;


	public String getUmeta_id (){
		return umeta_id;
	}
	public String getMeta_key (){
		return meta_key;
	}
	public String getUser_id (){
		return user_id;
	}
	public String getMeta_value (){
		return meta_value;
	}
	public void setUmeta_id(String umeta_id ){
		this.umeta_id=umeta_id;
	}
	public void setMeta_key(String meta_key ){
		this.meta_key=meta_key;
	}
	public void setUser_id(String user_id ){
		this.user_id=user_id;
	}
	public void setMeta_value(String meta_value ){
		this.meta_value=meta_value;
	}
}
