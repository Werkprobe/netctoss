package com.netnoss.www.entity;

public class BillDeatil {
	private int id;
	private String real_name;
	private String idcard_no;
	private String login_name;
	private String bill_month;
	private double cost;
	private String parment_mode;
	private String pay_state;
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getIdcard_no() {
		return idcard_no;
	}
	public void setIdcard_no(String idcard_no) {
		this.idcard_no = idcard_no;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getBill_month() {
		return bill_month;
	}
	public void setBill_month(String bill_month) {
		this.bill_month = bill_month;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getParment_mode() {
		return parment_mode;
	}
	public void setParment_mode(String parment_mode) {
		this.parment_mode = parment_mode;
	}
	public String getPay_state() {
		return pay_state;
	}
	public void setPay_state(String pay_state) {
		this.pay_state = pay_state;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "BillDeatil [id=" + id + ", real_name=" + real_name + ", idcard_no=" + idcard_no + ", login_name="
				+ login_name + ", bill_month=" + bill_month + ", cost=" + cost + ", parment_mode=" + parment_mode
				+ ", pay_state=" + pay_state + "]";
	}
	
}
