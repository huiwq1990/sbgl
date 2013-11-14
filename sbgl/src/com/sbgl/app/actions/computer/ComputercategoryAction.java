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
	private Integer computercategoryid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";		
	Page page = new Page();
	Integer pageNo=1;	
	
//  manage Computercategory
	public String manageComputercategory(){
		log.info(logprefix+"manageComputercategoryFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computercategoryService.countComputercategoryRow());
		computercategoryList  = computercategoryService.selectComputercategoryByPage(page);
		
//		查询全部
//		computercategoryList  = computercategoryService.selectComputercategoryById();
		
	    if(computercategoryList == null){
			computercategoryList = new ArrayList<Computercategory>();
		}
//		for(int i = 0; i < computercategoryList.size(); i++){
//			System.out.println("id="+computercategoryList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageComputercategoryFull(){
		log.info("exec action method:manageComputercategoryFull");
		
//      分页查询		
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computercategoryService.countComputercategoryRow());
		computercategoryFullList  = computercategoryService.selectComputercategoryFullByPage(page);
		
//		查询全部
//		computercategoryFullList  = computercategoryService.selectComputercategoryFullAll();

		if(computercategoryFullList == null){
			computercategoryFullList = new ArrayList<ComputercategoryFull>();
		}
//		for(int i = 0; i < computercategoryFullList.size(); i++){
//			System.out.println("id="+computercategoryFullList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}			
			
		
	//管理
	public String manageComputercategoryInfo(){
		log.info(logprefix +" manageComputercategory");
		//Page page = new Page();
		//if()
		computercategoryList  = computercategoryService.selectComputercategoryByPage(page);
		for(int i = 0; i < computercategoryList.size(); i++){
		//	System.out.println("id="+computercategoryList.get(i).getLoginusername());
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

	
//	del entityfull
	public String deleteComputercategoryFull(){
		try {
		
			Integer getId = computercategory.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Computercategory temp = computercategoryService.selectComputercategoryById(getId);
			if (temp != null) {
				computercategoryService.deleteComputercategory(getId);
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
	public String deleteComputercategoryFullAjax( ){
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
		log.info(logprefix + "updateComputercategoryAjax,id="+computercategory.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(computercategory.getId() != null && computercategory.getId() > 0){				
				Computercategory tempComputercategory = computercategoryService.selectComputercategoryById(computercategory.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempComputercategory.setParentcomputercategoryid(computercategory.getParentcomputercategoryid());
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
				log.info(logprefix + "updateComputercategoryAjax fail");
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
		log.info(logprefix + "editComputercategory");
			
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
		
		log.info(logprefix + "viewComputercategory");
		
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

/**
 * view ComputercategoryFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewComputercategoryFull() {
				
		try {
			int getId = computercategory.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			ComputercategoryFull temComputercategoryFull = computercategoryService.selectComputercategoryFullById(getId);				
			if(temComputercategoryFull!=null){				
				BeanUtils.copyProperties(computercategoryFull,temComputercategoryFull);
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


	//根据parentcomputercategoryid 查询实体
	public String selectComputercategoryByComputercategoryId() {
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
	//根据parentcomputercategoryid 查询实体full
	public String selectComputercategoryFullByComputercategoryId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computercategoryFullList  = computercategoryService.selectComputercategoryFullByComputercategoryId(userId);
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
	
	public int getComputercategoryid() {
		return computercategoryid;
	}

	public void setComputercategoryid(int computercategoryid) {
		this.computercategoryid = computercategoryid;
	}
		public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
}
