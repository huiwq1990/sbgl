package com.sbgl.app.services.teach;

import java.util.List;

import com.sbgl.app.entity.Coursecomputerorder;
import com.sbgl.app.entity.CoursecomputerorderFull;
import com.sbgl.util.*;

public interface CoursecomputerorderService{
	
	public void addCoursecomputerorder(Coursecomputerorder coursecomputerorder);

	public void addCoursecomputerorder(Coursecomputerorder ch,Coursecomputerorder en);
	public void addCoursecomputerorderWithId(Coursecomputerorder coursecomputerorder);
		
	public void updateCoursecomputerorder(Coursecomputerorder coursecomputerorder);
	
//  根据id删除实体	
	public int deleteCoursecomputerorder(Integer coursecomputerorderId);

//  根据实体删除实体
	public int deleteCoursecomputerorder(Coursecomputerorder coursecomputerorder);
	
//	根据id查询实体类		
	public Coursecomputerorder selectCoursecomputerorderById(Integer coursecomputerorderId);

//  查询全部实体
	//public List<Coursecomputerorder> selectCoursecomputerorderAll();
	
//	根据id查询full类
	public CoursecomputerorderFull selectCoursecomputerorderFullById(Integer coursecomputerorderId);

//  查询全部full
//	public List<CoursecomputerorderFull> selectCoursecomputerorderFullAll();
		
//  查询数量
	public int countCoursecomputerorderRow();
//  分页查询
	public List<Coursecomputerorder> selectCoursecomputerorderByPage(Page page);
	public List<CoursecomputerorderFull> selectCoursecomputerorderFullByPage(Page page);
		
		
	public List<Coursecomputerorder> selectCoursecomputerorderByCondition(String condition);

	public List<Coursecomputerorder>  selectCoursecomputerorderByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<CoursecomputerorderFull> selectCoursecomputerorderFullByCondition(String condition);
	
	
	// 查询实体full        
    public List<CoursecomputerorderFull>  selectCoursecomputerorderFullByConditionAndPage(String condition,final Page page);

	Coursecomputerorder selectBySemesterCourse(int semesterid,
			int courseid);
		
	
	
	
}
