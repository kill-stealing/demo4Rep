 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_term_relationships {

	private String term_taxonomy_id ;
	private String object_id ;
	private Integer term_order ;


	public String getTerm_taxonomy_id (){
		return term_taxonomy_id;
	}
	public String getObject_id (){
		return object_id;
	}
	public Integer getTerm_order (){
		return term_order;
	}
	public void setTerm_taxonomy_id(String term_taxonomy_id ){
		this.term_taxonomy_id=term_taxonomy_id;
	}
	public void setObject_id(String object_id ){
		this.object_id=object_id;
	}
	public void setTerm_order(Integer term_order ){
		this.term_order=term_order;
	}
}
