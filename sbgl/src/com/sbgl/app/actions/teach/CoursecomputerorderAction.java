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
import com.sbgl.app.entity.*;
import com.sbgl.app.services.teach.CoursecomputerorderService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("CoursecomputerorderAction")
public class CoursecomputerorderAction extends ActionSupport implements SessionAware,ModelDriven<Coursecomputerorder>{
	
	private static final Log log = LogFactory.getLog(CoursecomputerorderAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private CoursecomputerorderService coursecomputerorderService;
	
	private Coursecomputerorder coursecomputerorder = new Coursecomputerorder();//实例化一个模型
	private Coursecomputerorder coursecomputerorderModel = new Coursecomputerorder();//实例化一个模型
	private CoursecomputerorderFull coursecomputerorderFull = new CoursecomputerorderFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Coursecomputerorder> coursecomputerorderList = new ArrayList<Coursecomputerorder>();
	List<CoursecomputerorderFull> coursecomputerorderFullList = new ArrayList<CoursecomputerorderFull>();
	private Integer coursecomputerorderid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";
	
	private int pageNo=1;
	private String callType;
	private Page page = new Page();
	private ReturnJson returnJson = new ReturnJson();
	private String coursecomputerorderIdsForDel;
	
//  manage Coursecomputerorder
	public String manageCoursecomputerorder(){
		log.info(logprefix+"manageCoursecomputerorderFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(coursecomputerorderService.countCoursecomputerorderRow());
		coursecomputerorderList  = coursecomputerorderService.selectCoursecomputerorderByPage(page);
		
//		查询全部
//		coursecomputerorderList  = coursecomputerorderService.selectCoursecomputerorderById();
		
	    if(coursecomputerorderList == null){
			coursecomputerorderList = new ArrayList<Coursecomputerorder>();
		}
//		for(int i = 0; i < coursecomputerorderList.size(); i++){
//			System.out.println("id="+coursecomputerorderList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageCoursecomputerorderFull(){
		log.info("exec action method:manageCoursecomputerorderFull");
                
//      分页查询        
        if(pageNo <= 0){
			pageNo =1;
        }
        page.setTotalCount(coursecomputerorderService.countCoursecomputerorderRow());
        if(page.getTotalpage() < pageNo){
			pageNo = page.getTotalpage();
        }
        page.setPageNo(pageNo);
                
        coursecomputerorderFullList  = coursecomputerorderService.selectCoursecomputerorderFullByConditionAndPage("", page);
               

        if(coursecomputerorderFullList == null){
			coursecomputerorderFullList = new ArrayList<CoursecomputerorderFull>();
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

			
	public String addCoursecomputerorder(){	
		log.info("Add Entity");

		try {
			Coursecomputerorder temp = new Coursecomputerorder();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, coursecomputerorder);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			coursecomputerorderService.addCoursecomputerorder(temp);
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursecomputerorderAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addCoursecomputerorderAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Coursecomputerorder temp = new Coursecomputerorder();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, coursecomputerorder);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			coursecomputerorderService.addCoursecomputerorder(temp);
			
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
			log.error("类CoursecomputerorderAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		returnJson.setReason("添加失败");
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteCoursecomputerorder( ){
		try{
			if(coursecomputerorder.getId() != null && coursecomputerorder.getId() > 0){
				coursecomputerorderService.deleteCoursecomputerorder(coursecomputerorder.getId());
				actionMsg = getText("deleteCoursecomputerorderSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteCoursecomputerorderFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursecomputerorderAction的方法：deleteCoursecomputerorder错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteCoursecomputerorderAjax( ){
		try{
			if(coursecomputerorder.getId() != null && coursecomputerorder.getId() >= 0){
				coursecomputerorderService.deleteCoursecomputerorder(coursecomputerorder.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursecomputerorderAction的方法：deleteCoursecomputerorder错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteCoursecomputerorderFull(){
		try {
		
			Integer getId = coursecomputerorder.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Coursecomputerorder temp = coursecomputerorderService.selectCoursecomputerorderById(getId);
			if (temp != null) {
				coursecomputerorderService.deleteCoursecomputerorder(getId);
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
	public String deleteCoursecomputerorderFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			String ids[] = coursecomputerorderIdsForDel.split(";");
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
                                Coursecomputerorder temp = coursecomputerorderService.selectCoursecomputerorderById(tempDelId);                        
                                if (temp != null) {                        
                                        //其他操作                                        
                                        coursecomputerorderService.deleteCoursecomputerorder(tempDelId);                                        
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
	public String updateCoursecomputerorder(){
		try {
			if(coursecomputerorder.getId() != null && coursecomputerorder.getId() > 0){				
				Coursecomputerorder tempCoursecomputerorder = coursecomputerorderService.selectCoursecomputerorderById(coursecomputerorder.getId());
																				  								
												  								
												  								
												  								
								actionMsg = getText("viewCoursecomputerorderSuccess");
			}else{
				actionMsg = getText("viewCoursecomputerorderFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursecomputerorderAction的方法：viewCoursecomputerorder错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateCoursecomputerorderAjax(){
		log.info(logprefix + "updateCoursecomputerorderAjax,id="+coursecomputerorder.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(coursecomputerorder.getId() != null && coursecomputerorder.getId() > 0){				
				Coursecomputerorder tempCoursecomputerorder = coursecomputerorderService.selectCoursecomputerorderById(coursecomputerorder.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempCoursecomputerorder.setSemesterid(coursecomputerorder.getSemesterid());
  				tempCoursecomputerorder.setCourseid(coursecomputerorder.getCourseid());
  				tempCoursecomputerorder.setComputerorderid(coursecomputerorder.getComputerorderid());
  				tempCoursecomputerorder.setStatus(coursecomputerorder.getStatus());
 
				
				coursecomputerorderService.updateCoursecomputerorder(tempCoursecomputerorder);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewCoursecomputerorderSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewCoursecomputerorderFail");
				log.info(logprefix + "updateCoursecomputerorderAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursecomputerorderAction的方法：viewCoursecomputerorder错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editCoursecomputerorder(){
		log.info(logprefix + "editCoursecomputerorder");
			
		try {
			//实体的id可以为0
			if(coursecomputerorder.getId() != null && coursecomputerorder.getId() >= 0){				
				Coursecomputerorder temCoursecomputerorder = coursecomputerorderService.selectCoursecomputerorderById(coursecomputerorder.getId());
				if(temCoursecomputerorder != null){
					BeanUtils.copyProperties(coursecomputerorderModel,temCoursecomputerorder);	
					//actionMsg = getText("selectCoursecomputerorderByIdSuccess");
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
			log.error("类CoursecomputerorderAction的方法：selectCoursecomputerorderById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editCoursecomputerorderFull(){
		
		log.info(logprefix + "viewCoursecomputerorder");
		
		try {
			if(coursecomputerorder.getId() != null && coursecomputerorder.getId() > 0){				
				CoursecomputerorderFull temCoursecomputerorderFull = coursecomputerorderService.selectCoursecomputerorderFullById(coursecomputerorder.getId());
				BeanUtils.copyProperties(coursecomputerorderFull,temCoursecomputerorderFull);	
				actionMsg = getText("selectCoursecomputerorderByIdSuccess");
			}else{
				actionMsg = getText("selectCoursecomputerorderByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursecomputerorderAction的方法：selectCoursecomputerorderFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewCoursecomputerorder(){
		log.info("viewCoursecomputerorder");
		try {
			if(coursecomputerorder.getId() != null && coursecomputerorder.getId() > 0){				
				Coursecomputerorder temCoursecomputerorder = coursecomputerorderService.selectCoursecomputerorderById(coursecomputerorder.getId());
				BeanUtils.copyProperties(coursecomputerorderModel,temCoursecomputerorder);	
				actionMsg = getText("selectCoursecomputerorderByIdSuccess");
			}else{
				actionMsg = getText("selectCoursecomputerorderByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CoursecomputerorderAction的方法：selectCoursecomputerorderById错误"+e);
		}


		return "error";

	}	

/**
 * view CoursecomputerorderFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewCoursecomputerorderFull() {
				
		try {
			int getId = coursecomputerorder.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			CoursecomputerorderFull temCoursecomputerorderFull = coursecomputerorderService.selectCoursecomputerorderFullById(getId);				
			if(temCoursecomputerorderFull!=null){				
				BeanUtils.copyProperties(coursecomputerorderFull,temCoursecomputerorderFull);
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
	public Coursecomputerorder getModel() {
		// TODO Auto-generated method stub
		return coursecomputerorder;
	}

//  
	public Coursecomputerorder getCoursecomputerorder() {
		return coursecomputerorder;
	}
	
	public void setCoursecomputerorder(Coursecomputerorder coursecomputerorder) {
		this.coursecomputerorder = coursecomputerorder;
	}
//  entityModel
	public Coursecomputerorder getCoursecomputerorderModel() {
		return coursecomputerorderModel;
	}
	
	public void setCoursecomputerorderModel(Coursecomputerorder coursecomputerorderModel) {
		this.coursecomputerorderModel = coursecomputerorderModel;
	}
	
	public CoursecomputerorderFull getCoursecomputerorderFull() {
		return coursecomputerorderFull;
	}
	
	public void setCoursecomputerorderFull(CoursecomputerorderFull coursecomputerorderFull) {
		this.coursecomputerorderFull = coursecomputerorderFull;
	}
	
	public List<Coursecomputerorder> getCoursecomputerorderList() {
		return coursecomputerorderList;
	}


	public void setCoursecomputerorderList(List<Coursecomputerorder> coursecomputerorderList) {
		this.coursecomputerorderList = coursecomputerorderList;
	}

	public List<CoursecomputerorderFull> getCoursecomputerorderFullList() {
		return coursecomputerorderFullList;
	}


	public void setCoursecomputerorderFullList(List<CoursecomputerorderFull> coursecomputerorderFullList) {
		this.coursecomputerorderFullList = coursecomputerorderFullList;
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
	
	public int getCoursecomputerorderid() {
		return coursecomputerorderid;
	}

	public void setCoursecomputerorderid(int coursecomputerorderid) {
		this.coursecomputerorderid = coursecomputerorderid;
	}
		public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	public String getCoursecomputerorderIdsForDel() {
                return coursecomputerorderIdsForDel;
        }

        public void setCoursecomputerorderIdsForDel(String coursecomputerorderIdsForDel) {
                this.coursecomputerorderIdsForDel = coursecomputerorderIdsForDel;
        }
}
