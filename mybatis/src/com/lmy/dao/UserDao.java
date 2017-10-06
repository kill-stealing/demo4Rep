package com.lmy.dao;

import java.util.List;

import com.lmy.pojo.User;


public interface UserDao {
	User findUserById(int id);
	List<User> findUserByUsername(String username);
}
