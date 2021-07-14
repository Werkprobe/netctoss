package com.netnoss.www.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netnoss.www.dao.CostDao;
import com.netnoss.www.entity.Cost;
import com.netnoss.www.entity.ParamValue;
import com.netnoss.www.service.CostService;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;
import com.netnoss.www.view.CostListViewModel;

@Service
public class CostServiceImpl implements CostService{
	@Resource(name="costDao")
	private CostDao costDao;
	@Transactional
	public ResultData<CostListViewModel> findCostListInfo(ParamValue paramValue) {
		ResultData<CostListViewModel> resultData=new ResultData<CostListViewModel>();
		paramValue.setPageCount(StatusCode.PAGE_TOTAL_5);
		paramValue.setPageNum(paramValue.getPageNum()*StatusCode.PAGE_TOTAL_5);
		List<Cost> costList=costDao.findCostList(paramValue);
		if(null!=costList && 0!=costList.size()){
			int pageCount=costDao.findCostCount();
			CostListViewModel costListViewModel=new CostListViewModel();
			costListViewModel.setCostList(costList);
			if(pageCount%StatusCode.PAGE_TOTAL_5!=0){
				costListViewModel.setPageCount(pageCount/StatusCode.PAGE_TOTAL_5+1);
			}else{
				costListViewModel.setPageCount(pageCount/StatusCode.PAGE_TOTAL_5);
			}
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("Das ist erfolgreich.");
			resultData.setData(costListViewModel);
		}else{
			resultData.setStatus(StatusCode.FAIL_RESULT_EMPYT);
			resultData.setMsg("es ist Leer.");
			resultData.setData(null);
		}
		return resultData;
	}
	@Transactional
	public ResultData<String> modifyCostStatus(int status, int id) {
		ResultData<String> resultData=new ResultData<String>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime=sdf.format(new Date());
		costDao.updateCostStatus(status,id,startTime);
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("das Tarifpaket des Cost wurde Erfolgreich geändert");
		resultData.setData("Success");
		return resultData;
	}
	@Transactional
	public ResultData<String> delCostInfoById(int id) {
		ResultData<String> resultData=new ResultData<String>();
		costDao.delCostById(id);
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("die Information werde Erfolgreich gelöscht");
		resultData.setData("Success");
		return resultData;
	}
	@Transactional
	public ResultData<Cost> findCostByid(int id) {
		ResultData<Cost> resultData=new ResultData<Cost>();
		Cost costInfo=costDao.getCostbyId(id);
		if(null!=costInfo){
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("Es ist erfolgreich");
			resultData.setData(costInfo);
		}else{
			resultData.setStatus(StatusCode.FAIL);
			resultData.setMsg("fee_list.html");
			resultData.setData(null);
		}
		return resultData;
	}
	@Transactional
	public ResultData<String> modifyTarifInfo(Cost cost){
		ResultData<String> resultData=new ResultData<String>();
		costDao.modifyConstById(cost);
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("Erfolgreich gespeichert！");
		resultData.setData(null);
		return resultData;
	}
	@Transactional
	public ResultData<String> addTarifInfo(Cost cost) {
		ResultData<String> resultData=new ResultData<String>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cost.setCreatime( sdf.format(new Date()).toString());;
		costDao.saveTarifInfo(cost);
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("Erfolgreich gespeichert！");
		resultData.setData(null);
		return resultData;
	}
}
