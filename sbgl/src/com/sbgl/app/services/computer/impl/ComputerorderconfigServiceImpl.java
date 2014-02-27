package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computerorderconfig;
import com.sbgl.app.entity.ComputerorderconfigFull;
import com.sbgl.app.services.computer.ComputerorderconfigService;
import com.sbgl.app.dao.ComputerorderconfigDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computerorderconfigService")
@Transactional
public class ComputerorderconfigServiceImpl implements ComputerorderconfigService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputerorderconfigDao computerorderconfigDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addComputerorderconfig(Computerorderconfig computerorderconfig){
		
		String sql = " update Computerorderconfig set currentconfig = 0 where currentconfig = 1 ";
		baseDao.createSQL(sql);
		
		computerorderconfig.setId(baseDao.getCode("Computerorderconfig"));
		baseDao.saveEntity(computerorderconfig);		
	}
	
	@Override
	public void addComputerorderconfig(Computerorderconfig ch,Computerorderconfig en){
		
	/*
		int type = baseDao.getCode("Computerorderconfigtype");
		ch.setId(baseDao.getCode("Computerorderconfig"));
		ch.setComputerorderconfigtype(type);
		en.setId(baseDao.getCode("Computerorderconfig"));
		en.setComputerorderconfigtype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addComputerorderconfigWithId(Computerorderconfig computerorderconfig){
	
		baseDao.saveEntity(computerorderconfig);		
	}

//  根据id删除实体	
	@Override
	public int deleteComputerorderconfig(Integer computerorderconfigId){
		Computerorderconfig computerorderconfig = new Computerorderconfig();
		computerorderconfig.setId(computerorderconfigId);
		//return computerorderconfigDao.deleteEntity(computerorderconfigId);	
		//baseDao.deleteEntityById(Computerorderconfig.class,computerorderconfigId);
		baseDao.deleteEntity(computerorderconfig);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteComputerorderconfig(Computerorderconfig computerorderconfig) {
		return deleteComputerorderconfig(computerorderconfig.getId());
	}

	
	@Override
	public void updateComputerorderconfig(Computerorderconfig computerorderconfig){
		
		Computerorderconfig tempComputerorderconfig = new Computerorderconfig();
		//tempComputerorderconfig = baseDao.getEntityById(Computerorderconfig.class, computerorderconfig.getId());
		tempComputerorderconfig = computerorderconfig;
		//add your code here
		
		
		baseDao.updateEntity(tempComputerorderconfig);

	}

//	根据id查询实体类			
	@Override
	public Computerorderconfig selectComputerorderconfigById(Integer computerorderconfigId){		
		return baseDao.getEntityById(Computerorderconfig.class, computerorderconfigId);
	}

	@Override
	public Computerorderconfig selectCurrentComputerorderconfig( ){		
		return computerorderconfigDao.selectCurrentComputerorderconfig();
	}
	
	/*
	@Override
	public List<Computerorderconfig> selectComputerorderconfigAll(){
			
		return baseDao.getAllEntity(Computerorderconfig.class);
		
	}
	*/
	
//	根据id查询full类
	@Override
	public ComputerorderconfigFull selectComputerorderconfigFullById(Integer computerorderconfigId){
		String sql = "where a.id=" + computerorderconfigId;
		List<ComputerorderconfigFull> temp = computerorderconfigDao.selectComputerorderconfigFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	

	public int countComputerorderconfigRow(){
		return baseDao.getRowCount(Computerorderconfig.class);
	}
		
//  分页查询
	public List<Computerorderconfig> selectComputerorderconfigByPage(Page page){	
		return baseDao.selectByPage(Computerorderconfig.class,page);
	}
//  分页查询full	
	public List<ComputerorderconfigFull> selectComputerorderconfigFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Computerorderconfig.class));
		return computerorderconfigDao.selectComputerorderconfigFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Computerorderconfig> selectComputerorderconfigByCondition(String condition) {
		 return computerorderconfigDao.selectComputerorderconfigByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
	@Override
	public List<Computerorderconfig>  selectComputerorderconfigByConditionAndPage(String condition,final Page page) {
		return computerorderconfigDao.selectComputerorderconfigByConditionAndPage(condition,page);
	}
	
	
	//条件查询full
	@Override
	public List<ComputerorderconfigFull> selectComputerorderconfigFullByCondition(String condition) {
		return computerorderconfigDao.selectComputerorderconfigFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerorderconfigFull>  selectComputerorderconfigFullByConditionAndPage(String condition,final Page page) {
			return computerorderconfigDao.selectComputerorderconfigFullByConditionAndPage(condition, page);
		}
	


}
