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



import com.sbgl.app.entity.Courseconfig;
import com.sbgl.app.entity.CourseconfigFull;
import com.sbgl.app.services.teach.CourseconfigService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class CourseconfigSpringTest {

	public static void main(String[] args) {
//		intTable();
//iniDouble();
//		addCourseconfig();
//		selectCourseconfigAll();
//		deleteCourseconfig();
		
//		updateCourseconfig();
//		selectCourseconfigById(1L);
//		selectCourseconfigFullById(1L);
//      selectCourseconfigFullAll();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		CourseconfigService courseconfigService = (CourseconfigService) cxt.getBean("courseconfigService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Courseconfig");
		
		// 删除
		List<Courseconfig> courseconfigList = courseconfigService.selectCourseconfigAll();
		if (courseconfigList != null) {
			for (int i = 0; i < courseconfigList.size(); i++) {
				courseconfigService.deleteCourseconfig(courseconfigList.get(i).getId());
			}
		}


		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/teach"+"/Courseconfig");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				
				courseconfigService.addCourseconfig(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		CourseconfigService courseconfigService = (CourseconfigService) cxt.getBean("courseconfigService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Courseconfig");
		commonService.iniCode("Courseconfigtype");
		// 删除
		List<Courseconfig> courseconfigList = courseconfigService.selectCourseconfigAll();
		if (courseconfigList != null) {
			for (int i = 0; i < courseconfigList.size(); i++) {
				courseconfigService.deleteCourseconfig(courseconfigList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/teach"+"/Courseconfig");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Courseconfig ch =	getObj(attrs,dataList.get(i));
				Courseconfig en =	getObj(attrs,dataList.get(i+1));
								
				courseconfigService.addCourseconfig(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Courseconfig getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Courseconfig.class);
		
		Object obj =  Courseconfig.class.newInstance();
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
																method.invoke(obj,Integer.valueOf(datas[2].trim()));
 										}
					
									if(!datas[3].trim().equals("")){
					 method = map.get(attrs[3]);
																method.invoke(obj,DateUtil.parseDate(datas[3].trim()));  
										}
					
									if(!datas[4].trim().equals("")){
					 method = map.get(attrs[4]);
																method.invoke(obj,DateUtil.parseDate(datas[4].trim()));  
										}
					
									if(!datas[5].trim().equals("")){
					 method = map.get(attrs[5]);
																method.invoke(obj,DateUtil.parseDate(datas[5].trim()));  
										}
					
									if(!datas[6].trim().equals("")){
					 method = map.get(attrs[6]);
																method.invoke(obj,Integer.valueOf(datas[6].trim()));
 										}
					
								
		return (Courseconfig)obj;		
		
	}
	
	public static void addCourseconfig(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CourseconfigService courseconfigService = (CourseconfigService)cxt.getBean("courseconfigService");
		
		Courseconfig courseconfig = new Courseconfig() ;
		
		//courseconfig.setCreatetime(DateUtil.currentDate());
		 		
		courseconfigService.addCourseconfig(courseconfig);
	}
	
	public static void addCourseconfigWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CourseconfigService courseconfigService = (CourseconfigService)cxt.getBean("courseconfigService");
	
		Courseconfig courseconfig = new Courseconfig() ;
		 
		//courseconfig.setCreatetime(DateUtil.currentDate());
		 		
		courseconfigService.addCourseconfigWithId(courseconfig);
	}
	
	public static void deleteCourseconfig(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		CourseconfigService courseconfigService =(CourseconfigService)cxt.getBean("courseconfigService");
		
		courseconfigService.deleteCourseconfig(id);

	}
	
	public static void updateCourseconfig(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		CourseconfigService courseconfigService = (CourseconfigService)cxt.getBean("courseconfigService");
		
		Courseconfig courseconfig = new Courseconfig();
		//获取一个实体，更改它
		courseconfig = courseconfigService.selectCourseconfigById(0);
		//courseconfig.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//courseconfig.setCreatetime(DateUtil.currentDate());
		
		courseconfigService.updateCourseconfig(courseconfig);
	
	}
	
	public static void selectCourseconfigAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CourseconfigService courseconfigService = (CourseconfigService)cxt.getBean("courseconfigService");
		List<Courseconfig> objList  = courseconfigService.selectCourseconfigAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectCourseconfigById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CourseconfigService courseconfigService = (CourseconfigService)cxt.getBean("courseconfigService");
		Courseconfig obj  = courseconfigService.selectCourseconfigById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectCourseconfigFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CourseconfigService courseconfigService = (CourseconfigService)cxt.getBean("courseconfigService");
		CourseconfigFull obj  = courseconfigService.selectCourseconfigFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectCourseconfigFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CourseconfigService courseconfigService = (CourseconfigService)cxt.getBean("courseconfigService");
		List<CourseconfigFull> objList  = courseconfigService.selectCourseconfigFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectCourseconfigByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CourseconfigService courseconfigService = (CourseconfigService)cxt.getBean("courseconfigService");
		Page page = new Page(1,3);

		List<Courseconfig> objList  =  courseconfigService.selectCourseconfigByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectCourseconfigFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CourseconfigService courseconfigService = (CourseconfigService)cxt.getBean("courseconfigService");
		Page page = new Page(1,3);

		List<CourseconfigFull> objList  =  courseconfigService.selectCourseconfigFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
