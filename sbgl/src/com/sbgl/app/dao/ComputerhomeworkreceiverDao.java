package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Computerhomeworkreceiver;
import com.sbgl.app.entity.ComputerhomeworkreceiverFull;
import com.sbgl.util.*;

public interface ComputerhomeworkreceiverDao{

	
//删除实体
	public int deleteEntity(Integer computerhomeworkreceiverId);

public List<Computerhomeworkreceiver> selectComputerhomeworkreceiverByCondition(String condition);
	 public List<Computerhomeworkreceiver>  selectComputerhomeworkreceiverByConditionAndPage(String conditionSql,final Page page) ;

		public List<ComputerhomeworkreceiverFull> selectComputerhomeworkreceiverFullByCondition(String condition);
			 public List<ComputerhomeworkreceiverFull>  selectComputerhomeworkreceiverFullByConditionAndPage(String conditionSql,final Page page);
	
	public ComputerhomeworkreceiverFull selectComputerhomeworkreceiverFullById(Integer computerhomeworkreceiverId);
		
	public List<ComputerhomeworkreceiverFull> selectComputerhomeworkreceiverFullAll();
		
//  分页查询 实体full
	public List<ComputerhomeworkreceiverFull> selectComputerhomeworkreceiverFullByPage(Page page);



	//根据关联查询实体 
	public List<Computerhomeworkreceiver> selectComputerhomeworkreceiverByLoginuserId(Integer userid );

	public List<ComputerhomeworkreceiverFull> selectComputerhomeworkreceiverFullByLoginuserId(Integer userid );

	List<Computerhomeworkreceiver> sel(int homeworkid, int userid);

 
}