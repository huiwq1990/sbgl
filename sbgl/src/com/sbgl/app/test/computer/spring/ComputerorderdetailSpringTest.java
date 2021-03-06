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



import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.services.computer.ComputerorderdetailService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class ComputerorderdetailSpringTest {

	public static void main(String[] args) {
//		intTable();
//iniDouble();
//		addComputerorderdetail();
//		selectComputerorderdetailAll();
//		deleteComputerorderdetail();
		
//		updateComputerorderdetail();
//		selectComputerorderdetailById(1L);
//		selectComputerorderdetailFullById(1L);
//      selectComputerorderdetailFullAll();
		
		auditComputeroder();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerorderdetailService computerorderdetailService = (ComputerorderdetailService) cxt.getBean("computerorderdetailService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerorderdetail");
		
		// 删除
		List<Computerorderdetail> computerorderdetailList = computerorderdetailService.selectComputerorderdetailAll();
		if (computerorderdetailList != null) {
			for (int i = 0; i < computerorderdetailList.size(); i++) {
				computerorderdetailService.deleteComputerorderdetail(computerorderdetailList.get(i).getId());
			}
		}


		try {
			
		
			List<String> dataList = new ArrayList<String>();
			File f = new File( "D:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computerorderdetail");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
			
				computerorderdetailService.addComputerorderdetail(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerorderdetailService computerorderdetailService = (ComputerorderdetailService) cxt.getBean("computerorderdetailService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerorderdetail");
		commonService.iniCode("Computerorderdetailtype");
		// 删除
		List<Computerorderdetail> computerorderdetailList = computerorderdetailService.selectComputerorderdetailAll();
		if (computerorderdetailList != null) {
			for (int i = 0; i < computerorderdetailList.size(); i++) {
				computerorderdetailService.deleteComputerorderdetail(computerorderdetailList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "D:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computerorderdetail");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Computerorderdetail ch =	getObj(attrs,dataList.get(i));
				Computerorderdetail en =	getObj(attrs,dataList.get(i+1));
								
//				computerorderdetailService.addComputerorderdetail(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Computerorderdetail getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Computerorderdetail.class);
		
		Object obj =  Computerorderdetail.class.newInstance();
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
																method.invoke(obj,String.valueOf(datas[7].trim()));
 										}
					
									if(!datas[8].trim().equals("")){
					 method = map.get(attrs[8]);
																method.invoke(obj,Integer.valueOf(datas[8].trim()));
 										}
					
								
		return (Computerorderdetail)obj;		
		
	}
	
	public static void addComputerorderdetail(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderdetailService computerorderdetailService = (ComputerorderdetailService)cxt.getBean("computerorderdetailService");
		
		Computerorderdetail computerorderdetail = new Computerorderdetail() ;
		
		//computerorderdetail.setCreatetime(DateUtil.currentDate());
		 		
		computerorderdetailService.addComputerorderdetail(computerorderdetail);
	}
	
	public static void addComputerorderdetailWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderdetailService computerorderdetailService = (ComputerorderdetailService)cxt.getBean("computerorderdetailService");
	
		Computerorderdetail computerorderdetail = new Computerorderdetail() ;
		 
		//computerorderdetail.setCreatetime(DateUtil.currentDate());
		 		
		computerorderdetailService.addComputerorderdetailWithId(computerorderdetail);
	}
	
	public static void deleteComputerorderdetail(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerorderdetailService computerorderdetailService =(ComputerorderdetailService)cxt.getBean("computerorderdetailService");
		
		computerorderdetailService.deleteComputerorderdetail(id);

	}
	
	public static void updateComputerorderdetail(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerorderdetailService computerorderdetailService = (ComputerorderdetailService)cxt.getBean("computerorderdetailService");
		
		Computerorderdetail computerorderdetail = new Computerorderdetail();
		//获取一个实体，更改它
		computerorderdetail = computerorderdetailService.selectComputerorderdetailById(0);
		//computerorderdetail.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//computerorderdetail.setCreatetime(DateUtil.currentDate());
		
		computerorderdetailService.updateComputerorderdetail(computerorderdetail);
	
	}
	
	public static void selectComputerorderdetailAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderdetailService computerorderdetailService = (ComputerorderdetailService)cxt.getBean("computerorderdetailService");
		List<Computerorderdetail> objList  = computerorderdetailService.selectComputerorderdetailAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectComputerorderdetailById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderdetailService computerorderdetailService = (ComputerorderdetailService)cxt.getBean("computerorderdetailService");
		Computerorderdetail obj  = computerorderdetailService.selectComputerorderdetailById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectComputerorderdetailFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderdetailService computerorderdetailService = (ComputerorderdetailService)cxt.getBean("computerorderdetailService");
		ComputerorderdetailFull obj  = computerorderdetailService.selectComputerorderdetailFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectComputerorderdetailFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderdetailService computerorderdetailService = (ComputerorderdetailService)cxt.getBean("computerorderdetailService");
		List<ComputerorderdetailFull> objList  = computerorderdetailService.selectComputerorderdetailFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}


	
	public static void auditComputeroder(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderdetailService computerorderdetailService = (ComputerorderdetailService)cxt.getBean("computerorderdetailService");
		
		String computerorderId = "1";
		String sql = " where a.computerorderid = "+computerorderId  + " and c.languagetype="+ComputerConfig.languagech ;
		List<ComputerorderdetailFull> computerorderdetailFullList = computerorderdetailService.selectComputerorderdetailFullByCondition(sql);
		
		if(computerorderdetailFullList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < computerorderdetailFullList.size(); i++){
			System.out.println("id="+computerorderdetailFullList.get(i).getComputerorderdetailid() + "  " +computerorderdetailFullList.get(i).getComputermodelname() );
		}
	}
	
//	分页查询	
		public static void selectComputerorderdetailByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderdetailService computerorderdetailService = (ComputerorderdetailService)cxt.getBean("computerorderdetailService");
		Page page = new Page(1,3);

		List<Computerorderdetail> objList  =  computerorderdetailService.selectComputerorderdetailByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectComputerorderdetailFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerorderdetailService computerorderdetailService = (ComputerorderdetailService)cxt.getBean("computerorderdetailService");
		Page page = new Page(1,3);

		List<ComputerorderdetailFull> objList  =  computerorderdetailService.selectComputerorderdetailFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
