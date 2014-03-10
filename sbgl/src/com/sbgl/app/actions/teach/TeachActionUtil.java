package com.sbgl.app.actions.teach;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.Course;
import com.sbgl.app.entity.CourseFull;
import com.sbgl.app.entity.Courseconfig;
import com.sbgl.app.entity.Courseschedule;
import com.sbgl.app.entity.CoursescheduleFull;
import com.sbgl.app.entity.Usergroup;
import com.sbgl.util.DateUtil;

public class TeachActionUtil {

	
	public static HashMap<Integer, ArrayList<Course>> couseUsergroupMap(List<Usergroup> usergroupList,List<Course>  courseList){
		//model的分类信息，只显示中文的
//		String categorysqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";
//		List<ComputercategoryFull> tempComputercategoryFullList  = computercategoryService.selectComputercategoryFullByCondition(categorysqlch);
		HashMap<Integer, ArrayList<Course>> courseByIdGroupId = new HashMap<Integer,ArrayList<Course>>();

		for(Course c : courseList){
			int id = c.getId();
			if(!courseByIdGroupId.containsKey(id)){		
				courseByIdGroupId.put(id, new ArrayList<Course>());
			}
			courseByIdGroupId.get(id).add(c);			
		}
		
		
		for(Usergroup ug : usergroupList){
			int id = ug.getId();
			if(!courseByIdGroupId.containsKey(id)){
				courseByIdGroupId.put(id, new ArrayList<Course>());
			}
		}
		
		return courseByIdGroupId;
	}

	
	public static HashMap<Integer, ArrayList<CourseFull>> couseFullUsergroupMap(List<Usergroup> usergroupList,List<CourseFull>  courseFullList){
		//model的分类信息，只显示中文的
//		String categorysqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";
//		List<ComputercategoryFull> tempComputercategoryFullList  = computercategoryService.selectComputercategoryFullByCondition(categorysqlch);
		HashMap<Integer, ArrayList<CourseFull>> courseByIdGroupId = new HashMap<Integer,ArrayList<CourseFull>>();

		for(CourseFull c : courseFullList){
			int cousegroup = c.getCoursetype();
			if(!courseByIdGroupId.containsKey(cousegroup)){		
				courseByIdGroupId.put(cousegroup, new ArrayList<CourseFull>());
			}
			courseByIdGroupId.get(cousegroup).add(c);			
		}
		
		
		for(Usergroup ug : usergroupList){
			int id = ug.getId();
			if(!courseByIdGroupId.containsKey(id)){
				courseByIdGroupId.put(id, new ArrayList<CourseFull>());
			}
		}
		
		return courseByIdGroupId;
	}
	
	
	public static List<Integer> getDayList(){
		 List<Integer> dayList = new ArrayList<Integer>();
		 for(int i = 1; i <=7; i++){
			 dayList.add(i);
		 }
		 
		 return dayList;
	}
	
	
	
	
	public static HashMap<Integer,HashMap<Integer,ArrayList<CoursescheduleFull>>>  coursescheduleFullPeriodDayMapList(List<CoursescheduleFull> coursescheduleList){
		HashMap<Integer,HashMap<Integer,ArrayList<CoursescheduleFull>>> courseschedulePeriodDayMapList = new HashMap<Integer,HashMap<Integer,ArrayList<CoursescheduleFull>>>();

		for(CoursescheduleFull cs : coursescheduleList){
			int period = cs.getCoursescheduleperiod();
			int day = cs.getCoursescheduleday();
			boolean containperiod = courseschedulePeriodDayMapList.containsKey(period);
			if(containperiod == true){
				
			}else{
				HashMap<Integer,ArrayList<CoursescheduleFull>> peMap = new HashMap<Integer,ArrayList<CoursescheduleFull>>();
				courseschedulePeriodDayMapList.put(period, peMap);
			}
			

			
			
			ArrayList<CoursescheduleFull> tempList = courseschedulePeriodDayMapList.get(period).get(day);
			
			if(courseschedulePeriodDayMapList.get(period).get(day) == null){
				ArrayList<CoursescheduleFull> newList = new ArrayList<CoursescheduleFull>();
				newList.add(cs);
				
				 courseschedulePeriodDayMapList.get(period).put(day, newList);
			}else{
				boolean contain = false;
				for(CoursescheduleFull temp : tempList){
					if(temp.getCourseid().intValue() == cs.getCourseid().intValue()){
						contain = true;
					}
				}
				if(!contain){
					courseschedulePeriodDayMapList.get(period).get(day).add(cs);
				}

			}
			
					
						
		}
		
		return courseschedulePeriodDayMapList;
	}
	
	
	public static HashMap<Integer,HashMap<Integer,ArrayList<Courseschedule>>>  courseschedulePeriodDayMapList(List<Courseschedule> coursescheduleList){
		HashMap<Integer,HashMap<Integer,ArrayList<Courseschedule>>> courseschedulePeriodDayMapList = new HashMap<Integer,HashMap<Integer,ArrayList<Courseschedule>>>();

		for(Courseschedule cs : coursescheduleList){
			boolean period = courseschedulePeriodDayMapList.containsKey(cs.getPeriod());
			if(period == true){
				
			}else{
				HashMap<Integer,ArrayList<Courseschedule>> peMap = new HashMap<Integer,ArrayList<Courseschedule>>();
				courseschedulePeriodDayMapList.put(cs.getPeriod(), peMap);
			}
			
			ArrayList<Courseschedule> tempList = courseschedulePeriodDayMapList.get(cs.getPeriod()).get(cs.getDay());
			boolean contain = false;
			for(Courseschedule temp : tempList){
				if(temp.getId() == cs.getId()){
					contain = true;
				}
			}
			
			if(!contain){
				courseschedulePeriodDayMapList.get(cs.getPeriod()).get(cs.getDay()).add(cs);
			}
			
		}
		
		return courseschedulePeriodDayMapList;
	}

	
	
	public static HashMap<Integer,HashMap<Integer,Courseschedule>> setCourseschedulePeriodDayMap(List<Courseschedule> coursescheduleList){
		HashMap<Integer,HashMap<Integer,Courseschedule>> courseschedulePeriodDayMap = new HashMap<Integer,HashMap<Integer,Courseschedule>>();
		
//		课程，学期，周，天，日，时间段
//		HashMap<Integer,HashMap<Integer,Courseschedule>> dayPap = new HashMap<Integer,HashMap<Integer,Courseschedule>>();
		for(Courseschedule cs : coursescheduleList){
			boolean period = courseschedulePeriodDayMap.containsKey(cs.getPeriod());
			if(period == true){
				
			}else{
				HashMap<Integer,Courseschedule> peMap = new HashMap<Integer,Courseschedule>();
				courseschedulePeriodDayMap.put(cs.getPeriod(), peMap);
			}

			HashMap<Integer,Courseschedule> temp = courseschedulePeriodDayMap.get(cs.getPeriod());
			Courseschedule copy = new Courseschedule();
//			HashMap<Integer,Courseschedule> s = coursescheduleDayPeriodMap.get(1);
			temp.put(cs.getDay(), copy);
//			temp.put(4, new Courseschedule());
//			if(temp == null){
//				System.out.println("sssds");
//			}
//			Courseschedule copy = new Courseschedule();
//			try {
//				BeanUtils.copyProperties(copy, cs);
//			} catch (IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		}
		return courseschedulePeriodDayMap;

	}
	
	public static Date getSemesterDay(Date firstDay,int week,int day){
		Date newDate = DateUtil.addDay(firstDay, (week-1)*7 + day-1);
		return DateUtil.getDateDayDate(newDate);
	}

}
