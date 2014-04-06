package com.sbgl.app.services.admin;

import java.util.List;

import com.sbgl.app.entity.Managergroup;

public interface ManagerGroupService {
	//添加分组信息
	public int addManagerGroup(Managergroup usergroup);
	//修改分组信息
	public int alterManagerGroup(Managergroup usergroup);
	//删除分组信息
	public int deleteManagergroup(int managergroupId);
	//获取全部分组信息
	public List<Managergroup> getAllManagerGroup();
	//判断管理组名是否已经存在
	public boolean isExistGroupByUserid(int userid);
	//根据用户Id获取分组
	public Managergroup getManagerGroupByUserId(int userId);
	//根据类型获取分组
	public List<Managergroup> getManagerGroupByType(int typeId);
}
