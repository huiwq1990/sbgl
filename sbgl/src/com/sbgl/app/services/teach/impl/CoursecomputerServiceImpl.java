package com.sbgl.app.services.teach.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Coursecomputer;
import com.sbgl.app.entity.CoursecomputerFull;
import com.sbgl.app.entity.Courseschedule;
import com.sbgl.app.services.teach.CoursecomputerService;
import com.sbgl.app.dao.CoursecomputerDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.CoursescheduleDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("coursecomputerService")
@Transactional
public class CoursecomputerServiceImpl implements CoursecomputerService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private CoursecomputerDao coursecomputerDao;
	@Resource
	private CoursescheduleDao coursescheduleDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addCoursecomputer(Coursecomputer coursecomputer){
		coursecomputer.setId(baseDao.getCode("Coursecomputer"));
		baseDao.saveEntity(coursecomputer);		
	}
	
	@Override
	public void addCoursecomputer(Coursecomputer ch,Coursecomputer en){
		
	/*
		int type = baseDao.getCode("Coursecomputertype");
		ch.setId(baseDao.getCode("Coursecomputer"));
		ch.setCoursecomputertype(type);
		en.setId(baseDao.getCode("Coursecomputer"));
		en.setCoursecomputertype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addCoursecomputerWithId(Coursecomputer coursecomputer){
	
		baseDao.saveEntity(coursecomputer);		
	}

//  根据id删除实体	
	@Override
	public int deleteCoursecomputer(Integer coursecomputerId){
		Coursecomputer coursecomputer = new Coursecomputer();
		coursecomputer.setId(coursecomputerId);
		//return coursecomputerDao.deleteEntity(coursecomputerId);	
		//baseDao.deleteEntityById(Coursecomputer.class,coursecomputerId);
		baseDao.deleteEntity(coursecomputer);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteCoursecomputer(Coursecomputer coursecomputer) {
		return deleteCoursecomputer(coursecomputer.getId());
	}

	
//  根据实体删除实体
	@Override
	public int deleteCoursecomputerByCoursesscheduleId(int coursesscheduleid) {
		coursecomputerDao.delCoursecomputerByCourseschedule(coursesscheduleid);
		return 1;
	}
	
	@Override
	public void updateCoursecomputer(Coursecomputer coursecomputer){
		
		Coursecomputer tempCoursecomputer = new Coursecomputer();
		//tempCoursecomputer = baseDao.getEntityById(Coursecomputer.class, coursecomputer.getId());
		tempCoursecomputer = coursecomputer;
		//add your code here
		
		
		baseDao.updateEntity(tempCoursecomputer);

	}
	
	@Override
	public List<CoursecomputerFull> selectCoursecomputerFullByPeriod(Integer courseid,Integer semesterid,Integer week,Integer day,Integer period,int language){		
		return coursecomputerDao.selectCoursecomputerFullByPeriod(courseid, semesterid, week, day, period, language);
	}
	

//	根据id查询实体类			
	@Override
	public Coursecomputer selectCoursecomputerById(Integer coursecomputerId){		
		return baseDao.getEntityById(Coursecomputer.class, coursecomputerId);
	}
	
	/*
	@Override
	public List<Coursecomputer> selectCoursecomputerAll(){
			
		return baseDao.getAllEntity(Coursecomputer.class);
		
	}
	*/
	
//	根据id查询full类
	@Override
	public CoursecomputerFull selectCoursecomputerFullById(Integer coursecomputerId){
		String sql = "where a.id=" + coursecomputerId;
		List<CoursecomputerFull> temp = coursecomputerDao.selectCoursecomputerFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	

	public int countCoursecomputerRow(){
		return baseDao.getRowCount(Coursecomputer.class);
	}
		
//  分页查询
	public List<Coursecomputer> selectCoursecomputerByPage(Page page){	
		return baseDao.selectByPage(Coursecomputer.class,page);
	}
//  分页查询full	
	public List<CoursecomputerFull> selectCoursecomputerFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Coursecomputer.class));
		return coursecomputerDao.selectCoursecomputerFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Coursecomputer> selectCoursecomputerByCondition(String condition) {
		 return coursecomputerDao.selectCoursecomputerByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
	@Override
	public List<Coursecomputer>  selectCoursecomputerByConditionAndPage(String condition,final Page page) {
		return coursecomputerDao.selectCoursecomputerByConditionAndPage(condition,page);
	}
	
	
	//条件查询full
	@Override
	public List<CoursecomputerFull> selectCoursecomputerFullByCondition(String condition) {
		return coursecomputerDao.selectCoursecomputerFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<CoursecomputerFull>  selectCoursecomputerFullByConditionAndPage(String condition,final Page page) {
			return coursecomputerDao.selectCoursecomputerFullByConditionAndPage(condition, page);
		}
	


}
