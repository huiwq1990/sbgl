package com.sbgl.app.services.sms;

import com.sbgl.app.entity.SmsOverage;

public interface SmsService {
	public void sendMsg(String str);
	public SmsOverage smsOverage();
}
