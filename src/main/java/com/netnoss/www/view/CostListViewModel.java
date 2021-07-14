package com.netnoss.www.view;

import java.util.List;

import com.netnoss.www.entity.Cost;

public class CostListViewModel {
	private List<Cost> costList;
	private int pageCount;
	public List<Cost> getCostList() {
		return costList;
	}
	public void setCostList(List<Cost> costList) {
		this.costList = costList;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
}
