package com.lmy.dao;

import java.util.List;
import java.util.Map;

import com.lmy.pojo.CustomOrders;
import com.lmy.pojo.Orders;
import com.lmy.pojo.User;


public interface UserDao {
	User findUserById(int id);
	List<User> findUserByUsername(String username);
	
	List<Map<String, Object>> findUserListHashMap();
	
	List<User> findUserByNameAndSex(User user);
	
	List<User> findUserByNameAndSex1(User user);
	
	List<User> findUserByNameAndSex2(User user);
	
	List<User> findUserByDynamicCondition(List<Integer> ids);
	
	List<CustomOrders> findOrdersAndUsers();
	
	List<Orders> findOrdersAndUsers1();
	
	List<User> findUserAndOrders();
}
