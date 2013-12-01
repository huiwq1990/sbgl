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
import com.sbgl.app.entity.Computercategoryi18n;
import com.sbgl.app.services.computer.ComputercategoryService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class ComputercategorySpringTest {

	public static void main(String[] args) {
		iniDouble();
//		addComputercategory();
//		selectComputercategoryAll();
//		deleteComputercategory();
		
//		updateComputercategory();
//		selectComputercategoryById(1L);
//		selectComputercategoryFullById(1L);
//      selectComputercategoryFullAll();
//		selectComputercategoryi18n();
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
				
				
				computercategoryService.addComputercategory(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputercategoryService computercategoryService = (ComputercategoryService) cxt.getBean("computercategoryService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computercategory");
		commonService.iniCode("Computercategorytype");
		// 删除
		List<Computercategory> computercategoryList = computercategoryService.selectComputercategoryAll();
		if (computercategoryList != null) {
			for (int i = 0; i < computercategoryList.size(); i++) {
				computercategoryService.deleteComputercategory(computercategoryList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "D:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computercategory");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Computercategory ch =	getObj(attrs,dataList.get(i));
				Computercategory en =	getObj(attrs,dataList.get(i+1));
								
//				System.out.println(ch.getLanguagetype());
				computercategoryService.addComputercategory(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Computercategory getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Computercategory.class);
		
		Object obj =  Computercategory.class.newInstance();
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
																method.invoke(obj,String.valueOf(datas[2].trim()));
 										}
					
									if(!datas[3].trim().equals("")){
					 method = map.get(attrs[3]);
																method.invoke(obj,Integer.valueOf(datas[3].trim()));
 										}
					
									if(!datas[4].trim().equals("")){
					 method = map.get(attrs[4]);
																method.invoke(obj,String.valueOf(datas[4].trim()));
 										}
					
									if(!datas[5].trim().equals("")){
					 method = map.get(attrs[5]);
																method.invoke(obj,DateUtil.parseDate(datas[5].trim()));  
										}
					
									if(!datas[6].trim().equals("")){
					 method = map.get(attrs[6]);
																method.invoke(obj,Integer.valueOf(datas[6].trim()));
 										}
					
									if(!datas[7].trim().equals("")){
					 method = map.get(attrs[7]);
																method.invoke(obj,Integer.valueOf(datas[7].trim()));
 										}
					
								
		return (Computercategory)obj;		
		
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
		
		int a = computercategoryService.deleteComputercategory(id);
		System.out.println(a);
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
			System.out.println("id="+objList.get(i).getComputercategoryname());
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
		
		
		
		public static void selectComputercategoryi18n(){
			
			ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
			
			ComputercategoryService computercategoryService = (ComputercategoryService)cxt.getBean("computercategoryService");
//			Page page = new Page(1,3);

			List<Computercategoryi18n> objList  =  computercategoryService.selectComputercategoryi18nByCondition("");
			for(int i = 0; i < objList.size(); i++){
				System.out.println("id="+objList.get(i).getNamech());
			}
		}
}
