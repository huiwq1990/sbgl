package com.sbgl.app.services.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Administrator;
import com.sbgl.app.services.user.ManagerService;

@Scope("prototype") 
@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
	@Resource
	private BaseDao baseDao;
	
	@Override
	public int addManager(Administrator administrator) {
		int id = baseDao.getCode("managerId");
		administrator.setId( id );
		
		try {
			baseDao.saveEntity( administrator );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int alterManager(Administrator administrator) {
		int id = administrator.getId();
		Administrator storeAdministrator = baseDao.getEntityById(Administrator.class, id);
		
		storeAdministrator.setAdministratorid( administrator.getAdministratorid() );
		storeAdministrator.setEmail( administrator.getEmail() );
		storeAdministrator.setGender( administrator.getGender() );
		storeAdministrator.setName( administrator.getName() );
		storeAdministrator.setPassword( administrator.getPassword() );
		storeAdministrator.setPhoto( administrator.getPhoto() );
		storeAdministrator.setPrivilege( administrator.getPrivilege() );
		storeAdministrator.setTelephone( administrator.getTelephone() );
		
		try {
			baseDao.updateEntity( storeAdministrator );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int deleteManager(int administratorId) {
		Administrator administrator = baseDao.getEntityById(Administrator.class, administratorId);
		
		try {
			baseDao.deleteEntity( administrator );
			return 0;
		} catch (RuntimeException re) {
			return -1;
		}
	}

	@Override
	public List<Administrator> getAllManager() {
		List<Administrator> resultList = baseDao.getAllEntity(Administrator.class);
		return resultList;
	}

}
