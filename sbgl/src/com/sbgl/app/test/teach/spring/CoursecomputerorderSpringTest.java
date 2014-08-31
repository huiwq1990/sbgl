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
//import com.sbgl.app.entity.Coursecomputerorder;
//import com.sbgl.app.entity.CoursecomputerorderFull;
//import com.sbgl.app.services.teach.CoursecomputerorderService;
//import com.sbgl.util.*;
//import com.sbgl.app.services.common.*;
//
//public class CoursecomputerorderSpringTest {
//
//	public static void main(String[] args) {
////		intTable();
////iniDouble();
////		addCoursecomputerorder();
////		selectCoursecomputerorderAll();
////		deleteCoursecomputerorder();
//		
////		updateCoursecomputerorder();
////		selectCoursecomputerorderById(1L);
////		selectCoursecomputerorderFullById(1L);
////      selectCoursecomputerorderFullAll();
//
//	}
//	
//	
////初始化数据库 将数据库中数据删除 添加新的数据
//	public static void intTable() {
//		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//
//		CoursecomputerorderService coursecomputerorderService = (CoursecomputerorderService) cxt.getBean("coursecomputerorderService");
//		CommonService commonService = (CommonService) cxt.getBean("commonService");
//		// 将计数器置1
//		commonService.iniCode("Coursecomputerorder");
//		
//		// 删除
//		List<Coursecomputerorder> coursecomputerorderList = coursecomputerorderService.selectCoursecomputerorderAll();
//		if (coursecomputerorderList != null) {
//			for (int i = 0; i < coursecomputerorderList.size(); i++) {
//				coursecomputerorderService.deleteCoursecomputerorder(coursecomputerorderList.get(i).getId());
//			}
//		}
//
//
//		try {
//			
//			List<String> dataList = new ArrayList<String>();
//			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/teach"+"/Coursecomputerorder");
//			dataList = FileUtils.readLines(f);
//			String[] attrs = dataList.get(0).split(",");
//			for(int i=1; i < dataList.size();i++){
//				
//				coursecomputerorderService.addCoursecomputerorder(getObj(attrs,dataList.get(i)));
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
//		CoursecomputerorderService coursecomputerorderService = (CoursecomputerorderService) cxt.getBean("coursecomputerorderService");
//		CommonService commonService = (CommonService) cxt.getBean("commonService");
//		// 将计数器置1
//		commonService.iniCode("Coursecomputerorder");
//		commonService.iniCode("Coursecomputerordertype");
//		// 删除
//		List<Coursecomputerorder> coursecomputerorderList = coursecomputerorderService.selectCoursecomputerorderAll();
//		if (coursecomputerorderList != null) {
//			for (int i = 0; i < coursecomputerorderList.size(); i++) {
//				coursecomputerorderService.deleteCoursecomputerorder(coursecomputerorderList.get(i).getId());
//			}
//		}
//		
//		
//		try {
//			
//			List<String> dataList = new ArrayList<String>();
//			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/teach"+"/Coursecomputerorder");
//			dataList = FileUtils.readLines(f);
//			String[] attrs = dataList.get(0).split(",");
//			for(int i=1; i < dataList.size();i=i+2){
//				
//				Coursecomputerorder ch =	getObj(attrs,dataList.get(i));
//				Coursecomputerorder en =	getObj(attrs,dataList.get(i+1));
//								
//				coursecomputerorderService.addCoursecomputerorder(ch,en);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//	}
//	
//	public static Coursecomputerorder getObj(String[] attrs,String str) throws Exception{
//		HashMap<String, Method> map = ReflectUtil.ConverBean(Coursecomputerorder.class);
//		
//		Object obj =  Coursecomputerorder.class.newInstance();
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
//									if(!datas[4].trim().equals("")){
//					 method = map.get(attrs[4]);
//																method.invoke(obj,Integer.valueOf(datas[4].trim()));
// 										}
//					
//								
//		return (Coursecomputerorder)obj;		
//		
//	}
//	
//	public static void addCoursecomputerorder(){
//
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		CoursecomputerorderService coursecomputerorderService = (CoursecomputerorderService)cxt.getBean("coursecomputerorderService");
//		
//		Coursecomputerorder coursecomputerorder = new Coursecomputerorder() ;
//		
//		//coursecomputerorder.setCreatetime(DateUtil.currentDate());
//		 		
//		coursecomputerorderService.addCoursecomputerorder(coursecomputerorder);
//	}
//	
//	public static void addCoursecomputerorderWithId(){
//
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		CoursecomputerorderService coursecomputerorderService = (CoursecomputerorderService)cxt.getBean("coursecomputerorderService");
//	
//		Coursecomputerorder coursecomputerorder = new Coursecomputerorder() ;
//		 
//		//coursecomputerorder.setCreatetime(DateUtil.currentDate());
//		 		
//		coursecomputerorderService.addCoursecomputerorderWithId(coursecomputerorder);
//	}
//	
//	public static void deleteCoursecomputerorder(Integer id){
//
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		CoursecomputerorderService coursecomputerorderService =(CoursecomputerorderService)cxt.getBean("coursecomputerorderService");
//		
//		coursecomputerorderService.deleteCoursecomputerorder(id);
//
//	}
//	
//	public static void updateCoursecomputerorder(){
//
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		CoursecomputerorderService coursecomputerorderService = (CoursecomputerorderService)cxt.getBean("coursecomputerorderService");
//		
//		Coursecomputerorder coursecomputerorder = new Coursecomputerorder();
//		//获取一个实体，更改它
//		coursecomputerorder = coursecomputerorderService.selectCoursecomputerorderById(0);
//		//coursecomputerorder.setName("tq");
//		//computer.setCreatetime(DateUtil.currentDate());
//		//coursecomputerorder.setCreatetime(DateUtil.currentDate());
//		
//		coursecomputerorderService.updateCoursecomputerorder(coursecomputerorder);
//	
//	}
//	
//	public static void selectCoursecomputerorderAll(){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		CoursecomputerorderService coursecomputerorderService = (CoursecomputerorderService)cxt.getBean("coursecomputerorderService");
//		List<Coursecomputerorder> objList  = coursecomputerorderService.selectCoursecomputerorderAll();
//		for(int i = 0; i < objList.size(); i++){
//			System.out.println("id="+objList.get(i).getId());
//		}
//	}
//	public static void selectCoursecomputerorderById(Integer id){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		CoursecomputerorderService coursecomputerorderService = (CoursecomputerorderService)cxt.getBean("coursecomputerorderService");
//		Coursecomputerorder obj  = coursecomputerorderService.selectCoursecomputerorderById(id);
//		
//		System.out.println("id="+obj.getId());
//		
//	}
//	
//	public static void selectCoursecomputerorderFullById(Integer id){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		CoursecomputerorderService coursecomputerorderService = (CoursecomputerorderService)cxt.getBean("coursecomputerorderService");
//		CoursecomputerorderFull obj  = coursecomputerorderService.selectCoursecomputerorderFullById(id);
//		if(obj == null){
//			System.out.println("obj is null");
//			return;
//		}
//
////		System.out.println("id="+obj.getId());
//
//	}
//	
//	public static void selectCoursecomputerorderFullAll(){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		CoursecomputerorderService coursecomputerorderService = (CoursecomputerorderService)cxt.getBean("coursecomputerorderService");
//		List<CoursecomputerorderFull> objList  = coursecomputerorderService.selectCoursecomputerorderFullAll();
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
//		public static void selectCoursecomputerorderByPage(){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		CoursecomputerorderService coursecomputerorderService = (CoursecomputerorderService)cxt.getBean("coursecomputerorderService");
//		Page page = new Page(1,3);
//
//		List<Coursecomputerorder> objList  =  coursecomputerorderService.selectCoursecomputerorderByPage(page);
//		for(int i = 0; i < objList.size(); i++){
//			System.out.println("id="+objList.get(i).getId());
//		}
//	}
//	
//	//	分页查询	
//		public static void selectCoursecomputerorderFullByPage(){
//		
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		
//		CoursecomputerorderService coursecomputerorderService = (CoursecomputerorderService)cxt.getBean("coursecomputerorderService");
//		Page page = new Page(1,3);
//
//		List<CoursecomputerorderFull> objList  =  coursecomputerorderService.selectCoursecomputerorderFullByPage(page);
//		for(int i = 0; i < objList.size(); i++){
//			//System.out.println("id="+objList.get(i).getId());
//		}
//	}
//}
