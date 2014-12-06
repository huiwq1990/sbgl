package com.sbgl.app.services.sms.impl;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import com.ruanwei.interfacej.KarlosSmsTest;
import com.sbgl.app.entity.SmsOverage;
import com.sbgl.app.exception.SmsException;
import com.sbgl.app.services.sms.SmsService;


@Scope("prototype") 
@Service("smsService")
@Transactional
public class KarlosSmsService implements SmsService{
	
	public void sendMsg(String str){
		
	}

	@Override
	public SmsOverage smsOverage(){ 
		// TODO Auto-generated method stub
		return KarlosSmsTest.overage();
	}
}
