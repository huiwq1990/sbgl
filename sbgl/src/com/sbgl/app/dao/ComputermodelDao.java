package com.sbgl.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.sbgl.app.entity.Borrowperiod;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.ComputermodelFull;
import com.sbgl.util.*;

public interface ComputermodelDao{

	
//删除实体
	public int deleteEntity(Integer computermodelId);

public List<Computermodel> selectComputermodelByCondition(String condition);
	 public List<Computermodel>  selectComputermodelByConditionAndPage(String conditionSql,final Page page) ;

		public List<ComputermodelFull> selectComputermodelFullByCondition(String condition);
			 public List<ComputermodelFull>  selectComputermodelFullByConditionAndPage(String conditionSql,final Page page);
	
	public ComputermodelFull selectComputermodelFullById(Integer computermodelId);
		
	public List<ComputermodelFull> selectComputermodelFullAll();
		
//  分页查询 实体full
	public List<ComputermodelFull> selectComputermodelFullByPage(Page page);



	//根据关联查询实体 
	public List<Computermodel> selectComputermodelByComputercategoryId(Integer computercategoryid );

	public List<ComputermodelFull> selectComputermodelFullByComputercategoryId(Integer computercategoryid );



 
}