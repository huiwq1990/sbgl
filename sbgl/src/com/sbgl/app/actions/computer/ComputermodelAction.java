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
import com.sbgl.app.services.computer.ComputerService;
import com.sbgl.app.services.computer.ComputercategoryService;
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
	@Resource
	private ComputercategoryService computercategoryService;
	@Resource
	private ComputerService computerService;
	
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
	ReturnJson returnJson = new ReturnJson();
	
	//英文
	private String  computermodelNameEn ;
	private String  computermodelDescriptionEn;
	private int  computermodelIdEn;
	
	
	private String computermodelIdsForDel;

	/**
	 * 		
	 * @return
	 */
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
	
	
	//check model
	private boolean checkComputermodel(){
//		System.out.println("sssssss" + computermodel.getName());
		if(computermodel.getName()==null || computermodel.getName().trim().equals("")){
			returnJson.setFlag(0);	
			returnJson.setReason("模型名称不能为空");
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();				
			return false;
		}
		String name = computermodel.getName().trim();
		
		if(computermodelService.isComputermodelNameExist(name)){
			returnJson.setFlag(0);	
			returnJson.setReason("模型名称重复");
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();				
			return false;
		}
		
		Computercategory c= computercategoryService.selectComputercategoryById(computermodel.getComputercategoryid());
		if(c==null || c.getId()==0){
			returnJson.setFlag(0);	
			returnJson.setReason("模型分类不正确");
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();				
			return false;
		}
		
		return true;
	}
//  ajax add	
	public String addComputermodelAjax(){	
		log.info("Add Entity Ajax Manner");
		
//		boolean pass = checkComputermodel();
//		if(!pass){
//			return SUCCESS;
//		}
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		String uidStr = ComputerUtil.getCookieValue(cookies, ComputerConfig.cookieuserid);
		try {
			computermodel.setCreatetime(DateUtil.currentDate());
			
			Computermodel modelCh = new Computermodel();
			Computermodel modelEn = new Computermodel();

			BeanUtils.copyProperties(modelCh, computermodel);	
			modelCh.setLanguagetype("0");
			modelCh.setCreateuserid(Integer.valueOf(uidStr));
			modelCh.setStatus(0);
			
			BeanUtils.copyProperties(modelEn, computermodel);
			//英文的属性需要单独赋值
			modelEn.setLanguagetype("1");
			modelEn.setName(computermodelNameEn);
//			System.out.println(computermodelDescriptionEn);
			modelEn.setDescription(computermodelDescriptionEn);
			modelEn.setCreateuserid(Integer.valueOf(uidStr));
			modelEn.setStatus(0);
//			computermodelDescriptionEn.l;
			computermodelService.addComputermodel(modelCh,modelEn);
			
			returnJson.setFlag(1);	
			returnJson.setReason("添加机器模型成功");
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
		returnJson.setReason("数据库错误");
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}


	//del entityfull Ajax
	public String deleteComputermodelFullAjax( ){
		log.info(logprefix + "deleteComputercategoryFullAjax" + computermodelIdsForDel);
		ReturnJson returnJson = new ReturnJson();
		//检查要删除的id是否为空
		if(computermodelIdsForDel == null || computermodelIdsForDel.trim().length()==0){
			returnJson.setFlag(0);
			returnJson.setReason("删除型号的id为空");
			log.info("删除型号的id为空");
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
		}
		
		try{
			String ids[] = computermodelIdsForDel.split(";");
			for(int i=0; i < ids.length;i++){				
				Integer tempDelId = Integer.valueOf(ids[i]);			
				log.info(tempDelId);
				//检查id
//				if(tempDelId == null || tempDelId < 0){
//					returnJson.setFlag(0);
//					returnJson.setReason("删除的id不规范");
//					log.info("删除的id不规范");
//					JSONObject jo = JSONObject.fromObject(returnJson);
//					this.returnStr = jo.toString();
//					return SUCCESS;
//				}	
				//del
//				Computermodel temp = computermodelService.selectComputermodelById(tempDelId);			
//				if (temp != null) {		
//					computerService.updateComputermodelTo(tempDelId, -1);//删除
//					computermodelService.deleteComputermodel(tempDelId);
//					
//					
//				} else {
//					log.info("删除的id不存在");		
//					returnJson.setFlag(0);
//					returnJson.setReason("删除的id不存在");
//					JSONObject jo = JSONObject.fromObject(returnJson);
//					this.returnStr = jo.toString();
//					return SUCCESS;
//				}
				
				computerService.updateComputermodelTo(tempDelId, ComputerConfig.computercategorynotclassifyid);//删除
				computermodelService.deleteComputermodelByTyp(tempDelId);
			}
			returnJson.setFlag(1);
			returnJson.setReason("删除型号成功");
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		returnJson.setFlag(0);
		returnJson.setReason("删除发生内部错误");
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}



	//check model
	private boolean checkUpdateComputermodel(){
//		System.out.println("sssssss" + computermodel.getName());
		if(computermodel.getId()==null || computermodel.getId()<0){
			returnJson.setFlag(0);	
			returnJson.setReason("模型编号错误");
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();				
			return false;
		}
		if(computermodel.getName()==null || computermodel.getName().trim().equals("")){
			returnJson.setFlag(0);	
			returnJson.setReason("模型名称不能为空");
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();				
			return false;
		}
		
		String name = computermodel.getName().trim();		
		List<Computermodel> sameNameComputermodelList = computermodelService.selectComputermodelByName(name);
//		System.out.println(l.size());
		List<Integer> sameNameIdList = new ArrayList<Integer>();
		
		if(sameNameComputermodelList!=null && sameNameComputermodelList.size()>0){
			for(int i = 0; i < sameNameComputermodelList.size(); i++){
				if(sameNameComputermodelList.get(i).getId()!= computermodel.getId())
					sameNameIdList.add(sameNameComputermodelList.get(i).getId());
			}
			if(sameNameIdList.size()>0){
				returnJson.setFlag(0);	
				returnJson.setReason("模型名称重复");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();				
				return false;
			}
			
		}
		
		Computercategory c= computercategoryService.selectComputercategoryById(computermodel.getComputercategoryid());
		if(c==null || c.getId()==0){
			returnJson.setFlag(0);	
			returnJson.setReason("模型分类不正确");
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();				
			return false;
		}
		
		return true;
	}
	
	//ajax 修改
	public String updateComputermodelAjax(){
		log.info(logprefix + "updateComputermodelAjax,id="+computermodel.getId());
//		boolean pass = checkUpdateComputermodel();
//		if(!pass){
//			return SUCCESS;
//		}
		try {
					
				Computermodel tempCh = computermodelService.selectComputermodelById(computermodel.getId());
				Computermodel tempEn = computermodelService.selectComputermodelById(computermodelIdEn);
				
//              选择能更改的属性，与界面一致	
  				tempCh.setName(computermodel.getName());
  				tempCh.setDescription(computermodel.getDescription());
  				tempCh.setComputercategoryid(computermodel.getComputercategoryid());
  				tempCh.setPicpath(computermodel.getPicpath());

//  			修改En  				
  				tempEn.setName(computermodelNameEn);
  				tempEn.setDescription(computermodelDescriptionEn);
//  				System.out.println(computermodelDescriptionEn);
  				tempEn.setComputercategoryid(computermodel.getComputercategoryid());
  				tempEn.setPicpath(computermodel.getPicpath());
  				
				computermodelService.updateComputermodel(tempCh);	
				computermodelService.updateComputermodel(tempEn);		
				returnJson.setFlag(1);		
				returnJson.setReason("修改型号成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewComputermodelSuccess");
				return SUCCESS;
				
		
			
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


	public String getComputermodelIdsForDel() {
		return computermodelIdsForDel;
	}


	public void setComputermodelIdsForDel(String computermodelIdsForDel) {
		this.computermodelIdsForDel = computermodelIdsForDel;
	}


	public ComputermodelService getComputermodelService() {
		return computermodelService;
	}


	public void setComputermodelService(ComputermodelService computermodelService) {
		this.computermodelService = computermodelService;
	}


	public ComputercategoryService getComputercategoryService() {
		return computercategoryService;
	}


	public void setComputercategoryService(
			ComputercategoryService computercategoryService) {
		this.computercategoryService = computercategoryService;
	}


	public ComputerService getComputerService() {
		return computerService;
	}


	public void setComputerService(ComputerService computerService) {
		this.computerService = computerService;
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


	public String getComputermodelNameEn() {
		return computermodelNameEn;
	}


	public void setComputermodelNameEn(String computermodelNameEn) {
		this.computermodelNameEn = computermodelNameEn;
	}


	public String getComputermodelDescriptionEn() {
		return computermodelDescriptionEn;
	}


	public void setComputermodelDescriptionEn(String computermodelDescriptionEn) {
		this.computermodelDescriptionEn = computermodelDescriptionEn;
	}


	public static Log getLog() {
		return log;
	}


	public Map<String, Object> getSession() {
		return session;
	}


	public void setComputermodelid(Integer computermodelid) {
		this.computermodelid = computermodelid;
	}


	public int getComputermodelIdEn() {
		return computermodelIdEn;
	}


	public void setComputermodelIdEn(int computermodelIdEn) {
		this.computermodelIdEn = computermodelIdEn;
	}


	
	
	
}
