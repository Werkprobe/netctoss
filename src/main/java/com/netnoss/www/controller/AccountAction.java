package com.netnoss.www.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netnoss.www.entity.Account;
import com.netnoss.www.entity.AccountCondition;
import com.netnoss.www.service.AccountService;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;
import com.netnoss.www.view.AccountViewModel;

@Controller
@RequestMapping("/account")
public class AccountAction  {
	@Resource(name="accountServiceImpl")
	private AccountService accuontService;
	
	@RequestMapping("/findAccount.do")
	@ResponseBody
	public ResultData<AccountViewModel> findAccount(HttpServletRequest request,HttpServletResponse response){
		AccountCondition accountCondition=new AccountCondition();
		accountCondition.setIdCart(request.getParameter("idCart"));
		accountCondition.setLoginName(request.getParameter("loginName"));
		accountCondition.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
		accountCondition.setRealName(request.getParameter("realName"));
		String status=request.getParameter("status").toString();
		if(""!=status && null!=status){
		   accountCondition.setStatus(Integer.parseInt(request.getParameter("status")));
		}
		accountCondition.setPageSize(StatusCode.PAGE_TOTAL_5);
		ResultData<AccountViewModel> resultData=accuontService.findAccountInfo(accountCondition);
		return resultData;
	}
	@RequestMapping("/modifyStatusInfo.do")
	@ResponseBody
	public ResultData<String> modifyStatusInfo(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		String status=request.getParameter("status");
		ResultData<String> resultData=accuontService.modifyAccountStatusByid(Integer.parseInt(status), Integer.parseInt(id));
		return resultData;
	}
	
	@RequestMapping("/findAccountDetail.do")
	@ResponseBody
	public ResultData<Account> findAccountDetail(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		ResultData<Account> resultData=accuontService.findAccountInfoById(Integer.parseInt(id));
		return resultData;
	}
	@RequestMapping("/modifyAccountInfo.do")
	@ResponseBody
	public ResultData<String> modifyAccountInfo(HttpServletRequest request,HttpServletResponse response){
		Account account=new Account();
		account.setId(Integer.parseInt(request.getParameter("id")));
		account.setReal_name(request.getParameter("real_name"));
		account.setLogin_passwd(request.getParameter("login_passwd"));
		account.setTelephone(request.getParameter("telephone"));
		account.setMailaddress(request.getParameter("mailaddress"));
		account.setZipcode(request.getParameter("zipcode"));
		account.setGender(Integer.parseInt(request.getParameter("gender")));
		ResultData<String> resultData=accuontService.modifyAccountInfoById(account);
		return resultData;
	}
	@RequestMapping("/addAccount.do")
	@ResponseBody
	public ResultData<String> addAccount(HttpServletRequest request,HttpServletResponse response){
		Account account=new Account();
		account.setIdcard_no(request.getParameter("idcard_no"));
		account.setLogin_name(request.getParameter("login_name"));
		account.setReal_name(request.getParameter("real_name"));
		account.setLogin_passwd(request.getParameter("login_passwd"));
		account.setTelephone(request.getParameter("telephone"));
		account.setMailaddress(request.getParameter("mailaddress"));
		account.setZipcode(request.getParameter("zipcode"));
		account.setGender(Integer.parseInt(request.getParameter("gender")));
		ResultData<String> resultData=accuontService.insertNewAccount(account);
		return resultData;
	}
}
