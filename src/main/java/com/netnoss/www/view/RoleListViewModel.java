package com.netnoss.www.view;

import java.util.List;

import com.netnoss.www.entity.RoleInfo;


public class RoleListViewModel {
    private List<RoleInfo> roleList;
    private int pageCount;
	
	public List<RoleInfo> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<RoleInfo> roleList) {
		this.roleList = roleList;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	@Override
	public String toString() {
		return "RoleListViewModel [roleList=" + roleList + ", pageCount=" + pageCount + "]";
	}
	
}
