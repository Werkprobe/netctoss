package com.netnoss.www.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netnoss.www.dao.AccountDao;
import com.netnoss.www.entity.Account;
import com.netnoss.www.entity.AccountCondition;
import com.netnoss.www.service.AccountService;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;
import com.netnoss.www.view.AccountViewModel;
@Service
public class AccountServiceImpl implements AccountService {
	@Resource(name="accountDao")
	AccountDao accountDao;
	
	@Transactional
	public ResultData<AccountViewModel> findAccountInfo(AccountCondition accountCondition) {
		ResultData<AccountViewModel> resultData=new ResultData<AccountViewModel>();
		accountCondition.setPageNum(accountCondition.getPageNum()*StatusCode.PAGE_TOTAL_5);
		int pageCount=accountDao.findAccountpageCount(accountCondition);
		AccountViewModel accountViewModel=new AccountViewModel();
		if(pageCount%StatusCode.PAGE_TOTAL_5!=0){
			accountViewModel.setPageCount(pageCount/StatusCode.PAGE_TOTAL_5+1);
		}else{
			accountViewModel.setPageCount(pageCount/StatusCode.PAGE_TOTAL_5);
		}
		if(pageCount==0){
			resultData.setStatus(StatusCode.FAIL_RESULT_EMPYT);
			resultData.setMsg("Das Ergebnis ist leer.");
			resultData.setData(accountViewModel);
		}else{
			List<Account> accountList=accountDao.findAllAccount(accountCondition);
			accountViewModel.setAccountList(accountList);
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("Das ist erfolgreich.");
			resultData.setData(accountViewModel);
		}
		return resultData;
	}
	@Transactional
	public ResultData<String> insertNewAccount(Account account) {
		ResultData<String> resultData=new ResultData<String>();
		int isExtName=accountDao.isExistloginName(account.getLogin_name());
		if(1<=isExtName){
			resultData.setStatus(StatusCode.FAIL);
			resultData.setMsg("Account hat shon existiert.");
			resultData.setData(null);
			return resultData;
		}
		int isExtCardNo=accountDao.isExistIdCardNo(account.getIdcard_no());
		if(1<=isExtCardNo){
			resultData.setStatus(StatusCode.FAIL);
			resultData.setMsg("CardNo hat shon benutzt.");
			resultData.setData(null);
			return resultData;
		}
		account.setStatus(1);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String create_date=sdf.format(new Date());
		account.setCreate_date(create_date);
		accountDao.addNewAccount(account);
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("Account wurden erfolgreich hinzugefügt.");
		resultData.setData(null);
		return resultData;
	}
	@Transactional
	public ResultData<Account> findAccountInfoById(int id) {
		ResultData<Account> resultData=new ResultData<Account>();
		Account account=accountDao.findAccountById(id);
		if(null!=account){
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("Es ist erfolgreich");
			resultData.setData(account);
		}else{
			resultData.setStatus(StatusCode.FAIL);
			resultData.setMsg("account_list.html");
			resultData.setData(null);
		}
		return resultData;
	}
	@Transactional
	public ResultData<String> modifyAccountStatusByid(int status, int id) {
		ResultData<String> resultData=new ResultData<String>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String processTime=sdf.format(new Date());
		accountDao.updateAccountStatus(status,processTime, id);
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("der Zustand des Kontos wurde Erfolgreich geändert");
		resultData.setData("Success");
		return resultData;
	}
	@Transactional
	public ResultData<String> modifyAccountInfoById(Account account) {
		ResultData<String> resultData=new ResultData<String>();
		accountDao.updateAccountInfo(account);
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("Die Information des Account wird Erfolgreich geändert");
		resultData.setData(null);
		return resultData;
	}
	
}
