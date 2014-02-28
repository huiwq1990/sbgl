package com.sbgl.app.test.computer;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sbgl.util.DateUtil;

public class TestDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Date d = DateUtil.currentDate();
		Date dd = DateUtil.getDateDayDate(d);
		String dstr= DateUtil.dateFormat(dd, DateUtil.dateformatstr1);
		System.out.println(dstr);
   
		   SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");   
		   System.out.println("今天的日期："+df.format(d));   
		   System.out.println("两天前的日期：" + df.format(new Date(d.getTime() - 2 * 24 * 60 * 60 * 1000)));  
		   System.out.println("三天后的日期：" + df.format(new Date(d.getTime() + 3 * 24 * 60 * 60 * 1000)));
	}

}
