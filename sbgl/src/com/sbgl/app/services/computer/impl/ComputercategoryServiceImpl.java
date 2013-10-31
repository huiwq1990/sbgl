package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.ComputercategoryFull;
import com.sbgl.app.services.computer.ComputercategoryService;
import com.sbgl.app.dao.ComputercategoryDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computercategoryService")
@Transactional
public class ComputercategoryServiceImpl implements ComputercategoryService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputercategoryDao computercategoryDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addComputercategory(Computercategory computercategory){
	//	computercategory.setId(baseDao.getCode("Computercategory"));
		baseDao.saveEntity(computercategory);		
	}

	@Override
	public void addComputercategoryWithId(Computercategory computercategory){
	
		baseDao.saveEntity(computercategory);		
	}

//  根据id删除实体	
	@Override
	public int deleteComputercategory(Integer computercategoryId){
		Computercategory computercategory = new Computercategory();
		computercategory.setId(computercategoryId);
		return computercategoryDao.deleteEntity(computercategoryId);		
	}

//  根据实体删除实体
	@Override
	public int deleteComputercategory(Computercategory computercategory) {
		return deleteComputercategory(computercategory.getId());
	}

	
	@Override
	public void updateComputercategory(Computercategory computercategory){
		
		Computercategory tempComputercategory = new Computercategory();
		//tempComputercategory = baseDao.getEntityById(Computercategory.class, computercategory.getId());
		tempComputercategory = computercategory;
		//add your code here
		
		
		baseDao.updateEntity(tempComputercategory);

	}

//	根据id查询实体类			
	@Override
	public Computercategory selectComputercategoryById(Integer computercategoryId){		
		return baseDao.getEntityById(Computercategory.class, computercategoryId);
	}
	
	@Override
	public List<Computercategory> selectComputercategoryAll(){
			
		return baseDao.getAllEntity(Computercategory.class);
		
	}
	
//	根据id查询full类
	@Override
	public ComputercategoryFull selectComputercategoryFullById(Integer computercategoryId){
	
		return computercategoryDao.selectComputercategoryFullById(computercategoryId); 
	}	
	
	
	

//  查询全部实体full
	@Override
	public List<ComputercategoryFull> selectComputercategoryFullAll() {
		// TODO Auto-generated method stub
		return computercategoryDao.selectComputercategoryFullAll();
	}
	
	
	public int countComputercategoryRow(){
		return baseDao.getRowCount(Computercategory.class);
	}
		
//  分页查询

	public List<Computercategory> selectComputercategoryByPage(Page page){	
		
		return baseDao.selectByPage(Computercategory.class,page);
	}
	public List<ComputercategoryFull> selectComputercategoryFullByPage(Page page){	
		
		return computercategoryDao.selectComputercategoryFullByPage(page);
	}
	
	//根据createuserid 查询实体
	@Override
	public List<Computercategory> selectComputercategoryByLoginuserId(Integer createuserid ) {
		List<Computercategory> computercategoryList = new ArrayList<Computercategory>();
		String id = String.valueOf(createuserid );
		computercategoryList = baseDao.getEntityByProperty("Computercategory", "createuserid ", id);
		return computercategoryList;
	}
	//根据createuserid 查询实体full
	@Override
	public List<ComputercategoryFull> selectComputercategoryFullByLoginuserId(Integer createuserid ) {
		return computercategoryDao.selectComputercategoryFullByLoginuserId(createuserid );
	}

}
