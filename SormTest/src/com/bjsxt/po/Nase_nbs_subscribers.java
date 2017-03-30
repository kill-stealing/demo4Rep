 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_nbs_subscribers {

	private Integer id ;
	private String username ;
	private String wp_id ;
	private String status ;
	private String hash ;
	private String email ;
	private java.sql.Timestamp date_created ;
	private Integer form_id ;
	private String all_data ;


	public Integer getId (){
		return id;
	}
	public String getUsername (){
		return username;
	}
	public String getWp_id (){
		return wp_id;
	}
	public String getStatus (){
		return status;
	}
	public String getHash (){
		return hash;
	}
	public String getEmail (){
		return email;
	}
	public java.sql.Timestamp getDate_created (){
		return date_created;
	}
	public Integer getForm_id (){
		return form_id;
	}
	public String getAll_data (){
		return all_data;
	}
	public void setId(Integer id ){
		this.id=id;
	}
	public void setUsername(String username ){
		this.username=username;
	}
	public void setWp_id(String wp_id ){
		this.wp_id=wp_id;
	}
	public void setStatus(String status ){
		this.status=status;
	}
	public void setHash(String hash ){
		this.hash=hash;
	}
	public void setEmail(String email ){
		this.email=email;
	}
	public void setDate_created(java.sql.Timestamp date_created ){
		this.date_created=date_created;
	}
	public void setForm_id(Integer form_id ){
		this.form_id=form_id;
	}
	public void setAll_data(String all_data ){
		this.all_data=all_data;
	}
}
