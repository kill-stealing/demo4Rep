 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_term_taxonomy {

	private String term_taxonomy_id ;
	private Long count ;
	private String description ;
	private String term_id ;
	private String parent ;
	private String taxonomy ;


	public String getTerm_taxonomy_id (){
		return term_taxonomy_id;
	}
	public Long getCount (){
		return count;
	}
	public String getDescription (){
		return description;
	}
	public String getTerm_id (){
		return term_id;
	}
	public String getParent (){
		return parent;
	}
	public String getTaxonomy (){
		return taxonomy;
	}
	public void setTerm_taxonomy_id(String term_taxonomy_id ){
		this.term_taxonomy_id=term_taxonomy_id;
	}
	public void setCount(Long count ){
		this.count=count;
	}
	public void setDescription(String description ){
		this.description=description;
	}
	public void setTerm_id(String term_id ){
		this.term_id=term_id;
	}
	public void setParent(String parent ){
		this.parent=parent;
	}
	public void setTaxonomy(String taxonomy ){
		this.taxonomy=taxonomy;
	}
}
