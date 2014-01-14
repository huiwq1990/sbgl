package com.sbgl.app.actions.computer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.ComputercategoryFull;
import com.sbgl.app.entity.Computermodel;

public class ComputerActionUtil {
	
	public static HashMap<Integer, ArrayList<Computermodel>> categoryModelMap(List<Computermodel>  computermodelList){
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
	
	/**
	 * 获取当前语言
	 *
	 * @return
	 */
	public static String getUserIdFromCookie(Cookie[] cookies ){	
		String uidStr = ComputerCookieUtil.getCookieValue(cookies, ComputerConfig.cookieuserid);	
		if(uidStr == null ){
			return "";
		}
		return uidStr;
	}

}
