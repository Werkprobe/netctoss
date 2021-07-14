package com.netnoss.www.entity;

import java.io.Serializable;

/**
 * Entity Admin_Info
 * @author kevin
 *
 */
public class AdminInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String admin_code;
	private String password;
	private String name;
	private String telephone;
	private String email;
	private String enrolldate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAdmin_code() {
		return admin_code;
	}
	public void setAdmin_code(String admin_code) {
		this.admin_code = admin_code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	@Override
	public String toString() {
		return "Admin_Info [id=" + id + ", admin_code=" + admin_code + ", password=" + password + ", name="
				+ name + ", telephone=" + telephone + ", email=" + email + ", enrolldate=" + enrolldate + "]";
	}
	
}
