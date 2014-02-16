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



import com.sbgl.app.entity.Course;
import com.sbgl.app.entity.CourseFull;
import com.sbgl.app.services.teach.CourseService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class CourseSpringTest {

	public static void main(String[] args) {
//		intTable();
//iniDouble();
//		addCourse();
//		selectCourseAll();
//		deleteCourse();
		
//		updateCourse();
//		selectCourseById(1L);
//		selectCourseFullById(1L);
//      selectCourseFullAll();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		CourseService courseService = (CourseService) cxt.getBean("courseService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Course");
		
		// 删除
		List<Course> courseList = courseService.selectCourseAll();
		if (courseList != null) {
			for (int i = 0; i < courseList.size(); i++) {
				courseService.deleteCourse(courseList.get(i).getId());
			}
		}


		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/teach"+"/Course");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				
				courseService.addCourse(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		CourseService courseService = (CourseService) cxt.getBean("courseService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Course");
		commonService.iniCode("Coursetype");
		// 删除
		List<Course> courseList = courseService.selectCourseAll();
		if (courseList != null) {
			for (int i = 0; i < courseList.size(); i++) {
				courseService.deleteCourse(courseList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/teach"+"/Course");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Course ch =	getObj(attrs,dataList.get(i));
				Course en =	getObj(attrs,dataList.get(i+1));
								
				courseService.addCourse(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Course getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Course.class);
		
		Object obj =  Course.class.newInstance();
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
																method.invoke(obj,Integer.valueOf(datas[5].trim()));
 										}
					
									if(!datas[6].trim().equals("")){
					 method = map.get(attrs[6]);
																method.invoke(obj,DateUtil.parseDate(datas[6].trim()));  
										}
					
								
		return (Course)obj;		
		
	}
	
	public static void addCourse(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CourseService courseService = (CourseService)cxt.getBean("courseService");
		
		Course course = new Course() ;
		
		//course.setCreatetime(DateUtil.currentDate());
		 		
		courseService.addCourse(course);
	}
	
	public static void addCourseWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CourseService courseService = (CourseService)cxt.getBean("courseService");
	
		Course course = new Course() ;
		 
		//course.setCreatetime(DateUtil.currentDate());
		 		
		courseService.addCourseWithId(course);
	}
	
	public static void deleteCourse(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		CourseService courseService =(CourseService)cxt.getBean("courseService");
		
		courseService.deleteCourse(id);

	}
	
	public static void updateCourse(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		CourseService courseService = (CourseService)cxt.getBean("courseService");
		
		Course course = new Course();
		//获取一个实体，更改它
		course = courseService.selectCourseById(0);
		//course.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//course.setCreatetime(DateUtil.currentDate());
		
		courseService.updateCourse(course);
	
	}
	
	public static void selectCourseAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CourseService courseService = (CourseService)cxt.getBean("courseService");
		List<Course> objList  = courseService.selectCourseAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectCourseById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CourseService courseService = (CourseService)cxt.getBean("courseService");
		Course obj  = courseService.selectCourseById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectCourseFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CourseService courseService = (CourseService)cxt.getBean("courseService");
		CourseFull obj  = courseService.selectCourseFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectCourseFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CourseService courseService = (CourseService)cxt.getBean("courseService");
		List<CourseFull> objList  = courseService.selectCourseFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectCourseByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CourseService courseService = (CourseService)cxt.getBean("courseService");
		Page page = new Page(1,3);

		List<Course> objList  =  courseService.selectCourseByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectCourseFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CourseService courseService = (CourseService)cxt.getBean("courseService");
		Page page = new Page(1,3);

		List<CourseFull> objList  =  courseService.selectCourseFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
