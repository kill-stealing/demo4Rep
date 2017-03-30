 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_fsevents {

	private Long postid ;
	private java.sql.Date createdaten ;
	private String location ;
	private String subject ;
	private java.sql.Date publishdaten ;
	private String state ;
	private Integer eventid ;
	private Integer updatedbypost ;
	private Long author ;
	private Long publishauthor ;
	private Integer rec_main_id ;
	private java.sql.Date datefrom ;
	private String description ;
	private java.sql.Date dateto ;
	private String allday ;
	private String recurring ;


	public Long getPostid (){
		return postid;
	}
	public java.sql.Date getCreatedaten (){
		return createdaten;
	}
	public String getLocation (){
		return location;
	}
	public String getSubject (){
		return subject;
	}
	public java.sql.Date getPublishdaten (){
		return publishdaten;
	}
	public String getState (){
		return state;
	}
	public Integer getEventid (){
		return eventid;
	}
	public Integer getUpdatedbypost (){
		return updatedbypost;
	}
	public Long getAuthor (){
		return author;
	}
	public Long getPublishauthor (){
		return publishauthor;
	}
	public Integer getRec_main_id (){
		return rec_main_id;
	}
	public java.sql.Date getDatefrom (){
		return datefrom;
	}
	public String getDescription (){
		return description;
	}
	public java.sql.Date getDateto (){
		return dateto;
	}
	public String getAllday (){
		return allday;
	}
	public String getRecurring (){
		return recurring;
	}
	public void setPostid(Long postid ){
		this.postid=postid;
	}
	public void setCreatedaten(java.sql.Date createdaten ){
		this.createdaten=createdaten;
	}
	public void setLocation(String location ){
		this.location=location;
	}
	public void setSubject(String subject ){
		this.subject=subject;
	}
	public void setPublishdaten(java.sql.Date publishdaten ){
		this.publishdaten=publishdaten;
	}
	public void setState(String state ){
		this.state=state;
	}
	public void setEventid(Integer eventid ){
		this.eventid=eventid;
	}
	public void setUpdatedbypost(Integer updatedbypost ){
		this.updatedbypost=updatedbypost;
	}
	public void setAuthor(Long author ){
		this.author=author;
	}
	public void setPublishauthor(Long publishauthor ){
		this.publishauthor=publishauthor;
	}
	public void setRec_main_id(Integer rec_main_id ){
		this.rec_main_id=rec_main_id;
	}
	public void setDatefrom(java.sql.Date datefrom ){
		this.datefrom=datefrom;
	}
	public void setDescription(String description ){
		this.description=description;
	}
	public void setDateto(java.sql.Date dateto ){
		this.dateto=dateto;
	}
	public void setAllday(String allday ){
		this.allday=allday;
	}
	public void setRecurring(String recurring ){
		this.recurring=recurring;
	}
}
