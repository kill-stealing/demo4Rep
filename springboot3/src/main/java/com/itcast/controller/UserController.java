package com.itcast.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itcast.domain.User;
import com.itcast.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/hello")
	public String hello(){
		return "Hello World";
	}
	
	@RequestMapping("/user")
	public User showUser(){
		User user =new User();
		user.setId(1);
		user.setUsername("老王");
		user.setBirthday(new Date());
		user.setSex("男");
		user.setAddress("中国");
		return user;
	}
	
	@RequestMapping("maps")
	public Map<String, Object> showMaps(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", 1);
		map.put("name", "张无忌");
		return map;
	}
	
	@RequestMapping("/list")
	public List<User> showList(){
		List<User> list=new ArrayList<>();
		User user =new User();
		user.setId(1);
		user.setUsername("老王");
		user.setBirthday(new Date());
		user.setSex("男");
		user.setAddress("中国");
		
		User user1 =new User();
		user1.setId(2);
		user1.setUsername("赵丽颖");
		user1.setBirthday(new Date());
		user1.setSex("女");
		user1.setAddress("美国");
		list.add(user);
		list.add(user1);
		return list;
	}
	
	@RequestMapping("/findAll")
	public List<User> findAll(){
		return userService.findAll();
	}
}
