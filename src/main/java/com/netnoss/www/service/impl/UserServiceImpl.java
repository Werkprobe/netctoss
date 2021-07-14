package com.netnoss.www.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netnoss.www.dao.UserDao;
import com.netnoss.www.entity.AdminDetailInfo;
import com.netnoss.www.entity.AdminInfo;
import com.netnoss.www.service.UserService;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;

@Service
public class UserServiceImpl implements UserService {
	@Resource(name="userDao")
	private UserDao userDao;
	@Transactional
	public ResultData<AdminInfo> checkLogin(String adminCode,String password,String checkCode,String checkCodeSession) {
		//check Code
		ResultData<AdminInfo> resultData=new ResultData<AdminInfo>();
		resultData=checkCode(checkCode,checkCodeSession);
		if(StatusCode.SUCCESS==resultData.getStatus()){
			AdminInfo amdinInfo=userDao.findByName(adminCode);
			if(null==amdinInfo){//isexist username
				resultData.setStatus(StatusCode.FAIL_EMPTY);
				resultData.setMsg("UserName ist keine Existenz.");
				resultData.setData(null);
			}else{//check password
				if(amdinInfo.getPassword().equals(password)){
					resultData.setStatus(StatusCode.SUCCESS);
					resultData.setMsg("UserName und Passwörter sind richtig.");
					resultData.setData(amdinInfo);
				}else{
					resultData.setStatus(StatusCode.FAIL_PWD_ERROR);
					resultData.setMsg("Passwörter ist falsch.");
					resultData.setData(null);
				}
			}
		}
		return resultData;
	}
	@Transactional
	public ResultData<AdminInfo> checkCode(String checkCode, String checkCodeSession) {
		ResultData<AdminInfo> resultData=new ResultData<AdminInfo>();
		if(checkCode.equals(checkCodeSession)){
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("Code ist richtig.");
			resultData.setData(null);
		}else{
			resultData.setStatus(StatusCode.FAIL_CODE_ERROR);
			resultData.setMsg("Code ist falsch.");
			resultData.setData(null);
		}
		return resultData;
	}

	public ResultData<BufferedImage> getValidCode() {
		BufferedImage bufImg=new BufferedImage(StatusCode.IMG_WIDTH, StatusCode.IMG_HEIGHT, BufferedImage.TYPE_INT_BGR);
		Graphics g=bufImg.getGraphics();//Graphics gründen
		//farbe malen für border
		Random r=new Random();//RGB  color
		g.setColor(Color.black);
        g.drawRect(0,0,StatusCode.IMG_WIDTH,StatusCode.IMG_HEIGHT);
		//farbe malen
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		g.fillRect(1,1,StatusCode.IMG_WIDTH,StatusCode.IMG_HEIGHT);
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
        g.setFont(new Font(null,Font.BOLD|Font.ITALIC,20));
        //Code malen 
        StringBuilder codeStr=new StringBuilder();
        for (int i = 0; i < StatusCode.IMG_NUM; i++) {	
            char c = StatusCode.IMG_CODE_SEQUENCE[r.nextInt(StatusCode.IMG_CODE_SEQUENCE.length)];
            codeStr.append(c);
            g.drawString(c+"  ",12*(i+1),StatusCode.IMG_HEIGHT/2+8);
        }
        //plus line
        g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
        for (int i = 0; i < StatusCode.IMG_LINECOUNT; i++) {
            g.drawLine(r.nextInt(StatusCode.IMG_WIDTH/2),r.nextInt(StatusCode.IMG_WIDTH/2),r.nextInt(StatusCode.IMG_WIDTH),r.nextInt(StatusCode.IMG_HEIGHT));
        }
        ResultData<BufferedImage> resultData=new ResultData<BufferedImage>();
        resultData.setStatus(StatusCode.IMG_CODE_SUCCESS);
        resultData.setMsg(codeStr.toString());
        resultData.setData(bufImg);
		return resultData;
	}
	@Transactional
	public ResultData<String> modifyUserPasswrod(String adminCode,String aktueallPwd,String newPwd) {
		ResultData<String> resultData=new ResultData<String>();
		//bekommen userInfo
		AdminInfo adminInfo=userDao.findByName(adminCode);
		if(adminInfo!=null){
			String pwd=adminInfo.getPassword();
			if(pwd.equals(aktueallPwd)){
				adminInfo.setPassword(newPwd);
				userDao.modifyUserPwd(adminInfo);
				resultData.setStatus(StatusCode.SUCCESS);
				resultData.setMsg("Änderung ist erfolgreich.");
				resultData.setData(null);
			}else{
				resultData.setStatus(StatusCode.FAIL_PWD_ERROR);
				resultData.setMsg("Aktuelles Passwort ist falsch. Änderung ist nicht erfolgreich.");
				resultData.setData(null);
			}
		}else{
			resultData.setStatus(StatusCode.FAIL_PWD_ERROR);
			resultData.setMsg("UserName ist keine Existenz. Änderung ist nicht erfolgreich.");
			resultData.setData(null);
		}
		return resultData;
	}
	@Transactional
	public ResultData<AdminDetailInfo> findLoginAdminInfo(String amdinCode) {
		ResultData<AdminDetailInfo> resultData=new ResultData<AdminDetailInfo>();
		AdminDetailInfo amdinDetailInfo=userDao.findAdminDetailInfo(amdinCode);
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("es gibt die KontoInformation .");
		resultData.setData(amdinDetailInfo);
		return resultData;
	}
	@Transactional
	public ResultData<AdminInfo> modifyUserInfo(AdminInfo adminInfo) {
		ResultData<AdminInfo> resultData=new ResultData<AdminInfo>();
		userDao.modifyUserInfo(adminInfo);
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("Änderung ist erfolgreich.");
		resultData.setData(null);
		return resultData;
	}
	@Transactional
	public ResultData<String> findRolePriviByUser(int admin_id) {
		ResultData<String> resultData=new ResultData<String>();
		String admin_privilege_list=userDao.findRolesByUser(admin_id);
		if(null==admin_privilege_list || ""==admin_privilege_list){
			resultData.setStatus(StatusCode.FAIL);
			resultData.setMsg("das User hat keine Role");
			resultData.setData(null);
		}else{
			resultData.setStatus(StatusCode.SUCCESS);
			resultData.setMsg("dieses User hat privilege bekommen");
			resultData.setData(admin_privilege_list);
		}
		return resultData;
	}
}
