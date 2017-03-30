 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_newsletter_sent {

	private String open ;
	private String time ;
	private String error ;
	private String status ;
	private String user_id ;
	private String email_id ;
	private String ip ;


	public String getOpen (){
		return open;
	}
	public String getTime (){
		return time;
	}
	public String getError (){
		return error;
	}
	public String getStatus (){
		return status;
	}
	public String getUser_id (){
		return user_id;
	}
	public String getEmail_id (){
		return email_id;
	}
	public String getIp (){
		return ip;
	}
	public void setOpen(String open ){
		this.open=open;
	}
	public void setTime(String time ){
		this.time=time;
	}
	public void setError(String error ){
		this.error=error;
	}
	public void setStatus(String status ){
		this.status=status;
	}
	public void setUser_id(String user_id ){
		this.user_id=user_id;
	}
	public void setEmail_id(String email_id ){
		this.email_id=email_id;
	}
	public void setIp(String ip ){
		this.ip=ip;
	}
}
