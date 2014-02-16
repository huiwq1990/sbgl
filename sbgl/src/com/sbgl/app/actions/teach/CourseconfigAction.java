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
import com.sbgl.app.services.teach.CourseconfigService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("CourseconfigAction")
public class CourseconfigAction extends ActionSupport implements SessionAware,ModelDriven<Courseconfig>{
	
	private static final Log log = LogFactory.getLog(CourseconfigAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private CourseconfigService courseconfigService;
	
	private Courseconfig courseconfig = new Courseconfig();//实例化一个模型
	private Courseconfig courseconfigModel = new Courseconfig();//实例化一个模型
	private CourseconfigFull courseconfigFull = new CourseconfigFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Courseconfig> courseconfigList = new ArrayList<Courseconfig>();
	List<CourseconfigFull> courseconfigFullList = new ArrayList<CourseconfigFull>();
	private Integer courseconfigid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";
	
	private int pageNo=1;
	private String callType;
	private Page page = new Page();
	private ReturnJson returnJson = new ReturnJson();
	private String courseconfigIdsForDel;
	
//  manage Courseconfig
	public String manageCourseconfig(){
		log.info(logprefix+"manageCourseconfigFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(courseconfigService.countCourseconfigRow());
		courseconfigList  = courseconfigService.selectCourseconfigByPage(page);
		
//		查询全部
//		courseconfigList  = courseconfigService.selectCourseconfigById();
		
	    if(courseconfigList == null){
			courseconfigList = new ArrayList<Courseconfig>();
		}
//		for(int i = 0; i < courseconfigList.size(); i++){
//			System.out.println("id="+courseconfigList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageCourseconfigFull(){
		log.info("exec action method:manageCourseconfigFull");
                
//      分页查询        
        if(pageNo <= 0){
			pageNo =1;
        }
        page.setTotalCount(courseconfigService.countCourseconfigRow());
        if(page.getTotalpage() < pageNo){
			pageNo = page.getTotalpage();
        }
        page.setPageNo(pageNo);
                
        courseconfigFullList  = courseconfigService.selectCourseconfigFullByConditionAndPage("", page);
               

        if(courseconfigFullList == null){
			courseconfigFullList = new ArrayList<CourseconfigFull>();
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

			
	public String addCourseconfig(){	
		log.info("Add Entity");

		try {
			Courseconfig temp = new Courseconfig();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, courseconfig);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			courseconfigService.addCourseconfig(temp);
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseconfigAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addCourseconfigAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Courseconfig temp = new Courseconfig();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, courseconfig);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			courseconfigService.addCourseconfig(temp);
			
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
			log.error("类CourseconfigAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		returnJson.setReason("添加失败");
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteCourseconfig( ){
		try{
			if(courseconfig.getId() != null && courseconfig.getId() > 0){
				courseconfigService.deleteCourseconfig(courseconfig.getId());
				actionMsg = getText("deleteCourseconfigSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteCourseconfigFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseconfigAction的方法：deleteCourseconfig错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteCourseconfigAjax( ){
		try{
			if(courseconfig.getId() != null && courseconfig.getId() >= 0){
				courseconfigService.deleteCourseconfig(courseconfig.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseconfigAction的方法：deleteCourseconfig错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteCourseconfigFull(){
		try {
		
			Integer getId = courseconfig.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Courseconfig temp = courseconfigService.selectCourseconfigById(getId);
			if (temp != null) {
				courseconfigService.deleteCourseconfig(getId);
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
	public String deleteCourseconfigFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			String ids[] = courseconfigIdsForDel.split(";");
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
                                Courseconfig temp = courseconfigService.selectCourseconfigById(tempDelId);                        
                                if (temp != null) {                        
                                        //其他操作                                        
                                        courseconfigService.deleteCourseconfig(tempDelId);                                        
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
	public String updateCourseconfig(){
		try {
			if(courseconfig.getId() != null && courseconfig.getId() > 0){				
				Courseconfig tempCourseconfig = courseconfigService.selectCourseconfigById(courseconfig.getId());
																				  								
												  								
												  								
												  								
												  								
												  								
								actionMsg = getText("viewCourseconfigSuccess");
			}else{
				actionMsg = getText("viewCourseconfigFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseconfigAction的方法：viewCourseconfig错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateCourseconfigAjax(){
		log.info(logprefix + "updateCourseconfigAjax,id="+courseconfig.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(courseconfig.getId() != null && courseconfig.getId() > 0){				
				Courseconfig tempCourseconfig = courseconfigService.selectCourseconfigById(courseconfig.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempCourseconfig.setSchoolyear(courseconfig.getSchoolyear());
  				tempCourseconfig.setSemester(courseconfig.getSemester());
  				tempCourseconfig.setFirstday(courseconfig.getFirstday());
  				tempCourseconfig.setLastday(courseconfig.getLastday());
  				tempCourseconfig.setFirstweekfirstday(courseconfig.getFirstweekfirstday());
  				tempCourseconfig.setStatus(courseconfig.getStatus());
 
				
				courseconfigService.updateCourseconfig(tempCourseconfig);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewCourseconfigSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewCourseconfigFail");
				log.info(logprefix + "updateCourseconfigAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseconfigAction的方法：viewCourseconfig错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editCourseconfig(){
		log.info(logprefix + "editCourseconfig");
			
		try {
			//实体的id可以为0
			if(courseconfig.getId() != null && courseconfig.getId() >= 0){				
				Courseconfig temCourseconfig = courseconfigService.selectCourseconfigById(courseconfig.getId());
				if(temCourseconfig != null){
					BeanUtils.copyProperties(courseconfigModel,temCourseconfig);	
					//actionMsg = getText("selectCourseconfigByIdSuccess");
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
			log.error("类CourseconfigAction的方法：selectCourseconfigById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editCourseconfigFull(){
		
		log.info(logprefix + "viewCourseconfig");
		
		try {
			if(courseconfig.getId() != null && courseconfig.getId() > 0){				
				CourseconfigFull temCourseconfigFull = courseconfigService.selectCourseconfigFullById(courseconfig.getId());
				BeanUtils.copyProperties(courseconfigFull,temCourseconfigFull);	
				actionMsg = getText("selectCourseconfigByIdSuccess");
			}else{
				actionMsg = getText("selectCourseconfigByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseconfigAction的方法：selectCourseconfigFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewCourseconfig(){
		log.info("viewCourseconfig");
		try {
			if(courseconfig.getId() != null && courseconfig.getId() > 0){				
				Courseconfig temCourseconfig = courseconfigService.selectCourseconfigById(courseconfig.getId());
				BeanUtils.copyProperties(courseconfigModel,temCourseconfig);	
				actionMsg = getText("selectCourseconfigByIdSuccess");
			}else{
				actionMsg = getText("selectCourseconfigByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseconfigAction的方法：selectCourseconfigById错误"+e);
		}


		return "error";

	}	

/**
 * view CourseconfigFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewCourseconfigFull() {
				
		try {
			int getId = courseconfig.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			CourseconfigFull temCourseconfigFull = courseconfigService.selectCourseconfigFullById(getId);				
			if(temCourseconfigFull!=null){				
				BeanUtils.copyProperties(courseconfigFull,temCourseconfigFull);
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
	public Courseconfig getModel() {
		// TODO Auto-generated method stub
		return courseconfig;
	}

//  
	public Courseconfig getCourseconfig() {
		return courseconfig;
	}
	
	public void setCourseconfig(Courseconfig courseconfig) {
		this.courseconfig = courseconfig;
	}
//  entityModel
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
	
	public int getCourseconfigid() {
		return courseconfigid;
	}

	public void setCourseconfigid(int courseconfigid) {
		this.courseconfigid = courseconfigid;
	}
		public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	public String getCourseconfigIdsForDel() {
                return courseconfigIdsForDel;
        }

        public void setCourseconfigIdsForDel(String courseconfigIdsForDel) {
                this.courseconfigIdsForDel = courseconfigIdsForDel;
        }
}
