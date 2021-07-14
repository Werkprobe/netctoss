package com.netnoss.www.service;

import java.util.List;

import com.netnoss.www.entity.AdminInfo;
import com.netnoss.www.entity.AdminRole;
import com.netnoss.www.entity.AdminRoleCondition;
import com.netnoss.www.entity.AdminRoleDetailInfo;
import com.netnoss.www.entity.Role;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.view.AdminViewModel;

public interface AdminService {
	/**
	 * find adminroleinfolist
	 * @param condition
	 * @return ResultData<List<AdminRoleDetailInfo>
	 */
	public ResultData<AdminViewModel> findAdminRoleInfoList(AdminRoleCondition condition);
	/**
	 * find admin und role info f¨¹r id
	 * @param id
	 * @return ResultData<AdminRoleDetailInfo>
	 */
	public ResultData<AdminRoleDetailInfo> findAdminRoleDetail(int id);
	
	/**
	 * modifyAdminDetail(Role)Info f¨¹r Admin
	 * @param adminInfo
	 * @param adminRole
	 * @return ResultData<String>
	 */
	public ResultData<String> modifyAdminRoleService(AdminInfo adminInfo, List<AdminRole> adminRoleList);
	
	/**
	 * modify AdminPassword
	 * @param adminInfo
	 * @return  ResultData<String>
	 */
	public ResultData<String> modifyAdminPassword(String userId);
	/**
	 * add Admin und Role f¨¹r AdminId
	 * @param adminInfo
	 * @param adminRole
	 * @return
	 */
	public ResultData<String> addAdminRoleInfo(AdminInfo adminInfo,String adminRoleList);
	
	/**
	 * del AdminInfo
	 * @param id
	 * @return
	 */
	public ResultData<String> delAdminInfoById(int id);
	/**
	 * find RoleInfo List
	 * @return ResultData<Role>
	 */
	public  ResultData<List<Role>> findRoleListInfo();
}
