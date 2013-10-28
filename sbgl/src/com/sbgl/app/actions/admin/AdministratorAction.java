package com.sbgl.app.actions.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.services.admin.AdministratorService;


@Scope("prototype") 
@Controller("AdministratorAction")
public class AdministratorAction extends ActionSupport implements SessionAware  {
	
	private static final Log log = LogFactory.getLog(AdministratorAction.class);
	private Map<String, Object> session;
	
	private  com.sbgl.app.entity.Administrator administrator;
	
	@Resource
	private AdministratorService administratorService;
	
	/** 
	 * 添加
	 * add by wm 
	 */  
	public String addAdministrator(){	
		try{
			administratorService.addAdministrator(administrator);
		}catch(Exception e){
			e.printStackTrace();
			log.error("AdministratorAction��addAdministrator����error"+e);
		}
		return SUCCESS;
	}
	
	
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	    this.session = session;
	}


	public com.sbgl.app.entity.Administrator getAdministrator() {
		return administrator;
	}


	public void setAdministrator(com.sbgl.app.entity.Administrator administrator) {
		this.administrator = administrator;
	}
}
