package com.sbgl.app.actions.common;

import java.util.HashMap;
import java.util.Map;

import com.sbgl.app.common.computer.ComputerConfig;

public class CommonActionUtil {

	
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
	public static int getLanguagetype(Integer str){
		
		if(str==null || str!=1){
			return ComputerConfig.languagech;
		}
		
		return ComputerConfig.languageen;
	}
	
	
	public static Map<Integer,String>  getUserRole(int language){
		Map<Integer,String> userRoleMapCh = new HashMap<Integer,String>(){{
		       put( 1 ,  "学生" );
		       put( 2 ,  "教师" );
		       put( 3 ,  "校外人员" );
		}};
		
		Map<Integer,String> userRoleMapEn = new HashMap<Integer,String>(){{
		       put( 1 ,  "Student" );
		       put( 2 ,  "Teacher" );
		       put( 3 ,  "Other" );
		}};
		
		if(language==CommonConfig.languageen){
			return userRoleMapEn;
		}
		return userRoleMapCh;
	}
}
