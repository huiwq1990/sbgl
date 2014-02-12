package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Notification;
import com.sbgl.app.entity.NotificationFull;
import com.sbgl.util.*;

public interface NotificationDao{

	
//删除实体
	public int deleteEntity(Integer notificationId);

public List<Notification> selectNotificationByCondition(String condition);
	 public List<Notification>  selectNotificationByConditionAndPage(String conditionSql,final Page page) ;

		public List<NotificationFull> selectNotificationFullByCondition(String condition);
			 public List<NotificationFull>  selectNotificationFullByConditionAndPage(String conditionSql,final Page page);
	
	public NotificationFull selectNotificationFullById(Integer notificationId);
		
	public List<NotificationFull> selectNotificationFullAll();
		
//  分页查询 实体full
	public List<NotificationFull> selectNotificationFullByPage(Page page);




 
}