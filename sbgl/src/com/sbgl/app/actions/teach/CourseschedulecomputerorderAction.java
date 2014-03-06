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
import com.sbgl.app.services.teach.CourseschedulecomputerorderService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("CourseschedulecomputerorderAction")
public class CourseschedulecomputerorderAction extends ActionSupport implements SessionAware,ModelDriven<Courseschedulecomputerorder>{
	
	private static final Log log = LogFactory.getLog(CourseschedulecomputerorderAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private CourseschedulecomputerorderService courseschedulecomputerorderService;
	
	private Courseschedulecomputerorder courseschedulecomputerorder = new Courseschedulecomputerorder();//实例化一个模型
	private Courseschedulecomputerorder courseschedulecomputerorderModel = new Courseschedulecomputerorder();//实例化一个模型
	private CourseschedulecomputerorderFull courseschedulecomputerorderFull = new CourseschedulecomputerorderFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Courseschedulecomputerorder> courseschedulecomputerorderList = new ArrayList<Courseschedulecomputerorder>();
	List<CourseschedulecomputerorderFull> courseschedulecomputerorderFullList = new ArrayList<CourseschedulecomputerorderFull>();
	private Integer courseschedulecomputerorderid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";
	
	private int pageNo=1;
	private String callType;
	private Page page = new Page();
	private ReturnJson returnJson = new ReturnJson();
	private String courseschedulecomputerorderIdsForDel;
	
//  manage Courseschedulecomputerorder
	public String manageCourseschedulecomputerorder(){
		log.info(logprefix+"manageCourseschedulecomputerorderFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(courseschedulecomputerorderService.countCourseschedulecomputerorderRow());
		courseschedulecomputerorderList  = courseschedulecomputerorderService.selectCourseschedulecomputerorderByPage(page);
		
//		查询全部
//		courseschedulecomputerorderList  = courseschedulecomputerorderService.selectCourseschedulecomputerorderById();
		
	    if(courseschedulecomputerorderList == null){
			courseschedulecomputerorderList = new ArrayList<Courseschedulecomputerorder>();
		}
//		for(int i = 0; i < courseschedulecomputerorderList.size(); i++){
//			System.out.println("id="+courseschedulecomputerorderList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageCourseschedulecomputerorderFull(){
		log.info("exec action method:manageCourseschedulecomputerorderFull");
                
//      分页查询        
        if(pageNo <= 0){
			pageNo =1;
        }
        page.setTotalCount(courseschedulecomputerorderService.countCourseschedulecomputerorderRow());
        if(page.getTotalpage() < pageNo){
			pageNo = page.getTotalpage();
        }
        page.setPageNo(pageNo);
                
        courseschedulecomputerorderFullList  = courseschedulecomputerorderService.selectCourseschedulecomputerorderFullByConditionAndPage("", page);
               

        if(courseschedulecomputerorderFullList == null){
			courseschedulecomputerorderFullList = new ArrayList<CourseschedulecomputerorderFull>();
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

			
	public String addCourseschedulecomputerorder(){	
		log.info("Add Entity");

		try {
			Courseschedulecomputerorder temp = new Courseschedulecomputerorder();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, courseschedulecomputerorder);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			courseschedulecomputerorderService.addCourseschedulecomputerorder(temp);
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseschedulecomputerorderAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addCourseschedulecomputerorderAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Courseschedulecomputerorder temp = new Courseschedulecomputerorder();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, courseschedulecomputerorder);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			courseschedulecomputerorderService.addCourseschedulecomputerorder(temp);
			
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
			log.error("类CourseschedulecomputerorderAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		returnJson.setReason("添加失败");
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteCourseschedulecomputerorder( ){
		try{
			if(courseschedulecomputerorder.getId() != null && courseschedulecomputerorder.getId() > 0){
				courseschedulecomputerorderService.deleteCourseschedulecomputerorder(courseschedulecomputerorder.getId());
				actionMsg = getText("deleteCourseschedulecomputerorderSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteCourseschedulecomputerorderFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseschedulecomputerorderAction的方法：deleteCourseschedulecomputerorder错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteCourseschedulecomputerorderAjax( ){
		try{
			if(courseschedulecomputerorder.getId() != null && courseschedulecomputerorder.getId() >= 0){
				courseschedulecomputerorderService.deleteCourseschedulecomputerorder(courseschedulecomputerorder.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseschedulecomputerorderAction的方法：deleteCourseschedulecomputerorder错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteCourseschedulecomputerorderFull(){
		try {
		
			Integer getId = courseschedulecomputerorder.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Courseschedulecomputerorder temp = courseschedulecomputerorderService.selectCourseschedulecomputerorderById(getId);
			if (temp != null) {
				courseschedulecomputerorderService.deleteCourseschedulecomputerorder(getId);
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
	public String deleteCourseschedulecomputerorderFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			String ids[] = courseschedulecomputerorderIdsForDel.split(";");
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
                                Courseschedulecomputerorder temp = courseschedulecomputerorderService.selectCourseschedulecomputerorderById(tempDelId);                        
                                if (temp != null) {                        
                                        //其他操作                                        
                                        courseschedulecomputerorderService.deleteCourseschedulecomputerorder(tempDelId);                                        
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
	public String updateCourseschedulecomputerorder(){
		try {
			if(courseschedulecomputerorder.getId() != null && courseschedulecomputerorder.getId() > 0){				
				Courseschedulecomputerorder tempCourseschedulecomputerorder = courseschedulecomputerorderService.selectCourseschedulecomputerorderById(courseschedulecomputerorder.getId());
																				  								
												  								
												  								
								actionMsg = getText("viewCourseschedulecomputerorderSuccess");
			}else{
				actionMsg = getText("viewCourseschedulecomputerorderFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseschedulecomputerorderAction的方法：viewCourseschedulecomputerorder错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateCourseschedulecomputerorderAjax(){
		log.info(logprefix + "updateCourseschedulecomputerorderAjax,id="+courseschedulecomputerorder.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(courseschedulecomputerorder.getId() != null && courseschedulecomputerorder.getId() > 0){				
				Courseschedulecomputerorder tempCourseschedulecomputerorder = courseschedulecomputerorderService.selectCourseschedulecomputerorderById(courseschedulecomputerorder.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempCourseschedulecomputerorder.setComputercoursescheduleid(courseschedulecomputerorder.getComputercoursescheduleid());
  				tempCourseschedulecomputerorder.setComputerorderid(courseschedulecomputerorder.getComputerorderid());
  				tempCourseschedulecomputerorder.setStatus(courseschedulecomputerorder.getStatus());
 
				
				courseschedulecomputerorderService.updateCourseschedulecomputerorder(tempCourseschedulecomputerorder);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewCourseschedulecomputerorderSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewCourseschedulecomputerorderFail");
				log.info(logprefix + "updateCourseschedulecomputerorderAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseschedulecomputerorderAction的方法：viewCourseschedulecomputerorder错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editCourseschedulecomputerorder(){
		log.info(logprefix + "editCourseschedulecomputerorder");
			
		try {
			//实体的id可以为0
			if(courseschedulecomputerorder.getId() != null && courseschedulecomputerorder.getId() >= 0){				
				Courseschedulecomputerorder temCourseschedulecomputerorder = courseschedulecomputerorderService.selectCourseschedulecomputerorderById(courseschedulecomputerorder.getId());
				if(temCourseschedulecomputerorder != null){
					BeanUtils.copyProperties(courseschedulecomputerorderModel,temCourseschedulecomputerorder);	
					//actionMsg = getText("selectCourseschedulecomputerorderByIdSuccess");
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
			log.error("类CourseschedulecomputerorderAction的方法：selectCourseschedulecomputerorderById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editCourseschedulecomputerorderFull(){
		
		log.info(logprefix + "viewCourseschedulecomputerorder");
		
		try {
			if(courseschedulecomputerorder.getId() != null && courseschedulecomputerorder.getId() > 0){				
				CourseschedulecomputerorderFull temCourseschedulecomputerorderFull = courseschedulecomputerorderService.selectCourseschedulecomputerorderFullById(courseschedulecomputerorder.getId());
				BeanUtils.copyProperties(courseschedulecomputerorderFull,temCourseschedulecomputerorderFull);	
				actionMsg = getText("selectCourseschedulecomputerorderByIdSuccess");
			}else{
				actionMsg = getText("selectCourseschedulecomputerorderByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseschedulecomputerorderAction的方法：selectCourseschedulecomputerorderFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewCourseschedulecomputerorder(){
		log.info("viewCourseschedulecomputerorder");
		try {
			if(courseschedulecomputerorder.getId() != null && courseschedulecomputerorder.getId() > 0){				
				Courseschedulecomputerorder temCourseschedulecomputerorder = courseschedulecomputerorderService.selectCourseschedulecomputerorderById(courseschedulecomputerorder.getId());
				BeanUtils.copyProperties(courseschedulecomputerorderModel,temCourseschedulecomputerorder);	
				actionMsg = getText("selectCourseschedulecomputerorderByIdSuccess");
			}else{
				actionMsg = getText("selectCourseschedulecomputerorderByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseschedulecomputerorderAction的方法：selectCourseschedulecomputerorderById错误"+e);
		}


		return "error";

	}	

/**
 * view CourseschedulecomputerorderFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewCourseschedulecomputerorderFull() {
				
		try {
			int getId = courseschedulecomputerorder.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			CourseschedulecomputerorderFull temCourseschedulecomputerorderFull = courseschedulecomputerorderService.selectCourseschedulecomputerorderFullById(getId);				
			if(temCourseschedulecomputerorderFull!=null){				
				BeanUtils.copyProperties(courseschedulecomputerorderFull,temCourseschedulecomputerorderFull);
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
	//根据computerorderid查询实体
	public String selectCourseschedulecomputerorderByComputerorderId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		courseschedulecomputerorderList  = courseschedulecomputerorderService.selectCourseschedulecomputerorderAll();
		for(int i = 0; i < courseschedulecomputerorderList.size(); i++){
			System.out.println("id="+courseschedulecomputerorderList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据computerorderid查询实体full
	public String selectCourseschedulecomputerorderFullByComputerorderId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		courseschedulecomputerorderFullList  = courseschedulecomputerorderService.selectCourseschedulecomputerorderFullByComputerorderId(userId);
		for(int i = 0; i < courseschedulecomputerorderFullList.size(); i++){
			//System.out.println("id="+courseschedulecomputerorderFullList.get(i).getLoginusername());
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
	public Courseschedulecomputerorder getModel() {
		// TODO Auto-generated method stub
		return courseschedulecomputerorder;
	}

//  
	public Courseschedulecomputerorder getCourseschedulecomputerorder() {
		return courseschedulecomputerorder;
	}
	
	public void setCourseschedulecomputerorder(Courseschedulecomputerorder courseschedulecomputerorder) {
		this.courseschedulecomputerorder = courseschedulecomputerorder;
	}
//  entityModel
	public Courseschedulecomputerorder getCourseschedulecomputerorderModel() {
		return courseschedulecomputerorderModel;
	}
	
	public void setCourseschedulecomputerorderModel(Courseschedulecomputerorder courseschedulecomputerorderModel) {
		this.courseschedulecomputerorderModel = courseschedulecomputerorderModel;
	}
	
	public CourseschedulecomputerorderFull getCourseschedulecomputerorderFull() {
		return courseschedulecomputerorderFull;
	}
	
	public void setCourseschedulecomputerorderFull(CourseschedulecomputerorderFull courseschedulecomputerorderFull) {
		this.courseschedulecomputerorderFull = courseschedulecomputerorderFull;
	}
	
	public List<Courseschedulecomputerorder> getCourseschedulecomputerorderList() {
		return courseschedulecomputerorderList;
	}


	public void setCourseschedulecomputerorderList(List<Courseschedulecomputerorder> courseschedulecomputerorderList) {
		this.courseschedulecomputerorderList = courseschedulecomputerorderList;
	}

	public List<CourseschedulecomputerorderFull> getCourseschedulecomputerorderFullList() {
		return courseschedulecomputerorderFullList;
	}


	public void setCourseschedulecomputerorderFullList(List<CourseschedulecomputerorderFull> courseschedulecomputerorderFullList) {
		this.courseschedulecomputerorderFullList = courseschedulecomputerorderFullList;
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
	
	public int getCourseschedulecomputerorderid() {
		return courseschedulecomputerorderid;
	}

	public void setCourseschedulecomputerorderid(int courseschedulecomputerorderid) {
		this.courseschedulecomputerorderid = courseschedulecomputerorderid;
	}
		public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	public String getCourseschedulecomputerorderIdsForDel() {
                return courseschedulecomputerorderIdsForDel;
        }

        public void setCourseschedulecomputerorderIdsForDel(String courseschedulecomputerorderIdsForDel) {
                this.courseschedulecomputerorderIdsForDel = courseschedulecomputerorderIdsForDel;
        }
}
