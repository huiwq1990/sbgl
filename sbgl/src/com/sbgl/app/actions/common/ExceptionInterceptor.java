package com.sbgl.app.actions.common;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.services.login.LoginService;
import com.sbgl.app.services.user.ManagerService;
import com.sbgl.util.CookiesUtil;

public class ExceptionInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	@Resource
	private LoginService loginService;
	@Resource
	private ManagerService managerService;
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
		HttpServletResponse response = ServletActionContext.getResponse();
		session = request.getSession();
		String url = request.getRequestURL().toString();
		String uid = CookiesUtil.getCookie("uid");
		String pageLan = CookiesUtil.getCookie("pageLan");
		String rember = CookiesUtil.getCookie("rember");
		loginuser = (Loginuser)session.getAttribute(CommonConfig.sessionuser);
//		System.out.println("++++++++++++++++++++++++++++" + loginuser!=null?loginuser.getUserid():"未找到");
		Boolean isFirst = (Boolean) session.getAttribute("isFirst");
		if(isFirst != null && isFirst) {
			return "firstSetup";
		}
		if( uid == null && loginuser == null && url.indexOf("doLogin") == -1) {
			return "login";
		} else if(uid != null && loginuser == null) {
			loginuser = loginService.checkUser( Integer.valueOf(uid) );
			//如果还是未找到cookie中存储的用户，删除之
			if( loginuser == null ) {
				CookiesUtil.removeCookie("uid");
				CookiesUtil.removeCookie("userpass");
				CookiesUtil.removeCookie("userid");
				CookiesUtil.removeCookie("pageLan");
				CookiesUtil.removeCookie("rember");
				
				return "login";
			} else if( !CookiesUtil.getCookie("userpass").equals( loginuser.getPassword() ) || "0".equals(rember) ) {
				CookiesUtil.removeCookie("uid");
				CookiesUtil.removeCookie("userpass");
				CookiesUtil.removeCookie("userid");
				CookiesUtil.removeCookie("pageLan");
				CookiesUtil.removeCookie("rember");
				
				return "login";
			}
			
			Boolean isAdmin = managerService.isExistManagerCode( loginuser.getUserid() );
			if(isAdmin) {
				loginuser.setPrivilege("1");
			} else {
				loginuser.setPrivilege("0");
			}
			
			session.setAttribute(CommonConfig.sessionuser, loginuser);
			session.setAttribute(CommonConfig.sessionLanguagetype, pageLan);
			//如果是要求过持续登录的
			if( "1".equals(rember) && url.indexOf("login") != -1 ) {
				if(isAdmin) {
					response.sendRedirect("adminIndex.action");
				} else {
					response.sendRedirect("index.action");
				}
			}
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
