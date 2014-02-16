package com.sbgl.app.services.message.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Message;
import com.sbgl.app.entity.MessageFull;
import com.sbgl.app.services.message.MessageService;
import com.sbgl.app.dao.MessageDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private MessageDao messageDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addMessage(Message message){
		message.setId(baseDao.getCode("Message"));
		baseDao.saveEntity(message);		
	}
	
	@Override
	public void addMessage(Message ch,Message en){
		
	/*
		int type = baseDao.getCode("Messagetype");
		ch.setId(baseDao.getCode("Message"));
		ch.setMessagetype(type);
		en.setId(baseDao.getCode("Message"));
		en.setMessagetype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addMessageWithId(Message message){
	
		baseDao.saveEntity(message);		
	}

//  根据id删除实体	
	@Override
	public int deleteMessage(Integer messageId){
		Message message = new Message();
		message.setId(messageId);
		//return messageDao.deleteEntity(messageId);	
		//baseDao.deleteEntityById(Message.class,messageId);
		baseDao.deleteEntity(message);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteMessage(Message message) {
		return deleteMessage(message.getId());
	}

	
	@Override
	public void updateMessage(Message message){
		
		Message tempMessage = new Message();
		//tempMessage = baseDao.getEntityById(Message.class, message.getId());
		tempMessage = message;
		//add your code here
		
		
		baseDao.updateEntity(tempMessage);

	}

//	根据id查询实体类			
	@Override
	public Message selectMessageById(Integer messageId){		
		return baseDao.getEntityById(Message.class, messageId);
	}
	
	@Override
	public List<Message> selectMessageAll(){
			
		return baseDao.getAllEntity(Message.class);
		
	}
	
//	根据id查询full类
	@Override
	public MessageFull selectMessageFullById(Integer messageId){
		String sql = "where a.id=" + messageId;
		List<MessageFull> temp = messageDao.selectMessageFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	

	public int countMessageRow(){
		return baseDao.getRowCount(Message.class);
	}
		
//  分页查询
	public List<Message> selectMessageByPage(Page page){	
		return baseDao.selectByPage(Message.class,page);
	}
//  分页查询full	
	public List<MessageFull> selectMessageFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Message.class));
		return messageDao.selectMessageFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Message> selectMessageByCondition(String condition) {
		 return messageDao.selectMessageByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
	@Override
	public List<Message>  selectMessageByConditionAndPage(String condition,final Page page) {
		return messageDao.selectMessageByConditionAndPage(condition,page);
	}
	
	
	//条件查询full
	@Override
	public List<MessageFull> selectMessageFullByCondition(String condition) {
		return messageDao.selectMessageFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<MessageFull>  selectMessageFullByConditionAndPage(String condition,final Page page) {
			return messageDao.selectMessageFullByConditionAndPage(condition, page);
		}

		@Override
		public List<MessageFull> selectMessageFullAll() {
			// TODO Auto-generated method stub
			return null;
		}
	


}
