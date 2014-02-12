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



import com.sbgl.app.entity.Message;
import com.sbgl.app.entity.MessageFull;
import com.sbgl.app.services.message.MessageService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class MessageSpringTest {

	public static void main(String[] args) {
//		intTable();
//iniDouble();
//		addMessage();
//		selectMessageAll();
//		deleteMessage();
		
//		updateMessage();
//		selectMessageById(1L);
//		selectMessageFullById(1L);
//      selectMessageFullAll();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		MessageService messageService = (MessageService) cxt.getBean("messageService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Message");
		
		// 删除
		List<Message> messageList = messageService.selectMessageAll();
		if (messageList != null) {
			for (int i = 0; i < messageList.size(); i++) {
				messageService.deleteMessage(messageList.get(i).getId());
			}
		}


		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/message"+"/Message");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				
				messageService.addMessage(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		MessageService messageService = (MessageService) cxt.getBean("messageService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Message");
		commonService.iniCode("Messagetype");
		// 删除
		List<Message> messageList = messageService.selectMessageAll();
		if (messageList != null) {
			for (int i = 0; i < messageList.size(); i++) {
				messageService.deleteMessage(messageList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/message"+"/Message");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Message ch =	getObj(attrs,dataList.get(i));
				Message en =	getObj(attrs,dataList.get(i+1));
								
				messageService.addMessage(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Message getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Message.class);
		
		Object obj =  Message.class.newInstance();
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
																method.invoke(obj,DateUtil.parseDate(datas[4].trim()));  
										}
					
									if(!datas[5].trim().equals("")){
					 method = map.get(attrs[5]);
																method.invoke(obj,Integer.valueOf(datas[5].trim()));
 										}
					
									if(!datas[6].trim().equals("")){
					 method = map.get(attrs[6]);
																method.invoke(obj,Integer.valueOf(datas[6].trim()));
 										}
					
									if(!datas[7].trim().equals("")){
					 method = map.get(attrs[7]);
																method.invoke(obj,String.valueOf(datas[7].trim()));
 										}
					
									if(!datas[8].trim().equals("")){
					 method = map.get(attrs[8]);
																method.invoke(obj,Integer.valueOf(datas[8].trim()));
 										}
					
									if(!datas[9].trim().equals("")){
					 method = map.get(attrs[9]);
																method.invoke(obj,Integer.valueOf(datas[9].trim()));
 										}
					
									if(!datas[10].trim().equals("")){
					 method = map.get(attrs[10]);
																method.invoke(obj,Integer.valueOf(datas[10].trim()));
 										}
					
								
		return (Message)obj;		
		
	}
	
	public static void addMessage(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MessageService messageService = (MessageService)cxt.getBean("messageService");
		
		Message message = new Message() ;
		
		//message.setCreatetime(DateUtil.currentDate());
		 		
		messageService.addMessage(message);
	}
	
	public static void addMessageWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MessageService messageService = (MessageService)cxt.getBean("messageService");
	
		Message message = new Message() ;
		 
		//message.setCreatetime(DateUtil.currentDate());
		 		
		messageService.addMessageWithId(message);
	}
	
	public static void deleteMessage(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		MessageService messageService =(MessageService)cxt.getBean("messageService");
		
		messageService.deleteMessage(id);

	}
	
	public static void updateMessage(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		MessageService messageService = (MessageService)cxt.getBean("messageService");
		
		Message message = new Message();
		//获取一个实体，更改它
		message = messageService.selectMessageById(0);
		//message.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//message.setCreatetime(DateUtil.currentDate());
		
		messageService.updateMessage(message);
	
	}
	
	public static void selectMessageAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MessageService messageService = (MessageService)cxt.getBean("messageService");
		List<Message> objList  = messageService.selectMessageAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectMessageById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MessageService messageService = (MessageService)cxt.getBean("messageService");
		Message obj  = messageService.selectMessageById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectMessageFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MessageService messageService = (MessageService)cxt.getBean("messageService");
		MessageFull obj  = messageService.selectMessageFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectMessageFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MessageService messageService = (MessageService)cxt.getBean("messageService");
		List<MessageFull> objList  = messageService.selectMessageFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectMessageByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MessageService messageService = (MessageService)cxt.getBean("messageService");
		Page page = new Page(1,3);

		List<Message> objList  =  messageService.selectMessageByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectMessageFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MessageService messageService = (MessageService)cxt.getBean("messageService");
		Page page = new Page(1,3);

		List<MessageFull> objList  =  messageService.selectMessageFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
