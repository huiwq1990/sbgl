package com.sbgl.app.actions.common;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.common.SBGLConsistent;

public class EquipIntercepor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -489724138806466832L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> map = invocation.getInvocationContext().getSession();
		Loginuser user = (Loginuser) map.get("loginUser");
		//用户权限，1为系统管理员；2为系秘；3为器材管理员；4为机房管理员
		if(user != null && "3".equals(user.getRoletype()) && ("1".equals(user.getPrivilege()) || "2".equals(user.getPrivilege()) || "3".equals(user.getPrivilege()))) {
			return invocation.invoke();
		} else if(user == null) {
			return "login";
		} else {
			return "noPrivilege";
		}
	}

}
