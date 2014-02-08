package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computerconfig;
import com.sbgl.app.entity.ComputerconfigFull;
import com.sbgl.app.services.computer.ComputerconfigService;
import com.sbgl.app.dao.ComputerconfigDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computerconfigService")
@Transactional
public class ComputerconfigServiceImpl implements ComputerconfigService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputerconfigDao computerconfigDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addComputerconfig(Computerconfig computerconfig){
		computerconfig.setId(baseDao.getCode("Computerconfig"));
		baseDao.saveEntity(computerconfig);		
	}
	
	@Override
	public void addComputerconfig(Computerconfig ch,Computerconfig en){
		
	/*
		int type = baseDao.getCode("Computerconfigtype");
		ch.setId(baseDao.getCode("Computerconfig"));
		ch.setComputerconfigtype(type);
		en.setId(baseDao.getCode("Computerconfig"));
		en.setComputerconfigtype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addComputerconfigWithId(Computerconfig computerconfig){
	
		baseDao.saveEntity(computerconfig);		
	}

//  根据id删除实体	
	@Override
	public int deleteComputerconfig(Integer computerconfigId){
		Computerconfig computerconfig = new Computerconfig();
		computerconfig.setId(computerconfigId);
		//return computerconfigDao.deleteEntity(computerconfigId);	
		//baseDao.deleteEntityById(Computerconfig.class,computerconfigId);
		baseDao.deleteEntity(computerconfig);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteComputerconfig(Computerconfig computerconfig) {
		return deleteComputerconfig(computerconfig.getId());
	}

	
	@Override
	public void updateComputerconfig(Computerconfig computerconfig){
		
		Computerconfig tempComputerconfig = new Computerconfig();
		//tempComputerconfig = baseDao.getEntityById(Computerconfig.class, computerconfig.getId());
		tempComputerconfig = computerconfig;
		//add your code here
		
		
		baseDao.updateEntity(tempComputerconfig);

	}

//	根据id查询实体类			
	@Override
	public Computerconfig selectComputerconfigById(Integer computerconfigId){		
		return baseDao.getEntityById(Computerconfig.class, computerconfigId);
	}
	
	@Override
	public List<Computerconfig> selectComputerconfigAll(){
			
		return baseDao.getAllEntity(Computerconfig.class);
		
	}
	
//	根据id查询full类
	@Override
	public ComputerconfigFull selectComputerconfigFullById(Integer computerconfigId){
		String sql = "where a.id=" + computerconfigId;
		List<ComputerconfigFull> temp = computerconfigDao.selectComputerconfigFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	
	
	

//  查询全部实体full
	@Override
	public List<ComputerconfigFull> selectComputerconfigFullAll() {
		// TODO Auto-generated method stub
		return computerconfigDao.selectComputerconfigFullAll();
	}
	
	
	public int countComputerconfigRow(){
		return baseDao.getRowCount(Computerconfig.class);
	}
		
//  分页查询
	public List<Computerconfig> selectComputerconfigByPage(Page page){	
		return baseDao.selectByPage(Computerconfig.class,page);
	}
//  分页查询full	
	public List<ComputerconfigFull> selectComputerconfigFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Computerconfig.class));
		return computerconfigDao.selectComputerconfigFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Computerconfig> selectComputerconfigByCondition(String condition) {
		 return computerconfigDao.selectComputerconfigByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
        @Override
        public List<Computerconfig>  selectComputerconfigByConditionAndPage(String condition,final Page page) {
              return computerconfigDao.selectComputerconfigByConditionAndPage(condition,page);
        }
	
	
	//条件查询full
	@Override
	public List<ComputerconfigFull> selectComputerconfigFullByCondition(String condition) {
		return computerconfigDao.selectComputerconfigFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerconfigFull>  selectComputerconfigFullByConditionAndPage(String condition,final Page page) {
			return computerconfigDao.selectComputerconfigFullByConditionAndPage(condition, page);
		}
	
        @Override
    	public int execSql(String sql) {
//    		String sql = "delete from Computerorderdetail " + condition;
    		baseDao.createSQL(sql);
    		return 1;
    		
    	}

}
