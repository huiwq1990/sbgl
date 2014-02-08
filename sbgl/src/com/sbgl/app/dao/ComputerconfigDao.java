package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Computerconfig;
import com.sbgl.app.entity.ComputerconfigFull;
import com.sbgl.util.*;

public interface ComputerconfigDao{

	
//删除实体
	public int deleteEntity(Integer computerconfigId);

public List<Computerconfig> selectComputerconfigByCondition(String condition);
	 public List<Computerconfig>  selectComputerconfigByConditionAndPage(String conditionSql,final Page page) ;

		public List<ComputerconfigFull> selectComputerconfigFullByCondition(String condition);
			 public List<ComputerconfigFull>  selectComputerconfigFullByConditionAndPage(String conditionSql,final Page page);
	
	public ComputerconfigFull selectComputerconfigFullById(Integer computerconfigId);
		
	public List<ComputerconfigFull> selectComputerconfigFullAll();
		
//  分页查询 实体full
	public List<ComputerconfigFull> selectComputerconfigFullByPage(Page page);

 
}