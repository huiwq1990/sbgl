package com.sbgl.app.actions.order;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.entity.Course;
import com.sbgl.app.entity.CourseFull;
import com.sbgl.app.services.teach.CourseService;


@Scope("prototype") 
@Controller("OrderTeachingAction")
public class OrderTeachingAction extends ActionSupport   {
	
	private static final Log log = LogFactory.getLog(OrderTeachingAction.class);
	
	@Resource
	private CourseService courseService;
	private Integer courseid; //entity full 的id属性名称		
	private Course course = new Course();//实例化一个模型
	private Course courseModel = new Course();//实例化一个模型
	private CourseFull courseFull = new CourseFull();//实例化一个模型
	private List<Course> courseList = new ArrayList<Course>();
	private List<CourseFull> courseFullList = new ArrayList<CourseFull>();
	
	
	
	public String  teachingArrangement(){
		
		return SUCCESS;
	}
	

}	
