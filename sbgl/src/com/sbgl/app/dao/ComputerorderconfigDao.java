package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Computerorderconfig;
import com.sbgl.app.entity.ComputerorderconfigFull;
import com.sbgl.util.*;

public interface ComputerorderconfigDao{

	
//删除实体
	public int deleteEntity(Integer computerorderconfigId);

public List<Computerorderconfig> selectComputerorderconfigByCondition(String condition);
	 public List<Computerorderconfig>  selectComputerorderconfigByConditionAndPage(String conditionSql,final Page page) ;

		public List<ComputerorderconfigFull> selectComputerorderconfigFullByCondition(String condition);
			 public List<ComputerorderconfigFull>  selectComputerorderconfigFullByConditionAndPage(String conditionSql,final Page page);
	
	public ComputerorderconfigFull selectComputerorderconfigFullById(Integer computerorderconfigId);
		
	public List<ComputerorderconfigFull> selectComputerorderconfigFullAll();
		
//  分页查询 实体full
	public List<ComputerorderconfigFull> selectComputerorderconfigFullByPage(Page page);



	//根据关联查询实体 
	public List<Computerorderconfig> selectComputerorderconfigByLoginuserId(Integer createuserid);

	public List<ComputerorderconfigFull> selectComputerorderconfigFullByLoginuserId(Integer createuserid);

	Computerorderconfig selectCurrentComputerorderconfig();

 
}