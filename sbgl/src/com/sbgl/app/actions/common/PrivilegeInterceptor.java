package com.sbgl.app.actions.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sbgl.app.entity.Loginuser;

public class PrivilegeInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5590371833994363669L;
	private HttpSession session;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();
		
		String url = request.getRequestURL().toString();
//		int index1 = url.indexOf("account");
//		int index2 = url.indexOf("passwordSet");
		
		Loginuser user = (Loginuser) session.getAttribute(CommonConfig.sessionuser);
		Boolean isFirst = (Boolean) session.getAttribute("isFirst");
		if(isFirst != null && isFirst) {
			return "firstSetup";
		}
		if(user != null && "1".equals(user.getPrivilege())) {
			return invocation.invoke();
		} else if(user == null) {
			return "login";
		} else {
			return "noPrivilege";
		}
	}

}
