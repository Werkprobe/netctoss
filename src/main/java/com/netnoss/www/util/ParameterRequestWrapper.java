package com.netnoss.www.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.catalina.util.ParameterMap;

/**
 * plus param in HttpServletRequest
 * 
 * @author kevin Ren
 *
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper{
	private ParameterMap<String, String[]> params;
	
	 @SuppressWarnings("all")
	public ParameterRequestWrapper(HttpServletRequest request) {
		super(request);
		params =(ParameterMap<String, String[]>)request.getParameterMap();
	}

	@Override
	public String getParameter(String name) {
		return super.getParameter(name);
	}

	@Override
	public String[] getParameterValues(String name) {
		return super.getParameterValues(name);
	}
	
	public void setParams(ParameterMap<String, String[]> params) {
		this.params = params;
	}
	

	/**
	 * plus param
	 * @param name
	 * @param value
	 */
	public void addParameter(String name, Object value) {
		if (value != null) {
			params.setLocked(false);
			if (value instanceof String[]) {
				params.put(name, (String[]) value);
			} else if (value instanceof String) {
				params.put(name, new String[] { (String) value });
			} else {
				params.put(name, new String[] { String.valueOf(value) });
			}
			params.setLocked(true);
		}
	}
}
