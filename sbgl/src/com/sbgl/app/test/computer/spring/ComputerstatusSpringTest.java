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



import com.sbgl.app.entity.Computerstatus;
import com.sbgl.app.entity.ComputerstatusFull;
import com.sbgl.app.services.computer.ComputerstatusService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class ComputerstatusSpringTest {

	public static void main(String[] args) {
//		intTable();
//iniDouble();
//		addComputerstatus();
//		selectComputerstatusAll();
//		deleteComputerstatus();
		
//		updateComputerstatus();
//		selectComputerstatusById(1L);
//		selectComputerstatusFullById(1L);
//      selectComputerstatusFullAll();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerstatusService computerstatusService = (ComputerstatusService) cxt.getBean("computerstatusService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerstatus");
		
		// 删除
		List<Computerstatus> computerstatusList = computerstatusService.selectComputerstatusAll();
		if (computerstatusList != null) {
			for (int i = 0; i < computerstatusList.size(); i++) {
				computerstatusService.deleteComputerstatus(computerstatusList.get(i).getId());
			}
		}


		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "D:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computerstatus");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				
				computerstatusService.addComputerstatus(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerstatusService computerstatusService = (ComputerstatusService) cxt.getBean("computerstatusService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerstatus");
		commonService.iniCode("Computerstatustype");
		// 删除
		List<Computerstatus> computerstatusList = computerstatusService.selectComputerstatusAll();
		if (computerstatusList != null) {
			for (int i = 0; i < computerstatusList.size(); i++) {
				computerstatusService.deleteComputerstatus(computerstatusList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "D:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computerstatus");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Computerstatus ch =	getObj(attrs,dataList.get(i));
				Computerstatus en =	getObj(attrs,dataList.get(i+1));
								
				computerstatusService.addComputerstatus(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Computerstatus getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Computerstatus.class);
		
		Object obj =  Computerstatus.class.newInstance();
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
					
								
		return (Computerstatus)obj;		
		
	}
	
	public static void addComputerstatus(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerstatusService computerstatusService = (ComputerstatusService)cxt.getBean("computerstatusService");
		
		Computerstatus computerstatus = new Computerstatus() ;
		
		//computerstatus.setCreatetime(DateUtil.currentDate());
		 		
		computerstatusService.addComputerstatus(computerstatus);
	}
	
	public static void addComputerstatusWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerstatusService computerstatusService = (ComputerstatusService)cxt.getBean("computerstatusService");
	
		Computerstatus computerstatus = new Computerstatus() ;
		 
		//computerstatus.setCreatetime(DateUtil.currentDate());
		 		
		computerstatusService.addComputerstatusWithId(computerstatus);
	}
	
	public static void deleteComputerstatus(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerstatusService computerstatusService =(ComputerstatusService)cxt.getBean("computerstatusService");
		
		computerstatusService.deleteComputerstatus(id);

	}
	
	public static void updateComputerstatus(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerstatusService computerstatusService = (ComputerstatusService)cxt.getBean("computerstatusService");
		
		Computerstatus computerstatus = new Computerstatus();
		//获取一个实体，更改它
		computerstatus = computerstatusService.selectComputerstatusById(0);
		//computerstatus.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//computerstatus.setCreatetime(DateUtil.currentDate());
		
		computerstatusService.updateComputerstatus(computerstatus);
	
	}
	
	public static void selectComputerstatusAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerstatusService computerstatusService = (ComputerstatusService)cxt.getBean("computerstatusService");
		List<Computerstatus> objList  = computerstatusService.selectComputerstatusAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectComputerstatusById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerstatusService computerstatusService = (ComputerstatusService)cxt.getBean("computerstatusService");
		Computerstatus obj  = computerstatusService.selectComputerstatusById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectComputerstatusFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerstatusService computerstatusService = (ComputerstatusService)cxt.getBean("computerstatusService");
		ComputerstatusFull obj  = computerstatusService.selectComputerstatusFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectComputerstatusFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerstatusService computerstatusService = (ComputerstatusService)cxt.getBean("computerstatusService");
		List<ComputerstatusFull> objList  = computerstatusService.selectComputerstatusFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectComputerstatusByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerstatusService computerstatusService = (ComputerstatusService)cxt.getBean("computerstatusService");
		Page page = new Page(1,3);

		List<Computerstatus> objList  =  computerstatusService.selectComputerstatusByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectComputerstatusFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerstatusService computerstatusService = (ComputerstatusService)cxt.getBean("computerstatusService");
		Page page = new Page(1,3);

		List<ComputerstatusFull> objList  =  computerstatusService.selectComputerstatusFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
