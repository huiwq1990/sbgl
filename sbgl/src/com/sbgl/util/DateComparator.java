package com.sbgl.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class DateComparator implements Comparator{  
	public int compare(Object obj1, Object obj2) {  
        Date begin=(Date)obj1;  
        Date end=(Date)obj2;  
        if(begin.after(end)){  
            return 1;  
        }  
        else{  
            return -1;  
        }  
	}  
	
	public static void main(String[] args) {  
        List list=new ArrayList();  
        for (int i = 0; i < 5; i++) {  
            Date d=new Date(System.currentTimeMillis()+(int)(Math.random()*1000000000));  
            list.add(d);  
            System.out.println(d);  
        }  
        DateComparator c=new DateComparator();  
        Collections.sort(list,c);  
        System.err.println(list.get(0));  
        System.err.println(list.get(list.size()-1));  
    }  
}