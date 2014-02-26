package com.sbgl.app.actions.message;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONObject;


import javax.annotation.Resource;
import javax.servlet.http.Cookie;

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
import com.sbgl.app.actions.computer.ComputerActionUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.message.NotificationService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("NotificationAction")
public class NotificationAction extends BaseAction implements ModelDriven<Notification>{
	
	private static final Log log = LogFactory.getLog(NotificationAction.class);

	
	//Service	
	@Resource
	private NotificationService notificationService;
	private Integer notificationid; //entity full 的id属性名称	
	private Notification notification = new Notification();//实例化一个模型
	private Notification notificationModel = new Notification();//实例化一个模型
	private NotificationFull notificationFull = new NotificationFull();//实例化一个模型	
	private List<Notification> notificationList = new ArrayList<Notification>();
	private List<NotificationFull> notificationFullList = new ArrayList<NotificationFull>();
		
	private String logprefix = "exec action method:";
	

	
	private String notificationIdsForDel;


	
		
//	/**
//	 * 发送通知
//	 * @return
//	 */
//	public String sendNotification(){
//		
//		
//	}
	
//	查看站内信列表
	public String toNotificationInbox(){
		try{
			
		int uid = this.getCurrentUserId();
		
		String notifySql = " where receiverid = "+uid;
		notificationList  = notificationService.selectNotificationByCondition(notifySql);
		if(notificationList == null){
			notificationList = new ArrayList<Notification>();
		}
		this.totalcount = notificationList.size();
		this.setPageInfo(totalcount);
		
		notificationList  = notificationService.selectNotificationByConditionAndPage(notifySql, page);
		if(notificationList == null){
			notificationList = new ArrayList<Notification>();
		}
		
		return SUCCESS;
		
		}catch(Exception e){
			e.printStackTrace();
			log.error("类NotificationAction的方法：addBbstagfavourite错误"+e);
		}
		
		this.actionMsg = "内部错误";
		return "error";
         

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
		
		/*
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
                */
               
             
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
  				tempNotification.setSenderid(notification.getSenderid());
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

	@Override
	public Notification getModel() {
		// TODO Auto-generated method stub
		return notification;
	}

}
