package com.sbgl.app.services.user.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Usergroup;
import com.sbgl.app.services.user.GroupService;

@Scope("prototype") 
@Service("groupService")
public class GroupServiceImpl implements GroupService {
	@Resource
	private BaseDao baseDao;

	@Override
	public int addUserGroup(Usergroup usergroup) {
		int id = baseDao.getCode("usergroupId");
		usergroup.setId( id );
		usergroup.setCreatetime(new Date());
		
		try {
			baseDao.saveEntity( usergroup );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int alterUserGroup(Usergroup usergroup) {
		int id = usergroup.getId();
		Usergroup storeUsergroup = baseDao.getEntityById(Usergroup.class, id);
		
		storeUsergroup.setName( usergroup.getName() );
		storeUsergroup.setOwnerid( usergroup.getOwnerid() );
		storeUsergroup.setStatus( usergroup.getStatus() );
		storeUsergroup.setType( usergroup.getType() );
		
		try {
			baseDao.updateEntity( storeUsergroup );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int deleteUsergroup(int usergroupId) {
		Usergroup userGroup = baseDao.getEntityById(Usergroup.class, usergroupId);
		
		try {
			baseDao.deleteEntity( userGroup );
			return userGroup.getId();
		} catch (RuntimeException re) {
			return -1;
		}
	}

	@Override
	public List<Usergroup> getAllUserGroup() {
		List<Usergroup> resultList = baseDao.getAllEntity(Usergroup.class);
		return resultList;
	}

	@Override
	public boolean isExistGroupName(String groupName) {
		return baseDao.isExist(Usergroup.class, "name", groupName);
	}

	@Override
	public Usergroup getUserGroupByid(int groupId) {
		return baseDao.getEntityById(Usergroup.class, groupId);
	}

	@Override
	public List<Usergroup> getUserGroupByType(int typeId) {
		return baseDao.getEntityByIntProperty(Usergroup.class.getName(), "type", typeId);
	}

}
