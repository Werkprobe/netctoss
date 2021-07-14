package util.test.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netnoss.www.dao.AccountDao;
import com.netnoss.www.entity.Account;
import com.netnoss.www.service.AccountService;
import com.netnoss.www.service.impl.AccountServiceImpl;
import com.netnoss.www.util.ResultData;

public class AccountServiceTest {
	private AccountService accountService;
	@Before
	public void text(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("config/spring-mvc.xml");
		accountService=ac.getBean("accountServiceImpl",AccountServiceImpl.class);
	}
	@Test
	public void addAccountTest(){
		Account account=new Account();
		account.setReal_name("limi");
		account.setLogin_name("limi");
		account.setLogin_passwd("123456");
		account.setIdcard_no("130825198408051517");
		account.setCreate_date("2019-11-18");
		account.setMailaddress("Bohnenberg Str.11 In Hebei  ");
		account.setStatus(1);
		account.setGender(1);
		account.setBirthdate("1984-08-05");
		account.setTelephone("13426481516");
		account.setZipcode("068153");
		ResultData<String> result=accountService.insertNewAccount(account);
		System.out.println(result.getMsg());
	}
	
}
