package com.netnoss.www.service;


import com.netnoss.www.entity.Cost;
import com.netnoss.www.entity.ParamValue;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.view.CostListViewModel;

public interface CostService {
	/**
	 * find geschäftInfoList
	 * @param sortParam
	 * @return ResultData<List<Cost>>
	 */
	public ResultData<CostListViewModel> findCostListInfo(ParamValue paramValue);
	/**
	 * modify status in Cost
	 * @param status
	 * @param id
	 * @return ResultData<String>
	 */
	public ResultData<String> modifyCostStatus(int status,int id);
	/**
	 * delete Costinfo
	 * @param id
	 * @return  ResultData<String>
	 */
	public ResultData<String> delCostInfoById(int id);
	/**
	 * find TarifInfo by id
	 * @param id
	 * @return ResultData<Cost>
	 */
	public ResultData<Cost> findCostByid(int id);
	/**
	 * update TarifInfo by id
	 * @param cost
	 * @return ResultData<String>
	 */
	public ResultData<String> modifyTarifInfo(Cost cost);
	/**
	 * save Cost Info
	 * @param cost
	 * @return  ResultData<String>
	 */
	public ResultData<String> addTarifInfo(Cost cost);
}
