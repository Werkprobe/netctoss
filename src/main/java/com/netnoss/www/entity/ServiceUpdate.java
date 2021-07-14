package com.netnoss.www.entity;

public class ServiceUpdate {
	 private int id;
	 private int service_id;
	 private String unix_host;
	 private String os_username;
	 private int cost_id;
	 private String create_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getService_id() {
		return service_id;
	}
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}
	public String getUnix_host() {
		return unix_host;
	}
	public void setUnix_host(String unix_host) {
		this.unix_host = unix_host;
	}
	public String getOs_username() {
		return os_username;
	}
	public void setOs_username(String os_username) {
		this.os_username = os_username;
	}
	public int getCost_id() {
		return cost_id;
	}
	public void setCost_id(int cost_id) {
		this.cost_id = cost_id;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	 
	 
}
