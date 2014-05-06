package com.sbgl.app.actions.teach;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONObject;


import javax.annotation.Resource;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.commons.beanutils.BeanUtils;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.computer.ComputerActionUtil;
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.actions.util.PageActionUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.orderadmin.OrderAdminService;
import com.sbgl.app.services.teach.CourseService;
import com.sbgl.app.services.user.GroupService;
import com.sbgl.app.services.user.TeacherService;
import com.sbgl.common.DataError;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("CourseAction")
public class CourseAction extends BaseAction implements ModelDriven<Course>{
	
	private static final Log log = LogFactory.getLog(CourseAction.class);


	
	//Service	
	@Resource
	private CourseService courseService;
	private Integer courseid; //entity full 的id属性名称		
	private Course course = new Course();//实例化一个模型
	private Course courseModel = new Course();//实例化一个模型
	private CourseFull courseFull = new CourseFull();//实例化一个模型
	private List<Course> courseList = new ArrayList<Course>();
	private List<CourseFull> courseFullList = new ArrayList<CourseFull>();
	private List<CourseFull> courseFullListCh = new ArrayList<CourseFull>();
	private List<CourseFull> courseFullListEn = new ArrayList<CourseFull>();
	
	OrderAdminService orderAdminService ;
	
	@Resource
	private GroupService groupService;
	private List<Usergroup> userGroupList = new ArrayList<Usergroup>();
	
	@Resource
	private TeacherService teacherService;
	private List<Teacher> teacherList = new ArrayList<Teacher>();
	
	private String logprefix = "exec action method:";
	

	private String courseIdsForDel;

	
	
//	添加时，课程英文名称 修改时也用
	private int courseiden;
	private String coursenameen;

	
//	管理参数
	private Integer usergroupid;
	
	
	/**
	 * 跳转到课程添加界面
	 * @return
	 */
	public String toCourseAddPage(){
		log.info(logprefix+"toCourseAddPage");
		
		userGroupList = groupService.getUserGroupByType(CommonConfig.usergroupstudentid);		
		teacherList = teacherService.getAllTeacher();
		
		if(userGroupList == null){
			userGroupList = new ArrayList<Usergroup>();
		}
		
		if(teacherList == null){
			 teacherList = new ArrayList<Teacher>();
		}

		return SUCCESS;
	}		
			
		
	//管理 查询
	public String manageCourseFull(){
		log.info("exec action method:manageCourseFull");
		
//		配置参数
		if(usergroupid == null || usergroupid<=0){
			usergroupid = 0;
		}
		
		teacherList = teacherService.getAllTeacher();
		
//		获取学生分组信息
		userGroupList = groupService.getUserGroupByType(CommonConfig.usergroupstudentid);
		if(userGroupList == null){
			userGroupList = new ArrayList<Usergroup>();
		}
		
        this.totalcount = courseService.countRowByGrade(usergroupid)/2;
        page = PageActionUtil.getPage(totalcount, pageNo);
        pageNo = page.getPageNo();
        
    	courseFullListCh  = courseService.selFullByGradePage(usergroupid, page, CommonConfig.languagech); 
        courseFullListEn  = courseService.selFullByGradePage(usergroupid, page, CommonConfig.languageen); 
        
        if(courseFullListCh == null){
		     courseFullListCh = new ArrayList<CourseFull>();
        }
        if(courseFullListEn == null){
	    	courseFullListEn = new ArrayList<CourseFull>();
        } 

        if(callType!=null&&callType.equals("ajaxType")){
			return "success2";
        }else{
           return "success1";
        }	
	}			

	public boolean checkAddForm(){
		if(course.getName()==null || course.getName().trim().length() ==0 ){
			this.returnInfo = "请填写课程名称";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return false;
		}
		
		if(coursenameen==null || coursenameen.trim().length() ==0 ){
			this.returnInfo = "请填写课程英文名称";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return false;
		}
		
		if(course.getTeacherid()==null || course.getTeacherid() ==0 ){
			this.returnInfo = "请选择教师";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return false;
		}
		
		if(course.getType() == null || course.getType() == 0 ){
			this.returnInfo = "请选择课程类型";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return false;
		}
		
		return true;
		
	}
	
//  ajax add	
	public String addCourseAjax(){	
		log.info("Add Entity Ajax Manner");
	

		try {
			
			Integer uid = this.getCurrentAdminId();
			if(uid < 0){
				this.returnInfo = "用户未登录";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
				return SUCCESS;
			}
			
			
		
			if(!checkAddForm()){
				return SUCCESS;
			}
			
			course.setAdduserid(uid);
			
//			System.out.println(DateUtil.currentDate());
//			course.setAddtime(DateUtil.currentDate());
			
			Course ch = new Course();	
			BeanUtils.copyProperties(ch, course);		
			ch.setAddtime(DateUtil.currentDate());
			ch.setLanguagetype(CommonConfig.languagech);
			
			
			Course en = new Course();	
			BeanUtils.copyProperties(en, course);		
			en.setName(coursenameen);
			en.setAddtime(DateUtil.currentDate());
			en.setLanguagetype(CommonConfig.languageen);
			courseService.addCourse(ch,en);

			returnInfo = "添加成功";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseAction的方法：addBbstagfavourite错误"+e);
		}
		
		
		
		returnInfo = "系统内部错误，添加失败";
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		return SUCCESS;
		
	}


	//del entityfull Ajax
	public String deleteCourseFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			
//			检查要删除的id是否为空
			if(courseIdsForDel == null || courseIdsForDel.trim().length()==0){
				returnInfo = "请选择要删除的课程";
				log.error(returnInfo);
				returnStr = ComputerActionUtil.buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
				return SUCCESS;
			}
			
			
			String typeStrArray[] = courseIdsForDel.split(";");		
			List<Integer> delTypeList = new ArrayList<Integer>();
			
//			判断参数是否正确
			for(String typeStr : typeStrArray){
				if(!NumberUtils.isNumber(typeStr)){
					this.returnInfo = "删除参数不正确";
					log.info(returnInfo);
					this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
					return SUCCESS;
				}
				delTypeList.add(Integer.valueOf(typeStr));
			}
		
			
			courseService.deleteCourse(delTypeList);
			
			 this.returnInfo = "删除成功";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
				return SUCCESS;
			
                        
         } catch (DataError de) {
             de.printStackTrace();
             this.returnInfo = de.getMessage();
     		log.info(returnInfo);
     		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
     		return SUCCESS;
         }catch (Exception e) {
                        e.printStackTrace();
           }
 
        this.returnInfo = "系统发生内部错误，删除失败";
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		return SUCCESS;
	}


	boolean checkUpdateParms(){
		if(course.getName()==null || course.getName().trim().length() ==0 ){
			this.returnInfo = "请填写课程名称";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return false;
		}
		
		if(coursenameen==null || coursenameen.trim().length() ==0 ){
			this.returnInfo = "请填写课程英文名称";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return false;
		}
		
		if(course.getTeacherid()==null || course.getTeacherid() ==0 ){
			this.returnInfo = "请选择教师";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return false;
		}
		
		if(course.getType() == null || course.getType() == 0 ){
			this.returnInfo = "请选择课程类型";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return false;
		}
		
		return true;
	}
	//ajax 修改
	public String updateCourseAjax(){
		log.info(logprefix + "updateCourseAjax");

		try {			
				if(checkUpdateParms() == false){
					return SUCCESS;
				}
				Course ch = new Course();
				Course en = new Course();
				int coursetype = course.getCoursetype();
				
				ch = courseService.sel(coursetype, CommonConfig.languagech);
				en = courseService.sel(coursetype, CommonConfig.languageen);
				if(ch ==null || en ==null){
					this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, "获取课程信息失败");
					return SUCCESS;
				}
  				ch.setName(course.getName());
  				ch.setType(course.getType());
  				ch.setTeacherid(course.getTeacherid());

  				en.setName(coursenameen);
  				en.setType(course.getType());
  				en.setTeacherid(course.getTeacherid());
				
				courseService.updateCourse(ch,en);	
				
				this.returnInfo = "修改成功";					
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
				return SUCCESS;
				
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseAction的方法：viewCourse错误"+e);
		}

		this.returnInfo = "内部错误";					
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		return SUCCESS;
	}

	
	@Override
	public Course getModel() {
		// TODO Auto-generated method stub
		return course;
	}
	


	public CourseService getCourseService() {
		return courseService;
	}



	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}



	public Integer getCourseid() {
		return courseid;
	}



	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}



	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
	}



	public Course getCourseModel() {
		return courseModel;
	}



	public void setCourseModel(Course courseModel) {
		this.courseModel = courseModel;
	}



	public CourseFull getCourseFull() {
		return courseFull;
	}



	public void setCourseFull(CourseFull courseFull) {
		this.courseFull = courseFull;
	}



	public List<Course> getCourseList() {
		return courseList;
	}



	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}



	public List<CourseFull> getCourseFullList() {
		return courseFullList;
	}



	public void setCourseFullList(List<CourseFull> courseFullList) {
		this.courseFullList = courseFullList;
	}



	public List<CourseFull> getCourseFullListCh() {
		return courseFullListCh;
	}



	public void setCourseFullListCh(List<CourseFull> courseFullListCh) {
		this.courseFullListCh = courseFullListCh;
	}



	public List<CourseFull> getCourseFullListEn() {
		return courseFullListEn;
	}



	public void setCourseFullListEn(List<CourseFull> courseFullListEn) {
		this.courseFullListEn = courseFullListEn;
	}



	public GroupService getGroupService() {
		return groupService;
	}



	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}



	public List<Usergroup> getUserGroupList() {
		return userGroupList;
	}



	public void setUserGroupList(List<Usergroup> userGroupList) {
		this.userGroupList = userGroupList;
	}



	public String getLogprefix() {
		return logprefix;
	}



	public void setLogprefix(String logprefix) {
		this.logprefix = logprefix;
	}



	public String getCourseIdsForDel() {
		return courseIdsForDel;
	}



	public void setCourseIdsForDel(String courseIdsForDel) {
		this.courseIdsForDel = courseIdsForDel;
	}



	public String getCoursenameen() {
		return coursenameen;
	}



	public void setCoursenameen(String coursenameen) {
		this.coursenameen = coursenameen;
	}



	public int getCourseiden() {
		return courseiden;
	}



	public void setCourseiden(int courseiden) {
		this.courseiden = courseiden;
	}





	public Integer getUsergroupid() {
		return usergroupid;
	}



	public void setUsergroupid(Integer usergroupid) {
		this.usergroupid = usergroupid;
	}



	public static Log getLog() {
		return log;
	}



	public TeacherService getTeacherService() {
		return teacherService;
	}



	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}



	public List<Teacher> getTeacherList() {
		return teacherList;
	}



	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}



	


	
	
	
	

        
}
