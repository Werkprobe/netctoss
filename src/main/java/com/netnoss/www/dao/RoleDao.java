package com.netnoss.www.dao;

import java.util.List;

import com.netnoss.www.entity.AdminRole;
import com.netnoss.www.entity.Role;
import com.netnoss.www.entity.RoleInfo;
import com.netnoss.www.entity.RolePrivilege;



public interface RoleDao {
	
	/**
	 * find RoleList
	 * @return  List<RoleList> 
	 */
	public List<RoleInfo> findRoleList(int pageNum,int pageCount);
	/**
	 * find Role Count
	 * @return
	 */
	public int findRoleCount();
	/**
	 * plus role Berechtigungen
	 * @param rolePrivilegeList
	 */
	public void saveRoleGewalt(List<RolePrivilege> rolePrivilegeList);
	/**
	 * plue role
	 * @param name
	 */
	public void saveRoleInfo(Role role);
	/**
	 * find roleInfo
	 * @param id
	 * @return
	 */
	public Role findRoleByid(String name);
	/**
	 * find Gewalt f¨¹r Role
	 * @param admin_code
	 * @return RoleInfo
	 */
	public RoleInfo findRoleInfoById(int id);
	/**
	 * delete roleGewalt f¨¹r role_id
	 * @param roleId
	 */
	public void deleteRoleGewalt(int roleId);
	/**
	 * modify role_name f¨¹r role_id
	 * @param role
	 */
	public void updateRole(Role role);
	/**
	 *  die Role hat benutzt.
	 * @param roleId
	 * @returnAdminRole
	 */
	public List<AdminRole> findRolesByRoleId(int roleId);
	/**
	 * role lischt ab
	 * @param roleId
	 */
	public void deleteRole(int roleId);
	
	
}
