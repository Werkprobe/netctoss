package com.netnoss.www.entity;

public class BillDetailMonth {
	private String os_username;
	private String unix_host;
	private int account_id;
	private String sofar_duration;
	private double cost;
	private String name;
	public String getOs_username() {
		return os_username;
	}
	public void setOs_username(String os_username) {
		this.os_username = os_username;
	}
	public String getUnix_host() {
		return unix_host;
	}
	public void setUnix_host(String unix_host) {
		this.unix_host = unix_host;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public String getSofar_duration() {
		return sofar_duration;
	}
	public void setSofar_duration(String sofar_duration) {
		this.sofar_duration = sofar_duration;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "BillDetailMonth [os_username=" + os_username + ", unix_host=" + unix_host + ", account_id=" + account_id
				+ ", sofar_duration=" + sofar_duration + ", cost=" + cost + ", name=" + name + "]";
	}
	
}
