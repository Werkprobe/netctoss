package com.netnoss.www.entity;

public class ServiceDetailInfo {
	private int id;
	private int account_id;
	private String idcard_no;
	private String real_name;
	private String login_name;
	private int status;
	private String unix_host;
	private String name;
	
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdcard_no() {
		return idcard_no;
	}
	public void setIdcard_no(String idcard_no) {
		this.idcard_no = idcard_no;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUnix_host() {
		return unix_host;
	}
	public void setUnix_host(String unix_host) {
		this.unix_host = unix_host;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ServiceDetailInfo [id=" + id + ", idcard_no=" + idcard_no + ", real_name=" + real_name + ", login_name="
				+ login_name + ", status=" + status + ", unix_host=" + unix_host + ", name=" + name + "]";
	}
	
}
