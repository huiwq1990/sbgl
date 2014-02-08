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



import com.sbgl.app.entity.Computerconfig;
import com.sbgl.app.entity.ComputerconfigFull;
import com.sbgl.app.services.computer.ComputerconfigService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class ComputerconfigSpringTest {

	public static void main(String[] args) {
//		intTable();
//iniDouble();
//		addComputerconfig();
//		selectComputerconfigAll();
//		deleteComputerconfig();
		
//		updateComputerconfig();
//		selectComputerconfigById(1L);
//		selectComputerconfigFullById(1L);
//      selectComputerconfigFullAll();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerconfigService computerconfigService = (ComputerconfigService) cxt.getBean("computerconfigService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerconfig");
		
		// 删除
		List<Computerconfig> computerconfigList = computerconfigService.selectComputerconfigAll();
		if (computerconfigList != null) {
			for (int i = 0; i < computerconfigList.size(); i++) {
				computerconfigService.deleteComputerconfig(computerconfigList.get(i).getId());
			}
		}


		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computerconfig");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				
				computerconfigService.addComputerconfig(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerconfigService computerconfigService = (ComputerconfigService) cxt.getBean("computerconfigService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerconfig");
		commonService.iniCode("Computerconfigtype");
		// 删除
		List<Computerconfig> computerconfigList = computerconfigService.selectComputerconfigAll();
		if (computerconfigList != null) {
			for (int i = 0; i < computerconfigList.size(); i++) {
				computerconfigService.deleteComputerconfig(computerconfigList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computerconfig");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Computerconfig ch =	getObj(attrs,dataList.get(i));
				Computerconfig en =	getObj(attrs,dataList.get(i+1));
								
				computerconfigService.addComputerconfig(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Computerconfig getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Computerconfig.class);
		
		Object obj =  Computerconfig.class.newInstance();
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
					
								
		return (Computerconfig)obj;		
		
	}
	
	public static void addComputerconfig(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerconfigService computerconfigService = (ComputerconfigService)cxt.getBean("computerconfigService");
		
		Computerconfig computerconfig = new Computerconfig() ;
		
		//computerconfig.setCreatetime(DateUtil.currentDate());
		 		
		computerconfigService.addComputerconfig(computerconfig);
	}
	
	public static void addComputerconfigWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerconfigService computerconfigService = (ComputerconfigService)cxt.getBean("computerconfigService");
	
		Computerconfig computerconfig = new Computerconfig() ;
		 
		//computerconfig.setCreatetime(DateUtil.currentDate());
		 		
		computerconfigService.addComputerconfigWithId(computerconfig);
	}
	
	public static void deleteComputerconfig(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerconfigService computerconfigService =(ComputerconfigService)cxt.getBean("computerconfigService");
		
		computerconfigService.deleteComputerconfig(id);

	}
	
	public static void updateComputerconfig(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerconfigService computerconfigService = (ComputerconfigService)cxt.getBean("computerconfigService");
		
		Computerconfig computerconfig = new Computerconfig();
		//获取一个实体，更改它
		computerconfig = computerconfigService.selectComputerconfigById(0);
		//computerconfig.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//computerconfig.setCreatetime(DateUtil.currentDate());
		
		computerconfigService.updateComputerconfig(computerconfig);
	
	}
	
	public static void selectComputerconfigAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerconfigService computerconfigService = (ComputerconfigService)cxt.getBean("computerconfigService");
		List<Computerconfig> objList  = computerconfigService.selectComputerconfigAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectComputerconfigById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerconfigService computerconfigService = (ComputerconfigService)cxt.getBean("computerconfigService");
		Computerconfig obj  = computerconfigService.selectComputerconfigById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectComputerconfigFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerconfigService computerconfigService = (ComputerconfigService)cxt.getBean("computerconfigService");
		ComputerconfigFull obj  = computerconfigService.selectComputerconfigFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectComputerconfigFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerconfigService computerconfigService = (ComputerconfigService)cxt.getBean("computerconfigService");
		List<ComputerconfigFull> objList  = computerconfigService.selectComputerconfigFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectComputerconfigByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerconfigService computerconfigService = (ComputerconfigService)cxt.getBean("computerconfigService");
		Page page = new Page(1,3);

		List<Computerconfig> objList  =  computerconfigService.selectComputerconfigByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectComputerconfigFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerconfigService computerconfigService = (ComputerconfigService)cxt.getBean("computerconfigService");
		Page page = new Page(1,3);

		List<ComputerconfigFull> objList  =  computerconfigService.selectComputerconfigFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
