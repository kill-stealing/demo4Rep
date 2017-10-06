package mybatis;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.lmy.dao.UserDao;
import com.lmy.dao.UserDaoImpl;
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
}
