 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_newsletter_stats {

	private Integer id ;
	private java.sql.Timestamp created ;
	private Integer user_id ;
	private Integer link_id ;
	private Integer email_id ;
	private String url ;
	private String country ;
	private String ip ;
	private String anchor ;


	public Integer getId (){
		return id;
	}
	public java.sql.Timestamp getCreated (){
		return created;
	}
	public Integer getUser_id (){
		return user_id;
	}
	public Integer getLink_id (){
		return link_id;
	}
	public Integer getEmail_id (){
		return email_id;
	}
	public String getUrl (){
		return url;
	}
	public String getCountry (){
		return country;
	}
	public String getIp (){
		return ip;
	}
	public String getAnchor (){
		return anchor;
	}
	public void setId(Integer id ){
		this.id=id;
	}
	public void setCreated(java.sql.Timestamp created ){
		this.created=created;
	}
	public void setUser_id(Integer user_id ){
		this.user_id=user_id;
	}
	public void setLink_id(Integer link_id ){
		this.link_id=link_id;
	}
	public void setEmail_id(Integer email_id ){
		this.email_id=email_id;
	}
	public void setUrl(String url ){
		this.url=url;
	}
	public void setCountry(String country ){
		this.country=country;
	}
	public void setIp(String ip ){
		this.ip=ip;
	}
	public void setAnchor(String anchor ){
		this.anchor=anchor;
	}
}
