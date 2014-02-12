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
import com.sbgl.app.services.message.MessageService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("MessageAction")
public class MessageAction extends ActionSupport implements SessionAware,ModelDriven<Message>{
	
	private static final Log log = LogFactory.getLog(MessageAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private MessageService messageService;
	
	private Message message = new Message();//实例化一个模型
	private Message messageModel = new Message();//实例化一个模型
	private MessageFull messageFull = new MessageFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Message> messageList = new ArrayList<Message>();
	List<MessageFull> messageFullList = new ArrayList<MessageFull>();
	private Integer messageid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";
	
	private int pageNo=1;
	private String callType;
	private Page page = new Page();
	private ReturnJson returnJson = new ReturnJson();
	private String messageIdsForDel;
	
//  manage Message
	public String manageMessage(){
		log.info(logprefix+"manageMessageFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(messageService.countMessageRow());
		messageList  = messageService.selectMessageByPage(page);
		
//		查询全部
//		messageList  = messageService.selectMessageById();
		
	    if(messageList == null){
			messageList = new ArrayList<Message>();
		}
//		for(int i = 0; i < messageList.size(); i++){
//			System.out.println("id="+messageList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageMessageFull(){
		log.info("exec action method:manageMessageFull");
                
//      分页查询        
        if(pageNo <= 0){
			pageNo =1;
        }
        page.setTotalCount(messageService.countMessageRow());
        if(page.getTotalpage() < pageNo){
			pageNo = page.getTotalpage();
        }
        page.setPageNo(pageNo);
                
        messageFullList  = messageService.selectMessageFullByConditionAndPage("", page);
               

        if(messageFullList == null){
			messageFullList = new ArrayList<MessageFull>();
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

			
	public String addMessage(){	
		log.info("Add Entity");

		try {
			Message temp = new Message();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, message);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			messageService.addMessage(temp);
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MessageAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addMessageAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Message temp = new Message();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, message);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			messageService.addMessage(temp);
			
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
			log.error("类MessageAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		returnJson.setReason("添加失败");
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteMessage( ){
		try{
			if(message.getId() != null && message.getId() > 0){
				messageService.deleteMessage(message.getId());
				actionMsg = getText("deleteMessageSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteMessageFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MessageAction的方法：deleteMessage错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteMessageAjax( ){
		try{
			if(message.getId() != null && message.getId() >= 0){
				messageService.deleteMessage(message.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MessageAction的方法：deleteMessage错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteMessageFull(){
		try {
		
			Integer getId = message.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Message temp = messageService.selectMessageById(getId);
			if (temp != null) {
				messageService.deleteMessage(getId);
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
	public String deleteMessageFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			String ids[] = messageIdsForDel.split(";");
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
                                Message temp = messageService.selectMessageById(tempDelId);                        
                                if (temp != null) {                        
                                        //其他操作                                        
                                        messageService.deleteMessage(tempDelId);                                        
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
	public String updateMessage(){
		try {
			if(message.getId() != null && message.getId() > 0){				
				Message tempMessage = messageService.selectMessageById(message.getId());
																				  								
												  								
												  								
												  								
												  								
												  								
												  								
												  								
												  								
												  								
								actionMsg = getText("viewMessageSuccess");
			}else{
				actionMsg = getText("viewMessageFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MessageAction的方法：viewMessage错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateMessageAjax(){
		log.info(logprefix + "updateMessageAjax,id="+message.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(message.getId() != null && message.getId() > 0){				
				Message tempMessage = messageService.selectMessageById(message.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempMessage.setTitle(message.getTitle());
  				tempMessage.setContent(message.getContent());
  				tempMessage.setSenderid(message.getSenderid());
  				tempMessage.setSendtime(message.getSendtime());
  				tempMessage.setReplyid(message.getReplyid());
  				tempMessage.setReadstatus(message.getReadstatus());
  				tempMessage.setFilepath(message.getFilepath());
  				tempMessage.setIsbigfile(message.getIsbigfile());
  				tempMessage.setType(message.getType());
  				tempMessage.setStatus(message.getStatus());
 
				
				messageService.updateMessage(tempMessage);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewMessageSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewMessageFail");
				log.info(logprefix + "updateMessageAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MessageAction的方法：viewMessage错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editMessage(){
		log.info(logprefix + "editMessage");
			
		try {
			//实体的id可以为0
			if(message.getId() != null && message.getId() >= 0){				
				Message temMessage = messageService.selectMessageById(message.getId());
				if(temMessage != null){
					BeanUtils.copyProperties(messageModel,temMessage);	
					//actionMsg = getText("selectMessageByIdSuccess");
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
			log.error("类MessageAction的方法：selectMessageById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editMessageFull(){
		
		log.info(logprefix + "viewMessage");
		
		try {
			if(message.getId() != null && message.getId() > 0){				
				MessageFull temMessageFull = messageService.selectMessageFullById(message.getId());
				BeanUtils.copyProperties(messageFull,temMessageFull);	
				actionMsg = getText("selectMessageByIdSuccess");
			}else{
				actionMsg = getText("selectMessageByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MessageAction的方法：selectMessageFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewMessage(){
		log.info("viewMessage");
		try {
			if(message.getId() != null && message.getId() > 0){				
				Message temMessage = messageService.selectMessageById(message.getId());
				BeanUtils.copyProperties(messageModel,temMessage);	
				actionMsg = getText("selectMessageByIdSuccess");
			}else{
				actionMsg = getText("selectMessageByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MessageAction的方法：selectMessageById错误"+e);
		}


		return "error";

	}	

/**
 * view MessageFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewMessageFull() {
				
		try {
			int getId = message.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			MessageFull temMessageFull = messageService.selectMessageFullById(getId);				
			if(temMessageFull!=null){				
				BeanUtils.copyProperties(messageFull,temMessageFull);
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
	public String selectMessageByLoginuserId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		messageList  = messageService.selectMessageAll();
		for(int i = 0; i < messageList.size(); i++){
			System.out.println("id="+messageList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据senderid查询实体full
	public String selectMessageFullByLoginuserId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		messageFullList  = messageService.selectMessageFullByLoginuserId(userId);
		for(int i = 0; i < messageFullList.size(); i++){
			//System.out.println("id="+messageFullList.get(i).getLoginusername());
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
	public Message getModel() {
		// TODO Auto-generated method stub
		return message;
	}

//  
	public Message getMessage() {
		return message;
	}
	
	public void setMessage(Message message) {
		this.message = message;
	}
//  entityModel
	public Message getMessageModel() {
		return messageModel;
	}
	
	public void setMessageModel(Message messageModel) {
		this.messageModel = messageModel;
	}
	
	public MessageFull getMessageFull() {
		return messageFull;
	}
	
	public void setMessageFull(MessageFull messageFull) {
		this.messageFull = messageFull;
	}
	
	public List<Message> getMessageList() {
		return messageList;
	}


	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}

	public List<MessageFull> getMessageFullList() {
		return messageFullList;
	}


	public void setMessageFullList(List<MessageFull> messageFullList) {
		this.messageFullList = messageFullList;
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
	
	public int getMessageid() {
		return messageid;
	}

	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
		public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	public String getMessageIdsForDel() {
                return messageIdsForDel;
        }

        public void setMessageIdsForDel(String messageIdsForDel) {
                this.messageIdsForDel = messageIdsForDel;
        }
}
