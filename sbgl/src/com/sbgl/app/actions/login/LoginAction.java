package com.sbgl.app.actions.login;

import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.services.login.LoginService;
import com.sbgl.common.SBGLConsistent;
import com.sbgl.util.CookiesUtil;
import com.sbgl.util.JavascriptWriter;
import com.sbgl.util.WebUtils;

@Scope("prototype") 
@Controller("LoginAction")
public class LoginAction extends ActionSupport implements SessionAware {
	
	private static final Log log = LogFactory.getLog(LoginAction.class);
	private Map<String, Object> session;
	private Loginuser loginuser;
	

	@Resource
	private LoginService loginService;
	
	public String login(){
		return SUCCESS;
	}
	
	public void doLogin(){		
		Loginuser loginUser2 = new Loginuser();		
		boolean flag  = false;
		try{	
			loginUser2 = loginService.findUser(loginuser);
			if(loginUser2 != null){
				CookiesUtil.addLoginCookie("uid", String.valueOf(loginUser2.getId()));
				CookiesUtil.addLoginCookie("userpass", loginUser2.getPassword());
				
				flag = true;
				session.put("loginUser", loginUser2);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();			
		}
		Javascript(flag);
	}
	
	public String doManagerLogin() {		
		Loginuser loginUser3 = new Loginuser();		
		try{	
			loginUser3 = loginService.findUser(loginuser);
			if(loginUser3 != null) {
				//id password cookie
				CookiesUtil.addLoginCookie("userid", loginUser3.getUserid());
				CookiesUtil.addLoginCookie("username", loginUser3.getName());
				CookiesUtil.addLoginCookie("roletype", loginUser3.getRoletype());
				CookiesUtil.addLoginCookie("privilege", loginUser3.getPrivilege());
				CookiesUtil.addLoginCookie("id", loginUser3.getId().toString());
				
				if( "100".equals(loginUser3.getRoletype()) ) {
					session.put("useType", SBGLConsistent.USER_TYPE_ADMIN1);
					return "super";
				} else if( "200".equals(loginUser3.getRoletype()) ) {
					session.put("useType", SBGLConsistent.USER_TYPE_ADMIN2);
					return "equip";
				} else if( "300".equals(loginUser3.getRoletype()) ) {
					session.put("useType", SBGLConsistent.USER_TYPE_ADMIN3);
					return "room";
				}
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();			
		}
		return "faild";
	}
	
	public void Javascript(boolean flag){
		try{
			HttpServletResponse response = WebUtils.getHttpServletResponse();
			PrintWriter write =response.getWriter();
			if(flag){
				JavascriptWriter tjavascriptWriter = new JavascriptWriter(write);
				tjavascriptWriter.wirteToParent("succ","21321");
			}else{
				JavascriptWriter tjavascriptWriter = new JavascriptWriter(write);
				tjavascriptWriter.wirteToParent("fail","没有找到此人！");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();			
		}
	}
	
	public String doLogout(){
		session.remove("Loginuser");
		return SUCCESS;
	}
	
	public String doManagerLogout(){
		session.remove("Loginuser");
		return SUCCESS;
	}
	

	public Loginuser getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(Loginuser loginuser) {
		this.loginuser = loginuser;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


}
