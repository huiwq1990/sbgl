package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Courseschedule;
import com.sbgl.app.entity.CoursescheduleFull;
import com.sbgl.util.*;

public interface CoursescheduleDao{

	
//删除实体
	public int deleteEntity(Integer coursescheduleId);

public List<Courseschedule> selectCoursescheduleByCondition(String condition);
	 public List<Courseschedule>  selectCoursescheduleByConditionAndPage(String conditionSql,final Page page) ;

		public List<CoursescheduleFull> selectCoursescheduleFullByCondition(String condition);
			 public List<CoursescheduleFull>  selectCoursescheduleFullByConditionAndPage(String conditionSql,final Page page);
	
	public CoursescheduleFull selectCoursescheduleFullById(Integer coursescheduleId);
		
	public List<CoursescheduleFull> selectCoursescheduleFullAll();
		
//  分页查询 实体full
	public List<CoursescheduleFull> selectCoursescheduleFullByPage(Page page);

 
}