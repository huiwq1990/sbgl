package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Computerorderclassruledetail;
import com.sbgl.app.entity.ComputerorderclassruledetailFull;
import com.sbgl.util.*;

public interface ComputerorderclassruledetailDao{

	
//删除实体
	public int deleteEntity(Integer computerorderclassruledetailId);

public List<Computerorderclassruledetail> selectComputerorderclassruledetailByCondition(String condition);
	 public List<Computerorderclassruledetail>  selectComputerorderclassruledetailByConditionAndPage(String conditionSql,final Page page) ;

		public List<ComputerorderclassruledetailFull> selectComputerorderclassruledetailFullByCondition(String condition);
			 public List<ComputerorderclassruledetailFull>  selectComputerorderclassruledetailFullByConditionAndPage(String conditionSql,final Page page);
	
	public ComputerorderclassruledetailFull selectComputerorderclassruledetailFullById(Integer computerorderclassruledetailId);
		
	public List<ComputerorderclassruledetailFull> selectComputerorderclassruledetailFullAll();
		
//  分页查询 实体full
	public List<ComputerorderclassruledetailFull> selectComputerorderclassruledetailFullByPage(Page page);



	//根据关联查询实体 
	public List<Computerorderclassruledetail> selectComputerorderclassruledetailByComputermodelId(Integer allowedcomputermodelid );

	public List<ComputerorderclassruledetailFull> selectComputerorderclassruledetailFullByComputermodelId(Integer allowedcomputermodelid );

 
}