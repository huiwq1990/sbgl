package com.sbgl.app.services.admin.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Administrator;
import com.sbgl.app.services.admin.AdministratorService;

@Scope("prototype") 
@Service("administratorService")
@Transactional
public class AdministratorServiceImpl implements AdministratorService {

	@Resource
	private BaseDao baseDao;
	
	
	@Override
	public void addAdministrator(Administrator administrator) {
		// TODO Auto-generated method stub
		baseDao.saveEntity(administrator);
	}

	@Override
	public void updateAdministrator(Administrator administrator) {
		// TODO Auto-generated method stub
		Administrator tAdministrator = new Administrator();
		tAdministrator = baseDao.getEntityById(Administrator.class, administrator.getId());
		tAdministrator.setName(administrator.getName());
		tAdministrator.setGender(administrator.getGender());
		tAdministrator.setTelephone(administrator.getTelephone());
		tAdministrator.setEmail(administrator.getEmail());
		tAdministrator.setPhoto(administrator.getPhoto());
		tAdministrator.setPrivilege(administrator.getPrivilege());
		tAdministrator.setPassword(administrator.getPassword());
		baseDao.updateEntity(tAdministrator);
	}

	@Override
	public void delAdministrator(Integer adminId) {
		// TODO Auto-generated method stub
		Administrator administrator = new Administrator();
		administrator.setId(adminId);
		baseDao.deleteEntity(administrator);
	}

	@Override
	public Administrator getAdministratorById(Integer adminId) {
		// TODO Auto-generated method stub
		Administrator administrator = new Administrator();
		administrator = baseDao.getEntityById(Administrator.class, adminId);
		return administrator;
	}
	
}
