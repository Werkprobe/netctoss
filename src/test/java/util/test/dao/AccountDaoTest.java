package util.test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netnoss.www.dao.AccountDao;
import com.netnoss.www.entity.Account;
import com.netnoss.www.entity.AccountCondition;

public class AccountDaoTest {
	private AccountDao accountDao;
	@Before
	public void testDao(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("config/spring-mvc.xml");
		accountDao=ac.getBean("accountDao",AccountDao.class);
	}
	@Test
	public void findAccountTest(){
		AccountCondition accountCondition =new AccountCondition();
		accountCondition.setIdCart("");
		accountCondition.setLoginName("");
		accountCondition.setPageNum(0);
		accountCondition.setPageSize(5);
		accountCondition.setRealName("lili");
		accountCondition.setStatus(0);
		List<Account> accountList=accountDao.findAllAccount(accountCondition);
		int count=accountDao.findAccountpageCount(accountCondition);
		System.out.println(count);
		System.out.println(accountList);
	}
}
