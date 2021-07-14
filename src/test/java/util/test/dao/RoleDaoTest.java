package util.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netnoss.www.dao.RoleDao;
import com.netnoss.www.entity.Role;
import com.netnoss.www.entity.RoleInfo;
import com.netnoss.www.entity.RolePrivilege;

public class RoleDaoTest {
	private RoleDao roleDao;
	@Before
	public void text(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("config/spring-mvc.xml");
		roleDao=ac.getBean("roleDao",RoleDao.class);
	}
	@Test
	public void findByRoleCountTest(){
		int count=roleDao.findRoleCount();
		System.out.println(count);
	}
	@Test
	public void findByRoleListTest(){
		List<RoleInfo> roleList=roleDao.findRoleList(0, 10);
		System.out.println(roleList);
	}
	@Test
	public void saveRoleListTest(){
		List<RolePrivilege> rolePrivilegeList=new ArrayList<RolePrivilege>();
		RolePrivilege rolePrivilege=new RolePrivilege();
		rolePrivilege.setRoleId(2);
		rolePrivilege.setPrivilegeId(2);
		rolePrivilegeList.add(rolePrivilege);
		rolePrivilege=new RolePrivilege();
		rolePrivilege.setRoleId(2);
		rolePrivilege.setPrivilegeId(3);
		rolePrivilegeList.add(rolePrivilege);
		rolePrivilege=new RolePrivilege();
		rolePrivilege.setRoleId(2);
		rolePrivilege.setPrivilegeId(4);
		rolePrivilegeList.add(rolePrivilege);
		roleDao.saveRoleGewalt(rolePrivilegeList);
		System.out.println("insert success");
	}
	@Test
	public void saveRoleTest(){
		String name="klass";
		Role role=new Role();
		role.setName("klass");
		roleDao.saveRoleInfo(role);
		System.out.println(role.getId());
	}
	
	@Test
	public void findRoleTest(){
	    String name="admin";
		Role role=roleDao.findRoleByid(name);
		System.out.println(role);
	}
	@Test
	public void findRoleByAdminCode(){
	    int id=18;
	    RoleInfo role=roleDao.findRoleInfoById(id);
		System.out.println(role);
	}
}
