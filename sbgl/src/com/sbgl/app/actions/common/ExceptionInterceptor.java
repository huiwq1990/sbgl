package com.sbgl.app.actions.common;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1867067164261737940L;
	private String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		try {
			arg0.invoke();
		} catch(Exception e) {
			e.printStackTrace();
			
			if(e instanceof ClassNotFoundException) {
				errorMsg = "请求的页面不存在，检查后重试！";
			}
			return "exception-error";
		}
		return null;
	}
	
}
