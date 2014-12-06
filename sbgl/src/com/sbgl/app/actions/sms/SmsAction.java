package com.sbgl.app.actions.sms;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.entity.Computer;
import com.sbgl.app.entity.SmsOverage;
import com.sbgl.app.services.sms.SmsService;

@Scope("prototype") 
@Controller("SmsAction")
public class SmsAction extends BaseAction implements ModelDriven<SmsOverage>{

	private SmsService smsService;
	
	private SmsOverage smsOverage;
	
	public String smsManage(){
		smsOverage = smsService.smsOverage();
		return SUCCESS;		
	}
	
	

	@Override
	public SmsOverage getModel() {
		// TODO Auto-generated method stub
		return smsOverage;
	}



	public SmsService getSmsService() {
		return smsService;
	}



	public void setSmsService(SmsService smsService) {
		this.smsService = smsService;
	}



	public SmsOverage getSmsOverage() {
		return smsOverage;
	}



	public void setSmsOverage(SmsOverage smsOverage) {
		this.smsOverage = smsOverage;
	}
	
	
}
