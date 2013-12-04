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
	
}
