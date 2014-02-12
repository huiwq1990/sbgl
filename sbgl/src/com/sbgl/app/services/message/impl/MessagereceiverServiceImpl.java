package com.sbgl.app.services.message.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Messagereceiver;
import com.sbgl.app.entity.MessagereceiverFull;
import com.sbgl.app.services.message.MessagereceiverService;
import com.sbgl.app.dao.MessagereceiverDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("messagereceiverService")
@Transactional
public class MessagereceiverServiceImpl implements MessagereceiverService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private MessagereceiverDao messagereceiverDao;
	
//	public void sendNotification(String ){
//		
//	}
	
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addMessagereceiver(Messagereceiver messagereceiver){
		messagereceiver.setId(baseDao.getCode("Messagereceiver"));
		baseDao.saveEntity(messagereceiver);		
	}
	
	@Override
	public void addMessagereceiver(Messagereceiver ch,Messagereceiver en){
		
	/*
		int type = baseDao.getCode("Messagereceivertype");
		ch.setId(baseDao.getCode("Messagereceiver"));
		ch.setMessagereceivertype(type);
		en.setId(baseDao.getCode("Messagereceiver"));
		en.setMessagereceivertype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addMessagereceiverWithId(Messagereceiver messagereceiver){
	
		baseDao.saveEntity(messagereceiver);		
	}

//  根据id删除实体	
	@Override
	public int deleteMessagereceiver(Integer messagereceiverId){
		Messagereceiver messagereceiver = new Messagereceiver();
		messagereceiver.setId(messagereceiverId);
		//return messagereceiverDao.deleteEntity(messagereceiverId);	
		//baseDao.deleteEntityById(Messagereceiver.class,messagereceiverId);
		baseDao.deleteEntity(messagereceiver);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteMessagereceiver(Messagereceiver messagereceiver) {
		return deleteMessagereceiver(messagereceiver.getId());
	}

	
	@Override
	public void updateMessagereceiver(Messagereceiver messagereceiver){
		
		Messagereceiver tempMessagereceiver = new Messagereceiver();
		//tempMessagereceiver = baseDao.getEntityById(Messagereceiver.class, messagereceiver.getId());
		tempMessagereceiver = messagereceiver;
		//add your code here
		
		
		baseDao.updateEntity(tempMessagereceiver);

	}

//	根据id查询实体类			
	@Override
	public Messagereceiver selectMessagereceiverById(Integer messagereceiverId){		
		return baseDao.getEntityById(Messagereceiver.class, messagereceiverId);
	}
	
	@Override
	public List<Messagereceiver> selectMessagereceiverAll(){
			
		return baseDao.getAllEntity(Messagereceiver.class);
		
	}
	
//	根据id查询full类
	@Override
	public MessagereceiverFull selectMessagereceiverFullById(Integer messagereceiverId){
		String sql = "where a.id=" + messagereceiverId;
		List<MessagereceiverFull> temp = messagereceiverDao.selectMessagereceiverFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	

	public int countMessagereceiverRow(){
		return baseDao.getRowCount(Messagereceiver.class);
	}
		
//  分页查询
	public List<Messagereceiver> selectMessagereceiverByPage(Page page){	
		return baseDao.selectByPage(Messagereceiver.class,page);
	}
//  分页查询full	
	public List<MessagereceiverFull> selectMessagereceiverFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Messagereceiver.class));
		return messagereceiverDao.selectMessagereceiverFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Messagereceiver> selectMessagereceiverByCondition(String condition) {
		 return messagereceiverDao.selectMessagereceiverByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
	@Override
	public List<Messagereceiver>  selectMessagereceiverByConditionAndPage(String condition,final Page page) {
		return messagereceiverDao.selectMessagereceiverByConditionAndPage(condition,page);
	}
	
	
	//条件查询full
	@Override
	public List<MessagereceiverFull> selectMessagereceiverFullByCondition(String condition) {
		return messagereceiverDao.selectMessagereceiverFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<MessagereceiverFull>  selectMessagereceiverFullByConditionAndPage(String condition,final Page page) {
			return messagereceiverDao.selectMessagereceiverFullByConditionAndPage(condition, page);
		}

		
	


}
