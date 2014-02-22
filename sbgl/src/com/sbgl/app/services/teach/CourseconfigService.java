package com.sbgl.app.services.teach;

import java.util.List;

import com.sbgl.app.entity.Courseconfig;
import com.sbgl.app.entity.CourseconfigFull;
import com.sbgl.util.*;

public interface CourseconfigService{
	
	public void addCourseconfig(Courseconfig courseconfig);

	public void addCourseconfig(Courseconfig ch,Courseconfig en);
	public void addCourseconfigWithId(Courseconfig courseconfig);
		
	public void updateCourseconfig(Courseconfig courseconfig);
	
//  根据id删除实体	
	public int deleteCourseconfig(Integer courseconfigId);

//  根据实体删除实体
	public int deleteCourseconfig(Courseconfig courseconfig);
	
//	根据id查询实体类		
	public Courseconfig selectCourseconfigById(Integer courseconfigId);

//  查询全部实体
	//public List<Courseconfig> selectCourseconfigAll();
	
//	根据id查询full类
	public CourseconfigFull selectCourseconfigFullById(Integer courseconfigId);

//  查询全部full
//	public List<CourseconfigFull> selectCourseconfigFullAll();
		
//  查询数量
	public int countCourseconfigRow();
//  分页查询
	public List<Courseconfig> selectCourseconfigByPage(Page page);
	public List<CourseconfigFull> selectCourseconfigFullByPage(Page page);
		
		
	public List<Courseconfig> selectCourseconfigByCondition(String condition);

	public List<Courseconfig>  selectCourseconfigByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<CourseconfigFull> selectCourseconfigFullByCondition(String condition);
	
	
	// 查询实体full        
    public List<CourseconfigFull>  selectCourseconfigFullByConditionAndPage(String condition,final Page page);

	int execSql(String sql);
		
	
	
	
}
