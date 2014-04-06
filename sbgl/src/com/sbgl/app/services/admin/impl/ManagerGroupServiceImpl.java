package com.sbgl.app.services.admin.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Managergroup;
import com.sbgl.app.services.admin.ManagerGroupService;

@Scope("prototype") 
@Service("mgrGroupService")
public class ManagerGroupServiceImpl implements ManagerGroupService {
	@Resource
	private BaseDao baseDao;

	@Override
	public int addManagerGroup(Managergroup usergroup) {
		int id = baseDao.getCode("mangergroupId");
		usergroup.setId( id );
		
		try {
			baseDao.saveEntity( usergroup );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int alterManagerGroup(Managergroup usergroup) {
		int id = usergroup.getId();
		Managergroup storeManagergroup = baseDao.getEntityById(Managergroup.class, id);
		
		storeManagergroup.setUserid( usergroup.getUserid() );
		storeManagergroup.setManagertype( usergroup.getManagertype() );
		storeManagergroup.setUsertype( usergroup.getUsertype() );
		
		try {
			baseDao.updateEntity( storeManagergroup );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int deleteManagergroup(int managergroupId) {
		Managergroup userGroup = baseDao.getEntityById(Managergroup.class, managergroupId);
		
		try {
			baseDao.deleteEntity( userGroup );
			return userGroup.getId();
		} catch (RuntimeException re) {
			return -1;
		}
	}

	@Override
	public List<Managergroup> getAllManagerGroup() {
		List<Managergroup> resultList = baseDao.getAllEntity(Managergroup.class);
		return resultList;
	}

	@Override
	public boolean isExistGroupByUserid(int userid) {
		List<Managergroup> temp = baseDao.getEntityByIntProperty(Managergroup.class.getName(), "userid", userid);
		return temp != null && temp.size() > 0;
	}

	@Override
	public Managergroup getManagerGroupByUserId(int userId) {
		List<Managergroup> mgList = baseDao.getEntityByIntProperty(Managergroup.class.getName(), "userid", userId);
		return mgList != null && mgList.size() > 0  ? mgList.get(0) : null;
	}

	@Override
	public List<Managergroup> getManagerGroupByType(int typeId) {
		List<Managergroup> resultList = baseDao.getEntityByIntProperty(Managergroup.class.getName(), "usertype", typeId);
		return resultList;
	}

}
