package com.netnoss.www.dao;

import java.util.List;

import com.netnoss.www.entity.Account;
import com.netnoss.www.entity.AccountCondition;

public interface AccountDao {
	/**
	 * find Account
	 * @param idCard
	 * @param realName
	 * @param loginName
	 * @return List<Account>
	 */
   public List<Account> findAllAccount(AccountCondition accountCondition);
   /**
    * find pagecount for account
    * @param accountCondition
    * @return int
    */
   public int findAccountpageCount(AccountCondition accountCondition);
   
   /**
    * add new Account
    * @param account
    */
   public void addNewAccount(Account account);
   /**
    * login_name ist Exist
    * @param condition
    * @return int 1 true oder 0 false
    */
   public int isExistloginName(String condition);
   /**
    * login_name ist Exist
    * @param condition
    * @return int 1 true oder 0 false
    */
   public int isExistIdCardNo(String condition);
   /**
    * modify Account Status
    * @param status
    */
   public void updateAccountStatus(int status,String processTime,int id);
   
   /**
    * find Account by Id
    * @param id
    * @return Account
    */
   public Account findAccountById(int id);
   /**
    *  modify Account Information
    * @param account
    */
   public void updateAccountInfo(Account account);
   /**
    * find Account by id_Card
    * @param idcard_no
    * @return Account
    */
   public Account findAccountByidCard(String idcard_no);
}
