 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_advps_optionset {

	private String content ;
	private String slider ;
	private String template ;
	private Integer id ;
	private String caro_ticker ;
	private String navigation ;
	private String container ;
	private String query ;
	private String plist ;


	public String getContent (){
		return content;
	}
	public String getSlider (){
		return slider;
	}
	public String getTemplate (){
		return template;
	}
	public Integer getId (){
		return id;
	}
	public String getCaro_ticker (){
		return caro_ticker;
	}
	public String getNavigation (){
		return navigation;
	}
	public String getContainer (){
		return container;
	}
	public String getQuery (){
		return query;
	}
	public String getPlist (){
		return plist;
	}
	public void setContent(String content ){
		this.content=content;
	}
	public void setSlider(String slider ){
		this.slider=slider;
	}
	public void setTemplate(String template ){
		this.template=template;
	}
	public void setId(Integer id ){
		this.id=id;
	}
	public void setCaro_ticker(String caro_ticker ){
		this.caro_ticker=caro_ticker;
	}
	public void setNavigation(String navigation ){
		this.navigation=navigation;
	}
	public void setContainer(String container ){
		this.container=container;
	}
	public void setQuery(String query ){
		this.query=query;
	}
	public void setPlist(String plist ){
		this.plist=plist;
	}
}
