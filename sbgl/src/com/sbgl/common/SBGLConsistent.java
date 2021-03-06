package com.sbgl.common;

public class SBGLConsistent {
	/**
	 * 系统常量定义
	 */
	public final static int SYSTEM_ERROR = 0;
	public final static int DATABASE_ERROR = 1;
	public final static int RETURN_TAG_CREATE_ERROR = 3;
	public final static int RETURN_TAG_QUERY_ERROR = 4;
	public final static int RETURN_TAG_DELETE_ERROR = 5;
	public final static int RETURN_TAG_UPDATE_ERROR = 6;
	public final static int RETURN_TAG_NORMAL = 7;
	/**
	 * 数据库查询HQL语句条件逻辑连接
	 */
	public final static int HQL_OPTION_EQ = 8;  //相等
	public final static int HQL_OPTION_GT = 9;  //大于
	public final static int HQL_OPTION_LT = 10;  //小于
	public final static int HQL_OPTION_LK = 11;  //like
	public final static int HQL_OPTION_IN = 12;  //in
	
	public final static int HQL_OPTION_AD = 13;  //条件间与关系
	public final static int HQL_OPTION_OR = 14;  //条件间或关系
	
	public final static int HQL_VALUE_STR = 15;  //条件间或关系
	public final static int HQL_VALUE_INT = 16;  //条件间或关系
	/**
	 * 登录用户类型判断，也同时决定了其权限
	 */
	public final static int USER_TYPE_ADMIN1 = 17;  //超级管理员
	public final static int USER_TYPE_ADMIN2 = 18;  //设备管理员
	public final static int USER_TYPE_ADMIN3 = 19;  //机房管理员
	public final static int USER_TYPE_STU = 20;  //条件间或关系
	public final static int USER_TYPE_TEA = 21;  //条件间或关系
	public final static int USER_TYPE_WRK = 22;  //条件间或关系
}
