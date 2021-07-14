package util.test.service;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netnoss.www.entity.Cost;
import com.netnoss.www.entity.ParamValue;
import com.netnoss.www.service.CostService;
import com.netnoss.www.service.impl.CostServiceImpl;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;
import com.netnoss.www.view.CostListViewModel;

public class CostServiceTest {
	private CostService costService;
	@Before
	public void text(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("config/spring-mvc.xml");
		costService=ac.getBean("costServiceImpl",CostServiceImpl.class);
	}
	@Test
	public void findCostListInfo(){
		ParamValue paramValue=new ParamValue();
		paramValue.setOrderField("id");
		paramValue.setOrderType("DESC");
		paramValue.setPageCount(StatusCode.PAGE_TOTAL);
		paramValue.setPageNum(1);
		ResultData<CostListViewModel> resultData=costService.findCostListInfo(paramValue);
		System.out.println(resultData);
		
	}
	@Test
	public void modifyCostById(){
		int id=4;
		int status=0;
		ResultData<String> resultData=costService.modifyCostStatus(status, id);
		System.out.println(resultData.getMsg());
	}
	@Test
	public void findCostByid(){
		int id=8;
		ResultData<Cost> resultData=costService.findCostByid(id);
		System.out.println(resultData.getData()+";"+resultData.getMsg());
	}
}
