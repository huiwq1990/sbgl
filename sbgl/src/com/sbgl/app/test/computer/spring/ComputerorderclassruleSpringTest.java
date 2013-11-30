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



import com.sbgl.app.entity.Computerorderclassrule;
import com.sbgl.app.entity.ComputerorderclassruleFull;
import com.sbgl.app.services.computer.ComputerorderclassruleService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class ComputerorderclassruleSpringTest {

	public static void main(String[] args) {
//		intTable();
//iniDouble();
//		addComputerorderclassrule();
//		selectComputerorderclassruleAll();
//		deleteComputerorderclassrule();
		
//		updateComputerorderclassrule();
//		selectComputerorderclassruleById(1L);
//		selectComputerorderclassruleFullById(1L);
//      selectComputerorderclassruleFullAll();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerorderclassruleService computerorderclassruleService = (ComputerorderclassruleService) cxt.getBean("computerorderclassruleService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerorderclassrule");
		
		// 删除
		List<Computerorderclassrule> computerorderclassruleList = computerorderclassruleService.selectComputerorderclassruleAll();
		if (computerorderclassruleList != null) {
			for (int i = 0; i < computerorderclassruleList.size(); i++) {
				computerorderclassruleService.deleteComputerorderclassrule(computerorderclassruleList.get(i).getId());
			}
		}


		try {
			HashMap<String, Method> map = ReflectUtil.ConverBean(Computerorderclassrule.class);
			Object obj;
			List<String> dataList = new ArrayList<String>();
			File f = new File( "D:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computerorderclassrule");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				obj = Computerorderclassrule.class.newInstance();

				String[] datas = dataList.get(i).split(",");
				
				
				computerorderclassruleService.addComputerorderclassrule(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerorderclassruleService computerorderclassruleService = (ComputerorderclassruleService) cxt.getBean("computerorderclassruleService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerorderclassrule");
		commonService.iniCode("Computerorderclassruletype");
		// 删除
		List<Computerorderclassrule> computerorderclassruleList = computerorderclassruleService.selectComputerorderclassruleAll();
		if (computerorderclassruleList != null) {
			for (int i = 0; i < computerorderclassruleList.size(); i++) {
				computerorderclassruleService.deleteComputerorderclassrule(computerorderclassruleList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "D:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computerorderclassrule");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Computerorderclassrule ch =	getObj(attrs,dataList.get(i));
				Computerorderclassrule en =	getObj(attrs,dataList.get(i+1));
								
				computerorderclassruleService.addComputerorderclassrule(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Computerorderclassrule getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Computerorderclassrule.class);
		
		Object obj =  Computerorderclassrule.class.newInstance();
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
					
									if(!datas[8].trim().equals("")){
					 method = map.get(attrs[8]);
																method.invoke(obj,DateUtil.parseDate(datas[8].trim()));  
										}
					
									if(!datas[9].trim().equals("")){
					 method = map.get(attrs[9]);
																method.invoke(obj,Integer.valueOf(datas[9].trim()));
 										}
					
								
		return (Computerorderclassrule)obj;		
		
	}
	
	public static void addComputerorderclassrule(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderclassruleService computerorderclassruleService = (ComputerorderclassruleService)cxt.getBean("computerorderclassruleService");
		
		Computerorderclassrule computerorderclassrule = new Computerorderclassrule() ;
		
		//computerorderclassrule.setCreatetime(DateUtil.currentDate());
		 		
		computerorderclassruleService.addComputerorderclassrule(computerorderclassrule);
	}
	
	public static void addComputerorderclassruleWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderclassruleService computerorderclassruleService = (ComputerorderclassruleService)cxt.getBean("computerorderclassruleService");
	
		Computerorderclassrule computerorderclassrule = new Computerorderclassrule() ;
		 
		//computerorderclassrule.setCreatetime(DateUtil.currentDate());
		 		
		computerorderclassruleService.addComputerorderclassruleWithId(computerorderclassrule);
	}
	
	public static void deleteComputerorderclassrule(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerorderclassruleService computerorderclassruleService =(ComputerorderclassruleService)cxt.getBean("computerorderclassruleService");
		
		computerorderclassruleService.deleteComputerorderclassrule(id);

	}
	
	public static void updateComputerorderclassrule(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerorderclassruleService computerorderclassruleService = (ComputerorderclassruleService)cxt.getBean("computerorderclassruleService");
		
		Computerorderclassrule computerorderclassrule = new Computerorderclassrule();
		//获取一个实体，更改它
		computerorderclassrule = computerorderclassruleService.selectComputerorderclassruleById(0);
		//computerorderclassrule.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//computerorderclassrule.setCreatetime(DateUtil.currentDate());
		
		computerorderclassruleService.updateComputerorderclassrule(computerorderclassrule);
	
	}
	
	public static void selectComputerorderclassruleAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderclassruleService computerorderclassruleService = (ComputerorderclassruleService)cxt.getBean("computerorderclassruleService");
		List<Computerorderclassrule> objList  = computerorderclassruleService.selectComputerorderclassruleAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectComputerorderclassruleById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderclassruleService computerorderclassruleService = (ComputerorderclassruleService)cxt.getBean("computerorderclassruleService");
		Computerorderclassrule obj  = computerorderclassruleService.selectComputerorderclassruleById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectComputerorderclassruleFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderclassruleService computerorderclassruleService = (ComputerorderclassruleService)cxt.getBean("computerorderclassruleService");
		ComputerorderclassruleFull obj  = computerorderclassruleService.selectComputerorderclassruleFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectComputerorderclassruleFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderclassruleService computerorderclassruleService = (ComputerorderclassruleService)cxt.getBean("computerorderclassruleService");
		List<ComputerorderclassruleFull> objList  = computerorderclassruleService.selectComputerorderclassruleFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectComputerorderclassruleByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderclassruleService computerorderclassruleService = (ComputerorderclassruleService)cxt.getBean("computerorderclassruleService");
		Page page = new Page(1,3);

		List<Computerorderclassrule> objList  =  computerorderclassruleService.selectComputerorderclassruleByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectComputerorderclassruleFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderclassruleService computerorderclassruleService = (ComputerorderclassruleService)cxt.getBean("computerorderclassruleService");
		Page page = new Page(1,3);

		List<ComputerorderclassruleFull> objList  =  computerorderclassruleService.selectComputerorderclassruleFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
