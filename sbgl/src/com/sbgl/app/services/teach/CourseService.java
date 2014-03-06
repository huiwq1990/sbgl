package com.sbgl.app.services.teach;

import java.util.List;

import com.sbgl.app.entity.Course;
import com.sbgl.app.entity.CourseFull;
import com.sbgl.common.DataError;
import com.sbgl.util.*;

public interface CourseService{
	
	public void addCourse(Course course);

	public void addCourse(Course ch,Course en);
	public void addCourseWithId(Course course);
		
	public void updateCourse(Course course);
	
//  根据id删除实体	
	public int deleteCourse(Integer courseId);

//  根据实体删除实体
	public int deleteCourse(Course course);
	
//	根据id查询实体类		
	public Course selectCourseById(Integer courseId);

//  查询全部实体
	//public List<Course> selectCourseAll();
	
//	根据id查询full类
	public CourseFull selectCourseFullById(Integer courseId);

//  查询全部full
//	public List<CourseFull> selectCourseFullAll();
		
//  查询数量
	public int countCourseRow();
//  分页查询
	public List<Course> selectCourseByPage(Page page);
	public List<CourseFull> selectCourseFullByPage(Page page);
		
		
	public List<Course> selectCourseByCondition(String condition);

	public List<Course>  selectCourseByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<CourseFull> selectCourseFullByCondition(String condition);
	
	
	// 查询实体full        
    public List<CourseFull>  selectCourseFullByConditionAndPage(String condition,final Page page);

	boolean updateCourse(Course ch, Course en);

	int deleteCourse(List<Integer> delCourseIdList) throws DataError;

	Course selectCourseByCoursetype(Integer coursetype, int language);
		
	
	
	
}
