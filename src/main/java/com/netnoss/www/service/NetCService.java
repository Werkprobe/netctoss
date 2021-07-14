package com.netnoss.www.service;

import java.util.Map;

import com.netnoss.www.entity.Service;
import com.netnoss.www.entity.ServiceCondition;
import com.netnoss.www.entity.ServiceUpdate;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.view.NetcServiceViewModel;
import com.netnoss.www.view.ServiceModiViewModel;

public interface NetCService {
	/**
	 * find serviceallInfo
	 * @param serviceCondition
	 * @return ResultData<NetcServiceViewModel>
	 */
	public ResultData<NetcServiceViewModel> findServiceList(ServiceCondition serviceCondition);
	/**
	 * update Service StatusInfo by id
	 * @param status
	 * @param id
	 * @return ResultData<String>
	 */
	public  ResultData<String> modifyServiceStatusByid(int status,int id);
	/**
	 * find Serviceinfo by id
	 * @param id
	 * @return
	 */
	public ResultData<ServiceModiViewModel> findServiceByid(int id);
	/**
	 * modify ServiceCostid Aufgrund id
	 * @param cost_id
	 * @param id
	 * @return ResultData<String>
	 */
	public ResultData<String> modifyServiceCostId(ServiceUpdate serviceUpdate);
	/**
	 * find Account by idCard
	 * @param idcard_no
	 * @return  ResultData<String>
	 */
	public ResultData<String> findAccountbyidCard(String idcard_no);
	/**
	 * find Cost Art
	 * @return ResultData<Map<Integer,String>> 
	 */
	public ResultData<Map<Integer,String>> findAllCostArt();
	/**
	 * add new service
	 * @param sercvie
	 * @return ResultData<String>
	 */
	public ResultData<String> insertNewService(Service service);
}
