package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Messagereceiver;
import com.sbgl.app.entity.MessagereceiverFull;
import com.sbgl.util.*;

public interface MessagereceiverDao{

	
//删除实体
	public int deleteEntity(Integer messagereceiverId);

public List<Messagereceiver> selectMessagereceiverByCondition(String condition);
	 public List<Messagereceiver>  selectMessagereceiverByConditionAndPage(String conditionSql,final Page page) ;

		public List<MessagereceiverFull> selectMessagereceiverFullByCondition(String condition);
			 public List<MessagereceiverFull>  selectMessagereceiverFullByConditionAndPage(String conditionSql,final Page page);
	
	public MessagereceiverFull selectMessagereceiverFullById(Integer messagereceiverId);
		
	public List<MessagereceiverFull> selectMessagereceiverFullAll();
		
//  分页查询 实体full
	public List<MessagereceiverFull> selectMessagereceiverFullByPage(Page page);



	//根据关联查询实体 
	public List<Messagereceiver> selectMessagereceiverByLoginuserId(Integer receiverid);

	public List<MessagereceiverFull> selectMessagereceiverFullByLoginuserId(Integer receiverid);

 
}