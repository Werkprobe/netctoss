package com.netnoss.www.entity;

public class ServiceCondition {
	private String osUsername;
	private String serviceIp;
	private String ausWeise;
	private int status;
	private int pageNum;
	private int pageSize;
	public String getOsUsername() {
		return osUsername;
	}
	public void setOsUsername(String osUsername) {
		this.osUsername = osUsername;
	}
	public String getServiceIp() {
		return serviceIp;
	}
	public void setServiceIp(String serviceIp) {
		this.serviceIp = serviceIp;
	}
	public String getAusWeise() {
		return ausWeise;
	}
	public void setAusWeise(String ausWeise) {
		this.ausWeise = ausWeise;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
