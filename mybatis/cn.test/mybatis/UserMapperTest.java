package mybatis;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.lmy.mapper.UserMapper;
import com.lmy.pojo.QueryVo;
import com.lmy.pojo.User;

public class UserMapperTest {
	private SqlSessionFactory factory;
	
	@Before
	public void setUpSqlSessionFactory() throws Exception{
		InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
		factory=new SqlSessionFactoryBuilder().build(is);
	}
	
	@Test
	public void testFindUserById(){
		SqlSession openSession=factory.openSession();
		UserMapper userMapper=openSession.getMapper(UserMapper.class);
		User user=userMapper.findUserById(1);
		System.out.println(user);
	}
	
	@Test
	public void testFindUserByVo(){
		SqlSession openSession=factory.openSession();
		QueryVo queryVo=new QueryVo();
		User user=new User();
		user.setSex("2");
		user.setUsername("王");
		queryVo.setUser(user);
		UserMapper userMapper=openSession.getMapper(UserMapper.class);
		List<User> list=userMapper.findUserByVo(queryVo);
		System.out.println(list);
	}
}
