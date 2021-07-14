package com.netnoss.www.entity;

import java.util.List;

public class AdminRoleDetailInfo {
	private int id;
	private String name;
	private String admin_code;
	private String telephone;
	private String email;
	private String enrolldate;
	private String role_name;
	private String roleFristName;
	private String role_id;
	private List<Role> roleList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdmin_code() {
		return admin_code;
	}
	public void setAdmin_code(String admin_code) {
		this.admin_code = admin_code;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEnrolldate() {
		return enrolldate;
	}
	public void setEnrolldate(String enrolldate) {
		this.enrolldate = enrolldate;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	public String getRoleFristName() {
		return roleFristName;
	}
	public void setRoleFristName(String roleFristName) {
		this.roleFristName = roleFristName;
	}
	
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	@Override
	public String toString() {
		return "AdminRoleDetailInfo [id=" + id + ", name=" + name + ", admin_code=" + admin_code + ", telephone="
				+ telephone + ", email=" + email + ", enrolldate=" + enrolldate + ", role_name=" + role_name
				+ ", roleFristName=" + roleFristName + ", role_id=" + role_id + ", roleList=" + roleList + "]";
	}
	
	
}
