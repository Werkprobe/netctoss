package com.netnoss.www.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netnoss.www.entity.AdminInfo;
import com.netnoss.www.entity.AdminRole;
import com.netnoss.www.entity.AdminRoleCondition;
import com.netnoss.www.entity.AdminRoleDetailInfo;
import com.netnoss.www.entity.Role;
import com.netnoss.www.service.AdminService;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;
import com.netnoss.www.view.AdminViewModel;

@Controller
@RequestMapping("/admin")
public class AdminAction {
	@Resource(name="adminServiceImpl")
	private AdminService adminService;
	
	@RequestMapping("/adminRoleInfo.do")
	@ResponseBody
	public ResultData<AdminViewModel> findAdminRoleDetaiListInfo(HttpServletRequest request,HttpServletResponse response){
		AdminRoleCondition condition=new AdminRoleCondition();
		String privilegeId=request.getParameter("privilegeId");
		String roleName=request.getParameter("roleName");
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		if("0".equals(privilegeId)){
			condition.setPrivilegeId(null);
		}else{
			condition.setPrivilegeId(privilegeId);
		}
		condition.setPageNum(pageNum*StatusCode.PAGE_TOTAL);
		condition.setRoleName(roleName);
		condition.setPageSize(StatusCode.PAGE_TOTAL);
		ResultData<AdminViewModel> resultData=adminService.findAdminRoleInfoList(condition);
		return resultData;
	}
	
	@RequestMapping("/findAdminRoleByid.do")
	@ResponseBody
	public ResultData<AdminRoleDetailInfo> findAdminDetailInfo(HttpServletRequest request,HttpServletResponse response){
		int adminId=Integer.parseInt(request.getParameter("adminId"));
		ResultData<AdminRoleDetailInfo> resultData=adminService.findAdminRoleDetail(adminId);
		return resultData;
	}
	@RequestMapping("/checkPage.do")
	@ResponseBody
	public ResultData<String> loadPage(HttpServletRequest request,HttpServletResponse response){
		ResultData<String> resultData=new ResultData<String>();
		String url=request.getParameter("url");
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("Sie erlauben system");
		resultData.setData(url);
		return resultData;
	}
	
	@RequestMapping("/modifyAdminRoleInfo.do")
	@ResponseBody
	public ResultData<String>  modifyAdminRoleInfo(HttpServletRequest request,HttpServletResponse response){
		AdminInfo adminInfo=new AdminInfo();
		int id=Integer.parseInt(request.getParameter("id"));
		adminInfo.setId(id);
		adminInfo.setName(request.getParameter("adminName"));
		adminInfo.setTelephone(request.getParameter("telefon"));
		adminInfo.setEmail(request.getParameter("email"));
		String[] roleInfoArr=request.getParameter("roleInfo").split(",");
		List<AdminRole> adminRoleList=new ArrayList<AdminRole>();
		AdminRole adminRole=null; 
		for(String roleId:roleInfoArr){
			adminRole=new AdminRole();
			adminRole.setAdminId(id);
			adminRole.setRoleId(Integer.parseInt(roleId));
			adminRoleList.add(adminRole);
		}
		ResultData<String> resultData=adminService.modifyAdminRoleService(adminInfo, adminRoleList);
		return resultData;
	}
	
	@RequestMapping("/modifyAdminPassword.do")
	@ResponseBody
	public ResultData<String> resetPassword(HttpServletRequest request,HttpServletResponse response){
		String userid=request.getParameter("id");
		ResultData<String> resultData=adminService.modifyAdminPassword(userid);
		return resultData;
	}
	@RequestMapping("/addAdminRoleList.do")
	@ResponseBody
	public ResultData<String> addAdminRoleListInfo(HttpServletRequest request,HttpServletResponse response){
		AdminInfo adminInfo=new AdminInfo();
		adminInfo.setAdmin_code(request.getParameter("admin_code"));
		adminInfo.setPassword(request.getParameter("password"));
		adminInfo.setEmail(request.getParameter("email"));
		adminInfo.setName(request.getParameter("admin_name"));
		adminInfo.setTelephone(request.getParameter("telephone"));
		String adminRoleList=request.getParameter("roleIdList");
		ResultData<String> resultData=adminService.addAdminRoleInfo(adminInfo, adminRoleList);
		return resultData;
	}
	@RequestMapping("/delAdminInfoById.do")
	@ResponseBody
	public ResultData<String> delAdminInfoById(HttpServletRequest request,HttpServletResponse response){
		int id=Integer.parseInt(request.getParameter("id"));
		ResultData<String> resultData=adminService.delAdminInfoById(id);
		return resultData;
		
	}
	
	@RequestMapping("/getRoleInfoList.do")
	@ResponseBody
	public  ResultData<List<Role>> getRoleInfoList(HttpServletRequest request,HttpServletResponse response){
		ResultData<List<Role>> resultData=adminService.findRoleListInfo();
		return resultData;
	}
}
