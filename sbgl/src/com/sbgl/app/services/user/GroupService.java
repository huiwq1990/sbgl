package com.sbgl.app.services.user;

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
}
