package com.sbgl.app.actions.computer;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONObject;


import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.commons.beanutils.BeanUtils;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.common.computer.ComputerorderdetailInfo;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.services.computer.ComputerorderdetailService;
import com.sbgl.util.*;

public class ComputerorderActionUtil {
	
	
	
//	构建某一个型号，某一时段 某一天的可借数量         天的长度是可提前预约的天数（预约n天内的PC）
//	computeroderadvanceorderday为真实的computeroderadvanceorderday，不是展示的
	public static 	HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> computermodelPeriodDayInfo(List<Computermodel> computermodelList,int currentPeriod , List<Borrowperiod> borrowperiodList,int computeroderadvanceorderday){

		HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> availableBorrowModelMap = new HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> ();

		
//		初始化每个型号每个时段每天可借数量	
		for(Computermodel tempmodel : computermodelList){
//			Computermodel tempmodel =  computermodelList.get(tempmodelindex);//full list已经赋值
			HashMap<Integer,ArrayList<Integer>> periodDayAvailInfo = new HashMap<Integer,ArrayList<Integer>>();
			
			for(Borrowperiod tempBorrowperiod : borrowperiodList){
				ArrayList<Integer> dayInfo = new ArrayList<Integer>();
				
				//对于今天过去的时间段处理
				int todaynum = 0;
//				System.out.println("period "+tempBorrowperiod.getPeriodnum());
				if(tempBorrowperiod.getPeriodnum() < currentPeriod ){
					todaynum = 0;
				}else{
//					todaynum = tempmodelFull.getComputermodelavailableborrowcountnumber();
					todaynum = tempmodel.getAvailableborrowcountnumber();
				}
				dayInfo.add(todaynum);
				
				
				for(int tempday=1; tempday < computeroderadvanceorderday; tempday++){	
					dayInfo.add( tempmodel.getAvailableborrowcountnumber());
				}				
				periodDayAvailInfo.put(tempBorrowperiod.getPeriodnum(), dayInfo);
			}
//			availableBorrowModelMap.put(tempmodelFull.getComputermodelcomputermodeltype(), periodDayAvailInfo);
			availableBorrowModelMap.put(tempmodel.getComputermodeltype(), periodDayAvailInfo);
		}
		
		return availableBorrowModelMap;
	}
	
	
//	根据可预约map判断用户表单数据能否满足
	public static boolean checkVaildComputerorderForm(HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> availableBorrowModelMap,List<Computerorderdetail> haveOrderedValidComputerorderdetailList,List<Computerorderdetail> newOrderComputerorderdetailList,Date currentDate){
		
//		HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> availableBorrowModelMap = computermodelDao.computermodelPeriodDayInfo(currentPeriod, borrowperiodList, computeroderadvanceorderday);
 
//		List<Computerorderdetail> haveOrderedValidComputerorderdetailList  = computerorderdetailDao.selectValidComputerorderdetailFromStartToEnd(currentDate, currentPeriod, endDate, endPeriod);
//		System.out.println(haveOrderedValidComputerorderdetailList.size());
//		for(int i = 0; i <haveOrderedValidComputerorderdetailList.size(); i++){
//			System.out.println("id="+haveOrderedValidComputerorderdetailList.get(i).getId() + "  " +haveOrderedValidComputerorderdetailList.get(i).getComputermodelid());
//		}
		
		for(Computerorderdetail od : haveOrderedValidComputerorderdetailList){
			int between = DateUtil.daysBetween(currentDate,od.getBorrowday());
//			System.out.println("model: "+od.getComputermodelid()+"; day: "+od.getBorrowday()+"; period: "+od.getBorrowperiod());
			if(!availableBorrowModelMap.containsKey(od.getComputermodelid()) || !availableBorrowModelMap.get(od.getComputermodelid()).containsKey(od.getBorrowperiod()) ){
				return false;
			}
			
			List<Integer> modelDayAvailList = availableBorrowModelMap.get(od.getComputermodelid()).get(od.getBorrowperiod());

			int newcount =modelDayAvailList.get(between) - od.getBorrownumber();
			availableBorrowModelMap.get(od.getComputermodelid()).get(od.getBorrowperiod()).set(between, newcount);
		}
		
		for(Computerorderdetail od : newOrderComputerorderdetailList){
			int between = DateUtil.daysBetween(currentDate,od.getBorrowday());
//			log.info("model: "+od.getComputermodelid()+"; day: "+od.getBorrowday()+"; period: "+od.getBorrowperiod());
			
			if(!availableBorrowModelMap.containsKey(od.getComputermodelid()) || !availableBorrowModelMap.get(od.getComputermodelid()).containsKey(od.getBorrowperiod()) ){
				return false;
			}
			
			List<Integer> modelDayAvailList = availableBorrowModelMap.get(od.getComputermodelid()).get(od.getBorrowperiod());
			if(modelDayAvailList==null || modelDayAvailList.size()==0 || (modelDayAvailList.size()-1)<between){
				return false;
			}
			
			int newcount = modelDayAvailList.get(between) - od.getBorrownumber();
			if(newcount < 0){
				return false;
			}
//			availableBorrowModelMap.get(od.getComputermodelid()).get(od.getBorrowperiod()).set(between, newcount);
		}
		
		return true;
	}
	
	
//	根据未审核的预约和新作业，生成进行中的预约
	public static List<ComputerorderEntity>  setUnderwayComputerorder(List<ComputerorderFull> computerorderFullUnderwayList, List<ComputerhomeworkFull> newComputerhomeworkFullList){
		List<ComputerorderEntity> computerorderEntityList = new ArrayList<ComputerorderEntity>();//进行中的预约
		if(computerorderFullUnderwayList!=null && computerorderFullUnderwayList.size() > 0){
			for(ComputerorderFull h : computerorderFullUnderwayList){
				ComputerorderEntity co = new ComputerorderEntity();
				co.setType(1);
				co.setCreatetime(h.getComputerordercreatetime());
				co.setStarttime(h.getComputerorderorderstarttime());
				co.setEndtime(h.getComputerorderorderendtime());
				try {
					BeanUtils.copyProperties(co, h);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				computerorderEntityList.add(co);
			}
		}
		if(newComputerhomeworkFullList!=null && newComputerhomeworkFullList.size() > 0){
			for(ComputerhomeworkFull h : newComputerhomeworkFullList){
				ComputerorderEntity co = new ComputerorderEntity();
				co.setType(2);
				co.setCreatetime(h.getComputerhomeworkcreatetime());
				co.setStarttime(h.getComputerorderclassruleorderstarttime());
				co.setEndtime(h.getComputerorderclassruleorderendtime());
				try {
					BeanUtils.copyProperties(co, h);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				computerorderEntityList.add(co);
			}
		}
		
//		排序
//		Collections.sort(computerorderEntityList,new ComputerorderEntityComparator());  
		return computerorderEntityList;
	}
	
	
	
}
