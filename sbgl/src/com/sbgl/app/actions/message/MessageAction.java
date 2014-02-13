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
import com.sbgl.app.actions.computer.ComputerActionUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.message.MessageService;
import com.sbgl.app.services.message.MessagereceiverService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("MessageAction")
public class MessageAction extends ActionSupport implements SessionAware,ModelDriven<Message>{
	
	private static final Log log = LogFactory.getLog(MessageAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private MessageService messageService;
	private Integer messageid; //entity full 的id属性名称		
	private Message message = new Message();//实例化一个模型
	private Message messageModel = new Message();//实例化一个模型
	private MessageFull messageFull = new MessageFull();//实例化一个模型	
	private List<Message> messageList = new ArrayList<Message>();
	private List<MessageFull> messageFullList = new ArrayList<MessageFull>();
	
	
	
	@Resource
	private MessagereceiverService messagereceiverService;
	private Integer messagereceiverid; //entity full 的id属性名称		
	private Messagereceiver messagereceiver = new Messagereceiver();//实例化一个模型
	private Messagereceiver messagereceiverModel = new Messagereceiver();//实例化一个模型
	private MessagereceiverFull messagereceiverFull = new MessagereceiverFull();//实例化一个模型	
	private List<Messagereceiver> messagereceiverList = new ArrayList<Messagereceiver>();
	private List<MessagereceiverFull> messagereceiverFullList = new ArrayList<MessagereceiverFull>();
	
	
	
	private String logprefix = "exec action method:";
	

	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	private String returnInfo;
	private String actionMsg; // Action间传递的消息参数


	private int pageNo=1;
	private int totalcount = 0;
	private int totalpage = 0;
	private Page page = new Page();
	private String callType;
	private String messageIdsForDel;
	
	private String messagereceiverids;
	private ReturnJson returnJson = new ReturnJson();

	public int checkUserLogin(){
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		String uidStr = ComputerActionUtil.getUserIdFromCookie(cookies);
		if(uidStr==null || uidStr.trim().equals("0") || uidStr.trim().equals("")){
			return -1;
		}
		return Integer.valueOf(uidStr);
	}
	
	public int getCurrentLanguage(){
		return ComputerActionUtil.getLanguagetype((String) session.get(ComputerConfig.sessionLanguagetype));		
	}
	
	public void buildReturnStr(int flag,String errorStr){
		ReturnJson returnJson = new ReturnJson();
		returnJson.setFlag(flag);			
		returnJson.setReason(errorStr);
		
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
//		return SUCCESS;
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

	/**
	 * 跳转到添加界面
	 * @return
	 */
	public String toAddMessagePage(){
//		装载用户信息
		
		return SUCCESS; 
		
	}
	
//	检查添加信息
	public boolean passMessageAddInfoCheck(){
		if(messagereceiverids == null || messagereceiverids.length() == 0){
			returnInfo = "消息接收人为空";
			buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
			return false;
		}
		
		return true;
	}
//  ajax add	
	public String addMessageAjax(){	
		log.info("Add Entity Ajax Manner");
		
		Integer uid = checkUserLogin();
		log.info("login user id "+ uid);
		if(uid < 0){
			returnInfo = "用户未登录";
			buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
			return SUCCESS;
		}
		
		if(!passMessageAddInfoCheck()){
			return SUCCESS;
		}
	
		
		try {
			Message temp = new Message();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, message);
			temp.setSenderid(uid);
			temp.setSendtime(DateUtil.currentDate());
			temp.setReplyid(0);
			temp.setStatus(MessageConstant.MessageStatusValid);
			
			messageService.addMessage(temp);
			
			
			String[] userIds = messagereceiverids.split(";");
			for (int i = 0; i < userIds.length; i++) {
				Messagereceiver mr = new Messagereceiver();
				mr.setMessageid(temp.getId());
				mr.setReceiverid(Integer.valueOf(userIds[i]));
				mr.setHasview(0);
				mr.setStatus(0);
				messagereceiverService.addMessagereceiver(mr);
			}
			
			returnInfo = "消息发送成功";
			buildReturnStr(ComputerConfig.ajaxsuccessreturn,returnInfo);
			return SUCCESS;
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MessageAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnInfo = "消息发送失败";
		buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
		return SUCCESS;
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

	
	/**
	 * 消息收件箱
	 */
	public String toMessageInbox() {
		Integer uid = checkUserLogin();
		log.info("login user id "+ uid);
		if(uid < 0){
			actionMsg = "用户未登录";
			log.error(actionMsg);
			return ComputerConfig.usernotloginreturnstr;
		}
		
//		查询收到的消息
		String inboxsql = " where receiverid ="+uid + " and status = "+MessageConstant.MessageStatusValid + " order by id desc ";
		messagereceiverList =	messagereceiverService.selectMessagereceiverByCondition(inboxsql);
			
		if(messagereceiverList == null){
			page.setTotalCount(0);
			page.setPageNo(0);
			pageNo = 0;
			messageFullList = new ArrayList<MessageFull>();
			return SUCCESS;
		}
		
		setPageInfo(messagereceiverList.size());
		
		int pageIndex = page.getPageNo()-1;
		if(pageIndex < 0){
			pageIndex = 0;
		}
		int fromIndex = pageIndex * page.SIZE;
		int toIndex = fromIndex + page.SIZE;
		if(messagereceiverList.size() < toIndex){
			toIndex = messagereceiverList.size();
		}
		messagereceiverList = messagereceiverList.subList(fromIndex, toIndex);
		
		for (int i = 0; i < messagereceiverList.size(); i++) {
			String msgfullsel = " where a.id = "+ messagereceiverList.get(i).getMessageid();
			List<MessageFull> temp = messageService.selectMessageFullByCondition(msgfullsel);
			if(temp != null && temp.size() == 1){
				messageFullList.add(temp.get(0));
			}			
		}
//		messageFullList = messageService.selectMessageFullByCondition(inboxsql);
		if(messagereceiverList == null){
			messageFullList = new ArrayList<MessageFull>();
			return SUCCESS;
		}
		return SUCCESS;
	}
	
	/**
	 * 消息发件箱
	 */
	public String toMessageSendbox() {
		Integer uid = checkUserLogin();
		log.info("login user id "+ uid);
		if(uid < 0){
			actionMsg = "用户未登录";
			log.error(actionMsg);
			return ComputerConfig.usernotloginreturnstr;
		}
		
		
		if(pageNo ==0){
			pageNo =1;
		}
		
//		用于统计数量
		String sendboxsql = " where a.senderid ="+uid +" and a.status = "+MessageConstant.MessageStatusValid;
		messageFullList = messageService.selectMessageFullByCondition(sendboxsql);
		
		if(messageFullList == null){
			messageFullList = new ArrayList<MessageFull>();
		}
		
		//设置总数量
		page.setTotalCount(messageFullList.size());
		
		//如果页码大于总页数，重新设置
		if(pageNo>page.getTotalpage()){
			pageNo = page.getTotalpage();
		}
		
		page.setPageNo(pageNo);
		if(page.getTotalCount()==0){
			page.setPageNo(0);
			page.setTotalpage(0);
			pageNo = 0;
		}
		
		sendboxsql = " where a.senderid ="+uid +" and a.status = "+MessageConstant.MessageStatusValid;
		messageFullList = messageService.selectMessageFullByConditionAndPage(sendboxsql, page);
		
		if(messageFullList == null){
			messageFullList = new ArrayList<MessageFull>();
		}
		
		return SUCCESS;
	}
	
	public String toMessageSendboxNextPage() {
		log.info("");
		Integer uid = checkUserLogin();
		log.info("login user id "+ uid);
		if(uid < 0){
			actionMsg = "用户未登录";
			log.error(actionMsg);
			return ComputerConfig.usernotloginreturnstr;
		}
		
		setPageInfo(totalcount);

		
		String sendboxsql = " where a.senderid ="+uid +" and a.status = "+MessageConstant.MessageStatusValid;
		messageFullList = messageService.selectMessageFullByConditionAndPage(sendboxsql, page);
		
		if(messageFullList == null){
			messageFullList = new ArrayList<MessageFull>();
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 消息回收站
	 */
	public String toMessageTrash() {
		Integer uid = checkUserLogin();
		log.info("login user id "+ uid);
		if(uid < 0){
			actionMsg = "用户未登录";
			log.error(actionMsg);
			return ComputerConfig.usernotloginreturnstr;
		}
		
//		查询用户删除的发送的消息
		String delsendboxsql = " where a.senderid ="+uid +" and a.status = "+MessageConstant.MessageStatusDel;
		messageFullList = messageService.selectMessageFullByCondition(delsendboxsql);
		
//		查询删除的收到的信息		
//		查询收到的消息
		String delinboxsql = " where a.receiverid ="+uid + " a.status = "+MessageConstant.MessageStatusDel;
		messagereceiverList =	messagereceiverService.selectMessagereceiverByCondition(delinboxsql);
			
		if(messagereceiverList != null){
			for (int i = 0; i < messagereceiverList.size(); i++) {
				String msgfullsel = " where a.id = "+ messagereceiverList.get(i).getMessageid();
				List<MessageFull> temp = messageService.selectMessageFullByCondition(msgfullsel);
				if(temp != null && temp.size() == 1){
					messageFullList.add(temp.get(0));
				}			
			}
		}
		
		if(messageFullList == null){
			messageFullList = new ArrayList<MessageFull>();
		}
		
		return SUCCESS;
	}
	
	
	
	
	public void setPageInfo(int totalcount){
		if(pageNo ==0){
			pageNo =1;
		}
		
		//设置总数量
		page.setTotalCount(totalcount);
		
		//如果页码大于总页数，重新设置
		if(pageNo>page.getTotalpage()){
			pageNo = page.getTotalpage();
		}
		
		page.setPageNo(pageNo);
		if(page.getTotalCount()==0){
			page.setPageNo(0);
			page.setTotalpage(0);
			pageNo = 0;
		}
	}
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

		public MessageService getMessageService() {
			return messageService;
		}

		public void setMessageService(MessageService messageService) {
			this.messageService = messageService;
		}

		public MessagereceiverService getMessagereceiverService() {
			return messagereceiverService;
		}

		public void setMessagereceiverService(
				MessagereceiverService messagereceiverService) {
			this.messagereceiverService = messagereceiverService;
		}

		public Integer getMessagereceiverid() {
			return messagereceiverid;
		}

		public void setMessagereceiverid(Integer messagereceiverid) {
			this.messagereceiverid = messagereceiverid;
		}

		public Messagereceiver getMessagereceiver() {
			return messagereceiver;
		}

		public void setMessagereceiver(Messagereceiver messagereceiver) {
			this.messagereceiver = messagereceiver;
		}

		public Messagereceiver getMessagereceiverModel() {
			return messagereceiverModel;
		}

		public void setMessagereceiverModel(Messagereceiver messagereceiverModel) {
			this.messagereceiverModel = messagereceiverModel;
		}

		public MessagereceiverFull getMessagereceiverFull() {
			return messagereceiverFull;
		}

		public void setMessagereceiverFull(MessagereceiverFull messagereceiverFull) {
			this.messagereceiverFull = messagereceiverFull;
		}

		public List<Messagereceiver> getMessagereceiverList() {
			return messagereceiverList;
		}

		public void setMessagereceiverList(List<Messagereceiver> messagereceiverList) {
			this.messagereceiverList = messagereceiverList;
		}

		public List<MessagereceiverFull> getMessagereceiverFullList() {
			return messagereceiverFullList;
		}

		public void setMessagereceiverFullList(
				List<MessagereceiverFull> messagereceiverFullList) {
			this.messagereceiverFullList = messagereceiverFullList;
		}

		public String getLogprefix() {
			return logprefix;
		}

		public void setLogprefix(String logprefix) {
			this.logprefix = logprefix;
		}

		public String getReturnInfo() {
			return returnInfo;
		}

		public void setReturnInfo(String returnInfo) {
			this.returnInfo = returnInfo;
		}

		public String getActionMsg() {
			return actionMsg;
		}

		public void setActionMsg(String actionMsg) {
			this.actionMsg = actionMsg;
		}

		public String getCallType() {
			return callType;
		}

		public void setCallType(String callType) {
			this.callType = callType;
		}

		public String getMessagereceiverids() {
			return messagereceiverids;
		}

		public void setMessagereceiverids(String messagereceiverids) {
			this.messagereceiverids = messagereceiverids;
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

		public void setMessageid(Integer messageid) {
			this.messageid = messageid;
		}

		public int getTotalpage() {
			return totalpage;
		}

		public void setTotalpage(int totalpage) {
			this.totalpage = totalpage;
		}

		public int getTotalcount() {
			return totalcount;
		}

		public void setTotalcount(int totalcount) {
			this.totalcount = totalcount;
		}
        
        
}
