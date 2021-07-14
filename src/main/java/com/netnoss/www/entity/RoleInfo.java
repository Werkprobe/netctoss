package com.netnoss.www.entity;

public class RoleInfo {
	private int roleId;
	private String roleMd5;
	private String roleName;
	private String roleBesitz;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleBesitz() {
		return roleBesitz;
	}
	public void setRoleBesitz(String roleBesitz) {
		this.roleBesitz = roleBesitz;
	}
	
	public String getRoleMd5() {
		return roleMd5;
	}
	public void setRoleMd5(String roleMd5) {
		this.roleMd5 = roleMd5;
	}
	@Override
	public String toString() {
		return "RoleInfo [roleId=" + roleId + ", roleName=" + roleName + ", roleBesitz=" + roleBesitz + "]";
	}
	
}
