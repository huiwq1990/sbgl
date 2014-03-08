package com.sbgl.app.services.teach.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.Course;
import com.sbgl.app.entity.Coursecomputer;
import com.sbgl.app.entity.Coursecomputerorder;
import com.sbgl.app.entity.Courseconfig;
import com.sbgl.app.entity.Courseschedule;
import com.sbgl.app.entity.CoursescheduleFull;
import com.sbgl.app.entity.Courseschedulecomputerorder;
import com.sbgl.app.services.teach.CoursescheduleService;
import com.sbgl.app.actions.teach.CoursescheduleAction;
import com.sbgl.app.actions.teach.TeachActionUtil;
import com.sbgl.app.actions.teach.TeachConstant;
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.dao.ComputerorderDao;
import com.sbgl.app.dao.ComputerorderdetailDao;
import com.sbgl.app.dao.CoursecomputerDao;
import com.sbgl.app.dao.CoursecomputerorderDao;
import com.sbgl.app.dao.CoursescheduleDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.CourseschedulecomputerorderDao;
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
	private CourseschedulecomputerorderDao courseschedulecomputerorderDao;
	
	@Resource
	private CoursecomputerDao coursecomputerDao;
	
	@Resource
	private ComputerorderDao computerorderDao;
	
	@Resource
	private ComputerorderdetailDao computerorderdetailDao;
	
	@Resource
	private CoursecomputerorderDao coursecomputerorderDao;
	
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
	
	/**
	 * 在课程安排添加界面，批量添加
	 */	
	@Override
	public void addCourseschedule(Courseconfig currentCourseconfig, int courseid,Computerorder computerorder,List<Courseschedule> coursescheduleList,List<Coursecomputer> coursecomputerList,List<Computerorderdetail> computerorderdetailList) throws DataError{
	
		
//		创建信息的订单,如果本学期这个课程已经创建预约，则仍用这个预约，如果没有则新建一个
		int computerorderid =0;
		Coursecomputerorder coursecomputerorder = coursecomputerorderDao.selectBySemesterCourse(currentCourseconfig.getId(), courseid);
		Computerorder tempcomputerorder = new Computerorder();
		if(coursecomputerorder == null){
			try {
				BeanUtils.copyProperties(tempcomputerorder, computerorder);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new DataError("");
			}	
			tempcomputerorder.setId(baseDao.getCode("Computerorder"));
			baseDao.saveEntity(tempcomputerorder);
			
			computerorderid = tempcomputerorder.getId();
			
			
//			保存订单课程关系
			Coursecomputerorder newcoursecomputerorder = new Coursecomputerorder();
			newcoursecomputerorder.setComputerorderid(computerorderid);
			newcoursecomputerorder.setCourseid(courseid);
			newcoursecomputerorder.setSemesterid(currentCourseconfig.getId());
			
			newcoursecomputerorder.setId(baseDao.getCode("Coursecomputerorder"));
			baseDao.saveEntity(newcoursecomputerorder);
			
		}else{
			computerorderid = coursecomputerorder.getComputerorderid();
		}
		
		
		
		for(Courseschedule temp : coursescheduleList){

//			查询已经添加过的记录，结果做多只有一条
//			addedCoursescheduleList = new ArrayList<Courseschedule>();
			String addedsql = "   where status = "+TeachConstant.courseschedulevalidstatus+" and courseid = "+temp.getCourseid()+" and semester = "+temp.getSemester()+" and week="+temp.getWeek()+" and day = "+temp.getDay()+"  and period = "+temp.getPeriod();
			List<Courseschedule> addedCoursescheduleList = coursescheduleDao.selectCoursescheduleByCondition(addedsql);
			
			log.info("是否已经预约："+addedCoursescheduleList.size());
//			如果这节课已经添加 即已经预约，需要删除预约
			if(addedCoursescheduleList!=null && addedCoursescheduleList.size() > 0){
				Courseschedule added = addedCoursescheduleList.get(0);
				int csid = addedCoursescheduleList.get(0).getId();
//				删除已经添加
				coursescheduleDao.delCoursescheduleByPeriod(addedCoursescheduleList.get(0));
				coursecomputerDao.delCoursecomputerByCourseschedule(csid);
				
				
				log.info("coursescheduleList.get(0).getId()"+csid);
//				查询课程的机房
//				List<Courseschedulecomputerorder> cscoList = courseschedulecomputerorderDao.selectByCoursescheduleid(csid);
//		         if(cscoList !=null && cscoList.size()==1){	            	 
//		         }else{
//		        	 throw new DataError("获取课程安排订单序号出错");
//		         }
		         /*
//		      	根据订单号删除订单
				computerorderDao.delById(cscoList.get(0).getComputercoursescheduleid());
//				根据订单号删除订单详情
				computerorderdetailDao.delByComputerorderid(cscoList.get(0).getComputercoursescheduleid());
				*/
		         
//		         如果课程安排的机房已经添加，则它已经预约了器材。现在重新预约这个时段的器材，所以它的预约详情需要删除
//		         要删除的课程安排信息
//				int courseid = added.getCourseid();
				
				Date borrowday = TeachActionUtil.getSemesterDay(currentCourseconfig.getFirstday(),added.getWeek(),added.getDay());
				String bow = DateUtil.dateFormat(DateUtil.parseDate(DateUtil.getDateDay(borrowday)), DateUtil.dateformatstr1);
				computerorderdetailDao.delByPeriod(computerorderid,bow, added.getPeriod());
				
				log.info("课程信息已经添加");
			}

			

			
//			新添加课程表
			Courseschedule newcs = new Courseschedule();			
			try {
				BeanUtils.copyProperties(newcs, temp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new DataError("");
			}	
			newcs.setId(baseDao.getCode("Courseschedule"));
			newcs.setStatus(TeachConstant.courseschedulevalidstatus);
			baseDao.saveEntity(newcs);		
			
//			添加课程安排的机房使用信息
			for(Coursecomputer cc : coursecomputerList){
				
				
				computerorderdetailDao.delByPeriodComputermodeltype(borrowday, period, computermodeltype);
				
				
				int c = baseDao.getCode("Coursecomputer");
				Coursecomputer ccnew = new Coursecomputer();
				
				try {
					BeanUtils.copyProperties(ccnew, cc);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new DataError("");
				}	
				ccnew.setId(c);
				ccnew.setLessonid(newcs.getId());
				ccnew.setStatus(TeachConstant.courseschedulevalidstatus);
				baseDao.saveEntity(ccnew);
			}
			
			

		}
	




//	添加预约详情
		
		for(Computerorderdetail cod : computerorderdetailList){
			cod.setComputerorderid(computerorderid);
			cod.setId(baseDao.getCode("Computerorderdetail"));
			baseDao.saveEntity(cod);
		}
		
//		删除这个时段其他人的预约

		
	}
	
	


//  根据id删除实体	
	@Override
	public int deleteCourseschedule(Courseschedule temp){
		
//		删除课程
		coursescheduleDao.delCoursescheduleByPeriod(temp);
		
		
//		删除课程借用机器
		coursecomputerDao.delCoursecomputerByCourseschedule(temp.getId());
		
		
		return 1;
	}


//  删除课程安排小节，删除借用机器，删除预约
	@Override
	public int deleteCourseschedule(Courseconfig currentCourseconfig,int computerorderid,Courseschedule temp){
//		Coursecomputerorder coursecomputerorder = coursecomputerorderDao.selectBySemesterCourse(currentCourseconfig.getId(), temp.getCourseid());
//		
//		if()
		
//		删除课程安排
		coursescheduleDao.delCoursescheduleByPeriod(temp);
		
		
//		删除课程借用机器
		coursecomputerDao.delCoursecomputerByCourseschedule(temp.getId());
		
		Date borrowday = TeachActionUtil.getSemesterDay(currentCourseconfig.getFirstday(),temp.getWeek(),temp.getDay());
		String bow = DateUtil.dateFormat(DateUtil.parseDate(DateUtil.getDateDay(borrowday)), DateUtil.dateformatstr1);
		computerorderdetailDao.delByPeriod(computerorderid,bow, temp.getPeriod());
		
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
	public List<Courseschedule> selectCoursescheduleByPeriod(Integer courseId,Integer semesterId,Integer weeknum,Integer day,Integer period){
		return	coursescheduleDao.selectCoursescheduleByPeriod(courseId, semesterId, weeknum, day, period);	
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
	
	/**
	 * 查询某个学期某周的全部课程
	 */
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
