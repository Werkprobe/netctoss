package com.netnoss.www.interceptors;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.netnoss.www.util.ResultData;
import com.netnoss.www.util.StatusCode;

import net.sf.json.JSONObject;


public class LoginVerifyInterceptor implements HandlerInterceptor {
	private String path;
	private Properties props;
	public void setPath(String path) {
		this.path = path;
	}
	public void setProps(Properties props) {
		this.props = props;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		Object admin_code=session.getAttribute("admin_code");
		boolean bool=false;
		if(admin_code==null || "".equals(admin_code )){
				ResultData<String> resultData=new ResultData<String>();
				resultData.setStatus(StatusCode.FAIL_CODE_ERROR);
				resultData.setMsg("Bitte,Anmelden Sie");
				resultData.setData(request.getContextPath()+path);
				response.getWriter().println(JSONObject.fromObject(resultData).toString());
				bool=false;
		}else{
			String privileges=(String)session.getAttribute((String)admin_code);
			String user_oballow=request.getParameter("allow");
			String[] privilegeArr=privileges.split(",");
			for (String privilege: privilegeArr){
				String allow=props.getProperty("ROLE_"+privilege);
				if(null==allow || "".equals(allow)){
					continue;
				}else if(allow.equals(user_oballow)){
					bool=true;
					break;
				}
			}
			if(!bool){
				if("all".equals(user_oballow)){
					bool=true;
				}else{
					ResultData<String> resultData=new ResultData<String>();
					resultData.setStatus(StatusCode.FAIL_CODE_ERROR);
					resultData.setMsg("Bitte,Anmelden Sie");
					resultData.setData(request.getContextPath()+"/pages/nopower.html");
					response.getWriter().println(JSONObject.fromObject(resultData).toString());
				}
			}
		}
		request.setAttribute("admin_code", admin_code);
		return bool;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
