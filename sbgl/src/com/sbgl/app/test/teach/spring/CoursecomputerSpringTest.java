package com.sbgl.app.test.teach.spring;

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



import com.sbgl.app.entity.Coursecomputer;
import com.sbgl.app.entity.CoursecomputerFull;
import com.sbgl.app.services.teach.CoursecomputerService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class CoursecomputerSpringTest {

	public static void main(String[] args) {
//		intTable();
//iniDouble();
//		addCoursecomputer();
//		selectCoursecomputerAll();
//		deleteCoursecomputer();
		
//		updateCoursecomputer();
//		selectCoursecomputerById(1L);
//		selectCoursecomputerFullById(1L);
//      selectCoursecomputerFullAll();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		CoursecomputerService coursecomputerService = (CoursecomputerService) cxt.getBean("coursecomputerService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Coursecomputer");
		
		// 删除
		List<Coursecomputer> coursecomputerList = coursecomputerService.selectCoursecomputerAll();
		if (coursecomputerList != null) {
			for (int i = 0; i < coursecomputerList.size(); i++) {
				coursecomputerService.deleteCoursecomputer(coursecomputerList.get(i).getId());
			}
		}


		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/teach"+"/Coursecomputer");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				
				coursecomputerService.addCoursecomputer(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		CoursecomputerService coursecomputerService = (CoursecomputerService) cxt.getBean("coursecomputerService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Coursecomputer");
		commonService.iniCode("Coursecomputertype");
		// 删除
		List<Coursecomputer> coursecomputerList = coursecomputerService.selectCoursecomputerAll();
		if (coursecomputerList != null) {
			for (int i = 0; i < coursecomputerList.size(); i++) {
				coursecomputerService.deleteCoursecomputer(coursecomputerList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/teach"+"/Coursecomputer");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Coursecomputer ch =	getObj(attrs,dataList.get(i));
				Coursecomputer en =	getObj(attrs,dataList.get(i+1));
								
				coursecomputerService.addCoursecomputer(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Coursecomputer getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Coursecomputer.class);
		
		Object obj =  Coursecomputer.class.newInstance();
		String[] datas = str.split(",");
		Method method = null;
		
											if(!datas[0].trim().equals("")){
					 method = map.get(attrs[0]);
																method.invoke(obj,CoursecomputerId.valueOf(datas[0].trim()));
 										}
					
								
		return (Coursecomputer)obj;		
		
	}
	
	public static void addCoursecomputer(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CoursecomputerService coursecomputerService = (CoursecomputerService)cxt.getBean("coursecomputerService");
		
		Coursecomputer coursecomputer = new Coursecomputer() ;
		
		//coursecomputer.setCreatetime(DateUtil.currentDate());
		 		
		coursecomputerService.addCoursecomputer(coursecomputer);
	}
	
	public static void addCoursecomputerWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CoursecomputerService coursecomputerService = (CoursecomputerService)cxt.getBean("coursecomputerService");
	
		Coursecomputer coursecomputer = new Coursecomputer() ;
		 
		//coursecomputer.setCreatetime(DateUtil.currentDate());
		 		
		coursecomputerService.addCoursecomputerWithId(coursecomputer);
	}
	
	public static void deleteCoursecomputer(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		CoursecomputerService coursecomputerService =(CoursecomputerService)cxt.getBean("coursecomputerService");
		
		coursecomputerService.deleteCoursecomputer(id);

	}
	
	public static void updateCoursecomputer(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		CoursecomputerService coursecomputerService = (CoursecomputerService)cxt.getBean("coursecomputerService");
		
		Coursecomputer coursecomputer = new Coursecomputer();
		//获取一个实体，更改它
		coursecomputer = coursecomputerService.selectCoursecomputerById(0);
		//coursecomputer.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//coursecomputer.setCreatetime(DateUtil.currentDate());
		
		coursecomputerService.updateCoursecomputer(coursecomputer);
	
	}
	
	public static void selectCoursecomputerAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CoursecomputerService coursecomputerService = (CoursecomputerService)cxt.getBean("coursecomputerService");
		List<Coursecomputer> objList  = coursecomputerService.selectCoursecomputerAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectCoursecomputerById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CoursecomputerService coursecomputerService = (CoursecomputerService)cxt.getBean("coursecomputerService");
		Coursecomputer obj  = coursecomputerService.selectCoursecomputerById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectCoursecomputerFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CoursecomputerService coursecomputerService = (CoursecomputerService)cxt.getBean("coursecomputerService");
		CoursecomputerFull obj  = coursecomputerService.selectCoursecomputerFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectCoursecomputerFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CoursecomputerService coursecomputerService = (CoursecomputerService)cxt.getBean("coursecomputerService");
		List<CoursecomputerFull> objList  = coursecomputerService.selectCoursecomputerFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectCoursecomputerByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CoursecomputerService coursecomputerService = (CoursecomputerService)cxt.getBean("coursecomputerService");
		Page page = new Page(1,3);

		List<Coursecomputer> objList  =  coursecomputerService.selectCoursecomputerByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectCoursecomputerFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CoursecomputerService coursecomputerService = (CoursecomputerService)cxt.getBean("coursecomputerService");
		Page page = new Page(1,3);

		List<CoursecomputerFull> objList  =  coursecomputerService.selectCoursecomputerFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
