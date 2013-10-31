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
import com.sbgl.app.services.computer.ComputercategoryService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputercategoryAction")
public class ComputercategoryAction extends ActionSupport implements SessionAware,ModelDriven<Computercategory>{
	
	private static final Log log = LogFactory.getLog(ComputercategoryAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private ComputercategoryService computercategoryService;
	
	private Computercategory computercategory = new Computercategory();//实例化一个模型
	private Computercategory computercategoryModel = new Computercategory();//实例化一个模型
	private ComputercategoryFull computercategoryFull = new ComputercategoryFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Computercategory> computercategoryList = new ArrayList<Computercategory>();
	List<ComputercategoryFull> computercategoryFullList = new ArrayList<ComputercategoryFull>();
	private String logperfix = "exec method";		
	Page page = new Page();
			
	//管理
	public String manageComputercategory(){
		log.info(logperfix +" manageComputercategory");
		//Page page = new Page();
		//if()
		computercategoryList  = computercategoryService.selectComputercategoryByPage(page);
		for(int i = 0; i < computercategoryList.size(); i++){
		//	System.out.println("id="+computercategoryList.get(i).getLoginusername());
		}
		return SUCCESS;
	}		
			
	//管理
	public String manageComputercategoryFull(){
		log.info("exec action method:manageComputercategoryFull");
		
		
		computercategoryFullList  = computercategoryService.selectComputercategoryFullAll();
		for(int i = 0; i < computercategoryFullList.size(); i++){
		//	System.out.println("id="+computercategoryFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}			
			
			
	public String addComputercategory(){	
		log.info("Add Entity");

		try {
			Computercategory temp = new Computercategory();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computercategory);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			computercategoryService.addComputercategory(temp);
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputercategoryAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addComputercategoryAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Computercategory temp = new Computercategory();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computercategory);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			computercategoryService.addComputercategory(temp);
			
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
			log.error("类ComputercategoryAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteComputercategory( ){
		try{
			if(computercategory.getId() != null && computercategory.getId() > 0){
				computercategoryService.deleteComputercategory(computercategory.getId());
				actionMsg = getText("deleteComputercategorySuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteComputercategoryFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputercategoryAction的方法：deleteComputercategory错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteComputercategoryAjax( ){
		try{
			if(computercategory.getId() != null && computercategory.getId() >= 0){
				computercategoryService.deleteComputercategory(computercategory.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputercategoryAction的方法：deleteComputercategory错误"+e);
		}
		return "Error";
	}	

//修改
	public String updateComputercategory(){
		try {
			if(computercategory.getId() != null && computercategory.getId() > 0){				
				Computercategory tempComputercategory = computercategoryService.selectComputercategoryById(computercategory.getId());
																				  								
												  								
												  								
												  								
								actionMsg = getText("viewComputercategorySuccess");
			}else{
				actionMsg = getText("viewComputercategoryFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputercategoryAction的方法：viewComputercategory错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateComputercategoryAjax(){
		log.info(logperfix + "updateComputercategoryAjax");
		ReturnJson returnJson = new ReturnJson();
		try {
			if(computercategory.getId() != null && computercategory.getId() > 0){				
				Computercategory tempComputercategory = computercategoryService.selectComputercategoryById(computercategory.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempComputercategory.setName(computercategory.getName());
  				tempComputercategory.setCreatetime(computercategory.getCreatetime());
  				tempComputercategory.setCreateuserid(computercategory.getCreateuserid());
  				tempComputercategory.setStatus(computercategory.getStatus());
 
				
				computercategoryService.updateComputercategory(tempComputercategory);		
				returnJson.setFlag(1);		
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewComputercategorySuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewComputercategoryFail");
				System.out.println(actionMsg);
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputercategoryAction的方法：viewComputercategory错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editComputercategory(){
		log.info(logperfix + "editComputercategory");
			
		try {
			//实体的id可以为0
			if(computercategory.getId() != null && computercategory.getId() >= 0){				
				Computercategory temComputercategory = computercategoryService.selectComputercategoryById(computercategory.getId());
				if(temComputercategory != null){
					BeanUtils.copyProperties(computercategoryModel,temComputercategory);	
					//actionMsg = getText("selectComputercategoryByIdSuccess");
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
			log.error("类ComputercategoryAction的方法：selectComputercategoryById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editComputercategoryFull(){
		
		log.info(logperfix + "viewComputercategory");
		
		try {
			if(computercategory.getId() != null && computercategory.getId() > 0){				
				ComputercategoryFull temComputercategoryFull = computercategoryService.selectComputercategoryFullById(computercategory.getId());
				BeanUtils.copyProperties(computercategoryFull,temComputercategoryFull);	
				actionMsg = getText("selectComputercategoryByIdSuccess");
			}else{
				actionMsg = getText("selectComputercategoryByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputercategoryAction的方法：selectComputercategoryFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewComputercategory(){
		log.info("viewComputercategory");
		try {
			if(computercategory.getId() != null && computercategory.getId() > 0){				
				Computercategory temComputercategory = computercategoryService.selectComputercategoryById(computercategory.getId());
				BeanUtils.copyProperties(computercategoryModel,temComputercategory);	
				actionMsg = getText("selectComputercategoryByIdSuccess");
			}else{
				actionMsg = getText("selectComputercategoryByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputercategoryAction的方法：selectComputercategoryById错误"+e);
		}


		return "error";

	}	
	
	
	//根据对象Id查询
	public String selectComputercategoryById(){
		log.info("selectComputercategoryById");
		try {
			if(computercategory.getId() != null && computercategory.getId() > 0){				
				Computercategory temComputercategory = computercategoryService.selectComputercategoryById(computercategory.getId());
				BeanUtils.copyProperties(computercategoryModel,temComputercategory);	
				actionMsg = getText("selectComputercategoryByIdSuccess");
			}else{
				actionMsg = getText("selectComputercategoryByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputercategoryAction的方法：selectComputercategoryById错误"+e);
		}


		return "error";

	}	
	

	

	//查询全部
	public String selectComputercategoryAll(){
		
		computercategoryList  = computercategoryService.selectComputercategoryAll();
		for(int i = 0; i < computercategoryList.size(); i++){
			System.out.println("id="+computercategoryList.get(i).getId());
		}
		return SUCCESS;
	}

	//view entityFull
	public String viewComputercategoryFull(){
		System.out.println("viewComputercategoryFull");
			try {
				if(computercategory.getId() != null && computercategory.getId() > 0){				
				ComputercategoryFull temComputercategoryFull = computercategoryService.selectComputercategoryFullById(computercategory.getId());
				BeanUtils.copyProperties(computercategoryFull,temComputercategoryFull);	
				actionMsg = getText("selectComputercategoryByIdSuccess");
			}else{
				actionMsg = getText("selectComputercategoryByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputercategoryAction的方法：viewComputercategoryFull错误"+e);
		}
		
		return "error";

	}		
	
	
	//根据对象Id查询Full
	public String selectComputercategoryFullById(){
		System.out.println("selectComputercategoryFullById");
			try {
				if(computercategory.getId() != null && computercategory.getId() >= 0){				
				ComputercategoryFull temComputercategoryFull = computercategoryService.selectComputercategoryFullById(computercategory.getId());
				BeanUtils.copyProperties(computercategoryFull,temComputercategoryFull);	
				actionMsg = getText("selectComputercategoryByIdSuccess");
			}else{
				actionMsg = getText("selectComputercategoryByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputercategoryAction的方法：selectComputercategoryFullById错误"+e);
		}
		
		return "error";

	}	

	
	//查询全部Full
	public String selectComputercategoryFullAll(){
		log.info("exec selectComputercategoryFullAll");
		computercategoryFullList  = computercategoryService.selectComputercategoryFullAll();
		for(int i = 0; i < computercategoryFullList.size(); i++){
		//	System.out.println("id="+computercategoryFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}


	//根据createuserid 查询实体
	public String selectComputercategoryByLoginuserId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computercategoryList  = computercategoryService.selectComputercategoryAll();
		for(int i = 0; i < computercategoryList.size(); i++){
			System.out.println("id="+computercategoryList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据createuserid 查询实体full
	public String selectComputercategoryFullByLoginuserId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computercategoryFullList  = computercategoryService.selectComputercategoryFullByLoginuserId(userId);
		for(int i = 0; i < computercategoryFullList.size(); i++){
			//System.out.println("id="+computercategoryFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}

	//get set
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	    this.session = session;
	}
	
	@Override
	public Computercategory getModel() {
		// TODO Auto-generated method stub
		return computercategory;
	}

//  
	public Computercategory getComputercategory() {
		return computercategory;
	}
	
	public void setComputercategory(Computercategory computercategory) {
		this.computercategory = computercategory;
	}
//  entityModel
	public Computercategory getComputercategoryModel() {
		return computercategoryModel;
	}
	
	public void setComputercategoryModel(Computercategory computercategoryModel) {
		this.computercategoryModel = computercategoryModel;
	}
	
	public ComputercategoryFull getComputercategoryFull() {
		return computercategoryFull;
	}
	
	public void setComputercategoryFull(ComputercategoryFull computercategoryFull) {
		this.computercategoryFull = computercategoryFull;
	}
	
	public List<Computercategory> getComputercategoryList() {
		return computercategoryList;
	}


	public void setComputercategoryList(List<Computercategory> computercategoryList) {
		this.computercategoryList = computercategoryList;
	}

	public List<ComputercategoryFull> getComputercategoryFullList() {
		return computercategoryFullList;
	}


	public void setComputercategoryFullList(List<ComputercategoryFull> computercategoryFullList) {
		this.computercategoryFullList = computercategoryFullList;
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
	
}
