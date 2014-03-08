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
		
		Loginuser user = (Loginuser) session.getAttribute("loginUser");
		if(user != null && "3".equals(user.getRoletype())) {
			return invocation.invoke();
		} else if(user == null) {
			return "login";
		} else {
			return "noPrivilege";
		}
	}

}