package com.netnoss.www.view;

import java.util.List;

public class BillViewModel<T> {
	private List<T> billDetailInfoList;
	private int pageCount;
	public List<T> getBillDetailInfoList() {
		return billDetailInfoList;
	}
	public void setBillDetailInfoList(List<T> billDetailInfoList) {
		this.billDetailInfoList = billDetailInfoList;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	@Override
	public String toString() {
		return "BillViewModel [billDetailInfoList=" + billDetailInfoList + ", pageCount=" + pageCount + "]";
	}
	
	
}
