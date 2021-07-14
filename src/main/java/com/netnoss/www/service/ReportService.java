package com.netnoss.www.service;

import java.util.Map;

import com.netnoss.www.util.ResultData;
import com.netnoss.www.view.ResportViewModel;

public interface ReportService {
	/**
	 * find findReposList
	 * @return ResultData<ResportViewModel>
	 */
	public ResultData<ResportViewModel> findReposList(Map<String,Integer> paramMaps);
}
