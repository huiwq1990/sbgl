package com.sbgl.app.test.message.spring;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;



import com.sbgl.app.entity.Notification;
import com.sbgl.app.entity.NotificationFull;
import com.sbgl.app.services.message.NotificationService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class NotificationSpringTest {

	public static void main(String[] args) {
//		intTable();
//iniDouble();
//		addNotification();
//		selectNotificationAll();
//		deleteNotification();
		
//		updateNotification();
//		selectNotificationById(1L);
//		selectNotificationFullById(1L);
//      selectNotificationFullAll();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		NotificationService notificationService = (NotificationService) cxt.getBean("notificationService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Notification");
		
		// 删除
		List<Notification> notificationList = notificationService.selectNotificationAll();
		if (notificationList != null) {
			for (int i = 0; i < notificationList.size(); i++) {
				notificationService.deleteNotification(notificationList.get(i).getId());
			}
		}


		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/message"+"/Notification");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				
				notificationService.addNotification(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		NotificationService notificationService = (NotificationService) cxt.getBean("notificationService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Notification");
		commonService.iniCode("Notificationtype");
		// 删除
		List<Notification> notificationList = notificationService.selectNotificationAll();
		if (notificationList != null) {
			for (int i = 0; i < notificationList.size(); i++) {
				notificationService.deleteNotification(notificationList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/message"+"/Notification");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Notification ch =	getObj(attrs,dataList.get(i));
				Notification en =	getObj(attrs,dataList.get(i+1));
								
				notificationService.addNotification(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Notification getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Notification.class);
		
		Object obj =  Notification.class.newInstance();
		String[] datas = str.split(",");
		Method method = null;
		
											if(!datas[0].trim().equals("")){
					 method = map.get(attrs[0]);
																method.invoke(obj,Integer.valueOf(datas[0].trim()));
 										}
					
									if(!datas[1].trim().equals("")){
					 method = map.get(attrs[1]);
																method.invoke(obj,String.valueOf(datas[1].trim()));
 										}
					
									if(!datas[2].trim().equals("")){
					 method = map.get(attrs[2]);
																method.invoke(obj,String.valueOf(datas[2].trim()));
 										}
					
									if(!datas[3].trim().equals("")){
					 method = map.get(attrs[3]);
																method.invoke(obj,Integer.valueOf(datas[3].trim()));
 										}
					
									if(!datas[4].trim().equals("")){
					 method = map.get(attrs[4]);
																method.invoke(obj,Integer.valueOf(datas[4].trim()));
 										}
					
									if(!datas[5].trim().equals("")){
					 method = map.get(attrs[5]);
																method.invoke(obj,DateUtil.parseDate(datas[5].trim()));  
										}
					
									if(!datas[6].trim().equals("")){
					 method = map.get(attrs[6]);
																method.invoke(obj,Integer.valueOf(datas[6].trim()));
 										}
					
									if(!datas[7].trim().equals("")){
					 method = map.get(attrs[7]);
																method.invoke(obj,Integer.valueOf(datas[7].trim()));
 										}
					
									if(!datas[8].trim().equals("")){
					 method = map.get(attrs[8]);
																method.invoke(obj,Integer.valueOf(datas[8].trim()));
 										}
					
								
		return (Notification)obj;		
		
	}
	
	public static void addNotification(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		NotificationService notificationService = (NotificationService)cxt.getBean("notificationService");
		
		Notification notification = new Notification() ;
		
		//notification.setCreatetime(DateUtil.currentDate());
		 		
		notificationService.addNotification(notification);
	}
	
	public static void addNotificationWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		NotificationService notificationService = (NotificationService)cxt.getBean("notificationService");
	
		Notification notification = new Notification() ;
		 
		//notification.setCreatetime(DateUtil.currentDate());
		 		
		notificationService.addNotificationWithId(notification);
	}
	
	public static void deleteNotification(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		NotificationService notificationService =(NotificationService)cxt.getBean("notificationService");
		
		notificationService.deleteNotification(id);

	}
	
	public static void updateNotification(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		NotificationService notificationService = (NotificationService)cxt.getBean("notificationService");
		
		Notification notification = new Notification();
		//获取一个实体，更改它
		notification = notificationService.selectNotificationById(0);
		//notification.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//notification.setCreatetime(DateUtil.currentDate());
		
		notificationService.updateNotification(notification);
	
	}
	
	public static void selectNotificationAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		NotificationService notificationService = (NotificationService)cxt.getBean("notificationService");
		List<Notification> objList  = notificationService.selectNotificationAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectNotificationById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		NotificationService notificationService = (NotificationService)cxt.getBean("notificationService");
		Notification obj  = notificationService.selectNotificationById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectNotificationFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		NotificationService notificationService = (NotificationService)cxt.getBean("notificationService");
		NotificationFull obj  = notificationService.selectNotificationFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectNotificationFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		NotificationService notificationService = (NotificationService)cxt.getBean("notificationService");
		List<NotificationFull> objList  = notificationService.selectNotificationFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectNotificationByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		NotificationService notificationService = (NotificationService)cxt.getBean("notificationService");
		Page page = new Page(1,3);

		List<Notification> objList  =  notificationService.selectNotificationByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectNotificationFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		NotificationService notificationService = (NotificationService)cxt.getBean("notificationService");
		Page page = new Page(1,3);

		List<NotificationFull> objList  =  notificationService.selectNotificationFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
