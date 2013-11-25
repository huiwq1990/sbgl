package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Computerorderclassrule;
import com.sbgl.app.entity.ComputerorderclassruleFull;
import com.sbgl.util.*;

public interface ComputerorderclassruleDao{

	
//删除实体
	public int deleteEntity(Integer computerorderclassruleId);

public List<Computerorderclassrule> selectComputerorderclassruleByCondition(String condition);
	 public List<Computerorderclassrule>  selectComputerorderclassruleByConditionAndPage(String conditionSql,final Page page) ;

		public List<ComputerorderclassruleFull> selectComputerorderclassruleFullByCondition(String condition);
			 public List<ComputerorderclassruleFull>  selectComputerorderclassruleFullByConditionAndPage(String conditionSql,final Page page);
	
	public ComputerorderclassruleFull selectComputerorderclassruleFullById(Integer computerorderclassruleId);
		
	public List<ComputerorderclassruleFull> selectComputerorderclassruleFullAll();
		
//  分页查询 实体full
	public List<ComputerorderclassruleFull> selectComputerorderclassruleFullByPage(Page page);

 
}