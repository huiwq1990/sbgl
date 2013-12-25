package com.sbgl.app.services.user;

import java.util.List;

import com.sbgl.app.entity.Usergrouprelation;

public interface UserGroupRelationService {
	//添加用户-分组关系信息
	public int addUserGroupRelation(Usergrouprelation userGroupRelation);
	//修改用户-分组关系信息
	public int alterUserGroupRelation(Usergrouprelation userGroupRelation);
	//删除用户-分组关系信息
	public int deleteUserGroupRelation(int userGroupRelationId);
	//获取全部用户-分组关系信息
	public List<Usergrouprelation> getAllUserGroupRelation();
	//根据用户ID获取关系信息
	public Usergrouprelation getRelationByUserId(int userId);
}
