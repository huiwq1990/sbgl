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



import com.sbgl.app.entity.Messagereceiver;
import com.sbgl.app.entity.MessagereceiverFull;
import com.sbgl.app.services.message.MessagereceiverService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class MessagereceiverSpringTest {

	public static void main(String[] args) {
//		intTable();
//iniDouble();
//		addMessagereceiver();
//		selectMessagereceiverAll();
//		deleteMessagereceiver();
		
//		updateMessagereceiver();
//		selectMessagereceiverById(1L);
//		selectMessagereceiverFullById(1L);
//      selectMessagereceiverFullAll();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		MessagereceiverService messagereceiverService = (MessagereceiverService) cxt.getBean("messagereceiverService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Messagereceiver");
		
		// 删除
		List<Messagereceiver> messagereceiverList = messagereceiverService.selectMessagereceiverAll();
		if (messagereceiverList != null) {
			for (int i = 0; i < messagereceiverList.size(); i++) {
				messagereceiverService.deleteMessagereceiver(messagereceiverList.get(i).getId());
			}
		}


		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/message"+"/Messagereceiver");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				
				messagereceiverService.addMessagereceiver(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		MessagereceiverService messagereceiverService = (MessagereceiverService) cxt.getBean("messagereceiverService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Messagereceiver");
		commonService.iniCode("Messagereceivertype");
		// 删除
		List<Messagereceiver> messagereceiverList = messagereceiverService.selectMessagereceiverAll();
		if (messagereceiverList != null) {
			for (int i = 0; i < messagereceiverList.size(); i++) {
				messagereceiverService.deleteMessagereceiver(messagereceiverList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/message"+"/Messagereceiver");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Messagereceiver ch =	getObj(attrs,dataList.get(i));
				Messagereceiver en =	getObj(attrs,dataList.get(i+1));
								
				messagereceiverService.addMessagereceiver(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Messagereceiver getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Messagereceiver.class);
		
		Object obj =  Messagereceiver.class.newInstance();
		String[] datas = str.split(",");
		Method method = null;
		
											if(!datas[0].trim().equals("")){
					 method = map.get(attrs[0]);
																method.invoke(obj,Integer.valueOf(datas[0].trim()));
 										}
					
									if(!datas[1].trim().equals("")){
					 method = map.get(attrs[1]);
																method.invoke(obj,Integer.valueOf(datas[1].trim()));
 										}
					
									if(!datas[2].trim().equals("")){
					 method = map.get(attrs[2]);
																method.invoke(obj,Integer.valueOf(datas[2].trim()));
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
					
								
		return (Messagereceiver)obj;		
		
	}
	
	public static void addMessagereceiver(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MessagereceiverService messagereceiverService = (MessagereceiverService)cxt.getBean("messagereceiverService");
		
		Messagereceiver messagereceiver = new Messagereceiver() ;
		
		//messagereceiver.setCreatetime(DateUtil.currentDate());
		 		
		messagereceiverService.addMessagereceiver(messagereceiver);
	}
	
	public static void addMessagereceiverWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MessagereceiverService messagereceiverService = (MessagereceiverService)cxt.getBean("messagereceiverService");
	
		Messagereceiver messagereceiver = new Messagereceiver() ;
		 
		//messagereceiver.setCreatetime(DateUtil.currentDate());
		 		
		messagereceiverService.addMessagereceiverWithId(messagereceiver);
	}
	
	public static void deleteMessagereceiver(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		MessagereceiverService messagereceiverService =(MessagereceiverService)cxt.getBean("messagereceiverService");
		
		messagereceiverService.deleteMessagereceiver(id);

	}
	
	public static void updateMessagereceiver(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		MessagereceiverService messagereceiverService = (MessagereceiverService)cxt.getBean("messagereceiverService");
		
		Messagereceiver messagereceiver = new Messagereceiver();
		//获取一个实体，更改它
		messagereceiver = messagereceiverService.selectMessagereceiverById(0);
		//messagereceiver.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//messagereceiver.setCreatetime(DateUtil.currentDate());
		
		messagereceiverService.updateMessagereceiver(messagereceiver);
	
	}
	
	public static void selectMessagereceiverAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MessagereceiverService messagereceiverService = (MessagereceiverService)cxt.getBean("messagereceiverService");
		List<Messagereceiver> objList  = messagereceiverService.selectMessagereceiverAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectMessagereceiverById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MessagereceiverService messagereceiverService = (MessagereceiverService)cxt.getBean("messagereceiverService");
		Messagereceiver obj  = messagereceiverService.selectMessagereceiverById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectMessagereceiverFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MessagereceiverService messagereceiverService = (MessagereceiverService)cxt.getBean("messagereceiverService");
		MessagereceiverFull obj  = messagereceiverService.selectMessagereceiverFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectMessagereceiverFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MessagereceiverService messagereceiverService = (MessagereceiverService)cxt.getBean("messagereceiverService");
		List<MessagereceiverFull> objList  = messagereceiverService.selectMessagereceiverFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectMessagereceiverByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MessagereceiverService messagereceiverService = (MessagereceiverService)cxt.getBean("messagereceiverService");
		Page page = new Page(1,3);

		List<Messagereceiver> objList  =  messagereceiverService.selectMessagereceiverByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectMessagereceiverFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MessagereceiverService messagereceiverService = (MessagereceiverService)cxt.getBean("messagereceiverService");
		Page page = new Page(1,3);

		List<MessagereceiverFull> objList  =  messagereceiverService.selectMessagereceiverFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
