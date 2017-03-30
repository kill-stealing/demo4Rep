 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_nbs_newsletters {

	private Integer queued ;
	private String status ;
	private Integer send_on ;
	private java.sql.Timestamp last_sent ;
	private Integer click ;
	private java.sql.Timestamp date_created ;
	private Integer ab_id ;
	private String label ;
	private String params ;
	private Integer id ;
	private Integer subscribers_cnt ;
	private Integer open ;
	private Integer oid ;
	private String sort_order ;
	private Integer uniq_open ;
	private Integer sent_cnt ;


	public Integer getQueued (){
		return queued;
	}
	public String getStatus (){
		return status;
	}
	public Integer getSend_on (){
		return send_on;
	}
	public java.sql.Timestamp getLast_sent (){
		return last_sent;
	}
	public Integer getClick (){
		return click;
	}
	public java.sql.Timestamp getDate_created (){
		return date_created;
	}
	public Integer getAb_id (){
		return ab_id;
	}
	public String getLabel (){
		return label;
	}
	public String getParams (){
		return params;
	}
	public Integer getId (){
		return id;
	}
	public Integer getSubscribers_cnt (){
		return subscribers_cnt;
	}
	public Integer getOpen (){
		return open;
	}
	public Integer getOid (){
		return oid;
	}
	public String getSort_order (){
		return sort_order;
	}
	public Integer getUniq_open (){
		return uniq_open;
	}
	public Integer getSent_cnt (){
		return sent_cnt;
	}
	public void setQueued(Integer queued ){
		this.queued=queued;
	}
	public void setStatus(String status ){
		this.status=status;
	}
	public void setSend_on(Integer send_on ){
		this.send_on=send_on;
	}
	public void setLast_sent(java.sql.Timestamp last_sent ){
		this.last_sent=last_sent;
	}
	public void setClick(Integer click ){
		this.click=click;
	}
	public void setDate_created(java.sql.Timestamp date_created ){
		this.date_created=date_created;
	}
	public void setAb_id(Integer ab_id ){
		this.ab_id=ab_id;
	}
	public void setLabel(String label ){
		this.label=label;
	}
	public void setParams(String params ){
		this.params=params;
	}
	public void setId(Integer id ){
		this.id=id;
	}
	public void setSubscribers_cnt(Integer subscribers_cnt ){
		this.subscribers_cnt=subscribers_cnt;
	}
	public void setOpen(Integer open ){
		this.open=open;
	}
	public void setOid(Integer oid ){
		this.oid=oid;
	}
	public void setSort_order(String sort_order ){
		this.sort_order=sort_order;
	}
	public void setUniq_open(Integer uniq_open ){
		this.uniq_open=uniq_open;
	}
	public void setSent_cnt(Integer sent_cnt ){
		this.sent_cnt=sent_cnt;
	}
}
