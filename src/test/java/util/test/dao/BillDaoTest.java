package util.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netnoss.www.dao.BillDao;
import com.netnoss.www.entity.BillDeatil;

public class BillDaoTest {
	private BillDao billDao;
	@Before
	public void init(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("config/spring-mvc.xml");
		billDao=ac.getBean("billDao",BillDao.class);
	}
	@Test
	public void test(){
		Map<String,Object> paramMaps=new HashMap<String,Object>();
		paramMaps.put("idcard_no", "110111234567890981");
		paramMaps.put("login_name", "liming");
		paramMaps.put("real_name", "liming");
		paramMaps.put("bill_month","201201");
		paramMaps.put("pageNum", 0);
		paramMaps.put("pageSize", 10);
		List<BillDeatil> billInfo=billDao.findBillInfo(paramMaps);
		System.out.println(billDao);
		System.out.println(billInfo);
	}
}
