package com.netnoss.www.view;

import java.util.List;

import com.netnoss.www.entity.Account;
/**
 * 
 * @author kevin
 *
 */
public class AccountViewModel {
	 private List<Account> accountList;
	 private int pageCount;
	
	public List<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	 
	 
}
