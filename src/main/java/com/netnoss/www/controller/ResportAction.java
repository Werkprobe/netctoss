package com.netnoss.www.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netnoss.www.service.ReportService;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.view.ResportViewModel;
@Controller
@RequestMapping("/report")
public class ResportAction {
	@Resource(name="reportServiceImpl")
	private ReportService reportService;
	@RequestMapping("/findReportList.do")
	@ResponseBody
	public ResultData<ResportViewModel> findReportListDeatil(HttpServletRequest request,HttpServletRequest response){
		Map<String, Integer> paramMaps=new HashMap<String,Integer>();
		paramMaps.put("pageNum", Integer.parseInt(request.getParameter("pageNum")));
		ResultData<ResportViewModel> resultData=reportService.findReposList(paramMaps);
		return resultData;
	}
}
