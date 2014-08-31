//package com.sbgl.app.test.message.spring;
//
//import java.io.File;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang.time.DateUtils;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//
//
//
//import com.sbgl.app.entity.Msgsend;
//import com.sbgl.app.entity.MsgsendFull;
//import com.sbgl.app.services.message.MsgsendService;
//import com.sbgl.util.*;
//import com.sbgl.app.services.common.*;
//
//public class MsgsendSpringTest {
//
//	public static void main(String[] args) {
////		intTable();
////iniDouble();
////		addMsgsend();
////		selectMsgsendAll();
////		deleteMsgsend();
//		
////		updateMsgsend();
////		selectMsgsendById(1L);
////		selectMsgsendFullById(1L);
////      selectMsgsendFullAll();
//
//	}
//	
//	
////初始化数据库 将数据库中数据删除 添加新的数据
//	public static void intTable() {
//		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//
//		MsgsendService msgsendService = (MsgsendService) cxt.getBean("msgsendService");
//		CommonService commonService = (CommonService) cxt.getBean("commonService");
//		// 将计数器置1
//		commonService.iniCode("Msgsend");
//		
//		// 删除
//		List<Msgsend> msgsendList = msgsendService.selectMsgsendAll();
//		if (msgsendList != null) {
//			for (int i = 0; i < msgsendList.size(); i++) {
//				msgsendService.deleteMsgsend(msgsendList.get(i).getId());
//			}
//		}
//
//
//		try {
//			
//			List<String> dataList = new ArrayList<String>();
//			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/message"+"/Msgsend");
//			dataList = FileUtils.readLines(f);
//			String[] attrs = dataList.get(0).split(",");
//			for(int i=1; i < dataList.size();i++){
//				
//				msgsendService.addMsgsend(getObj(attrs,dataList.get(i)));
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	
//	public static void iniDouble(){
//		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//
//		MsgsendService msgsendService = (MsgsendService) cxt.getBean("msgsendService");
//		CommonService commonService = (CommonService) cxt.getBean("commonService");
//		// 将计数器置1
//		commonService.iniCode("Msgsend");
//		commonService.iniCode("Msgsendtype");
//		// 删除
//		List<Msgsend> msgsendList = msgsendService.selectMsgsendAll();
//		if (msgsendList != null) {
//			for (int i = 0; i < msgsendList.size(); i++) {
//				msgsendService.deleteMsgsend(msgsendList.get(i).getId());
//			}
//		}
//		
//		
//		try {
//			
//			List<String> dataList = new ArrayList<String>();
//			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/message"+"/Msgsend");
//			dataList = FileUtils.readLines(f);
//			String[] attrs = dataList.get(0).split(",");
//			for(int i=1; i < dataList.size();i=i+2){
//				
//				Msgsend ch =	getObj(attrs,dataList.get(i));
//				Msgsend en =	getObj(attrs,dataList.get(i+1));
//								
//				msgsendService.addMsgsend(ch,en);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//	}
//	
//	public static Msgsend getObj(String[] attrs,String str) throws Exception{
//		HashMap<String, Method> map = ReflectUtil.ConverBean(Msgsend.class);
//		
//		Object obj =  Msgsend.class.newInstance();
//		String[] datas = str.split(",");
//		Method method = null;
//		
//											if(!datas[0].trim().equals("")){
//					 method = map.get(attrs[0]);
//																method.invoke(obj,Integer.valueOf(datas[0].trim()));
// 										}
//					
//									if(!datas[1].trim().equals("")){
//					 method = map.get(attrs[1]);
//																method.invoke(obj,Integer.valueOf(datas[1].trim()));
// 										}
//					
//									if(!datas[2].trim().equals("")){
//					 method = map.get(attrs[2]);
//																method.invoke(obj,Integer.valueOf(datas[2].trim()));
// 										}
//					
//									if(!datas[3].trim().equals("")){
//					 method = map.get(attrs[3]);
//																method.invoke(obj,String.valueOf(datas[3].trim()));
// 										}
//					
//									if(!datas[4].trim().equals("")){
//					 method = map.get(attrs[4]);
//																method.invoke(obj,String.valueOf(datas[4].trim()));
// 										}
//					
//									if(!datas[5].trim().equals("")){
//					 method = map.get(attrs[5]);
//																method.invoke(obj,Integer.valueOf(datas[5].trim()));
// 										}
//					
//									if(!datas[6].trim().equals("")){
//					 method = map.get(attrs[6]);
//																method.invoke(obj,DateUtil.parseDate(datas[6].trim()));  
//										}
//					
//									if(!datas[7].trim().equals("")){
//					 method = map.get(attrs[7]);
//																method.invoke(obj,DateUtil.parseDate(datas[7].trim()));  
//										}
//					
//									if(!datas[8].trim().equals("")){
//					 method = map.get(attrs[8]);
//																method.invoke(obj,Integer.valueOf(datas[8].trim()));
// 										}
//					
//								
//		return (Msgsend)obj;		
//		
//	}
//	
//	public static void addMsgsend(){
//
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		MsgsendService msgsendService = (MsgsendService)cxt.getBean("msgsendService");
//		
//		Msgsend msgsend = new Msgsend() ;
//		
//		//msgsend.setCreatetime(DateUtil.currentDate());
//		 		
//		msgsendService.addMsgsend(msgsend);
//	}
//	
//	public static void addMsgsendWithId(){
//
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		MsgsendService msgsendService = (MsgsendService)cxt.getBean("msgsendService");
//	
//		Msgsend msgsend = new Msgsend() ;
//		 
//		//msgsend.setCreatetime(DateUtil.currentDate());
//		 		
//		msgsendService.addMsgsendWithId(msgsend);
//	}
//	
//	public static void deleteMsgsend(Integer id){
//
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		MsgsendService msgsendService =(MsgsendService)cxt.getBean("msgsendService");
//		
//		msgsendService.deleteMsgsend(id);
//
//	}
//	
//	public static void updateMsgsend(){
//
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		MsgsendService msgsendService = (MsgsendService)cxt.getBean("msgsendService");
//		
//		Msgsend msgsend = new Msgsend();
//		//获取一个实体，更改它
//		msgsend = msgsendService.selectMsgsendById(0);
//		//msgsend.setName("tq");
//		//computer.setCreatetime(DateUtil.currentDate());
//		//msgsend.setCreatetime(DateUtil.currentDate());
//		
//		msgsendService.updateMsgsend(msgsend);
//	
//	}
//	
//	public static void selectMsgsendAll(){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		MsgsendService msgsendService = (MsgsendService)cxt.getBean("msgsendService");
//		List<Msgsend> objList  = msgsendService.selectMsgsendAll();
//		for(int i = 0; i < objList.size(); i++){
//			System.out.println("id="+objList.get(i).getId());
//		}
//	}
//	public static void selectMsgsendById(Integer id){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		MsgsendService msgsendService = (MsgsendService)cxt.getBean("msgsendService");
//		Msgsend obj  = msgsendService.selectMsgsendById(id);
//		
//		System.out.println("id="+obj.getId());
//		
//	}
//	
//	public static void selectMsgsendFullById(Integer id){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		MsgsendService msgsendService = (MsgsendService)cxt.getBean("msgsendService");
//		MsgsendFull obj  = msgsendService.selectMsgsendFullById(id);
//		if(obj == null){
//			System.out.println("obj is null");
//			return;
//		}
//
////		System.out.println("id="+obj.getId());
//
//	}
//	
//	public static void selectMsgsendFullAll(){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		MsgsendService msgsendService = (MsgsendService)cxt.getBean("msgsendService");
//		List<MsgsendFull> objList  = msgsendService.selectMsgsendFullAll();
//		if(objList == null){
//			System.out.println("objList is null");
//			return;
//		}
//		for(int i = 0; i < objList.size(); i++){
//	//		System.out.println("id="+objList.get(i).getId());
//		}
//	}
//
////	分页查询	
//		public static void selectMsgsendByPage(){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		MsgsendService msgsendService = (MsgsendService)cxt.getBean("msgsendService");
//		Page page = new Page(1,3);
//
//		List<Msgsend> objList  =  msgsendService.selectMsgsendByPage(page);
//		for(int i = 0; i < objList.size(); i++){
//			System.out.println("id="+objList.get(i).getId());
//		}
//	}
//	
//	//	分页查询	
//		public static void selectMsgsendFullByPage(){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		MsgsendService msgsendService = (MsgsendService)cxt.getBean("msgsendService");
//		Page page = new Page(1,3);
//
//		List<MsgsendFull> objList  =  msgsendService.selectMsgsendFullByPage(page);
//		for(int i = 0; i < objList.size(); i++){
//			//System.out.println("id="+objList.get(i).getId());
//		}
//	}
//}
