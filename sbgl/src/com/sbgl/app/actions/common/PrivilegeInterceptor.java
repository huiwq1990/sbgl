package com.sbgl.app.actions.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.entity.Userlogininfo;
import com.sbgl.app.services.login.LoginService;
import com.sbgl.app.services.user.ManagerService;
import com.sbgl.app.services.user.UserlogininfoService;
import com.sbgl.util.CookiesUtil;

public class PrivilegeInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5590371833994363669L;
	private HttpSession session;
	@Resource
	private LoginService loginService;
	@Resource
	private ManagerService managerService;
	@Resource
	private UserlogininfoService loginInfoService;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		session = request.getSession();
		
		String rember = CookiesUtil.getCookie("rember");
		
		String url = request.getRequestURL().toString();
		int index1 = url.indexOf("firstSetup");
		int index2 = url.indexOf("passwordSet");
		int index3 = url.indexOf("account");
		int index4 = url.indexOf("login");
		
		Loginuser user = (Loginuser) session.getAttribute(CommonConfig.sessionuser);
		if(user == null) {
			String uid = CookiesUtil.getCookie("uid");
			if(uid != null) {
				user = loginService.checkUser( Integer.valueOf(uid) );
				//如果还是未找到cookie中存储的用户，删除之
				if( user == null ) {
					CookiesUtil.removeCookie("uid");
					CookiesUtil.removeCookie("userpass");
					CookiesUtil.removeCookie("userid");
					CookiesUtil.removeCookie("pageLan");
					CookiesUtil.removeCookie("rember");
					
					return "login";
				} else if( !CookiesUtil.getCookie("userpass").equals( user.getPassword() ) ) {
					CookiesUtil.removeCookie("uid");
					CookiesUtil.removeCookie("userpass");
					CookiesUtil.removeCookie("userid");
					CookiesUtil.removeCookie("pageLan");
					CookiesUtil.removeCookie("rember");
					
					return "login";
				} 
				/*else {
					CookiesUtil.addLoginCookie("uid", String.valueOf(user.getId()));
					CookiesUtil.addLoginCookie("userpass", user.getPassword());
					CookiesUtil.addLoginCookie(CommonConfig.cookieuserid, String.valueOf(user.getUserid()));
				}*/
				
				Userlogininfo loginInfo = loginInfoService.getLoinInfoByUserId( Integer.valueOf(uid) );
				if(loginInfo == null) {
					return "firstSetup";
				} else {
					session.setAttribute(CommonConfig.sessionLanguagetype, loginInfo.getPagelanguage());
					CookiesUtil.addLoginCookie("pageLan", loginInfo.getPagelanguage());
				}
				
			} else {
				return "login";
			}
			Boolean isFirst = (Boolean) session.getAttribute("isFirst");
			if(isFirst != null && isFirst) {
				return "firstSetup";
			}
			Boolean isAdmin = managerService.isExistManagerCode( user.getUserid() );
			if(isAdmin) {
				user.setPrivilege("1");
			} else {
				user.setPrivilege("0");
			}
			session.setAttribute(CommonConfig.sessionuser, user);
			if(index1 != -1 || index2 != -1 || index3 != -1 ) {
				return invocation.invoke();
			} else if(index4 != -1) {
				//如果是要求过持续登录的
				if( "1".equals(rember) ) {
					if(isAdmin) {
						response.sendRedirect("adminIndex.action");
					} else {
						response.sendRedirect("index.action");
					}
					return invocation.invoke();
				}
			}
		}
		Boolean isAdmin = managerService.isExistManagerCode( user.getUserid() );
		if(isAdmin) {
			user.setPrivilege("1");
			//如果是要求过持续登录的
			if( "1".equals(rember) && index4 != -1 ) {
				if(isAdmin) {
					response.sendRedirect("adminIndex.action");
				} else {
					response.sendRedirect("index.action");
				}
			}
			return invocation.invoke();
		} else {
			user.setPrivilege("0");
			//如果是要求过持续登录的
			if( "1".equals(rember) && index4 != -1 ) {
				if(isAdmin) {
					response.sendRedirect("adminIndex.action");
				} else {
					response.sendRedirect("index.action");
				}
			}
			session.setAttribute(CommonConfig.sessionuser, user);
			return "noPrivilege";
		}
	}
}
