package com.netnoss.www.view;

import java.util.List;


import com.netnoss.www.entity.ReportInfo;

public class ResportViewModel {
	private List<ReportInfo> resportList;
	private int pageCount;
	public List<ReportInfo> getResportList() {
		return resportList;
	}
	public void setResportList(List<ReportInfo> resportList) {
		this.resportList = resportList;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
}
