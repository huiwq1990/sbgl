package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.ComputermodelFull;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.app.dao.ComputermodelDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computermodelService")
@Transactional
public class ComputermodelServiceImpl implements ComputermodelService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputermodelDao computermodelDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addComputermodel(Computermodel computermodel){
		computermodel.setId(baseDao.getCode("Computermodel"));
		baseDao.saveEntity(computermodel);		
	}

	@Override
	public void addComputermodelWithId(Computermodel computermodel){
	
		baseDao.saveEntity(computermodel);		
	}

//  根据id删除实体	
	@Override
	public int deleteComputermodel(Integer computermodelId){
		Computermodel computermodel = new Computermodel();
		computermodel.setId(computermodelId);
		//return computermodelDao.deleteEntity(computermodelId);	
		//baseDao.deleteEntityById(Computermodel.class,computermodelId);
		baseDao.deleteEntity(computermodel);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteComputermodel(Computermodel computermodel) {
		return deleteComputermodel(computermodel.getId());
	}

	
	@Override
	public void updateComputermodel(Computermodel computermodel){
		
		Computermodel tempComputermodel = new Computermodel();
		//tempComputermodel = baseDao.getEntityById(Computermodel.class, computermodel.getId());
		tempComputermodel = computermodel;
		//add your code here
		
		
		baseDao.updateEntity(tempComputermodel);

	}

//	根据id查询实体类			
	@Override
	public Computermodel selectComputermodelById(Integer computermodelId){		
		return baseDao.getEntityById(Computermodel.class, computermodelId);
	}
	
	@Override
	public List<Computermodel> selectComputermodelAll(){
			
		return baseDao.getAllEntity(Computermodel.class);
		
	}
	
//	根据id查询full类
	@Override
	public ComputermodelFull selectComputermodelFullById(Integer computermodelId){
	
		return computermodelDao.selectComputermodelFullById(computermodelId); 
	}	
	
	
	

//  查询全部实体full
	@Override
	public List<ComputermodelFull> selectComputermodelFullAll() {
		// TODO Auto-generated method stub
		return computermodelDao.selectComputermodelFullAll();
	}
	
	
	public int countComputermodelRow(){
		return baseDao.getRowCount(Computermodel.class);
	}
		
//  分页查询
	public List<Computermodel> selectComputermodelByPage(Page page){	
		return baseDao.selectByPage(Computermodel.class,page);
	}
//  分页查询full	
	public List<ComputermodelFull> selectComputermodelFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Computermodel.class));
		return computermodelDao.selectComputermodelFullByPage(page);
	}
	
	//根据createuserid 查询实体
	@Override
	public List<Computermodel> selectComputermodelByLoginuserId(Integer createuserid ) {
		List<Computermodel> computermodelList = new ArrayList<Computermodel>();
		String id = String.valueOf(createuserid );
		computermodelList = baseDao.getEntityByProperty("Computermodel", "createuserid ", id);
		return computermodelList;
	}
	//根据createuserid 查询实体full
	@Override
	public List<ComputermodelFull> selectComputermodelFullByLoginuserId(Integer createuserid ) {
		return computermodelDao.selectComputermodelFullByLoginuserId(createuserid );
	}

}
