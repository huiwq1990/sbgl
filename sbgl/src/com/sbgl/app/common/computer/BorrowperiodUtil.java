package com.sbgl.app.common.computer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.computer.PeriodUtil;
import com.sbgl.app.entity.Borrowperiod;
import com.sbgl.util.DateUtil;

public class BorrowperiodUtil {
	
	static int[][] workTime ={{8,0},{12,00},{13,30},{18,0},{19,0},{22,30},{24,0}};
//	static int[][] workTime ={{12,0},{12,30},{13,30},{18,0},{19,0},{22,30},{24,0}};
	public static int getTimePeriod(int hour,int min){
		
		for(int i=0; i<workTime.length;i++){
			if(hour < workTime[i][0] || (hour == workTime[i][0] && min < workTime[i][1])){
				return i+1;
			}
		}
		return 0;
		
	}
	
	public static int getTimePeriod(Date date){
		
		int hour =Integer.valueOf(DateUtil.getDateHour(date));
		
			int min =Integer.valueOf(DateUtil.getDateMinute(date));
			return getTimePeriod(hour,min);
		
	}
	
	/**
	 * 获取date的时间段
	 * @param date
	 * @return
	 */
	public static int getBorrowTimePeriod(Date date){
		
		
		
		int period = getTimePeriod(date);
	System.out.println(period);
//		List<Borrowperiod> periodList = getBorrowperiodList();
//		for (int i = 0; i < periodList.size(); i++) {
//			if((periodList.get(i).getId() < period) && (periodList.get(i).getId() < period)){
//				return 
//			}
//		}
//		if()
//		if(period <= 6){
			if(period%2 ==0){
				return period;
			}else{
				return period+1;
			}
//		}
		
//		return 0;
	}
	
	
	public static int getMaxPeriod(){
		String currentDay = "2013-10-01 23:59:59";
		
		return BorrowperiodUtil.getBorrowTimePeriod(DateUtil.parseDate(currentDay));
	}
	
	public static void main(String[] args) {
		
		System.out.println(getBorrowperiodByNum(2).getPeroidname());
		
		System.out.println(PeriodUtil.getTimePeriod(13, 00));
		String currentDay = "2013-10-01 23:00:00";
		
		int currentPeriod = BorrowperiodUtil.getBorrowTimePeriod(DateUtil.parseDate(currentDay));
		 System.out.println("currentPeriod: "+currentPeriod);
		 
		 
		 currentDay = "2013-10-01 00:00:00";
		currentPeriod = BorrowperiodUtil.getBorrowTimePeriod(DateUtil.parseDate(currentDay));
		System.out.println("currentDay 的period: "+currentPeriod);
			 
			 
		 
		 
		 System.out.println(getMaxPeriod());
	}

	public static List<Borrowperiod> getBorrowperiodList(int lan){
		if(lan == CommonConfig.languageen){
			return getBorrowperiodListEn();
		}else{
			return getBorrowperiodList();
		}
	}
	
	public static List<Borrowperiod> getBorrowperiodList(){
		
		List<Borrowperiod> periodList = new ArrayList<Borrowperiod>();
		
		Borrowperiod p2 = new Borrowperiod();
		p2.setId(2);
		p2.setPeriodnum(2);
		p2.setPeroidname("上午");
		periodList.add(p2);
		
		Borrowperiod p4 = new Borrowperiod();
		p4.setId(4);
		p4.setPeriodnum(4);
		p4.setPeroidname("下午");
		periodList.add(p4);
		
		Borrowperiod p6 = new Borrowperiod();
		p6.setId(6);
		p6.setPeriodnum(6);
		p6.setPeroidname("晚上");
		periodList.add(p6);
		
		return periodList;
	}
	
	public static List<Borrowperiod> getBorrowperiodListEn(){
		
		List<Borrowperiod> periodList = new ArrayList<Borrowperiod>();
		
		Borrowperiod p2 = new Borrowperiod();
		p2.setId(2);
		p2.setPeriodnum(2);
		p2.setPeroidname("Morning");
		periodList.add(p2);
		
		Borrowperiod p4 = new Borrowperiod();
		p4.setId(4);
		p4.setPeriodnum(4);
		p4.setPeroidname("Afternoon");
		periodList.add(p4);
		
		Borrowperiod p6 = new Borrowperiod();
		p6.setId(6);
		p6.setPeriodnum(6);
		p6.setPeroidname("Evening");
		periodList.add(p6);
		
		return periodList;
	}
	
	
	
	public static Map<Integer,Borrowperiod> getBorrowperiodMap(){
		
		Map<Integer,Borrowperiod> periodMap = new HashMap<Integer,Borrowperiod>();
		List<Borrowperiod> periodList = getBorrowperiodList();
		for(Borrowperiod bp : periodList){
			periodMap.put(bp.getId(), bp);
		}
		
		
		return periodMap;
	}

	public static Borrowperiod getBorrowperiodByNum(int num){
		return getBorrowperiodMap().get(num);
	}
	
	
	public static Map<Integer,Borrowperiod> getBorrowperiodMap(int language){
		
		Map<Integer,Borrowperiod> periodMap = new HashMap<Integer,Borrowperiod>();
		List<Borrowperiod> periodList = getBorrowperiodList(language);
		for(Borrowperiod bp : periodList){
			periodMap.put(bp.getId(), bp);
		}
		
		
		return periodMap;
	}
	public static Borrowperiod getBorrowperiodByNum(int num,int language){
		return getBorrowperiodMap(language).get(num);
	}
}
