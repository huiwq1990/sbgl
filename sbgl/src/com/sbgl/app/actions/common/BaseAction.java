package com.sbgl.app.actions.common;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.actions.computer.ComputerActionUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.entity.Loginuser;

public class BaseAction extends ActionSupport implements SessionAware{
	
	private Map<String, Object> session;
	
	
	
	public int getCurrentUserId(){		
		Loginuser lu =  (Loginuser) session.get(CommonConfig.sessionuser);
//		return lu.getUserId();
		return 1;
	}
	
	public Loginuser getCurrentUser(){		
		Loginuser lu =  (Loginuser) session.get(CommonConfig.sessionuser);
		return lu;
	}
	
	public int getCurrentLanguage(){
		return CommonActionUtil.getLanguagetype((String) session.get(ComputerConfig.sessionLanguagetype));		
	}
	
	
	
	
	
	

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	

}
