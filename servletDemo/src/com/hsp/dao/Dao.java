package com.hsp.dao;

import java.util.List;

import com.hsp.entity.User;

public interface Dao {
	User getUser(User user);
	
	List<User> getUser();
	
	int ifExit(User user);
	
	List<User> getUser(int pageNum,int pageSize);
}
