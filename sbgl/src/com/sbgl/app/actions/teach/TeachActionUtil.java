package com.sbgl.app.actions.teach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.Course;
import com.sbgl.app.entity.CourseFull;
import com.sbgl.app.entity.Usergroup;

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
 
}
