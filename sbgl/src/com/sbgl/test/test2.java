package com.sbgl.test;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sbgl.app.entity.Equipmentdetail;

public class test2 {
	public static void main(String[] args) {
		try{
			String s1 = "2012-02-01";
			String s2 = "2012-04-04";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date   begin=sdf.parse(s1);  
			Date   end=sdf.parse(s2); 
			System.out.println(sdf1.format(begin)); 
			System.out.println(sdf1.format(end)); 
			double   between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒    
			double  day=between/(24*3600);	
			for(int i = 1;i<=day;i++){
				Calendar cd = Calendar.getInstance();   
				cd.setTime(sdf.parse(s1));   
				cd.add(Calendar.DATE, i);//增加一天   
				System.out.println(sdf.format(cd.getTime()));
	        }  
		}catch(Exception e){
			
		}
	}
}
