package com.sbgl.app.services.message;

import java.util.List;

import com.sbgl.app.entity.Message;
import com.sbgl.app.entity.MessageFull;
import com.sbgl.util.*;

public interface MessageService{
	
	public void addMessage(Message message);

	public void addMessage(Message ch,Message en);
	public void addMessageWithId(Message message);
		
	public void updateMessage(Message message);
	
//  根据id删除实体	
	public int deleteMessage(Integer messageId);

//  根据实体删除实体
	public int deleteMessage(Message message);
	
//	根据id查询实体类		
	public Message selectMessageById(Integer messageId);

//  查询全部实体
	public List<Message> selectMessageAll();
	
//	根据id查询full类
	public MessageFull selectMessageFullById(Integer messageId);

//  查询全部full
	public List<MessageFull> selectMessageFullAll();
		
//  查询数量
	public int countMessageRow();
//  分页查询
	public List<Message> selectMessageByPage(Page page);
	public List<MessageFull> selectMessageFullByPage(Page page);
		
		
	public List<Message> selectMessageByCondition(String condition);

	public List<Message>  selectMessageByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<MessageFull> selectMessageFullByCondition(String condition);
	
	
	// 查询实体full        
    public List<MessageFull>  selectMessageFullByConditionAndPage(String condition,final Page page);
		
	
	
	
}
