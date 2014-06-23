package com.sbgl.app.services.equipment.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Rentunit;
import com.sbgl.app.services.equipment.RentUnitService;

@Scope("prototype") 
@Service("rentUnitService")
public class RentUnitServiceImpl implements RentUnitService {
	
	@Resource
	private BaseDao baseDao;

	@Override
	public Rentunit findById(Integer id) {
		return baseDao.getEntityById(Rentunit.class, id);
	}

	@Override
	public List<Rentunit> getAll() {
		
		return baseDao.getAllEntity(Rentunit.class);
	}

}
