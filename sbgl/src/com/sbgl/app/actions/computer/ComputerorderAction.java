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
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputerorderAction")
public class ComputerorderAction extends ActionSupport implements SessionAware,ModelDriven<Computerorder>{
	
	private static final Log log = LogFactory.getLog(ComputerorderAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private ComputerorderService computerorderService;
	
	private Computerorder computerorder = new Computerorder();//实例化一个模型
	private Computerorder computerorderModel = new Computerorder();//实例化一个模型
	private ComputerorderFull computerorderFull = new ComputerorderFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Computerorder> computerorderList = new ArrayList<Computerorder>();
	List<ComputerorderFull> computerorderFullList = new ArrayList<ComputerorderFull>();
	private Integer computerorderid; //entity full 的id属性名称		
	private String logperfix = "exec action method:";		
	Page page = new Page();
		
	
		//管理
	public String manageComputerorder(){
		log.info("exec action method:manageComputerorderFull");
		
		
		computerorderFullList  = computerorderService.selectComputerorderFullAll();
		for(int i = 0; i < computerorderFullList.size(); i++){
		//	System.out.println("id="+computerorderFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}	
	
	//管理
	public String manageComputerorderInfo(){
		log.info(logperfix +" manageComputerorder");
		//Page page = new Page();
		//if()
		computerorderList  = computerorderService.selectComputerorderByPage(page);
		for(int i = 0; i < computerorderList.size(); i++){
		//	System.out.println("id="+computerorderList.get(i).getLoginusername());
		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageComputerorderFull(){
		log.info("exec action method:manageComputerorderFull");
		
//      分页查询		
		Page page = new Page();
		computerorderFullList  = computerorderService.selectComputerorderFullByPage(page);
		
//		查询全部
//		computerorderFullList  = computerorderService.selectComputerorderFullAll();

		for(int i = 0; i < computerorderFullList.size(); i++){
		//	System.out.println("id="+computerorderFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}			
			
			
	public String addComputerorder(){	
		log.info("Add Entity");

		try {
			Computerorder temp = new Computerorder();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerorder);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			computerorderService.addComputerorder(temp);
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addComputerorderAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Computerorder temp = new Computerorder();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerorder);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			computerorderService.addComputerorder(temp);
			
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
			log.error("类ComputerorderAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteComputerorder( ){
		try{
			if(computerorder.getId() != null && computerorder.getId() > 0){
				computerorderService.deleteComputerorder(computerorder.getId());
				actionMsg = getText("deleteComputerorderSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteComputerorderFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderAction的方法：deleteComputerorder错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteComputerorderAjax( ){
		try{
			if(computerorder.getId() != null && computerorder.getId() >= 0){
				computerorderService.deleteComputerorder(computerorder.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderAction的方法：deleteComputerorder错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteComputerorderFull(){
		try {
		
			Integer getId = computerorder.getId();
			if(getId != null && getId >= 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Computerorder temp = computerorderService.selectComputerorderById(getId);
			if (temp != null) {
				computerorderService.deleteComputerorder(getId);
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
	public String deleteComputerorderFullAjax( ){
		try{
			if(computerorder.getId() != null && computerorder.getId() >= 0){
				computerorderService.deleteComputerorder(computerorder.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderAction的方法：deleteComputerorder错误"+e);
		}
		return "Error";
	}

//修改
	public String updateComputerorder(){
		try {
			if(computerorder.getId() != null && computerorder.getId() > 0){				
				Computerorder tempComputerorder = computerorderService.selectComputerorderById(computerorder.getId());
																				  								
												  								
												  								
												  								
								actionMsg = getText("viewComputerorderSuccess");
			}else{
				actionMsg = getText("viewComputerorderFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderAction的方法：viewComputerorder错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateComputerorderAjax(){
		log.info(logperfix + "updateComputerorderAjax,id="+computerorder.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(computerorder.getId() != null && computerorder.getId() > 0){				
				Computerorder tempComputerorder = computerorderService.selectComputerorderById(computerorder.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempComputerorder.setSerialnumber(computerorder.getSerialnumber());
  				tempComputerorder.setUserid(computerorder.getUserid());
  				tempComputerorder.setCreatetime(computerorder.getCreatetime());
  				tempComputerorder.setStatus(computerorder.getStatus());
 
				
				computerorderService.updateComputerorder(tempComputerorder);		
				returnJson.setFlag(1);		
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewComputerorderSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewComputerorderFail");
				log.info(logperfix + "updateComputerorderAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderAction的方法：viewComputerorder错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editComputerorder(){
		log.info(logperfix + "editComputerorder");
			
		try {
			//实体的id可以为0
			if(computerorder.getId() != null && computerorder.getId() >= 0){				
				Computerorder temComputerorder = computerorderService.selectComputerorderById(computerorder.getId());
				if(temComputerorder != null){
					BeanUtils.copyProperties(computerorderModel,temComputerorder);	
					//actionMsg = getText("selectComputerorderByIdSuccess");
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
			log.error("类ComputerorderAction的方法：selectComputerorderById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editComputerorderFull(){
		
		log.info(logperfix + "viewComputerorder");
		
		try {
			if(computerorder.getId() != null && computerorder.getId() > 0){				
				ComputerorderFull temComputerorderFull = computerorderService.selectComputerorderFullById(computerorder.getId());
				BeanUtils.copyProperties(computerorderFull,temComputerorderFull);	
				actionMsg = getText("selectComputerorderByIdSuccess");
			}else{
				actionMsg = getText("selectComputerorderByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderAction的方法：selectComputerorderFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewComputerorder(){
		log.info("viewComputerorder");
		try {
			if(computerorder.getId() != null && computerorder.getId() > 0){				
				Computerorder temComputerorder = computerorderService.selectComputerorderById(computerorder.getId());
				BeanUtils.copyProperties(computerorderModel,temComputerorder);	
				actionMsg = getText("selectComputerorderByIdSuccess");
			}else{
				actionMsg = getText("selectComputerorderByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderAction的方法：selectComputerorderById错误"+e);
		}


		return "error";

	}	

/**
 * view ComputerorderFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewYaomingFull() {
				
		try {
			int getId = computerorder.getId();
			log.info(this.logperfix + ";id=" + getId);
			
			if (getId > 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			ComputerorderFull temComputerorderFull = computerorderService.selectComputerorderFullById(getId);				
			if(temComputerorderFull!=null){				
				BeanUtils.copyProperties(computerorderFull,temComputerorderFull);
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
	public String selectComputerorderById(){
		log.info("selectComputerorderById");
		try {
			if(computerorder.getId() != null && computerorder.getId() > 0){				
				Computerorder temComputerorder = computerorderService.selectComputerorderById(computerorder.getId());
				BeanUtils.copyProperties(computerorderModel,temComputerorder);	
				actionMsg = getText("selectComputerorderByIdSuccess");
			}else{
				actionMsg = getText("selectComputerorderByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderAction的方法：selectComputerorderById错误"+e);
		}


		return "error";

	}	
	

	

	//查询全部
	public String selectComputerorderAll(){
		
		computerorderList  = computerorderService.selectComputerorderAll();
		for(int i = 0; i < computerorderList.size(); i++){
			System.out.println("id="+computerorderList.get(i).getId());
		}
		return SUCCESS;
	}



	
	//根据对象Id查询Full
	public String selectComputerorderFullById(){
		System.out.println("selectComputerorderFullById");
			try {
				if(computerorder.getId() != null && computerorder.getId() >= 0){				
				ComputerorderFull temComputerorderFull = computerorderService.selectComputerorderFullById(computerorder.getId());
				BeanUtils.copyProperties(computerorderFull,temComputerorderFull);	
				actionMsg = getText("selectComputerorderByIdSuccess");
			}else{
				actionMsg = getText("selectComputerorderByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderAction的方法：selectComputerorderFullById错误"+e);
		}
		
		return "error";

	}	

	
	//查询全部Full
	public String selectComputerorderFullAll(){
		log.info("exec selectComputerorderFullAll");
		computerorderFullList  = computerorderService.selectComputerorderFullAll();
		for(int i = 0; i < computerorderFullList.size(); i++){
		//	System.out.println("id="+computerorderFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}


	//根据userid 查询实体
	public String selectComputerorderByLoginuserId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerorderList  = computerorderService.selectComputerorderAll();
		for(int i = 0; i < computerorderList.size(); i++){
			System.out.println("id="+computerorderList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据userid 查询实体full
	public String selectComputerorderFullByLoginuserId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerorderFullList  = computerorderService.selectComputerorderFullByLoginuserId(userId);
		for(int i = 0; i < computerorderFullList.size(); i++){
			//System.out.println("id="+computerorderFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}

	//get set
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	    this.session = session;
	}
	
	@Override
	public Computerorder getModel() {
		// TODO Auto-generated method stub
		return computerorder;
	}

//  
	public Computerorder getComputerorder() {
		return computerorder;
	}
	
	public void setComputerorder(Computerorder computerorder) {
		this.computerorder = computerorder;
	}
//  entityModel
	public Computerorder getComputerorderModel() {
		return computerorderModel;
	}
	
	public void setComputerorderModel(Computerorder computerorderModel) {
		this.computerorderModel = computerorderModel;
	}
	
	public ComputerorderFull getComputerorderFull() {
		return computerorderFull;
	}
	
	public void setComputerorderFull(ComputerorderFull computerorderFull) {
		this.computerorderFull = computerorderFull;
	}
	
	public List<Computerorder> getComputerorderList() {
		return computerorderList;
	}


	public void setComputerorderList(List<Computerorder> computerorderList) {
		this.computerorderList = computerorderList;
	}

	public List<ComputerorderFull> getComputerorderFullList() {
		return computerorderFullList;
	}


	public void setComputerorderFullList(List<ComputerorderFull> computerorderFullList) {
		this.computerorderFullList = computerorderFullList;
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
	
	public int getComputerorderid() {
		return computerorderid;
	}

	public void setComputerorderid(int computerorderid) {
		this.computerorderid = computerorderid;
	}
	
}
