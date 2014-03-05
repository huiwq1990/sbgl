package com.sbgl.app.services.user;

import java.util.List;

import com.sbgl.app.dao.QueryResult;
import com.sbgl.app.entity.Student;
import com.sbgl.app.entity.User;
import com.sbgl.common.HQLOption;
import com.sbgl.util.Page;

public interface UserService {
	//添加用户信息
	public int addUser(User user);
	//修改用户信息
	public int alterUser(User user);
	//删除用户信息
	public int deleteUser(int userId);
	//获取全部用户信息
	public List<User> getAllStudent();
	//判断用户编号与邮箱是否存在
	public boolean isExistUserInfo(String userNum, String email);
	//根据用户编号和密码获取用户
	public User getUserByNumberAndPass(String number, String userpass);
	//根据用户邮箱和密码获取用户
	public User getUserByEmailAndPass(String email, String userpass);
	//获取具有管理员权限的用户
	public List<User> getManager();
	//根据条件分页查询用户
	public QueryResult getUsersByPageWithOpts(List<HQLOption> hqlOptionList, Page page);
}
