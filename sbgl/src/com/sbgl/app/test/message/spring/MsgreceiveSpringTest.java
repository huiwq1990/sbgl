package com.sbgl.app.test.message.spring;

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



import com.sbgl.app.entity.Msgreceive;
import com.sbgl.app.entity.MsgreceiveFull;
import com.sbgl.app.services.message.MsgreceiveService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class MsgreceiveSpringTest {

	public static void main(String[] args) {
//		intTable();
//iniDouble();
//		addMsgreceive();
//		selectMsgreceiveAll();
//		deleteMsgreceive();
		
//		updateMsgreceive();
//		selectMsgreceiveById(1L);
//		selectMsgreceiveFullById(1L);
//      selectMsgreceiveFullAll();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		MsgreceiveService msgreceiveService = (MsgreceiveService) cxt.getBean("msgreceiveService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Msgreceive");
		
		// 删除
		List<Msgreceive> msgreceiveList = msgreceiveService.selectMsgreceiveAll();
		if (msgreceiveList != null) {
			for (int i = 0; i < msgreceiveList.size(); i++) {
				msgreceiveService.deleteMsgreceive(msgreceiveList.get(i).getId());
			}
		}


		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/message"+"/Msgreceive");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				
				msgreceiveService.addMsgreceive(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		MsgreceiveService msgreceiveService = (MsgreceiveService) cxt.getBean("msgreceiveService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Msgreceive");
		commonService.iniCode("Msgreceivetype");
		// 删除
		List<Msgreceive> msgreceiveList = msgreceiveService.selectMsgreceiveAll();
		if (msgreceiveList != null) {
			for (int i = 0; i < msgreceiveList.size(); i++) {
				msgreceiveService.deleteMsgreceive(msgreceiveList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/message"+"/Msgreceive");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Msgreceive ch =	getObj(attrs,dataList.get(i));
				Msgreceive en =	getObj(attrs,dataList.get(i+1));
								
				msgreceiveService.addMsgreceive(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Msgreceive getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Msgreceive.class);
		
		Object obj =  Msgreceive.class.newInstance();
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
																method.invoke(obj,String.valueOf(datas[3].trim()));
 										}
					
									if(!datas[4].trim().equals("")){
					 method = map.get(attrs[4]);
																method.invoke(obj,String.valueOf(datas[4].trim()));
 										}
					
									if(!datas[5].trim().equals("")){
					 method = map.get(attrs[5]);
																method.invoke(obj,Integer.valueOf(datas[5].trim()));
 										}
					
									if(!datas[6].trim().equals("")){
					 method = map.get(attrs[6]);
																method.invoke(obj,DateUtil.parseDate(datas[6].trim()));  
										}
					
									if(!datas[7].trim().equals("")){
					 method = map.get(attrs[7]);
																method.invoke(obj,DateUtil.parseDate(datas[7].trim()));  
										}
					
									if(!datas[8].trim().equals("")){
					 method = map.get(attrs[8]);
																method.invoke(obj,Integer.valueOf(datas[8].trim()));
 										}
					
								
		return (Msgreceive)obj;		
		
	}
	
	public static void addMsgreceive(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MsgreceiveService msgreceiveService = (MsgreceiveService)cxt.getBean("msgreceiveService");
		
		Msgreceive msgreceive = new Msgreceive() ;
		
		//msgreceive.setCreatetime(DateUtil.currentDate());
		 		
		msgreceiveService.addMsgreceive(msgreceive);
	}
	
	public static void addMsgreceiveWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MsgreceiveService msgreceiveService = (MsgreceiveService)cxt.getBean("msgreceiveService");
	
		Msgreceive msgreceive = new Msgreceive() ;
		 
		//msgreceive.setCreatetime(DateUtil.currentDate());
		 		
		msgreceiveService.addMsgreceiveWithId(msgreceive);
	}
	
	public static void deleteMsgreceive(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		MsgreceiveService msgreceiveService =(MsgreceiveService)cxt.getBean("msgreceiveService");
		
		msgreceiveService.deleteMsgreceive(id);

	}
	
	public static void updateMsgreceive(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		MsgreceiveService msgreceiveService = (MsgreceiveService)cxt.getBean("msgreceiveService");
		
		Msgreceive msgreceive = new Msgreceive();
		//获取一个实体，更改它
		msgreceive = msgreceiveService.selectMsgreceiveById(0);
		//msgreceive.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//msgreceive.setCreatetime(DateUtil.currentDate());
		
		msgreceiveService.updateMsgreceive(msgreceive);
	
	}
	
	public static void selectMsgreceiveAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MsgreceiveService msgreceiveService = (MsgreceiveService)cxt.getBean("msgreceiveService");
		List<Msgreceive> objList  = msgreceiveService.selectMsgreceiveAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectMsgreceiveById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MsgreceiveService msgreceiveService = (MsgreceiveService)cxt.getBean("msgreceiveService");
		Msgreceive obj  = msgreceiveService.selectMsgreceiveById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectMsgreceiveFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MsgreceiveService msgreceiveService = (MsgreceiveService)cxt.getBean("msgreceiveService");
		MsgreceiveFull obj  = msgreceiveService.selectMsgreceiveFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectMsgreceiveFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MsgreceiveService msgreceiveService = (MsgreceiveService)cxt.getBean("msgreceiveService");
		List<MsgreceiveFull> objList  = msgreceiveService.selectMsgreceiveFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectMsgreceiveByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MsgreceiveService msgreceiveService = (MsgreceiveService)cxt.getBean("msgreceiveService");
		Page page = new Page(1,3);

		List<Msgreceive> objList  =  msgreceiveService.selectMsgreceiveByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectMsgreceiveFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		MsgreceiveService msgreceiveService = (MsgreceiveService)cxt.getBean("msgreceiveService");
		Page page = new Page(1,3);

		List<MsgreceiveFull> objList  =  msgreceiveService.selectMsgreceiveFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
