package com.netnoss.www.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netnoss.www.entity.AdminDetailInfo;
import com.netnoss.www.entity.AdminInfo;
import com.netnoss.www.service.UserService;
import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;
/**
 * login Action
 * @author Kevin Ren
 *
 */
@Controller
@RequestMapping(value="/user")
public class UserAction {
	@Resource(name="userServiceImpl")
	private UserService userService;
	/**
	 * login Metode machen
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/tologin.do")
	@ResponseBody
	public ResultData<AdminInfo> toLogin(HttpServletRequest request,HttpServletResponse response){
		//get parameter
		String adminCode=request.getParameter("admin_code").trim();
		String password=request.getParameter("pwd").trim();
		String checkCode=request.getParameter("checkCode").trim().toUpperCase();
		HttpSession session=request.getSession();
		String checkCodeSession=(String)session.getAttribute("checkCode");
		ResultData<AdminInfo> rd=userService.checkLogin(adminCode,password,checkCode,checkCodeSession);
		if(rd.getStatus()==StatusCode.SUCCESS){
			//get privilege
			ResultData<String> admin_privileges=userService.findRolePriviByUser(rd.getData().getId());
			String username=rd.getData().getAdmin_code();
			//save admin_code to session
			session.setAttribute("admin_code", username);
			session.setAttribute(username, admin_privileges.getData());
		}
		return rd;
	};
	/**
	 * Code method kontrollieren
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/checkCode.do")
	@ResponseBody
	public ResultData<AdminInfo> checkInCode(HttpServletRequest request,HttpServletResponse response){
		String checkCode=request.getParameter("checkCode").trim().toUpperCase();
		HttpSession session=request.getSession();
		String checkCodeSession=(String)session.getAttribute("checkCode");
		ResultData<AdminInfo> rd=userService.checkCode(checkCode, checkCodeSession);
		return rd;
	}
	
	/**
	 * get  validation codes
	 * @author kevin
	 *
	 */
	@RequestMapping("/getCode.do")
	public void getCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ResultData<BufferedImage> rd=userService.getValidCode(); 
		//Code werde in den Session schreiben
        HttpSession session=request.getSession();
        session.setAttribute("checkCode", rd.getMsg());
        response.setContentType("image/jpeg");
        OutputStream os=response.getOutputStream();
        ImageIO.write(rd.getData(),"png",os);
        os.close();
		
	}
	/**
	 * modify pwd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/modifypwd.do")
	@ResponseBody
	public ResultData<String> modifyUserPwd(HttpServletRequest request,HttpServletResponse response){
		String admin_code=(String)request.getAttribute("admin_code");
		String aktueallPwd=request.getParameter("aktuell_pwd");
		String newPwd=request.getParameter("neu_pwd");
		ResultData<String> resultData=userService.modifyUserPasswrod(admin_code, aktueallPwd, newPwd);
		return resultData;
		
	}
	@RequestMapping("/findAdminInfo.do")
	@ResponseBody
	public ResultData<AdminDetailInfo> findAdminInfo(HttpServletRequest request,HttpServletResponse response){
		 String amdinCode=(String)request.getAttribute("admin_code");
		 ResultData<AdminDetailInfo> resultData=userService.findLoginAdminInfo(amdinCode);
		return resultData;
	}
	@RequestMapping("/modifyAdminInfo.do")
	@ResponseBody
	public ResultData<AdminInfo> modifyAdminInfo(HttpServletRequest request,HttpServletResponse response){
		String adminCode=(String)request.getAttribute("admin_code");
		String name=request.getParameter("userName");
		String telefonNummer=request.getParameter("telefonNummer");
		String email=request.getParameter("email");
		AdminInfo adminInfo=new AdminInfo();
		adminInfo.setAdmin_code(adminCode);
		adminInfo.setName(name);
		adminInfo.setEmail(email);
		adminInfo.setTelephone(telefonNummer);
		ResultData<AdminInfo> resultData=userService.modifyUserInfo(adminInfo);
		return resultData;
	}
	
	@RequestMapping("/load_page.do")
	@ResponseBody
	public ResultData<String> loadPage(HttpServletRequest request,HttpServletResponse response){
		ResultData<String> resultData=new ResultData<String>();
		String url=request.getParameter("url");
		resultData.setStatus(StatusCode.SUCCESS);
		resultData.setMsg("Sie erlauben system");
		resultData.setData(url);
		return resultData;
	}
}
