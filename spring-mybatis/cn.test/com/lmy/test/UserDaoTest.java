package com.lmy.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lmy.dao.UserDao;
import com.lmy.pojo.User;

public class UserDaoTest {
	
	private ApplicationContext applicationContext;
	
	@Before
	public void setUpFactory() throws Exception{
		applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void findUserById() throws Exception{
		UserDao userDao=(UserDao) applicationContext.getBean("userDao");
		User user=userDao.findUserById(32);
		System.out.println(user);
	}
	
	@Test
	public void findUserByUsername() throws Exception{
		UserDao userDao=(UserDao) applicationContext.getBean("userDao");
		List<User> list=userDao.findUserByUsername("王");
		System.out.println(list);
	}
}
