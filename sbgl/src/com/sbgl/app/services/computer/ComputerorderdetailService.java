package com.sbgl.app.services.computer;

import java.util.Date;
import java.util.List;

import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.util.*;

public interface ComputerorderdetailService{
	
	public void addComputerorderdetail(Computerorderdetail computerorderdetail);

	public void addComputerorderdetail(Computerorderdetail ch,Computerorderdetail en);
	public void addComputerorderdetailWithId(Computerorderdetail computerorderdetail);
		
	public void updateComputerorderdetail(Computerorderdetail computerorderdetail);
	
//  根据id删除实体	
	public int deleteComputerorderdetail(Integer computerorderdetailId);

//  根据实体删除实体
	public int deleteComputerorderdetail(Computerorderdetail computerorderdetail);
	
//	根据id查询实体类		
	public Computerorderdetail selectComputerorderdetailById(Integer computerorderdetailId);

//  查询全部实体
	public List<Computerorderdetail> selectComputerorderdetailAll();
	
//	根据id查询full类
	public ComputerorderdetailFull selectComputerorderdetailFullById(Integer computerorderdetailId);

//  查询全部full
	public List<ComputerorderdetailFull> selectComputerorderdetailFullAll();
		
//  查询数量
	public int countComputerorderdetailRow();
//  分页查询
	public List<Computerorderdetail> selectComputerorderdetailByPage(Page page);
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByPage(Page page);
		
		
		public List<Computerorderdetail> selectComputerorderdetailByCondition(String condition);

        public List<Computerorderdetail>  selectComputerorderdetailByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByCondition(String condition);
	
	
	// 查询实体full        
        public List<ComputerorderdetailFull>  selectComputerorderdetailFullByConditionAndPage(String condition,final Page page);
		
	
//根据关联查询实体	
	public List<Computerorderdetail> selectComputerorderdetailByComputerorderId(Integer computerorderid );
	public List<Computerorderdetail> selectComputerorderdetailByComputermodelId(Integer computermodelid );
	public List<Computerorderdetail> selectComputerorderdetailByComputerId(Integer computerid );
//根据关联查询实体full	
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputerorderId(Integer computerorderid );
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputermodelId(Integer computermodelid );
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputerId(Integer computerid );

	List<Computerorderdetail> selectComputerorderdetailAfterNow(
			String currentDay, int currentPeriod);

	int deleteComputerorderdetailByCondition(String condition);

	int execSql(String sql);

	List<Computerorderdetail> selectBookedComputerorderdetailFromStartToEnd(
			String startDay, int startPeriod, String endDay, int endPeriod);

	List<Computerorderdetail> selectBookedComputerorderdetailByModeltypeFromStartToEnd(
			String startDay, int startPeriod, String endDay, int endPeriod,
			String modeltypes);

	List<Computerorderdetail> selectValidComputerorderdetailFromStartToEnd(
			Date startDate, int startPeriod, Date endDate, int endPeriod);

	List<Computerorderdetail> selectValidComputerorderdetailFromStartToEndByModel(
			Date startDate, int startPeriod, Date endDate, int endPeriod,
			String modeltypeStr);



	List<ComputerorderdetailFull> selectValidFullFromStartToEndByModel(
			Date startDate, int startPeriod, Date endDate, int endPeriod,
			String modeltypeStr, int language);

	List<ComputerorderdetailFull> selectValidFullFromStartToEnd(Date startDate,
			int startPeriod, Date endDate, int endPeriod, int language);

	List<Computerorderdetail> selByOrderId(int orderid);

	List<ComputerorderdetailFull> selFullByOrderId(int orderid, int language);

		
	
	
}
