package util.test.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netnoss.www.entity.ServiceCondition;
import com.netnoss.www.service.NetCService;
import com.netnoss.www.service.impl.NetCServiceImpl;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;
import com.netnoss.www.view.NetcServiceViewModel;

public class ServiceTest {
	private NetCService netcService;
	@Before
	public void init(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("config/spring-mvc.xml");
		netcService=ac.getBean("netCServiceImpl",NetCServiceImpl.class);
	}
	@Test
	public void test(){
		ServiceCondition serviceCondition=new ServiceCondition();
		serviceCondition.setPageNum(Integer.parseInt("1"));
		serviceCondition.setPageSize(StatusCode.PAGE_TOTAL_5);
		ResultData<NetcServiceViewModel> resultData=netcService.findServiceList(serviceCondition);
		System.out.println(resultData);
	}
}
