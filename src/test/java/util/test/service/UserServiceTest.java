package util.test.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netnoss.www.dao.UserDao;
import com.netnoss.www.entity.AdminInfo;
import com.netnoss.www.service.UserService;
import com.netnoss.www.service.impl.UserServiceImpl;
import com.netnoss.www.util.ResultData;

/**
 * test userServlce
 * @author kevin
 *
 */
public class UserServiceTest {
	private UserService userService;
	@Before
	public void text(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("config/spring-mvc.xml");
		userService=ac.getBean("userServiceImpl",UserServiceImpl.class);
	}
	@Test
	public void getUserInfoTest(){
		String adminCode="admin";
		String password="admin";
		String checkCode="123";
		String checkCodeSession="123" ;
		ResultData  resultData=userService.checkLogin(adminCode,password,checkCode,checkCodeSession);
		System.out.println(resultData);
	}
}
