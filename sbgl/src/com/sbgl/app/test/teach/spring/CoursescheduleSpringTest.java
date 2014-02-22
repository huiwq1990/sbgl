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



import com.sbgl.app.entity.Courseschedule;
import com.sbgl.app.entity.CoursescheduleFull;
import com.sbgl.app.services.teach.CoursescheduleService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class CoursescheduleSpringTest {

	public static void main(String[] args) {
//		intTable();
//iniDouble();
//		addCourseschedule();
//		selectCoursescheduleAll();
//		deleteCourseschedule();
		
//		updateCourseschedule();
//		selectCoursescheduleById(1L);
//		selectCoursescheduleFullById(1L);
//      selectCoursescheduleFullAll();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		CoursescheduleService coursescheduleService = (CoursescheduleService) cxt.getBean("coursescheduleService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Courseschedule");
		
		// 删除
		List<Courseschedule> coursescheduleList = coursescheduleService.selectCoursescheduleAll();
		if (coursescheduleList != null) {
			for (int i = 0; i < coursescheduleList.size(); i++) {
				coursescheduleService.deleteCourseschedule(coursescheduleList.get(i).getId());
			}
		}


		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/teach"+"/Courseschedule");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				
				coursescheduleService.addCourseschedule(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		CoursescheduleService coursescheduleService = (CoursescheduleService) cxt.getBean("coursescheduleService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Courseschedule");
		commonService.iniCode("Coursescheduletype");
		// 删除
		List<Courseschedule> coursescheduleList = coursescheduleService.selectCoursescheduleAll();
		if (coursescheduleList != null) {
			for (int i = 0; i < coursescheduleList.size(); i++) {
				coursescheduleService.deleteCourseschedule(coursescheduleList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/teach"+"/Courseschedule");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Courseschedule ch =	getObj(attrs,dataList.get(i));
				Courseschedule en =	getObj(attrs,dataList.get(i+1));
								
				coursescheduleService.addCourseschedule(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Courseschedule getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Courseschedule.class);
		
		Object obj =  Courseschedule.class.newInstance();
		String[] datas = str.split(",");
		Method method = null;
		
											if(!datas[0].trim().equals("")){
					 method = map.get(attrs[0]);
																method.invoke(obj,CoursescheduleId.valueOf(datas[0].trim()));
 										}
					
								
		return (Courseschedule)obj;		
		
	}
	
	public static void addCourseschedule(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CoursescheduleService coursescheduleService = (CoursescheduleService)cxt.getBean("coursescheduleService");
		
		Courseschedule courseschedule = new Courseschedule() ;
		
		//courseschedule.setCreatetime(DateUtil.currentDate());
		 		
		coursescheduleService.addCourseschedule(courseschedule);
	}
	
	public static void addCoursescheduleWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CoursescheduleService coursescheduleService = (CoursescheduleService)cxt.getBean("coursescheduleService");
	
		Courseschedule courseschedule = new Courseschedule() ;
		 
		//courseschedule.setCreatetime(DateUtil.currentDate());
		 		
		coursescheduleService.addCoursescheduleWithId(courseschedule);
	}
	
	public static void deleteCourseschedule(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		CoursescheduleService coursescheduleService =(CoursescheduleService)cxt.getBean("coursescheduleService");
		
		coursescheduleService.deleteCourseschedule(id);

	}
	
	public static void updateCourseschedule(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		CoursescheduleService coursescheduleService = (CoursescheduleService)cxt.getBean("coursescheduleService");
		
		Courseschedule courseschedule = new Courseschedule();
		//获取一个实体，更改它
		courseschedule = coursescheduleService.selectCoursescheduleById(0);
		//courseschedule.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//courseschedule.setCreatetime(DateUtil.currentDate());
		
		coursescheduleService.updateCourseschedule(courseschedule);
	
	}
	
	public static void selectCoursescheduleAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CoursescheduleService coursescheduleService = (CoursescheduleService)cxt.getBean("coursescheduleService");
		List<Courseschedule> objList  = coursescheduleService.selectCoursescheduleAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectCoursescheduleById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CoursescheduleService coursescheduleService = (CoursescheduleService)cxt.getBean("coursescheduleService");
		Courseschedule obj  = coursescheduleService.selectCoursescheduleById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectCoursescheduleFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CoursescheduleService coursescheduleService = (CoursescheduleService)cxt.getBean("coursescheduleService");
		CoursescheduleFull obj  = coursescheduleService.selectCoursescheduleFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectCoursescheduleFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CoursescheduleService coursescheduleService = (CoursescheduleService)cxt.getBean("coursescheduleService");
		List<CoursescheduleFull> objList  = coursescheduleService.selectCoursescheduleFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectCoursescheduleByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CoursescheduleService coursescheduleService = (CoursescheduleService)cxt.getBean("coursescheduleService");
		Page page = new Page(1,3);

		List<Courseschedule> objList  =  coursescheduleService.selectCoursescheduleByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectCoursescheduleFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		CoursescheduleService coursescheduleService = (CoursescheduleService)cxt.getBean("coursescheduleService");
		Page page = new Page(1,3);

		List<CoursescheduleFull> objList  =  coursescheduleService.selectCoursescheduleFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
