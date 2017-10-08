package com.lmy.dao;

import java.util.List;
import java.util.Map;

import com.lmy.pojo.CustomOrders;
import com.lmy.pojo.Orders;
import com.lmy.pojo.User;


public interface UserDao {
	User findUserById(int id);
	List<User> findUserByUsername(String username);
}
