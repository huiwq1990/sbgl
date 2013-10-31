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
import com.sbgl.app.services.computer.ComputerService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputerAction")
public class ComputerAction extends ActionSupport implements SessionAware,ModelDriven<Computer>{
	
	private static final Log log = LogFactory.getLog(ComputerAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private ComputerService computerService;
	
	private Computer computer = new Computer();//实例化一个模型
	private Computer computerModel = new Computer();//实例化一个模型
	private ComputerFull computerFull = new ComputerFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Computer> computerList = new ArrayList<Computer>();
	List<ComputerFull> computerFullList = new ArrayList<ComputerFull>();
	private String logperfix = "exec method";		
	Page page = new Page();
			
	//管理
	public String manageComputer(){
		log.info(logperfix +" manageComputer");
		//Page page = new Page();
		//if()
		computerList  = computerService.selectComputerByPage(page);
		for(int i = 0; i < computerList.size(); i++){
		//	System.out.println("id="+computerList.get(i).getLoginusername());
		}
		return SUCCESS;
	}		
			
	//管理
	public String manageComputerFull(){
		log.info("exec action method:manageComputerFull");
		
		
		computerFullList  = computerService.selectComputerFullAll();
		for(int i = 0; i < computerFullList.size(); i++){
		//	System.out.println("id="+computerFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}			
			
			
	public String addComputer(){	
		log.info("Add Entity");

		try {
			Computer temp = new Computer();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computer);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			computerService.addComputer(temp);
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addComputerAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Computer temp = new Computer();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computer);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			computerService.addComputer(temp);
			
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
			log.error("类ComputerAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteComputer( ){
		try{
			if(computer.getId() != null && computer.getId() > 0){
				computerService.deleteComputer(computer.getId());
				actionMsg = getText("deleteComputerSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteComputerFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerAction的方法：deleteComputer错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteComputerAjax( ){
		try{
			if(computer.getId() != null && computer.getId() >= 0){
				computerService.deleteComputer(computer.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerAction的方法：deleteComputer错误"+e);
		}
		return "Error";
	}	

//修改
	public String updateComputer(){
		try {
			if(computer.getId() != null && computer.getId() > 0){				
				Computer tempComputer = computerService.selectComputerById(computer.getId());
																				  								
												  								
												  								
												  								
												  								
												  								
								actionMsg = getText("viewComputerSuccess");
			}else{
				actionMsg = getText("viewComputerFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerAction的方法：viewComputer错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateComputerAjax(){
		log.info(logperfix + "updateComputerAjax");
		ReturnJson returnJson = new ReturnJson();
		try {
			if(computer.getId() != null && computer.getId() > 0){				
				Computer tempComputer = computerService.selectComputerById(computer.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempComputer.setSerialnumber(computer.getSerialnumber());
  				tempComputer.setName(computer.getName());
  				tempComputer.setComputercategoryid(computer.getComputercategoryid());
  				tempComputer.setCreatetime(computer.getCreatetime());
  				tempComputer.setCreateuserid(computer.getCreateuserid());
  				tempComputer.setStatus(computer.getStatus());
 
				
				computerService.updateComputer(tempComputer);		
				returnJson.setFlag(1);		
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewComputerSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewComputerFail");
				System.out.println(actionMsg);
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerAction的方法：viewComputer错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editComputer(){
		log.info(logperfix + "editComputer");
			
		try {
			//实体的id可以为0
			if(computer.getId() != null && computer.getId() >= 0){				
				Computer temComputer = computerService.selectComputerById(computer.getId());
				if(temComputer != null){
					BeanUtils.copyProperties(computerModel,temComputer);	
					//actionMsg = getText("selectComputerByIdSuccess");
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
			log.error("类ComputerAction的方法：selectComputerById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editComputerFull(){
		
		log.info(logperfix + "viewComputer");
		
		try {
			if(computer.getId() != null && computer.getId() > 0){				
				ComputerFull temComputerFull = computerService.selectComputerFullById(computer.getId());
				BeanUtils.copyProperties(computerFull,temComputerFull);	
				actionMsg = getText("selectComputerByIdSuccess");
			}else{
				actionMsg = getText("selectComputerByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerAction的方法：selectComputerFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewComputer(){
		log.info("viewComputer");
		try {
			if(computer.getId() != null && computer.getId() > 0){				
				Computer temComputer = computerService.selectComputerById(computer.getId());
				BeanUtils.copyProperties(computerModel,temComputer);	
				actionMsg = getText("selectComputerByIdSuccess");
			}else{
				actionMsg = getText("selectComputerByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerAction的方法：selectComputerById错误"+e);
		}


		return "error";

	}	
	
	
	//根据对象Id查询
	public String selectComputerById(){
		log.info("selectComputerById");
		try {
			if(computer.getId() != null && computer.getId() > 0){				
				Computer temComputer = computerService.selectComputerById(computer.getId());
				BeanUtils.copyProperties(computerModel,temComputer);	
				actionMsg = getText("selectComputerByIdSuccess");
			}else{
				actionMsg = getText("selectComputerByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerAction的方法：selectComputerById错误"+e);
		}


		return "error";

	}	
	

	

	//查询全部
	public String selectComputerAll(){
		
		computerList  = computerService.selectComputerAll();
		for(int i = 0; i < computerList.size(); i++){
			System.out.println("id="+computerList.get(i).getId());
		}
		return SUCCESS;
	}

	//view entityFull
	public String viewComputerFull(){
		System.out.println("viewComputerFull");
			try {
				if(computer.getId() != null && computer.getId() > 0){				
				ComputerFull temComputerFull = computerService.selectComputerFullById(computer.getId());
				BeanUtils.copyProperties(computerFull,temComputerFull);	
				actionMsg = getText("selectComputerByIdSuccess");
			}else{
				actionMsg = getText("selectComputerByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerAction的方法：viewComputerFull错误"+e);
		}
		
		return "error";

	}		
	
	
	//根据对象Id查询Full
	public String selectComputerFullById(){
		System.out.println("selectComputerFullById");
			try {
				if(computer.getId() != null && computer.getId() >= 0){				
				ComputerFull temComputerFull = computerService.selectComputerFullById(computer.getId());
				BeanUtils.copyProperties(computerFull,temComputerFull);	
				actionMsg = getText("selectComputerByIdSuccess");
			}else{
				actionMsg = getText("selectComputerByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerAction的方法：selectComputerFullById错误"+e);
		}
		
		return "error";

	}	

	
	//查询全部Full
	public String selectComputerFullAll(){
		log.info("exec selectComputerFullAll");
		computerFullList  = computerService.selectComputerFullAll();
		for(int i = 0; i < computerFullList.size(); i++){
		//	System.out.println("id="+computerFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}


	//根据computercategoryid 查询实体
	public String selectComputerByComputercategoryId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerList  = computerService.selectComputerAll();
		for(int i = 0; i < computerList.size(); i++){
			System.out.println("id="+computerList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据createuserid 查询实体
	public String selectComputerByLoginuserId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerList  = computerService.selectComputerAll();
		for(int i = 0; i < computerList.size(); i++){
			System.out.println("id="+computerList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据computercategoryid 查询实体full
	public String selectComputerFullByComputercategoryId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerFullList  = computerService.selectComputerFullByComputercategoryId(userId);
		for(int i = 0; i < computerFullList.size(); i++){
			//System.out.println("id="+computerFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}
	//根据createuserid 查询实体full
	public String selectComputerFullByLoginuserId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerFullList  = computerService.selectComputerFullByLoginuserId(userId);
		for(int i = 0; i < computerFullList.size(); i++){
			//System.out.println("id="+computerFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}

	//get set
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	    this.session = session;
	}
	
	@Override
	public Computer getModel() {
		// TODO Auto-generated method stub
		return computer;
	}

//  
	public Computer getComputer() {
		return computer;
	}
	
	public void setComputer(Computer computer) {
		this.computer = computer;
	}
//  entityModel
	public Computer getComputerModel() {
		return computerModel;
	}
	
	public void setComputerModel(Computer computerModel) {
		this.computerModel = computerModel;
	}
	
	public ComputerFull getComputerFull() {
		return computerFull;
	}
	
	public void setComputerFull(ComputerFull computerFull) {
		this.computerFull = computerFull;
	}
	
	public List<Computer> getComputerList() {
		return computerList;
	}


	public void setComputerList(List<Computer> computerList) {
		this.computerList = computerList;
	}

	public List<ComputerFull> getComputerFullList() {
		return computerFullList;
	}


	public void setComputerFullList(List<ComputerFull> computerFullList) {
		this.computerFullList = computerFullList;
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
