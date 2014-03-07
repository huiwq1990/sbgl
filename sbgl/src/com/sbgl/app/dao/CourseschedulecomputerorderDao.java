package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Courseschedulecomputerorder;
import com.sbgl.app.entity.CourseschedulecomputerorderFull;
import com.sbgl.util.*;

public interface CourseschedulecomputerorderDao{

	
//删除实体
	public int deleteEntity(Integer courseschedulecomputerorderId);

public List<Courseschedulecomputerorder> selectCourseschedulecomputerorderByCondition(String condition);
	 public List<Courseschedulecomputerorder>  selectCourseschedulecomputerorderByConditionAndPage(String conditionSql,final Page page) ;

		public List<CourseschedulecomputerorderFull> selectCourseschedulecomputerorderFullByCondition(String condition);
			 public List<CourseschedulecomputerorderFull>  selectCourseschedulecomputerorderFullByConditionAndPage(String conditionSql,final Page page);
	
	public CourseschedulecomputerorderFull selectCourseschedulecomputerorderFullById(Integer courseschedulecomputerorderId);
		
	public List<CourseschedulecomputerorderFull> selectCourseschedulecomputerorderFullAll();
		
//  分页查询 实体full
	public List<CourseschedulecomputerorderFull> selectCourseschedulecomputerorderFullByPage(Page page);



	//根据关联查询实体 
	public List<Courseschedulecomputerorder> selectCourseschedulecomputerorderByComputerorderId(Integer computerorderid);

	public List<CourseschedulecomputerorderFull> selectCourseschedulecomputerorderFullByComputerorderId(Integer computerorderid);

	void delByCoursescheduleid(int csid);

	List<Courseschedulecomputerorder> selectByCoursescheduleid(
			int coursescheduleid);

 
}