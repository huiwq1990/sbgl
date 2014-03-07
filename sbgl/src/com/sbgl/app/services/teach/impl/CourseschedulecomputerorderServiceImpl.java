package com.sbgl.app.services.teach.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Courseschedulecomputerorder;
import com.sbgl.app.entity.CourseschedulecomputerorderFull;
import com.sbgl.app.services.teach.CourseschedulecomputerorderService;
import com.sbgl.app.dao.CourseschedulecomputerorderDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("courseschedulecomputerorderService")
@Transactional
public class CourseschedulecomputerorderServiceImpl implements CourseschedulecomputerorderService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private CourseschedulecomputerorderDao courseschedulecomputerorderDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addCourseschedulecomputerorder(Courseschedulecomputerorder courseschedulecomputerorder){
		courseschedulecomputerorder.setId(baseDao.getCode("Courseschedulecomputerorder"));
		baseDao.saveEntity(courseschedulecomputerorder);		
	}
	
	@Override
	public void addCourseschedulecomputerorder(Courseschedulecomputerorder ch,Courseschedulecomputerorder en){
		
	/*
		int type = baseDao.getCode("Courseschedulecomputerordertype");
		ch.setId(baseDao.getCode("Courseschedulecomputerorder"));
		ch.setCourseschedulecomputerordertype(type);
		en.setId(baseDao.getCode("Courseschedulecomputerorder"));
		en.setCourseschedulecomputerordertype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addCourseschedulecomputerorderWithId(Courseschedulecomputerorder courseschedulecomputerorder){
	
		baseDao.saveEntity(courseschedulecomputerorder);		
	}

//  根据id删除实体	
	@Override
	public int deleteCourseschedulecomputerorder(Integer courseschedulecomputerorderId){
		Courseschedulecomputerorder courseschedulecomputerorder = new Courseschedulecomputerorder();
		courseschedulecomputerorder.setId(courseschedulecomputerorderId);
		//return courseschedulecomputerorderDao.deleteEntity(courseschedulecomputerorderId);	
		//baseDao.deleteEntityById(Courseschedulecomputerorder.class,courseschedulecomputerorderId);
		baseDao.deleteEntity(courseschedulecomputerorder);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteCourseschedulecomputerorder(Courseschedulecomputerorder courseschedulecomputerorder) {
		return deleteCourseschedulecomputerorder(courseschedulecomputerorder.getId());
	}

	
	@Override
	public void updateCourseschedulecomputerorder(Courseschedulecomputerorder courseschedulecomputerorder){
		
		Courseschedulecomputerorder tempCourseschedulecomputerorder = new Courseschedulecomputerorder();
		//tempCourseschedulecomputerorder = baseDao.getEntityById(Courseschedulecomputerorder.class, courseschedulecomputerorder.getId());
		tempCourseschedulecomputerorder = courseschedulecomputerorder;
		//add your code here
		
		
		baseDao.updateEntity(tempCourseschedulecomputerorder);

	}

//	根据id查询实体类			
	@Override
	public Courseschedulecomputerorder selectCourseschedulecomputerorderById(Integer courseschedulecomputerorderId){		
		return baseDao.getEntityById(Courseschedulecomputerorder.class, courseschedulecomputerorderId);
	}
	
	/*
	@Override
	public List<Courseschedulecomputerorder> selectCourseschedulecomputerorderAll(){
			
		return baseDao.getAllEntity(Courseschedulecomputerorder.class);
		
	}
	*/
	
//	根据id查询full类
	@Override
	public CourseschedulecomputerorderFull selectCourseschedulecomputerorderFullById(Integer courseschedulecomputerorderId){
		String sql = "where a.id=" + courseschedulecomputerorderId;
		List<CourseschedulecomputerorderFull> temp = courseschedulecomputerorderDao.selectCourseschedulecomputerorderFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	

	public int countCourseschedulecomputerorderRow(){
		return baseDao.getRowCount(Courseschedulecomputerorder.class);
	}
		
//  分页查询
	public List<Courseschedulecomputerorder> selectCourseschedulecomputerorderByPage(Page page){	
		return baseDao.selectByPage(Courseschedulecomputerorder.class,page);
	}
//  分页查询full	
	public List<CourseschedulecomputerorderFull> selectCourseschedulecomputerorderFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Courseschedulecomputerorder.class));
		return courseschedulecomputerorderDao.selectCourseschedulecomputerorderFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Courseschedulecomputerorder> selectCourseschedulecomputerorderByCondition(String condition) {
		 return courseschedulecomputerorderDao.selectCourseschedulecomputerorderByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
	@Override
	public List<Courseschedulecomputerorder>  selectCourseschedulecomputerorderByConditionAndPage(String condition,final Page page) {
		return courseschedulecomputerorderDao.selectCourseschedulecomputerorderByConditionAndPage(condition,page);
	}
	
	
	//条件查询full
	@Override
	public List<CourseschedulecomputerorderFull> selectCourseschedulecomputerorderFullByCondition(String condition) {
		return courseschedulecomputerorderDao.selectCourseschedulecomputerorderFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<CourseschedulecomputerorderFull>  selectCourseschedulecomputerorderFullByConditionAndPage(String condition,final Page page) {
			return courseschedulecomputerorderDao.selectCourseschedulecomputerorderFullByConditionAndPage(condition, page);
		}
	


}
