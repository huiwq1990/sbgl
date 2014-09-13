package com.sbgl.app.dao;

import java.util.Date;
import java.util.List;

import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.entity.CoursescheduleFull;
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


	int deleteComputerorderdetailByCondition(String condition);

	public List<Computerorderdetail> selectValidComputerorderdetailFromStartToEnd(
			Date currentDate, int startPeriod, Date endDate, int endPeriod);

	List<Computerorderdetail> selectValidComputerorderdetailFromStartToEndByModel(
			Date startDate, int startPeriod, Date endDate, int endPeriod,
			String modeltypeStr);



	List<Computerorderdetail> selectComputerorderByDate(Date queryDate);

	void delByComputerorderid(int computerorderid);


	void delByPeriod(int computerorderid, String borrowday, int period);



	

	void delByPeriodComputermodeltype(String borrowday, int period,
			int computermodeltype);

	void delById(int computerorderid);



	List<ComputerorderdetailFull> selectValidFullFromStartToEnd(Date startDate,
			int startPeriod, Date endDate, int endPeriod, int language);

	List<ComputerorderdetailFull> selectValidFullFromStartToEndByModel(
			Date startDate, int startPeriod, Date endDate, int endPeriod,
			String modeltypeStr, int language);

	List<Computerorderdetail> selByOrderId(int orderid);

	List<ComputerorderdetailFull> selFullByOrderId(int orderid, int language);

	int getOrderTime(int orderid);

	List<Computerorderdetail> selectValidComputerorderdetailFromStartToEndByModel(
			Date startDate, int startPeriod, Date endDate, int endPeriod,
			List<Integer> modeltypeList);

	void delById(List<Integer> idList);



 
}