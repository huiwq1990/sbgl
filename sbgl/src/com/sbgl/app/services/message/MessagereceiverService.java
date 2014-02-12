package com.sbgl.app.services.message;

import java.util.List;

import com.sbgl.app.entity.Messagereceiver;
import com.sbgl.app.entity.MessagereceiverFull;
import com.sbgl.util.*;

public interface MessagereceiverService{
	
	public void addMessagereceiver(Messagereceiver messagereceiver);

	public void addMessagereceiver(Messagereceiver ch,Messagereceiver en);
	public void addMessagereceiverWithId(Messagereceiver messagereceiver);
		
	public void updateMessagereceiver(Messagereceiver messagereceiver);
	
//  根据id删除实体	
	public int deleteMessagereceiver(Integer messagereceiverId);

//  根据实体删除实体
	public int deleteMessagereceiver(Messagereceiver messagereceiver);
	
//	根据id查询实体类		
	public Messagereceiver selectMessagereceiverById(Integer messagereceiverId);

//  查询全部实体
	public List<Messagereceiver> selectMessagereceiverAll();
	
//	根据id查询full类
	public MessagereceiverFull selectMessagereceiverFullById(Integer messagereceiverId);


		
//  查询数量
	public int countMessagereceiverRow();
//  分页查询
	public List<Messagereceiver> selectMessagereceiverByPage(Page page);
	public List<MessagereceiverFull> selectMessagereceiverFullByPage(Page page);
		
		
	public List<Messagereceiver> selectMessagereceiverByCondition(String condition);

	public List<Messagereceiver>  selectMessagereceiverByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<MessagereceiverFull> selectMessagereceiverFullByCondition(String condition);
	
	
	// 查询实体full        
    public List<MessagereceiverFull>  selectMessagereceiverFullByConditionAndPage(String condition,final Page page);
		
	
	
	
}
