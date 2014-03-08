package com.sbgl.app.actions.login;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.entity.Clazz;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.entity.Student;
import com.sbgl.app.services.login.LoginService;
import com.sbgl.app.services.user.ClazzService;
import com.sbgl.app.services.user.StudentService;
import com.sbgl.util.CookiesUtil;
import com.sbgl.util.JavascriptWriter;
import com.sbgl.util.MD5Util;
import com.sbgl.util.WebUtils;

@Scope("prototype") 
@Controller("LoginAction")
public class LoginAction extends ActionSupport {
	
	private static final Log log = LogFactory.getLog(LoginAction.class);
	private HttpSession session;
	private Loginuser loginuser;
	
	//显示用户基本信息用
	private String userName;
	private String gender;
	private String email;
	private String phone;
	private String classBelongTo;
	private String userId;
	
	@Resource
	private LoginService loginService;
	@Resource
	private ClazzService clazzService;
	@Resource
	private StudentService studentService;
	
	public String login(){
		return SUCCESS;
	}
	
	public String showUserInfo() {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();
		
		Loginuser user = (Loginuser) session.getAttribute("loginUser");
		if(user != null) {
			userId = user.getUserid();
			userName = user.getName();
			gender = "0".equals( user.getGender() ) ? "男" : "女";
			phone = user.getTelephone();
			email = user.getEmail();
			if( "1".equals( user.getRoletype() ) ) {
				Student stu = studentService.getStudentById( user.getId() );
				if( stu.getClassid() != null && stu.getClassid() != -1) {
					Clazz clazz = clazzService.getClazzById( stu.getClassid() );
					classBelongTo = clazz.getClassname();
				} else {
					classBelongTo = "-1";
				}
			}
			
		}
		
		return SUCCESS;
	}
	
	public void doLogin(){
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();
		Loginuser loginUser2 = new Loginuser();		
		boolean flag  = false;
		String loginType = "";
		try{	
			loginUser2 = loginService.findUser(loginuser);
			if(loginUser2 != null){
				CookiesUtil.addLoginCookie("uid", String.valueOf(loginUser2.getId()));
				CookiesUtil.addLoginCookie("userpass", MD5Util.MD5( loginUser2.getPassword() + loginUser2.getId().toString() ));
				CookiesUtil.addLoginCookie("userid", String.valueOf(loginUser2.getUserid()));
				flag = true;
				session.setAttribute("loginUser", loginUser2);
				if("3".equals(loginUser2.getRoletype())) {
					loginType = "admin";
				} else {
					loginType = "user";
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();			
		}
		Javascript(flag, loginType);
	}
	
	public void Javascript(boolean flag, String loginType){
		try{
			HttpServletResponse response = WebUtils.getHttpServletResponse();
			response.setContentType("text/html");
			response.setHeader("Cache-Control","no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter write =response.getWriter();
			if(flag){
				JavascriptWriter tjavascriptWriter = new JavascriptWriter(write);
				tjavascriptWriter.wirteToParent("succ", loginType);
			}else{
				JavascriptWriter tjavascriptWriter = new JavascriptWriter(write);
				tjavascriptWriter.wirteToParent("fail","用户名错误或密码错误！");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();			
		}
	}
	
	public String doLogout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();
		
		CookiesUtil.removeCookie("uid");
		CookiesUtil.removeCookie("userpass");
		CookiesUtil.removeCookie("userid");
		
		session.removeAttribute("loginUser");
		
		return SUCCESS;
	}
	
	

	public Loginuser getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(Loginuser loginuser) {
		this.loginuser = loginuser;
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getClassBelongTo() {
		return classBelongTo;
	}

	public void setClassBelongTo(String classBelongTo) {
		this.classBelongTo = classBelongTo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
