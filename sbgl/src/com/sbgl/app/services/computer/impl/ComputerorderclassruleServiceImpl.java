package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computerorderclassrule;
import com.sbgl.app.entity.ComputerorderclassruleFull;
import com.sbgl.app.services.computer.ComputerorderclassruleService;
import com.sbgl.app.dao.ComputerorderclassruleDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computerorderclassruleService")
@Transactional
public class ComputerorderclassruleServiceImpl implements ComputerorderclassruleService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputerorderclassruleDao computerorderclassruleDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addComputerorderclassrule(Computerorderclassrule computerorderclassrule){
		computerorderclassrule.setId(baseDao.getCode("Computerorderclassrule"));
		baseDao.saveEntity(computerorderclassrule);		
	}
	
	@Override
	public void addComputerorderclassrule(Computerorderclassrule ch,Computerorderclassrule en){
		
	/*
		int type = baseDao.getCode("Computerorderclassruletype");
		ch.setId(baseDao.getCode("Computerorderclassrule"));
		ch.setComputerorderclassruletype(type);
		en.setId(baseDao.getCode("Computerorderclassrule"));
		en.setComputerorderclassruletype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addComputerorderclassruleWithId(Computerorderclassrule computerorderclassrule){
	
		baseDao.saveEntity(computerorderclassrule);		
	}

//  根据id删除实体	
	@Override
	public int deleteComputerorderclassrule(Integer computerorderclassruleId){
		Computerorderclassrule computerorderclassrule = new Computerorderclassrule();
		computerorderclassrule.setId(computerorderclassruleId);
		//return computerorderclassruleDao.deleteEntity(computerorderclassruleId);	
		//baseDao.deleteEntityById(Computerorderclassrule.class,computerorderclassruleId);
		baseDao.deleteEntity(computerorderclassrule);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteComputerorderclassrule(Computerorderclassrule computerorderclassrule) {
		return deleteComputerorderclassrule(computerorderclassrule.getId());
	}

	
	@Override
	public void updateComputerorderclassrule(Computerorderclassrule computerorderclassrule){
		
		Computerorderclassrule tempComputerorderclassrule = new Computerorderclassrule();
		//tempComputerorderclassrule = baseDao.getEntityById(Computerorderclassrule.class, computerorderclassrule.getId());
		tempComputerorderclassrule = computerorderclassrule;
		//add your code here
		
		
		baseDao.updateEntity(tempComputerorderclassrule);

	}

//	根据id查询实体类			
	@Override
	public Computerorderclassrule selectComputerorderclassruleById(Integer computerorderclassruleId){		
		return baseDao.getEntityById(Computerorderclassrule.class, computerorderclassruleId);
	}
	
	@Override
	public List<Computerorderclassrule> selectComputerorderclassruleAll(){
			
		return baseDao.getAllEntity(Computerorderclassrule.class);
		
	}
	
//	根据id查询full类
	@Override
	public ComputerorderclassruleFull selectComputerorderclassruleFullById(Integer computerorderclassruleId){
		String sql = "where a.id="+computerorderclassruleId;
		List<ComputerorderclassruleFull> temp = computerorderclassruleDao.selectComputerorderclassruleFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	
	
	

//  查询全部实体full
	@Override
	public List<ComputerorderclassruleFull> selectComputerorderclassruleFullAll() {
		// TODO Auto-generated method stub
		return computerorderclassruleDao.selectComputerorderclassruleFullAll();
	}
	
	
	public int countComputerorderclassruleRow(){
		return baseDao.getRowCount(Computerorderclassrule.class);
	}
		
//  分页查询
	public List<Computerorderclassrule> selectComputerorderclassruleByPage(Page page){	
		return baseDao.selectByPage(Computerorderclassrule.class,page);
	}
//  分页查询full	
	public List<ComputerorderclassruleFull> selectComputerorderclassruleFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Computerorderclassrule.class));
		return computerorderclassruleDao.selectComputerorderclassruleFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Computerorderclassrule> selectComputerorderclassruleByCondition(String condition) {
		 return computerorderclassruleDao.selectComputerorderclassruleByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
        @Override
        public List<Computerorderclassrule>  selectComputerorderclassruleByConditionAndPage(String condition,final Page page) {
              return computerorderclassruleDao.selectComputerorderclassruleByConditionAndPage(condition,page);
        }
	
	
	//条件查询full
	@Override
	public List<ComputerorderclassruleFull> selectComputerorderclassruleFullByCondition(String condition) {
		return computerorderclassruleDao.selectComputerorderclassruleFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerorderclassruleFull>  selectComputerorderclassruleFullByConditionAndPage(String condition,final Page page) {
			return computerorderclassruleDao.selectComputerorderclassruleFullByConditionAndPage(condition, page);
		}
	
	

}
