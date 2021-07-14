package com.netnoss.www.entity;

public class Bill {
	private int id;
	private int account_id;
	private String bill_month;
	private double cost;
	private String parment_mode;
	private String pay_state;
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
	
}
