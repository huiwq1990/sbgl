package com.sbgl.app.actions.common;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sbgl.app.entity.Loginuser;

public class UserIntercepor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1928412476749781053L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> map = invocation.getInvocationContext().getSession();
		Loginuser user = (Loginuser) map.get("loginUser");
		if(user != null && "3".equals(user.getRoletype()) && ("1".equals(user.getPrivilege()) || "2".equals(user.getPrivilege()))) {
			return invocation.invoke();
		} else if(user == null) {
			return "login";
		} else {
			return "noPrivilege";
		}
		
	}
}
