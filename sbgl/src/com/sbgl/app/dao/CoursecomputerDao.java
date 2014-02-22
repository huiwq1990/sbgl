package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Coursecomputer;
import com.sbgl.app.entity.CoursecomputerFull;
import com.sbgl.util.*;

public interface CoursecomputerDao{

	
//删除实体
	public int deleteEntity(Integer coursecomputerId);

public List<Coursecomputer> selectCoursecomputerByCondition(String condition);
	 public List<Coursecomputer>  selectCoursecomputerByConditionAndPage(String conditionSql,final Page page) ;

		public List<CoursecomputerFull> selectCoursecomputerFullByCondition(String condition);
			 public List<CoursecomputerFull>  selectCoursecomputerFullByConditionAndPage(String conditionSql,final Page page);
	
	public CoursecomputerFull selectCoursecomputerFullById(Integer coursecomputerId);
		
	public List<CoursecomputerFull> selectCoursecomputerFullAll();
		
//  分页查询 实体full
	public List<CoursecomputerFull> selectCoursecomputerFullByPage(Page page);

 
}