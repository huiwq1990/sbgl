package com.sbgl.app.services.message;

import java.util.List;

import com.sbgl.app.entity.Notification;
import com.sbgl.app.entity.NotificationFull;
import com.sbgl.util.*;

public interface NotificationService{
	
	public void addNotification(Notification notification);

	public void addNotification(Notification ch,Notification en);
	public void addNotificationWithId(Notification notification);
		
	public void updateNotification(Notification notification);
	
//  根据id删除实体	
	public int deleteNotification(Integer notificationId);

//  根据实体删除实体
	public int deleteNotification(Notification notification);
	
//	根据id查询实体类		
	public Notification selectNotificationById(Integer notificationId);

//  查询全部实体
	public List<Notification> selectNotificationAll();
	
//	根据id查询full类
	public NotificationFull selectNotificationFullById(Integer notificationId);

//  查询全部full
	public List<NotificationFull> selectNotificationFullAll();
		
//  查询数量
	public int countNotificationRow();
//  分页查询
	public List<Notification> selectNotificationByPage(Page page);
	public List<NotificationFull> selectNotificationFullByPage(Page page);
		
		
	public List<Notification> selectNotificationByCondition(String condition);

	public List<Notification>  selectNotificationByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<NotificationFull> selectNotificationFullByCondition(String condition);
	
	
	// 查询实体full        
    public List<NotificationFull>  selectNotificationFullByConditionAndPage(String condition,final Page page);
		
	
	
	
}
