 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_advps_thumbnail {

	private Integer id ;
	private Integer height ;
	private Integer width ;
	private String thumb_name ;
	private String crop ;


	public Integer getId (){
		return id;
	}
	public Integer getHeight (){
		return height;
	}
	public Integer getWidth (){
		return width;
	}
	public String getThumb_name (){
		return thumb_name;
	}
	public String getCrop (){
		return crop;
	}
	public void setId(Integer id ){
		this.id=id;
	}
	public void setHeight(Integer height ){
		this.height=height;
	}
	public void setWidth(Integer width ){
		this.width=width;
	}
	public void setThumb_name(String thumb_name ){
		this.thumb_name=thumb_name;
	}
	public void setCrop(String crop ){
		this.crop=crop;
	}
}
