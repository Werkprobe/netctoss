package com.netnoss.www.service;

import java.awt.image.BufferedImage;

import com.netnoss.www.entity.AdminDetailInfo;
import com.netnoss.www.entity.AdminInfo;
import com.netnoss.www.util.ResultData;

public interface UserService {
	/**
	 * checkLogin ist richtig
	 * @param adminCode
	 * @param password
	 * @return  ResultData<AdminInfo>
	 */
	public ResultData<AdminInfo> checkLogin(String adminCode,String password,String checkCode,String checkCodeSession);
	/**
	 * check Code ist richtig
	 * @param checkCode
	 * @param checkCodeSession
	 * @return  ResultData<AdminInfo>
	 */
	public ResultData<AdminInfo> checkCode(String checkCode,String checkCodeSession);
	/**
	 * create validation codes. 
	 * @return ResultData<BufferedImage> 
	 */
	public ResultData<BufferedImage> getValidCode();
	/**
	 *  modify loginUser passwort
	 * @param adminCode
	 * @param aktueallPwd
	 * @param newPwd
	 * @return  ResultData<AdminInfo>
	 */
	public  ResultData<String> modifyUserPasswrod(String adminCode,String aktueallPwd,String newPwd);
	/**
	 *  find loginUser info
	 * @param amdinCode
	 * @return  ResultData<AdminInfo>
	 */
	public ResultData<AdminDetailInfo> findLoginAdminInfo(String amdinCode);
	/**
	 * modify losginUser info
	 * @param adminInfo
	 * @return  ResultData<AdminInfo>
	 */
	public ResultData<AdminInfo> modifyUserInfo(AdminInfo adminInfo);
	/**
	 * find Role Privilege
	 * @param admin_id
	 * @return ResultData<String>
	 */
	public ResultData<String> findRolePriviByUser(int admin_id);
}
