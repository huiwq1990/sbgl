package com.sbgl.app.services.admin;

import java.util.List;

import com.sbgl.app.entity.Usergroup;

public interface GroupService {
	//添加分组信息
	public int addUserGroup(Usergroup usergroup);
	//修改分组信息
	public int alterUserGroup(Usergroup usergroup);
	//删除分组信息
	public int deleteUsergroup(int usergroupId);
	//获取全部分组信息
	public List<Usergroup> getAllUserGroup();
	//判断用户组名是否已经存在
	public boolean isExistGroupName(String groupName);
	//根据Id获取分组
	public Usergroup getUserGroupByid(int groupId);
	//根据类型获取分组
	public List<Usergroup> getUserGroupByType(int typeId);
}
