 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_power_stats_visits {

	private String os ;
	private Integer is_bot ;
	private java.sql.Date date ;
	private String ip ;
	private String country ;
	private String id ;
	private String browser_version ;
	private java.sql.Time time ;
	private String device ;
	private String browser ;
	private String user_agent ;
	private String referer ;
	private String language ;
	private Integer is_search_engine ;
	private String user ;


	public String getOs (){
		return os;
	}
	public Integer getIs_bot (){
		return is_bot;
	}
	public java.sql.Date getDate (){
		return date;
	}
	public String getIp (){
		return ip;
	}
	public String getCountry (){
		return country;
	}
	public String getId (){
		return id;
	}
	public String getBrowser_version (){
		return browser_version;
	}
	public java.sql.Time getTime (){
		return time;
	}
	public String getDevice (){
		return device;
	}
	public String getBrowser (){
		return browser;
	}
	public String getUser_agent (){
		return user_agent;
	}
	public String getReferer (){
		return referer;
	}
	public String getLanguage (){
		return language;
	}
	public Integer getIs_search_engine (){
		return is_search_engine;
	}
	public String getUser (){
		return user;
	}
	public void setOs(String os ){
		this.os=os;
	}
	public void setIs_bot(Integer is_bot ){
		this.is_bot=is_bot;
	}
	public void setDate(java.sql.Date date ){
		this.date=date;
	}
	public void setIp(String ip ){
		this.ip=ip;
	}
	public void setCountry(String country ){
		this.country=country;
	}
	public void setId(String id ){
		this.id=id;
	}
	public void setBrowser_version(String browser_version ){
		this.browser_version=browser_version;
	}
	public void setTime(java.sql.Time time ){
		this.time=time;
	}
	public void setDevice(String device ){
		this.device=device;
	}
	public void setBrowser(String browser ){
		this.browser=browser;
	}
	public void setUser_agent(String user_agent ){
		this.user_agent=user_agent;
	}
	public void setReferer(String referer ){
		this.referer=referer;
	}
	public void setLanguage(String language ){
		this.language=language;
	}
	public void setIs_search_engine(Integer is_search_engine ){
		this.is_search_engine=is_search_engine;
	}
	public void setUser(String user ){
		this.user=user;
	}
}
