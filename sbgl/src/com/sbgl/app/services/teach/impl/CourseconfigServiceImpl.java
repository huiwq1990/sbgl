package com.sbgl.app.services.teach.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Courseconfig;
import com.sbgl.app.entity.CourseconfigFull;
import com.sbgl.app.services.teach.CourseconfigService;
import com.sbgl.app.actions.teach.TeachConstant;
import com.sbgl.app.dao.CourseconfigDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.common.DataError;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("courseconfigService")
@Transactional
public class CourseconfigServiceImpl implements CourseconfigService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private CourseconfigDao courseconfigDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addCourseconfig(Courseconfig courseconfig){
		
//		将所有当前学期设置不是当前学期
		baseDao.createSQL(" update Courseconfig set currentsemester = "+TeachConstant.coursesconfignotcurrentsemester+" where currentsemester = "+TeachConstant.coursesconfigcurrentsemester);
		
		courseconfig.setId(baseDao.getCode("Courseconfig"));
//		courseconfig.set
		courseconfig.setStatus(TeachConstant.coursesconfigvalidstatus);
//		将添加的设置为当前学期
		courseconfig.setCurrentsemester(TeachConstant.coursesconfigcurrentsemester);
		courseconfig.setStatus(TeachConstant.coursesconfigvalidstatus);
		baseDao.saveEntity(courseconfig);		
	}
	
	@Override
	public void addCourseconfig(Courseconfig ch,Courseconfig en){
		
	/*
		int type = baseDao.getCode("Courseconfigtype");
		ch.setId(baseDao.getCode("Courseconfig"));
		ch.setCourseconfigtype(type);
		en.setId(baseDao.getCode("Courseconfig"));
		en.setCourseconfigtype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addCourseconfigWithId(Courseconfig courseconfig){
	
		baseDao.saveEntity(courseconfig);		
	}

//  根据id删除实体	
	@Override
	public int deleteCourseconfig(Integer courseconfigId){
		Courseconfig courseconfig = new Courseconfig();
		courseconfig.setId(courseconfigId);
		//return courseconfigDao.deleteEntity(courseconfigId);	
		//baseDao.deleteEntityById(Courseconfig.class,courseconfigId);
		baseDao.deleteEntity(courseconfig);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteCourseconfig(Courseconfig courseconfig) {
		return deleteCourseconfig(courseconfig.getId());
	}

	
	@Override
	public void updateCourseconfig(Courseconfig courseconfig){
		
		Courseconfig tempCourseconfig = new Courseconfig();
		//tempCourseconfig = baseDao.getEntityById(Courseconfig.class, courseconfig.getId());
		tempCourseconfig = courseconfig;
		//add your code here
		
		
		baseDao.updateEntity(tempCourseconfig);

	}

	
	
	/**
	 * 如果存在当前学期则返回，否则为null
	 */
	@Override
	public Courseconfig getCurrentCourseconfig( ) throws DataError{		
		
		return courseconfigDao.getCurrentCourseconfig();
	}
	
	
	
//	根据id查询实体类			
	@Override
	public Courseconfig selectCourseconfigById(Integer courseconfigId){		
		return baseDao.getEntityById(Courseconfig.class, courseconfigId);
	}
	
	/**
	 * 
	 */
	@Override
	public List<Courseconfig> selAll(){		
		return courseconfigDao.selAll();
	}
	
//	@Override
//	public List<Courseconfig> selectCourseconfigAll(){
//			
//		return baseDao.getAllEntity(Courseconfig.class);
//		
//	}
	
//	根据id查询full类
	@Override
	public CourseconfigFull selectCourseconfigFullById(Integer courseconfigId){
		String sql = "where a.id=" + courseconfigId;
		List<CourseconfigFull> temp = courseconfigDao.selectCourseconfigFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	

	public int countCourseconfigRow(){
		return baseDao.getRowCount(Courseconfig.class);
	}
		
//  分页查询
	public List<Courseconfig> selectCourseconfigByPage(Page page){	
		return baseDao.selectByPage(Courseconfig.class,page);
	}
//  分页查询full	
	public List<CourseconfigFull> selectCourseconfigFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Courseconfig.class));
		return courseconfigDao.selectCourseconfigFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Courseconfig> selectCourseconfigByCondition(String condition) {
		 return courseconfigDao.selectCourseconfigByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
	@Override
	public List<Courseconfig>  selectCourseconfigByConditionAndPage(String condition,final Page page) {
		return courseconfigDao.selectCourseconfigByConditionAndPage(condition,page);
	}
	
	
	//条件查询full
	@Override
	public List<CourseconfigFull> selectCourseconfigFullByCondition(String condition) {
		return courseconfigDao.selectCourseconfigFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<CourseconfigFull>  selectCourseconfigFullByConditionAndPage(String condition,final Page page) {
			return courseconfigDao.selectCourseconfigFullByConditionAndPage(condition, page);
		}
	
    	@Override
    	public int execSql(String sql) {
//    		String sql = "delete from Computerorderdetail " + condition;
    		baseDao.createSQL(sql);
    		return 1;
    		
    	}


}
