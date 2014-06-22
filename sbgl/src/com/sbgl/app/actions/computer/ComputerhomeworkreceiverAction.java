package com.sbgl.app.actions.computer;

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
import com.sbgl.app.services.computer.ComputerhomeworkreceiverService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputerhomeworkreceiverAction")
public class ComputerhomeworkreceiverAction extends BaseAction implements SessionAware,ModelDriven<Computerhomeworkreceiver>{
	
	private static final Log log = LogFactory.getLog(ComputerhomeworkreceiverAction.class);


	
	//Service	
	@Resource
	private ComputerhomeworkreceiverService computerhomeworkreceiverService;
	
	private Computerhomeworkreceiver computerhomeworkreceiver = new Computerhomeworkreceiver();//实例化一个模型
	private Computerhomeworkreceiver computerhomeworkreceiverModel = new Computerhomeworkreceiver();//实例化一个模型
	private ComputerhomeworkreceiverFull computerhomeworkreceiverFull = new ComputerhomeworkreceiverFull();//实例化一个模型
	
	List<Computerhomeworkreceiver> computerhomeworkreceiverList = new ArrayList<Computerhomeworkreceiver>();
	List<ComputerhomeworkreceiverFull> computerhomeworkreceiverFullList = new ArrayList<ComputerhomeworkreceiverFull>();
	private Integer computerhomeworkreceiverid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";		


	String computerhomeworkreceiverIdsForDel;
	
//  manage Computerhomeworkreceiver
	public String manageComputerhomeworkreceiver(){
		log.info(logprefix+"manageComputerhomeworkreceiverFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computerhomeworkreceiverService.countComputerhomeworkreceiverRow());
		computerhomeworkreceiverList  = computerhomeworkreceiverService.selectComputerhomeworkreceiverByPage(page);
		
//		查询全部
//		computerhomeworkreceiverList  = computerhomeworkreceiverService.selectComputerhomeworkreceiverById();
		
	    if(computerhomeworkreceiverList == null){
			computerhomeworkreceiverList = new ArrayList<Computerhomeworkreceiver>();
		}
//		for(int i = 0; i < computerhomeworkreceiverList.size(); i++){
//			System.out.println("id="+computerhomeworkreceiverList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageComputerhomeworkreceiverFull(){
		log.info("exec action method:manageComputerhomeworkreceiverFull");
		
//      分页查询		
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computerhomeworkreceiverService.countComputerhomeworkreceiverRow());
		computerhomeworkreceiverFullList  = computerhomeworkreceiverService.selectComputerhomeworkreceiverFullByPage(page);
		
//		查询全部
//		computerhomeworkreceiverFullList  = computerhomeworkreceiverService.selectComputerhomeworkreceiverFullAll();

		if(computerhomeworkreceiverFullList == null){
			computerhomeworkreceiverFullList = new ArrayList<ComputerhomeworkreceiverFull>();
		}
//		for(int i = 0; i < computerhomeworkreceiverFullList.size(); i++){
//			System.out.println("id="+computerhomeworkreceiverFullList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}			
			
		
	//管理
	public String manageComputerhomeworkreceiverInfo(){
		log.info(logprefix +" manageComputerhomeworkreceiver");
		//Page page = new Page();
		//if()
		computerhomeworkreceiverList  = computerhomeworkreceiverService.selectComputerhomeworkreceiverByPage(page);
		for(int i = 0; i < computerhomeworkreceiverList.size(); i++){
		//	System.out.println("id="+computerhomeworkreceiverList.get(i).getLoginusername());
		}
		return SUCCESS;
	}	
			
	public String addComputerhomeworkreceiver(){	
		log.info("Add Entity");

		try {
			Computerhomeworkreceiver temp = new Computerhomeworkreceiver();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerhomeworkreceiver);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			computerhomeworkreceiverService.addComputerhomeworkreceiver(temp);
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkreceiverAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addComputerhomeworkreceiverAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Computerhomeworkreceiver temp = new Computerhomeworkreceiver();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerhomeworkreceiver);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			computerhomeworkreceiverService.addComputerhomeworkreceiver(temp);
			
			returnJson.setFlag(1);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkreceiverAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

/**
 * 
 * @return
 */
	public String deleteMineComputerhomeworkreceiver( ){
		try{
			System.out.println("作业id:"+computerhomeworkreceiver.getComputerhomeworkid());
			computerhomeworkreceiver = computerhomeworkreceiverService.sel(computerhomeworkreceiver.getComputerhomeworkid(), this.getCurrentUserId());
			if(computerhomeworkreceiver == null){
				return "404";
			}
			System.out.println("删除的作业接收者："+computerhomeworkreceiver.getId());
			computerhomeworkreceiverService.delById(computerhomeworkreceiver.getId());
				
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return "404";
	}


//修改
	public String updateComputerhomeworkreceiver(){
		try {
			if(computerhomeworkreceiver.getId() != null && computerhomeworkreceiver.getId() > 0){				
				Computerhomeworkreceiver tempComputerhomeworkreceiver = computerhomeworkreceiverService.selectComputerhomeworkreceiverById(computerhomeworkreceiver.getId());
																				  								
												  								
								actionMsg = getText("viewComputerhomeworkreceiverSuccess");
			}else{
				actionMsg = getText("viewComputerhomeworkreceiverFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkreceiverAction的方法：viewComputerhomeworkreceiver错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateComputerhomeworkreceiverAjax(){
		log.info(logprefix + "updateComputerhomeworkreceiverAjax,id="+computerhomeworkreceiver.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(computerhomeworkreceiver.getId() != null && computerhomeworkreceiver.getId() > 0){				
				Computerhomeworkreceiver tempComputerhomeworkreceiver = computerhomeworkreceiverService.selectComputerhomeworkreceiverById(computerhomeworkreceiver.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempComputerhomeworkreceiver.setComputerhomeworkid(computerhomeworkreceiver.getComputerhomeworkid());
  				tempComputerhomeworkreceiver.setUserid(computerhomeworkreceiver.getUserid());
 
				
				computerhomeworkreceiverService.updateComputerhomeworkreceiver(tempComputerhomeworkreceiver);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewComputerhomeworkreceiverSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewComputerhomeworkreceiverFail");
				log.info(logprefix + "updateComputerhomeworkreceiverAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkreceiverAction的方法：viewComputerhomeworkreceiver错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editComputerhomeworkreceiver(){
		log.info(logprefix + "editComputerhomeworkreceiver");
			
		try {
			//实体的id可以为0
			if(computerhomeworkreceiver.getId() != null && computerhomeworkreceiver.getId() >= 0){				
				Computerhomeworkreceiver temComputerhomeworkreceiver = computerhomeworkreceiverService.selectComputerhomeworkreceiverById(computerhomeworkreceiver.getId());
				if(temComputerhomeworkreceiver != null){
					BeanUtils.copyProperties(computerhomeworkreceiverModel,temComputerhomeworkreceiver);	
					//actionMsg = getText("selectComputerhomeworkreceiverByIdSuccess");
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
			log.error("类ComputerhomeworkreceiverAction的方法：selectComputerhomeworkreceiverById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editComputerhomeworkreceiverFull(){
		
		log.info(logprefix + "viewComputerhomeworkreceiver");
		
		try {
			if(computerhomeworkreceiver.getId() != null && computerhomeworkreceiver.getId() > 0){				
				ComputerhomeworkreceiverFull temComputerhomeworkreceiverFull = computerhomeworkreceiverService.selectComputerhomeworkreceiverFullById(computerhomeworkreceiver.getId());
				BeanUtils.copyProperties(computerhomeworkreceiverFull,temComputerhomeworkreceiverFull);	
				actionMsg = getText("selectComputerhomeworkreceiverByIdSuccess");
			}else{
				actionMsg = getText("selectComputerhomeworkreceiverByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkreceiverAction的方法：selectComputerhomeworkreceiverFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewComputerhomeworkreceiver(){
		log.info("viewComputerhomeworkreceiver");
		try {
			if(computerhomeworkreceiver.getId() != null && computerhomeworkreceiver.getId() > 0){				
				Computerhomeworkreceiver temComputerhomeworkreceiver = computerhomeworkreceiverService.selectComputerhomeworkreceiverById(computerhomeworkreceiver.getId());
				BeanUtils.copyProperties(computerhomeworkreceiverModel,temComputerhomeworkreceiver);	
				actionMsg = getText("selectComputerhomeworkreceiverByIdSuccess");
			}else{
				actionMsg = getText("selectComputerhomeworkreceiverByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkreceiverAction的方法：selectComputerhomeworkreceiverById错误"+e);
		}


		return "error";

	}	

/**
 * view ComputerhomeworkreceiverFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewComputerhomeworkreceiverFull() {
				
		try {
			int getId = computerhomeworkreceiver.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			ComputerhomeworkreceiverFull temComputerhomeworkreceiverFull = computerhomeworkreceiverService.selectComputerhomeworkreceiverFullById(getId);				
			if(temComputerhomeworkreceiverFull!=null){				
				BeanUtils.copyProperties(computerhomeworkreceiverFull,temComputerhomeworkreceiverFull);
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
	//根据userid 查询实体
	public String selectComputerhomeworkreceiverByLoginuserId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerhomeworkreceiverList  = computerhomeworkreceiverService.selectComputerhomeworkreceiverAll();
		for(int i = 0; i < computerhomeworkreceiverList.size(); i++){
			System.out.println("id="+computerhomeworkreceiverList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据userid 查询实体full
	public String selectComputerhomeworkreceiverFullByLoginuserId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerhomeworkreceiverFullList  = computerhomeworkreceiverService.selectComputerhomeworkreceiverFullByLoginuserId(userId);
		for(int i = 0; i < computerhomeworkreceiverFullList.size(); i++){
			//System.out.println("id="+computerhomeworkreceiverFullList.get(i).getLoginusername());
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
	public Computerhomeworkreceiver getModel() {
		// TODO Auto-generated method stub
		return computerhomeworkreceiver;
	}

//  
	public Computerhomeworkreceiver getComputerhomeworkreceiver() {
		return computerhomeworkreceiver;
	}
	
	public void setComputerhomeworkreceiver(Computerhomeworkreceiver computerhomeworkreceiver) {
		this.computerhomeworkreceiver = computerhomeworkreceiver;
	}
//  entityModel
	public Computerhomeworkreceiver getComputerhomeworkreceiverModel() {
		return computerhomeworkreceiverModel;
	}
	
	public void setComputerhomeworkreceiverModel(Computerhomeworkreceiver computerhomeworkreceiverModel) {
		this.computerhomeworkreceiverModel = computerhomeworkreceiverModel;
	}
	
	public ComputerhomeworkreceiverFull getComputerhomeworkreceiverFull() {
		return computerhomeworkreceiverFull;
	}
	
	public void setComputerhomeworkreceiverFull(ComputerhomeworkreceiverFull computerhomeworkreceiverFull) {
		this.computerhomeworkreceiverFull = computerhomeworkreceiverFull;
	}
	
	public List<Computerhomeworkreceiver> getComputerhomeworkreceiverList() {
		return computerhomeworkreceiverList;
	}


	public void setComputerhomeworkreceiverList(List<Computerhomeworkreceiver> computerhomeworkreceiverList) {
		this.computerhomeworkreceiverList = computerhomeworkreceiverList;
	}

	public List<ComputerhomeworkreceiverFull> getComputerhomeworkreceiverFullList() {
		return computerhomeworkreceiverFullList;
	}


	public void setComputerhomeworkreceiverFullList(List<ComputerhomeworkreceiverFull> computerhomeworkreceiverFullList) {
		this.computerhomeworkreceiverFullList = computerhomeworkreceiverFullList;
	}


	
	public int getComputerhomeworkreceiverid() {
		return computerhomeworkreceiverid;
	}

	public void setComputerhomeworkreceiverid(int computerhomeworkreceiverid) {
		this.computerhomeworkreceiverid = computerhomeworkreceiverid;
	}
	

	
	
	public String getComputerhomeworkreceiverIdsForDel() {
                return computerhomeworkreceiverIdsForDel;
        }

        public void setComputerhomeworkreceiverIdsForDel(String computerhomeworkreceiverIdsForDel) {
                this.computerhomeworkreceiverIdsForDel = computerhomeworkreceiverIdsForDel;
        }
}
