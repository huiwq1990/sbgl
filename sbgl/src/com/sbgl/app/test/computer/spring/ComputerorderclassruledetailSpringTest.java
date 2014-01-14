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



import com.sbgl.app.entity.Computerorderclassruledetail;
import com.sbgl.app.entity.ComputerorderclassruledetailFull;
import com.sbgl.app.services.computer.ComputerorderclassruledetailService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class ComputerorderclassruledetailSpringTest {

	public static void main(String[] args) {
//		intTable();
//iniDouble();
//		addComputerorderclassruledetail();
//		selectComputerorderclassruledetailAll();
//		deleteComputerorderclassruledetail();
		
//		updateComputerorderclassruledetail();
//		selectComputerorderclassruledetailById(1L);
//		selectComputerorderclassruledetailFullById(1L);
//      selectComputerorderclassruledetailFullAll();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerorderclassruledetailService computerorderclassruledetailService = (ComputerorderclassruledetailService) cxt.getBean("computerorderclassruledetailService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerorderclassruledetail");
		
		// 删除
		List<Computerorderclassruledetail> computerorderclassruledetailList = computerorderclassruledetailService.selectComputerorderclassruledetailAll();
		if (computerorderclassruledetailList != null) {
			for (int i = 0; i < computerorderclassruledetailList.size(); i++) {
				computerorderclassruledetailService.deleteComputerorderclassruledetail(computerorderclassruledetailList.get(i).getId());
			}
		}


		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computerorderclassruledetail");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				
				computerorderclassruledetailService.addComputerorderclassruledetail(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerorderclassruledetailService computerorderclassruledetailService = (ComputerorderclassruledetailService) cxt.getBean("computerorderclassruledetailService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerorderclassruledetail");
		commonService.iniCode("Computerorderclassruledetailtype");
		// 删除
		List<Computerorderclassruledetail> computerorderclassruledetailList = computerorderclassruledetailService.selectComputerorderclassruledetailAll();
		if (computerorderclassruledetailList != null) {
			for (int i = 0; i < computerorderclassruledetailList.size(); i++) {
				computerorderclassruledetailService.deleteComputerorderclassruledetail(computerorderclassruledetailList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computerorderclassruledetail");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Computerorderclassruledetail ch =	getObj(attrs,dataList.get(i));
				Computerorderclassruledetail en =	getObj(attrs,dataList.get(i+1));
								
				computerorderclassruledetailService.addComputerorderclassruledetail(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Computerorderclassruledetail getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Computerorderclassruledetail.class);
		
		Object obj =  Computerorderclassruledetail.class.newInstance();
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
																method.invoke(obj,Integer.valueOf(datas[2].trim()));
 										}
					
								
		return (Computerorderclassruledetail)obj;		
		
	}
	
	public static void addComputerorderclassruledetail(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderclassruledetailService computerorderclassruledetailService = (ComputerorderclassruledetailService)cxt.getBean("computerorderclassruledetailService");
		
		Computerorderclassruledetail computerorderclassruledetail = new Computerorderclassruledetail() ;
		
		//computerorderclassruledetail.setCreatetime(DateUtil.currentDate());
		 		
		computerorderclassruledetailService.addComputerorderclassruledetail(computerorderclassruledetail);
	}
	
	public static void addComputerorderclassruledetailWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderclassruledetailService computerorderclassruledetailService = (ComputerorderclassruledetailService)cxt.getBean("computerorderclassruledetailService");
	
		Computerorderclassruledetail computerorderclassruledetail = new Computerorderclassruledetail() ;
		 
		//computerorderclassruledetail.setCreatetime(DateUtil.currentDate());
		 		
		computerorderclassruledetailService.addComputerorderclassruledetailWithId(computerorderclassruledetail);
	}
	
	public static void deleteComputerorderclassruledetail(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerorderclassruledetailService computerorderclassruledetailService =(ComputerorderclassruledetailService)cxt.getBean("computerorderclassruledetailService");
		
		computerorderclassruledetailService.deleteComputerorderclassruledetail(id);

	}
	
	public static void updateComputerorderclassruledetail(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerorderclassruledetailService computerorderclassruledetailService = (ComputerorderclassruledetailService)cxt.getBean("computerorderclassruledetailService");
		
		Computerorderclassruledetail computerorderclassruledetail = new Computerorderclassruledetail();
		//获取一个实体，更改它
		computerorderclassruledetail = computerorderclassruledetailService.selectComputerorderclassruledetailById(0);
		//computerorderclassruledetail.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//computerorderclassruledetail.setCreatetime(DateUtil.currentDate());
		
		computerorderclassruledetailService.updateComputerorderclassruledetail(computerorderclassruledetail);
	
	}
	
	public static void selectComputerorderclassruledetailAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderclassruledetailService computerorderclassruledetailService = (ComputerorderclassruledetailService)cxt.getBean("computerorderclassruledetailService");
		List<Computerorderclassruledetail> objList  = computerorderclassruledetailService.selectComputerorderclassruledetailAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectComputerorderclassruledetailById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderclassruledetailService computerorderclassruledetailService = (ComputerorderclassruledetailService)cxt.getBean("computerorderclassruledetailService");
		Computerorderclassruledetail obj  = computerorderclassruledetailService.selectComputerorderclassruledetailById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectComputerorderclassruledetailFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderclassruledetailService computerorderclassruledetailService = (ComputerorderclassruledetailService)cxt.getBean("computerorderclassruledetailService");
		ComputerorderclassruledetailFull obj  = computerorderclassruledetailService.selectComputerorderclassruledetailFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectComputerorderclassruledetailFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderclassruledetailService computerorderclassruledetailService = (ComputerorderclassruledetailService)cxt.getBean("computerorderclassruledetailService");
		List<ComputerorderclassruledetailFull> objList  = computerorderclassruledetailService.selectComputerorderclassruledetailFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectComputerorderclassruledetailByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderclassruledetailService computerorderclassruledetailService = (ComputerorderclassruledetailService)cxt.getBean("computerorderclassruledetailService");
		Page page = new Page(1,3);

		List<Computerorderclassruledetail> objList  =  computerorderclassruledetailService.selectComputerorderclassruledetailByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectComputerorderclassruledetailFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderclassruledetailService computerorderclassruledetailService = (ComputerorderclassruledetailService)cxt.getBean("computerorderclassruledetailService");
		Page page = new Page(1,3);

		List<ComputerorderclassruledetailFull> objList  =  computerorderclassruledetailService.selectComputerorderclassruledetailFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
