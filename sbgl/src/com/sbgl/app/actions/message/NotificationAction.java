package com.sbgl.app.actions.message;

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
import com.sbgl.app.services.message.NotificationService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("NotificationAction")
public class NotificationAction extends ActionSupport implements SessionAware,ModelDriven<Notification>{
	
	private static final Log log = LogFactory.getLog(NotificationAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private NotificationService notificationService;
	
	private Notification notification = new Notification();//实例化一个模型
	private Notification notificationModel = new Notification();//实例化一个模型
	private NotificationFull notificationFull = new NotificationFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Notification> notificationList = new ArrayList<Notification>();
	List<NotificationFull> notificationFullList = new ArrayList<NotificationFull>();
	private Integer notificationid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";
	
	private int pageNo=1;
	private String callType;
	private Page page = new Page();
	private ReturnJson returnJson = new ReturnJson();
	private String notificationIdsForDel;
	
//  manage Notification
	public String manageNotification(){
		log.info(logprefix+"manageNotificationFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(notificationService.countNotificationRow());
		notificationList  = notificationService.selectNotificationByPage(page);
		
//		查询全部
//		notificationList  = notificationService.selectNotificationById();
		
	    if(notificationList == null){
			notificationList = new ArrayList<Notification>();
		}
//		for(int i = 0; i < notificationList.size(); i++){
//			System.out.println("id="+notificationList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageNotificationFull(){
		log.info("exec action method:manageNotificationFull");
                
//      分页查询        
        if(pageNo <= 0){
			pageNo =1;
        }
        page.setTotalCount(notificationService.countNotificationRow());
        if(page.getTotalpage() < pageNo){
			pageNo = page.getTotalpage();
        }
        page.setPageNo(pageNo);
                
        notificationFullList  = notificationService.selectNotificationFullByConditionAndPage("", page);
               

        if(notificationFullList == null){
			notificationFullList = new ArrayList<NotificationFull>();
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

			
	public String addNotification(){	
		log.info("Add Entity");

		try {
			Notification temp = new Notification();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, notification);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			notificationService.addNotification(temp);
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类NotificationAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addNotificationAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Notification temp = new Notification();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, notification);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			notificationService.addNotification(temp);
			
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
			log.error("类NotificationAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		returnJson.setReason("添加失败");
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteNotification( ){
		try{
			if(notification.getId() != null && notification.getId() > 0){
				notificationService.deleteNotification(notification.getId());
				actionMsg = getText("deleteNotificationSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteNotificationFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类NotificationAction的方法：deleteNotification错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteNotificationAjax( ){
		try{
			if(notification.getId() != null && notification.getId() >= 0){
				notificationService.deleteNotification(notification.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类NotificationAction的方法：deleteNotification错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteNotificationFull(){
		try {
		
			Integer getId = notification.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Notification temp = notificationService.selectNotificationById(getId);
			if (temp != null) {
				notificationService.deleteNotification(getId);
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
	public String deleteNotificationFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			String ids[] = notificationIdsForDel.split(";");
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
                                Notification temp = notificationService.selectNotificationById(tempDelId);                        
                                if (temp != null) {                        
                                        //其他操作                                        
                                        notificationService.deleteNotification(tempDelId);                                        
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
	public String updateNotification(){
		try {
			if(notification.getId() != null && notification.getId() > 0){				
				Notification tempNotification = notificationService.selectNotificationById(notification.getId());
																				  								
												  								
												  								
												  								
												  								
												  								
												  								
												  								
								actionMsg = getText("viewNotificationSuccess");
			}else{
				actionMsg = getText("viewNotificationFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类NotificationAction的方法：viewNotification错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateNotificationAjax(){
		log.info(logprefix + "updateNotificationAjax,id="+notification.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(notification.getId() != null && notification.getId() > 0){				
				Notification tempNotification = notificationService.selectNotificationById(notification.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempNotification.setTitle(notification.getTitle());
  				tempNotification.setContent(notification.getContent());
  				tempNotification.setSenderrid(notification.getSenderrid());
  				tempNotification.setReceiverid(notification.getReceiverid());
  				tempNotification.setSendtime(notification.getSendtime());
  				tempNotification.setReadstatus(notification.getReadstatus());
  				tempNotification.setModeltype(notification.getModeltype());
  				tempNotification.setStatus(notification.getStatus());
 
				
				notificationService.updateNotification(tempNotification);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewNotificationSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewNotificationFail");
				log.info(logprefix + "updateNotificationAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类NotificationAction的方法：viewNotification错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editNotification(){
		log.info(logprefix + "editNotification");
			
		try {
			//实体的id可以为0
			if(notification.getId() != null && notification.getId() >= 0){				
				Notification temNotification = notificationService.selectNotificationById(notification.getId());
				if(temNotification != null){
					BeanUtils.copyProperties(notificationModel,temNotification);	
					//actionMsg = getText("selectNotificationByIdSuccess");
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
			log.error("类NotificationAction的方法：selectNotificationById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editNotificationFull(){
		
		log.info(logprefix + "viewNotification");
		
		try {
			if(notification.getId() != null && notification.getId() > 0){				
				NotificationFull temNotificationFull = notificationService.selectNotificationFullById(notification.getId());
				BeanUtils.copyProperties(notificationFull,temNotificationFull);	
				actionMsg = getText("selectNotificationByIdSuccess");
			}else{
				actionMsg = getText("selectNotificationByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类NotificationAction的方法：selectNotificationFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewNotification(){
		log.info("viewNotification");
		try {
			if(notification.getId() != null && notification.getId() > 0){				
				Notification temNotification = notificationService.selectNotificationById(notification.getId());
				BeanUtils.copyProperties(notificationModel,temNotification);	
				actionMsg = getText("selectNotificationByIdSuccess");
			}else{
				actionMsg = getText("selectNotificationByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类NotificationAction的方法：selectNotificationById错误"+e);
		}


		return "error";

	}	

/**
 * view NotificationFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewNotificationFull() {
				
		try {
			int getId = notification.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			NotificationFull temNotificationFull = notificationService.selectNotificationFullById(getId);				
			if(temNotificationFull!=null){				
				BeanUtils.copyProperties(notificationFull,temNotificationFull);
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
	//根据senderid查询实体
	public String selectNotificationByLoginuserId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		notificationList  = notificationService.selectNotificationAll();
		for(int i = 0; i < notificationList.size(); i++){
			System.out.println("id="+notificationList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据receiverid查询实体
	public String selectNotificationByLoginuserId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		notificationList  = notificationService.selectNotificationAll();
		for(int i = 0; i < notificationList.size(); i++){
			System.out.println("id="+notificationList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据senderid查询实体full
	public String selectNotificationFullByLoginuserId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		notificationFullList  = notificationService.selectNotificationFullByLoginuserId(userId);
		for(int i = 0; i < notificationFullList.size(); i++){
			//System.out.println("id="+notificationFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}
	//根据receiverid查询实体full
	public String selectNotificationFullByLoginuserId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		notificationFullList  = notificationService.selectNotificationFullByLoginuserId(userId);
		for(int i = 0; i < notificationFullList.size(); i++){
			//System.out.println("id="+notificationFullList.get(i).getLoginusername());
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
	public Notification getModel() {
		// TODO Auto-generated method stub
		return notification;
	}

//  
	public Notification getNotification() {
		return notification;
	}
	
	public void setNotification(Notification notification) {
		this.notification = notification;
	}
//  entityModel
	public Notification getNotificationModel() {
		return notificationModel;
	}
	
	public void setNotificationModel(Notification notificationModel) {
		this.notificationModel = notificationModel;
	}
	
	public NotificationFull getNotificationFull() {
		return notificationFull;
	}
	
	public void setNotificationFull(NotificationFull notificationFull) {
		this.notificationFull = notificationFull;
	}
	
	public List<Notification> getNotificationList() {
		return notificationList;
	}


	public void setNotificationList(List<Notification> notificationList) {
		this.notificationList = notificationList;
	}

	public List<NotificationFull> getNotificationFullList() {
		return notificationFullList;
	}


	public void setNotificationFullList(List<NotificationFull> notificationFullList) {
		this.notificationFullList = notificationFullList;
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
	
	public int getNotificationid() {
		return notificationid;
	}

	public void setNotificationid(int notificationid) {
		this.notificationid = notificationid;
	}
		public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	public String getNotificationIdsForDel() {
                return notificationIdsForDel;
        }

        public void setNotificationIdsForDel(String notificationIdsForDel) {
                this.notificationIdsForDel = notificationIdsForDel;
        }
}
