package com.netnoss.www.entity;

public class Account {
	 private int id;
	 private int recommender_id;
	 private String login_name;
	 private String login_passwd;
	 private int status;
	 private String create_date;
	 private String pause_date;
	 private String close_date;
	 private String real_name;
	 private String idcard_no;
	 private String birthdate;
	 private int gender;
	 private String occupation;
	 private String telephone;
	 private String email;
	 private String mailaddress;
	 private String zipcode;
	 private String last_login_time;
	 private String last_login_ip;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRecommender_id() {
		return recommender_id;
	}
	public void setRecommender_id(int recommender_id) {
		this.recommender_id = recommender_id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getLogin_passwd() {
		return login_passwd;
	}
	public void setLogin_passwd(String login_passwd) {
		this.login_passwd = login_passwd;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getPause_date() {
		return pause_date;
	}
	public void setPause_date(String pause_date) {
		this.pause_date = pause_date;
	}
	public String getClose_date() {
		return close_date;
	}
	public void setClose_date(String close_date) {
		this.close_date = close_date;
	}
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
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMailaddress() {
		return mailaddress;
	}
	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}
	public String getLast_login_ip() {
		return last_login_ip;
	}
	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", recommender_id=" + recommender_id + ", login_name=" + login_name
				+ ", login_passwd=" + login_passwd + ", status=" + status + ", create_date=" + create_date
				+ ", pause_date=" + pause_date + ", close_date=" + close_date + ", real_name=" + real_name
				+ ", idcard_no=" + idcard_no + ", birthdate=" + birthdate + ", gender=" + gender + ", occupation="
				+ occupation + ", telephone=" + telephone + ", email=" + email + ", mailaddress=" + mailaddress
				+ ", zipcode=" + zipcode + ", last_login_time=" + last_login_time + ", last_login_ip=" + last_login_ip
				+ "]";
	}
}
