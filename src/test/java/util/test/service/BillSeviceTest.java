package util.test.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netnoss.www.entity.BillDeatil;
import com.netnoss.www.service.BillService;
import com.netnoss.www.service.impl.BillServiceImpl;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.view.BillViewModel;

public class BillSeviceTest {
	private BillService billService;
	@Before
	public void init(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("config/spring-mvc.xml");
		billService=ac.getBean("billServiceImpl",BillServiceImpl.class);
	}
	@Test
	public void test(){
		Map<String,Object> paramMaps=new HashMap<String,Object>();
		//paramMaps.put("idcard_no", "110111234567890981");
		//paramMaps.put("login_name", "liming");
		//paramMaps.put("real_name", "liming");
		paramMaps.put("bill_month","202012");
		paramMaps.put("pageNum", 0);
		paramMaps.put("pageSize", 10);
		ResultData<BillViewModel<BillDeatil>> resultData=billService.findBillInfo(paramMaps);
		System.out.println(resultData);
	}
}
