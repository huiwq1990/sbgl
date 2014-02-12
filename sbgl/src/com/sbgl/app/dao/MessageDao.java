package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Message;
import com.sbgl.app.entity.MessageFull;
import com.sbgl.util.*;

public interface MessageDao{

	
//删除实体
	public int deleteEntity(Integer messageId);

public List<Message> selectMessageByCondition(String condition);
	 public List<Message>  selectMessageByConditionAndPage(String conditionSql,final Page page) ;

		public List<MessageFull> selectMessageFullByCondition(String condition);
			 public List<MessageFull>  selectMessageFullByConditionAndPage(String conditionSql,final Page page);
	
	public MessageFull selectMessageFullById(Integer messageId);
		
	public List<MessageFull> selectMessageFullAll();
		
//  分页查询 实体full
	public List<MessageFull> selectMessageFullByPage(Page page);



	//根据关联查询实体 
	public List<Message> selectMessageByLoginuserId(Integer senderid);

	public List<MessageFull> selectMessageFullByLoginuserId(Integer senderid);

 
}