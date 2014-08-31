//package com.sbgl.app.test.teach.spring;
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
//import com.sbgl.app.entity.Courseschedulecomputerorder;
//import com.sbgl.app.entity.CourseschedulecomputerorderFull;
//import com.sbgl.app.services.teach.CourseschedulecomputerorderService;
//import com.sbgl.util.*;
//import com.sbgl.app.services.common.*;
//
//public class CourseschedulecomputerorderSpringTest {
//
//	public static void main(String[] args) {
////		intTable();
////iniDouble();
////		addCourseschedulecomputerorder();
////		selectCourseschedulecomputerorderAll();
////		deleteCourseschedulecomputerorder();
//		
////		updateCourseschedulecomputerorder();
////		selectCourseschedulecomputerorderById(1L);
////		selectCourseschedulecomputerorderFullById(1L);
////      selectCourseschedulecomputerorderFullAll();
//
//	}
//	
//	
////初始化数据库 将数据库中数据删除 添加新的数据
//	public static void intTable() {
//		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//
//		CourseschedulecomputerorderService courseschedulecomputerorderService = (CourseschedulecomputerorderService) cxt.getBean("courseschedulecomputerorderService");
//		CommonService commonService = (CommonService) cxt.getBean("commonService");
//		// 将计数器置1
//		commonService.iniCode("Courseschedulecomputerorder");
//		
//		// 删除
//		List<Courseschedulecomputerorder> courseschedulecomputerorderList = courseschedulecomputerorderService.selectCourseschedulecomputerorderAll();
//		if (courseschedulecomputerorderList != null) {
//			for (int i = 0; i < courseschedulecomputerorderList.size(); i++) {
//				courseschedulecomputerorderService.deleteCourseschedulecomputerorder(courseschedulecomputerorderList.get(i).getId());
//			}
//		}
//
//
//		try {
//			
//			List<String> dataList = new ArrayList<String>();
//			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/teach"+"/Courseschedulecomputerorder");
//			dataList = FileUtils.readLines(f);
//			String[] attrs = dataList.get(0).split(",");
//			for(int i=1; i < dataList.size();i++){
//				
//				courseschedulecomputerorderService.addCourseschedulecomputerorder(getObj(attrs,dataList.get(i)));
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
//		CourseschedulecomputerorderService courseschedulecomputerorderService = (CourseschedulecomputerorderService) cxt.getBean("courseschedulecomputerorderService");
//		CommonService commonService = (CommonService) cxt.getBean("commonService");
//		// 将计数器置1
//		commonService.iniCode("Courseschedulecomputerorder");
//		commonService.iniCode("Courseschedulecomputerordertype");
//		// 删除
//		List<Courseschedulecomputerorder> courseschedulecomputerorderList = courseschedulecomputerorderService.selectCourseschedulecomputerorderAll();
//		if (courseschedulecomputerorderList != null) {
//			for (int i = 0; i < courseschedulecomputerorderList.size(); i++) {
//				courseschedulecomputerorderService.deleteCourseschedulecomputerorder(courseschedulecomputerorderList.get(i).getId());
//			}
//		}
//		
//		
//		try {
//			
//			List<String> dataList = new ArrayList<String>();
//			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/teach"+"/Courseschedulecomputerorder");
//			dataList = FileUtils.readLines(f);
//			String[] attrs = dataList.get(0).split(",");
//			for(int i=1; i < dataList.size();i=i+2){
//				
//				Courseschedulecomputerorder ch =	getObj(attrs,dataList.get(i));
//				Courseschedulecomputerorder en =	getObj(attrs,dataList.get(i+1));
//								
//				courseschedulecomputerorderService.addCourseschedulecomputerorder(ch,en);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//	}
//	
//	public static Courseschedulecomputerorder getObj(String[] attrs,String str) throws Exception{
//		HashMap<String, Method> map = ReflectUtil.ConverBean(Courseschedulecomputerorder.class);
//		
//		Object obj =  Courseschedulecomputerorder.class.newInstance();
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
//																method.invoke(obj,Integer.valueOf(datas[3].trim()));
// 										}
//					
//								
//		return (Courseschedulecomputerorder)obj;		
//		
//	}
//	
//	public static void addCourseschedulecomputerorder(){
//
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		CourseschedulecomputerorderService courseschedulecomputerorderService = (CourseschedulecomputerorderService)cxt.getBean("courseschedulecomputerorderService");
//		
//		Courseschedulecomputerorder courseschedulecomputerorder = new Courseschedulecomputerorder() ;
//		
//		//courseschedulecomputerorder.setCreatetime(DateUtil.currentDate());
//		 		
//		courseschedulecomputerorderService.addCourseschedulecomputerorder(courseschedulecomputerorder);
//	}
//	
//	public static void addCourseschedulecomputerorderWithId(){
//
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		CourseschedulecomputerorderService courseschedulecomputerorderService = (CourseschedulecomputerorderService)cxt.getBean("courseschedulecomputerorderService");
//	
//		Courseschedulecomputerorder courseschedulecomputerorder = new Courseschedulecomputerorder() ;
//		 
//		//courseschedulecomputerorder.setCreatetime(DateUtil.currentDate());
//		 		
//		courseschedulecomputerorderService.addCourseschedulecomputerorderWithId(courseschedulecomputerorder);
//	}
//	
//	public static void deleteCourseschedulecomputerorder(Integer id){
//
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		CourseschedulecomputerorderService courseschedulecomputerorderService =(CourseschedulecomputerorderService)cxt.getBean("courseschedulecomputerorderService");
//		
//		courseschedulecomputerorderService.deleteCourseschedulecomputerorder(id);
//
//	}
//	
//	public static void updateCourseschedulecomputerorder(){
//
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		CourseschedulecomputerorderService courseschedulecomputerorderService = (CourseschedulecomputerorderService)cxt.getBean("courseschedulecomputerorderService");
//		
//		Courseschedulecomputerorder courseschedulecomputerorder = new Courseschedulecomputerorder();
//		//获取一个实体，更改它
//		courseschedulecomputerorder = courseschedulecomputerorderService.selectCourseschedulecomputerorderById(0);
//		//courseschedulecomputerorder.setName("tq");
//		//computer.setCreatetime(DateUtil.currentDate());
//		//courseschedulecomputerorder.setCreatetime(DateUtil.currentDate());
//		
//		courseschedulecomputerorderService.updateCourseschedulecomputerorder(courseschedulecomputerorder);
//	
//	}
//	
//	public static void selectCourseschedulecomputerorderAll(){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		CourseschedulecomputerorderService courseschedulecomputerorderService = (CourseschedulecomputerorderService)cxt.getBean("courseschedulecomputerorderService");
//		List<Courseschedulecomputerorder> objList  = courseschedulecomputerorderService.selectCourseschedulecomputerorderAll();
//		for(int i = 0; i < objList.size(); i++){
//			System.out.println("id="+objList.get(i).getId());
//		}
//	}
//	public static void selectCourseschedulecomputerorderById(Integer id){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		CourseschedulecomputerorderService courseschedulecomputerorderService = (CourseschedulecomputerorderService)cxt.getBean("courseschedulecomputerorderService");
//		Courseschedulecomputerorder obj  = courseschedulecomputerorderService.selectCourseschedulecomputerorderById(id);
//		
//		System.out.println("id="+obj.getId());
//		
//	}
//	
//	public static void selectCourseschedulecomputerorderFullById(Integer id){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		CourseschedulecomputerorderService courseschedulecomputerorderService = (CourseschedulecomputerorderService)cxt.getBean("courseschedulecomputerorderService");
//		CourseschedulecomputerorderFull obj  = courseschedulecomputerorderService.selectCourseschedulecomputerorderFullById(id);
//		if(obj == null){
//			System.out.println("obj is null");
//			return;
//		}
//
////		System.out.println("id="+obj.getId());
//
//	}
//	
//	public static void selectCourseschedulecomputerorderFullAll(){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		CourseschedulecomputerorderService courseschedulecomputerorderService = (CourseschedulecomputerorderService)cxt.getBean("courseschedulecomputerorderService");
//		List<CourseschedulecomputerorderFull> objList  = courseschedulecomputerorderService.selectCourseschedulecomputerorderFullAll();
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
//		public static void selectCourseschedulecomputerorderByPage(){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		CourseschedulecomputerorderService courseschedulecomputerorderService = (CourseschedulecomputerorderService)cxt.getBean("courseschedulecomputerorderService");
//		Page page = new Page(1,3);
//
//		List<Courseschedulecomputerorder> objList  =  courseschedulecomputerorderService.selectCourseschedulecomputerorderByPage(page);
//		for(int i = 0; i < objList.size(); i++){
//			System.out.println("id="+objList.get(i).getId());
//		}
//	}
//	
//	//	分页查询	
//		public static void selectCourseschedulecomputerorderFullByPage(){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		CourseschedulecomputerorderService courseschedulecomputerorderService = (CourseschedulecomputerorderService)cxt.getBean("courseschedulecomputerorderService");
//		Page page = new Page(1,3);
//
//		List<CourseschedulecomputerorderFull> objList  =  courseschedulecomputerorderService.selectCourseschedulecomputerorderFullByPage(page);
//		for(int i = 0; i < objList.size(); i++){
//			//System.out.println("id="+objList.get(i).getId());
//		}
//	}
//}
