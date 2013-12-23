package com.sbgl.app.services.user;

import java.util.List;

import com.sbgl.app.entity.Administrator;

public interface ManagerService {
	//添加管理员
	public int addManager(Administrator administrator);
	//修改管理员
	public int alterManager(Administrator administrator);
	//删除管理员
	public int deleteManager(int administratorId);
	//获取全部管理员
	public List<Administrator> getAllManager();
}
