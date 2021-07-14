package com.netnoss.www.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netnoss.www.dao.ReportDao;
import com.netnoss.www.entity.ReportInfo;
import com.netnoss.www.service.ReportService;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;
import com.netnoss.www.view.ResportViewModel;

@Service
public class ReportServiceImpl implements ReportService {
	@Resource(name="reportDao")
	private ReportDao reportDao;
	@Transactional
	public ResultData<ResportViewModel> findReposList(Map<String,Integer> paramMaps){
		 ResultData<ResportViewModel> resultData=new  ResultData<ResportViewModel>();
		int pageCount=reportDao.findTotalCount();
		ResportViewModel resportViewModel=new ResportViewModel();
		if(pageCount%StatusCode.PAGE_TOTAL!=0){
			resportViewModel.setPageCount(pageCount/StatusCode.PAGE_TOTAL+1);
		}else{
			resportViewModel.setPageCount(pageCount/StatusCode.PAGE_TOTAL);
		}
		if(pageCount==0){
			resultData.setStatus(StatusCode.FAIL_RESULT_EMPYT);
			resultData.setMsg("Das Ergebnis ist leer.");
			resultData.setData(resportViewModel);
		}else{
			paramMaps.put("pageNum",(Integer)paramMaps.get("pageNum")*StatusCode.PAGE_TOTAL);
			paramMaps.put("pageSize",StatusCode.PAGE_TOTAL);
			List<ReportInfo> resportList=reportDao.findReportInfo(paramMaps);
			resportViewModel.setResportList(resportList);
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("Das ist erfolgreich.");
			resultData.setData(resportViewModel);
		}
		return resultData;
	}

	

}
