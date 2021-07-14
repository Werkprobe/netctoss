package com.netnoss.www.view;

import java.util.List;

import com.netnoss.www.entity.AdminRoleDetailInfo;

public class AdminViewModel {
	 private List<AdminRoleDetailInfo> adminList;
	 private int pageCount;
	
	public List<AdminRoleDetailInfo> getAdminList() {
		return adminList;
	}
	public void setAdminList(List<AdminRoleDetailInfo> adminList) {
		this.adminList = adminList;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	@Override
	public String toString() {
		return "AdminViewModel [adminList=" + adminList + ", pageCount=" + pageCount + "]";
	}
	    
}
