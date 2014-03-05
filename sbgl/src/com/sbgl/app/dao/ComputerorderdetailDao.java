package com.sbgl.app.dao;

import java.util.Date;
import java.util.List;

import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.util.*;

public interface ComputerorderdetailDao{

	
//删除实体
	public int deleteEntity(Integer computerorderdetailId);

public List<Computerorderdetail> selectComputerorderdetailByCondition(String condition);
	 public List<Computerorderdetail>  selectComputerorderdetailByConditionAndPage(String conditionSql,final Page page) ;

		public List<ComputerorderdetailFull> selectComputerorderdetailFullByCondition(String condition);
			 public List<ComputerorderdetailFull>  selectComputerorderdetailFullByConditionAndPage(String conditionSql,final Page page);
	
	public ComputerorderdetailFull selectComputerorderdetailFullById(Integer computerorderdetailId);
		
	public List<ComputerorderdetailFull> selectComputerorderdetailFullAll();
		
//  分页查询 实体full
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByPage(Page page);



	//根据关联查询实体 
	public List<Computerorderdetail> selectComputerorderdetailByComputerorderId(Integer computerorderid );
	//根据关联查询实体 
	public List<Computerorderdetail> selectComputerorderdetailByComputermodelId(Integer computermodelid );
	//根据关联查询实体 
	public List<Computerorderdetail> selectComputerorderdetailByComputerId(Integer computerid );

	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputerorderId(Integer computerorderid );
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputermodelId(Integer computermodelid );
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputerId(Integer computerid );

	int deleteComputerorderdetailByCondition(String condition);

	public List<Computerorderdetail> selectValidComputerorderdetailFromStartToEnd(
			Date currentDate, int startPeriod, Date endDate, int endPeriod);

	List<Computerorderdetail> selectValidComputerorderdetailFromStartToEndByModel(
			Date startDate, int startPeriod, Date endDate, int endPeriod,
			String modeltypeStr);



	List<Computerorderdetail> selectComputerorderByDate(Date queryDate);

 
}