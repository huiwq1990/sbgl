package com.sbgl.app.common.computer;

public class ComputerConfig {
	
//	用户登录信息放置的cookie的名称
	public final static String cookieuserid = "userid";
	
//	用户语言信息的session信息
	public final static String sessionLanguagetype = "languagetype";
	
	//预约提前时间
//	public static int computerorderTotalOrderDay = 16;
	//预约时间段
	public  static int computerorderTotalOrderPeriod = 3;
	
	//预约提前时间
	public static int computeroderadvanceorderday=18;
	public static int computerodertablercolumn = 7;
	
	
	public static int languagech = 0;
	public static int languageen = 1;
	public static String languagechStr = String.valueOf(languagech);
	public static String languageenStr = String.valueOf(languageen);
	
//	未分类的类型
	public final static int computercategorynotclassifyid = -1;
	
}
