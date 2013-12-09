package com.sbgl.app.actions.computer;

public class PeriodUtil {
//	static String[][] workTime ={{"8:00","12:00"},{"1:00","17:00"}};
	static int[][] workTime ={{8,0},{11,30},{13,30},{18,0},{19,0},{21,30},{24,0}};
	public static int getTimePeriod(int hour,int min){
		
		for(int i=0; i<workTime.length;i++){
			if(hour < workTime[i][0] || (hour == workTime[i][0] && min < workTime[i][1])){
				return i+1;
			}
		}
		return 0;
		
	}
	
	public static void main(String[] args) {
		System.out.println(PeriodUtil.getTimePeriod(14, 40));
	}

}
