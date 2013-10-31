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




import com.sbgl.app.entity.Computer;
import com.sbgl.app.entity.ComputerFull;
import com.sbgl.app.services.computer.ComputerService;
import com.sbgl.util.*;
//import com.sbgl.app.services.common.CommonService;

public class ComputerSpringTest {

	public static void main(String[] args) {
//		intTable();
//		addComputer();
//		selectComputerAll();
//		deleteComputer();
		
//		updateComputer();
//		selectComputerById(1L);
//		selectComputerFullById(1L);
//      selectComputerFullAll();

	}
	
	/*
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerService computerService = (ComputerService) cxt.getBean("computerService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computer");
		
		// 删除
		List<Computer> computerList = computerService.selectComputerAll();
		if (computerList != null) {
			for (int i = 0; i < computerList.size(); i++) {
				computerService.deleteComputer(computerList.get(i).getId());
			}
		}


		try {
			HashMap<String, Method> map = ReflectUtil.ConverBean(Computer.class);
			Object obj;
			List<String> dataList = new ArrayList<String>();
			File f = new File( "data/Computer");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				obj = Computer.class.newInstance();

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
																method.invoke(obj,Integer.valueOf(datas[3].trim()));
 										}
					
									if(!datas[4].trim().equals("")){
					 method = map.get(attrs[4]);
																method.invoke(obj,DateUtil.parseDate(datas[4].trim()));  
										}
					
									if(!datas[5].trim().equals("")){
					 method = map.get(attrs[5]);
																method.invoke(obj,Integer.valueOf(datas[5].trim()));
 										}
					
									if(!datas[6].trim().equals("")){
					 method = map.get(attrs[6]);
																method.invoke(obj,Integer.valueOf(datas[6].trim()));
 										}
					
								
				computerService.addComputer((Computer)obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public static void addComputer(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerService computerService = (ComputerService)cxt.getBean("computerService");
		
		Computer computer = new Computer() ;
		
		//computer.setCreatetime(DateUtil.currentDate());
		 		
		computerService.addComputer(computer);
	}
	
	public static void addComputerWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerService computerService = (ComputerService)cxt.getBean("computerService");
	
		Computer computer = new Computer() ;
		 
		//computer.setCreatetime(DateUtil.currentDate());
		 		
		computerService.addComputerWithId(computer);
	}
	
	public static void deleteComputer(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerService computerService =(ComputerService)cxt.getBean("computerService");
		
		computerService.deleteComputer(id);

	}
	
	public static void updateComputer(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerService computerService = (ComputerService)cxt.getBean("computerService");
		
		Computer computer = new Computer();
		//获取一个实体，更改它
		computer = computerService.selectComputerById(0);
		//computer.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//computer.setCreatetime(DateUtil.currentDate());
		
		computerService.updateComputer(computer);
	
	}
	
	public static void selectComputerAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerService computerService = (ComputerService)cxt.getBean("computerService");
		List<Computer> objList  = computerService.selectComputerAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectComputerById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerService computerService = (ComputerService)cxt.getBean("computerService");
		Computer obj  = computerService.selectComputerById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectComputerFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerService computerService = (ComputerService)cxt.getBean("computerService");
		ComputerFull obj  = computerService.selectComputerFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectComputerFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerService computerService = (ComputerService)cxt.getBean("computerService");
		List<ComputerFull> objList  = computerService.selectComputerFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectComputerByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerService computerService = (ComputerService)cxt.getBean("computerService");
		Page page = new Page(1,3);

		List<Computer> objList  =  computerService.selectComputerByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectComputerFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerService computerService = (ComputerService)cxt.getBean("computerService");
		Page page = new Page(1,3);

		List<ComputerFull> objList  =  computerService.selectComputerFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
