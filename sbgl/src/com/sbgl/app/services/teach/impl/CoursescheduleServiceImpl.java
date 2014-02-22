package com.sbgl.app.services.teach.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Courseschedule;
import com.sbgl.app.entity.CoursescheduleFull;
import com.sbgl.app.services.teach.CoursescheduleService;
import com.sbgl.app.dao.CoursescheduleDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("coursescheduleService")
@Transactional
public class CoursescheduleServiceImpl implements CoursescheduleService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private CoursescheduleDao coursescheduleDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addCourseschedule(Courseschedule courseschedule){
		courseschedule.setId(baseDao.getCode("Courseschedule"));
		baseDao.saveEntity(courseschedule);		
	}
	
	@Override
	public void addCourseschedule(Courseschedule ch,Courseschedule en){
		
	/*
		int type = baseDao.getCode("Coursescheduletype");
		ch.setId(baseDao.getCode("Courseschedule"));
		ch.setCoursescheduletype(type);
		en.setId(baseDao.getCode("Courseschedule"));
		en.setCoursescheduletype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addCoursescheduleWithId(Courseschedule courseschedule){
	
		baseDao.saveEntity(courseschedule);		
	}

//  根据id删除实体	
	@Override
	public int deleteCourseschedule(Integer coursescheduleId){
		Courseschedule courseschedule = new Courseschedule();
		courseschedule.setId(coursescheduleId);
		//return coursescheduleDao.deleteEntity(coursescheduleId);	
		//baseDao.deleteEntityById(Courseschedule.class,coursescheduleId);
		baseDao.deleteEntity(courseschedule);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteCourseschedule(Courseschedule courseschedule) {
		return deleteCourseschedule(courseschedule.getId());
	}

	
	@Override
	public void updateCourseschedule(Courseschedule courseschedule){
		
		Courseschedule tempCourseschedule = new Courseschedule();
		//tempCourseschedule = baseDao.getEntityById(Courseschedule.class, courseschedule.getId());
		tempCourseschedule = courseschedule;
		//add your code here
		
		
		baseDao.updateEntity(tempCourseschedule);

	}

//	根据id查询实体类			
	@Override
	public Courseschedule selectCoursescheduleById(Integer coursescheduleId){		
		return baseDao.getEntityById(Courseschedule.class, coursescheduleId);
	}
	
	/*
	@Override
	public List<Courseschedule> selectCoursescheduleAll(){
			
		return baseDao.getAllEntity(Courseschedule.class);
		
	}
	*/
	
//	根据id查询full类
	@Override
	public CoursescheduleFull selectCoursescheduleFullById(Integer coursescheduleId){
		String sql = "where a.id=" + coursescheduleId;
		List<CoursescheduleFull> temp = coursescheduleDao.selectCoursescheduleFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	

	public int countCoursescheduleRow(){
		return baseDao.getRowCount(Courseschedule.class);
	}
		
//  分页查询
	public List<Courseschedule> selectCoursescheduleByPage(Page page){	
		return baseDao.selectByPage(Courseschedule.class,page);
	}
//  分页查询full	
	public List<CoursescheduleFull> selectCoursescheduleFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Courseschedule.class));
		return coursescheduleDao.selectCoursescheduleFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Courseschedule> selectCoursescheduleByCondition(String condition) {
		 return coursescheduleDao.selectCoursescheduleByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
	@Override
	public List<Courseschedule>  selectCoursescheduleByConditionAndPage(String condition,final Page page) {
		return coursescheduleDao.selectCoursescheduleByConditionAndPage(condition,page);
	}
	
	
	//条件查询full
	@Override
	public List<CoursescheduleFull> selectCoursescheduleFullByCondition(String condition) {
		return coursescheduleDao.selectCoursescheduleFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<CoursescheduleFull>  selectCoursescheduleFullByConditionAndPage(String condition,final Page page) {
			return coursescheduleDao.selectCoursescheduleFullByConditionAndPage(condition, page);
		}
	

    	@Override
    	public int execSql(String sql) {
//    		String sql = "delete from Computerorderdetail " + condition;
    		baseDao.createSQL(sql);
    		return 1;
    		
    	}
}
