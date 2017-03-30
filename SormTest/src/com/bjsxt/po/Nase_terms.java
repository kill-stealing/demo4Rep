 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_terms {

	private String name ;
	private String term_id ;
	private String slug ;
	private Long term_group ;


	public String getName (){
		return name;
	}
	public String getTerm_id (){
		return term_id;
	}
	public String getSlug (){
		return slug;
	}
	public Long getTerm_group (){
		return term_group;
	}
	public void setName(String name ){
		this.name=name;
	}
	public void setTerm_id(String term_id ){
		this.term_id=term_id;
	}
	public void setSlug(String slug ){
		this.slug=slug;
	}
	public void setTerm_group(Long term_group ){
		this.term_group=term_group;
	}
}
