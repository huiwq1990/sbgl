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
		System.out.println(sendMsgStr);
		
		KarlosSmsTest.keyword(sendMsgStr);
		sendMsgStr ="【电影学院摄影系】张同学您好，您的机房预约申请已审核通过，预约号为1222，使用时段为9：00-10：00；请提前到机房管理办公室授权门卡，妥善安排好时间，遵守机房管理规定。";

		KarlosSmsTest.sendMsg(telephone, sendMsgStr);
		
	}
	
	
	public static void main(String[] args){
		
		Map<String,String> varValMap = new HashMap<String,String>();
		String username="test";
		varValMap.put("username",username);
		varValMap.put("ordertime", DateUtil.dateFormat(DateUtil.currentDate(),DateUtil.dateformatstr1));
		
		try {
			sendSMS("18600732005","pc_order_audit_pass",varValMap);
		} catch (SmsException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
