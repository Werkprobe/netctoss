package com.netnoss.www.dao;

import java.util.List;
import java.util.Map;

import com.netnoss.www.entity.BillDeatil;
import com.netnoss.www.entity.BillDetailMonth;

public interface BillDao {
	/**
	 * find bill information
	 * @param paramMaps
	 * @return List<BillDeatil> 
	 */
	public List<BillDeatil> findBillInfo(Map<String,Object> paramMaps);
	/**
	 * find Bill pageCount
	 * @param paramMaps
	 * @return int
	 */
	public int findBillPageCount(Map<String,Object> paramMaps);
	
	/**
	 * find Bill Detail month
	 * @param paramMaps
	 * @return List<BillDetailMonth>
	 */
	public List<BillDetailMonth> findBillMonth(Map<String,Object> paramMaps);
	
	/**
	 * find Monat detail Bill Pagecount
	 * @param bill_id
	 * @return int
	 */
	public int findbillDetailPageCount(int bill_id);
}
