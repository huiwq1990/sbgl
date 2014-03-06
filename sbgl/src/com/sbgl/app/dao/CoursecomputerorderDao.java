package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Coursecomputerorder;
import com.sbgl.app.entity.CoursecomputerorderFull;
import com.sbgl.util.*;

public interface CoursecomputerorderDao{

	
//删除实体
	public int deleteEntity(Integer coursecomputerorderId);

public List<Coursecomputerorder> selectCoursecomputerorderByCondition(String condition);
	 public List<Coursecomputerorder>  selectCoursecomputerorderByConditionAndPage(String conditionSql,final Page page) ;

		public List<CoursecomputerorderFull> selectCoursecomputerorderFullByCondition(String condition);
			 public List<CoursecomputerorderFull>  selectCoursecomputerorderFullByConditionAndPage(String conditionSql,final Page page);
	
	public CoursecomputerorderFull selectCoursecomputerorderFullById(Integer coursecomputerorderId);
		
	public List<CoursecomputerorderFull> selectCoursecomputerorderFullAll();
		
//  分页查询 实体full
	public List<CoursecomputerorderFull> selectCoursecomputerorderFullByPage(Page page);

	Coursecomputerorder selectBySemesterCourse(int semesterid, int courseid);

 
}