package com.netnoss.www.service;

import com.netnoss.www.entity.Role;
import com.netnoss.www.entity.RoleInfo;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.view.RoleListViewModel;

public interface RoleService {
	/**
	 * find role List
	 * @return RoleListViewModel
	 */
	public ResultData<RoleListViewModel> findRoleList(int pageNum);
	/**
	 * save roleInfo
	 * @param name
	 * @param gewalt
	 * @return ResultData<String>
	 */
	public ResultData<String> saveRoleInfo(String name,String gewalt);
	/**
	 * find role f¨¹r Id
	 * @param id
	 * @return
	 */
	public ResultData<RoleInfo> findRoleDetailById(int id);
	/**
	 * update roleDatail f¨¹r role_id
	 * @param role
	 * @param gewalt
	 * @return
	 */
	public ResultData<String> modifyRoleDetail(Role role,String gewalt);
	/**
	 * die role lischt ab 
	 * @param RoleId
	 * @return
	 */
	public ResultData<String> delectRole(int roleId);
	
	 
}
