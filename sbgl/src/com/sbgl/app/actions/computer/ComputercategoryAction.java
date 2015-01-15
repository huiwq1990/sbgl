package com.sbgl.app.actions.computer;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.commons.beanutils.BeanUtils;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.computer.ComputercategoryService;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputercategoryAction")
public class ComputercategoryAction extends BaseAction implements ModelDriven<Computercategory>{
	
	private static final Log log = LogFactory.getLog(ComputercategoryAction.class);

	
	//Service	
	@Resource
	private ComputercategoryService computercategoryService;
	
	@Resource
	private ComputermodelService computermodelService;
	List<Computermodel> computermodelList = new ArrayList<Computermodel>();
	
	private Computercategory computercategory = new Computercategory();//实例化一个模型
	private Computercategory computercategoryModel = new Computercategory();//实例化一个模型
	private ComputercategoryFull computercategoryFull = new ComputercategoryFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Computercategory> computercategoryList = new ArrayList<Computercategory>();
	List<ComputercategoryFull> computercategoryFullList = new ArrayList<ComputercategoryFull>();
	private Integer computercategoryid; //entity full 的id属性名称		
	
	
	
	private String logprefix = "exec action method:";		

	
	
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
	

	
//  ajax add	
	public String addComputercategoryAjax(){	
		log.info("Add Entity Ajax Manner");

		
		Integer uid = this.getCurrentUserId();
		if(uid < 0){
			this.returnInfo = "用户未登录";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return SUCCESS;
		}
		
		//名称不规范
		boolean pass = checkComputercategoryName();
		if(!pass){
			return SUCCESS;
		}
		
		try {
			Computercategory tempCh = new Computercategory();		
			BeanUtils.copyProperties(tempCh, computercategory);		
			tempCh.setName(inputAddCategoryNameCh.trim());
			tempCh.setLanguagetype(ComputerConfig.languagechStr);
			tempCh.setCreateuserid(uid);
			tempCh.setStatus(0);
			
			Computercategory tempEn = new Computercategory();			
			BeanUtils.copyProperties(tempEn, computercategory);		
			tempEn.setName(inputAddCategoryNameEn.trim());
			tempEn.setLanguagetype(ComputerConfig.languageenStr);			
			tempEn.setCreateuserid(uid);
			tempEn.setStatus(0);
			
			computercategoryService.addComputercategory(tempCh,tempEn);

			this.returnInfo = "添加分类成功";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputercategoryAction的方法：addBbstagfavourite错误"+e);
		}

		this.returnInfo = "内部错误，添加分类失败";
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		return SUCCESS;
	}
	
	private boolean checkComputercategoryName(){
		if(inputAddCategoryNameEn==null || inputAddCategoryNameEn.trim().equals("") || inputAddCategoryNameCh==null || inputAddCategoryNameCh.trim().equals("")){
			this.returnInfo = "分类名称不能为空";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);			
			return false;
		}
		
		
		
		if(computercategoryService.isComputercategoryNameExist(inputAddCategoryNameCh.trim())){
			
			this.returnInfo = "分类名称重复";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);	
			return false;
		}
		
		return true;
	}

	

	//del entityfull Ajax
	/**
	 * 删除分类，将相应分类下面的型号设置为-1
	 */
	public String deleteComputercategoryFullAjax( ){
		log.info(logprefix + "deleteComputercategoryFullAjax");
	
		try{
			
			//检查要删除的id是否为空
			if(computercategoryIdsForDel == null || computercategoryIdsForDel.trim().length()==0){
				returnInfo = "没有选择删除的选项";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
				return SUCCESS;
			}
			
		
			String typeStrArray[] = computercategoryIdsForDel.split(";");
			
			List<Integer> delTypeList = new ArrayList<Integer>();
			
			for(String typeStr : typeStrArray){
				if(!NumberUtils.isNumber(typeStr)){
					this.returnInfo = "删除参数不正确";
					log.info(returnInfo);
					this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
					return SUCCESS;
				}
				delTypeList.add(Integer.valueOf(typeStr));
			}
			
			
			for(Integer type : delTypeList){
				
				computercategoryList = computercategoryService.selectComputercategoryByCondition(" where languagetype="+CommonConfig.languagech+" and computercategorytype = "+type);
				if(computercategoryList == null || computercategoryList.size() == 0){
					this.returnInfo = "删除的机房分类"+type+"不存在";
					log.info(returnInfo);
					this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
					return SUCCESS;
				}
				
				
				String modelSql = " where computercategoryid = "+type + " and languagetype="+CommonConfig.languagech;
				computermodelList = computermodelService.selectComputermodelByCondition(modelSql);
//				判断允许删除
				if(computermodelList == null || computermodelList.size() == 0){
					//允许删除
				}else{
					
					String temp = "";
					for(Computermodel cm : computermodelList){
						temp += cm.getName() +",";
					}
					temp = temp.substring(0,temp.length()-1);
					
					this.returnInfo = "机房分类被模型"+temp+"使用";
					log.info(returnInfo);
					this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
					return SUCCESS;
				}
				
			}
			
			computercategoryService.deleteComputercategoryByType(delTypeList);
			
			this.returnInfo = "删除成功";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
			return SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.returnInfo = "发生系统内部错误，删除失败";
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		return SUCCESS;
	}


	//ajax 修改
	public String updateComputercategoryAjax(){
		log.info(logprefix + "updateComputercategoryAjax,id=");
		
		try {
			
//			ch
				Computercategory tempch = computercategoryService.selectComputercategoryById(computercategoryIdCh);
				tempch.setName(computercategoryNameCh);		
				
//				en
				Computercategory tempen = computercategoryService.selectComputercategoryById(computercategoryIdEn);
				tempen.setName(computercategoryNameEn);
  				computercategoryService.updateComputercategory(tempch,tempen);	
  				
				this.returnInfo = "修改成功!";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
				return SUCCESS;
				
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputercategoryAction的方法：viewComputercategory错误"+e);
		}

			
		this.returnInfo = "系统内部错误，修改失败";
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		return SUCCESS;
	}
	
	public String testJson(){
		
	System.out.println("ss");
		return SUCCESS;
	}
	
	public String selAllComputercategoryJson(){
		try{
			System.out.println(this.language);
			List<Computercategory> list = computercategoryService.sel(this.language);
	
			returnMap.put("flag",JsonActionUtil.ajaxsuccessreturn);
			returnMap.put("computercategoryList", list);

			return SUCCESS;
//			throw new Exception();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputercategoryAction的方法：selAllComputercategoryJson错误"+e);
		}
		
		returnMap.put("flag", JsonActionUtil.ajaxerrorreturn);
		returnMap.put("reason", "系统错误");
//		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn,"系统错误");
		return SUCCESS;
	}
	
	@Override
	public Computercategory getModel() {
		// TODO Auto-generated method stub
		return computercategory;
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

	public Computercategory getComputercategory() {
		return computercategory;
	}

	public void setComputercategory(Computercategory computercategory) {
		this.computercategory = computercategory;
	}

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

	public String getActionMsg() {
		return actionMsg;
	}

	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}

	public String getReturnStr() {
		return returnStr;
	}

	public void setReturnStr(String returnStr) {
		this.returnStr = returnStr;
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

	public void setComputercategoryFullList(
			List<ComputercategoryFull> computercategoryFullList) {
		this.computercategoryFullList = computercategoryFullList;
	}

	public Integer getComputercategoryid() {
		return computercategoryid;
	}

	public void setComputercategoryid(Integer computercategoryid) {
		this.computercategoryid = computercategoryid;
	}

	public String getLogprefix() {
		return logprefix;
	}

	public void setLogprefix(String logprefix) {
		this.logprefix = logprefix;
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

	public String getComputercategoryIdsForDel() {
		return computercategoryIdsForDel;
	}

	public void setComputercategoryIdsForDel(String computercategoryIdsForDel) {
		this.computercategoryIdsForDel = computercategoryIdsForDel;
	}

	public static Log getLog() {
		return log;
	}

	public List<Computermodel> getComputermodelList() {
		return computermodelList;
	}

	public void setComputermodelList(List<Computermodel> computermodelList) {
		this.computermodelList = computermodelList;
	}
	
	
	
	
	

}
