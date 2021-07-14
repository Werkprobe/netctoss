package com.netnoss.www.dao;


import com.netnoss.www.entity.AdminDetailInfo;
import com.netnoss.www.entity.AdminInfo;

public interface UserDao {
	/**
	 * finden userInfo
	 * @param adminCode
	 * @return
	 */
	public AdminInfo findByName(String adminCode);
	/**
	 * moidy User
	 * @param modifyUserPwd
	 */
	public void modifyUserPwd(AdminInfo adminInfo);
	/**
	 * modify LoginUser Info
	 * @param adminInfo
	 */
	public void modifyUserInfo(AdminInfo adminInfo);
	/**
	 * finden userdetail information
	 * @param adminCode
	 */
	public AdminDetailInfo findAdminDetailInfo(String adminCode);
	/**
	 * find user privilege
	 * @param admin_id
	 * @return  List<Integer>
	 */
	public String findRolesByUser(int admin_id);
}
