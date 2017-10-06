package mybatis;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.lmy.pojo.User;

public class UserTest {
	
	@Test
	public void testFindUserById() throws Exception{
		//通过流将核心配置文件读取进来 
		InputStream inputStream=Resources.getResourceAsStream("SqlMapConfig.xml");
		//通过核心配置文件输入流创建会话工厂
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂创建会话
		SqlSession openSession=factory.openSession();
		//第一个参数：所调用的sql语句= namespace+.sqlId
		//第二个参数：sql的参数
		User user=openSession.selectOne("test.findUserById",1);
		System.out.println(user);
		openSession.close();
	}

	@Test
	public void testFindUserByUsername() throws Exception{
		InputStream is=Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		SqlSession openSession=factory.openSession();
		List<User> list=openSession.selectList("test.findUserByUsername", "%王%");
		System.out.println(list);
	}
	
	@Test
	public void testFindUserByUsername1() throws Exception{
		InputStream is=Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		SqlSession openSession=factory.openSession();
		List<User> list=openSession.selectList("test.findUserByUsername1", "lmy");
	}
	@Test
	public void insertUser() throws Exception{
		User user=new User();
		user.setUsername("lmy");
		user.setBirthday(new Date());
		user.setSex("男");
		user.setAddress("灯塔");
		InputStream is=Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		SqlSession openSession=factory.openSession();
		openSession.insert("test.inserUser", user);
		//提交事务(mybatis会自动开启事务，但是它不知道何时提交，所以需要手动提交事务)
		openSession.commit();
		System.out.println(user.getId());
	}
	
	@Test
	public void insertUser1() throws Exception{
		User user=new User();
		user.setUsername("lmy");
		user.setBirthday(new Date());
		user.setSex("男");
		user.setAddress("灯塔");
		InputStream is=Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		SqlSession openSession=factory.openSession();
		openSession.insert("test.inserUser1", user);
		//提交事务(mybatis会自动开启事务，但是它不知道何时提交，所以需要手动提交事务)
		openSession.commit();
		System.out.println("user.getId() "+user.getId());
	}
	
	@Test
	public void deleteUserById() throws Exception{
		InputStream is=Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		SqlSession openSession=factory.openSession();
		openSession.insert("test.deleteUserById",31);
		//提交事务(mybatis会自动开启事务，但是它不知道何时提交，所以需要手动提交事务)
		openSession.commit();
	}
	
	@Test
	public void updateUserById() throws Exception{
		User user=new User();
		user.setUsername("lmy_new");
		user.setBirthday(new Date());
		user.setSex("男");
		user.setAddress("灯塔");
		user.setId(32);
		InputStream is=Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		SqlSession openSession=factory.openSession();
		openSession.insert("test.updateUserById",user);
		//提交事务(mybatis会自动开启事务，但是它不知道何时提交，所以需要手动提交事务)
		openSession.commit();
	}
}
