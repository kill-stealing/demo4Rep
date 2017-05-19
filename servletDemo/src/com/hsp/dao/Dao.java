package com.hsp.dao;

import com.hsp.entity.User;

public interface Dao {
	User getUser(User user);
	
	int ifExit(User user);
}
