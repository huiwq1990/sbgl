package com.sbgl.app.actions.computer;

import java.util.Date;

import com.sbgl.util.DateUtil;

public class OrderUtil {

	
	/**
	 * 获取可借用的时间段
	 * @param currentDate
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static OrderTime[] getOrderTime(Date currentDate,Date startDate,Date endDate){	
		OrderTime startOrderTime = new OrderTime();
		OrderTime endOrderTime = new OrderTime();
		
		//开始时间大于结束时间，返回空
		if(startDate.compareTo(endDate) > 0){
			endOrderTime = startOrderTime;
			System.out.println("ss");
//			return null;
		}
		//如果结束时间大于当前时间
		if(currentDate.compareTo(endDate) > 0){
			System.out.println("结束时间大于当前时间");
			endOrderTime = startOrderTime;
//			return null;
		}
		
		//现在时间大于开始时间
		if(currentDate.compareTo(startDate) > 0){
			startDate = currentDate;
		}
		
		startOrderTime.setDate(startDate);
		startOrderTime.setDay(DateUtil.getDateDay(startDate));
		startOrderTime.setPeriod(PeriodUtil.getTimePeriod(Integer.valueOf(DateUtil.getDateHour(startDate)), Integer.valueOf(DateUtil.getDateMinute(startDate))));
		
		endOrderTime.setDate(endDate);
		endOrderTime.setDay(DateUtil.getDateDay(endDate));
		endOrderTime.setPeriod(PeriodUtil.getTimePeriod(Integer.valueOf(DateUtil.getDateHour(endDate)), Integer.valueOf(DateUtil.getDateMinute(endDate))));
		
		OrderTime[] orderTime = {startOrderTime,endOrderTime};
		return orderTime ;		
	}
	
	public static void main(String[] args) {
		Date currentDate ,startDate, endDate;
		OrderTime[] orderTime;
		//正常
		currentDate = DateUtil.parseDate("2013-10-10 8:30");
		startDate = DateUtil.parseDate("2013-10-11 8:30");
		endDate = DateUtil.parseDate("2013-10-13 20:30");
		orderTime = getOrderTime(currentDate,startDate,endDate);
		System.out.println(orderTime[0].getDay() + "  " +orderTime[0].getPeriod()+"; "+orderTime[1].getDay() + "  " +orderTime[1].getPeriod());
		
		//开始时间大于当前时间
		currentDate = DateUtil.parseDate("2013-10-11 8:30");
		startDate = DateUtil.parseDate("2013-10-10 8:30");
		endDate = DateUtil.parseDate("2013-10-13 20:30");
		orderTime = getOrderTime(currentDate,startDate,endDate);
		System.out.println(orderTime[0].getDay() + "  " +orderTime[0].getPeriod()+"; "+orderTime[1].getDay() + "  " +orderTime[1].getPeriod());
		
		//当前时间大于预约结束时间段
		currentDate = DateUtil.parseDate("2013-10-14 8:30");
		startDate = DateUtil.parseDate("2013-10-10 8:30");
		endDate = DateUtil.parseDate("2013-10-13 20:30");
		orderTime = getOrderTime(currentDate,startDate,endDate);
		System.out.println(orderTime[0].getDay() + "  " +orderTime[0].getPeriod()+"; "+orderTime[1].getDay() + "  " +orderTime[1].getPeriod());
		
	}
	
}
