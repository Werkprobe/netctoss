package com.netnoss.www.error;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class ExceptionBean {
	 Logger logger=Logger.getLogger(ExceptionBean.class);
	 
	@AfterThrowing(pointcut="within(com.netnoss.www.service.impl.*)",throwing="e")
	public void exec(Exception e){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr=sdf.format(new Date());
		logger.error("==============Start"+timeStr+"============");
		logger.error("Error-type"+e);
		logger.error("Error-detail:");
		StackTraceElement[] ste=e.getStackTrace();
		for(StackTraceElement ele:ste){
			logger.error(ele.toString());
		}
		timeStr=sdf.format(new Date());
		logger.error("==============End"+timeStr+"============");
	}
}
