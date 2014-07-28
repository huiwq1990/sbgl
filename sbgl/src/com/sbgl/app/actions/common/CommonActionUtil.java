package com.sbgl.app.actions.common;

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
}
