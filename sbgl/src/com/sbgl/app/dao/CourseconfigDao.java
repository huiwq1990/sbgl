package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Courseconfig;
import com.sbgl.app.entity.CourseconfigFull;
import com.sbgl.common.DataError;
import com.sbgl.util.*;

public interface CourseconfigDao{

	
//删除实体
	public int deleteEntity(Integer courseconfigId);

public List<Courseconfig> selectCourseconfigByCondition(String condition);
	 public List<Courseconfig>  selectCourseconfigByConditionAndPage(String conditionSql,final Page page) ;

		public List<CourseconfigFull> selectCourseconfigFullByCondition(String condition);
			 public List<CourseconfigFull>  selectCourseconfigFullByConditionAndPage(String conditionSql,final Page page);
	
	public CourseconfigFull selectCourseconfigFullById(Integer courseconfigId);
		
	public List<CourseconfigFull> selectCourseconfigFullAll();
		
//  分页查询 实体full
	public List<CourseconfigFull> selectCourseconfigFullByPage(Page page);

	List<Courseconfig> selAll();

	Courseconfig getCurrentCourseconfig() throws DataError;

 
}