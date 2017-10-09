package com.lmy.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lmy.mapper.UserMapper;
import com.lmy.pojo.User;
import com.lmy.pojo.UserExample;
import com.lmy.pojo.UserExample.Criteria;

public class UserMapperTest {

	private ApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
	}

	@Test
	public void testFindUserById() throws Exception {
//		UserMapper userMapper=(UserMapper) applicationContext.getBean("userMapper");
		UserMapper userMapper=(UserMapper) applicationContext.getBean(UserMapper.class);
		User user = userMapper.selectByPrimaryKey(1);
		System.out.println(user);
	}
	
	@Test
	public void testFindUserByNameAndSex() throws Exception{
		UserMapper userMapper=(UserMapper) applicationContext.getBean(UserMapper.class);
		UserExample example=new UserExample();
		Criteria criteria=example.createCriteria();
		criteria.andUsernameLike("%l%");
		criteria.andSexEqualTo("男");
		List<User> list=userMapper.selectByExample(example);
		System.out.println(list);
	}
}
