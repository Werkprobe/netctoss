package com.netnoss.www.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;

@Controller
@RequestMapping(value="/loginCheck")
public class LoadCheckAction {
	@RequestMapping("/loadPage.do")
	@ResponseBody
	public ResultData<String> loadPage(HttpServletRequest request,HttpServletResponse response){
		ResultData<String> resultData=new ResultData<String>();
		String url=request.getParameter("url");
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("Sie erlauben system");
		resultData.setData(url);
		return resultData;
	}
	@RequestMapping("/loginOut.do")
	@ResponseBody
	public ResultData<String> quitSystem(HttpServletRequest request){
		String username=(String)request.getSession().getAttribute("admin_code");
		request.getSession().removeAttribute("admin_code");
		request.getSession().removeAttribute(username);
		ResultData<String> resultData=new ResultData<String>();
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("Exit system");
		resultData.setData("");
		return resultData;
	}
}
