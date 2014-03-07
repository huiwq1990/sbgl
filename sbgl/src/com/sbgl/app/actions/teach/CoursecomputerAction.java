package com.sbgl.app.actions.teach;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONArray;
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
import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.teach.CoursecomputerService;
import com.sbgl.app.services.teach.CourseconfigService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("CoursecomputerAction")
public class CoursecomputerAction extends BaseAction implements ModelDriven<Coursecomputer>{
	
	private static final Log log = LogFactory.getLog(CoursecomputerAction.class);

	
	//Service	
	@Resource
	private CoursecomputerService coursecomputerService;
	private Integer coursecomputerid; //entity full 的id属性名称		
	private Coursecomputer coursecomputer = new Coursecomputer();//实例化一个模型
	private Coursecomputer coursecomputerModel = new Coursecomputer();//实例化一个模型
	private CoursecomputerFull coursecomputerFull = new CoursecomputerFull();//实例化一个模型
	private List<Coursecomputer> coursecomputerList = new ArrayList<Coursecomputer>();
	private List<CoursecomputerFull> coursecomputerFullList = new ArrayList<CoursecomputerFull>();

	
	@Resource
	private CourseconfigService courseconfigService;
	private Courseconfig courseconfig = new Courseconfig();//实例化一个模型
	
	
	private String logprefix = "exec action method:";
	
//	选择的课程 选择的星期
	private int selcourseid;
	private int selweek;
	private int selday;
	private int selperiod;

	private String coursecomputerIdsForDel;
	
	
//  manage Coursecomputer
	public String manageCoursecomputer(){
		log.info(logprefix+"manageCoursecomputerFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(coursecomputerService.countCoursecomputerRow());
		coursecomputerList  = coursecomputerService.selectCoursecomputerByPage(page);
		
//		查询全部
//		coursecomputerList  = coursecomputerService.selectCoursecomputerById();
		
	    if(coursecomputerList == null){
			coursecomputerList = new ArrayList<Coursecomputer>();
		}
//		for(int i = 0; i < coursecomputerList.size(); i++){
//			System.out.println("id="+coursecomputerList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageCoursecomputerFull(){
		log.info("exec action method:manageCoursecomputerFull");
                
//      分页查询        
        if(pageNo <= 0){
			pageNo =1;
        }
        page.setTotalCount(coursecomputerService.countCoursecomputerRow());
        if(page.getTotalpage() < pageNo){
			pageNo = page.getTotalpage();
        }
        page.setPageNo(pageNo);
                
        coursecomputerFullList  = coursecomputerService.selectCoursecomputerFullByConditionAndPage("", page);
               

        if(coursecomputerFullList == null){
			coursecomputerFullList = new ArrayList<CoursecomputerFull>();
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
	public String addCoursecomputerAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Coursecomputer temp = new Coursecomputer();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, coursecomputer);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			coursecomputerService.addCoursecomputer(temp);
			
			returnJson.setFlag(1);
			returnJson.setReason("添加成功");
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursecomputerAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		returnJson.setReason("添加失败");
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteCoursecomputer( ){
		try{
			if(coursecomputer.getId() != null && coursecomputer.getId() > 0){
				coursecomputerService.deleteCoursecomputer(coursecomputer.getId());
				actionMsg = getText("deleteCoursecomputerSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteCoursecomputerFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursecomputerAction的方法：deleteCoursecomputer错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteCoursecomputerAjax( ){
		try{
			if(coursecomputer.getId() != null && coursecomputer.getId() >= 0){
				coursecomputerService.deleteCoursecomputer(coursecomputer.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursecomputerAction的方法：deleteCoursecomputer错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteCoursecomputerFull(){
		try {
		
			Integer getId = coursecomputer.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Coursecomputer temp = coursecomputerService.selectCoursecomputerById(getId);
			if (temp != null) {
				coursecomputerService.deleteCoursecomputer(getId);
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
	
/*	//del entityfull Ajax
	public String deleteCoursecomputerFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			String ids[] = coursecomputerIdsForDel.split(";");
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
                                Coursecomputer temp = coursecomputerService.selectCoursecomputerById(tempDelId);                        
                                if (temp != null) {                        
                                        //其他操作                                        
                                        coursecomputerService.deleteCoursecomputer(tempDelId);                                        
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
*/

	
	
	//ajax 修改
	public String updateCoursecomputerAjax(){
		log.info(logprefix + "updateCoursecomputerAjax,id="+coursecomputer.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(coursecomputer.getId() != null && coursecomputer.getId() > 0){				
				Coursecomputer tempCoursecomputer = coursecomputerService.selectCoursecomputerById(coursecomputer.getId());
				
				
 
				
				coursecomputerService.updateCoursecomputer(tempCoursecomputer);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewCoursecomputerSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewCoursecomputerFail");
				log.info(logprefix + "updateCoursecomputerAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursecomputerAction的方法：viewCoursecomputer错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	public  String selectCoursecomputerByPeriod(){
		try{
//		选择的课程 选择的星期
		courseconfig = courseconfigService.getCurrentCourseconfig();
		if(courseconfig == null){
			returnInfo = "没有设置学期信息";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
			return SUCCESS;
		}
		
		log.info("课程序号："+selcourseid+"；学期信息："+courseconfig.getId()+"；周："+selweek+";日："+selday+";时间段："+selperiod);
		coursecomputerFullList = coursecomputerService.selectCoursecomputerFullByPeriod(selcourseid, courseconfig.getId(), selweek, selday, selperiod, CommonConfig.languagech);
		
		log.info(coursecomputerFullList.size());
		for(CoursecomputerFull f: coursecomputerFullList){
			System.out.println(f.getCmname());
		}
		
		returnInfo = "添加成功";
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
		return SUCCESS;
		
		 } catch (Exception e) {
             e.printStackTrace();
		 }
		 
		returnInfo = "获取信息失败";
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
		return SUCCESS;
	}
	
	public  String selectCoursecomputerByPeriodAjax(){
		try{
//		选择的课程 选择的星期
		courseconfig = courseconfigService.getCurrentCourseconfig();
		if(courseconfig == null){
			returnInfo = "没有设置学期信息";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
			return SUCCESS;
		}
		
		coursecomputerFullList = coursecomputerService.selectCoursecomputerFullByPeriod(selcourseid, courseconfig.getId(), selweek, selday, selperiod, CommonConfig.languagech);
		JSONArray jo = JSONArray.fromObject(coursecomputerFullList);
		
		String j= jo.toString();
		
		returnInfo = "添加成功";
		log.info(returnInfo);
//		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo,j);
		this.returnStr = j;
		return SUCCESS;
		
		 } catch (Exception e) {
             e.printStackTrace();
		 }
		 
		returnInfo = "获取信息失败";
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
		return SUCCESS;
	}
	
	@Override
	public Coursecomputer getModel() {
		// TODO Auto-generated method stub
		return coursecomputer;
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

	public String getLogprefix() {
		return logprefix;
	}

	public void setLogprefix(String logprefix) {
		this.logprefix = logprefix;
	}

	public int getSelcourseid() {
		return selcourseid;
	}

	public void setSelcourseid(int selcourseid) {
		this.selcourseid = selcourseid;
	}

	public int getSelweek() {
		return selweek;
	}

	public void setSelweek(int selweek) {
		this.selweek = selweek;
	}

	public int getSelday() {
		return selday;
	}

	public void setSelday(int selday) {
		this.selday = selday;
	}

	public int getSelperiod() {
		return selperiod;
	}

	public void setSelperiod(int selperiod) {
		this.selperiod = selperiod;
	}

	public String getCoursecomputerIdsForDel() {
		return coursecomputerIdsForDel;
	}

	public void setCoursecomputerIdsForDel(String coursecomputerIdsForDel) {
		this.coursecomputerIdsForDel = coursecomputerIdsForDel;
	}

	public static Log getLog() {
		return log;
	}

	
	
	
}
