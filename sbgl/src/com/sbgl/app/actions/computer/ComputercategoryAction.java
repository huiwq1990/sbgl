package com.sbgl.app.actions.computer;

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
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.computer.ComputercategoryService;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputercategoryAction")
public class ComputercategoryAction extends ActionSupport implements SessionAware,ModelDriven<Computercategory>{
	
	private static final Log log = LogFactory.getLog(ComputercategoryAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private ComputercategoryService computercategoryService;
	
	@Resource
	private ComputermodelService computermodelService;
	
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

	ReturnJson returnJson = new ReturnJson();
	
	
	//添加信息
	private String inputAddCategoryNameEn;
	private String inputAddCategoryNameCh;
	
	//修改信息
	private int computercategoryIdCh;
	private String computercategoryNameCh;
	private int computercategoryIdEn;
	private String computercategoryNameEn;
	
	//删除
	String computercategoryIdsForDel;
	
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
		
		//名称不规范
//		boolean pass = checkComputercategoryName();
//		if(!pass){
//			return SUCCESS;
//		}
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		String uidStr = ComputerCookieUtil.getCookieValue(cookies, ComputerConfig.cookieuserid);
		
		try {
			Computercategory tempCh = new Computercategory();		
			BeanUtils.copyProperties(tempCh, computercategory);		
			tempCh.setName(inputAddCategoryNameCh.trim());
			tempCh.setLanguagetype("0");
			tempCh.setCreateuserid(Integer.valueOf(uidStr));
			tempCh.setStatus(0);
			
			Computercategory tempEn = new Computercategory();			
			BeanUtils.copyProperties(tempEn, computercategory);		
			tempEn.setName(inputAddCategoryNameEn.trim());
			tempEn.setLanguagetype("1");			
			tempEn.setCreateuserid(Integer.valueOf(uidStr));
			tempEn.setStatus(0);
			
			computercategoryService.addComputercategory(tempCh,tempEn);
			
			returnJson.setFlag(1);
			returnJson.setReason("添加分类成功");
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
		returnJson.setReason("内部错误，添加分类失败");
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}
	
	private boolean checkComputercategoryName(){
		if(computercategory.getName()==null || computercategory.getName().trim().equals("")){
			returnJson.setFlag(0);	
			returnJson.setReason("分类名称不能为空");
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();				
			return false;
		}
		
		
		
		if(computercategoryService.isComputercategoryNameExist(computercategory.getName().trim())){
			returnJson.setFlag(0);	
			returnJson.setReason("分类名称重复");
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();				
			return false;
		}
		
		return true;
	}

//删除
	public String deleteComputercategory( ){
		try{
			String ids[] = computercategoryIdsForDel.split(";");
			for(int i=0; i < ids.length-1;i++){
				int tempDelId = Integer.valueOf(ids[i]);
				if(tempDelId > 0){
					computercategoryService.deleteComputercategory(tempDelId);
					actionMsg = getText("deleteComputercategorySuccess");
				}else{
					System.out.println("删除的id不存在");
					actionMsg = getText("deleteComputercategoryFail");
					return "Error";
				}
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
		
		try{
			String ids[] = computercategoryIdsForDel.split(";");
			for(int i=0; i < ids.length-1;i++){
				
				
				Integer tempDelId = Integer.valueOf(ids[i]);			
				if(tempDelId != null || tempDelId < 0){
					log.info("删除的id不规范");
					return "Error";
				}	
				Computercategory temp = computercategoryService.selectComputercategoryById(tempDelId);			
				if (temp != null) {				
					computercategoryService.deleteComputercategory(tempDelId);			
				} else {
					log.info("删除的id不存在");			
					return "Error";	
				}
			}
			
			return SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error";
	}
	
	//del entityfull Ajax
	/**
	 * 删除分类，将相应分类下面的型号设置为-1
	 */
	public String deleteComputercategoryFullAjax( ){
		log.info(logprefix + "deleteComputercategoryFullAjax");
		ReturnJson returnJson = new ReturnJson();
		
		try{
			String ids[] = computercategoryIdsForDel.split(";");
			for(int i=0; i < ids.length;i++){
				
				Integer typeId = Integer.valueOf(ids[i]);			
				log.info(typeId);
				//检查id
				/*
				if(tempDelId == null || tempDelId < 0){
					returnJson.setFlag(0);
					returnJson.setReason("删除的id不规范");
					log.info("删除的id不规范");
					JSONObject jo = JSONObject.fromObject(returnJson);
					this.returnStr = jo.toString();
					return SUCCESS;
				}	
				//del
				Computercategory temp = computercategoryService.selectComputercategoryById(typeId);			
				if (temp != null) {			
					//将相应的PC类型分类设置成-1
					computermodelService.updateCategoryComputermodel(typeId);
					computercategoryService.deleteComputercategory(typeId);
					
				} else {
					log.info("删除的id不存在");		
					returnJson.setFlag(0);
					returnJson.setReason("删除的id不存在");
					JSONObject jo = JSONObject.fromObject(returnJson);
					this.returnStr = jo.toString();
					return SUCCESS;
				}
				*/
				computermodelService.updateCategoryComputermodel(typeId);
				computercategoryService.deleteComputercategoryByType(typeId);
				
				
				
			}
			returnJson.setFlag(1);
			returnJson.setReason("删除成功!");
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
	public String updateComputercategoryAjax(){
		log.info(logprefix + "updateComputercategoryAjax,id=");
		
		try {
			
//			ch
				Computercategory tempComputercategory = computercategoryService.selectComputercategoryById(computercategoryIdCh);

  				tempComputercategory.setName(computercategoryNameCh);
				computercategoryService.updateComputercategory(tempComputercategory);			
				
//				en
				tempComputercategory = computercategoryService.selectComputercategoryById(computercategoryIdEn);
  				tempComputercategory.setName(computercategoryNameEn);
  				computercategoryService.updateComputercategory(tempComputercategory);	
  				
				returnJson.setFlag(1);		
				returnJson.setReason("修改成功!");
				JSONObject jo = JSONObject.fromObject(returnJson);				
				this.returnStr = jo.toString();
				//actionMsg = getText("viewComputercategorySuccess");
				return SUCCESS;
				
			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputercategoryAction的方法：viewComputercategory错误"+e);
		}

			returnJson.setFlag(0);		
			returnJson.setReason("修改失败");
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

	public String getComputercategoryIdsForDel() {
		return computercategoryIdsForDel;
	}

	public void setComputercategoryIdsForDel(String computercategoryIdsForDel) {
		this.computercategoryIdsForDel = computercategoryIdsForDel;
	}

	public ComputercategoryService getComputercategoryService() {
		return computercategoryService;
	}

	public void setComputercategoryService(
			ComputercategoryService computercategoryService) {
		this.computercategoryService = computercategoryService;
	}

	public ComputermodelService getComputermodelService() {
		return computermodelService;
	}

	public void setComputermodelService(ComputermodelService computermodelService) {
		this.computermodelService = computermodelService;
	}

	public String getActionMsg() {
		return actionMsg;
	}

	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}

	public String getLogprefix() {
		return logprefix;
	}

	public void setLogprefix(String logprefix) {
		this.logprefix = logprefix;
	}

	public ReturnJson getReturnJson() {
		return returnJson;
	}

	public void setReturnJson(ReturnJson returnJson) {
		this.returnJson = returnJson;
	}

	public String getInputAddCategoryNameEn() {
		return inputAddCategoryNameEn;
	}

	public void setInputAddCategoryNameEn(String inputAddCategoryNameEn) {
		this.inputAddCategoryNameEn = inputAddCategoryNameEn;
	}

	public String getInputAddCategoryNameCh() {
		return inputAddCategoryNameCh;
	}

	public void setInputAddCategoryNameCh(String inputAddCategoryNameCh) {
		this.inputAddCategoryNameCh = inputAddCategoryNameCh;
	}

	public static Log getLog() {
		return log;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setComputercategoryid(Integer computercategoryid) {
		this.computercategoryid = computercategoryid;
	}

	public int getComputercategoryIdCh() {
		return computercategoryIdCh;
	}

	public void setComputercategoryIdCh(int computercategoryIdCh) {
		this.computercategoryIdCh = computercategoryIdCh;
	}

	public String getComputercategoryNameCh() {
		return computercategoryNameCh;
	}

	public void setComputercategoryNameCh(String computercategoryNameCh) {
		this.computercategoryNameCh = computercategoryNameCh;
	}

	public int getComputercategoryIdEn() {
		return computercategoryIdEn;
	}

	public void setComputercategoryIdEn(int computercategoryIdEn) {
		this.computercategoryIdEn = computercategoryIdEn;
	}

	public String getComputercategoryNameEn() {
		return computercategoryNameEn;
	}

	public void setComputercategoryNameEn(String computercategoryNameEn) {
		this.computercategoryNameEn = computercategoryNameEn;
	}
	
	
}
