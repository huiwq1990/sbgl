package com.sbgl.app.services.user;

import java.util.List;

import com.sbgl.app.actions.admin.template.UserInfoTemplate;
import com.sbgl.app.entity.User;

public interface UserService {
	//添加用户分组信息
	public int addUser(User user);
	//修改用户分组信息
	public int alterUser(User user);
	//删除用户信息
	public int deleteUser(int userId);
	//批量删除用户信息
	public int deleteUser(String userIds);
	//获取全部用户信息
	public List<User> getAllUser();
	//判断身份证号是否存在
	public boolean isExistUserIDCode(String userIDCode);
	//判断用户号是否存在
	public boolean isExistUserCode(String userCode);
	//根据ID获取用户信息
	public User getUserById(int userId);
	//获取用户总数
	public Integer getSumOfUser();
	//根据用于显示的用户数据
	public List<UserInfoTemplate> getUserInfo();
	//添加管理员属性
	public int addManagerPro(int userId, int managerType);
}
