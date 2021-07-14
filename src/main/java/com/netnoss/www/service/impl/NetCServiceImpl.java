package com.netnoss.www.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netnoss.www.dao.AccountDao;
import com.netnoss.www.dao.CostDao;
import com.netnoss.www.dao.ServiceDao;
import com.netnoss.www.dao.ServiceUpdateDao;
import com.netnoss.www.entity.Account;
import com.netnoss.www.entity.Cost;
import com.netnoss.www.entity.ServiceCondition;
import com.netnoss.www.entity.ServiceDetailInfo;
import com.netnoss.www.entity.ServiceUpdate;
import com.netnoss.www.service.NetCService;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;
import com.netnoss.www.view.NetcServiceViewModel;
import com.netnoss.www.view.ServiceModiViewModel;
@Service
public class NetCServiceImpl implements NetCService{
	@Resource(name="serviceDao")
	private ServiceDao serviceDao;
	@Resource(name="serviceUpdateDao")
	private ServiceUpdateDao serviceUpdateDao;
	@Resource(name="costDao")
	private CostDao costDao;
	@Resource(name="accountDao")
	private AccountDao accountDao;
	@Transactional
	public ResultData<NetcServiceViewModel> findServiceList(ServiceCondition serviceCondition) {
		ResultData<NetcServiceViewModel> resultData=new ResultData<NetcServiceViewModel>();
		serviceCondition.setPageNum(serviceCondition.getPageNum()*StatusCode.PAGE_TOTAL_5);
		int pageCount=serviceDao.findServicePageCount(serviceCondition);
		NetcServiceViewModel serviceViewModel=new NetcServiceViewModel();
		if(pageCount%StatusCode.PAGE_TOTAL_5!=0){
			serviceViewModel.setPageCount(pageCount/StatusCode.PAGE_TOTAL_5+1);
		}else{
			serviceViewModel.setPageCount(pageCount/StatusCode.PAGE_TOTAL_5);
		}
		if(pageCount==0){
			resultData.setStatus(StatusCode.FAIL_RESULT_EMPYT);
			resultData.setMsg("Das Ergebnis ist leer.");
			resultData.setData(serviceViewModel);
		}else{
			List<ServiceDetailInfo> serviceList=serviceDao.findAllService(serviceCondition);
			serviceViewModel.setServiceDetailInfoList(serviceList);
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("Das ist erfolgreich.");
			resultData.setData(serviceViewModel);
		}
		return resultData;
	}
	@Transactional
	public ResultData<String> modifyServiceStatusByid(int status, int id) {
		ResultData<String> resultData=new ResultData<String>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String processTime=sdf.format(new Date());
		serviceDao.updateServiceStatus(status,processTime, id);
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("Der Zustand des Services wurde erfolgreich geändert.");
		resultData.setData("Success");
		return resultData;
	}
	@Transactional
	public ResultData<ServiceModiViewModel> findServiceByid(int id) {
		ResultData<ServiceModiViewModel> resultData=new ResultData<ServiceModiViewModel>();
		com.netnoss.www.entity.Service serviceInfo=serviceDao.findServiceByid(id);
		List<Cost> costList=costDao.findAllCost();
		if(null!=serviceInfo){
			ServiceModiViewModel serviceDetailInfo=new ServiceModiViewModel();
			serviceDetailInfo.setAccount_id(serviceInfo.getAccount_id());
			serviceDetailInfo.setLogin_name(serviceInfo.getOs_username());
			serviceDetailInfo.setUnix_host(serviceInfo.getUnix_host());
			serviceDetailInfo.setId(serviceInfo.getId());
			serviceDetailInfo.setCost_id(serviceInfo.getCost_id());
			Map<Integer,String> costMap=new HashMap<Integer,String>();
			serviceDetailInfo.setCostList(costMap);
			for(Cost cost:costList){
				costMap.put(cost.getId(), cost.getName());
			}
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("es gibt die ServiceInformation.");
			resultData.setData(serviceDetailInfo);
		}else{
			resultData.setStatus(StatusCode.FAIL);
			resultData.setMsg("service_list.html");
			resultData.setData(null);
		}
		return resultData;
	}
	@Transactional
	public ResultData<String> modifyServiceCostId(ServiceUpdate serviceUpdate) {
		ResultData<String> resultData=new ResultData<String>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String create_date=sdf.format(new Date());
		serviceUpdate.setCreate_date(create_date);
		serviceUpdateDao.saveServiceInfo(serviceUpdate);
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("Success,bis zum Ende dieses Monats wird diese Art wechseln.");
		resultData.setData(null);
		return resultData;
	}
	@Transactional
	public ResultData<String> findAccountbyidCard(String idcard_no) {
		ResultData<String> resultData=new ResultData<String>();
		Account  account=accountDao.findAccountByidCard(idcard_no);
		if(null!=account){
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("Das ergebnis wurde es ins Konto geschrieben.");
			resultData.setData(account.getLogin_name()+","+account.getId());
		}else{
			resultData.setStatus(StatusCode.FAIL_RESULT_EMPYT);
			resultData.setMsg("Ohne diese Konto, geben Sie bitte erneut ein.");
			resultData.setData(null);
		}
		
		return resultData;
	}
	@Transactional
	public ResultData<Map<Integer, String>> findAllCostArt() {
		ResultData<Map<Integer, String>> resultData=new ResultData<Map<Integer, String>>();
		List<Cost> costList=costDao.findAllCost();
		if(null!=costList){
			Map<Integer,String> costMap=new HashMap<Integer,String>();
			for(Cost cost:costList){
				costMap.put(cost.getId(), cost.getName());
			}
			resultData.setData(costMap);
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("es gibt dieses Cost.");
		}else{
			resultData.setStatus(StatusCode.FAIL_RESULT_EMPYT);
			resultData.setMsg("Vielleicht erstellen Sie noch kein Cost.");
			resultData.setData(null);
		}
		return resultData;
	}
	@Transactional
	public ResultData<String> insertNewService(com.netnoss.www.entity.Service service) {
		ResultData<String>  resultData=new ResultData<String>();
		Map<String,String> paramMaps=new HashMap<String,String>();
		paramMaps.put("unix_host", service.getUnix_host());
		paramMaps.put("os_username",service.getOs_username());
		List<com.netnoss.www.entity.Service> serviceList=serviceDao.findHostByname(paramMaps);
		if(null!=serviceDao && 0!=serviceList.size()){
			resultData.setStatus(StatusCode.FAIL);
			resultData.setMsg("dieser OS Username war shon in dem Service(" + service.getUnix_host()+".");
			resultData.setData(null);
		}else{
			service.setStatus(1);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String create_date=sdf.format(new Date());
			service.setCreate_date(create_date);
			serviceDao.addNewService(service);
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("Service wurden erfolgreich hinzugefügt.");
			resultData.setData(null);
		}
		return resultData;
	}
	
}
