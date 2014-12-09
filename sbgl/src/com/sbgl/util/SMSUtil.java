package com.sbgl.util;

import java.util.HashMap;
import java.util.Map;

import com.ruanwei.interfacej.KarlosSmsTest;
import com.sbgl.app.exception.SmsException;
import com.sbgl.common.SMSTemplate;

public class SMSUtil {
	
	
	public static void sendSMS(String telephone,String templateName,Map<String, String> varValMap) throws SmsException {
		String templateStr="";

		templateStr = SMSTemplate.getSMSTemplate(templateName);
		
		String sendMsgStr = VelocityUtil.convStr(templateStr, varValMap);
		KarlosSmsTest.sendMsg(telephone, sendMsgStr);
		
	}
	
	
	public static void main(String[] args){
		
		Map<String,String> varValMap = new HashMap<String,String>();
		String username="test";
		varValMap.put("username",username);
		varValMap.put("ordertime", DateUtil.dateFormat(DateUtil.currentDate(),DateUtil.dateformatstr1));
		
		try {
			sendSMS("18810846401","pc_order_audit_pass",varValMap);
		} catch (SmsException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
