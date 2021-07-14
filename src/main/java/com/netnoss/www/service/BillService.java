package com.netnoss.www.service;


import java.util.Map;

import com.netnoss.www.entity.BillDeatil;
import com.netnoss.www.entity.BillDetailMonth;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.view.BillViewModel;

public interface BillService {
	/**
	 * find Bill Info aufgrund Vorbedingungen
	 * @param paramMaps
	 * @return
	 */
	public ResultData<BillViewModel<BillDeatil>> findBillInfo(Map<String,Object> paramMaps);
	
	/**
	 * find Bill Detail aufgrund Month
	 * @param paramMaps
	 * @return
	 */
	public ResultData<BillViewModel<BillDetailMonth>>findBillDetail(Map<String,Object> paramMaps);
}
