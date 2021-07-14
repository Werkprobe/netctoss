package com.netnoss.www.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netnoss.www.dao.BillDao;
import com.netnoss.www.entity.BillDeatil;
import com.netnoss.www.entity.BillDetailMonth;
import com.netnoss.www.service.BillService;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;
import com.netnoss.www.view.BillViewModel;

@Service
public class BillServiceImpl implements BillService{
	@Resource(name="billDao")
	private BillDao billDao;
	@Transactional
	public ResultData<BillViewModel<BillDeatil>> findBillInfo(Map<String, Object> paramMaps) {
		ResultData<BillViewModel<BillDeatil>> resultData=new ResultData<BillViewModel<BillDeatil>>();
		int pageCount=billDao.findBillPageCount(paramMaps);
		BillViewModel<BillDeatil> billViewModel=new BillViewModel<BillDeatil>();
		if(pageCount%StatusCode.PAGE_TOTAL!=0){
			billViewModel.setPageCount(pageCount/StatusCode.PAGE_TOTAL+1);
		}else{
			billViewModel.setPageCount(pageCount/StatusCode.PAGE_TOTAL);
		}
		if(pageCount==0){
			resultData.setStatus(StatusCode.FAIL_RESULT_EMPYT);
			resultData.setMsg("Das Ergebnis ist leer.");
			resultData.setData(billViewModel);
		}else{
			paramMaps.put("pageNum",(Integer)paramMaps.get("pageNum")*StatusCode.PAGE_TOTAL);
			paramMaps.put("pageSize",StatusCode.PAGE_TOTAL);
			List<BillDeatil> billDeatilList=billDao.findBillInfo(paramMaps);
			billViewModel.setBillDetailInfoList(billDeatilList);
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("Das ist erfolgreich.");
			resultData.setData(billViewModel);
		}
		return resultData;
	}
	@Transactional
	public ResultData<BillViewModel<BillDetailMonth>> findBillDetail(Map<String, Object> paramMaps) {
		ResultData<BillViewModel<BillDetailMonth>> resultData=new ResultData<BillViewModel<BillDetailMonth>>();
		int pageCount=billDao.findbillDetailPageCount((Integer)paramMaps.get("bill_id"));
		BillViewModel<BillDetailMonth> billDeatilViewModel=new BillViewModel<BillDetailMonth>();
		if(pageCount%StatusCode.PAGE_TOTAL!=0){
			billDeatilViewModel.setPageCount(pageCount/StatusCode.PAGE_TOTAL+1);
		}else{
			billDeatilViewModel.setPageCount(pageCount/StatusCode.PAGE_TOTAL);
		}
		if(pageCount==0){
			resultData.setStatus(StatusCode.FAIL_RESULT_EMPYT);
			resultData.setMsg("Das Ergebnis ist leer.");
			resultData.setData(billDeatilViewModel);
		}else{
			paramMaps.put("pageNum",(Integer)paramMaps.get("pageNum")*StatusCode.PAGE_TOTAL);
			paramMaps.put("pageSize",StatusCode.PAGE_TOTAL);
			List<BillDetailMonth> billMonthlList=billDao.findBillMonth(paramMaps);
			billDeatilViewModel.setBillDetailInfoList(billMonthlList);
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("Das ist erfolgreich.");
			resultData.setData(billDeatilViewModel);
		}
		return resultData;
	}

}
