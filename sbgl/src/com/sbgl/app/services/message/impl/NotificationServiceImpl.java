package com.sbgl.app.services.message.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Notification;
import com.sbgl.app.entity.NotificationFull;
import com.sbgl.app.services.message.NotificationService;
import com.sbgl.app.dao.NotificationDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("notificationService")
@Transactional
public class NotificationServiceImpl implements NotificationService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private NotificationDao notificationDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addNotification(Notification notification){
		notification.setId(baseDao.getCode("Notification"));
		baseDao.saveEntity(notification);		
	}
	
	@Override
	public void addNotification(Notification ch,Notification en){
		
	/*
		int type = baseDao.getCode("Notificationtype");
		ch.setId(baseDao.getCode("Notification"));
		ch.setNotificationtype(type);
		en.setId(baseDao.getCode("Notification"));
		en.setNotificationtype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addNotificationWithId(Notification notification){
	
		baseDao.saveEntity(notification);		
	}

//  根据id删除实体	
	@Override
	public int deleteNotification(Integer notificationId){
		Notification notification = new Notification();
		notification.setId(notificationId);
		//return notificationDao.deleteEntity(notificationId);	
		//baseDao.deleteEntityById(Notification.class,notificationId);
		baseDao.deleteEntity(notification);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteNotification(Notification notification) {
		return deleteNotification(notification.getId());
	}

	
	@Override
	public void updateNotification(Notification notification){
		
		Notification tempNotification = new Notification();
		//tempNotification = baseDao.getEntityById(Notification.class, notification.getId());
		tempNotification = notification;
		//add your code here
		
		
		baseDao.updateEntity(tempNotification);

	}

//	根据id查询实体类			
	@Override
	public Notification selectNotificationById(Integer notificationId){		
		return baseDao.getEntityById(Notification.class, notificationId);
	}
	
	@Override
	public List<Notification> selectNotificationAll(){
			
		return baseDao.getAllEntity(Notification.class);
		
	}
	
//	根据id查询full类
	@Override
	public NotificationFull selectNotificationFullById(Integer notificationId){
		String sql = "where a.id=" + notificationId;
		List<NotificationFull> temp = notificationDao.selectNotificationFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	

	public int countNotificationRow(){
		return baseDao.getRowCount(Notification.class);
	}
		
//  分页查询
	public List<Notification> selectNotificationByPage(Page page){	
		return baseDao.selectByPage(Notification.class,page);
	}
//  分页查询full	
	public List<NotificationFull> selectNotificationFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Notification.class));
		return notificationDao.selectNotificationFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Notification> selectNotificationByCondition(String condition) {
		 return notificationDao.selectNotificationByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
	@Override
	public List<Notification>  selectNotificationByConditionAndPage(String condition,final Page page) {
		return notificationDao.selectNotificationByConditionAndPage(condition,page);
	}
	
	
	//条件查询full
	@Override
	public List<NotificationFull> selectNotificationFullByCondition(String condition) {
		return notificationDao.selectNotificationFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<NotificationFull>  selectNotificationFullByConditionAndPage(String condition,final Page page) {
			return notificationDao.selectNotificationFullByConditionAndPage(condition, page);
		}

		@Override
		public List<NotificationFull> selectNotificationFullAll() {
			// TODO Auto-generated method stub
			return null;
		}
	


}
