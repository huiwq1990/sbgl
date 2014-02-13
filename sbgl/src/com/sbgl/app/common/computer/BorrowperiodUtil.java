package com.sbgl.app.common.computer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbgl.app.actions.computer.PeriodUtil;
import com.sbgl.app.entity.Borrowperiod;
import com.sbgl.util.DateUtil;

public class BorrowperiodUtil {
	
	static int[][] workTime ={{8,0},{11,30},{13,30},{18,0},{19,0},{21,30},{24,0}};
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
	
	public static void main(String[] args) {
		System.out.println(PeriodUtil.getTimePeriod(13, 00));
		String currentDay = "2013-10-01 23:00:00";
		
		int currentPeriod = BorrowperiodUtil.getBorrowTimePeriod(DateUtil.parseDate(currentDay));
		 System.out.println("currentPeriod: "+currentPeriod);
	}

	
	
	public static List<Borrowperiod> getBorrowperiodList(){
		
		List<Borrowperiod> periodList = new ArrayList<Borrowperiod>();
		
		Borrowperiod p2 = new Borrowperiod();
		p2.setId(0);
		p2.setPeriodnum(2);
		p2.setPeroidname("上午");
		periodList.add(p2);
		
		Borrowperiod p4 = new Borrowperiod();
		p4.setId(1);
		p4.setPeriodnum(4);
		p4.setPeroidname("下午");
		periodList.add(p4);
		
		Borrowperiod p6 = new Borrowperiod();
		p6.setId(2);
		p6.setPeriodnum(6);
		p6.setPeroidname("晚上");
		periodList.add(p6);
		
		return periodList;
	}
	
	
	public static Map<Integer,Borrowperiod> getBorrowperiodMap(){
		
		Map<Integer,Borrowperiod> periodMap = new HashMap<Integer,Borrowperiod>();
		
		Borrowperiod p = new Borrowperiod();
		p.setId(2);
		p.setPeriodnum(2);
		p.setPeroidname("上午");
		periodMap.put(p.getId(), p);
		
		p.setId(4);
		p.setPeriodnum(4);
		p.setPeroidname("下午");
		periodMap.put(p.getId(), p);
		
		p.setId(6);
		p.setPeriodnum(6);
		p.setPeroidname("晚上");
		periodMap.put(p.getId(), p);
		
		return periodMap;
	}

}
