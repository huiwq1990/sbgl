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



import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.ComputermodelFull;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class ComputermodelSpringTest {

	public static void main(String[] args) {
//		intTable();
iniDouble();
//		addComputermodel();
//		selectComputermodelAll();
//		deleteComputermodel();
		
//		updateComputermodel();
//		selectComputermodelById(1L);
//		selectComputermodelFullById(1L);
//      selectComputermodelFullAll();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputermodelService computermodelService = (ComputermodelService) cxt.getBean("computermodelService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computermodel");
		
		// 删除
		List<Computermodel> computermodelList = computermodelService.selectComputermodelAll();
		if (computermodelList != null) {
			for (int i = 0; i < computermodelList.size(); i++) {
				computermodelService.deleteComputermodel(computermodelList.get(i).getId());
			}
		}


		try {
			HashMap<String, Method> map = ReflectUtil.ConverBean(Computermodel.class);
			Object obj;
			List<String> dataList = new ArrayList<String>();
			File f = new File( "D:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computermodel");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				obj = Computermodel.class.newInstance();

				String[] datas = dataList.get(i).split(",");
				
				
				computermodelService.addComputermodel(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputermodelService computermodelService = (ComputermodelService) cxt.getBean("computermodelService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computermodel");
		commonService.iniCode("Computermodeltype");
		// 删除
		List<Computermodel> computermodelList = computermodelService.selectComputermodelAll();
		if (computermodelList != null) {
			for (int i = 0; i < computermodelList.size(); i++) {
				computermodelService.deleteComputermodel(computermodelList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "D:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computermodel");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Computermodel ch =	getObj(attrs,dataList.get(i));
				Computermodel en =	getObj(attrs,dataList.get(i+1));
								
				computermodelService.addComputermodel(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Computermodel getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Computermodel.class);
		
		Object obj =  Computermodel.class.newInstance();
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
																method.invoke(obj,DateUtil.parseDate(datas[6].trim()));  
										}
					
									if(!datas[7].trim().equals("")){
					 method = map.get(attrs[7]);
																method.invoke(obj,Integer.valueOf(datas[7].trim()));
 										}
					
									if(!datas[8].trim().equals("")){
					 method = map.get(attrs[8]);
																method.invoke(obj,Integer.valueOf(datas[8].trim()));
 										}
					
									if(!datas[9].trim().equals("")){
					 method = map.get(attrs[9]);
																method.invoke(obj,Integer.valueOf(datas[9].trim()));
 										}
					
									if(!datas[10].trim().equals("")){
					 method = map.get(attrs[10]);
																method.invoke(obj,String.valueOf(datas[10].trim()));
 										}
					
									if(!datas[11].trim().equals("")){
					 method = map.get(attrs[11]);
																method.invoke(obj,Integer.valueOf(datas[11].trim()));
 										}
					
								
		return (Computermodel)obj;		
		
	}
	
	public static void addComputermodel(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputermodelService computermodelService = (ComputermodelService)cxt.getBean("computermodelService");
		
		Computermodel computermodel = new Computermodel() ;
		
		//computermodel.setCreatetime(DateUtil.currentDate());
		 		
		computermodelService.addComputermodel(computermodel);
	}
	
	public static void addComputermodelWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputermodelService computermodelService = (ComputermodelService)cxt.getBean("computermodelService");
	
		Computermodel computermodel = new Computermodel() ;
		 
		//computermodel.setCreatetime(DateUtil.currentDate());
		 		
		computermodelService.addComputermodelWithId(computermodel);
	}
	
	public static void deleteComputermodel(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputermodelService computermodelService =(ComputermodelService)cxt.getBean("computermodelService");
		
		computermodelService.deleteComputermodel(id);

	}
	
	public static void updateComputermodel(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputermodelService computermodelService = (ComputermodelService)cxt.getBean("computermodelService");
		
		Computermodel computermodel = new Computermodel();
		//获取一个实体，更改它
		computermodel = computermodelService.selectComputermodelById(0);
		//computermodel.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//computermodel.setCreatetime(DateUtil.currentDate());
		
		computermodelService.updateComputermodel(computermodel);
	
	}
	
	public static void selectComputermodelAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputermodelService computermodelService = (ComputermodelService)cxt.getBean("computermodelService");
		List<Computermodel> objList  = computermodelService.selectComputermodelAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectComputermodelById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputermodelService computermodelService = (ComputermodelService)cxt.getBean("computermodelService");
		Computermodel obj  = computermodelService.selectComputermodelById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectComputermodelFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputermodelService computermodelService = (ComputermodelService)cxt.getBean("computermodelService");
		ComputermodelFull obj  = computermodelService.selectComputermodelFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectComputermodelFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputermodelService computermodelService = (ComputermodelService)cxt.getBean("computermodelService");
		List<ComputermodelFull> objList  = computermodelService.selectComputermodelFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectComputermodelByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputermodelService computermodelService = (ComputermodelService)cxt.getBean("computermodelService");
		Page page = new Page(1,3);

		List<Computermodel> objList  =  computermodelService.selectComputermodelByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectComputermodelFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputermodelService computermodelService = (ComputermodelService)cxt.getBean("computermodelService");
		Page page = new Page(1,3);

		List<ComputermodelFull> objList  =  computermodelService.selectComputermodelFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
