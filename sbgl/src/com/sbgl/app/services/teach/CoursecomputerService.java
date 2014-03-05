package com.sbgl.app.services.teach;

import java.util.List;

import com.sbgl.app.entity.Coursecomputer;
import com.sbgl.app.entity.CoursecomputerFull;
import com.sbgl.util.*;

public interface CoursecomputerService{
	
	public void addCoursecomputer(Coursecomputer coursecomputer);

	public void addCoursecomputer(Coursecomputer ch,Coursecomputer en);
	public void addCoursecomputerWithId(Coursecomputer coursecomputer);
		
	public void updateCoursecomputer(Coursecomputer coursecomputer);
	
//  根据id删除实体	
	public int deleteCoursecomputer(Integer coursecomputerId);

//  根据实体删除实体
	public int deleteCoursecomputer(Coursecomputer coursecomputer);
	
//	根据id查询实体类		
	public Coursecomputer selectCoursecomputerById(Integer coursecomputerId);

//  查询全部实体
	//public List<Coursecomputer> selectCoursecomputerAll();
	
//	根据id查询full类
	public CoursecomputerFull selectCoursecomputerFullById(Integer coursecomputerId);

//  查询全部full
//	public List<CoursecomputerFull> selectCoursecomputerFullAll();
		
//  查询数量
	public int countCoursecomputerRow();
//  分页查询
	public List<Coursecomputer> selectCoursecomputerByPage(Page page);
	public List<CoursecomputerFull> selectCoursecomputerFullByPage(Page page);
		
		
	public List<Coursecomputer> selectCoursecomputerByCondition(String condition);

	public List<Coursecomputer>  selectCoursecomputerByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<CoursecomputerFull> selectCoursecomputerFullByCondition(String condition);
	
	
	// 查询实体full        
    public List<CoursecomputerFull>  selectCoursecomputerFullByConditionAndPage(String condition,final Page page);



	List<CoursecomputerFull> selectCoursecomputerFullByPeriod(Integer courseid,
			Integer semesterid, Integer week, Integer day, Integer period,
			int language);

	int deleteCoursecomputerByCoursesscheduleId(int coursesscheduleid);
		
	
	
	
}
