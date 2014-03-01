package com.sbgl.app.services.teach.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computerorderclassrule;
import com.sbgl.app.entity.Course;
import com.sbgl.app.entity.CourseFull;
import com.sbgl.app.entity.Courseschedule;
import com.sbgl.app.services.teach.CourseService;
import com.sbgl.app.actions.orderadmin.OrderCountFull;
import com.sbgl.app.dao.ComputerorderclassruleDao;
import com.sbgl.app.dao.CourseDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.CoursescheduleDao;
import com.sbgl.app.dao.OrderAdminDao;
import com.sbgl.common.DataError;
import com.sbgl.util.*;

import javax.annotation.Resource;

import org.jfree.util.Log;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private CourseDao courseDao;
	
	@Resource
	private ComputerorderclassruleDao computerorderclassruleDao;
	
	@Resource
	private CoursescheduleDao coursescheduleDao;
	
	@Resource
	private  OrderAdminDao orderAdminDao;
	
	
//	@Resource
//	private CourseDao courseDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addCourse(Course course){
		course.setId(baseDao.getCode("Course"));
		baseDao.saveEntity(course);		
	}
	
	@Override
	public void addCourse(Course ch,Course en){
		

		int type = baseDao.getCode("Coursetype");
		ch.setId(baseDao.getCode("Course"));
		ch.setCoursetype(type);
		en.setId(baseDao.getCode("Course"));
		en.setCoursetype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		

	}

	@Override
	public void addCourseWithId(Course course){
	
		baseDao.saveEntity(course);		
	}

//  根据id删除实体	
	@Override
	public int deleteCourse(Integer courseId){
		Course course = new Course();
		course.setId(courseId);
		//return courseDao.deleteEntity(courseId);	
		//baseDao.deleteEntityById(Course.class,courseId);
		baseDao.deleteEntity(course);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteCourse(Course course) {
		return deleteCourse(course.getId());
	}
	
	
	@Override
	public int deleteCourse(List<Integer> delCourseTypeList) throws DataError {
		
		for(Integer cType : delCourseTypeList){
			
			List<Computerorderclassrule> corList = computerorderclassruleDao.selectComputerorderclassruleByCondition(" where classid = "+cType);
			
			List<Courseschedule> csList = coursescheduleDao.selectCoursescheduleByCondition(" where courseid = "+cType);
			
			OrderCountFull ocf = orderAdminDao.findOrderCountRule(cType);
			
			if( (corList == null || corList.size() ==0) && (csList == null || csList.size() ==0) &&  (ocf.getOrderCount1()== 0 ) ){
//				System.out.println("课程没有被使用");
			}else{
				throw new DataError("课程已经被使用");
			}
		
			 baseDao.deleteByProperty("Course","coursetype", cType);
//			baseDao.createSQL("delete from course where coursetype = "+cid);
			
		}
		
		return 1;
		
		
		
	}


	
	@Override
	public void updateCourse(Course course){
		
		Course tempCourse = new Course();
		//tempCourse = baseDao.getEntityById(Course.class, course.getId());
		tempCourse = course;
		//add your code here
		
		
		baseDao.updateEntity(tempCourse);

	}
	
	
	
	@Override
	public boolean updateCourse(Course ch,Course en){
		
		baseDao.updateEntity(ch);
		baseDao.updateEntity(en);
		
		return true;		

	}

//	根据id查询实体类			
	@Override
	public Course selectCourseById(Integer courseId){		
		return baseDao.getEntityById(Course.class, courseId);
	}
	
//	@Override
//	public List<Course> selectCourseAll(){
//			
//		return baseDao.getAllEntity(Course.class);
//		
//	}
	
//	根据id查询full类
	@Override
	public CourseFull selectCourseFullById(Integer courseId){
		String sql = "where a.id=" + courseId;
		List<CourseFull> temp = courseDao.selectCourseFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	

	public int countCourseRow(){
		return baseDao.getRowCount(Course.class);
	}
		
//  分页查询
	public List<Course> selectCourseByPage(Page page){	
		return baseDao.selectByPage(Course.class,page);
	}
//  分页查询full	
	public List<CourseFull> selectCourseFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Course.class));
		return courseDao.selectCourseFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Course> selectCourseByCondition(String condition) {
		 return courseDao.selectCourseByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
	@Override
	public List<Course>  selectCourseByConditionAndPage(String condition,final Page page) {
		return courseDao.selectCourseByConditionAndPage(condition,page);
	}
	
	
	//条件查询full
	@Override
	public List<CourseFull> selectCourseFullByCondition(String condition) {
		return courseDao.selectCourseFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<CourseFull>  selectCourseFullByConditionAndPage(String condition,final Page page) {
			return courseDao.selectCourseFullByConditionAndPage(condition, page);
		}
	


}
