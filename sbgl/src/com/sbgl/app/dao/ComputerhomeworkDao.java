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



	List<Computerhomework> sel(Integer id);

	List<ComputerhomeworkFull> selFullByList(List<Integer> idList);

 
}