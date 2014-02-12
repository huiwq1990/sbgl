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
import com.sbgl.app.services.message.MessagereceiverService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("MessagereceiverAction")
public class MessagereceiverAction extends ActionSupport implements SessionAware,ModelDriven<Messagereceiver>{
	
	private static final Log log = LogFactory.getLog(MessagereceiverAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private MessagereceiverService messagereceiverService;
	private Integer messagereceiverid; //entity full 的id属性名称		
	private Messagereceiver messagereceiver = new Messagereceiver();//实例化一个模型
	private Messagereceiver messagereceiverModel = new Messagereceiver();//实例化一个模型
	private MessagereceiverFull messagereceiverFull = new MessagereceiverFull();//实例化一个模型	
	private List<Messagereceiver> messagereceiverList = new ArrayList<Messagereceiver>();
	private List<MessagereceiverFull> messagereceiverFullList = new ArrayList<MessagereceiverFull>();
	
	private String logprefix = "exec action method:";
	

	private String callType;
	private int pageNo=1;
	private Page page = new Page();
	
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	private String returnInfo;
	private String actionMsg; // Action间传递的消息参数

	private ReturnJson returnJson = new ReturnJson();
	private String messagereceiverIdsForDel;
	
//  manage Messagereceiver
	public String manageMessagereceiver(){
		log.info(logprefix+"manageMessagereceiverFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(messagereceiverService.countMessagereceiverRow());
		messagereceiverList  = messagereceiverService.selectMessagereceiverByPage(page);
		
//		查询全部
//		messagereceiverList  = messagereceiverService.selectMessagereceiverById();
		
	    if(messagereceiverList == null){
			messagereceiverList = new ArrayList<Messagereceiver>();
		}
//		for(int i = 0; i < messagereceiverList.size(); i++){
//			System.out.println("id="+messagereceiverList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageMessagereceiverFull(){
		log.info("exec action method:manageMessagereceiverFull");
                
//      分页查询        
        if(pageNo <= 0){
			pageNo =1;
        }
        page.setTotalCount(messagereceiverService.countMessagereceiverRow());
        if(page.getTotalpage() < pageNo){
			pageNo = page.getTotalpage();
        }
        page.setPageNo(pageNo);
                
        messagereceiverFullList  = messagereceiverService.selectMessagereceiverFullByConditionAndPage("", page);
               

        if(messagereceiverFullList == null){
			messagereceiverFullList = new ArrayList<MessagereceiverFull>();
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

			
	public String addMessagereceiver(){	
		log.info("Add Entity");

		try {
			Messagereceiver temp = new Messagereceiver();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, messagereceiver);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			messagereceiverService.addMessagereceiver(temp);
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MessagereceiverAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addMessagereceiverAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Messagereceiver temp = new Messagereceiver();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, messagereceiver);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			messagereceiverService.addMessagereceiver(temp);
			
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
			log.error("类MessagereceiverAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		returnJson.setReason("添加失败");
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteMessagereceiver( ){
		try{
			if(messagereceiver.getId() != null && messagereceiver.getId() > 0){
				messagereceiverService.deleteMessagereceiver(messagereceiver.getId());
				actionMsg = getText("deleteMessagereceiverSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteMessagereceiverFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MessagereceiverAction的方法：deleteMessagereceiver错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteMessagereceiverAjax( ){
		try{
			if(messagereceiver.getId() != null && messagereceiver.getId() >= 0){
				messagereceiverService.deleteMessagereceiver(messagereceiver.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MessagereceiverAction的方法：deleteMessagereceiver错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteMessagereceiverFull(){
		try {
		
			Integer getId = messagereceiver.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Messagereceiver temp = messagereceiverService.selectMessagereceiverById(getId);
			if (temp != null) {
				messagereceiverService.deleteMessagereceiver(getId);
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
	public String deleteMessagereceiverFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			String ids[] = messagereceiverIdsForDel.split(";");
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
                                Messagereceiver temp = messagereceiverService.selectMessagereceiverById(tempDelId);                        
                                if (temp != null) {                        
                                        //其他操作                                        
                                        messagereceiverService.deleteMessagereceiver(tempDelId);                                        
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
	public String updateMessagereceiver(){
		try {
			if(messagereceiver.getId() != null && messagereceiver.getId() > 0){				
				Messagereceiver tempMessagereceiver = messagereceiverService.selectMessagereceiverById(messagereceiver.getId());
																				  								
												  								
												  								
												  								
												  								
								actionMsg = getText("viewMessagereceiverSuccess");
			}else{
				actionMsg = getText("viewMessagereceiverFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MessagereceiverAction的方法：viewMessagereceiver错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateMessagereceiverAjax(){
		log.info(logprefix + "updateMessagereceiverAjax,id="+messagereceiver.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(messagereceiver.getId() != null && messagereceiver.getId() > 0){				
				Messagereceiver tempMessagereceiver = messagereceiverService.selectMessagereceiverById(messagereceiver.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempMessagereceiver.setMessageid(messagereceiver.getMessageid());
  				tempMessagereceiver.setReceiverid(messagereceiver.getReceiverid());
  				tempMessagereceiver.setHasview(messagereceiver.getHasview());
  				tempMessagereceiver.setViewdate(messagereceiver.getViewdate());
  				tempMessagereceiver.setStatus(messagereceiver.getStatus());
 
				
				messagereceiverService.updateMessagereceiver(tempMessagereceiver);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewMessagereceiverSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewMessagereceiverFail");
				log.info(logprefix + "updateMessagereceiverAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MessagereceiverAction的方法：viewMessagereceiver错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editMessagereceiver(){
		log.info(logprefix + "editMessagereceiver");
			
		try {
			//实体的id可以为0
			if(messagereceiver.getId() != null && messagereceiver.getId() >= 0){				
				Messagereceiver temMessagereceiver = messagereceiverService.selectMessagereceiverById(messagereceiver.getId());
				if(temMessagereceiver != null){
					BeanUtils.copyProperties(messagereceiverModel,temMessagereceiver);	
					//actionMsg = getText("selectMessagereceiverByIdSuccess");
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
			log.error("类MessagereceiverAction的方法：selectMessagereceiverById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editMessagereceiverFull(){
		
		log.info(logprefix + "viewMessagereceiver");
		
		try {
			if(messagereceiver.getId() != null && messagereceiver.getId() > 0){				
				MessagereceiverFull temMessagereceiverFull = messagereceiverService.selectMessagereceiverFullById(messagereceiver.getId());
				BeanUtils.copyProperties(messagereceiverFull,temMessagereceiverFull);	
				actionMsg = getText("selectMessagereceiverByIdSuccess");
			}else{
				actionMsg = getText("selectMessagereceiverByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MessagereceiverAction的方法：selectMessagereceiverFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewMessagereceiver(){
		log.info("viewMessagereceiver");
		try {
			if(messagereceiver.getId() != null && messagereceiver.getId() > 0){				
				Messagereceiver temMessagereceiver = messagereceiverService.selectMessagereceiverById(messagereceiver.getId());
				BeanUtils.copyProperties(messagereceiverModel,temMessagereceiver);	
				actionMsg = getText("selectMessagereceiverByIdSuccess");
			}else{
				actionMsg = getText("selectMessagereceiverByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MessagereceiverAction的方法：selectMessagereceiverById错误"+e);
		}


		return "error";

	}	

/**
 * view MessagereceiverFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewMessagereceiverFull() {
				
		try {
			int getId = messagereceiver.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			MessagereceiverFull temMessagereceiverFull = messagereceiverService.selectMessagereceiverFullById(getId);				
			if(temMessagereceiverFull!=null){				
				BeanUtils.copyProperties(messagereceiverFull,temMessagereceiverFull);
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
	//根据receiverid查询实体
	public String selectMessagereceiverByLoginuserId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		messagereceiverList  = messagereceiverService.selectMessagereceiverAll();
		for(int i = 0; i < messagereceiverList.size(); i++){
			System.out.println("id="+messagereceiverList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据receiverid查询实体full
	public String selectMessagereceiverFullByLoginuserId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		messagereceiverFullList  = messagereceiverService.selectMessagereceiverFullByLoginuserId(userId);
		for(int i = 0; i < messagereceiverFullList.size(); i++){
			//System.out.println("id="+messagereceiverFullList.get(i).getLoginusername());
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
	public Messagereceiver getModel() {
		// TODO Auto-generated method stub
		return messagereceiver;
	}

//  
	public Messagereceiver getMessagereceiver() {
		return messagereceiver;
	}
	
	public void setMessagereceiver(Messagereceiver messagereceiver) {
		this.messagereceiver = messagereceiver;
	}
//  entityModel
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


	public void setMessagereceiverFullList(List<MessagereceiverFull> messagereceiverFullList) {
		this.messagereceiverFullList = messagereceiverFullList;
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
	
	public int getMessagereceiverid() {
		return messagereceiverid;
	}

	public void setMessagereceiverid(int messagereceiverid) {
		this.messagereceiverid = messagereceiverid;
	}
		public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	public String getMessagereceiverIdsForDel() {
                return messagereceiverIdsForDel;
        }

        public void setMessagereceiverIdsForDel(String messagereceiverIdsForDel) {
                this.messagereceiverIdsForDel = messagereceiverIdsForDel;
        }
}
