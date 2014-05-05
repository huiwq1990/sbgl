package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computerstatus;
import com.sbgl.app.entity.ComputerstatusFull;
import com.sbgl.app.services.computer.ComputerstatusService;
import com.sbgl.app.dao.ComputerstatusDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computerstatusService")
@Transactional
public class ComputerstatusServiceImpl implements ComputerstatusService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputerstatusDao computerstatusDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addComputerstatus(Computerstatus computerstatus){
		computerstatus.setId(baseDao.getCode("Computerstatus"));
		baseDao.saveEntity(computerstatus);		
	}

	@Override
	public void addComputerstatusWithId(Computerstatus computerstatus){
	
		baseDao.saveEntity(computerstatus);		
	}

//  根据id删除实体	
	@Override
	public int deleteComputerstatus(Integer computerstatusId){
		Computerstatus computerstatus = new Computerstatus();
		computerstatus.setId(computerstatusId);
		//return computerstatusDao.deleteEntity(computerstatusId);	
		//baseDao.deleteEntityById(Computerstatus.class,computerstatusId);
		baseDao.deleteEntity(computerstatus);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteComputerstatus(Computerstatus computerstatus) {
		return deleteComputerstatus(computerstatus.getId());
	}

	
	@Override
	public void updateComputerstatus(Computerstatus computerstatus){
		
		Computerstatus tempComputerstatus = new Computerstatus();
		//tempComputerstatus = baseDao.getEntityById(Computerstatus.class, computerstatus.getId());
		tempComputerstatus = computerstatus;
		//add your code here
		
		
		baseDao.updateEntity(tempComputerstatus);

	}

	/**
	 * 查询全部状态
	 */
	@Override
	public List<Computerstatus> sel() {
		 return computerstatusDao.sel();
	}
	@Override
	public List<ComputerstatusFull> selFull() {
		 return computerstatusDao.selFull();
	}
	
//	根据id查询实体类			
	@Override
	public Computerstatus selectComputerstatusById(Integer computerstatusId){		
		return baseDao.getEntityById(Computerstatus.class, computerstatusId);
	}
	
	@Override
	public List<Computerstatus> selectComputerstatusAll(){
			
		return baseDao.getAllEntity(Computerstatus.class);
		
	}
	
	
	
	
//	根据id查询full类
	@Override
	public ComputerstatusFull selectComputerstatusFullById(Integer computerstatusId){
	
		return computerstatusDao.selectComputerstatusFullById(computerstatusId); 
	}	
	
	
	

//  查询全部实体full
	@Override
	public List<ComputerstatusFull> selectComputerstatusFullAll() {
		// TODO Auto-generated method stub
		return computerstatusDao.selectComputerstatusFullAll();
	}
	
	
	public int countComputerstatusRow(){
		return baseDao.getRowCount(Computerstatus.class);
	}
		
//  分页查询
	public List<Computerstatus> selectComputerstatusByPage(Page page){	
		return baseDao.selectByPage(Computerstatus.class,page);
	}
//  分页查询full	
	public List<ComputerstatusFull> selectComputerstatusFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Computerstatus.class));
		return computerstatusDao.selectComputerstatusFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Computerstatus> selectComputerstatusByCondition(String condition) {
		 return computerstatusDao.selectComputerstatusByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
        @Override
        public List<Computerstatus>  selectComputerstatusByConditionAndPage(String condition,final Page page) {
              return computerstatusDao.selectComputerstatusByConditionAndPage(condition,page);
        }
	
	
	//条件查询full
	@Override
	public List<ComputerstatusFull> selectComputerstatusFullByCondition(String condition) {
		return computerstatusDao.selectComputerstatusFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerstatusFull>  selectComputerstatusFullByConditionAndPage(String condition,final Page page) {
			return computerstatusDao.selectComputerstatusFullByConditionAndPage(condition, page);
		}
	
	

}
