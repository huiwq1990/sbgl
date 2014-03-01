package com.sbgl.app.actions.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.services.login.LoginService;
import com.sbgl.util.CookiesUtil;

public class ExceptionInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	@Resource
	private LoginService loginService;
	private static final long serialVersionUID = 1867067164261737940L;
	private HttpSession session;
	private Loginuser loginuser;
	
	private String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();
		String url = request.getRequestURL().toString();
		String uid = CookiesUtil.getCookie("uid");
//		System.out.println("============================" + uid);
		loginuser = (Loginuser)session.getAttribute("loginUser");
//		System.out.println("++++++++++++++++++++++++++++" + loginuser!=null?loginuser.getUserid():"未找到");
		if( uid == null && loginuser == null && url.indexOf("doLogin") == -1) {
			return "login";
		} else if(uid != null && loginuser == null) {
			Loginuser l = new Loginuser();
			l.setUserid( CookiesUtil.getCookie("userid") );
			l.setPassword( CookiesUtil.getCookie("userpass") );
			loginuser = loginService.findUser(l);
			//如果还是未找到cookie中存储的用户，删除之
			if( loginuser == null ) {
				CookiesUtil.removeCookie("udi");
				CookiesUtil.removeCookie("userpass");
				CookiesUtil.removeCookie("userid");
			}
			session.setAttribute("loginUser", loginuser);
		}
		
		try {
			arg0.invoke();
		} catch(Exception e) {
			e.printStackTrace();
			
			if(e instanceof ClassNotFoundException) {
				errorMsg = "请求的页面不存在，检查后重试！";
			} else {
				errorMsg = "系统出错！请联系管理员";
			}
			return "exception-error";
		}
		return null;
	}
}
