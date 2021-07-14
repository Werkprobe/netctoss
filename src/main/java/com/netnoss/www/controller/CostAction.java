package com.netnoss.www.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netnoss.www.entity.Cost;
import com.netnoss.www.entity.ParamValue;
import com.netnoss.www.service.CostService;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.view.CostListViewModel;

@Controller
@RequestMapping("/const")
public class CostAction {
	@Resource(name="costServiceImpl")
	private CostService costService;
	
	@RequestMapping("/findCostListInfo.do")
	@ResponseBody
	public ResultData<CostListViewModel> findCostListInfo(HttpServletRequest request,HttpServletResponse response){
		String orderField=request.getParameter("orderField");
		String orderType=request.getParameter("orderType");
		String pageNum=request.getParameter("pageIndex");
		ParamValue paramValue=new ParamValue();
		paramValue.setOrderField(orderField);
		paramValue.setOrderType(orderType);
		paramValue.setPageNum(Integer.parseInt(pageNum));
		ResultData<CostListViewModel> resultData=costService.findCostListInfo(paramValue);
		return resultData;
	}
	@RequestMapping("/modifyStatusInfo.do")
	@ResponseBody
	public ResultData<String> modifyCostStausInfo(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		String status=request.getParameter("status");
		ResultData<String> resultData=costService.modifyCostStatus(Integer.parseInt(status), Integer.parseInt(id));
		return resultData;
	}
	@RequestMapping("/delCostInfo.do")
	@ResponseBody
	public ResultData<String> delCostInfo(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		ResultData<String> resultData=costService.delCostInfoById(Integer.parseInt(id));
		return resultData;
	}
	@RequestMapping("/modifyCost.do")
	@ResponseBody
	public ResultData<Cost> getCostInfoDetail(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		ResultData<Cost> resultData=costService.findCostByid(Integer.parseInt(id));
		return resultData;
	}
	@RequestMapping("/updateCostInfo.do")
	@ResponseBody
	public ResultData<String> updateTarifInfo(HttpServletRequest request,HttpServletResponse response){
		Cost cost=new Cost();
		cost.setId(Integer.parseInt(request.getParameter("id")));
		cost.setName(request.getParameter("name"));
		cost.setBase_duration(request.getParameter("base_duration"));
		cost.setBase_cost(request.getParameter("base_cost"));
		cost.setUnit_cost(request.getParameter("unit_cost"));
		cost.setDescr(request.getParameter("descr"));
		cost.setCosttype((char)(Integer.parseInt(request.getParameter("costtype"))+'0'));
		ResultData<String> resultData=costService.modifyTarifInfo(cost);
		return resultData;
	}
	
	@RequestMapping("/addCostInfo.do")
	@ResponseBody
	public ResultData<String> addTarifInfo(HttpServletRequest request,HttpServletResponse response){
		Cost cost=new Cost();
		cost.setName(request.getParameter("name"));
		cost.setStatus((char)(Integer.parseInt(request.getParameter("status"))+'0'));
		cost.setBase_duration(request.getParameter("base_duration"));
		cost.setBase_cost(request.getParameter("base_cost"));
		cost.setUnit_cost(request.getParameter("unit_cost"));
		cost.setDescr(request.getParameter("descr"));
		cost.setCosttype((char)(Integer.parseInt(request.getParameter("costtype"))+'0'));
		ResultData<String> resultData=costService.addTarifInfo(cost);
		return resultData;
	}
	
	@RequestMapping("/findCostDetail.do")
	@ResponseBody
	public ResultData<Cost> findCostDetail(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		ResultData<Cost> resultData=costService.findCostByid(Integer.parseInt(id));
		return resultData;
	}
}
