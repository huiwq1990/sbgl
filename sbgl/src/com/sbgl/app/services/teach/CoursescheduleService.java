package com.sbgl.app.services.teach;

import java.util.List;

import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.Course;
import com.sbgl.app.entity.Coursecomputer;
import com.sbgl.app.entity.Courseconfig;
import com.sbgl.app.entity.Courseschedule;
import com.sbgl.app.entity.CoursescheduleFull;
import com.sbgl.common.DataError;
import com.sbgl.util.*;

public interface CoursescheduleService{
	
	public void addCourseschedule(Courseschedule courseschedule);

	public void addCourseschedule(Courseschedule ch,Courseschedule en);
	public void addCoursescheduleWithId(Courseschedule courseschedule);
		
	public void updateCourseschedule(Courseschedule courseschedule);
	
//  根据id删除实体	
	public int deleteCourseschedule(Integer coursescheduleId);

//  根据实体删除实体
	public int deleteCourseschedule(Courseschedule courseschedule);
	
//	根据id查询实体类		
	public Courseschedule selectCoursescheduleById(Integer coursescheduleId);

//  查询全部实体
	//public List<Courseschedule> selectCoursescheduleAll();
	
//	根据id查询full类
	public CoursescheduleFull selectCoursescheduleFullById(Integer coursescheduleId);

//  查询全部full
//	public List<CoursescheduleFull> selectCoursescheduleFullAll();
		
//  查询数量
	public int countCoursescheduleRow();
//  分页查询
	public List<Courseschedule> selectCoursescheduleByPage(Page page);
	public List<CoursescheduleFull> selectCoursescheduleFullByPage(Page page);
		
		
	public List<Courseschedule> selectCoursescheduleByCondition(String condition);

	public List<Courseschedule>  selectCoursescheduleByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<CoursescheduleFull> selectCoursescheduleFullByCondition(String condition);
	
	
	// 查询实体full        
    public List<CoursescheduleFull>  selectCoursescheduleFullByConditionAndPage(String condition,final Page page);

	int execSql(String sql);

	



	List<Courseschedule> selectCoursescheduleByWeek(Integer courseId,
			Integer semesterId, Integer weeknum);

	List<Courseschedule> selectCoursescheduleByWeek(Integer semesterId,
			Integer weeknum);

	List<CoursescheduleFull> selectCoursescheduleFullByWeek(Integer semesterId,
			Integer weeknum);

	List<Courseschedule> selectCoursescheduleByPeriod(Integer courseId,
			Integer semesterId, Integer weeknum, Integer day, Integer period);




	void addCourseschedule(Courseconfig currentCourseconfig, int courseid,
			Computerorder computerorder,
			List<Courseschedule> coursescheduleList,
			List<Coursecomputer> coursecomputerList,
			List<Computerorderdetail> computerorderdetailList) throws DataError;

	int deleteCourseschedule(Courseconfig currentCourseconfig,
			int computerorderid, Courseschedule temp);
		
	
	
	
}
