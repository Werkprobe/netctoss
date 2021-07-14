package com.netnoss.www.service;

import com.netnoss.www.entity.Account;
import com.netnoss.www.entity.AccountCondition;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.view.AccountViewModel;

public interface AccountService {
	/**
	 * findAccount
	 * @param accountCondition
	 * @return ResultData<Account>
	 */
	public ResultData<AccountViewModel> findAccountInfo(AccountCondition accountCondition);
	/**
	 * add new Account service
	 * @param account
	 * @return ResultData<String>
	 */
	public ResultData<String> insertNewAccount(Account account);
	/**
	 * name ist Exist for Login
	 * @param loginName
	 * @return ResultData<String>
	 */
	//public ResultData<String> isExistLoginName(String loginName);
	/**
	 * cardNo ist exist
	 * @param cardNo
	 * @return ResultData<String>
	 */
	//public ResultData<String> isExistIdCardNo(String cardNo);
	/**
	 * update Account StatusInfo by id
	 * @param status
	 * @param id
	 * @return ResultData<String>
	 */
	public  ResultData<String> modifyAccountStatusByid(int status,int id);
	/**
	 * find AccountInf by id
	 * @param id
	 * @return ResultData<Account>
	 */
	public  ResultData<Account> findAccountInfoById(int id);
	/**
	 * update Account info by id
	 * @param account
	 * @return ResultData<String>
	 */
	public ResultData<String> modifyAccountInfoById(Account account);
}
