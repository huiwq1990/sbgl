package com.sbgl.app.services.teach;

import java.util.List;

import com.sbgl.app.entity.Courseschedulecomputerorder;
import com.sbgl.app.entity.CourseschedulecomputerorderFull;
import com.sbgl.util.*;

public interface CourseschedulecomputerorderService{
	
	public void addCourseschedulecomputerorder(Courseschedulecomputerorder courseschedulecomputerorder);

	public void addCourseschedulecomputerorder(Courseschedulecomputerorder ch,Courseschedulecomputerorder en);
	public void addCourseschedulecomputerorderWithId(Courseschedulecomputerorder courseschedulecomputerorder);
		
	public void updateCourseschedulecomputerorder(Courseschedulecomputerorder courseschedulecomputerorder);
	
//  根据id删除实体	
	public int deleteCourseschedulecomputerorder(Integer courseschedulecomputerorderId);

//  根据实体删除实体
	public int deleteCourseschedulecomputerorder(Courseschedulecomputerorder courseschedulecomputerorder);
	
//	根据id查询实体类		
	public Courseschedulecomputerorder selectCourseschedulecomputerorderById(Integer courseschedulecomputerorderId);

//  查询全部实体
	//public List<Courseschedulecomputerorder> selectCourseschedulecomputerorderAll();
	
//	根据id查询full类
	public CourseschedulecomputerorderFull selectCourseschedulecomputerorderFullById(Integer courseschedulecomputerorderId);

//  查询全部full
//	public List<CourseschedulecomputerorderFull> selectCourseschedulecomputerorderFullAll();
		
//  查询数量
	public int countCourseschedulecomputerorderRow();
//  分页查询
	public List<Courseschedulecomputerorder> selectCourseschedulecomputerorderByPage(Page page);
	public List<CourseschedulecomputerorderFull> selectCourseschedulecomputerorderFullByPage(Page page);
		
		
	public List<Courseschedulecomputerorder> selectCourseschedulecomputerorderByCondition(String condition);

	public List<Courseschedulecomputerorder>  selectCourseschedulecomputerorderByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<CourseschedulecomputerorderFull> selectCourseschedulecomputerorderFullByCondition(String condition);
	
	
	// 查询实体full        
    public List<CourseschedulecomputerorderFull>  selectCourseschedulecomputerorderFullByConditionAndPage(String condition,final Page page);
		
	
	
	
}
