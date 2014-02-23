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
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.teach.CourseService;
import com.sbgl.app.services.user.GroupService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("CourseAction")
public class CourseAction extends ActionSupport implements SessionAware,ModelDriven<Course>{
	
	private static final Log log = LogFactory.getLog(CourseAction.class);

	private Map<String, Object> session;
	
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
	
	
	private String logprefix = "exec action method:";
	

	private ReturnJson returnJson = new ReturnJson();
	private String courseIdsForDel;
	
	
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	private String returnInfo;
	private String actionMsg; // Action间传递的消息参数
	
	private int pageNo=1;
	private int totalcount = 0;
	private int totalpage = 0;
	private Page page = new Page();
	private String callType;
	
//	添加时，课程英文名称
	private String coursenameen;
	private int courseiden;
	
	public int getAdminId(){
		
		Object obj = ServletActionContext.getRequest().getSession().getAttribute(CommonConfig.sessionadminid);
		if(obj!=null){
			return (Integer) obj;
		}
		
		return -1;
	}
	public void setPageInfo(int totalcount){
		if(pageNo ==0){
			pageNo =1;
		}
		
		//设置总数量
		page.setTotalCount(totalcount);
		
		//如果页码大于总页数，重新设置
		if(pageNo>page.getTotalpage()){
			pageNo = page.getTotalpage();
		}
		
		page.setPageNo(pageNo);
		if(page.getTotalCount()==0){
			page.setPageNo(0);
			page.setTotalpage(0);
			pageNo = 0;
			System.out.println(pageNo);
		}
	}
	
	/**
	 * 跳转到课程添加界面
	 * @return
	 */
	public String toCourseAddPage(){
		log.info(logprefix+"toCourseAddPage");
		
		userGroupList = groupService.getUserGroupByType(CommonConfig.usergroupstudentid);
		
		
		if(userGroupList == null){
			userGroupList = new ArrayList<Usergroup>();
		}

		return SUCCESS;
	}		
			
		
	//管理 查询
	public String manageCourseFull(){
		log.info("exec action method:manageCourseFull");
                
//      分页查询        
        int count = courseService.countCourseRow()/2;
        System.out.println(count);
        this.setPageInfo(count);
        
                
        courseFullListCh  = courseService.selectCourseFullByConditionAndPage(" where a.languagetype = "+CommonConfig.languagech, page);
        courseFullListEn  = courseService.selectCourseFullByConditionAndPage(" where a.languagetype = "+CommonConfig.languageen, page);
               

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


	
	//ajax 修改
	public String updateCourseAjax(){
		log.info(logprefix + "updateCourseAjax");
		ReturnJson returnJson = new ReturnJson();
		try {				
				Course ch = courseService.selectCourseById(course.getId());
				Course en = courseService.selectCourseById(courseiden);
				if(ch == null || en == null){
					
				}
//              选择能更改的属性，与界面一致	
  				ch.setName(course.getName());
  				ch.setDescription(course.getDescription());
  				ch.setType(course.getType());
  				ch.setTeacherid(course.getTeacherid());

  				en.setName(coursenameen);
  				en.setDescription(course.getDescription());
  				en.setType(course.getType());
  				en.setTeacherid(course.getTeacherid());
				
  				
  				
				courseService.updateCourse(ch);		
				courseService.updateCourse(en);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewCourseSuccess");
				return SUCCESS;
				
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseAction的方法：viewCourse错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
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

		public CourseService getCourseService() {
			return courseService;
		}

		public void setCourseService(CourseService courseService) {
			this.courseService = courseService;
		}

		public String getLogprefix() {
			return logprefix;
		}

		public void setLogprefix(String logprefix) {
			this.logprefix = logprefix;
		}

		public String getCallType() {
			return callType;
		}

		public void setCallType(String callType) {
			this.callType = callType;
		}

		public ReturnJson getReturnJson() {
			return returnJson;
		}

		public void setReturnJson(ReturnJson returnJson) {
			this.returnJson = returnJson;
		}

		public String getReturnInfo() {
			return returnInfo;
		}

		public void setReturnInfo(String returnInfo) {
			this.returnInfo = returnInfo;
		}

		public String getActionMsg() {
			return actionMsg;
		}

		public void setActionMsg(String actionMsg) {
			this.actionMsg = actionMsg;
		}

		public String getCoursenameen() {
			return coursenameen;
		}

		public void setCoursenameen(String coursenameen) {
			this.coursenameen = coursenameen;
		}

		public static Log getLog() {
			return log;
		}

		public Map<String, Object> getSession() {
			return session;
		}

		public void setCourseid(Integer courseid) {
			this.courseid = courseid;
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

		public int getTotalcount() {
			return totalcount;
		}

		public void setTotalcount(int totalcount) {
			this.totalcount = totalcount;
		}

		public int getTotalpage() {
			return totalpage;
		}

		public void setTotalpage(int totalpage) {
			this.totalpage = totalpage;
		}

		public int getCourseiden() {
			return courseiden;
		}

		public void setCourseiden(int courseiden) {
			this.courseiden = courseiden;
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
        
        
}
