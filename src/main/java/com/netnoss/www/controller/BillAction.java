package com.netnoss.www.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netnoss.www.entity.BillDeatil;
import com.netnoss.www.entity.BillDetailMonth;
import com.netnoss.www.service.BillService;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.view.BillViewModel;

@Controller
@RequestMapping("/bill")
public class BillAction {
	@Resource(name="billServiceImpl")
	private BillService billService;
	
	@RequestMapping("/findBillList.do")
	@ResponseBody
	public ResultData<BillViewModel<BillDeatil>> findBillList(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> paramMaps=new HashMap<String,Object>();
		paramMaps.put("idcard_no", request.getParameter("idcard_no"));
		paramMaps.put("login_name",request.getParameter("login_name"));
		paramMaps.put("real_name", request.getParameter("real_name"));
		paramMaps.put("bill_month",request.getParameter("bill_month"));
		paramMaps.put("pageNum", Integer.parseInt(request.getParameter("pageNum")));
		ResultData<BillViewModel<BillDeatil>>  resultData=billService.findBillInfo(paramMaps);
		return resultData;
	}
	@RequestMapping("/findBillDetailMonth")
	@ResponseBody
	public ResultData<BillViewModel<BillDetailMonth>> findBillDetailMonth(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> paramMaps=new HashMap<String,Object>();
		paramMaps.put("bill_id",Integer.valueOf(request.getParameter("bill_id")));
		paramMaps.put("month_id",request.getParameter("month_id"));
		paramMaps.put("pageNum", Integer.parseInt(request.getParameter("pageNum")));
		ResultData<BillViewModel<BillDetailMonth>> resultData=billService.findBillDetail(paramMaps);
		return resultData;
	}
}
