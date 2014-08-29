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
import com.sbgl.app.services.computer.ComputercategoryService;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputermodelAction")
public class ComputermodelAction extends BaseAction implements ModelDriven<Computermodel>{
	
	private static final Log log = LogFactory.getLog(ComputermodelAction.class);

	//Service	
	@Resource
	private ComputermodelService computermodelService;
	@Resource
	private ComputercategoryService computercategoryService;

	
	private Computermodel computermodel = new Computermodel();//实例化一个模型
	private Computermodel computermodelModel = new Computermodel();//实例化一个模型
	private ComputermodelFull computermodelFull = new ComputermodelFull();//实例化一个模型

	List<Computermodel> computermodelList = new ArrayList<Computermodel>();
	List<ComputermodelFull> computermodelFullList = new ArrayList<ComputermodelFull>();
	private Integer computermodelid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";		

	
	@Resource
	private ComputerService computerService;
	private Integer computerid; //entity full 的id属性名称		
	private Computer computer = new Computer();//实例化一个模型
	private Computer computerModel = new Computer();//实例化一个模型
	private ComputerFull computerFull = new ComputerFull();//实例化一个模型	
	private List<Computer> computerList = new ArrayList<Computer>();
	private List<ComputerFull> computerFullList = new ArrayList<ComputerFull>();
	
	
	
	
	//英文
	private String  computermodelNameEn ;
	private String  computermodelDescriptionEn;
	private int  computermodelIdEn;
	
	
	private String computermodelIdsForDel;



	
	
	
	
	//check model
	private boolean checkComputermodel(){
//		System.out.println("sssssss" + computermodel.getName());
		if(computermodel.getName()==null || computermodel.getName().trim().equals("")){
			this.returnInfo = "模型名称不能为空";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);

			return false;
		}
		if(computermodelNameEn==null || computermodelNameEn.trim().equals("")){
			this.returnInfo = "模型英文名称不能为空";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);

			return false;
		}
		
		
		if(computermodel.getComputercategoryid()<=0){
			this.returnInfo = "请选择模型分类";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);

			return false;
		}
		
		
//		String name = computermodel.getName().trim();		
//		if(computermodelService.isComputermodelNameExist(name)){
//
//			this.returnInfo = "模型名称重复";
//			log.info(returnInfo);
//			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
//
//			
//			return false;
//		}
		
//		Computercategory c= computercategoryService.selectComputercategoryById(computermodel.getComputercategoryid());
//		if(c==null || c.getId()==0){
//			
//			this.returnInfo = "模型分类不正确";
//			log.info(returnInfo);
//			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
//			
//			return false;
//		}
		
		return true;
	}

	//  ajax add	
	public String addComputermodelAjax(){	
		log.info("Add Entity Ajax Manner");
		
		boolean pass = checkComputermodel();
		if(!pass){
			return SUCCESS;
		}

		
		
		Integer uid = this.getCurrentUserId();
		if(uid < 0){
			this.returnInfo = "用户未登录";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return SUCCESS;
		}
		
		try {
//			设置初始值
			computermodel.setCreatetime(DateUtil.currentDate());
			computermodel.setAvailableborrowcountnumber(0);
			computermodel.setComputercount(0);
			computermodel.setCreateuserid(uid);
			if(computermodel.getPicpath()==null | computermodel.getPicpath().trim().equals(""))
				computermodel.setPicpath("default.jpg");
//			System.out.println(computermodel.getHourrentprice());
			Computermodel modelCh = new Computermodel();
			Computermodel modelEn = new Computermodel();

			
			BeanUtils.copyProperties(modelCh, computermodel);	
			modelCh.setLanguagetype(ComputerConfig.languagechStr);
			modelCh.setCreateuserid(uid);
//			modelCh.setHourrentprice()
			modelCh.setStatus(0);
			
			BeanUtils.copyProperties(modelEn, computermodel);
			//英文的属性需要单独赋值
			modelEn.setLanguagetype(ComputerConfig.languageenStr);
			modelEn.setName(computermodelNameEn);
//			System.out.println(computermodelDescriptionEn);
			modelEn.setDescription(computermodelDescriptionEn);
			modelEn.setCreateuserid(uid);
			modelEn.setStatus(0);
//			computermodelDescriptionEn.l;
			computermodelService.addComputermodel(modelCh,modelEn);
			
			
			this.returnInfo = "添加机房模型成功";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
			return SUCCESS;
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputermodelAction的方法：addBbstagfavourite错误"+e);
		}
		
		this.returnInfo = "系统内部错误，添加失败";
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		return SUCCESS;
		
	}


	//del entityfull Ajax
	public String deleteComputermodelFullAjax( ){
		log.info(logprefix + "deleteComputercategoryFullAjax" + computermodelIdsForDel);

		
		try{
			
			
			//检查要删除的id是否为空
			if(computermodelIdsForDel == null || computermodelIdsForDel.trim().length()==0){
				returnInfo = "没有选择删除的选项";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
				return SUCCESS;
			}

			
			
			String typeStrArray[] = computermodelIdsForDel.split(";");			
			List<Integer> delTypeList = new ArrayList<Integer>();
			
//			判断参数是否正确
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
				computermodelList = computermodelService.selectComputermodelByCondition(" where computermodeltype = "+type + " and languagetype = "+CommonConfig.languagech);	
//				删除的id不存在
				if(computermodelList==null || computermodelList.size() == 0){
					returnInfo = "删除ID为"+type+"的模型不存在";
					log.info(returnInfo);
					this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
					return SUCCESS;
				}

				computerList = computerService.selectComputerByCondition(" where computermodelid= "+type+ " and languagetype = "+CommonConfig.languagech);	

//				当模型下面的设备为空时，才可以删除
				if(computerList==null || computerList.size() ==0){
					
				}else{
					String str = "";
					for(Computer t : computerList){
						str += t.getSerialnumber()+";";
					}
					str = str.substring(0,str.length()-1);
					returnInfo = "模型"+computermodelList.get(0).getName()+"包含了设备："+str;
					log.info(returnInfo);
					this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
					return SUCCESS;
				}				
			}
			
//			通过验证，删除数据
			computermodelService.deleteComputermodelByType(delTypeList);
			
			this.returnInfo = "删除型号成功";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
			return SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.returnInfo = "系统发生内部错误，删除失败";
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		return SUCCESS;
	}



	//check model
	private boolean checkUpdateComputermodel(){
//		System.out.println("sssssss" + computermodel.getName());
		if(computermodel.getId()==null || computermodel.getId()<0){

			this.returnInfo = "模型编号错误";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
					
			return false;
		}
		if(computermodel.getName()==null || computermodel.getName().trim().equals("")){

			this.returnInfo = "模型名称不能为空";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		
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
				this.returnInfo = "模型名称重复";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);		
				return false;
			}
			
		}
		
		Computercategory c= computercategoryService.selectComputercategoryById(computermodel.getComputercategoryid());
		if(c==null || c.getId()==0){
			this.returnInfo = "模型分类不正确";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);

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
		Integer uid = this.getCurrentUserId();
		if(uid < 0){
			this.returnInfo = "用户未登录";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return SUCCESS;
		}
		
		try {
				
			computermodel.setCreateuserid(uid);
			
				Computermodel tempCh = computermodelService.selectComputermodelById(computermodel.getId());
				Computermodel tempEn = computermodelService.selectComputermodelById(computermodelIdEn);
				
//              选择能更改的属性，与界面一致	
  				tempCh.setName(computermodel.getName());
  				tempCh.setDescription(computermodel.getDescription());
  				tempCh.setComputercategoryid(computermodel.getComputercategoryid());
  				tempCh.setPicpath(computermodel.getPicpath());
  				tempCh.setHourrentprice(computermodel.getHourrentprice());

//  			修改En  				
  				tempEn.setName(computermodelNameEn);
  				tempEn.setDescription(computermodelDescriptionEn);
//  				System.out.println(computermodelDescriptionEn);
  				tempEn.setComputercategoryid(computermodel.getComputercategoryid());
  				tempEn.setPicpath(computermodel.getPicpath());
  				tempEn.setHourrentprice(computermodel.getHourrentprice());
				computermodelService.updateComputermodel(tempCh,tempEn);			

				this.returnInfo = "修改型号成功";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
				return SUCCESS;
				
		
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputermodelAction的方法：viewComputermodel错误"+e);
		}

		this.returnInfo = "系统内部错误，修改失败";
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		return SUCCESS;
	}
	
	

	
	@Override
	public Computermodel getModel() {
		// TODO Auto-generated method stub
		return computermodel;
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

	public Computermodel getComputermodel() {
		return computermodel;
	}

	public void setComputermodel(Computermodel computermodel) {
		this.computermodel = computermodel;
	}

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

	public void setComputermodelFullList(
			List<ComputermodelFull> computermodelFullList) {
		this.computermodelFullList = computermodelFullList;
	}

	public Integer getComputermodelid() {
		return computermodelid;
	}

	public void setComputermodelid(Integer computermodelid) {
		this.computermodelid = computermodelid;
	}

	public String getLogprefix() {
		return logprefix;
	}

	public void setLogprefix(String logprefix) {
		this.logprefix = logprefix;
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

	public int getComputermodelIdEn() {
		return computermodelIdEn;
	}

	public void setComputermodelIdEn(int computermodelIdEn) {
		this.computermodelIdEn = computermodelIdEn;
	}

	public String getComputermodelIdsForDel() {
		return computermodelIdsForDel;
	}

	public void setComputermodelIdsForDel(String computermodelIdsForDel) {
		this.computermodelIdsForDel = computermodelIdsForDel;
	}

	public static Log getLog() {
		return log;
	}

	
	
	
	
	
	
}
