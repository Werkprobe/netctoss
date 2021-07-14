package com.netnoss.www.view;

import java.util.Map;

public class ServiceModiViewModel {
	private int id;
	private int account_id;
	private String login_name;
	private int cost_id;
	private String unix_host;
	private Map<Integer,String> costList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	
	public int getCost_id() {
		return cost_id;
	}
	public void setCost_id(int cost_id) {
		this.cost_id = cost_id;
	}
	public String getUnix_host() {
		return unix_host;
	}
	public void setUnix_host(String unix_host) {
		this.unix_host = unix_host;
	}
	public Map<Integer, String> getCostList() {
		return costList;
	}
	public void setCostList(Map<Integer, String> costList) {
		this.costList = costList;
	} 
	
	
}
