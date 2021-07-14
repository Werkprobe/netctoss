package util.test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netnoss.www.dao.CostDao;
import com.netnoss.www.entity.Cost;
import com.netnoss.www.entity.ParamValue;
import com.netnoss.www.util.StatusCode;

public class CostDaoTest {
	
	private CostDao costDao;
	@Before
	public void test(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("config/spring-mvc.xml");
		costDao=ac.getBean("costDao",CostDao.class);
	}
	@Test
	public void findCostList(){
		ParamValue sortParam=new ParamValue();
		sortParam.setOrderField("id");
		sortParam.setOrderType("desc");
		sortParam.setPageCount(StatusCode.PAGE_TOTAL);
		sortParam.setPageNum(0);
		List<Cost> costs=costDao.findCostList(sortParam);
		System.out.println(costs);
	}
}
