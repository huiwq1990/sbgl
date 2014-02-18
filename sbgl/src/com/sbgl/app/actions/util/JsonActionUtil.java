package com.sbgl.app.actions.util;

import net.sf.json.JSONObject;

import com.sbgl.util.ReturnJson;

public class JsonActionUtil {

//	error return flag
	public final static int ajaxerrorreturn = 0;
	public final static int ajaxsuccessreturn = 1;
	
	public final static int ajaxadminnotloginreturn = 2;
	
	public final static int ajaxusernotloginreturn = 3;
	

//	构造返回字符串
	public static String buildReturnStr(int flag,String errorStr){
		ReturnJson returnJson = new ReturnJson();
		returnJson.setFlag(flag);			
		returnJson.setReason(errorStr);
		
		JSONObject jo = JSONObject.fromObject(returnJson);
		
		return jo.toString();
	}
	
//	构造返回字符串
//	public static String buildReturnStr(int flag,String errorStr){
//		ReturnJson returnJson = new ReturnJson();
//		returnJson.setFlag(flag);			
//		returnJson.setReason(errorStr);
//		
//		JSONObject jo = JSONObject.fromObject(returnJson);
//		
//		return jo.toString();
//	}
}
