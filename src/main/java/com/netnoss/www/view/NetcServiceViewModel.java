package com.netnoss.www.view;

import java.util.List;

import com.netnoss.www.entity.ServiceDetailInfo;

public class NetcServiceViewModel {
	private List<ServiceDetailInfo> serviceDetailInfoList;
	private int pageCount;
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<ServiceDetailInfo> getServiceDetailInfoList() {
		return serviceDetailInfoList;
	}
	public void setServiceDetailInfoList(List<ServiceDetailInfo> serviceDetailInfoList) {
		this.serviceDetailInfoList = serviceDetailInfoList;
	}
	
}
