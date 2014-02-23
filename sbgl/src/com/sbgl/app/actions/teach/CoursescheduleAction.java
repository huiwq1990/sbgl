package com.sbgl.app.actions.teach;

import java.util.HashMap;
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
import com.sbgl.app.actions.computer.ComputerActionUtil;
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.computer.ComputercategoryService;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.app.services.teach.CourseService;
import com.sbgl.app.services.teach.CoursecomputerService;
import com.sbgl.app.services.teach.CourseconfigService;
import com.sbgl.app.services.teach.CoursescheduleService;
import com.sbgl.app.services.user.GroupService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("CoursescheduleAction")
public class CoursescheduleAction extends ActionSupport implements SessionAware,ModelDriven<Courseschedule>{
	
	private static final Log log = LogFactory.getLog(CoursescheduleAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private CoursescheduleService coursescheduleService;
	private Courseschedule courseschedule = new Courseschedule();//实例化一个模型
	private Courseschedule coursescheduleModel = new Courseschedule();//实例化一个模型
	private CoursescheduleFull coursescheduleFull = new CoursescheduleFull();//实例化一个模型
	List<Courseschedule> coursescheduleList = new ArrayList<Courseschedule>();
	List<CoursescheduleFull> coursescheduleFullList = new ArrayList<CoursescheduleFull>();
	private Integer coursescheduleid; //entity full 的id属性名称		
	
	
	
	@Resource
	private ComputercategoryService computercategoryService;
	int computercategorytype = 0;
	//父级分类的list
	List<Computercategory> parentcomputercategoryList = new ArrayList<Computercategory>();
	List<Computercategory> computercategoryList = new ArrayList<Computercategory>();
	List<ComputercategoryFull> computercategoryFullList = new ArrayList<ComputercategoryFull>();
	List<ComputercategoryFull> computercategoryFullListCh = new ArrayList<ComputercategoryFull>();
	List<ComputercategoryFull> computercategoryFullListEn = new ArrayList<ComputercategoryFull>();
	List<Integer> computercategoryModelSize = new ArrayList<Integer>();
	List<Integer> computercategoryComputerSize = new ArrayList<Integer>();
	
	@Resource
	private ComputermodelService computermodelService;
	int computermodeltype = 0;
//	List<Computercategory> computermodeComputercategoryList = new ArrayList<Computercategory>();
	List<Computermodel> computermodelList = new ArrayList<Computermodel>();
	List<ComputermodelFull> computermodelFullList = new ArrayList<ComputermodelFull>();
	List<ComputermodelFull> computermodelFullListCh = new ArrayList<ComputermodelFull>();
	List<ComputermodelFull> computermodelFullListEn = new ArrayList<ComputermodelFull>();
	
	private HashMap<Integer, ArrayList<Computermodel>> computermodelByComputercategoryId = new HashMap<Integer,ArrayList<Computermodel>>();
	
	@Resource
	private CourseService courseService;
	private Integer courseid; //entity full 的id属性名称		
	private Course course = new Course();//实例化一个模型
	private Course courseModel = new Course();//实例化一个模型
	private CourseFull courseFull = new CourseFull();//实例化一个模型
	private List<Course> courseList = new ArrayList<Course>();
	private List<CourseFull> courseFullList = new ArrayList<CourseFull>();
	
	
	@Resource
	private CourseconfigService courseconfigService;
	private Courseconfig courseconfig = new Courseconfig();//实例化一个模型
	private Courseconfig courseconfigModel = new Courseconfig();//实例化一个模型
	private CourseconfigFull courseconfigFull = new CourseconfigFull();//实例化一个模型
	List<Courseconfig> courseconfigList = new ArrayList<Courseconfig>();
	List<CourseconfigFull> courseconfigFullList = new ArrayList<CourseconfigFull>();
	private Integer courseconfigid; //entity full 的id属性名称
	
	@Resource
	private CoursecomputerService coursecomputerService;
	private Integer coursecomputerid; //entity full 的id属性名称		
	private Coursecomputer coursecomputer = new Coursecomputer();//实例化一个模型
	private Coursecomputer coursecomputerModel = new Coursecomputer();//实例化一个模型
	private CoursecomputerFull coursecomputerFull = new CoursecomputerFull();//实例化一个模型
	private List<Coursecomputer> coursecomputerList = new ArrayList<Coursecomputer>();
	private List<CoursecomputerFull> coursecomputerFullList = new ArrayList<CoursecomputerFull>();
	
	
	@Resource
	private GroupService groupService;
	private List<Usergroup> usergroupList = new ArrayList<Usergroup>();
	
	private HashMap<Integer, ArrayList<Course>> courseByGroupId = new HashMap<Integer,ArrayList<Course>>();
	private HashMap<Integer, ArrayList<CourseFull>> courseFullByGroupId = new HashMap<Integer,ArrayList<CourseFull>>();

	
	private String logprefix = "exec action method:";
	
	private int pageNo=1;
	private String callType;
	private Page page = new Page();
	private ReturnJson returnJson = new ReturnJson();
	private String coursescheduleIdsForDel;
	
	
//	add info
	private String inputdayperiod;
	private String inputweek;
	private String inputcomputerorderinfo;
	private String intputcourseid;
	
//	星期总数
	private int totalweeknum;
	
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	private String returnInfo;
	private String actionMsg; // Action间传递的消息参数
	

	private int totalcount = 0;
	private int totalpage = 0;

	
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
	
	
	public String toAddCourseschedulePage(){
		
//		获取学期信息
		courseconfigList = courseconfigService.selectCourseconfigByCondition(" where currentsemester = "+TeachConstant.coursesconfigcurrentsemester);
		if(courseconfigList == null || courseconfigList.size() != 1){
			return TeachConstant.notsetcourseconfig;
		}
		courseconfig = courseconfigList.get(0);
		totalweeknum = courseconfig.getWeeknum();
		

//		查询computer分类模型信息
		String categorySqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";	
		computercategoryList  = computercategoryService.selectComputercategoryByCondition(categorySqlch);		
		
		String modelSqlch = " where a.languagetype=0  ";	
		computermodelList  = computermodelService.selectComputermodelByCondition(modelSqlch);	
		System.out.println(computercategoryList.size());
		
		computermodelByComputercategoryId = ComputerActionUtil.categoryModelMap(computercategoryList, computermodelList);
		
//		课程组信息
		usergroupList = groupService.getUserGroupByType(CommonConfig.usergroupstudentid);		
//		课程信息
		courseFullList  = courseService.selectCourseFullByCondition(" where a.languagetype = "+CommonConfig.languagech);
		courseFullByGroupId = TeachActionUtil.couseFullUsergroupMap(usergroupList, courseFullList);
//		System.out.println(courseFullList.size());
		System.out.println("courseFullByGroupId size:"+courseFullByGroupId.size());
//		 System.out.println("courseFullByIdGroupId"+courseFullByGroupId.get(1).get(0).getCoursename());
//		System.out.println(computermodelByComputercategoryId.get(-1).size());
		 

	     if(courseFullList == null){
	        	courseFullList = new ArrayList<CourseFull>();
	        }
		if(computercategoryFullList == null){
			 computercategoryFullList = new ArrayList<ComputercategoryFull>();
		}
		
		if(computermodelByComputercategoryId == null){
			computermodelByComputercategoryId = new HashMap<Integer,ArrayList<Computermodel>>();
		}
		
		if(courseFullByGroupId == null){
			courseFullByGroupId = new HashMap<Integer,ArrayList<CourseFull>>();
		}
		
		return SUCCESS;
		
	}
	
			
	//管理 查询
	public String manageCoursescheduleFull(){
//		获取学期信息
		courseconfigList = courseconfigService.selectCourseconfigByCondition(" where currentsemester = "+TeachConstant.coursesconfigcurrentsemester);
		if(courseconfigList == null || courseconfigList.size() != 1){
			return TeachConstant.notsetcourseconfig;
		}
		courseconfig = courseconfigList.get(0);
		totalweeknum = courseconfig.getWeeknum();
		

//		查询computer分类模型信息
		String categorySqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";	
		computercategoryList  = computercategoryService.selectComputercategoryByCondition(categorySqlch);		
		
		String modelSqlch = " where a.languagetype=0  ";	
		computermodelList  = computermodelService.selectComputermodelByCondition(modelSqlch);	
		System.out.println(computercategoryList.size());
		
		computermodelByComputercategoryId = ComputerActionUtil.categoryModelMap(computercategoryList, computermodelList);
		
//		课程信息
		courseFullList  = courseService.selectCourseFullByCondition(" where a.languagetype = "+CommonConfig.languagech);
//		System.out.println(courseFullList.size());
		 
//		System.out.println(computermodelByComputercategoryId.get(-1).size());
		 

	     if(courseFullList == null){
	        	courseFullList = new ArrayList<CourseFull>();
	        }
		if(computercategoryFullList == null){
			 computercategoryFullList = new ArrayList<ComputercategoryFull>();
		}
		
		if(computermodelByComputercategoryId == null){
			computermodelByComputercategoryId = new HashMap<Integer,ArrayList<Computermodel>>();
		}
		
		return SUCCESS;
	}			


//  ajax add	
	public String addCoursescheduleAjax(){	
		log.info("Add Entity Ajax Manner");
		
		int aid = this.getAdminId();
		if(aid == -1){
			returnInfo = "管理员未登录";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxadminnotloginreturn, returnInfo);
			return SUCCESS;
		}
		
		
		if(!checkParm()){
			return SUCCESS; 
		}
		
		
//		获取学期信息
		courseconfigList = courseconfigService.selectCourseconfigByCondition(" where currentsemester =" +TeachConstant.coursesconfigcurrentsemester);
		if(courseconfigList == null || courseconfigList.size() != 1){
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, "没有设置学期信息");			
			return SUCCESS;
		}
		courseconfig = courseconfigList.get(0);
		
		
		try {
		String[] dayperiods = inputdayperiod.split(";");
		String[] weeks = inputweek.split(";");
		String[] computerorderinfos = inputcomputerorderinfo.split(";");
		
//		List<>
//		coursescheduleList
		for(String w : weeks){
			for(String dayperiod : dayperiods){
				
					 Courseschedule temp = new Courseschedule();//实例化一个模型
					 String[] dp = dayperiod.split(",");
					 temp.setSemester(courseconfig.getId());
					 
					 temp.setWeek(Integer.valueOf(w));
					 temp.setDay(Integer.valueOf(dp[0]));
					 temp.setPeriod(Integer.valueOf(dp[1]));
					 temp.setCourseid(Integer.valueOf(intputcourseid));
					 temp.setAdduserid(aid);
					 
					 temp.setStatus(TeachConstant.courseschedulevalidstatus);

					 coursescheduleList.add(temp);
			}
		}
		
		
		for(String computerorderinfo : computerorderinfos){
			 Coursecomputer temp = new Coursecomputer();
			 String[] co = computerorderinfo.split(",");
			 temp.setComputerid(Integer.valueOf(co[0]));
			 temp.setBorrownum(Integer.valueOf(co[1]));
			 coursecomputerList.add(temp);
		}
		

	
		
		for(Courseschedule temp : coursescheduleList){

//			String sql = " update Courseschedule set status = "+TeachConstant.coursescheduledel+" where courseid = "+temp.getCourseid()+" and semester = "+temp.getSemester()+" and week="+temp.getWeek()+" and day = "+temp.getDay()+"  and period = "+temp.getPeriod();
//			coursescheduleService.execSql(sql);
//			查询已经添加过的记录
			List<Courseschedule> addedCoursescheduleList = new ArrayList<Courseschedule>();
			String addedsql = "   where status = "+TeachConstant.courseschedulevalidstatus+" and courseid = "+temp.getCourseid()+" and semester = "+temp.getSemester()+" and week="+temp.getWeek()+" and day = "+temp.getDay()+"  and period = "+temp.getPeriod();
			addedCoursescheduleList = coursescheduleService.selectCoursescheduleByCondition(addedsql);
			
			
			if(addedCoursescheduleList!=null && addedCoursescheduleList.size() > 0){
//				删除已经添加
				String sql = " update Courseschedule set status = "+TeachConstant.coursescheduledelstatus+" where courseid = "+temp.getCourseid()+" and semester = "+temp.getSemester()+" and week="+temp.getWeek()+" and day = "+temp.getDay()+"  and period = "+temp.getPeriod();
				coursescheduleService.execSql(sql);
				log.info("课程信息已经添加");
			}

			

			
//			新添加课程表
			coursescheduleService.addCourseschedule(temp);
			
			for(Coursecomputer cc : coursecomputerList){
				cc.setLessonid(temp.getId());
				coursecomputerService.addCoursecomputer(cc);
			}
			
		}
		
		returnInfo = "添加成功";
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
		return SUCCESS;
		
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursescheduleAction的方法：addBbstagfavourite错误"+e);
		}

		returnInfo = "内部错误";
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		return SUCCESS;
	}

	
	public boolean checkParm(){
		if(inputdayperiod==null ){
			returnInfo = "请选择时间段";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return false;
		}
		
		if(inputweek==null || inputweek.length() ==0 ){
			returnInfo = "请选择周次";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return false;
		}
	if(intputcourseid==null || intputcourseid.length() ==0 ){
			
			returnInfo = "请选择课程";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return false;
		}
		if( inputcomputerorderinfo==null || inputcomputerorderinfo.length() ==0 ){
			returnInfo = "请选择预约机房";
		
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return false;
		}
		
	
		return true;
	}
//删除
	public String deleteCourseschedule( ){
		try{
			if(courseschedule.getId() != null && courseschedule.getId() > 0){
				coursescheduleService.deleteCourseschedule(courseschedule.getId());
				actionMsg = getText("deleteCoursescheduleSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteCoursescheduleFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursescheduleAction的方法：deleteCourseschedule错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteCoursescheduleAjax( ){
		try{
			if(courseschedule.getId() != null && courseschedule.getId() >= 0){
				coursescheduleService.deleteCourseschedule(courseschedule.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursescheduleAction的方法：deleteCourseschedule错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteCoursescheduleFull(){
		try {
		
			Integer getId = courseschedule.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Courseschedule temp = coursescheduleService.selectCoursescheduleById(getId);
			if (temp != null) {
				coursescheduleService.deleteCourseschedule(getId);
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
	public String deleteCoursescheduleFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			String ids[] = coursescheduleIdsForDel.split(";");
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
                                Courseschedule temp = coursescheduleService.selectCoursescheduleById(tempDelId);                        
                                if (temp != null) {                        
                                        //其他操作                                        
                                        coursescheduleService.deleteCourseschedule(tempDelId);                                        
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
	public String updateCourseschedule(){
		try {
			if(courseschedule.getId() != null && courseschedule.getId() > 0){				
				Courseschedule tempCourseschedule = coursescheduleService.selectCoursescheduleById(courseschedule.getId());
																				  								
												  								
								actionMsg = getText("viewCoursescheduleSuccess");
			}else{
				actionMsg = getText("viewCoursescheduleFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursescheduleAction的方法：viewCourseschedule错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateCoursescheduleAjax(){
		log.info(logprefix + "updateCoursescheduleAjax,id="+courseschedule.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(courseschedule.getId() != null && courseschedule.getId() > 0){				
				Courseschedule tempCourseschedule = coursescheduleService.selectCoursescheduleById(courseschedule.getId());
				
				
 
				
				coursescheduleService.updateCourseschedule(tempCourseschedule);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewCoursescheduleSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewCoursescheduleFail");
				log.info(logprefix + "updateCoursescheduleAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursescheduleAction的方法：viewCourseschedule错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editCourseschedule(){
		log.info(logprefix + "editCourseschedule");
			
		try {
			//实体的id可以为0
			if(courseschedule.getId() != null && courseschedule.getId() >= 0){				
				Courseschedule temCourseschedule = coursescheduleService.selectCoursescheduleById(courseschedule.getId());
				if(temCourseschedule != null){
					BeanUtils.copyProperties(coursescheduleModel,temCourseschedule);	
					//actionMsg = getText("selectCoursescheduleByIdSuccess");
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
			log.error("类CoursescheduleAction的方法：selectCoursescheduleById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editCoursescheduleFull(){
		
		log.info(logprefix + "viewCourseschedule");
		
		try {
			if(courseschedule.getId() != null && courseschedule.getId() > 0){				
				CoursescheduleFull temCoursescheduleFull = coursescheduleService.selectCoursescheduleFullById(courseschedule.getId());
				BeanUtils.copyProperties(coursescheduleFull,temCoursescheduleFull);	
				actionMsg = getText("selectCoursescheduleByIdSuccess");
			}else{
				actionMsg = getText("selectCoursescheduleByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursescheduleAction的方法：selectCoursescheduleFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewCourseschedule(){
		log.info("viewCourseschedule");
		try {
			if(courseschedule.getId() != null && courseschedule.getId() > 0){				
				Courseschedule temCourseschedule = coursescheduleService.selectCoursescheduleById(courseschedule.getId());
				BeanUtils.copyProperties(coursescheduleModel,temCourseschedule);	
				actionMsg = getText("selectCoursescheduleByIdSuccess");
			}else{
				actionMsg = getText("selectCoursescheduleByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursescheduleAction的方法：selectCoursescheduleById错误"+e);
		}


		return "error";

	}	

/**
 * view CoursescheduleFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewCoursescheduleFull() {
				
		try {
			int getId = courseschedule.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			CoursescheduleFull temCoursescheduleFull = coursescheduleService.selectCoursescheduleFullById(getId);				
			if(temCoursescheduleFull!=null){				
				BeanUtils.copyProperties(coursescheduleFull,temCoursescheduleFull);
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
*/
	//get set
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	    this.session = session;
	}
	
	@Override
	public Courseschedule getModel() {
		// TODO Auto-generated method stub
		return courseschedule;
	}

//  
	public Courseschedule getCourseschedule() {
		return courseschedule;
	}
	
	public void setCourseschedule(Courseschedule courseschedule) {
		this.courseschedule = courseschedule;
	}
//  entityModel
	public Courseschedule getCoursescheduleModel() {
		return coursescheduleModel;
	}
	
	public void setCoursescheduleModel(Courseschedule coursescheduleModel) {
		this.coursescheduleModel = coursescheduleModel;
	}
	
	public CoursescheduleFull getCoursescheduleFull() {
		return coursescheduleFull;
	}
	
	public void setCoursescheduleFull(CoursescheduleFull coursescheduleFull) {
		this.coursescheduleFull = coursescheduleFull;
	}
	
	public List<Courseschedule> getCoursescheduleList() {
		return coursescheduleList;
	}


	public void setCoursescheduleList(List<Courseschedule> coursescheduleList) {
		this.coursescheduleList = coursescheduleList;
	}

	public List<CoursescheduleFull> getCoursescheduleFullList() {
		return coursescheduleFullList;
	}


	public void setCoursescheduleFullList(List<CoursescheduleFull> coursescheduleFullList) {
		this.coursescheduleFullList = coursescheduleFullList;
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
	
	public int getCoursescheduleid() {
		return coursescheduleid;
	}

	public void setCoursescheduleid(int coursescheduleid) {
		this.coursescheduleid = coursescheduleid;
	}
		public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	public String getCoursescheduleIdsForDel() {
                return coursescheduleIdsForDel;
        }

        public void setCoursescheduleIdsForDel(String coursescheduleIdsForDel) {
                this.coursescheduleIdsForDel = coursescheduleIdsForDel;
        }



		public CoursescheduleService getCoursescheduleService() {
			return coursescheduleService;
		}



		public void setCoursescheduleService(CoursescheduleService coursescheduleService) {
			this.coursescheduleService = coursescheduleService;
		}



		public String getActionMsg() {
			return actionMsg;
		}



		public void setActionMsg(String actionMsg) {
			this.actionMsg = actionMsg;
		}



		public ComputercategoryService getComputercategoryService() {
			return computercategoryService;
		}



		public void setComputercategoryService(
				ComputercategoryService computercategoryService) {
			this.computercategoryService = computercategoryService;
		}



		public int getComputercategorytype() {
			return computercategorytype;
		}



		public void setComputercategorytype(int computercategorytype) {
			this.computercategorytype = computercategorytype;
		}



		public List<Computercategory> getParentcomputercategoryList() {
			return parentcomputercategoryList;
		}



		public void setParentcomputercategoryList(
				List<Computercategory> parentcomputercategoryList) {
			this.parentcomputercategoryList = parentcomputercategoryList;
		}



		public List<Computercategory> getComputercategoryList() {
			return computercategoryList;
		}



		public void setComputercategoryList(List<Computercategory> computercategoryList) {
			this.computercategoryList = computercategoryList;
		}



		public List<ComputercategoryFull> getComputercategoryFullList() {
			return computercategoryFullList;
		}



		public void setComputercategoryFullList(
				List<ComputercategoryFull> computercategoryFullList) {
			this.computercategoryFullList = computercategoryFullList;
		}



		public List<ComputercategoryFull> getComputercategoryFullListCh() {
			return computercategoryFullListCh;
		}



		public void setComputercategoryFullListCh(
				List<ComputercategoryFull> computercategoryFullListCh) {
			this.computercategoryFullListCh = computercategoryFullListCh;
		}



		public List<ComputercategoryFull> getComputercategoryFullListEn() {
			return computercategoryFullListEn;
		}



		public void setComputercategoryFullListEn(
				List<ComputercategoryFull> computercategoryFullListEn) {
			this.computercategoryFullListEn = computercategoryFullListEn;
		}



		public List<Integer> getComputercategoryModelSize() {
			return computercategoryModelSize;
		}



		public void setComputercategoryModelSize(List<Integer> computercategoryModelSize) {
			this.computercategoryModelSize = computercategoryModelSize;
		}



		public List<Integer> getComputercategoryComputerSize() {
			return computercategoryComputerSize;
		}



		public void setComputercategoryComputerSize(
				List<Integer> computercategoryComputerSize) {
			this.computercategoryComputerSize = computercategoryComputerSize;
		}



		public ComputermodelService getComputermodelService() {
			return computermodelService;
		}



		public void setComputermodelService(ComputermodelService computermodelService) {
			this.computermodelService = computermodelService;
		}



		public int getComputermodeltype() {
			return computermodeltype;
		}



		public void setComputermodeltype(int computermodeltype) {
			this.computermodeltype = computermodeltype;
		}



		public List<Computermodel> getComputermodelList() {
			return computermodelList;
		}



		public void setComputermodelList(List<Computermodel> computermodelList) {
			this.computermodelList = computermodelList;
		}



		public List<ComputermodelFull> getComputermodelFullList() {
			return computermodelFullList;
		}



		public void setComputermodelFullList(
				List<ComputermodelFull> computermodelFullList) {
			this.computermodelFullList = computermodelFullList;
		}



		public List<ComputermodelFull> getComputermodelFullListCh() {
			return computermodelFullListCh;
		}



		public void setComputermodelFullListCh(
				List<ComputermodelFull> computermodelFullListCh) {
			this.computermodelFullListCh = computermodelFullListCh;
		}



		public List<ComputermodelFull> getComputermodelFullListEn() {
			return computermodelFullListEn;
		}



		public void setComputermodelFullListEn(
				List<ComputermodelFull> computermodelFullListEn) {
			this.computermodelFullListEn = computermodelFullListEn;
		}



		public HashMap<Integer, ArrayList<Computermodel>> getComputermodelByComputercategoryId() {
			return computermodelByComputercategoryId;
		}



		public void setComputermodelByComputercategoryId(
				HashMap<Integer, ArrayList<Computermodel>> computermodelByComputercategoryId) {
			this.computermodelByComputercategoryId = computermodelByComputercategoryId;
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



		public static Log getLog() {
			return log;
		}



		public Map<String, Object> getSession() {
			return session;
		}



		public void setCoursescheduleid(Integer coursescheduleid) {
			this.coursescheduleid = coursescheduleid;
		}


		public CourseconfigService getCourseconfigService() {
			return courseconfigService;
		}


		public void setCourseconfigService(CourseconfigService courseconfigService) {
			this.courseconfigService = courseconfigService;
		}


		public Courseconfig getCourseconfig() {
			return courseconfig;
		}


		public void setCourseconfig(Courseconfig courseconfig) {
			this.courseconfig = courseconfig;
		}


		public Courseconfig getCourseconfigModel() {
			return courseconfigModel;
		}


		public void setCourseconfigModel(Courseconfig courseconfigModel) {
			this.courseconfigModel = courseconfigModel;
		}


		public CourseconfigFull getCourseconfigFull() {
			return courseconfigFull;
		}


		public void setCourseconfigFull(CourseconfigFull courseconfigFull) {
			this.courseconfigFull = courseconfigFull;
		}


		public List<Courseconfig> getCourseconfigList() {
			return courseconfigList;
		}


		public void setCourseconfigList(List<Courseconfig> courseconfigList) {
			this.courseconfigList = courseconfigList;
		}


		public List<CourseconfigFull> getCourseconfigFullList() {
			return courseconfigFullList;
		}


		public void setCourseconfigFullList(List<CourseconfigFull> courseconfigFullList) {
			this.courseconfigFullList = courseconfigFullList;
		}


		public Integer getCourseconfigid() {
			return courseconfigid;
		}


		public void setCourseconfigid(Integer courseconfigid) {
			this.courseconfigid = courseconfigid;
		}


		public CoursecomputerService getCoursecomputerService() {
			return coursecomputerService;
		}


		public void setCoursecomputerService(CoursecomputerService coursecomputerService) {
			this.coursecomputerService = coursecomputerService;
		}


		public Integer getCoursecomputerid() {
			return coursecomputerid;
		}


		public void setCoursecomputerid(Integer coursecomputerid) {
			this.coursecomputerid = coursecomputerid;
		}


		public Coursecomputer getCoursecomputer() {
			return coursecomputer;
		}


		public void setCoursecomputer(Coursecomputer coursecomputer) {
			this.coursecomputer = coursecomputer;
		}


		public Coursecomputer getCoursecomputerModel() {
			return coursecomputerModel;
		}


		public void setCoursecomputerModel(Coursecomputer coursecomputerModel) {
			this.coursecomputerModel = coursecomputerModel;
		}


		public CoursecomputerFull getCoursecomputerFull() {
			return coursecomputerFull;
		}


		public void setCoursecomputerFull(CoursecomputerFull coursecomputerFull) {
			this.coursecomputerFull = coursecomputerFull;
		}


		public List<Coursecomputer> getCoursecomputerList() {
			return coursecomputerList;
		}


		public void setCoursecomputerList(List<Coursecomputer> coursecomputerList) {
			this.coursecomputerList = coursecomputerList;
		}


		public List<CoursecomputerFull> getCoursecomputerFullList() {
			return coursecomputerFullList;
		}


		public void setCoursecomputerFullList(
				List<CoursecomputerFull> coursecomputerFullList) {
			this.coursecomputerFullList = coursecomputerFullList;
		}


		public String getInputdayperiod() {
			return inputdayperiod;
		}


		public void setInputdayperiod(String inputdayperiod) {
			this.inputdayperiod = inputdayperiod;
		}


		public String getInputweek() {
			return inputweek;
		}


		public void setInputweek(String inputweek) {
			this.inputweek = inputweek;
		}


		public String getInputcomputerorderinfo() {
			return inputcomputerorderinfo;
		}


		public void setInputcomputerorderinfo(String inputcomputerorderinfo) {
			this.inputcomputerorderinfo = inputcomputerorderinfo;
		}


		public String getReturnInfo() {
			return returnInfo;
		}


		public void setReturnInfo(String returnInfo) {
			this.returnInfo = returnInfo;
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


		public String getIntputcourseid() {
			return intputcourseid;
		}


		public void setIntputcourseid(String intputcourseid) {
			this.intputcourseid = intputcourseid;
		}


		public int getTotalweeknum() {
			return totalweeknum;
		}


		public void setTotalweeknum(int totalweeknum) {
			this.totalweeknum = totalweeknum;
		}


		public GroupService getGroupService() {
			return groupService;
		}


		public void setGroupService(GroupService groupService) {
			this.groupService = groupService;
		}


		public List<Usergroup> getUsergroupList() {
			return usergroupList;
		}


		public void setUsergroupList(List<Usergroup> usergroupList) {
			this.usergroupList = usergroupList;
		}


		public HashMap<Integer, ArrayList<Course>> getCourseByGroupId() {
			return courseByGroupId;
		}


		public void setCourseByGroupId(
				HashMap<Integer, ArrayList<Course>> courseByGroupId) {
			this.courseByGroupId = courseByGroupId;
		}


		public HashMap<Integer, ArrayList<CourseFull>> getCourseFullByGroupId() {
			return courseFullByGroupId;
		}


		public void setCourseFullByGroupId(
				HashMap<Integer, ArrayList<CourseFull>> courseFullByGroupId) {
			this.courseFullByGroupId = courseFullByGroupId;
		}


		
        
        
}
