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

	List<CourseFull> selectCourseFullByCoursetype(Integer coursetype, int language);

	List<Course> selectCourseByCoursetype(Integer coursetype, int language);

	int countRowByGrade(int grade);

	List<CourseFull> selFullByGradePage(Integer grade, Page page, int language);

	List<CourseFull> selFullByGrade(Integer grade, int language);

	Course sel(int coursetype, int language);


 
}