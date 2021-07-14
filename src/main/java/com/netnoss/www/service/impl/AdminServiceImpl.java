package com.netnoss.www.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netnoss.www.dao.AdminDao;
import com.netnoss.www.dao.UserDao;
import com.netnoss.www.entity.AdminInfo;
import com.netnoss.www.entity.AdminRole;
import com.netnoss.www.entity.AdminRoleCondition;
import com.netnoss.www.entity.AdminRoleDetailInfo;
import com.netnoss.www.entity.Role;
import com.netnoss.www.service.AdminService;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;
import com.netnoss.www.view.AdminViewModel;

@Service
public class AdminServiceImpl implements AdminService {
	@Resource(name="adminDao")
	private AdminDao adminDao;
	@Resource(name="userDao")
	private UserDao userDao;
	@Transactional
	public ResultData<AdminViewModel> findAdminRoleInfoList(AdminRoleCondition condition) {
		ResultData<AdminViewModel> resultData=new ResultData<AdminViewModel>();
		int pageCount=adminDao.findAdminCountPage(condition);
		List<AdminRoleDetailInfo> adminRoleList=adminDao.findAdminRoleListInfo(condition);
		AdminViewModel adminRoleViewModel=new AdminViewModel();
		if(pageCount%StatusCode.PAGE_TOTAL!=0){
			adminRoleViewModel.setPageCount(pageCount/StatusCode.PAGE_TOTAL+1);
		}else{
			adminRoleViewModel.setPageCount(pageCount/StatusCode.PAGE_TOTAL);
		}
		adminRoleViewModel.setAdminList(adminRoleList);
		if(adminRoleList.size()==0 || adminRoleList==null){
			resultData.setStatus(StatusCode.FAIL_RESULT_EMPYT);
			resultData.setMsg("Das Ergebnis ist leer.");
			resultData.setData(adminRoleViewModel);
		}else{
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("Das ist erfolgreich.");
			resultData.setData(adminRoleViewModel);
		}
		return resultData;
	}
	@Transactional
	public ResultData<AdminRoleDetailInfo> findAdminRoleDetail(int id) {
		ResultData<AdminRoleDetailInfo> resultData=new ResultData<AdminRoleDetailInfo>();
		AdminRoleDetailInfo adminRoleDetailInfo=adminDao.findAdminRoleInfoById(id);
		if(null!=adminRoleDetailInfo){
			List<Role> roleList=adminDao.findRoleListInfo();
			adminRoleDetailInfo.setRoleList(roleList);
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("es gibt die AdminRoleInformation .");
			resultData.setData(adminRoleDetailInfo);
		}else{
			resultData.setStatus(StatusCode.FAIL);
			resultData.setMsg("admin_list.html");
			resultData.setData(null);
		}
		return resultData;
	}
	@Transactional
	public ResultData<String> modifyAdminRoleService(AdminInfo adminInfo, List<AdminRole> adminRoleList) {
		ResultData<String> resultData=new ResultData<String>();
		adminDao.updateAdminInfoById(adminInfo);
		adminDao.delRoleById(adminInfo.getId());
		adminDao.insertRoleAdmin(adminRoleList);
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("die Information des Admins wird Erfolgreich geändert");
		resultData.setData(null);
		return resultData;
	}
	@Transactional
	public ResultData<String> modifyAdminPassword(String userId) {
		ResultData<String> resultData=new ResultData<String>();
		String[] userIdList=userId.split(",");
		AdminInfo adminInfo=null;
		for(String id:userIdList){
			adminInfo=new AdminInfo();
			adminInfo.setId(Integer.parseInt(id));
			adminInfo.setPassword("a123456");
			adminDao.modifyPassword(adminInfo);
		}
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("die Passwörter wird Erfolgreich wieder geändert");
		resultData.setData(null);
		return resultData;
	}
	@Transactional
	public ResultData<String> addAdminRoleInfo(AdminInfo adminInfo, String adminRoleList) {
		ResultData<String> resultData=new ResultData<String>();
		AdminInfo extAdminInfo=userDao.findByName(adminInfo.getAdmin_code());
		if(null==extAdminInfo){
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			adminInfo.setEnrolldate(sdf.format(new Date()));
			adminDao.addAdminInfo(adminInfo);
			String[] adminRoleArry=adminRoleList.split(",");
			List<AdminRole> adminRoles=new ArrayList<AdminRole>();
			AdminRole adminRole=null;
			for(String adminRoleStr:adminRoleArry){
				adminRole=new AdminRole();
				adminRole.setAdminId(adminInfo.getId());
				adminRole.setRoleId(Integer.parseInt(adminRoleStr));
				adminRoles.add(adminRole);
			}
			adminDao.insertRoleAdmin(adminRoles);
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("Admininformation wurden erfolgreich hinzugefügt.");
			resultData.setData(null);
		}else{
			resultData.setStatus(StatusCode.FAIL);
			resultData.setMsg("Admincode ist Existierend.");
			resultData.setData(null);
		}
		return resultData;
	}
	@Transactional
	public ResultData<String> delAdminInfoById(int id) {
		ResultData<String> resultData=new ResultData<String>();
		adminDao.delRoleById(id);
		adminDao.delAdminInfo(id);
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("Admininformation wurden erfolgreich gelöscht.");
		resultData.setData(null);
		return resultData;
	}
	@Transactional
	public ResultData<List<Role>> findRoleListInfo() {
		List<Role> roleList=adminDao.findRoleList();
		ResultData<List<Role>> resultData=new ResultData<List<Role>>();
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("roleInfo wurde erfolgreich gefinden.");
		resultData.setData(roleList);
		return resultData;
	}
	
}
