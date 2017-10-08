package com.lmy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.lmy.pojo.CustomOrders;
import com.lmy.pojo.Orders;
import com.lmy.pojo.User;

public class UserDaoImpl implements UserDao{
	private SqlSessionFactory sqlSessionFactory;
	
	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory=sqlSessionFactory;
	}

	@Override
	public User findUserById(int id) {
		SqlSession openSession=sqlSessionFactory.openSession();
		User user=openSession.selectOne("test.findUserById",id);
		return user;
	}

	@Override
	public List<User> findUserByUsername(String username) {
		SqlSession openSession=sqlSessionFactory.openSession();
		List<User> list=openSession.selectList("test.findUserByUsername1",username);
		return list;
	}

	@Override
	public List<Map<String, Object>> findUserListHashMap() {
		SqlSession openSession=sqlSessionFactory.openSession();
		List<Map<String, Object>> list=openSession.selectList("test.findUserListHashMap");
		return list;
	}

	@Override
	public List<User> findUserByNameAndSex(User user) {
		SqlSession openSession=sqlSessionFactory.openSession();
		List<User> list=openSession.selectList("test.findUserByNameAndSex",user);
		return list;
	}

	@Override
	public List<User> findUserByNameAndSex1(User user) {
		SqlSession openSession=sqlSessionFactory.openSession();
		List<User> list=openSession.selectList("test.findUserByNameAndSex1",user);
		return list;
	}
	
	@Override
	public List<User> findUserByNameAndSex2(User user) {
		SqlSession openSession=sqlSessionFactory.openSession();
		List<User> list=openSession.selectList("test.findUserByNameAndSex2",user);
		return list;
	}

	@Override
	public List<User> findUserByDynamicCondition(List<Integer> ids) {
		SqlSession openSession=sqlSessionFactory.openSession();
		List<User> list=openSession.selectList("test.findUserByDynamicCondition",ids);
		return list;
	}

	@Override
	public List<CustomOrders> findOrdersAndUsers() {
		SqlSession openSession=sqlSessionFactory.openSession();
		List<CustomOrders> list=openSession.selectList("test.findOrdersAndUsers");
		return list;
	}

	@Override
	public List<Orders> findOrdersAndUsers1() {
		SqlSession openSession=sqlSessionFactory.openSession();
		List<Orders> list=openSession.selectList("test.findOrdersAndUsers1");
		return list;
	}

	@Override
	public List<User> findUserAndOrders() {
		SqlSession openSession=sqlSessionFactory.openSession();
		List<User> list=openSession.selectList("test.findUserAndOrders");
		return list;
	}

}
