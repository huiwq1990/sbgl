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
import com.sbgl.app.entity.*;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputermodelAction")
public class ComputermodelAction extends ActionSupport implements SessionAware,ModelDriven<Computermodel>{
	
	private static final Log log = LogFactory.getLog(ComputermodelAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private ComputermodelService computermodelService;
	
	private Computermodel computermodel = new Computermodel();//实例化一个模型
	private Computermodel computermodelModel = new Computermodel();//实例化一个模型
	private ComputermodelFull computermodelFull = new ComputermodelFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Computermodel> computermodelList = new ArrayList<Computermodel>();
	List<ComputermodelFull> computermodelFullList = new ArrayList<ComputermodelFull>();
	private Integer computermodelid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";		
	Page page = new Page();
	Integer pageNo=1;	
	
//  manage Computermodel
	public String manageComputermodel(){
		log.info(logprefix+"manageComputermodelFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computermodelService.countComputermodelRow());
		computermodelList  = computermodelService.selectComputermodelByPage(page);
		
//		查询全部
//		computermodelList  = computermodelService.selectComputermodelById();
		
	    if(computermodelList == null){
			computermodelList = new ArrayList<Computermodel>();
		}
//		for(int i = 0; i < computermodelList.size(); i++){
//			System.out.println("id="+computermodelList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageComputermodelFull(){
		log.info("exec action method:manageComputermodelFull");
		
//      分页查询		
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computermodelService.countComputermodelRow());
		computermodelFullList  = computermodelService.selectComputermodelFullByPage(page);
		
//		查询全部
//		computermodelFullList  = computermodelService.selectComputermodelFullAll();

		if(computermodelFullList == null){
			computermodelFullList = new ArrayList<ComputermodelFull>();
		}
//		for(int i = 0; i < computermodelFullList.size(); i++){
//			System.out.println("id="+computermodelFullList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}			
			
		
	//管理
	public String manageComputermodelInfo(){
		log.info(logprefix +" manageComputermodel");
		//Page page = new Page();
		//if()
		computermodelList  = computermodelService.selectComputermodelByPage(page);
		for(int i = 0; i < computermodelList.size(); i++){
		//	System.out.println("id="+computermodelList.get(i).getLoginusername());
		}
		return SUCCESS;
	}	
			
	public String addComputermodel(){	
		log.info("Add Entity");

		try {
			Computermodel temp = new Computermodel();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computermodel);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			computermodelService.addComputermodel(temp);
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputermodelAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addComputermodelAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Computermodel temp = new Computermodel();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computermodel);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			computermodelService.addComputermodel(temp);
			
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
			log.error("类ComputermodelAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteComputermodel( ){
		try{
			if(computermodel.getId() != null && computermodel.getId() > 0){
				computermodelService.deleteComputermodel(computermodel.getId());
				actionMsg = getText("deleteComputermodelSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteComputermodelFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputermodelAction的方法：deleteComputermodel错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteComputermodelAjax( ){
		try{
			if(computermodel.getId() != null && computermodel.getId() >= 0){
				computermodelService.deleteComputermodel(computermodel.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputermodelAction的方法：deleteComputermodel错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteComputermodelFull(){
		try {
		
			Integer getId = computermodel.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Computermodel temp = computermodelService.selectComputermodelById(getId);
			if (temp != null) {
				computermodelService.deleteComputermodel(getId);
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
	public String deleteComputermodelFullAjax( ){
		try{
			if(computermodel.getId() != null && computermodel.getId() >= 0){
				computermodelService.deleteComputermodel(computermodel.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputermodelAction的方法：deleteComputermodel错误"+e);
		}
		return "Error";
	}

//修改
	public String updateComputermodel(){
		try {
			if(computermodel.getId() != null && computermodel.getId() > 0){				
				Computermodel tempComputermodel = computermodelService.selectComputermodelById(computermodel.getId());
																				  								
												  								
												  								
												  								
												  								
												  								
												  								
								actionMsg = getText("viewComputermodelSuccess");
			}else{
				actionMsg = getText("viewComputermodelFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputermodelAction的方法：viewComputermodel错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateComputermodelAjax(){
		log.info(logprefix + "updateComputermodelAjax,id="+computermodel.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(computermodel.getId() != null && computermodel.getId() > 0){				
				Computermodel tempComputermodel = computermodelService.selectComputermodelById(computermodel.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempComputermodel.setName(computermodel.getName());
  				tempComputermodel.setComputercategoryid(computermodel.getComputercategoryid());
  				tempComputermodel.setPicpath(computermodel.getPicpath());
  				tempComputermodel.setCreatetime(computermodel.getCreatetime());
  				tempComputermodel.setCreateuserid(computermodel.getCreateuserid());
  				tempComputermodel.setCount(computermodel.getCount());
  				tempComputermodel.setStatus(computermodel.getStatus());
 
				
				computermodelService.updateComputermodel(tempComputermodel);		
				returnJson.setFlag(1);		
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewComputermodelSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewComputermodelFail");
				log.info(logprefix + "updateComputermodelAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputermodelAction的方法：viewComputermodel错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editComputermodel(){
		log.info(logprefix + "editComputermodel");
			
		try {
			//实体的id可以为0
			if(computermodel.getId() != null && computermodel.getId() >= 0){				
				Computermodel temComputermodel = computermodelService.selectComputermodelById(computermodel.getId());
				if(temComputermodel != null){
					BeanUtils.copyProperties(computermodelModel,temComputermodel);	
					//actionMsg = getText("selectComputermodelByIdSuccess");
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
			log.error("类ComputermodelAction的方法：selectComputermodelById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editComputermodelFull(){
		
		log.info(logprefix + "viewComputermodel");
		
		try {
			if(computermodel.getId() != null && computermodel.getId() > 0){				
				ComputermodelFull temComputermodelFull = computermodelService.selectComputermodelFullById(computermodel.getId());
				BeanUtils.copyProperties(computermodelFull,temComputermodelFull);	
				actionMsg = getText("selectComputermodelByIdSuccess");
			}else{
				actionMsg = getText("selectComputermodelByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputermodelAction的方法：selectComputermodelFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewComputermodel(){
		log.info("viewComputermodel");
		try {
			if(computermodel.getId() != null && computermodel.getId() > 0){				
				Computermodel temComputermodel = computermodelService.selectComputermodelById(computermodel.getId());
				BeanUtils.copyProperties(computermodelModel,temComputermodel);	
				actionMsg = getText("selectComputermodelByIdSuccess");
			}else{
				actionMsg = getText("selectComputermodelByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputermodelAction的方法：selectComputermodelById错误"+e);
		}


		return "error";

	}	

/**
 * view ComputermodelFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewComputermodelFull() {
				
		try {
			int getId = computermodel.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			ComputermodelFull temComputermodelFull = computermodelService.selectComputermodelFullById(getId);				
			if(temComputermodelFull!=null){				
				BeanUtils.copyProperties(computermodelFull,temComputermodelFull);
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

	
	//根据对象Id查询
	public String selectComputermodelById(){
		log.info("selectComputermodelById");
		try {
			if(computermodel.getId() != null && computermodel.getId() > 0){				
				Computermodel temComputermodel = computermodelService.selectComputermodelById(computermodel.getId());
				BeanUtils.copyProperties(computermodelModel,temComputermodel);	
				actionMsg = getText("selectComputermodelByIdSuccess");
			}else{
				actionMsg = getText("selectComputermodelByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputermodelAction的方法：selectComputermodelById错误"+e);
		}


		return "error";

	}	
	

	

	//查询全部
	public String selectComputermodelAll(){
		
		computermodelList  = computermodelService.selectComputermodelAll();
		for(int i = 0; i < computermodelList.size(); i++){
			System.out.println("id="+computermodelList.get(i).getId());
		}
		return SUCCESS;
	}



	
	//根据对象Id查询Full
	public String selectComputermodelFullById(){
		System.out.println("selectComputermodelFullById");
			try {
				if(computermodel.getId() != null && computermodel.getId() >= 0){				
				ComputermodelFull temComputermodelFull = computermodelService.selectComputermodelFullById(computermodel.getId());
				BeanUtils.copyProperties(computermodelFull,temComputermodelFull);	
				actionMsg = getText("selectComputermodelByIdSuccess");
			}else{
				actionMsg = getText("selectComputermodelByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputermodelAction的方法：selectComputermodelFullById错误"+e);
		}
		
		return "error";

	}	

	
	//查询全部Full
	public String selectComputermodelFullAll(){
		log.info("exec selectComputermodelFullAll");
		computermodelFullList  = computermodelService.selectComputermodelFullAll();
		for(int i = 0; i < computermodelFullList.size(); i++){
		//	System.out.println("id="+computermodelFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}


	//根据computercategoryid 查询实体
	public String selectComputermodelByComputercategoryId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computermodelList  = computermodelService.selectComputermodelAll();
		for(int i = 0; i < computermodelList.size(); i++){
			System.out.println("id="+computermodelList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据computercategoryid 查询实体full
	public String selectComputermodelFullByComputercategoryId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computermodelFullList  = computermodelService.selectComputermodelFullByComputercategoryId(userId);
		for(int i = 0; i < computermodelFullList.size(); i++){
			//System.out.println("id="+computermodelFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}

	//get set
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	    this.session = session;
	}
	
	@Override
	public Computermodel getModel() {
		// TODO Auto-generated method stub
		return computermodel;
	}

//  
	public Computermodel getComputermodel() {
		return computermodel;
	}
	
	public void setComputermodel(Computermodel computermodel) {
		this.computermodel = computermodel;
	}
//  entityModel
	public Computermodel getComputermodelModel() {
		return computermodelModel;
	}
	
	public void setComputermodelModel(Computermodel computermodelModel) {
		this.computermodelModel = computermodelModel;
	}
	
	public ComputermodelFull getComputermodelFull() {
		return computermodelFull;
	}
	
	public void setComputermodelFull(ComputermodelFull computermodelFull) {
		this.computermodelFull = computermodelFull;
	}
	
	public List<Computermodel> getComputermodelList() {
		return computermodelList;
	}


	public void setComputermodelList(List<Computermodel> computermodelList) {
		this.computermodelList = computermodelList;
	}

	public List<ComputermodelFull> getComputermodelFullList() {
		return computermodelFullList;
	}


	public void setComputermodelFullList(List<ComputermodelFull> computermodelFullList) {
		this.computermodelFullList = computermodelFullList;
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
	
	public int getComputermodelid() {
		return computermodelid;
	}

	public void setComputermodelid(int computermodelid) {
		this.computermodelid = computermodelid;
	}
		public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
}
