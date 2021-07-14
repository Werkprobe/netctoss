package util.test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netnoss.www.dao.ServiceDao;
import com.netnoss.www.entity.ServiceCondition;
import com.netnoss.www.entity.ServiceDetailInfo;
import com.netnoss.www.util.StatusCode;

public class ServiceDaoTest {
	private ServiceDao serviceDao;
	@Before
	public void init(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("config/spring-mvc.xml");
		serviceDao=ac.getBean("serviceDao",ServiceDao.class);
	}
	@Test
	public void test(){
		ServiceCondition sc=new ServiceCondition();
		sc.setPageNum(0*StatusCode.PAGE_TOTAL);
		sc.setPageSize(StatusCode.PAGE_TOTAL);
		sc.setAusWeise("410381194302256528");
		sc.setOsUsername("login1");
		sc.setStatus(1);
		sc.setServiceIp("123");
		List<ServiceDetailInfo>  findAllDao=serviceDao.findAllService(sc);
		System.out.println(findAllDao);
	}
}
