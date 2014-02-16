package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Course;
import com.sbgl.app.entity.CourseFull;
import com.sbgl.util.*;

public interface CourseDao{

	
//删除实体
	public int deleteEntity(Integer courseId);

public List<Course> selectCourseByCondition(String condition);
	 public List<Course>  selectCourseByConditionAndPage(String conditionSql,final Page page) ;

		public List<CourseFull> selectCourseFullByCondition(String condition);
			 public List<CourseFull>  selectCourseFullByConditionAndPage(String conditionSql,final Page page);
	
	public CourseFull selectCourseFullById(Integer courseId);
		
	public List<CourseFull> selectCourseFullAll();
		
//  分页查询 实体full
	public List<CourseFull> selectCourseFullByPage(Page page);



	//根据关联查询实体 
	public List<Course> selectCourseByLoginuserId(Integer adduserid);
	//根据关联查询实体 
	public List<Course> selectCourseByLoginuserId(Integer teacherid);

	public List<CourseFull> selectCourseFullByLoginuserId(Integer adduserid);
	public List<CourseFull> selectCourseFullByLoginuserId(Integer teacherid);

 
}