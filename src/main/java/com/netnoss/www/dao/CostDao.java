package com.netnoss.www.dao;

import java.util.List;

import com.netnoss.www.entity.Cost;
import com.netnoss.www.entity.ParamValue;

public interface CostDao {
	/**
	 * find geschäftInfo list
	 * @return List<Cost>
	 */
	public List<Cost> findCostList(ParamValue param);
	/**
	 * find page Cost
	 * @return  int
	 */
	public int findCostCount();
	/**
	 * update Cost Status
	 */
	public void updateCostStatus(int status,int id,String startTiem);
	/**
	 * delete Cost info
	 * @param id
	 */
	public void delCostById(int id);
	/**
	 * find CostInfo by id
	 * @param id
	 * @return
	 */
	public Cost getCostbyId(int id);
	/**
	 * modify Tarif by Id
	 * @param cost
	 */
	public void modifyConstById(Cost cost);
	/**
	 * save TarifInfo
	 * @param cost
	 */
	public void saveTarifInfo(Cost cost);
	
	/**
	 * find Cost List
	 * @return List<Cost>
	 */
	public List<Cost> findAllCost();
}
