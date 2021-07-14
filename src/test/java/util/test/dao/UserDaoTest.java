package util.test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netnoss.www.dao.UserDao;
import com.netnoss.www.entity.AdminInfo;
import com.netnoss.www.entity.AdminRoleCondition;
import com.netnoss.www.entity.AdminRoleDetailInfo;

/**
 * userDao Test
 * @author kevin
 *
 */
public class UserDaoTest {
	private UserDao userDao;
	@Before
	public void text(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("config/spring-mvc.xml");
		 userDao=ac.getBean("userDao",UserDao.class);
	}
	@Test
	public void findByNameTest(){
		String adminCode="admin";
		AdminInfo  adminInfo=userDao.findByName(adminCode);
		System.out.println(adminInfo);
	}
	
}
