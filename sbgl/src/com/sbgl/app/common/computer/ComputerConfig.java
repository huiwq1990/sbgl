package com.sbgl.app.common.computer;

public class ComputerConfig {
	
	//默认图片名称
	public final static String ComputerPicDefaultName = "nopic.jpg";
	
//	模块名称
	public final static String ComputerModelName = "computer";
	
	
//	导航栏类型
	public static final String ComputerManageInfoIncludeSidebarType = "computerManageInfo";	
//	PC管理导航栏的文件路径
	public static final String ComputerManageInfoIncludeSidebarPath = "computer/computerManageInfoIncludeSidebar.vm";
//	导航栏的名称
	public static final String IncludeSidebarDirectiveName = "includeSidebar"; 
	
//	error return flag
	public final static int ajaxerrorreturn = 0;
	public final static int ajaxsuccessreturn = 1;
	
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
	
//	可借状态id
	public final static int computeravailableborrowstatusid = 1;
	
	
//	用户未登录返回值
	public final static String usernotloginreturnstr = "usernotlogin";
	
//	访问界面不存在
	public final static String pagenotfound = "pagenotfound";
	public final static String innererror = "innererror";
	public final static String ordererror = "ordererror";
	
//	作业已经查看预约
	public final static int computerhomeworkhasview = 1;
	public final static int computerhomeworknotview = 0;
	public final static int computerhomeworkhasorder = 1;
	
//	public final static int computerhomeworknotorder = 0;
	public final static int computerhomeworkorderfinish = 1;
	public final static int computerhomeworkordernotfinish = 0;
	
	public final static int computerdelstatus = -1;
	
	
}
