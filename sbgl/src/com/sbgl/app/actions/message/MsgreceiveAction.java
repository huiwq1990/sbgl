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
import com.sbgl.app.entity.*;
import com.sbgl.app.services.message.MsgreceiveService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("MsgreceiveAction")
public class MsgreceiveAction extends BaseAction implements ModelDriven<Msgreceive>{
	
	private static final Log log = LogFactory.getLog(MsgreceiveAction.class);

	
	//Service	
	@Resource
	private MsgreceiveService msgreceiveService;
	
	private Msgreceive msgreceive = new Msgreceive();//实例化一个模型
	private Msgreceive msgreceiveModel = new Msgreceive();//实例化一个模型
	private MsgreceiveFull msgreceiveFull = new MsgreceiveFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	private List<Msgreceive> msgreceiveList = new ArrayList<Msgreceive>();
	private List<MsgreceiveFull> msgreceiveFullList = new ArrayList<MsgreceiveFull>();
	private Integer msgreceiveid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";
	
	private String msgreceiveIdsForDel;
	
//  manage Msgreceive
 	public String manageMsgreceive(){
		log.info(logprefix+"manageMsgreceiveFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(msgreceiveService.countMsgreceiveRow());
		msgreceiveList  = msgreceiveService.selectMsgreceiveByPage(page);
		
//		查询全部
//		msgreceiveList  = msgreceiveService.selectMsgreceiveById();
		
	    if(msgreceiveList == null){
			msgreceiveList = new ArrayList<Msgreceive>();
		}
//		for(int i = 0; i < msgreceiveList.size(); i++){
//			System.out.println("id="+msgreceiveList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageMsgreceiveFull(){
		log.info("exec action method:manageMsgreceiveFull");
                
//      分页查询        
        if(pageNo <= 0){
			pageNo =1;
        }
        page.setTotalCount(msgreceiveService.countMsgreceiveRow());
        if(page.getTotalpage() < pageNo){
			pageNo = page.getTotalpage();
        }
        page.setPageNo(pageNo);
                
        msgreceiveFullList  = msgreceiveService.selectMsgreceiveFullByConditionAndPage("", page);
               

        if(msgreceiveFullList == null){
			msgreceiveFullList = new ArrayList<MsgreceiveFull>();
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

			
	public String addMsgreceive(){	
		log.info("Add Entity");

		try {
			Msgreceive temp = new Msgreceive();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, msgreceive);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			msgreceiveService.addMsgreceive(temp);
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MsgreceiveAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addMsgreceiveAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Msgreceive temp = new Msgreceive();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, msgreceive);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			msgreceiveService.addMsgreceive(temp);
			
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
			log.error("类MsgreceiveAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		returnJson.setReason("添加失败");
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteMsgreceive( ){
		try{
			if(msgreceive.getId() != null && msgreceive.getId() > 0){
				msgreceiveService.deleteMsgreceive(msgreceive.getId());
				actionMsg = getText("deleteMsgreceiveSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteMsgreceiveFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MsgreceiveAction的方法：deleteMsgreceive错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteMsgreceiveAjax( ){
		try{
			if(msgreceive.getId() != null && msgreceive.getId() >= 0){
				msgreceiveService.deleteMsgreceive(msgreceive.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MsgreceiveAction的方法：deleteMsgreceive错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteMsgreceiveFull(){
		try {
		
			Integer getId = msgreceive.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Msgreceive temp = msgreceiveService.selectMsgreceiveById(getId);
			if (temp != null) {
				msgreceiveService.deleteMsgreceive(getId);
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
	public String deleteMsgreceiveFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			String ids[] = msgreceiveIdsForDel.split(";");
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
                                Msgreceive temp = msgreceiveService.selectMsgreceiveById(tempDelId);                        
                                if (temp != null) {                        
                                        //其他操作                                        
                                        msgreceiveService.deleteMsgreceive(tempDelId);                                        
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
	public String updateMsgreceive(){
		try {
			if(msgreceive.getId() != null && msgreceive.getId() > 0){				
				Msgreceive tempMsgreceive = msgreceiveService.selectMsgreceiveById(msgreceive.getId());
																				  								
												  								
												  								
												  								
												  								
												  								
												  								
												  								
								actionMsg = getText("viewMsgreceiveSuccess");
			}else{
				actionMsg = getText("viewMsgreceiveFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MsgreceiveAction的方法：viewMsgreceive错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateMsgreceiveAjax(){
		log.info(logprefix + "updateMsgreceiveAjax,id="+msgreceive.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(msgreceive.getId() != null && msgreceive.getId() > 0){				
				Msgreceive tempMsgreceive = msgreceiveService.selectMsgreceiveById(msgreceive.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempMsgreceive.setSenderid(msgreceive.getSenderid());
  				tempMsgreceive.setReceiverid(msgreceive.getReceiverid());
  				tempMsgreceive.setTitle(msgreceive.getTitle());
  				tempMsgreceive.setContent(msgreceive.getContent());
  				tempMsgreceive.setType(msgreceive.getType());
  				tempMsgreceive.setSendtime(msgreceive.getSendtime());
  				tempMsgreceive.setReadtime(msgreceive.getReadtime());
  				tempMsgreceive.setStatus(msgreceive.getStatus());
 
				
				msgreceiveService.updateMsgreceive(tempMsgreceive);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewMsgreceiveSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewMsgreceiveFail");
				log.info(logprefix + "updateMsgreceiveAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MsgreceiveAction的方法：viewMsgreceive错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editMsgreceive(){
		log.info(logprefix + "editMsgreceive");
			
		try {
			//实体的id可以为0
			if(msgreceive.getId() != null && msgreceive.getId() >= 0){				
				Msgreceive temMsgreceive = msgreceiveService.selectMsgreceiveById(msgreceive.getId());
				if(temMsgreceive != null){
					BeanUtils.copyProperties(msgreceiveModel,temMsgreceive);	
					//actionMsg = getText("selectMsgreceiveByIdSuccess");
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
			log.error("类MsgreceiveAction的方法：selectMsgreceiveById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editMsgreceiveFull(){
		
		log.info(logprefix + "viewMsgreceive");
		
		try {
			if(msgreceive.getId() != null && msgreceive.getId() > 0){				
				MsgreceiveFull temMsgreceiveFull = msgreceiveService.selectMsgreceiveFullById(msgreceive.getId());
				BeanUtils.copyProperties(msgreceiveFull,temMsgreceiveFull);	
				actionMsg = getText("selectMsgreceiveByIdSuccess");
			}else{
				actionMsg = getText("selectMsgreceiveByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MsgreceiveAction的方法：selectMsgreceiveFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewMsgreceive(){
		log.info("viewMsgreceive");
		try {
			if(msgreceive.getId() != null && msgreceive.getId() > 0){				
				Msgreceive temMsgreceive = msgreceiveService.selectMsgreceiveById(msgreceive.getId());
				BeanUtils.copyProperties(msgreceiveModel,temMsgreceive);	
				actionMsg = getText("selectMsgreceiveByIdSuccess");
			}else{
				actionMsg = getText("selectMsgreceiveByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类MsgreceiveAction的方法：selectMsgreceiveById错误"+e);
		}


		return "error";

	}	

/**
 * view MsgreceiveFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewMsgreceiveFull() {
				
		try {
			int getId = msgreceive.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			MsgreceiveFull temMsgreceiveFull = msgreceiveService.selectMsgreceiveFullById(getId);				
			if(temMsgreceiveFull!=null){				
				BeanUtils.copyProperties(msgreceiveFull,temMsgreceiveFull);
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
	public String selectMsgreceiveByLoginuserId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		msgreceiveList  = msgreceiveService.selectMsgreceiveAll();
		for(int i = 0; i < msgreceiveList.size(); i++){
			System.out.println("id="+msgreceiveList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据receiverid查询实体
	public String selectMsgreceiveByLoginuserId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		msgreceiveList  = msgreceiveService.selectMsgreceiveAll();
		for(int i = 0; i < msgreceiveList.size(); i++){
			System.out.println("id="+msgreceiveList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据senderid查询实体full
	public String selectMsgreceiveFullByLoginuserId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		msgreceiveFullList  = msgreceiveService.selectMsgreceiveFullByLoginuserId(userId);
		for(int i = 0; i < msgreceiveFullList.size(); i++){
			//System.out.println("id="+msgreceiveFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}
	//根据receiverid查询实体full
	public String selectMsgreceiveFullByLoginuserId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		msgreceiveFullList  = msgreceiveService.selectMsgreceiveFullByLoginuserId(userId);
		for(int i = 0; i < msgreceiveFullList.size(); i++){
			//System.out.println("id="+msgreceiveFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}
*/

	
	@Override
	public Msgreceive getModel() {
		// TODO Auto-generated method stub
		return msgreceive;
	}
	
	
	

//  
	public Msgreceive getMsgreceive() {
		return msgreceive;
	}
	
	public void setMsgreceive(Msgreceive msgreceive) {
		this.msgreceive = msgreceive;
	}
//  entityModel
	public Msgreceive getMsgreceiveModel() {
		return msgreceiveModel;
	}
	
	public void setMsgreceiveModel(Msgreceive msgreceiveModel) {
		this.msgreceiveModel = msgreceiveModel;
	}
	
	public MsgreceiveFull getMsgreceiveFull() {
		return msgreceiveFull;
	}
	
	public void setMsgreceiveFull(MsgreceiveFull msgreceiveFull) {
		this.msgreceiveFull = msgreceiveFull;
	}
	
	public List<Msgreceive> getMsgreceiveList() {
		return msgreceiveList;
	}


	public void setMsgreceiveList(List<Msgreceive> msgreceiveList) {
		this.msgreceiveList = msgreceiveList;
	}

	public List<MsgreceiveFull> getMsgreceiveFullList() {
		return msgreceiveFullList;
	}


	public void setMsgreceiveFullList(List<MsgreceiveFull> msgreceiveFullList) {
		this.msgreceiveFullList = msgreceiveFullList;
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
	
	public int getMsgreceiveid() {
		return msgreceiveid;
	}

	public void setMsgreceiveid(int msgreceiveid) {
		this.msgreceiveid = msgreceiveid;
	}
		public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	public String getMsgreceiveIdsForDel() {
                return msgreceiveIdsForDel;
        }

        public void setMsgreceiveIdsForDel(String msgreceiveIdsForDel) {
                this.msgreceiveIdsForDel = msgreceiveIdsForDel;
        }
}
