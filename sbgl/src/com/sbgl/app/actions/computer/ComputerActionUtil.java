package com.sbgl.app.actions.computer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.ComputercategoryFull;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.util.ReturnJson;

public class ComputerActionUtil {
	
	public static HashMap<Integer, ArrayList<Computermodel>> categoryModelMap(List<Computercategory> computercategoryList,List<Computermodel>  computermodelList){
		//model的分类信息，只显示中文的
//		String categorysqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";
//		List<ComputercategoryFull> tempComputercategoryFullList  = computercategoryService.selectComputercategoryFullByCondition(categorysqlch);
		HashMap<Integer, ArrayList<Computermodel>> computermodelByComputercategoryId = new HashMap<Integer,ArrayList<Computermodel>>();

		for(Computermodel computermodel : computermodelList){
			int computercategoryType = computermodel.getComputercategoryid();
			if(!computermodelByComputercategoryId.containsKey(computercategoryType)){		
				computermodelByComputercategoryId.put(computercategoryType, new ArrayList<Computermodel>());
			}
			computermodelByComputercategoryId.get(computercategoryType).add(computermodel);			
		}
		
		
		for(Computercategory ca : computercategoryList){
			int categorytype = ca.getComputercategorytype();
			if(!computermodelByComputercategoryId.containsKey(categorytype)){
				computermodelByComputercategoryId.put(categorytype, new ArrayList<Computermodel>());
			}
		}
		
		return computermodelByComputercategoryId;
	}


	
	/**
	 * 获取当前语言
	 * @param str
	 * @return
	 */
	public static int getLanguagetype(String str){
		if(str==null || !str.trim().equals("1")){
			return ComputerConfig.languagech;
		}
		
		return ComputerConfig.languageen;
	}
	
	public static String getLanguagetypeStr(String str){
		if(str==null || !str.trim().equals("1")){
			return ComputerConfig.languageenStr;
		}
		
		return ComputerConfig.languagechStr;
	}
	

	
	
	/**
	 * 获取当前用户
	 *
	 * @return
	 */
	public static String getUserIdFromCookie(Cookie[] cookies ){	
		String uidStr = ComputerCookieUtil.getCookieValue(cookies, ComputerConfig.cookieuserid);	
		if(uidStr == null ){
			return "0";
		}
		return uidStr;
	}
	
	/**
	 * 获取当前用户
	 *
	 * @return
	 */
	public static String getUserId(HttpServletRequest re ){
		String uidStr = getUserIdFromCookie(re.getCookies());
		
		return uidStr;
	}

	
	
	public static String buildReturnStr(int flag,String errorStr){
		ReturnJson returnJson = new ReturnJson();
		returnJson.setFlag(flag);			
		returnJson.setReason(errorStr);
		
		JSONObject jo = JSONObject.fromObject(returnJson);
		
		return jo.toString();
	}
	
}
