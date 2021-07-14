package util.test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netnoss.www.dao.AdminDao;
import com.netnoss.www.dao.RoleDao;
import com.netnoss.www.entity.AdminInfo;
import com.netnoss.www.entity.AdminRoleCondition;
import com.netnoss.www.entity.AdminRoleDetailInfo;
import com.netnoss.www.entity.Role;

public class AdminDaoTest {
	private AdminDao adminDao;
	@Before
	public void test(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("config/spring-mvc.xml");
		adminDao=ac.getBean("adminDao",AdminDao.class);
	}
	@Test
	public void findAdminRoleDetailList(){
		AdminRoleCondition condition=new AdminRoleCondition();
		condition.setRoleName(null);
		 List<AdminRoleDetailInfo> detailInfo=adminDao.findAdminRoleListInfo(condition);
		 System.out.println(detailInfo);
	}
	@Test
	public void findAdminRoleDetail(){
		 int adminId=1;
		 AdminRoleDetailInfo detailInfo=adminDao.findAdminRoleInfoById(adminId);
		 System.out.println(detailInfo);
	}
	@Test
	public void addAdminInfoTest(){
		AdminInfo adminInfo=new AdminInfo();
		adminInfo.setAdmin_code("admin1");
		adminInfo.setEmail("renlk84@163.com");
		adminInfo.setEnrolldate("2019-09-18");
		adminInfo.setName("susan");
		adminInfo.setPassword("a1234");
		adminInfo.setTelephone("18610240206");
		adminDao.addAdminInfo(adminInfo);
		System.out.println(adminInfo.getId());
	}
	@Test
	public void findRoleListTest(){
		List<Role> roleList=adminDao.findRoleList();
		 System.out.println(roleList);
	}
}
