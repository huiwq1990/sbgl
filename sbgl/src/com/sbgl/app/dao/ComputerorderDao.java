package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.util.*;

public interface ComputerorderDao{

	
//删除实体
	public int deleteEntity(Integer computerorderId);

public List<Computerorder> selectComputerorderByCondition(String condition);
	 public List<Computerorder>  selectComputerorderByConditionAndPage(String conditionSql,final Page page) ;

		public List<ComputerorderFull> selectComputerorderFullByCondition(String condition);
			 public List<ComputerorderFull>  selectComputerorderFullByConditionAndPage(String conditionSql,final Page page);
	
	public ComputerorderFull selectComputerorderFullById(Integer computerorderId);
		
	public List<ComputerorderFull> selectComputerorderFullAll();
		
//  分页查询 实体full
	public List<ComputerorderFull> selectComputerorderFullByPage(Page page);

	void delById(int computerorderid);

	Computerorder selectById(int computerorderid);

	List<ComputerorderFull> setUnderwayComputerorder(int uid);

	List<ComputerorderFull> selFullByStatus(int uid, int orderstatus);

	List<ComputerorderFull> selFullByStatus(int orderstatus);



	int countRow(String hql);

 
}