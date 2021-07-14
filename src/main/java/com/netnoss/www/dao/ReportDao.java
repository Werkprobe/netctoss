package com.netnoss.www.dao;

import java.util.List;
import java.util.Map;

import com.netnoss.www.entity.ReportInfo;

public interface ReportDao {
	/**
	 * find Reprot information
	 * @return  List<ReportInfo>
	 */
	public List<ReportInfo> findReportInfo(Map<String,Integer> paramMaps); 
	/**
	 *  find Reprot totalpage
	 * @return int 
	 * @throws Exception
	 */
	public int findTotalCount();
}
