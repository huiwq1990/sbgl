package com.sbgl.app.services.user;

import com.sbgl.app.entity.Userlogininfo;

public interface UserlogininfoService {
	/**
	 * 添加用户登录信息
	 * @param info 登录信息
	 * @return 存储id
	 */
	public int addUserLoinInfo(Userlogininfo info);
	
	/**
	 * 修改用户登录信息
	 * @param info 登录信息
	 * @return 存储id
	 */
	public int alterUserLoginInfo(Userlogininfo info);
	
	/**
	 * 根据用户id获取用户登录信息
	 * @param userId 用户id
	 * @return 登录信息
	 */
	public Userlogininfo getLoinInfoByUserId(int userId);
	
	/**
	 * 根据用户id获取用户是否是第一次登录
	 * @param userId 用户id
	 * @return 是否是第一次登录
	 */
	public Boolean getIsFirstLoginByUserId(int userId);
}
