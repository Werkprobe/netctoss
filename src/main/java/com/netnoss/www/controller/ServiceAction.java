package com.netnoss.www.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netnoss.www.entity.ServiceCondition;
import com.netnoss.www.entity.ServiceUpdate;
import com.netnoss.www.service.NetCService;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;
import com.netnoss.www.view.NetcServiceViewModel;import com.netnoss.www.view.ServiceModiViewModel;

@Controller
@RequestMapping("/service")
public class ServiceAction {
	@Resource(name="netCServiceImpl")
	private NetCService netCService;
	
	@RequestMapping("/findServiceList.do")
	@ResponseBody
	public ResultData<NetcServiceViewModel> findServiceInfoList(HttpServletRequest request,HttpServletResponse response){
		ServiceCondition serviceCondition=new ServiceCondition();
		serviceCondition.setOsUsername(request.getParameter("osUsername"));
		serviceCondition.setAusWeise(request.getParameter("ausWeise"));
		serviceCondition.setServiceIp(request.getParameter("serviceIp"));
		serviceCondition.setStatus(Integer.parseInt(request.getParameter("status")));
		serviceCondition.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
		serviceCondition.setPageSize(StatusCode.PAGE_TOTAL_5);
		ResultData<NetcServiceViewModel> resultData=netCService.findServiceList(serviceCondition);
		return resultData;
	}
	@RequestMapping("/modifyStatusInfo.do")
	@ResponseBody
	public ResultData<String> modifyStatusInfo(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		String status=request.getParameter("status");
		ResultData<String> resultData=netCService.modifyServiceStatusByid(Integer.parseInt(status), Integer.parseInt(id));
		return resultData;
	}
	@RequestMapping("/loadServiceModi.do")
	@ResponseBody
	public ResultData<ServiceModiViewModel> loadServiceModiInfo(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		ResultData<ServiceModiViewModel>  resultData=netCService.findServiceByid(Integer.parseInt(id));
		return resultData;
	}
	@RequestMapping("/modifyCostTyp.do")
	@ResponseBody
	public ResultData<String> modifyCostart(HttpServletRequest request,HttpServletResponse response){
		ServiceUpdate serviceUpdate=new ServiceUpdate();
		serviceUpdate.setService_id(Integer.parseInt(request.getParameter("id")));
		serviceUpdate.setCost_id(Integer.parseInt(request.getParameter("cost_id")));
		serviceUpdate.setOs_username(request.getParameter("os_username"));
		serviceUpdate.setUnix_host(request.getParameter("unix_host"));
		ResultData<String> resultData=netCService.modifyServiceCostId(serviceUpdate);
		return resultData;
	}
	@RequestMapping("/findAccountByCard.do")
	@ResponseBody
	public ResultData<String> findAccountbyidCard(HttpServletRequest request,HttpServletResponse response){
		String idcard_no=request.getParameter("idcard_no");
		ResultData<String> resultData=netCService.findAccountbyidCard(idcard_no);
		return resultData;
	}
	@RequestMapping("/initCostArt.do")
	@ResponseBody
	public ResultData<Map<Integer, String>> findAllCostArt(){
		ResultData<Map<Integer, String>> resultData=netCService.findAllCostArt();
		return resultData;
	}
	@RequestMapping("/addService.do")
	@ResponseBody
	public ResultData<String> addService(HttpServletRequest request,HttpServletResponse response){
		com.netnoss.www.entity.Service service=new com.netnoss.www.entity.Service();
		service.setUnix_host(request.getParameter("unix_host"));
		service.setOs_username(request.getParameter("os_username"));
		service.setLogin_passwd(request.getParameter("login_passwd"));
		service.setCost_id(Integer.parseInt(request.getParameter("cost_id")));
		service.setAccount_id(Integer.parseInt(request.getParameter("account_id")));
		ResultData<String> resultData=netCService.insertNewService(service);
		return resultData;
	}
}
