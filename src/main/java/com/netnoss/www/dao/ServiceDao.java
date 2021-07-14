package com.netnoss.www.dao;

import java.util.List;
import java.util.Map;

import com.netnoss.www.entity.Service;
import com.netnoss.www.entity.ServiceCondition;
import com.netnoss.www.entity.ServiceDetailInfo;

public interface ServiceDao {
	/**
	 * find pagecount
	 * @param serviceCondition
	 * @return int
	 */
	
	public int findServicePageCount(ServiceCondition serviceCondition);
	/**
	 * find serviceInfo
	 * @param serviceCondition
	 * @return List<ServiceDetailInfo>
	 */
	public List<ServiceDetailInfo> findAllService(ServiceCondition serviceCondition);
	   /**
	    * modify service Status
	    * @param status
	    */
	public void updateServiceStatus(int status,String processTime,int id);
	
	/**
	 * find Service info aufgrund id
	 * @param id
	 * @return
	 */
	public  Service findServiceByid(int id);
	/**
	 * modify Serivce costTyp
	 * @param cost_id
	 * @param id
	 */
	public void modifyCostId(int cost_id,int id);
	/**
	 * add Service
	 * @param service
	 */
	public void addNewService(Service service);
	/**
	 * find host name
	 * @param params
	 * @return List<Service>
	 */
	public List<Service> findHostByname(Map<String,String> params);  
	
}
