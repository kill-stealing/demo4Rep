package mybatis;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.lmy.dao.UserDao;
import com.lmy.dao.UserDaoImpl;
import com.lmy.pojo.CustomOrders;
import com.lmy.pojo.Orders;
import com.lmy.pojo.User;

public class UserDaoTest {
	
	private SqlSessionFactory factory;
	
	@Before
	public void setUpFactory() throws Exception{
		InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
		factory=new SqlSessionFactoryBuilder().build(is);
	}
	
	@Test
	public void findUserById() throws Exception{
		UserDao userDao=new UserDaoImpl(factory);
		User user=userDao.findUserById(32);
		System.out.println(user);
	}
	
	@Test
	public void findUserByUsername() throws Exception{
		UserDao userDao=new UserDaoImpl(factory);
		List<User> list=userDao.findUserByUsername("王");
		System.out.println(list);
	}
	
	@Test
	public void findUserListHashMap() throws Exception{
		UserDao userDao=new UserDaoImpl(factory);
		List<Map<String, Object>> list=userDao.findUserListHashMap();
		System.out.println(list);
	}
	
	@Test
	public void findUserByNameAndSex() throws Exception{
		UserDao userDao=new UserDaoImpl(factory);
		User user=new User();
		user.setUsername("王");
		user.setSex("2");
		List<User> list=userDao.findUserByNameAndSex(user);
		System.out.println(list);
	}
	
	@Test
	public void findUserByNameAndSex1() throws Exception{
		UserDao userDao=new UserDaoImpl(factory);
		User user=new User();
		//user.setUsername("王");
		user.setSex("1");
		List<User> list=userDao.findUserByNameAndSex1(user);
		System.out.println(list);
	}
	
	@Test
	public void findUserByNameAndSex2() throws Exception{
		UserDao userDao=new UserDaoImpl(factory);
		User user=new User();
		//user.setUsername("王");
		user.setSex("1");
		List<User> list=userDao.findUserByNameAndSex2(user);
		System.out.println(list);
	}
	
	@Test
	public void findUserByDynamicCondition() throws Exception{
		UserDao userDao=new UserDaoImpl(factory);
		List<Integer> ids=new ArrayList<Integer>();
		ids.add(1);
		ids.add(10);
		ids.add(16);
		ids.add(22);
		
		List<User> list1=userDao.findUserByDynamicCondition(ids);
		System.out.println(list1);
	}
	
	@Test
	public void findOrdersAndUsers() throws Exception{
		UserDao userDao=new UserDaoImpl(factory);
		List<CustomOrders> list=userDao.findOrdersAndUsers();
		System.out.println(list);
	}
	
	@Test
	public void findOrdersAndUsers1() throws Exception{
		UserDao userDao=new UserDaoImpl(factory);
		List<Orders> list=userDao.findOrdersAndUsers1();
		System.out.println(list);
	}
	
	@Test
	public void findUserAndOrders() throws Exception{
		UserDao userDao=new UserDaoImpl(factory);
		List<User> list=userDao.findUserAndOrders();
		System.out.println(list);
	}
}
