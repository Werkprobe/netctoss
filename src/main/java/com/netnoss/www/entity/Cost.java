package com.netnoss.www.entity;

public class Cost {
	private int id;
	private String name;
	private String base_duration;
	private String base_cost;
	private String unit_cost;
	private char status;
	private String descr;
	private String creatime;
	private String startime;
	private char costtype;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBase_duration() {
		return base_duration;
	}
	public void setBase_duration(String base_duration) {
		this.base_duration = base_duration;
	}
	public String getBase_cost() {
		return base_cost;
	}
	public void setBase_cost(String base_cost) {
		this.base_cost = base_cost;
	}
	public String getUnit_cost() {
		return unit_cost;
	}
	public void setUnit_cost(String unit_cost) {
		this.unit_cost = unit_cost;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getCreatime() {
		return creatime;
	}
	public void setCreatime(String creatime) {
		this.creatime = creatime;
	}
	
	public String getStartime() {
		return startime;
	}
	public void setStartime(String startime) {
		this.startime = startime;
	}
	public char getCosttype() {
		return costtype;
	}
	public void setCosttype(char costtype) {
		this.costtype = costtype;
	}
	@Override
	public String toString() {
		return "Cost [id=" + id + ", name=" + name + ", base_duration=" + base_duration + ", base_cost=" + base_cost
				+ ", unit_cost=" + unit_cost + ", status=" + status + ", descr=" + descr + ", creatime=" + creatime
				+ ", startime=" + startime + ", costtype=" + costtype + "]";
	}
	
}
