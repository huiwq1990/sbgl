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
import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.computer.ComputerActionUtil;
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.common.computer.BorrowperiodUtil;
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
public class CoursescheduleAction extends BaseAction implements ModelDriven<Courseschedule>{
	
	private static final Log log = LogFactory.getLog(CoursescheduleAction.class);

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

	
//	日，时间段
	HashMap<Integer,HashMap<Integer,Courseschedule>> coursescheduleDayPeriodMap = new HashMap<Integer,HashMap<Integer,Courseschedule>>();
	
	
//	时间段 日
	HashMap<Integer,HashMap<Integer,Courseschedule>> courseschedulePeriodDayMap = new HashMap<Integer,HashMap<Integer,Courseschedule>>();
	
	HashMap<Integer,HashMap<Integer,ArrayList<Courseschedule>>> courseschedulePeriodDayMapList = new HashMap<Integer,HashMap<Integer,ArrayList<Courseschedule>>>();
	HashMap<Integer,HashMap<Integer,ArrayList<CoursescheduleFull>>> coursescheduleFullPeriodDayMapList = new HashMap<Integer,HashMap<Integer,ArrayList<CoursescheduleFull>>>();
	
//	List<HashMap<Integer,HashMap<Integer,Courseschedule>>> courseschedulePeriodDayMapList = new ArrayList<HashMap<Integer,HashMap<Integer,Courseschedule>>>();
	
	private List<Borrowperiod> periodList = new ArrayList<Borrowperiod>();
	
	private List<Integer> dayList = new ArrayList<Integer>();
	
	
	private String logprefix = "exec action method:";
	
	private String coursescheduleIdsForDel;
	
	
//	add info
	private String inputdayperiod;
	private String inputweek;
	private String inputcomputerorderinfo;
	private String intputcourseid;
	
//	星期总数
	private int totalweeknum;
	
	private int totalcount = 0;
	private int totalpage = 0;

	
//	添加时，课程英文名称
	private String coursenameen;
	private int courseiden;

//	跳转到课程计划添加
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
	
	
//	查看某一周的所有课程的安排
	public String browseAllCoursescheduleByWeek(){
		
		try{
			
			int aid = this.getCurrentAdminId();
			if(aid == -1){
				returnInfo = "管理员未登录";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxadminnotloginreturn, returnInfo);
				return SUCCESS;
			}
			
//			获取学期信息
			courseconfig = courseconfigService.getCurrentCourseconfig();
			if(courseconfig == null){
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, "没有设置学期信息");			
				return SUCCESS;
			}
			


//			查询computer分类模型信息
			String categorySqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";	
			computercategoryList  = computercategoryService.selectComputercategoryByCondition(categorySqlch);					
			String modelSqlch = " where a.languagetype=0  ";	
			computermodelList  = computermodelService.selectComputermodelByCondition(modelSqlch);	
			System.out.println(computercategoryList.size());
//			构建分类模型的map
			computermodelByComputercategoryId = ComputerActionUtil.categoryModelMap(computercategoryList, computermodelList);
			
//			课程信息
			courseFullList  = courseService.selectCourseFullByCondition(" where a.languagetype = "+CommonConfig.languagech);
//			System.out.println(courseFullList.size());
			 
//			System.out.println(computermodelByComputercategoryId.get(-1).size());
			 

		     if(courseFullList == null){
		        	courseFullList = new ArrayList<CourseFull>();
		        }
			if(computercategoryFullList == null){
				 computercategoryFullList = new ArrayList<ComputercategoryFull>();
			}
			
			if(computermodelByComputercategoryId == null){
				computermodelByComputercategoryId = new HashMap<Integer,ArrayList<Computermodel>>();
			}
			

//			获取时间段信息，及天的信息
			periodList =  BorrowperiodUtil.getBorrowperiodList();
			dayList = TeachActionUtil.getDayList();

			
			log.info(courseconfig.getId() +  " " +courseschedule.getCourseid() + "  "+ courseschedule.getWeek());
			coursescheduleFullList = coursescheduleService.selectCoursescheduleFullByWeek(courseconfig.getId(), courseschedule.getWeek());

			System.out.println("第"+courseschedule.getWeek()+"周所有课程预约数目"+coursescheduleFullList.size());
			//			转化成map
			coursescheduleFullPeriodDayMapList = TeachActionUtil.coursescheduleFullPeriodDayMapList(coursescheduleFullList);
			System.out.println(coursescheduleFullPeriodDayMapList.get(1).get(2).get(0).getCoursename());
			
			returnInfo = "";
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
	
	//管理 查询
	public String manageCoursescheduleFull(){
		try{
//		获取学期信息
		courseconfig = courseconfigService.getCurrentCourseconfig();
		if(courseconfig == null){
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
		
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursescheduleAction的方法：addBbstagfavourite错误"+e);
		}
		
		return "error";
	}			


//  ajax add	
	public String addCoursescheduleAjax(){	
		log.info("Add Entity Ajax Manner");
		try{
		
		int aid = this.getCurrentUserId();
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
		courseconfig = courseconfigService.getCurrentCourseconfig();
		if(courseconfig == null){
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, "没有设置学期信息");			
			return SUCCESS;
		}
		
		
		
		String[] dayperiods = inputdayperiod.split(";");
		String[] weeks = inputweek.split(";");
		String[] computerorderinfos = inputcomputerorderinfo.split(";");
		

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
		
//		log.info(coursescheduleList)

		coursescheduleService.addCourseschedule(coursescheduleList, coursecomputerList);

		
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

	/*
	public String viewCoursescheduleByWeekAjax(){
		try{
			
			int aid = this.getCurrentAdminId();
			if(aid == -1){
				returnInfo = "管理员未登录";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxadminnotloginreturn, returnInfo);
				return SUCCESS;
			}
			
//			获取学期信息
			courseconfig = courseconfigService.getCurrentCourseconfig();
			if(courseconfig == null){
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, "没有设置学期信息");			
				return SUCCESS;
			}

			
			
			coursescheduleList = coursescheduleService.selectCoursescheduleByWeek(courseschedule.getCourseid(), courseconfig.getSemester(), courseschedule.getWeek());
//			转化成map
			dayPeriodMap();
			
			returnInfo = "";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
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
	

	
	public void dayPeriodMap(){
//		课程，学期，周，天，日，时间段
//		HashMap<Integer,HashMap<Integer,Courseschedule>> dayPap = new HashMap<Integer,HashMap<Integer,Courseschedule>>();
		for(Courseschedule cs : coursescheduleList){
			boolean weekexist = coursescheduleDayPeriodMap.containsKey(cs.getDay());
			if(weekexist == true){
				
			}else{
				HashMap<Integer,Courseschedule> peMap = new HashMap<Integer,Courseschedule>();
				coursescheduleDayPeriodMap.put(cs.getDay(), peMap);
			}
			
//			boolean periodExist = dayPap.get(cs.getDay()).containsKey(cs.getPeriod());
//			
//			if(periodExist == false){
//				
//			}
			coursescheduleDayPeriodMap.get(cs.getDay()).put(cs.getPeriod(), cs);
		}
		
	}*/
	
	/*
	public String viewCoursescheduleByPeriodDayAjax(){
		try{
			
			int aid = this.getCurrentAdminId();
			if(aid == -1){
				returnInfo = "管理员未登录";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxadminnotloginreturn, returnInfo);
				return SUCCESS;
			}
			
//			获取学期信息
			courseconfig = courseconfigService.getCurrentCourseconfig();
			if(courseconfig == null){
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, "没有设置学期信息");			
				return SUCCESS;
			}

			
			
			coursescheduleList = coursescheduleService.selectCoursescheduleByWeek(courseschedule.getCourseid(), courseconfig.getSemester(), courseschedule.getWeek());
//			转化成map
			periodDayMap(coursescheduleList);
			
			returnInfo = "";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
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
	*/
	

	public void periodDayMap(List<Courseschedule> coursescheduleList){
//		课程，学期，周，天，日，时间段
//		HashMap<Integer,HashMap<Integer,Courseschedule>> dayPap = new HashMap<Integer,HashMap<Integer,Courseschedule>>();
		for(Courseschedule cs : coursescheduleList){
			boolean period = coursescheduleDayPeriodMap.containsKey(cs.getPeriod());
			if(period == true){
				
			}else{
				HashMap<Integer,Courseschedule> peMap = new HashMap<Integer,Courseschedule>();
				courseschedulePeriodDayMap.put(cs.getPeriod(), peMap);
			}
			
//			boolean periodExist = dayPap.get(cs.getDay()).containsKey(cs.getPeriod());
//			
//			if(periodExist == false){
//				
//			}
			courseschedulePeriodDayMap.get(cs.getPeriod()).put(cs.getDay(), cs);
		}
		
	}
	

	public String viewCoursescheduleByWeek(){
		try{
			
			int aid = this.getCurrentAdminId();
			if(aid == -1){
				returnInfo = "管理员未登录";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxadminnotloginreturn, returnInfo);
				return SUCCESS;
			}
			
//			获取学期信息
			courseconfig = courseconfigService.getCurrentCourseconfig();
			if(courseconfig == null){
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, "没有设置学期信息");			
				return SUCCESS;
			}
			


//			查询computer分类模型信息
			String categorySqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";	
			computercategoryList  = computercategoryService.selectComputercategoryByCondition(categorySqlch);					
			String modelSqlch = " where a.languagetype=0  ";	
			computermodelList  = computermodelService.selectComputermodelByCondition(modelSqlch);	
			System.out.println(computercategoryList.size());
//			构建分类模型的map
			computermodelByComputercategoryId = ComputerActionUtil.categoryModelMap(computercategoryList, computermodelList);
			
//			课程信息
			courseFullList  = courseService.selectCourseFullByCondition(" where a.languagetype = "+CommonConfig.languagech);
//			System.out.println(courseFullList.size());
			 
//			System.out.println(computermodelByComputercategoryId.get(-1).size());
			 

		     if(courseFullList == null){
		        	courseFullList = new ArrayList<CourseFull>();
		        }
			if(computercategoryFullList == null){
				 computercategoryFullList = new ArrayList<ComputercategoryFull>();
			}
			
			if(computermodelByComputercategoryId == null){
				computermodelByComputercategoryId = new HashMap<Integer,ArrayList<Computermodel>>();
			}
			

//			获取时间段信息，及天的信息
			periodList =  BorrowperiodUtil.getBorrowperiodList();
			dayList = TeachActionUtil.getDayList();

			
			log.info(courseconfig.getId() +  " " +courseschedule.getCourseid() + "  "+ courseschedule.getWeek());
			coursescheduleList = coursescheduleService.selectCoursescheduleByWeek(courseschedule.getCourseid(), courseconfig.getId(), courseschedule.getWeek());

			System.out.println(coursescheduleList.size());
			//			转化成map
			periodDayMap(coursescheduleList);
			
			returnInfo = "";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
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


	//del entityfull Ajax
	public String deleteCoursescheduleFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
//		try{
//			String ids[] = coursescheduleIdsForDel.split(";");
//			for(int i=0; i < ids.length;i++){
//                                
//				Integer tempDelId = Integer.valueOf(ids[i]);                        
//				log.info(tempDelId);
//              
//                        
//                } catch (Exception e) {
//                        e.printStackTrace();
//                }
//                
//                returnJson.setFlag(0);
//                returnJson.setReason("删除的内部错误");
//                JSONObject jo = JSONObject.fromObject(returnJson);
//                this.returnStr = jo.toString();
                return SUCCESS;
        }



	
	@Override
	public Courseschedule getModel() {
		// TODO Auto-generated method stub
		return courseschedule;
	}


	public CoursescheduleService getCoursescheduleService() {
		return coursescheduleService;
	}


	public void setCoursescheduleService(CoursescheduleService coursescheduleService) {
		this.coursescheduleService = coursescheduleService;
	}


	public Courseschedule getCourseschedule() {
		return courseschedule;
	}


	public void setCourseschedule(Courseschedule courseschedule) {
		this.courseschedule = courseschedule;
	}


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


	public void setCoursescheduleFullList(
			List<CoursescheduleFull> coursescheduleFullList) {
		this.coursescheduleFullList = coursescheduleFullList;
	}


	public Integer getCoursescheduleid() {
		return coursescheduleid;
	}


	public void setCoursescheduleid(Integer coursescheduleid) {
		this.coursescheduleid = coursescheduleid;
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


	public HashMap<Integer, HashMap<Integer, Courseschedule>> getCoursescheduleDayPeriodMap() {
		return coursescheduleDayPeriodMap;
	}


	public void setCoursescheduleDayPeriodMap(
			HashMap<Integer, HashMap<Integer, Courseschedule>> coursescheduleDayPeriodMap) {
		this.coursescheduleDayPeriodMap = coursescheduleDayPeriodMap;
	}


	public String getLogprefix() {
		return logprefix;
	}


	public void setLogprefix(String logprefix) {
		this.logprefix = logprefix;
	}


	public String getCoursescheduleIdsForDel() {
		return coursescheduleIdsForDel;
	}


	public void setCoursescheduleIdsForDel(String coursescheduleIdsForDel) {
		this.coursescheduleIdsForDel = coursescheduleIdsForDel;
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


	public static Log getLog() {
		return log;
	}


	public HashMap<Integer, HashMap<Integer, Courseschedule>> getCourseschedulePeriodDayMap() {
		return courseschedulePeriodDayMap;
	}


	public void setCourseschedulePeriodDayMap(
			HashMap<Integer, HashMap<Integer, Courseschedule>> courseschedulePeriodDayMap) {
		this.courseschedulePeriodDayMap = courseschedulePeriodDayMap;
	}


	public List<Borrowperiod> getPeriodList() {
		return periodList;
	}


	public void setPeriodList(List<Borrowperiod> periodList) {
		this.periodList = periodList;
	}


	public List<Integer> getDayList() {
		return dayList;
	}


	public void setDayList(List<Integer> dayList) {
		this.dayList = dayList;
	}


	public HashMap<Integer, HashMap<Integer, ArrayList<Courseschedule>>> getCourseschedulePeriodDayMapList() {
		return courseschedulePeriodDayMapList;
	}


	public void setCourseschedulePeriodDayMapList(
			HashMap<Integer, HashMap<Integer, ArrayList<Courseschedule>>> courseschedulePeriodDayMapList) {
		this.courseschedulePeriodDayMapList = courseschedulePeriodDayMapList;
	}


	public HashMap<Integer, HashMap<Integer, ArrayList<CoursescheduleFull>>> getCoursescheduleFullPeriodDayMapList() {
		return coursescheduleFullPeriodDayMapList;
	}


	public void setCoursescheduleFullPeriodDayMapList(
			HashMap<Integer, HashMap<Integer, ArrayList<CoursescheduleFull>>> coursescheduleFullPeriodDayMapList) {
		this.coursescheduleFullPeriodDayMapList = coursescheduleFullPeriodDayMapList;
	}

	
        
        
}
