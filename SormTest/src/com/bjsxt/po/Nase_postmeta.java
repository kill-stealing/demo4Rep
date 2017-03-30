 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_postmeta {

	private String meta_key ;
	private String post_id ;
	private String meta_value ;
	private String meta_id ;


	public String getMeta_key(){
		return meta_key;
	}
	public String getPost_id (){
		return post_id;
	}
	public String getMeta_value (){
		return meta_value;
	}
	public String getMeta_id (){
		return meta_id;
	}
	public void setMeta_key(String meta_key ){
		this.meta_key=meta_key;
	}
	public void setPost_id(String post_id ){
		this.post_id=post_id;
	}
	public void setMeta_value(String meta_value ){
		this.meta_value=meta_value;
	}
	public void setMeta_id(String meta_id ){
		this.meta_id=meta_id;
	}
}
