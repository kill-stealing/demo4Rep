package com.jsp;

import java.io.Serializable;

public class CounterBean implements Serializable{
	int count=0;
	
	public CounterBean() {
		// TODO Auto-generated constructor stub
	}
	
	public int getCounter(){
		count++;
		return count;
	}
	
	public void setCount(int c){
		count=c;
	}
}
