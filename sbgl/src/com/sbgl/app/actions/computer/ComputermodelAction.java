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
import com.sbgl.app.actions.common.BaseAction;
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


	
	public int getCurrentLanguage(){
		return ComputerActionUtil.getLanguagetype((String) session.get(ComputerConfig.sessionLanguagetype));		
	}
	
	public void buildReturnStr(int flag,String errorStr){
		ReturnJson returnJson = new ReturnJson();
		returnJson.setFlag(flag);			
		returnJson.setReason(errorStr);
		
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
//		return SUCCESS;
	}
	
	
	
	
	//check model
	private boolean checkComputermodel(){
//		System.out.println("sssssss" + computermodel.getName());
		if(computermodel.getName()==null || computermodel.getName().trim().equals("")){
			this.returnInfo = "模型名称不能为空";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);

			return false;
		}
		String name = computermodel.getName().trim();
		
		if(computermodelService.isComputermodelNameExist(name)){

			this.returnInfo = "模型名称重复";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);

			
			return false;
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

	//  ajax add	
	public String addComputermodelAjax(){	
		log.info("Add Entity Ajax Manner");
		
//		boolean pass = checkComputermodel();
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
//			设置初始值
			computermodel.setCreatetime(DateUtil.currentDate());
			computermodel.setAvailableborrowcountnumber(0);
			computermodel.setComputercount(0);
			computermodel.setCreateuserid(uid);

			
			Computermodel modelCh = new Computermodel();
			Computermodel modelEn = new Computermodel();

			
			BeanUtils.copyProperties(modelCh, computermodel);	
			modelCh.setLanguagetype(ComputerConfig.languagechStr);
			modelCh.setCreateuserid(uid);
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
		ReturnJson returnJson = new ReturnJson();
		//检查要删除的id是否为空
		if(computermodelIdsForDel == null || computermodelIdsForDel.trim().length()==0){
			returnInfo = "删除型号的id为空";
			buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
			return SUCCESS;
		}
		
		try{
//			删除的是Model的类型，不是id
			String modeltypes[] = computermodelIdsForDel.split(";");
			for(int i=0; i < modeltypes.length;i++){
				String modeltypeStr = modeltypes[i];
//				Integer tempDelId = Integer.valueOf(ids[i]);			
//				log.info(tempDelId);
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
				
//				computerService.updateComputermodelTo(tempDelId, ComputerConfig.computercategorynotclassifyid);//删除
				
				computermodelList = computermodelService.selectComputermodelByCondition(" where computermodeltype = "+modeltypeStr + " and languagetype = "+ComputerConfig.languagech);	
//				删除的id不存在
				if(computermodelList==null || computermodelList.size() == 0){
					returnInfo = "删除ID为"+modeltypeStr+"的模型不存在";
					buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
					return SUCCESS;
				}
				
				
				computerList = computerService.selectComputerByCondition(" where computermodelid= "+modeltypeStr+ " and languagetype = "+ComputerConfig.languagech);	
//				当模型下面的设备为空时，才可以删除
				if(computerList==null || computerList.size() ==0){
					computermodelService.deleteComputermodelByTyp(Integer.valueOf(modeltypeStr));
				}else{
					String str = "";
					for(Computer t : computerList){
						str += t.getSerialnumber()+";";
					}
					str = str.substring(0,str.length()-1);
					returnInfo = "模型"+computermodelList.get(0).getName()+"包含了设备："+str;
					buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
					return SUCCESS;
				}
				
				
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

//  			修改En  				
  				tempEn.setName(computermodelNameEn);
  				tempEn.setDescription(computermodelDescriptionEn);
//  				System.out.println(computermodelDescriptionEn);
  				tempEn.setComputercategoryid(computermodel.getComputercategoryid());
  				tempEn.setPicpath(computermodel.getPicpath());
  				
				computermodelService.updateComputermodel(tempCh);	
				computermodelService.updateComputermodel(tempEn);		

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

	
	
	
	
}
