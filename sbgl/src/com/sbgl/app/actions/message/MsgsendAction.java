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
import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.message.MsgsendService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("MsgsendAction")
public class MsgsendAction extends BaseAction implements ModelDriven<Msgsend>{
	
	private static final Log log = LogFactory.getLog(MsgsendAction.class);
	
	//Service	
	@Resource
	private MsgsendService msgsendService;
	
	private Integer msgsendid; //entity full 的id属性名称	
	private Msgsend msgsend = new Msgsend();//实例化一个模型
	private Msgsend msgsendModel = new Msgsend();//实例化一个模型
	private MsgsendFull msgsendFull = new MsgsendFull();//实例化一个模型
	private List<Msgsend> msgsendList = new ArrayList<Msgsend>();
	private List<MsgsendFull> msgsendFullList = new ArrayList<MsgsendFull>();
	
	private String logprefix = "exec action method:";
	
	
	private String msgsendIdsForDel;
	
//  manage Msgsend
	public String manageMsgsend(){
		log.info(logprefix+"manageMsgsendFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(msgsendService.countMsgsendRow());
		msgsendList  = msgsendService.selectMsgsendByPage(page);
		
//		查询全部
//		msgsendList  = msgsendService.selectMsgsendById();
		
	    if(msgsendList == null){
			msgsendList = new ArrayList<Msgsend>();
		}
//		for(int i = 0; i < msgsendList.size(); i++){
//			System.out.println("id="+msgsendList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageMsgsendFull(){
		log.info("exec action method:manageMsgsendFull");
                
//      分页查询        
        if(pageNo <= 0){
			pageNo =1;
        }
        page.setTotalCount(msgsendService.countMsgsendRow());
        if(page.getTotalpage() < pageNo){
			pageNo = page.getTotalpage();
        }
        page.setPageNo(pageNo);
                
        msgsendFullList  = msgsendService.selectMsgsendFullByConditionAndPage("", page);
               

        if(msgsendFullList == null){
			msgsendFullList = new ArrayList<MsgsendFull>();
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

		
	public String toSendMsgPage(){	
		
		return SUCCESS;
	}

//  ajax add	
	public String sendMsgAjax(){	
		log.info("Add Entity Ajax Manner");
		
		
		
		try {
			Msgsend temps = new Msgsend();
			Msgreceive tempr = new Msgreceive();
			
			msgsend.setSendtime(DateUtil.currentDate());
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temps, msgsend);
			BeanUtils.copyProperties(tempr, msgsend);
			
			msgsendService.snedMsg(temps, tempr);
		
			this.returnInfo = "添加成功";
			returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MsgsendAction的方法：addBbstagfavourite错误"+e);
		}
		
		this.returnInfo = "内部错误";
		returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		return SUCCESS;
	}

//删除
	public String deleteMsgsend( ){
		try{
			if(msgsend.getId() != null && msgsend.getId() > 0){
				msgsendService.deleteMsgsend(msgsend.getId());
				actionMsg = getText("deleteMsgsendSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteMsgsendFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MsgsendAction的方法：deleteMsgsend错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteMsgsendAjax( ){
		try{
			if(msgsend.getId() != null && msgsend.getId() >= 0){
				msgsendService.deleteMsgsend(msgsend.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MsgsendAction的方法：deleteMsgsend错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteMsgsendFull(){
		try {
		
			Integer getId = msgsend.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Msgsend temp = msgsendService.selectMsgsendById(getId);
			if (temp != null) {
				msgsendService.deleteMsgsend(getId);
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
	public String deleteMsgsendFullAjax( ){
		/*
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			String ids[] = msgsendIdsForDel.split(";");
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
                                Msgsend temp = msgsendService.selectMsgsendById(tempDelId);                        
                                if (temp != null) {                        
                                        //其他操作                                        
                                        msgsendService.deleteMsgsend(tempDelId);                                        
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
                this.returnStr = jo.toString();*/
                return SUCCESS;
        }

//修改
	public String updateMsgsend(){
		try {
			if(msgsend.getId() != null && msgsend.getId() > 0){				
				Msgsend tempMsgsend = msgsendService.selectMsgsendById(msgsend.getId());
																				  								
												  								
												  								
												  								
												  								
												  								
												  								
												  								
								actionMsg = getText("viewMsgsendSuccess");
			}else{
				actionMsg = getText("viewMsgsendFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MsgsendAction的方法：viewMsgsend错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateMsgsendAjax(){
		log.info(logprefix + "updateMsgsendAjax,id="+msgsend.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(msgsend.getId() != null && msgsend.getId() > 0){				
				Msgsend tempMsgsend = msgsendService.selectMsgsendById(msgsend.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempMsgsend.setSenderid(msgsend.getSenderid());
  				tempMsgsend.setReceiverid(msgsend.getReceiverid());
  				tempMsgsend.setTitle(msgsend.getTitle());
  				tempMsgsend.setContent(msgsend.getContent());
  				tempMsgsend.setType(msgsend.getType());
  				tempMsgsend.setSendtime(msgsend.getSendtime());
  				tempMsgsend.setReadtime(msgsend.getReadtime());
  				tempMsgsend.setStatus(msgsend.getStatus());
 
				
				msgsendService.updateMsgsend(tempMsgsend);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewMsgsendSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewMsgsendFail");
				log.info(logprefix + "updateMsgsendAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MsgsendAction的方法：viewMsgsend错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editMsgsend(){
		log.info(logprefix + "editMsgsend");
			
		try {
			//实体的id可以为0
			if(msgsend.getId() != null && msgsend.getId() >= 0){				
				Msgsend temMsgsend = msgsendService.selectMsgsendById(msgsend.getId());
				if(temMsgsend != null){
					BeanUtils.copyProperties(msgsendModel,temMsgsend);	
					//actionMsg = getText("selectMsgsendByIdSuccess");
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
			log.error("类MsgsendAction的方法：selectMsgsendById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editMsgsendFull(){
		
		log.info(logprefix + "viewMsgsend");
		
		try {
			if(msgsend.getId() != null && msgsend.getId() > 0){				
				MsgsendFull temMsgsendFull = msgsendService.selectMsgsendFullById(msgsend.getId());
				BeanUtils.copyProperties(msgsendFull,temMsgsendFull);	
				actionMsg = getText("selectMsgsendByIdSuccess");
			}else{
				actionMsg = getText("selectMsgsendByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MsgsendAction的方法：selectMsgsendFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewMsgsend(){
		log.info("viewMsgsend");
		try {
			if(msgsend.getId() != null && msgsend.getId() > 0){				
				Msgsend temMsgsend = msgsendService.selectMsgsendById(msgsend.getId());
				BeanUtils.copyProperties(msgsendModel,temMsgsend);	
				actionMsg = getText("selectMsgsendByIdSuccess");
			}else{
				actionMsg = getText("selectMsgsendByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MsgsendAction的方法：selectMsgsendById错误"+e);
		}


		return "error";

	}	

/**
 * view MsgsendFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewMsgsendFull() {
				
		try {
			int getId = msgsend.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			MsgsendFull temMsgsendFull = msgsendService.selectMsgsendFullById(getId);				
			if(temMsgsendFull!=null){				
				BeanUtils.copyProperties(msgsendFull,temMsgsendFull);
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

	
	@Override
	public Msgsend getModel() {
		// TODO Auto-generated method stub
		return msgsend;
	}

	
	
	
}
