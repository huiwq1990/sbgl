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



import com.sbgl.app.entity.Computerorderconfig;
import com.sbgl.app.entity.ComputerorderconfigFull;
import com.sbgl.app.services.computer.ComputerorderconfigService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class ComputerorderconfigSpringTest {

	public static void main(String[] args) {
//		intTable();
//iniDouble();
//		addComputerorderconfig();
//		selectComputerorderconfigAll();
//		deleteComputerorderconfig();
		
//		updateComputerorderconfig();
//		selectComputerorderconfigById(1L);
//		selectComputerorderconfigFullById(1L);
//      selectComputerorderconfigFullAll();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerorderconfigService computerorderconfigService = (ComputerorderconfigService) cxt.getBean("computerorderconfigService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerorderconfig");
		
		// 删除
		List<Computerorderconfig> computerorderconfigList = computerorderconfigService.selectComputerorderconfigAll();
		if (computerorderconfigList != null) {
			for (int i = 0; i < computerorderconfigList.size(); i++) {
				computerorderconfigService.deleteComputerorderconfig(computerorderconfigList.get(i).getId());
			}
		}


		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computerorderconfig");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				
				computerorderconfigService.addComputerorderconfig(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerorderconfigService computerorderconfigService = (ComputerorderconfigService) cxt.getBean("computerorderconfigService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerorderconfig");
		commonService.iniCode("Computerorderconfigtype");
		// 删除
		List<Computerorderconfig> computerorderconfigList = computerorderconfigService.selectComputerorderconfigAll();
		if (computerorderconfigList != null) {
			for (int i = 0; i < computerorderconfigList.size(); i++) {
				computerorderconfigService.deleteComputerorderconfig(computerorderconfigList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computerorderconfig");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Computerorderconfig ch =	getObj(attrs,dataList.get(i));
				Computerorderconfig en =	getObj(attrs,dataList.get(i+1));
								
				computerorderconfigService.addComputerorderconfig(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Computerorderconfig getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Computerorderconfig.class);
		
		Object obj =  Computerorderconfig.class.newInstance();
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
					
									if(!datas[8].trim().equals("")){
					 method = map.get(attrs[8]);
																method.invoke(obj,Integer.valueOf(datas[8].trim()));
 										}
					
									if(!datas[9].trim().equals("")){
					 method = map.get(attrs[9]);
																method.invoke(obj,Integer.valueOf(datas[9].trim()));
 										}
					
								
		return (Computerorderconfig)obj;		
		
	}
	
	public static void addComputerorderconfig(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderconfigService computerorderconfigService = (ComputerorderconfigService)cxt.getBean("computerorderconfigService");
		
		Computerorderconfig computerorderconfig = new Computerorderconfig() ;
		
		//computerorderconfig.setCreatetime(DateUtil.currentDate());
		 		
		computerorderconfigService.addComputerorderconfig(computerorderconfig);
	}
	
	public static void addComputerorderconfigWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderconfigService computerorderconfigService = (ComputerorderconfigService)cxt.getBean("computerorderconfigService");
	
		Computerorderconfig computerorderconfig = new Computerorderconfig() ;
		 
		//computerorderconfig.setCreatetime(DateUtil.currentDate());
		 		
		computerorderconfigService.addComputerorderconfigWithId(computerorderconfig);
	}
	
	public static void deleteComputerorderconfig(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerorderconfigService computerorderconfigService =(ComputerorderconfigService)cxt.getBean("computerorderconfigService");
		
		computerorderconfigService.deleteComputerorderconfig(id);

	}
	
	public static void updateComputerorderconfig(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerorderconfigService computerorderconfigService = (ComputerorderconfigService)cxt.getBean("computerorderconfigService");
		
		Computerorderconfig computerorderconfig = new Computerorderconfig();
		//获取一个实体，更改它
		computerorderconfig = computerorderconfigService.selectComputerorderconfigById(0);
		//computerorderconfig.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//computerorderconfig.setCreatetime(DateUtil.currentDate());
		
		computerorderconfigService.updateComputerorderconfig(computerorderconfig);
	
	}
	
	public static void selectComputerorderconfigAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderconfigService computerorderconfigService = (ComputerorderconfigService)cxt.getBean("computerorderconfigService");
		List<Computerorderconfig> objList  = computerorderconfigService.selectComputerorderconfigAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectComputerorderconfigById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderconfigService computerorderconfigService = (ComputerorderconfigService)cxt.getBean("computerorderconfigService");
		Computerorderconfig obj  = computerorderconfigService.selectComputerorderconfigById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectComputerorderconfigFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderconfigService computerorderconfigService = (ComputerorderconfigService)cxt.getBean("computerorderconfigService");
		ComputerorderconfigFull obj  = computerorderconfigService.selectComputerorderconfigFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectComputerorderconfigFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderconfigService computerorderconfigService = (ComputerorderconfigService)cxt.getBean("computerorderconfigService");
		List<ComputerorderconfigFull> objList  = computerorderconfigService.selectComputerorderconfigFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectComputerorderconfigByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderconfigService computerorderconfigService = (ComputerorderconfigService)cxt.getBean("computerorderconfigService");
		Page page = new Page(1,3);

		List<Computerorderconfig> objList  =  computerorderconfigService.selectComputerorderconfigByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectComputerorderconfigFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderconfigService computerorderconfigService = (ComputerorderconfigService)cxt.getBean("computerorderconfigService");
		Page page = new Page(1,3);

		List<ComputerorderconfigFull> objList  =  computerorderconfigService.selectComputerorderconfigFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
