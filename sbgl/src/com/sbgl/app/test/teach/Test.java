package com.sbgl.app.test.teach;

import java.util.HashMap;

import com.sbgl.app.entity.Courseschedule;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Integer,Integer> h = new HashMap<Integer,Integer>();
		
		h.put(1, 1);
		System.out.println(h.get(1));
		
		
		h.put(1,2);
		System.out.println(h.get(1));
		
		int a = 1;
		HashMap<Integer,HashMap<Integer,Courseschedule>> coursescheduleDayPeriodMap = new HashMap<Integer,HashMap<Integer,Courseschedule>>();
		HashMap<Integer,Courseschedule> peMap = new HashMap<Integer,Courseschedule>();
		coursescheduleDayPeriodMap.put(a, peMap);
		
		int b = 1;
		HashMap<Integer,Courseschedule> peMap2 = coursescheduleDayPeriodMap.get(1);
		coursescheduleDayPeriodMap.put(b, peMap2);
		peMap.put(2, new Courseschedule());
		
		HashMap<Integer,Courseschedule> s = coursescheduleDayPeriodMap.get(1);
		s.put(3, new Courseschedule());
		
		
//		HashMap<Integer,Courseschedule> s2 = coursescheduleDayPeriodMap.get(2);
//		s2.put(3, new Courseschedule());
		
		System.out.println(coursescheduleDayPeriodMap.get(1).size());
		
//		System.out.println(coursescheduleDayPeriodMap.get(2).size());
	}

}
