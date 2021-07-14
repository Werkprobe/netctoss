package util.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netnoss.www.entity.RolePrivilege;
import com.netnoss.www.service.RoleService;
import com.netnoss.www.service.impl.RoleServiceImpl;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.view.RoleListViewModel;


public class RoleServiceTest {
	private RoleService roleService;
	@Before
	public void text(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("config/spring-mvc.xml");
		roleService=ac.getBean("roleServiceImpl",RoleServiceImpl.class);
	}
	@Test
	public void RoleListViewMode(){
		 ResultData<RoleListViewModel> resultData=roleService.findRoleList(0);
		 System.out.println(resultData);
		
	}
	@Test
	public void SaveRoleInfo(){
		String name="klass";
		String gewalt="4,5,6,7";
		 ResultData<String> resultData=roleService.saveRoleInfo(name, gewalt);
		 System.out.println(resultData.getMsg());
	}
}
