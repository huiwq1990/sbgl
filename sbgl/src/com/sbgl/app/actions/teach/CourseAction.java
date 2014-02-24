package com.sbgl.app.actions.teach;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONObject;


import javax.annotation.Resource;
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
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.teach.CourseService;
import com.sbgl.app.services.user.GroupService;
import com.sbgl.app.services.user.TeacherService;
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
	
	

	
	public int getAdminId(){
		
		Object obj = ServletActionContext.getRequest().getSession().getAttribute(CommonConfig.sessionadminid);
		if(obj!=null){
			return (Integer) obj;
		}
		
		return -1;
	}
	
	
	
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
		
//		获取学生分组信息
		userGroupList = groupService.getUserGroupByType(CommonConfig.usergroupstudentid);
		if(userGroupList == null){
			userGroupList = new ArrayList<Usergroup>();
		}
		
                
//      分页查询 
		String courseSqlCh = " where  a.languagetype = "+CommonConfig.languagech;
		
		String courseSqlEn = " where  a.languagetype = "+CommonConfig.languageen;
		
		if(usergroupid != 0){
			courseSqlCh += "  and a.type="+usergroupid ;			
			courseSqlEn += "  and a.type="+usergroupid ;
		}
		courseFullListCh  = courseService.selectCourseFullByCondition(courseSqlCh);
		if(courseFullListCh == null){
	     courseFullListCh = new ArrayList<CourseFull>();
	    }

		for(CourseFull c : courseFullListCh){
			System.out.println(c.getCoursename());
		}
		 
        int count = courseFullListCh.size();
        setPageInfo(count);
        System.out.println(count);
        
    	courseFullListCh  = courseService.selectCourseFullByConditionAndPage( courseSqlCh,page);        
        courseFullListEn  = courseService.selectCourseFullByConditionAndPage(courseSqlEn, page);
		 if(courseFullListCh == null){
		     courseFullListCh = new ArrayList<CourseFull>();
		    }
			 
			 if(courseFullListEn == null){
		        	courseFullListEn = new ArrayList<CourseFull>();
		     } 

       
       
//      for(int i = 0; i < computerhomeworkFullList.size(); i++){
//      	System.out.println("id=");
//      }
        if(callType!=null&&callType.equals("ajaxType")){
			return "success2";
        }else{
           return "success1";
        }	
	}			

			
	
//  ajax add	
	public String addCourseAjax(){	
		log.info("Add Entity Ajax Manner");
	
		int aid = this.getAdminId();
		if(aid == -1){
			returnInfo = "管理员未登录";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxadminnotloginreturn, returnInfo);
			return SUCCESS;
		}

		try {
			
			course.setAdduserid(aid);
			course.setAddtime(DateUtil.currentDate());
			
			Course ch = new Course();	
			BeanUtils.copyProperties(ch, course);				
			ch.setLanguagetype(CommonConfig.languagech);
			
			
			Course en = new Course();	
			BeanUtils.copyProperties(en, course);		
			en.setName(coursenameen);
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
		
		
		
		returnInfo = "添加失败";
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		return SUCCESS;
		
	}


	//del entityfull Ajax
	public String deleteCourseFullAjax( ){
		
	/*	log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			String ids[] = courseIdsForDel.split(";");
			for(int i=0; i < ids.length;i++){
                                
				Integer tempDelId = Integer.valueOf(ids[i]);                        
				log.info(tempDelId);
                                //检查id
                                if(tempDelId == null || tempDelId < 0){
                                        returnJson.setFlag(0);
                                        returnJson.setReason("删除的id不规范");
                                        log.info("删除的id不规范");
                                        JSONObject jo = JSONObject.fromObject(returnJson);
                                        this.returnStr = jo.toString();
                                        return SUCCESS;
                                }        
                                //del
                                Course temp = courseService.selectCourseById(tempDelId);                        
                                if (temp != null) {                        
                                        //其他操作                                        
                                        courseService.deleteCourse(tempDelId);                                        
                                } else {
                                        log.info("删除的id不存在");                
                                        returnJson.setFlag(0);
                                        returnJson.setReason("删除的id不存在");
                                        JSONObject jo = JSONObject.fromObject(returnJson);
                                        this.returnStr = jo.toString();
                                        return SUCCESS;
                                }
                        }
                        returnJson.setFlag(1);
                        returnJson.setReason("删除成功");
                        JSONObject jo = JSONObject.fromObject(returnJson);
                        this.returnStr = jo.toString();
                        return SUCCESS;
                        
                } catch (Exception e) {
                        e.printStackTrace();
                }
                
                returnJson.setFlag(0);
                returnJson.setReason("删除的内部错误");
                JSONObject jo = JSONObject.fromObject(returnJson);
                this.returnStr = jo.toString();*/
                return SUCCESS;
        }


	
	//ajax 修改
	public String updateCourseAjax(){
		log.info(logprefix + "updateCourseAjax");

		try {				
				Course ch = courseService.selectCourseById(course.getId());
				Course en = courseService.selectCourseById(courseiden);
				if(ch == null || en == null){
					this.returnInfo = "获取修改信息出错";					
					this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
					return SUCCESS;
				}
//              选择能更改的属性，与界面一致	
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
