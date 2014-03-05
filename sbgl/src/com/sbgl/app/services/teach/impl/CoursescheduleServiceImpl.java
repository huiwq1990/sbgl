package com.sbgl.app.services.teach.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Coursecomputer;
import com.sbgl.app.entity.Courseschedule;
import com.sbgl.app.entity.CoursescheduleFull;
import com.sbgl.app.services.teach.CoursescheduleService;
import com.sbgl.app.actions.teach.CoursescheduleAction;
import com.sbgl.app.actions.teach.TeachConstant;
import com.sbgl.app.dao.CoursecomputerDao;
import com.sbgl.app.dao.CoursescheduleDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.common.DataError;
import com.sbgl.util.*;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("coursescheduleService")
@Transactional
public class CoursescheduleServiceImpl implements CoursescheduleService{
	
	private static final Log log = LogFactory.getLog(CoursescheduleServiceImpl.class);

	
	@Resource
	private BaseDao baseDao;
	@Resource
	private CoursescheduleDao coursescheduleDao;
	
	@Resource
	private CoursecomputerDao coursecomputerDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addCourseschedule(Courseschedule courseschedule){
		courseschedule.setId(baseDao.getCode("Courseschedule"));
		baseDao.saveEntity(courseschedule);		
	}
	
	
	
	@Override
	public void addCourseschedule(Courseschedule ch,Courseschedule en){
		
	/*
		int type = baseDao.getCode("Coursescheduletype");
		ch.setId(baseDao.getCode("Courseschedule"));
		ch.setCoursescheduletype(type);
		en.setId(baseDao.getCode("Courseschedule"));
		en.setCoursescheduletype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addCoursescheduleWithId(Courseschedule courseschedule){
	
		baseDao.saveEntity(courseschedule);		
	}
	
	
	@Override
	public void addCourseschedule(List<Courseschedule> coursescheduleList,List<Coursecomputer> coursecomputerList) throws DataError{
	
		for(Courseschedule temp : coursescheduleList){

//			String sql = " update Courseschedule set status = "+TeachConstant.coursescheduledel+" where courseid = "+temp.getCourseid()+" and semester = "+temp.getSemester()+" and week="+temp.getWeek()+" and day = "+temp.getDay()+"  and period = "+temp.getPeriod();
//			coursescheduleService.execSql(sql);
//			查询已经添加过的记录
			List<Courseschedule> addedCoursescheduleList = new ArrayList<Courseschedule>();
			String addedsql = "   where status = "+TeachConstant.courseschedulevalidstatus+" and courseid = "+temp.getCourseid()+" and semester = "+temp.getSemester()+" and week="+temp.getWeek()+" and day = "+temp.getDay()+"  and period = "+temp.getPeriod();
			addedCoursescheduleList = coursescheduleDao.selectCoursescheduleByCondition(addedsql);
			
			
			if(addedCoursescheduleList!=null && addedCoursescheduleList.size() > 0){
//				删除已经添加
				coursescheduleDao.delCoursescheduleByCondition(temp);
				coursecomputerDao.delCoursecomputerByCourseschedule(temp.getId());
				log.info("课程信息已经添加");
			}

			

			
//			新添加课程表
			Courseschedule en = new Courseschedule();
			
			try {
				BeanUtils.copyProperties(en, temp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new DataError("");
			}	
			en.setId(baseDao.getCode("Courseschedule"));
			en.setStatus(TeachConstant.courseschedulevalidstatus);
			baseDao.saveEntity(en);		
			
//			添加课程机房使用信息
			for(Coursecomputer cc : coursecomputerList){
				int c = baseDao.getCode("Coursecomputer");
				Coursecomputer ccnew = new Coursecomputer();
				
				try {
					BeanUtils.copyProperties(ccnew, cc);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new DataError("");
				}	
				ccnew.setId(c);
				ccnew.setLessonid(en.getId());
				ccnew.setStatus(TeachConstant.courseschedulevalidstatus);
				baseDao.saveEntity(ccnew);
			}
			
		}
		
	}
	
	


//  根据id删除实体	
	@Override
	public int deleteCourseschedule(Courseschedule temp){
//		删除课程
		coursescheduleDao.delCoursescheduleByCondition(temp);
		
//		删除课程借用机器
		coursecomputerDao.delCoursecomputerByCourseschedule(temp.getId());
		
		
		return 1;
	}



	
	@Override
	public void updateCourseschedule(Courseschedule courseschedule){
		
		Courseschedule tempCourseschedule = new Courseschedule();
		//tempCourseschedule = baseDao.getEntityById(Courseschedule.class, courseschedule.getId());
		tempCourseschedule = courseschedule;
		//add your code here
		
		
		baseDao.updateEntity(tempCourseschedule);

	}
	
	/**
	 * 获取某个课程某学期某周的课程
	 * @param coursescheduleId
	 * @return
	 */
	@Override
	public List<Courseschedule> selectCoursescheduleByWeek(Integer courseId,Integer semesterId,Integer weeknum){
		List<Courseschedule> coursescheduleList = new ArrayList<Courseschedule>();
		String addedsql = "   where status = "+TeachConstant.courseschedulevalidstatus+" and courseid = "+courseId+" and semester = "+semesterId+" and week="+weeknum;
		coursescheduleList = coursescheduleDao.selectCoursescheduleByCondition(addedsql);
		
		return coursescheduleList;
		
	}
	
	
	/**
	 * 获取所有课程某学期某周的课程
	 * @param coursescheduleId
	 * @return
	 */
	@Override
	public List<Courseschedule> selectCoursescheduleByWeek(Integer semesterId,Integer weeknum){
		List<Courseschedule> coursescheduleList = new ArrayList<Courseschedule>();
		String addedsql = "   where status = "+TeachConstant.courseschedulevalidstatus+" and semester = "+semesterId+" and week="+weeknum;
		coursescheduleList = coursescheduleDao.selectCoursescheduleByCondition(addedsql);
		
		return coursescheduleList;
		
	}
	
	@Override
	public List<CoursescheduleFull> selectCoursescheduleFullByWeek(Integer semesterId,Integer weeknum){
		List<CoursescheduleFull> coursescheduleList = new ArrayList<CoursescheduleFull>();
		String addedsql = "   where a.status = "+TeachConstant.courseschedulevalidstatus+" and a.semester = "+semesterId+" and a.week="+weeknum;
		coursescheduleList = coursescheduleDao.selectCoursescheduleFullByCondition(addedsql);
		
		return coursescheduleList;
		
	}
	

//	根据id查询实体类			
	@Override
	public Courseschedule selectCoursescheduleById(Integer coursescheduleId){		
		return baseDao.getEntityById(Courseschedule.class, coursescheduleId);
	}
	
	/*
	@Override
	public List<Courseschedule> selectCoursescheduleAll(){
			
		return baseDao.getAllEntity(Courseschedule.class);
		
	}
	*/
	
//	根据id查询full类
	@Override
	public CoursescheduleFull selectCoursescheduleFullById(Integer coursescheduleId){
		String sql = "where a.id=" + coursescheduleId;
		List<CoursescheduleFull> temp = coursescheduleDao.selectCoursescheduleFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	

	public int countCoursescheduleRow(){
		return baseDao.getRowCount(Courseschedule.class);
	}
		
//  分页查询
	public List<Courseschedule> selectCoursescheduleByPage(Page page){	
		return baseDao.selectByPage(Courseschedule.class,page);
	}
//  分页查询full	
	public List<CoursescheduleFull> selectCoursescheduleFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Courseschedule.class));
		return coursescheduleDao.selectCoursescheduleFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Courseschedule> selectCoursescheduleByCondition(String condition) {
		 return coursescheduleDao.selectCoursescheduleByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
	@Override
	public List<Courseschedule>  selectCoursescheduleByConditionAndPage(String condition,final Page page) {
		return coursescheduleDao.selectCoursescheduleByConditionAndPage(condition,page);
	}
	
	
	//条件查询full
	@Override
	public List<CoursescheduleFull> selectCoursescheduleFullByCondition(String condition) {
		return coursescheduleDao.selectCoursescheduleFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<CoursescheduleFull>  selectCoursescheduleFullByConditionAndPage(String condition,final Page page) {
			return coursescheduleDao.selectCoursescheduleFullByConditionAndPage(condition, page);
		}
	

    	@Override
    	public int execSql(String sql) {
//    		String sql = "delete from Computerorderdetail " + condition;
    		baseDao.createSQL(sql);
    		return 1;
    		
    	}



		@Override
		public int deleteCourseschedule(Integer coursescheduleId) {
			// TODO Auto-generated method stub
			return 0;
		}
}
