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



import com.sbgl.app.entity.Computerhomeworkreceiver;
import com.sbgl.app.entity.ComputerhomeworkreceiverFull;
import com.sbgl.app.services.computer.ComputerhomeworkreceiverService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;

public class ComputerhomeworkreceiverSpringTest {

	public static void main(String[] args) {
//		intTable();
//iniDouble();
//		addComputerhomeworkreceiver();
//		selectComputerhomeworkreceiverAll();
//		deleteComputerhomeworkreceiver();
		
//		updateComputerhomeworkreceiver();
//		selectComputerhomeworkreceiverById(1L);
//		selectComputerhomeworkreceiverFullById(1L);
//      selectComputerhomeworkreceiverFullAll();

	}
	
	
//初始化数据库 将数据库中数据删除 添加新的数据
	public static void intTable() {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerhomeworkreceiverService computerhomeworkreceiverService = (ComputerhomeworkreceiverService) cxt.getBean("computerhomeworkreceiverService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerhomeworkreceiver");
		
		// 删除
		List<Computerhomeworkreceiver> computerhomeworkreceiverList = computerhomeworkreceiverService.selectComputerhomeworkreceiverAll();
		if (computerhomeworkreceiverList != null) {
			for (int i = 0; i < computerhomeworkreceiverList.size(); i++) {
				computerhomeworkreceiverService.deleteComputerhomeworkreceiver(computerhomeworkreceiverList.get(i).getId());
			}
		}


		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computerhomeworkreceiver");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i++){
				
				computerhomeworkreceiverService.addComputerhomeworkreceiver(getObj(attrs,dataList.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void iniDouble(){
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		ComputerhomeworkreceiverService computerhomeworkreceiverService = (ComputerhomeworkreceiverService) cxt.getBean("computerhomeworkreceiverService");
		CommonService commonService = (CommonService) cxt.getBean("commonService");
		// 将计数器置1
		commonService.iniCode("Computerhomeworkreceiver");
		commonService.iniCode("Computerhomeworkreceivertype");
		// 删除
		List<Computerhomeworkreceiver> computerhomeworkreceiverList = computerhomeworkreceiverService.selectComputerhomeworkreceiverAll();
		if (computerhomeworkreceiverList != null) {
			for (int i = 0; i < computerhomeworkreceiverList.size(); i++) {
				computerhomeworkreceiverService.deleteComputerhomeworkreceiver(computerhomeworkreceiverList.get(i).getId());
			}
		}
		
		
		try {
			
			List<String> dataList = new ArrayList<String>();
			File f = new File( "E:/GitHub/sbgl/sbgl/Data"+"/computer"+"/Computerhomeworkreceiver");
			dataList = FileUtils.readLines(f);
			String[] attrs = dataList.get(0).split(",");
			for(int i=1; i < dataList.size();i=i+2){
				
				Computerhomeworkreceiver ch =	getObj(attrs,dataList.get(i));
				Computerhomeworkreceiver en =	getObj(attrs,dataList.get(i+1));
								
				computerhomeworkreceiverService.addComputerhomeworkreceiver(ch,en);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Computerhomeworkreceiver getObj(String[] attrs,String str) throws Exception{
		HashMap<String, Method> map = ReflectUtil.ConverBean(Computerhomeworkreceiver.class);
		
		Object obj =  Computerhomeworkreceiver.class.newInstance();
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
					
								
		return (Computerhomeworkreceiver)obj;		
		
	}
	
	public static void addComputerhomeworkreceiver(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerhomeworkreceiverService computerhomeworkreceiverService = (ComputerhomeworkreceiverService)cxt.getBean("computerhomeworkreceiverService");
		
		Computerhomeworkreceiver computerhomeworkreceiver = new Computerhomeworkreceiver() ;
		
		//computerhomeworkreceiver.setCreatetime(DateUtil.currentDate());
		 		
		computerhomeworkreceiverService.addComputerhomeworkreceiver(computerhomeworkreceiver);
	}
	
	public static void addComputerhomeworkreceiverWithId(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerhomeworkreceiverService computerhomeworkreceiverService = (ComputerhomeworkreceiverService)cxt.getBean("computerhomeworkreceiverService");
	
		Computerhomeworkreceiver computerhomeworkreceiver = new Computerhomeworkreceiver() ;
		 
		//computerhomeworkreceiver.setCreatetime(DateUtil.currentDate());
		 		
		computerhomeworkreceiverService.addComputerhomeworkreceiverWithId(computerhomeworkreceiver);
	}
	
	public static void deleteComputerhomeworkreceiver(Integer id){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerhomeworkreceiverService computerhomeworkreceiverService =(ComputerhomeworkreceiverService)cxt.getBean("computerhomeworkreceiverService");
		
		computerhomeworkreceiverService.deleteComputerhomeworkreceiver(id);

	}
	
	public static void updateComputerhomeworkreceiver(){

		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		ComputerhomeworkreceiverService computerhomeworkreceiverService = (ComputerhomeworkreceiverService)cxt.getBean("computerhomeworkreceiverService");
		
		Computerhomeworkreceiver computerhomeworkreceiver = new Computerhomeworkreceiver();
		//获取一个实体，更改它
		computerhomeworkreceiver = computerhomeworkreceiverService.selectComputerhomeworkreceiverById(0);
		//computerhomeworkreceiver.setName("tq");
		//computer.setCreatetime(DateUtil.currentDate());
		//computerhomeworkreceiver.setCreatetime(DateUtil.currentDate());
		
		computerhomeworkreceiverService.updateComputerhomeworkreceiver(computerhomeworkreceiver);
	
	}
	
	public static void selectComputerhomeworkreceiverAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerhomeworkreceiverService computerhomeworkreceiverService = (ComputerhomeworkreceiverService)cxt.getBean("computerhomeworkreceiverService");
		List<Computerhomeworkreceiver> objList  = computerhomeworkreceiverService.selectComputerhomeworkreceiverAll();
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	public static void selectComputerhomeworkreceiverById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerhomeworkreceiverService computerhomeworkreceiverService = (ComputerhomeworkreceiverService)cxt.getBean("computerhomeworkreceiverService");
		Computerhomeworkreceiver obj  = computerhomeworkreceiverService.selectComputerhomeworkreceiverById(id);
		
		System.out.println("id="+obj.getId());
		
	}
	
	public static void selectComputerhomeworkreceiverFullById(Integer id){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerhomeworkreceiverService computerhomeworkreceiverService = (ComputerhomeworkreceiverService)cxt.getBean("computerhomeworkreceiverService");
		ComputerhomeworkreceiverFull obj  = computerhomeworkreceiverService.selectComputerhomeworkreceiverFullById(id);
		if(obj == null){
			System.out.println("obj is null");
			return;
		}

//		System.out.println("id="+obj.getId());

	}
	
	public static void selectComputerhomeworkreceiverFullAll(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerhomeworkreceiverService computerhomeworkreceiverService = (ComputerhomeworkreceiverService)cxt.getBean("computerhomeworkreceiverService");
		List<ComputerhomeworkreceiverFull> objList  = computerhomeworkreceiverService.selectComputerhomeworkreceiverFullAll();
		if(objList == null){
			System.out.println("objList is null");
			return;
		}
		for(int i = 0; i < objList.size(); i++){
	//		System.out.println("id="+objList.get(i).getId());
		}
	}

//	分页查询	
		public static void selectComputerhomeworkreceiverByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerhomeworkreceiverService computerhomeworkreceiverService = (ComputerhomeworkreceiverService)cxt.getBean("computerhomeworkreceiverService");
		Page page = new Page(1,3);

		List<Computerhomeworkreceiver> objList  =  computerhomeworkreceiverService.selectComputerhomeworkreceiverByPage(page);
		for(int i = 0; i < objList.size(); i++){
			System.out.println("id="+objList.get(i).getId());
		}
	}
	
	//	分页查询	
		public static void selectComputerhomeworkreceiverFullByPage(){
		
		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputerhomeworkreceiverService computerhomeworkreceiverService = (ComputerhomeworkreceiverService)cxt.getBean("computerhomeworkreceiverService");
		Page page = new Page(1,3);

		List<ComputerhomeworkreceiverFull> objList  =  computerhomeworkreceiverService.selectComputerhomeworkreceiverFullByPage(page);
		for(int i = 0; i < objList.size(); i++){
			//System.out.println("id="+objList.get(i).getId());
		}
	}
}
