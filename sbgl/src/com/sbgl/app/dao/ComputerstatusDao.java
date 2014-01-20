package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Computerstatus;
import com.sbgl.app.entity.ComputerstatusFull;
import com.sbgl.util.*;

public interface ComputerstatusDao{

	
//删除实体
	public int deleteEntity(Integer computerstatusId);

public List<Computerstatus> selectComputerstatusByCondition(String condition);
	 public List<Computerstatus>  selectComputerstatusByConditionAndPage(String conditionSql,final Page page) ;

		public List<ComputerstatusFull> selectComputerstatusFullByCondition(String condition);
			 public List<ComputerstatusFull>  selectComputerstatusFullByConditionAndPage(String conditionSql,final Page page);
	
	public ComputerstatusFull selectComputerstatusFullById(Integer computerstatusId);
		
	public List<ComputerstatusFull> selectComputerstatusFullAll();
		
//  分页查询 实体full
	public List<ComputerstatusFull> selectComputerstatusFullByPage(Page page);

 
}