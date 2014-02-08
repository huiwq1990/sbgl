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
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.computer.ComputerService;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.app.services.computer.ComputerstatusService;
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
	private Integer computerid; //entity full 的id属性名称		
	
	
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
	Page page = new Page();
	Integer pageNo=1;	
	

	private int  computerIdEn;
	private String  computerSerialnumberEn;
	private String  computerRemarkEn;
	
	ReturnJson returnJson = new ReturnJson();
	//删除
	String computerIdsForDel;
	


			

//  ajax add	
	public String addComputerAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			
			computer.setCreatetime(DateUtil.currentDate());
			
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
			if(computerstatusList==null || computerstatusList.size() !=0){
				returnJson.setFlag(0);		
				returnJson.setReason("获取可借状态失败!");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
			}
			
			Computermodel cm = computermodelService.selectComputermodelByCondition(" where computermodeltype = " + ch.getComputermodelid() ).get(0);			
			if(cm.getComputercount()== null){
				cm.setComputercount(0);
			}
			computermodelService.execSql(" update Computermodel set computercount="+(cm.getComputercount()+1)+"  where computermodeltype = "+ch.getComputermodelid());

			if(computerstatusList.get(0).getAvailableborrow()== ComputerConfig.computeravailableborrowstatusid){
				if(cm.getAvailableborrowcountnumber() == 0){
					cm.setAvailableborrowcountnumber(0);
				}				
				computermodelService.execSql(" update Computermodel set availableborrowcountnumber="+(cm.getAvailableborrowcountnumber()+1)+"  where computermodeltype = "+ch.getComputermodelid());
			}
			
			
			computerService.addComputer(ch,en);
			
			returnJson.setFlag(1);		
			returnJson.setReason("添加机器成功!");
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
		returnJson.setReason("添加机器失败!");
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}


	//del entityfull Ajax
	public String deleteComputerFullAjax( ){
		log.info(logprefix + "deleteComputercategoryFullAjax");
		ReturnJson returnJson = new ReturnJson();
		
		try{
			String ids[] = computerIdsForDel.split(";");
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
//				computermodService.updateCategoryComputermodel(typeId);
				computerService.deleteComputerByType(typeId);
				
				
				
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
	public String updateComputerAjax(){
		log.info(logprefix + "updateComputerAjax,id="+computer.getId()+"  " + computerIdEn+"  end");

		try {
			
			System.out.println("computer.getSerialnumber()"+computer.getSerialnumber());
//			ch
			Computer tempComputer = computerService.selectComputerById(computer.getId());
			int originalComputerStatus = tempComputer.getComputerstatusid();
			tempComputer.setSerialnumber(computer.getSerialnumber());
			tempComputer.setComputermodelid(computer.getComputermodelid());			
			tempComputer.setComputerstatusid(computer.getComputerstatusid());
			tempComputer.setRemark(computer.getRemark());
		
			Computer ch = tempComputer;
			
			computerService.updateComputer(tempComputer);
//			en
			tempComputer = computerService.selectComputerById(computerIdEn);
			tempComputer.setSerialnumber(computerSerialnumberEn);
			tempComputer.setComputermodelid(computer.getComputermodelid());			
			tempComputer.setComputerstatusid(computer.getComputerstatusid());
			tempComputer.setRemark(computerRemarkEn);
			computerService.updateComputer(tempComputer);
			
//			修改可借数量
			List<Computerstatus> computerstatusList = computerstatusService.selectComputerstatusByCondition( " where id = " + ch.getComputerstatusid() );
			List<Computerstatus> orgComputerstatusList = computerstatusService.selectComputerstatusByCondition( " where id = " + originalComputerStatus );
			
			
			if(computerstatusList==null || computerstatusList.size() !=0 || orgComputerstatusList==null || orgComputerstatusList.size() !=0){
				returnJson.setFlag(0);		
				returnJson.setReason("获取可借状态失败!");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
			}
			int nowAviBow = computerstatusList.get(0).getAvailableborrow();
			int orgAviBow = orgComputerstatusList.get(0).getAvailableborrow();
			
//			原先可借现在不可借
			if( (nowAviBow != orgAviBow) && (orgAviBow == ComputerConfig.computeravailableborrowstatusid) ){
				Computermodel cm = computermodelService.selectComputermodelByCondition(" where computermodeltype = " + ch.getComputermodelid() ).get(0);
				if(cm.getAvailableborrowcountnumber() == 0){
					cm.setAvailableborrowcountnumber(0);
				}				
				computermodelService.execSql(" update Computermodel set availableborrowcountnumber="+(cm.getAvailableborrowcountnumber()-1)+"  where computermodeltype = "+ch.getComputermodelid());
			}
			
//			原先不可借现在可借
			if( (nowAviBow != orgAviBow) && (orgAviBow != ComputerConfig.computeravailableborrowstatusid) ){
				Computermodel cm = computermodelService.selectComputermodelByCondition(" where computermodeltype = " + ch.getComputermodelid() ).get(0);
				if(cm.getAvailableborrowcountnumber() == 0){
					cm.setAvailableborrowcountnumber(0);
				}				
				computermodelService.execSql(" update Computermodel set availableborrowcountnumber="+(cm.getAvailableborrowcountnumber()+1)+"  where computermodeltype = "+ch.getComputermodelid());
			}
						
			returnJson.setFlag(1);		
			returnJson.setReason("修改成功!");
			JSONObject jo = JSONObject.fromObject(returnJson);				
			this.returnStr = jo.toString();
			//actionMsg = getText("viewComputercategorySuccess");
			return SUCCESS;
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerAction的方法：viewComputer错误"+e);
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
	public String editComputer(){
		log.info(logprefix + "editComputer");
			
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
		
		log.info(logprefix + "viewComputer");
		
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

/**
 * view ComputerFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewComputerFull() {
				
		try {
			int getId = computer.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			ComputerFull temComputerFull = computerService.selectComputerFullById(getId);				
			if(temComputerFull!=null){				
				BeanUtils.copyProperties(computerFull,temComputerFull);
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


	//根据computermodelid 查询实体
	public String selectComputerByComputermodelId() {
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
	//根据computermodelid 查询实体full
	public String selectComputerFullByComputermodelId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerFullList  = computerService.selectComputerFullByComputermodelId(userId);
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
	
	public int getComputerid() {
		return computerid;
	}

	public void setComputerid(int computerid) {
		this.computerid = computerid;
	}
		public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
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

	public String getComputerSerialnumberEn() {
		return computerSerialnumberEn;
	}

	public void setComputerSerialnumberEn(String computerSerialnumberEn) {
		this.computerSerialnumberEn = computerSerialnumberEn;
	}

	public static Log getLog() {
		return log;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setComputerid(Integer computerid) {
		this.computerid = computerid;
	}

	public String getComputerRemarkEn() {
		return computerRemarkEn;
	}

	public void setComputerRemarkEn(String computerRemarkEn) {
		this.computerRemarkEn = computerRemarkEn;
	}

	public ReturnJson getReturnJson() {
		return returnJson;
	}

	public void setReturnJson(ReturnJson returnJson) {
		this.returnJson = returnJson;
	}

	public String getComputerIdsForDel() {
		return computerIdsForDel;
	}

	public void setComputerIdsForDel(String computerIdsForDel) {
		this.computerIdsForDel = computerIdsForDel;
	}

	public int getComputerIdEn() {
		return computerIdEn;
	}

	public void setComputerIdEn(int computerIdEn) {
		this.computerIdEn = computerIdEn;
	}
	
	
}
