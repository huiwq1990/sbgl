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




import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.util.*;
//import com.sbgl.app.services.common.CommonService;

public class ComputerorderSpringTest {

	public static void main(String[] args) {
//		intTable();
//		addComputerorder();
//		selectComputerorderAll();
//		deleteComputerorder();
		
//		updateComputerorder();
//		selectComputerorderById(1L);
//		selectComputerorderFullById(1L);
//      selectComputerorderFullAll();

	}
	
	/*
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerorderService computerorderService = (ComputerorderService) cxt.getBean("computerorderService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerorder");
		
		// 删除
		List<Computerorder> computerorderList = computerorderService.selectComputerorderAll();
		if (computerorderList != null) {
			for (int i = 0; i < computerorderList.size(); i++) {
				computerorderService.deleteComputerorder(computerorderList.get(i).getId());
			}
		}


		try {
			HashMap<String, Method> map = ReflectUtil.ConverBean(Computerorder.class);
			Object obj;
			List<String> dataList = new ArrayList<String>();
			File f = new File( "data/Computerorder");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				obj = Computerorder.class.newInstance();

				String[] datas = dataList.get(i).split(",");
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
																method.invoke(obj,Integer.valueOf(datas[4].trim()));
 										}
					
									if(!datas[5].trim().equals("")){
					 method = map.get(attrs[5]);
																method.invoke(obj,DateUtil.parseDate(datas[5].trim()));  
										}
					
									if(!datas[6].trim().equals("")){
					 method = map.get(attrs[6]);
																method.invoke(obj,DateUtil.parseDate(datas[6].trim()));  
										}
					
								
				computerorderService.addComputerorder((Computerorder)obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public static void addComputerorder(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderService computerorderService = (ComputerorderService)cxt.getBean("computerorderService");
		
		Computerorder computerorder = new Computerorder() ;
		
		//computerorder.setCreatetime(DateUtil.currentDate());
		 		
		computerorderService.addComputerorder(computerorder);
	}
	
	public static void addComputerorderWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderService computerorderService = (ComputerorderService)cxt.getBean("computerorderService");
	
		Computerorder computerorder = new Computerorder() ;
		 
		//computerorder.setCreatetime(DateUtil.currentDate());
		 		
		computerorderService.addComputerorderWithId(computerorder);
	}
	
	public static void deleteComputerorder(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerorderService computerorderService =(ComputerorderService)cxt.getBean("computerorderService");
		
		computerorderService.deleteComputerorder(id);

	}
	
	public static void updateComputerorder(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerorderService computerorderService = (ComputerorderService)cxt.getBean("computerorderService");
		
		Computerorder computerorder = new Computerorder();
		//获取一个实体，更改它
		computerorder = computerorderService.selectComputerorderById(0);
		//computerorder.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//computerorder.setCreatetime(DateUtil.currentDate());
		
		computerorderService.updateComputerorder(computerorder);
	
	}
	
	public static void selectComputerorderAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderService computerorderService = (ComputerorderService)cxt.getBean("computerorderService");
		List<Computerorder> objList  = computerorderService.selectComputerorderAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectComputerorderById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderService computerorderService = (ComputerorderService)cxt.getBean("computerorderService");
		Computerorder obj  = computerorderService.selectComputerorderById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectComputerorderFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderService computerorderService = (ComputerorderService)cxt.getBean("computerorderService");
		ComputerorderFull obj  = computerorderService.selectComputerorderFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectComputerorderFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderService computerorderService = (ComputerorderService)cxt.getBean("computerorderService");
		List<ComputerorderFull> objList  = computerorderService.selectComputerorderFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectComputerorderByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderService computerorderService = (ComputerorderService)cxt.getBean("computerorderService");
		Page page = new Page(1,3);

		List<Computerorder> objList  =  computerorderService.selectComputerorderByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectComputerorderFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderService computerorderService = (ComputerorderService)cxt.getBean("computerorderService");
		Page page = new Page(1,3);

		List<ComputerorderFull> objList  =  computerorderService.selectComputerorderFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
