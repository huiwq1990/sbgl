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

	
	private Computermodel computermodel = new Computermodel();//实例化一个模型
	private Computermodel computermodelModel = new Computermodel();//实例化一个模型
	private ComputermodelFull computermodelFull = new ComputermodelFull();//实例化一个模型

	List<Computermodel> computermodelList = new ArrayList<Computermodel>();
	List<ComputermodelFull> computermodelFullList = new ArrayList<ComputermodelFull>();
	private Integer computermodelid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";		
	ReturnJson returnJson = new ReturnJson();
	
	
	@Resource
	private ComputerService computerService;
	private Integer computerid; //entity full 的id属性名称		
	private Computer computer = new Computer();//实例化一个模型
	private Computer computerModel = new Computer();//实例化一个模型
	private ComputerFull computerFull = new ComputerFull();//实例化一个模型	
	private List<Computer> computerList = new ArrayList<Computer>();
	private List<ComputerFull> computerFullList = new ArrayList<ComputerFull>();
	
	
	
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	private String returnInfo;
	private String actionMsg; // Action间传递的消息参数
	
	private Integer pageNo=1;	
	private Page page = new Page();

	
	
	
	//英文
	private String  computermodelNameEn ;
	private String  computermodelDescriptionEn;
	private int  computermodelIdEn;
	
	
	private String computermodelIdsForDel;

		

	public int checkUserLogin(){
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		String uidStr = ComputerActionUtil.getUserIdFromCookie(cookies);
		if(uidStr==null || uidStr.trim().equals("0") || uidStr.trim().equals("")){
			return -1;
		}
		return Integer.valueOf(uidStr);
	}
	
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

		Integer uid = checkUserLogin();
		System.out.println("sss"+ uid);
		if(uid < 0){
			returnJson.setFlag(0);
			returnJson.setReason("用户未登录");
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();			
			return SUCCESS;
		}
		
		try {
//			设置初值指
			computermodel.setCreatetime(DateUtil.currentDate());
			computermodel.setAvailableborrowcountnumber(0);
			computermodel.setComputercount(0);
			
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

	public String getReturnInfo() {
		return returnInfo;
	}

	public void setReturnInfo(String returnInfo) {
		this.returnInfo = returnInfo;
	}


	
	
	
}
