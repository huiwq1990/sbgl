package com.sbgl.app.actions.computer;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

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
import com.sbgl.app.services.computer.ComputerService;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.app.services.computer.ComputerstatusService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputerAction")
public class ComputerAction extends BaseAction implements ModelDriven<Computer>{
	
	private static final Log log = LogFactory.getLog(ComputerAction.class);

	
	//Service	
	@Resource
	private ComputerService computerService;
	private Integer computerid; //entity full 的id属性名称		
	private Computer computer = new Computer();//实例化一个模型
	private Computer computerModel = new Computer();//实例化一个模型
	private ComputerFull computerFull = new ComputerFull();//实例化一个模型	
	private List<Computer> computerList = new ArrayList<Computer>();
	private List<ComputerFull> computerFullList = new ArrayList<ComputerFull>();
	
	
	
//	添加pc时需要修改model
	@Resource
	private ComputermodelService computermodelService;
	int computermodeltype = 0;
	List<Computermodel> computermodelList = new ArrayList<Computermodel>();
	List<ComputermodelFull> computermodelFullList = new ArrayList<ComputermodelFull>();
	List<ComputermodelFull> computermodelFullListCh = new ArrayList<ComputermodelFull>();
	List<ComputermodelFull> computermodelFullListEn = new ArrayList<ComputermodelFull>();
	
	
	
	@Resource
	private ComputerstatusService computerstatusService;
	int computerstatusid = 0;
	List<Computerstatus> computerstatusList = new ArrayList<Computerstatus>();
	List<ComputerstatusFull> computerstatusFullList = new ArrayList<ComputerstatusFull>();
	
	
	
	private String logprefix = "exec action method:";	
	
	
	
	private int  computerIdEn;
	private String  computerSerialnumberEn;
	private String  computerRemarkEn;

	
	//删除
	String computerIdsForDel;
	
	
	public boolean checkAddForm(){
		if(computer.getSerialnumber()==null || computer.getSerialnumber().trim().length() == 0){
			this.returnInfo = "请输入设备/资源编号。";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return false;
		}
		
		if(computer.getComputermodelid()==null || computer.getComputermodelid()== 0){
			this.returnInfo = "请选择设备/资源对应的型号。";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return false;
		}
		
		if(computer.getComputerstatusid()==null || computer.getComputerstatusid()== 0){
			this.returnInfo = "请选择设备/资源对应的状态。";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return false;
		}
		
		
		return true;
		
	}

//  ajax add	
	public String addComputerAjax(){	
		log.info("Add Entity Ajax Manner");
		try {
		
		Integer uid = this.getCurrentUserId();
		if(uid < 0){
			this.returnInfo = "用户未登录";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return SUCCESS;
		}
		
		
		if(!checkAddForm()){
			return SUCCESS;
		}

			
			computer.setCreatetime(DateUtil.currentDate());
			computer.setCreateuserid(uid);
			
			Computer ch = new Computer();
			Computer en = new Computer();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(ch, computer);			
			BeanUtils.copyProperties(en, computer);		
			
			ch.setLanguagetype(ComputerConfig.languagechStr);
	
			en.setLanguagetype(ComputerConfig.languageenStr);
			en.setSerialnumber(computerSerialnumberEn);
			en.setRemark(computerRemarkEn);
			
			
		
			
//			修改数量
			List<Computerstatus> computerstatusList = computerstatusService.selectComputerstatusByCondition( " where id = " + ch.getComputerstatusid() );
			if(computerstatusList==null || computerstatusList.size() ==0){
				this.returnInfo = "获取可借状态失败";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
				return SUCCESS;
			}
			int availBorrow = computerstatusList.get(0).getAvailableborrow();
			
			List<Computermodel> cmList = computermodelService.selectComputermodelByCondition(" where computermodeltype = " + ch.getComputermodelid() );			
			if(cmList==null || cmList.size() == 0){
				this.returnInfo = "无法获取机房模型";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
				return SUCCESS;
			}
			Computermodel cm = cmList.get(0);
			
//			如果模型的数量为null,设置为0
			if(cm.getComputercount()== null){
				cm.setComputercount(0);
			}
			if(cm.getAvailableborrowcountnumber()==null){
				cm.setAvailableborrowcountnumber(0);
			}
						
			computerService.addComputerAndSetNum(ch, en,availBorrow , cm.getComputercount(), cm.getAvailableborrowcountnumber());
			
			this.returnInfo = "添加机器成功";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerAction的方法：addBbstagfavourite错误"+e);
		}

		this.returnInfo = "系统内部错误，添加机器失败";
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		return SUCCESS;
	}


	//del entityfull Ajax
	public String deleteComputerFullAjax( ){
		log.info(logprefix + "deleteComputercategoryFullAjax");
		try{
			
			Integer uid = this.getCurrentUserId();
			if(uid < 0){
				this.returnInfo = "用户未登录";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
				return SUCCESS;
			}
			
		
//		检查要删除的id是否为空
		if(computerIdsForDel == null || computerIdsForDel.trim().length()==0){
			returnInfo = "删除型号的id为空";
			log.error(returnInfo);
			returnStr = ComputerActionUtil.buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
			return SUCCESS;
		}
		
		String typeStrArray[] = computerIdsForDel.split(";");		
		List<Integer> delTypeList = new ArrayList<Integer>();
		
//		判断参数是否正确
		for(String typeStr : typeStrArray){
			if(!NumberUtils.isNumber(typeStr)){
				this.returnInfo = "删除参数不正确";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
				return SUCCESS;
			}
			delTypeList.add(Integer.valueOf(typeStr));
		}
		
//		判断机器是否存在
		for(Integer type : delTypeList){
			computerList = computerService.selectComputerByCondition(" where computertype="+type+" and languagetype = "+ComputerConfig.languagech);	
			if(computerList== null || computerList.size() == 0){
				returnInfo = "删除id为"+type+"的机器不存在";
				log.error(returnInfo);
				returnStr = ComputerActionUtil.buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
				return SUCCESS;
			}	
			
		}
		
		
		computerService.deleteComputerByType(delTypeList);

			
		returnInfo = "删除成功!";
		returnStr = ComputerActionUtil.buildReturnStr(ComputerConfig.ajaxsuccessreturn,returnInfo);
		return SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		returnInfo = "删除的内部错误！";
		log.error(returnInfo);
		returnStr = ComputerActionUtil.buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
		return SUCCESS;
		
	}


	/*
	 * 修改信息检查
	 */
	public boolean passCheckUpdate(){
		
//		String re = 
		if(computer==null || computer.getId()==0 || computer.getSerialnumber()==null || computer.getSerialnumber().trim().equals("") ||
				computer.getComputermodelid()== null || computer.getComputermodelid()==0 ||
				computer.getComputerstatusid()== null || computer.getComputerstatusid() ==0){
			this.returnInfo = "提交的机器信息不完整";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);	
			return false;
		}
		
		return true;
		
	
	}
	
	//ajax 修改
	public String updateComputerAjax(){
		log.info(logprefix + "updateComputerAjax,Computertype="+computer.getComputertype());
		
		try {
		
			
		if(!passCheckUpdate()){			
			return SUCCESS;
		}

//			ch
			Computer tempch = computerService.selectComputerByTypeAndLanguage(computer.getComputertype(),CommonConfig.languagech);
			
			if(tempch == null){
				this.returnInfo = "获取机器信息失败";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);	
				return SUCCESS; 
			}
			
//			int 
			int originalComputerStatus = tempch.getComputerstatusid();
			int orignialComputerModelType = tempch.getComputermodelid();
			tempch.setSerialnumber(computer.getSerialnumber());
			tempch.setComputermodelid(computer.getComputermodelid());			
			tempch.setComputerstatusid(computer.getComputerstatusid());
			tempch.setRemark(computer.getRemark());
			
		
			Computer tempen = new Computer();
//			en
//			Computer tempen = computerService.selectComputerById(computerIdEn);
//			tempen.setSerialnumber(computerSerialnumberEn);
//			tempen.setComputermodelid(computer.getComputermodelid());			
//			tempen.setComputerstatusid(computer.getComputerstatusid());
//			tempen.setRemark(computerRemarkEn);
			
			
			
//			修改可借数量
			List<Computerstatus> computerstatusList = computerstatusService.selectComputerstatusByCondition( " where id = " + tempch.getComputerstatusid() );
			List<Computerstatus> orgComputerstatusList = computerstatusService.selectComputerstatusByCondition( " where id = " + originalComputerStatus );
			
			
			if(computerstatusList==null || computerstatusList.size() ==0 || orgComputerstatusList==null || orgComputerstatusList.size() ==0){
				this.returnInfo = "获取机房状态失败";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
				return SUCCESS;
			}
			
//			获取状态下机房是否可借
			int nowAviBow = computerstatusList.get(0).getAvailableborrow();
			int orgAviBow = orgComputerstatusList.get(0).getAvailableborrow();
			
			computerService.updateComputer(tempch, tempen,orignialComputerModelType, nowAviBow, orgAviBow);
						
			this.returnInfo = "修改成功";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
			return SUCCESS;
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerAction的方法：viewComputer错误"+e);
		}


		this.returnInfo = "系统内部错误，修改失败";
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		return SUCCESS;
	}
	
	
	
	
	@Override
	public Computer getModel() {
		// TODO Auto-generated method stub
		return computer;
	}


	public ComputerService getComputerService() {
		return computerService;
	}


	public void setComputerService(ComputerService computerService) {
		this.computerService = computerService;
	}


	public Integer getComputerid() {
		return computerid;
	}


	public void setComputerid(Integer computerid) {
		this.computerid = computerid;
	}


	public Computer getComputer() {
		return computer;
	}


	public void setComputer(Computer computer) {
		this.computer = computer;
	}


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


	public ComputermodelService getComputermodelService() {
		return computermodelService;
	}


	public void setComputermodelService(ComputermodelService computermodelService) {
		this.computermodelService = computermodelService;
	}


	public int getComputermodeltype() {
		return computermodeltype;
	}


	public void setComputermodeltype(int computermodeltype) {
		this.computermodeltype = computermodeltype;
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


	public void setComputermodelFullList(
			List<ComputermodelFull> computermodelFullList) {
		this.computermodelFullList = computermodelFullList;
	}


	public List<ComputermodelFull> getComputermodelFullListCh() {
		return computermodelFullListCh;
	}


	public void setComputermodelFullListCh(
			List<ComputermodelFull> computermodelFullListCh) {
		this.computermodelFullListCh = computermodelFullListCh;
	}


	public List<ComputermodelFull> getComputermodelFullListEn() {
		return computermodelFullListEn;
	}


	public void setComputermodelFullListEn(
			List<ComputermodelFull> computermodelFullListEn) {
		this.computermodelFullListEn = computermodelFullListEn;
	}


	public ComputerstatusService getComputerstatusService() {
		return computerstatusService;
	}


	public void setComputerstatusService(ComputerstatusService computerstatusService) {
		this.computerstatusService = computerstatusService;
	}


	public int getComputerstatusid() {
		return computerstatusid;
	}


	public void setComputerstatusid(int computerstatusid) {
		this.computerstatusid = computerstatusid;
	}


	public List<Computerstatus> getComputerstatusList() {
		return computerstatusList;
	}


	public void setComputerstatusList(List<Computerstatus> computerstatusList) {
		this.computerstatusList = computerstatusList;
	}


	public List<ComputerstatusFull> getComputerstatusFullList() {
		return computerstatusFullList;
	}


	public void setComputerstatusFullList(
			List<ComputerstatusFull> computerstatusFullList) {
		this.computerstatusFullList = computerstatusFullList;
	}


	public String getLogprefix() {
		return logprefix;
	}


	public void setLogprefix(String logprefix) {
		this.logprefix = logprefix;
	}


	public int getComputerIdEn() {
		return computerIdEn;
	}


	public void setComputerIdEn(int computerIdEn) {
		this.computerIdEn = computerIdEn;
	}


	public String getComputerSerialnumberEn() {
		return computerSerialnumberEn;
	}


	public void setComputerSerialnumberEn(String computerSerialnumberEn) {
		this.computerSerialnumberEn = computerSerialnumberEn;
	}


	public String getComputerRemarkEn() {
		return computerRemarkEn;
	}


	public void setComputerRemarkEn(String computerRemarkEn) {
		this.computerRemarkEn = computerRemarkEn;
	}


	public String getComputerIdsForDel() {
		return computerIdsForDel;
	}


	public void setComputerIdsForDel(String computerIdsForDel) {
		this.computerIdsForDel = computerIdsForDel;
	}


	public static Log getLog() {
		return log;
	}

	
	
	
	
	
}
