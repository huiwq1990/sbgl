package com.sbgl.app.services.teach.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Coursecomputerorder;
import com.sbgl.app.entity.CoursecomputerorderFull;
import com.sbgl.app.services.teach.CoursecomputerorderService;
import com.sbgl.app.dao.CoursecomputerorderDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("coursecomputerorderService")
@Transactional
public class CoursecomputerorderServiceImpl implements CoursecomputerorderService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private CoursecomputerorderDao coursecomputerorderDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addCoursecomputerorder(Coursecomputerorder coursecomputerorder){
		coursecomputerorder.setId(baseDao.getCode("Coursecomputerorder"));
		baseDao.saveEntity(coursecomputerorder);		
	}
	
	@Override
	public void addCoursecomputerorder(Coursecomputerorder ch,Coursecomputerorder en){
		
	/*
		int type = baseDao.getCode("Coursecomputerordertype");
		ch.setId(baseDao.getCode("Coursecomputerorder"));
		ch.setCoursecomputerordertype(type);
		en.setId(baseDao.getCode("Coursecomputerorder"));
		en.setCoursecomputerordertype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addCoursecomputerorderWithId(Coursecomputerorder coursecomputerorder){
	
		baseDao.saveEntity(coursecomputerorder);		
	}

//  根据id删除实体	
	@Override
	public int deleteCoursecomputerorder(Integer coursecomputerorderId){
		Coursecomputerorder coursecomputerorder = new Coursecomputerorder();
		coursecomputerorder.setId(coursecomputerorderId);
		//return coursecomputerorderDao.deleteEntity(coursecomputerorderId);	
		//baseDao.deleteEntityById(Coursecomputerorder.class,coursecomputerorderId);
		baseDao.deleteEntity(coursecomputerorder);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteCoursecomputerorder(Coursecomputerorder coursecomputerorder) {
		return deleteCoursecomputerorder(coursecomputerorder.getId());
	}

	
	@Override
	public void updateCoursecomputerorder(Coursecomputerorder coursecomputerorder){
		
		Coursecomputerorder tempCoursecomputerorder = new Coursecomputerorder();
		//tempCoursecomputerorder = baseDao.getEntityById(Coursecomputerorder.class, coursecomputerorder.getId());
		tempCoursecomputerorder = coursecomputerorder;
		//add your code here
		
		
		baseDao.updateEntity(tempCoursecomputerorder);

	}

	/**
	 * 查询某学期某个课程的 某个学期的预约
	 * 学期信息
	 * 课程类型
	 */
	@Override
	public Coursecomputerorder selectBySemesterCourse(int semesterid,int courseid){
		
		
		return coursecomputerorderDao.selectBySemesterCourse(semesterid, courseid);
	}
	
//	根据id查询实体类			
	@Override
	public Coursecomputerorder selectCoursecomputerorderById(Integer coursecomputerorderId){		
		return baseDao.getEntityById(Coursecomputerorder.class, coursecomputerorderId);
	}
	
	/*
	@Override
	public List<Coursecomputerorder> selectCoursecomputerorderAll(){
			
		return baseDao.getAllEntity(Coursecomputerorder.class);
		
	}
	*/
	
//	根据id查询full类
	@Override
	public CoursecomputerorderFull selectCoursecomputerorderFullById(Integer coursecomputerorderId){
		String sql = "where a.id=" + coursecomputerorderId;
		List<CoursecomputerorderFull> temp = coursecomputerorderDao.selectCoursecomputerorderFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	

	public int countCoursecomputerorderRow(){
		return baseDao.getRowCount(Coursecomputerorder.class);
	}
		
//  分页查询
	public List<Coursecomputerorder> selectCoursecomputerorderByPage(Page page){	
		return baseDao.selectByPage(Coursecomputerorder.class,page);
	}
//  分页查询full	
	public List<CoursecomputerorderFull> selectCoursecomputerorderFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Coursecomputerorder.class));
		return coursecomputerorderDao.selectCoursecomputerorderFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Coursecomputerorder> selectCoursecomputerorderByCondition(String condition) {
		 return coursecomputerorderDao.selectCoursecomputerorderByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
	@Override
	public List<Coursecomputerorder>  selectCoursecomputerorderByConditionAndPage(String condition,final Page page) {
		return coursecomputerorderDao.selectCoursecomputerorderByConditionAndPage(condition,page);
	}
	
	
	//条件查询full
	@Override
	public List<CoursecomputerorderFull> selectCoursecomputerorderFullByCondition(String condition) {
		return coursecomputerorderDao.selectCoursecomputerorderFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<CoursecomputerorderFull>  selectCoursecomputerorderFullByConditionAndPage(String condition,final Page page) {
			return coursecomputerorderDao.selectCoursecomputerorderFullByConditionAndPage(condition, page);
		}
	


}
