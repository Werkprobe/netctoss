package com.netnoss.www.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.netnoss.www.entity.Role;
import com.netnoss.www.entity.RoleInfo;
import com.netnoss.www.service.RoleService;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;
import com.netnoss.www.view.RoleListViewModel;

@Controller
@RequestMapping("/rolemangement")
public class RoleAction {
	@Resource(name="roleServiceImpl")
	private RoleService roleService;
	
	
	@RequestMapping("/roleList.do")
	@ResponseBody
	public ResultData<RoleListViewModel> RoleListLoad(HttpServletRequest request,HttpServletResponse response){
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		ResultData<RoleListViewModel> resultData=roleService.findRoleList(pageNum);
		return resultData;
	}
	@RequestMapping("/addRoleInfo.do")
	@ResponseBody
	public ResultData<String> addRoleInfo(HttpServletRequest request,HttpServletResponse response){
		String name=request.getParameter("rolleName");
		String gewalt=request.getParameter("gewalt");
		ResultData<String> resultData=roleService.saveRoleInfo(name, gewalt);
		return resultData;
	}

	@RequestMapping("/checkEingung.do")
	@ResponseBody
	public ResultData<String> loadPage(HttpServletRequest request,HttpServletResponse response){
		ResultData<String> resultData=new ResultData<String>();
		String url=request.getParameter("url");
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("Sie erlauben system");
		resultData.setData(url);
		return resultData;
	}
	@RequestMapping("/findRoleDetail.do")
	@ResponseBody
	public ResultData<RoleInfo> findRoleDetail(HttpServletRequest request,HttpServletResponse response){
		String idStr=request.getParameter("roleId");
		int roleId=Integer.parseInt(idStr.substring(idStr.lastIndexOf("_")+1));
		ResultData<RoleInfo> resultData=roleService.findRoleDetailById(roleId);
		return resultData;
	}
	@RequestMapping("/modifyRGInfo.do")
	@ResponseBody
	public ResultData<String> modifyRGInfo(HttpServletRequest request,HttpServletResponse response){
		Role role=new Role();
		String roleId=request.getParameter("roleId");
		String roleName=request.getParameter("roleName");
		String gewalt=request.getParameter("gewalt");
		role.setId(Integer.parseInt(roleId));
		role.setName(roleName);
		ResultData<String> resultData=roleService.modifyRoleDetail(role, gewalt);
		return resultData; 
	}
	@RequestMapping("/delRoleInfo.do")
	@ResponseBody
	public ResultData<String>  delRoleInfo(HttpServletRequest request,HttpServletResponse response){
		String roleId=request.getParameter("roleId");
		ResultData<String> resultData=roleService.delectRole(Integer.parseInt(roleId));
		return resultData;
	}
}
