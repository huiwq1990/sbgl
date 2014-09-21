package com.sbgl.app.actions.login;

import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.entity.Clazz;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.entity.Student;
import com.sbgl.app.entity.Userlogininfo;
import com.sbgl.app.services.login.LoginService;
import com.sbgl.app.services.user.ClazzService;
import com.sbgl.app.services.user.ManagerService;
import com.sbgl.app.services.user.StudentService;
import com.sbgl.app.services.user.UserlogininfoService;
import com.sbgl.util.CardPassUtil;
import com.sbgl.util.CookiesUtil;
import com.sbgl.util.JavascriptWriter;
import com.sbgl.util.WebUtils;

@Scope("prototype") 
@Controller("LoginAction")
public class LoginAction extends ActionSupport {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	private static final Log log = LogFactory.getLog(LoginAction.class);
	private HttpSession session;
	private Loginuser loginuser;
	private Integer rember;
	
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
	@Resource
	private ManagerService managerService;
	@Resource
	private UserlogininfoService loginInfoService;
	
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
			//兼容以前密码没有加密的情况
			loginUser2 = loginService.findUser(loginuser);
			if(loginUser2 == null) {
				loginuser.setPassword( CardPassUtil.encrypt(loginuser.getPassword()) );
				loginUser2 = loginService.findUser(loginuser);
			}
			
			if(loginUser2 != null) {
				CookiesUtil.addLoginCookie("uid", String.valueOf(loginUser2.getId()));
				CookiesUtil.addLoginCookie("userpass", loginUser2.getPassword());
				CookiesUtil.addLoginCookie(CommonConfig.cookieuserid, String.valueOf(loginUser2.getUserid()));
				
				Boolean isAdmin = managerService.isExistManagerCode( loginUser2.getUserid() );
				Userlogininfo loginInfo = loginInfoService.getLoinInfoByUserId( loginUser2.getId() );
				
				if(isAdmin) {
					loginType = "admin";
					loginUser2.setPrivilege("1");
				} else {
					loginType = "user";
					loginUser2.setPrivilege("0");
				}
				
				if(loginInfo == null) {
					loginInfo = new Userlogininfo();
					loginInfo.setUserid( loginUser2.getId() );
					loginInfo.setIsfirstlogin("true");
					loginInfo.setLastlogintime( new Date() );
					loginInfo.setLogincount(1);
					loginInfo.setPagelanguage("0");
					loginInfoService.addUserLoinInfo(loginInfo);
				}
				if( "1".equals( loginUser2.getRoletype() ) ) {	//如果是学生的话，在remark中记录班级信息
					Student stu = studentService.getStudentById( loginUser2.getId() );
					Clazz clazz = clazzService.getClazzById( stu.getClassid() );
					session.setAttribute( "stuClass",  clazz.getClassname() );
				}
				if( "true".equals( loginInfo.getIsfirstlogin() ) ) {
					loginType = "firstLogin";
					session.setAttribute("isFirst", true);
				} else {
					session.setAttribute("isFirst", false);
				}
				//更新用户登录信息
				if(loginInfo != null) {
					loginInfo.setLastlogintime( new Date() );
					Integer count = loginInfo.getLogincount();
					count = count == null ? 0 : ++count;
					loginInfo.setLogincount(count);
					loginInfoService.alterUserLoginInfo(loginInfo);
				}
				
				flag = true;
				loginUser2.setPassword( CardPassUtil.decrypt(loginUser2.getPassword()) );
				session.setAttribute(CommonConfig.sessionuser, loginUser2);
				session.setAttribute(CommonConfig.sessionLanguagetype, loginInfo.getPagelanguage());
				
				CookiesUtil.addLoginCookie("pageLan", loginInfo.getPagelanguage());
				//如果要求保持一周登录，写入保持状态cookie
				if(rember != null && rember == 1) {
					session.setAttribute("rember", 1);
					CookiesUtil.addLoginCookie("rember", "1");
				} else {
					//不记录用户登录
					session.setAttribute("rember", 0);
					CookiesUtil.addLoginCookie("rember", "0");
				}
				
			}
			
		}catch (Exception e) {
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
			e.printStackTrace();			
		}
	}
	
	public String doLogout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();
		
		Integer rember = (Integer) session.getAttribute("rember");
		
		if(rember != null && rember == 0) {
			//不记录登录信息，全部清除
			CookiesUtil.removeCookie("uid");
			CookiesUtil.removeCookie("userpass");
			CookiesUtil.removeCookie("userid");
			CookiesUtil.removeCookie("pageLan");
			CookiesUtil.removeCookie("rember");
			
			session.removeAttribute("loginUser");
			session.removeAttribute("stuClass");
			session.removeAttribute("rember");
		} else {
			session.removeAttribute("loginUser");
			session.removeAttribute("stuClass");
			session.removeAttribute("rember");
		}
		
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

	public Integer getRember() {
		return rember;
	}

	public void setRember(Integer rember) {
		this.rember = rember;
	}
	
	
}
