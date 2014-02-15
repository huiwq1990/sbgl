package com.sbgl.app.actions.login;

import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.services.login.LoginService;
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
			if(loginUser2!=null){
				CookiesUtil.addLoginCookie("id", loginUser2.getId().toString());
				CookiesUtil.addLoginCookie("userid", loginUser2.getUserid());
				CookiesUtil.addLoginCookie("username", loginUser2.getName());
				CookiesUtil.addLoginCookie("roletype", loginUser2.getRoletype());
				flag = true;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();			
		}
		Javascript(flag);
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
	
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	    this.session = session;
	}

	public Loginuser getLoginUser() {
		return loginuser;
	}

	public void setLoginUser(Loginuser loginuser) {
		this.loginuser = loginuser;
	}


}
