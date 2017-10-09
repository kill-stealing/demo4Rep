package com.lmy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.lmy.pojo.CustomOrders;
import com.lmy.pojo.Orders;
import com.lmy.pojo.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao{

	@Override
	public User findUserById(int id) {
		SqlSession openSession=this.getSqlSession();
		User user=openSession.selectOne("test.findUserById",id);
		return user;
	}

	@Override
	public List<User> findUserByUsername(String username) {
		SqlSession openSession=this.getSqlSession();
		List<User> list=openSession.selectList("test.findUserByUsername1",username);
		return list;
	}

}
