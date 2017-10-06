package mybatis;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.lmy.mapper.UserMapper;
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
}
