package com.netnoss.www.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netnoss.www.dao.RoleDao;
import com.netnoss.www.entity.AdminRole;
import com.netnoss.www.entity.Role;
import com.netnoss.www.entity.RoleInfo;
import com.netnoss.www.entity.RolePrivilege;
import com.netnoss.www.service.RoleService;
import com.netnoss.www.util.NetctUtil;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;
import com.netnoss.www.view.RoleListViewModel;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Resource(name="roleDao")
	private RoleDao roleDao;
	@Resource(name="roleProps")
	private Properties props;
	@Transactional
	public ResultData<RoleListViewModel> findRoleList(int pageNum) {
		ResultData<RoleListViewModel> resultData=new ResultData<RoleListViewModel>();
		RoleListViewModel roleListViewMode=new RoleListViewModel();
		//get RoleList
		List<RoleInfo> roleList=roleDao.findRoleList(pageNum*StatusCode.PAGE_TOTAL,StatusCode.PAGE_TOTAL);
		//get RoleCount
		int count=roleDao.findRoleCount();
		if(null!=roleList && 0!=roleList.size()){
			for(RoleInfo roleInfo:roleList){
				String roleBesitz=roleInfo.getRoleBesitz();
				String[] besiteArr=roleBesitz.split(",");
				StringBuilder besiteBuild=new StringBuilder();
				for(String besiteTemp:besiteArr){
					besiteBuild.append(props.get("ROLE_"+besiteTemp));
					besiteBuild.append(" ");
				}
				try {
					roleInfo.setRoleMd5(NetctUtil.getMd5(Integer.toString(roleInfo.getRoleId()))+"_"+roleInfo.getRoleId());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				roleInfo.setRoleBesitz(besiteBuild.toString().trim().replaceAll(" ", "、"));
			}
			roleListViewMode.setRoleList(roleList);
			if(count%StatusCode.PAGE_TOTAL!=0){
				roleListViewMode.setPageCount(count/StatusCode.PAGE_TOTAL+1);
			}else{
				roleListViewMode.setPageCount(count/StatusCode.PAGE_TOTAL);
			}
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("es ist erfolgreich.");
			resultData.setData(roleListViewMode);
		}else{
			resultData.setStatus(StatusCode.FAIL_RESULT_EMPYT);
			resultData.setMsg("es ist Leer.");
			resultData.setData(null);
		}
		return 	resultData;
	}
	@Transactional
	public ResultData<String> saveRoleInfo(String name, String gewalt) {
		ResultData<String> resultData=new ResultData<String>();
		//rollename ist exist
		Role role=roleDao.findRoleByid(name);
		if(null!=role){
			resultData.setStatus(StatusCode.FAIL);
			resultData.setMsg("Speichern fehlgeschlangen，Rollenname ist doppelt vorhanden！");
			resultData.setData(null);
		}else{
			Role roleInfo=new Role();
			roleInfo.setName(name);
			roleDao.saveRoleInfo(roleInfo);
			//get role primary Key
			String[] berechtigungen=gewalt.split(",");
			List<RolePrivilege> rolePrivilegeList=new ArrayList<RolePrivilege>();
			RolePrivilege rolePrivilege=null;
			for(int i=0;i<berechtigungen.length;i++){
				rolePrivilege=new RolePrivilege();
				rolePrivilege.setRoleId(roleInfo.getId());
				rolePrivilege.setPrivilegeId(Integer.parseInt(berechtigungen[i]));
				rolePrivilegeList.add(rolePrivilege);
			}
			roleDao.saveRoleGewalt(rolePrivilegeList);
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("Erfolgreich gespeichert！");
			resultData.setData(null);
		}
		
		return resultData;
	}
	@Transactional
	public ResultData<RoleInfo> findRoleDetailById(int id) {
		ResultData<RoleInfo> resultData=new ResultData<RoleInfo>();
		RoleInfo roleInfo=roleDao.findRoleInfoById(id);
		if(null!=roleInfo){
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("Es ist erfolgreich");
			resultData.setData(roleInfo);
		}else{
			resultData.setStatus(StatusCode.FAIL);
			resultData.setMsg("role_list.html");
			resultData.setData(null);
		}
		return resultData;
	}
	@Transactional
	public ResultData<String> modifyRoleDetail(Role role, String gewalt) {
		ResultData<String> resultData=new ResultData<String>();
		//check roleName isexist
		Role roleTemp=roleDao.findRoleByid(role.getName());
		if(null==roleTemp || roleTemp.getId()==role.getId()){
			//update roleName
			roleDao.updateRole(role);
			roleDao.deleteRoleGewalt(role.getId());
			List<RolePrivilege> rolePrivilegeList=new ArrayList<RolePrivilege>();
			String[] berechtigungen=gewalt.split(",");
			RolePrivilege rolePrivilege=null;
			for(int i=0;i<berechtigungen.length;i++){
				rolePrivilege=new RolePrivilege();
				rolePrivilege.setRoleId(role.getId());
				rolePrivilege.setPrivilegeId(Integer.parseInt(berechtigungen[i]));
				rolePrivilegeList.add(rolePrivilege);
			}
			roleDao.saveRoleGewalt(rolePrivilegeList);
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("es ist erfolgreich");
			resultData.setData(null);
		}else{
			resultData.setStatus(StatusCode.FAIL);
			resultData.setMsg("Speichern fehlgeschlangen，Rollenname ist doppelt vorhanden！");
			resultData.setData(null);
		}
		return resultData;
	}
	@Transactional
	public ResultData<String> delectRole(int roleId) {
		ResultData<String> resultData=new ResultData<String>();
		//hat die role benutzt
		List<AdminRole> adminRoles=roleDao.findRolesByRoleId(roleId);
		if(null==adminRoles || 0==adminRoles.size()){
			roleDao.deleteRoleGewalt(roleId);
			roleDao.deleteRole(roleId);
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("die Role hat Erfolg abgeloschen");
			resultData.setData(null);
		}else{
			resultData.setStatus(StatusCode.FAIL);
			resultData.setMsg("die Role hat keinen Erfolg abgeloschen");
			resultData.setData(null);
		}
		return resultData;
	}

}
