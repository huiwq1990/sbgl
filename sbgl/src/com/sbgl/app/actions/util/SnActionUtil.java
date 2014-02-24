package com.sbgl.app.actions.util;

import java.util.Date;

public class SnActionUtil {
	
	public static String genComputerorderSn(int uid,int computerordertype,Date date){
		
		String sn = "PP"+String.format("%03d",uid)+computerordertype+computerordertype+date.getTime();
		return sn;
	}

}
