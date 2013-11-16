package com.sbgl.app.test.computer.spring;

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



import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.ComputercategoryFull;
import com.sbgl.app.services.computer.ComputercategoryService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class ComputercategorySpringTest {

	public static void main(String[] args) {
//		intTable();
//		addComputercategory();
//		selectComputercategoryAll();
//		deleteComputercategory();
		
//		updateComputercategory();
//		selectComputercategoryById(1L);
//		selectComputercategoryFullById(1L);
//      selectComputercategoryFullAll();

		
		selectParentComputercategory();
	}
	
	public static void selectParentComputercategory(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputercategoryService computercategoryService = (ComputercategoryService)cxt.getBean("computercategoryService");
	
		List<Computercategory> parentcomputercategoryList =computercategoryService.selectParentComputercategory();
		for(int i = 0; i < parentcomputercategoryList.size(); i++){
			System.out.println("id="+parentcomputercategoryList.get(i).getName());
		}
	}
	
	
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputercategoryService computercategoryService = (ComputercategoryService) cxt.getBean("computercategoryService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computercategory");
		
		// 删除
		List<Computercategory> computercategoryList = computercategoryService.selectComputercategoryAll();
		if (computercategoryList != null) {
			for (int i = 0; i < computercategoryList.size(); i++) {
				computercategoryService.deleteComputercategory(computercategoryList.get(i).getId());
			}
		}


		try {
			HashMap<String, Method> map = ReflectUtil.ConverBean(Computercategory.class);
			Object obj;
			List<String> dataList = new ArrayList<String>();
			File f = new File( "D:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computercategory");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				obj = Computercategory.class.newInstance();

				String[] datas = dataList.get(i).split(",");
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
																method.invoke(obj,String.valueOf(datas[2].trim()));
 										}
					
									if(!datas[3].trim().equals("")){
					 method = map.get(attrs[3]);
																method.invoke(obj,DateUtil.parseDate(datas[3].trim()));  
										}
					
									if(!datas[4].trim().equals("")){
					 method = map.get(attrs[4]);
																method.invoke(obj,Integer.valueOf(datas[4].trim()));
 										}
					
									if(!datas[5].trim().equals("")){
					 method = map.get(attrs[5]);
																method.invoke(obj,Integer.valueOf(datas[5].trim()));
 										}
					
								
				computercategoryService.addComputercategory((Computercategory)obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addComputercategory(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputercategoryService computercategoryService = (ComputercategoryService)cxt.getBean("computercategoryService");
		
		Computercategory computercategory = new Computercategory() ;
		
		//computercategory.setCreatetime(DateUtil.currentDate());
		 		
		computercategoryService.addComputercategory(computercategory);
	}
	
	public static void addComputercategoryWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputercategoryService computercategoryService = (ComputercategoryService)cxt.getBean("computercategoryService");
	
		Computercategory computercategory = new Computercategory() ;
		 
		//computercategory.setCreatetime(DateUtil.currentDate());
		 		
		computercategoryService.addComputercategoryWithId(computercategory);
	}
	
	public static void deleteComputercategory(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputercategoryService computercategoryService =(ComputercategoryService)cxt.getBean("computercategoryService");
		
		computercategoryService.deleteComputercategory(id);

	}
	
	public static void updateComputercategory(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputercategoryService computercategoryService = (ComputercategoryService)cxt.getBean("computercategoryService");
		
		Computercategory computercategory = new Computercategory();
		//获取一个实体，更改它
		computercategory = computercategoryService.selectComputercategoryById(0);
		//computercategory.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//computercategory.setCreatetime(DateUtil.currentDate());
		
		computercategoryService.updateComputercategory(computercategory);
	
	}
	
	public static void selectComputercategoryAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputercategoryService computercategoryService = (ComputercategoryService)cxt.getBean("computercategoryService");
		List<Computercategory> objList  = computercategoryService.selectComputercategoryAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectComputercategoryById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputercategoryService computercategoryService = (ComputercategoryService)cxt.getBean("computercategoryService");
		Computercategory obj  = computercategoryService.selectComputercategoryById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectComputercategoryFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputercategoryService computercategoryService = (ComputercategoryService)cxt.getBean("computercategoryService");
		ComputercategoryFull obj  = computercategoryService.selectComputercategoryFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectComputercategoryFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputercategoryService computercategoryService = (ComputercategoryService)cxt.getBean("computercategoryService");
		List<ComputercategoryFull> objList  = computercategoryService.selectComputercategoryFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectComputercategoryByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputercategoryService computercategoryService = (ComputercategoryService)cxt.getBean("computercategoryService");
		Page page = new Page(1,3);

		List<Computercategory> objList  =  computercategoryService.selectComputercategoryByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectComputercategoryFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputercategoryService computercategoryService = (ComputercategoryService)cxt.getBean("computercategoryService");
		Page page = new Page(1,3);

		List<ComputercategoryFull> objList  =  computercategoryService.selectComputercategoryFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
