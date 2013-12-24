package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Computerhomework;
import com.sbgl.app.entity.ComputerhomeworkFull;
import com.sbgl.util.*;

public interface ComputerhomeworkDao{

	
//删除实体
	public int deleteEntity(Integer computerhomeworkId);

public List<Computerhomework> selectComputerhomeworkByCondition(String condition);
	 public List<Computerhomework>  selectComputerhomeworkByConditionAndPage(String conditionSql,final Page page) ;

		public List<ComputerhomeworkFull> selectComputerhomeworkFullByCondition(String condition);
			 public List<ComputerhomeworkFull>  selectComputerhomeworkFullByConditionAndPage(String conditionSql,final Page page);
	
	public ComputerhomeworkFull selectComputerhomeworkFullById(Integer computerhomeworkId);
		
	public List<ComputerhomeworkFull> selectComputerhomeworkFullAll();
		
//  分页查询 实体full
	public List<ComputerhomeworkFull> selectComputerhomeworkFullByPage(Page page);



	//根据关联查询实体 
	public List<Computerhomework> selectComputerhomeworkByComputerorderclassruleId(Integer computerorderclassruleid );

	public List<ComputerhomeworkFull> selectComputerhomeworkFullByComputerorderclassruleId(Integer computerorderclassruleid );

 
}