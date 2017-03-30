 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_nbs_subscribers_lists {

	private Integer subscribers_cnt ;
	private String unique_id ;
	private Integer id ;
	private String description ;
	private Integer newletters_cnt ;
	private java.sql.Timestamp date_created ;
	private String label ;
	private Integer unsubscribed_cnt ;


	public Integer getSubscribers_cnt (){
		return subscribers_cnt;
	}
	public String getUnique_id (){
		return unique_id;
	}
	public Integer getId (){
		return id;
	}
	public String getDescription (){
		return description;
	}
	public Integer getNewletters_cnt (){
		return newletters_cnt;
	}
	public java.sql.Timestamp getDate_created (){
		return date_created;
	}
	public String getLabel (){
		return label;
	}
	public Integer getUnsubscribed_cnt (){
		return unsubscribed_cnt;
	}
	public void setSubscribers_cnt(Integer subscribers_cnt ){
		this.subscribers_cnt=subscribers_cnt;
	}
	public void setUnique_id(String unique_id ){
		this.unique_id=unique_id;
	}
	public void setId(Integer id ){
		this.id=id;
	}
	public void setDescription(String description ){
		this.description=description;
	}
	public void setNewletters_cnt(Integer newletters_cnt ){
		this.newletters_cnt=newletters_cnt;
	}
	public void setDate_created(java.sql.Timestamp date_created ){
		this.date_created=date_created;
	}
	public void setLabel(String label ){
		this.label=label;
	}
	public void setUnsubscribed_cnt(Integer unsubscribed_cnt ){
		this.unsubscribed_cnt=unsubscribed_cnt;
	}
}
