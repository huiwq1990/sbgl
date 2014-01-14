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



import com.sbgl.app.entity.Computerhomework;
import com.sbgl.app.entity.ComputerhomeworkFull;
import com.sbgl.app.services.computer.ComputerhomeworkService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class ComputerhomeworkSpringTest {

	public static void main(String[] args) {
//		intTable();
//iniDouble();
//		addComputerhomework();
//		selectComputerhomeworkAll();
//		deleteComputerhomework();
		
//		updateComputerhomework();
//		selectComputerhomeworkById(1L);
//		selectComputerhomeworkFullById(1L);
//      selectComputerhomeworkFullAll();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerhomeworkService computerhomeworkService = (ComputerhomeworkService) cxt.getBean("computerhomeworkService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerhomework");
		
		// 删除
		List<Computerhomework> computerhomeworkList = computerhomeworkService.selectComputerhomeworkAll();
		if (computerhomeworkList != null) {
			for (int i = 0; i < computerhomeworkList.size(); i++) {
				computerhomeworkService.deleteComputerhomework(computerhomeworkList.get(i).getId());
			}
		}


		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computerhomework");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				
				computerhomeworkService.addComputerhomework(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerhomeworkService computerhomeworkService = (ComputerhomeworkService) cxt.getBean("computerhomeworkService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerhomework");
		commonService.iniCode("Computerhomeworktype");
		// 删除
		List<Computerhomework> computerhomeworkList = computerhomeworkService.selectComputerhomeworkAll();
		if (computerhomeworkList != null) {
			for (int i = 0; i < computerhomeworkList.size(); i++) {
				computerhomeworkService.deleteComputerhomework(computerhomeworkList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computerhomework");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Computerhomework ch =	getObj(attrs,dataList.get(i));
				Computerhomework en =	getObj(attrs,dataList.get(i+1));
								
				computerhomeworkService.addComputerhomework(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Computerhomework getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Computerhomework.class);
		
		Object obj =  Computerhomework.class.newInstance();
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
																method.invoke(obj,String.valueOf(datas[3].trim()));
 										}
					
									if(!datas[4].trim().equals("")){
					 method = map.get(attrs[4]);
																method.invoke(obj,Integer.valueOf(datas[4].trim()));
 										}
					
									if(!datas[5].trim().equals("")){
					 method = map.get(attrs[5]);
																method.invoke(obj,String.valueOf(datas[5].trim()));
 										}
					
									if(!datas[6].trim().equals("")){
					 method = map.get(attrs[6]);
																method.invoke(obj,Integer.valueOf(datas[6].trim()));
 										}
					
									if(!datas[7].trim().equals("")){
					 method = map.get(attrs[7]);
																method.invoke(obj,DateUtil.parseDate(datas[7].trim()));  
										}
					
								
		return (Computerhomework)obj;		
		
	}
	
	public static void addComputerhomework(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerhomeworkService computerhomeworkService = (ComputerhomeworkService)cxt.getBean("computerhomeworkService");
		
		Computerhomework computerhomework = new Computerhomework() ;
		
		//computerhomework.setCreatetime(DateUtil.currentDate());
		 		
		computerhomeworkService.addComputerhomework(computerhomework);
	}
	
	public static void addComputerhomeworkWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerhomeworkService computerhomeworkService = (ComputerhomeworkService)cxt.getBean("computerhomeworkService");
	
		Computerhomework computerhomework = new Computerhomework() ;
		 
		//computerhomework.setCreatetime(DateUtil.currentDate());
		 		
		computerhomeworkService.addComputerhomeworkWithId(computerhomework);
	}
	
	public static void deleteComputerhomework(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerhomeworkService computerhomeworkService =(ComputerhomeworkService)cxt.getBean("computerhomeworkService");
		
		computerhomeworkService.deleteComputerhomework(id);

	}
	
	public static void updateComputerhomework(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerhomeworkService computerhomeworkService = (ComputerhomeworkService)cxt.getBean("computerhomeworkService");
		
		Computerhomework computerhomework = new Computerhomework();
		//获取一个实体，更改它
		computerhomework = computerhomeworkService.selectComputerhomeworkById(0);
		//computerhomework.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//computerhomework.setCreatetime(DateUtil.currentDate());
		
		computerhomeworkService.updateComputerhomework(computerhomework);
	
	}
	
	public static void selectComputerhomeworkAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerhomeworkService computerhomeworkService = (ComputerhomeworkService)cxt.getBean("computerhomeworkService");
		List<Computerhomework> objList  = computerhomeworkService.selectComputerhomeworkAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectComputerhomeworkById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerhomeworkService computerhomeworkService = (ComputerhomeworkService)cxt.getBean("computerhomeworkService");
		Computerhomework obj  = computerhomeworkService.selectComputerhomeworkById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectComputerhomeworkFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerhomeworkService computerhomeworkService = (ComputerhomeworkService)cxt.getBean("computerhomeworkService");
		ComputerhomeworkFull obj  = computerhomeworkService.selectComputerhomeworkFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectComputerhomeworkFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerhomeworkService computerhomeworkService = (ComputerhomeworkService)cxt.getBean("computerhomeworkService");
		List<ComputerhomeworkFull> objList  = computerhomeworkService.selectComputerhomeworkFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectComputerhomeworkByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerhomeworkService computerhomeworkService = (ComputerhomeworkService)cxt.getBean("computerhomeworkService");
		Page page = new Page(1,3);

		List<Computerhomework> objList  =  computerhomeworkService.selectComputerhomeworkByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectComputerhomeworkFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerhomeworkService computerhomeworkService = (ComputerhomeworkService)cxt.getBean("computerhomeworkService");
		Page page = new Page(1,3);

		List<ComputerhomeworkFull> objList  =  computerhomeworkService.selectComputerhomeworkFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
