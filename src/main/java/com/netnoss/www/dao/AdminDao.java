package com.netnoss.www.dao;

import java.util.List;

import com.netnoss.www.entity.AdminInfo;
import com.netnoss.www.entity.AdminRole;
import com.netnoss.www.entity.AdminRoleCondition;
import com.netnoss.www.entity.AdminRoleDetailInfo;
import com.netnoss.www.entity.Role;

public interface AdminDao {
	/**
	 * finden adminroleDetailInfoList
	 * @param condition
	 * @return List<AdminRoleDetailInfo>
	 */
	public List<AdminRoleDetailInfo> findAdminRoleListInfo(AdminRoleCondition condition);
	/**
	 *get adminRole pageNum
	 * @param condition
	 * @return  int
	 */
	public int findAdminCountPage(AdminRoleCondition condition); 
	/**
	 * get AdminRoleInfo f¨¹r Id
	 * @param adminId
	 * @return
	 */
	public AdminRoleDetailInfo findAdminRoleInfoById(int adminId);
	/**
	 * find Role info
	 * @return
	 */
	public List<Role> findRoleListInfo();
	
	/**
	 * modify AdminInfo
	 * @param adminInfo
	 */
	public void updateAdminInfoById(AdminInfo adminInfo);
	/**
	 * delete RoleInfo f¨¹r AdminId
	 * @param id
	 */
	public void delRoleById(int id);
	
	/**
	 * insert RoleInfo f¨¹r AdminId
	 * @param adminRoleList
	 */
	public void insertRoleAdmin(List<AdminRole> adminRoleList);
	/**
	 * modify userPassword
	 * @param adminInfo
	 */
	public void modifyPassword(AdminInfo adminInfo);
	/**
	 * add addAdminInfo
	 * @param adminInfo
	 */
	public void addAdminInfo(AdminInfo adminInfo);
	/**
	 * del AdminInfo
	 * @param id
	 */
	public void delAdminInfo(int id);
	/**
	 * find Role List
	 * @return
	 */
	public List<Role> findRoleList();
}
