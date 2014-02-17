package com.sbgl.app.actions.teach;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONObject;


import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.commons.beanutils.BeanUtils;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.teach.CourseService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("CourseAction")
public class CourseAction extends ActionSupport implements SessionAware,ModelDriven<Course>{
	
	private static final Log log = LogFactory.getLog(CourseAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private CourseService courseService;
	
	private Course course = new Course();//实例化一个模型
	private Course courseModel = new Course();//实例化一个模型
	private CourseFull courseFull = new CourseFull();//实例化一个模型

	List<Course> courseList = new ArrayList<Course>();
	List<CourseFull> courseFullList = new ArrayList<CourseFull>();
	private Integer courseid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";
	
	private int pageNo=1;
	private String callType;
	private Page page = new Page();
	private ReturnJson returnJson = new ReturnJson();
	private String courseIdsForDel;
	
	
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	private String returnInfo;
	private String actionMsg; // Action间传递的消息参数
	
	
	
//	添加时，课程英文名称
	private String coursenameen;

	public int getAdminId(){
		
	}
	
	/**
	 * 跳转到课程添加界面
	 * @return
	 */
	public String toCourseAddPage(){
		log.info(logprefix+"toCourseAddPage");
		

		return SUCCESS;
	}		
			
	
//  manage Course
	public String manageCourse(){
		log.info(logprefix+"manageCourseFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(courseService.countCourseRow());
		courseList  = courseService.selectCourseByPage(page);
		
//		查询全部
//		courseList  = courseService.selectCourseById();
		
	    if(courseList == null){
			courseList = new ArrayList<Course>();
		}
//		for(int i = 0; i < courseList.size(); i++){
//			System.out.println("id="+courseList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageCourseFull(){
		log.info("exec action method:manageCourseFull");
                
//      分页查询        
        if(pageNo <= 0){
			pageNo =1;
        }
        page.setTotalCount(courseService.countCourseRow());
        if(page.getTotalpage() < pageNo){
			pageNo = page.getTotalpage();
        }
        page.setPageNo(pageNo);
                
        courseFullList  = courseService.selectCourseFullByConditionAndPage("", page);
               

        if(courseFullList == null){
			courseFullList = new ArrayList<CourseFull>();
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
	
		
		try {
			Course temp = new Course();
			
			temp.setAddtime(DateUtil.currentDate());
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, course);			
			
			
			courseService.addCourse(temp);
			
			

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

//删除
	public String deleteCourse( ){
		try{
			if(course.getId() != null && course.getId() > 0){
				courseService.deleteCourse(course.getId());
				actionMsg = getText("deleteCourseSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteCourseFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseAction的方法：deleteCourse错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteCourseAjax( ){
		try{
			if(course.getId() != null && course.getId() >= 0){
				courseService.deleteCourse(course.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseAction的方法：deleteCourse错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteCourseFull(){
		try {
		
			Integer getId = course.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Course temp = courseService.selectCourseById(getId);
			if (temp != null) {
				courseService.deleteCourse(getId);
				return SUCCESS;
			} else {
				log.info("删除的id不存在");
				return "Error";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error";
	}
	
	//del entityfull Ajax
	public String deleteCourseFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
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
                this.returnStr = jo.toString();
                return SUCCESS;
        }

//修改
	public String updateCourse(){
		try {
			if(course.getId() != null && course.getId() > 0){				
				Course tempCourse = courseService.selectCourseById(course.getId());
																				  								
												  								
												  								
												  								
												  								
												  								
								actionMsg = getText("viewCourseSuccess");
			}else{
				actionMsg = getText("viewCourseFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseAction的方法：viewCourse错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateCourseAjax(){
		log.info(logprefix + "updateCourseAjax,id="+course.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(course.getId() != null && course.getId() > 0){				
				Course tempCourse = courseService.selectCourseById(course.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempCourse.setName(course.getName());
  				tempCourse.setDescription(course.getDescription());
  				tempCourse.setType(course.getType());
  				tempCourse.setAdduserid(course.getAdduserid());
  				tempCourse.setTeacherid(course.getTeacherid());
  				tempCourse.setAddtime(course.getAddtime());
 
				
				courseService.updateCourse(tempCourse);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewCourseSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewCourseFail");
				log.info(logprefix + "updateCourseAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseAction的方法：viewCourse错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editCourse(){
		log.info(logprefix + "editCourse");
			
		try {
			//实体的id可以为0
			if(course.getId() != null && course.getId() >= 0){				
				Course temCourse = courseService.selectCourseById(course.getId());
				if(temCourse != null){
					BeanUtils.copyProperties(courseModel,temCourse);	
					//actionMsg = getText("selectCourseByIdSuccess");
					return SUCCESS;
				}				
			}		
			return "PageNotExist";
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseAction的方法：selectCourseById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editCourseFull(){
		
		log.info(logprefix + "viewCourse");
		
		try {
			if(course.getId() != null && course.getId() > 0){				
				CourseFull temCourseFull = courseService.selectCourseFullById(course.getId());
				BeanUtils.copyProperties(courseFull,temCourseFull);	
				actionMsg = getText("selectCourseByIdSuccess");
			}else{
				actionMsg = getText("selectCourseByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseAction的方法：selectCourseFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewCourse(){
		log.info("viewCourse");
		try {
			if(course.getId() != null && course.getId() > 0){				
				Course temCourse = courseService.selectCourseById(course.getId());
				BeanUtils.copyProperties(courseModel,temCourse);	
				actionMsg = getText("selectCourseByIdSuccess");
			}else{
				actionMsg = getText("selectCourseByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseAction的方法：selectCourseById错误"+e);
		}


		return "error";

	}	

/**
 * view CourseFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewCourseFull() {
				
		try {
			int getId = course.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			CourseFull temCourseFull = courseService.selectCourseFullById(getId);				
			if(temCourseFull!=null){				
				BeanUtils.copyProperties(courseFull,temCourseFull);
				return SUCCESS;				
			}else{
				log.error("error,查询实体不存在。");
				return "Error";
			}			

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return "Error";
	}
/*
	//根据adduserid查询实体
	public String selectCourseByLoginuserId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		courseList  = courseService.selectCourseAll();
		for(int i = 0; i < courseList.size(); i++){
			System.out.println("id="+courseList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据teacherid查询实体
	public String selectCourseByLoginuserId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		courseList  = courseService.selectCourseAll();
		for(int i = 0; i < courseList.size(); i++){
			System.out.println("id="+courseList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据adduserid查询实体full
	public String selectCourseFullByLoginuserId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		courseFullList  = courseService.selectCourseFullByLoginuserId(userId);
		for(int i = 0; i < courseFullList.size(); i++){
			//System.out.println("id="+courseFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}
	//根据teacherid查询实体full
	public String selectCourseFullByLoginuserId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		courseFullList  = courseService.selectCourseFullByLoginuserId(userId);
		for(int i = 0; i < courseFullList.size(); i++){
			//System.out.println("id="+courseFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}
*/
	//get set
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	    this.session = session;
	}
	
	@Override
	public Course getModel() {
		// TODO Auto-generated method stub
		return course;
	}

//  
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
//  entityModel
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

	public String getReturnStr() {
		return returnStr;
	}


	public void setReturnStr(String returnStr) {
		this.returnStr = returnStr;
	}
	
	public Page getPage() {
		return page;
	}


	public void setPage(Page page) {
		this.page = page;
	}
	
	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
		public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	public String getCourseIdsForDel() {
                return courseIdsForDel;
        }

        public void setCourseIdsForDel(String courseIdsForDel) {
                this.courseIdsForDel = courseIdsForDel;
        }
}
