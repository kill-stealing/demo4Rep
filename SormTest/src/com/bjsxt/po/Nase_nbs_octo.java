 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_nbs_octo {

	private String unique_id ;
	private Integer id ;
	private String is_base ;
	private String is_pro ;
	private Integer original_id ;
	private String img ;
	private String active ;
	private java.sql.Timestamp date_created ;
	private String label ;
	private Integer pid ;
	private String params ;
	private String sort_order ;


	public String getUnique_id (){
		return unique_id;
	}
	public Integer getId (){
		return id;
	}
	public String getIs_base (){
		return is_base;
	}
	public String getIs_pro (){
		return is_pro;
	}
	public Integer getOriginal_id (){
		return original_id;
	}
	public String getImg (){
		return img;
	}
	public String getActive (){
		return active;
	}
	public java.sql.Timestamp getDate_created (){
		return date_created;
	}
	public String getLabel (){
		return label;
	}
	public Integer getPid (){
		return pid;
	}
	public String getParams (){
		return params;
	}
	public String getSort_order (){
		return sort_order;
	}
	public void setUnique_id(String unique_id ){
		this.unique_id=unique_id;
	}
	public void setId(Integer id ){
		this.id=id;
	}
	public void setIs_base(String is_base ){
		this.is_base=is_base;
	}
	public void setIs_pro(String is_pro ){
		this.is_pro=is_pro;
	}
	public void setOriginal_id(Integer original_id ){
		this.original_id=original_id;
	}
	public void setImg(String img ){
		this.img=img;
	}
	public void setActive(String active ){
		this.active=active;
	}
	public void setDate_created(java.sql.Timestamp date_created ){
		this.date_created=date_created;
	}
	public void setLabel(String label ){
		this.label=label;
	}
	public void setPid(Integer pid ){
		this.pid=pid;
	}
	public void setParams(String params ){
		this.params=params;
	}
	public void setSort_order(String sort_order ){
		this.sort_order=sort_order;
	}
}
