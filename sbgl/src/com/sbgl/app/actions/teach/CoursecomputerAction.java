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
import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.teach.CoursecomputerService;
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

	private String logprefix = "exec action method:";
	

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
//修改
	public String updateCoursecomputer(){
		try {
			if(coursecomputer.getId() != null && coursecomputer.getId() > 0){				
				Coursecomputer tempCoursecomputer = coursecomputerService.selectCoursecomputerById(coursecomputer.getId());
																				  								
												  								
								actionMsg = getText("viewCoursecomputerSuccess");
			}else{
				actionMsg = getText("viewCoursecomputerFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursecomputerAction的方法：viewCoursecomputer错误"+e);
		}

		return "error";
	}
	
	
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
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editCoursecomputer(){
		log.info(logprefix + "editCoursecomputer");
			
		try {
			//实体的id可以为0
			if(coursecomputer.getId() != null && coursecomputer.getId() >= 0){				
				Coursecomputer temCoursecomputer = coursecomputerService.selectCoursecomputerById(coursecomputer.getId());
				if(temCoursecomputer != null){
					BeanUtils.copyProperties(coursecomputerModel,temCoursecomputer);	
					//actionMsg = getText("selectCoursecomputerByIdSuccess");
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
			log.error("类CoursecomputerAction的方法：selectCoursecomputerById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editCoursecomputerFull(){
		
		log.info(logprefix + "viewCoursecomputer");
		
		try {
			if(coursecomputer.getId() != null && coursecomputer.getId() > 0){				
				CoursecomputerFull temCoursecomputerFull = coursecomputerService.selectCoursecomputerFullById(coursecomputer.getId());
				BeanUtils.copyProperties(coursecomputerFull,temCoursecomputerFull);	
				actionMsg = getText("selectCoursecomputerByIdSuccess");
			}else{
				actionMsg = getText("selectCoursecomputerByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursecomputerAction的方法：selectCoursecomputerFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewCoursecomputer(){
		log.info("viewCoursecomputer");
		try {
			if(coursecomputer.getId() != null && coursecomputer.getId() > 0){				
				Coursecomputer temCoursecomputer = coursecomputerService.selectCoursecomputerById(coursecomputer.getId());
				BeanUtils.copyProperties(coursecomputerModel,temCoursecomputer);	
				actionMsg = getText("selectCoursecomputerByIdSuccess");
			}else{
				actionMsg = getText("selectCoursecomputerByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursecomputerAction的方法：selectCoursecomputerById错误"+e);
		}


		return "error";

	}	

/**
 * view CoursecomputerFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewCoursecomputerFull() {
				
		try {
			int getId = coursecomputer.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			CoursecomputerFull temCoursecomputerFull = coursecomputerService.selectCoursecomputerFullById(getId);				
			if(temCoursecomputerFull!=null){				
				BeanUtils.copyProperties(coursecomputerFull,temCoursecomputerFull);
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

	@Override
	public Coursecomputer getModel() {
		// TODO Auto-generated method stub
		return coursecomputer;
	}

	
	
	
}
