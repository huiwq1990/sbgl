package com.sbgl.app.actions.common;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sbgl.common.SBGLConsistent;

public class EquipIntercepor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -489724138806466832L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
//		Map<String, Object> map = invocation.getInvocationContext().getSession();
//		
//		if(null == map.get("userType")) {
//			return "login";
//		} else if( (Integer)map.get("userType") == SBGLConsistent.USER_TYPE_ADMIN1 || (Integer)map.get("userType") == SBGLConsistent.USER_TYPE_ADMIN2 ) {
//			return invocation.invoke();
//		} else {
//			return "denied";
//		}
		return invocation.invoke();
	}

}
