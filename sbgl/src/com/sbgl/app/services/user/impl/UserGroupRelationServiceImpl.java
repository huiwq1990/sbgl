package com.sbgl.app.services.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Usergrouprelation;
import com.sbgl.app.services.user.UserGroupRelationService;

@Scope("prototype") 
@Service("userGroupRelationService")
public class UserGroupRelationServiceImpl implements UserGroupRelationService {
	@Resource
	private BaseDao baseDao;

	@Override
	public int addUserGroupRelation(Usergrouprelation userGroupRelation) {
		int id = baseDao.getCode("relationId");
		userGroupRelation.setId( id );
		
		try {
			baseDao.saveEntity( userGroupRelation );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int alterUserGroupRelation(Usergrouprelation userGroupRelation) {
		int id = userGroupRelation.getId();
		Usergrouprelation storeRelation = baseDao.getEntityById(Usergrouprelation.class, id);
		
		storeRelation.setGroupid( userGroupRelation.getGroupid() );
		storeRelation.setStatus( userGroupRelation.getStatus() );
		storeRelation.setUserid( userGroupRelation.getUserid() );
		
		try {
			baseDao.updateEntity( storeRelation );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int deleteUserGroupRelation(int userGroupRelationId) {
		Usergrouprelation relation = baseDao.getEntityById(Usergrouprelation.class, userGroupRelationId);
		
		try {
			baseDao.deleteEntity( relation );
			return 0;
		} catch (RuntimeException re) {
			return -1;
		}
	}

	@Override
	public List<Usergrouprelation> getAllUserGroupRelation() {
		List<Usergrouprelation> resultList = baseDao.getAllEntity(Usergrouprelation.class);
		return resultList;
	}

}
