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
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.computer.ComputerhomeworkService;
import com.sbgl.app.services.computer.ComputerorderclassruleService;
import com.sbgl.app.services.computer.ComputerorderclassruledetailService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputerorderclassruleAction")
public class ComputerorderclassruleAction extends ActionSupport implements SessionAware,ModelDriven<Computerorderclassrule>{
	
	private static final Log log = LogFactory.getLog(ComputerorderclassruleAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private ComputerorderclassruleService computerorderclassruleService;
	
	private Computerorderclassrule computerorderclassrule = new Computerorderclassrule();//实例化一个模型
	private Computerorderclassrule computerorderclassruleModel = new Computerorderclassrule();//实例化一个模型
	private ComputerorderclassruleFull computerorderclassruleFull = new ComputerorderclassruleFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	
	List<Computerorderclassrule> computerorderclassruleList = new ArrayList<Computerorderclassrule>();
	List<ComputerorderclassruleFull> computerorderclassruleFullList = new ArrayList<ComputerorderclassruleFull>();
	private Integer computerorderclassruleid; //entity full 的id属性名称		

	
	
	@Resource
	private ComputerhomeworkService computerhomeworkService;	
	private Computerhomework computerhomework = new Computerhomework();//实例化一个模型
	private ComputerhomeworkFull computerhomeworkFull = new ComputerhomeworkFull();//实例化一个模型
	private List<Computerhomework> computerhomeworkList = new ArrayList<Computerhomework>();
	private List<ComputerhomeworkFull> computerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();
	
	
	
	
	
	
	
	
	
	private String logprefix = "exec action method:";		
	Page page = new Page();
	Integer pageNo=1;
	
		
	@Resource
	private ComputerorderclassruledetailService computerorderclassruledetailService;
	
	
	ReturnJson returnJson = new ReturnJson();
	String computerorderclassruleIdsForDel;
	
//	规则允许借的pc
	private String allowborrowpcids;

	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	private String returnInfo;
	
//  manage Computerorderclassrule
	public String manageComputerorderclassrule(){
		log.info(logprefix+"manageComputerorderclassruleFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computerorderclassruleService.countComputerorderclassruleRow());
		computerorderclassruleList  = computerorderclassruleService.selectComputerorderclassruleByPage(page);
		
//		查询全部
//		computerorderclassruleList  = computerorderclassruleService.selectComputerorderclassruleById();
		
	    if(computerorderclassruleList == null){
			computerorderclassruleList = new ArrayList<Computerorderclassrule>();
		}
//		for(int i = 0; i < computerorderclassruleList.size(); i++){
//			System.out.println("id="+computerorderclassruleList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageComputerorderclassruleFull(){
		log.info("exec action method:manageComputerorderclassruleFull");
		
//      分页查询		
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computerorderclassruleService.countComputerorderclassruleRow());
		computerorderclassruleFullList  = computerorderclassruleService.selectComputerorderclassruleFullByPage(page);
		
//		查询全部
//		computerorderclassruleFullList  = computerorderclassruleService.selectComputerorderclassruleFullAll();

		if(computerorderclassruleFullList == null){
			computerorderclassruleFullList = new ArrayList<ComputerorderclassruleFull>();
		}
//		for(int i = 0; i < computerorderclassruleFullList.size(); i++){
//			System.out.println("id="+computerorderclassruleFullList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}			
			
		
		
	public String addComputerorderclassrule(){	
		log.info("Add Entity");

		try {
			Computerorderclassrule temp = new Computerorderclassrule();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerorderclassrule);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			computerorderclassruleService.addComputerorderclassrule(temp);
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderclassruleAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addComputerorderclassruleAjax(){	
		log.info("Add Entity Ajax Manner");
		
		String returnInfo = "";
		if(addCheck() == false){
			returnJson.setFlag(0);		
			returnJson.setReason("参数不规范");
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			
			return SUCCESS;
		}
	
		try {
			Computerorderclassrule temp = new Computerorderclassrule();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerorderclassrule);			
		
			temp.setCreatetime(DateUtil.currentDate());
			int uid = Integer.valueOf(ComputerActionUtil.getUserId(ServletActionContext.getRequest()));
			temp.setCreateuserid(uid);
			

			
			String[] pcid = allowborrowpcids.split(";");
//			如果有异常
			for (int i = 0; i < pcid.length; i++) {
//				未分类是-1，判断要注意
				if(!NumberUtils.isNumber(pcid[i])){
					log.error("模型Id不规范："+pcid[i]);
					returnInfo = "获取允许借用模型Id出错";
					buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
					return SUCCESS;
				}
			}
//			添加规则，获取规则id
			computerorderclassruleService.addComputerorderclassrule(temp);
			
			if(temp.getId()==null || temp.getId() < 0){				
				returnInfo = "获取规则信息ID出错";
				buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
				return SUCCESS;
			}
			
			for (int i = 0; i < pcid.length; i++) {
				Computerorderclassruledetail c = new Computerorderclassruledetail();
				c.setAllowedcomputermodelid(Integer.valueOf(pcid[i]));
				c.setComputerorderclassruleid(temp.getId());
				computerorderclassruledetailService.addComputerorderclassruledetail(c);
			}
					
			returnInfo = "添加规则成功";
			buildReturnStr(ComputerConfig.ajaxsuccessreturn,returnInfo);
			return SUCCESS;
		} catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderclassruleAction的方法：addComputerorderclassruleAjax错误"+e);
		}
		
		returnJson.setFlag(0);	
		if(returnInfo.length() ==0 ){			
			returnJson.setReason(returnInfo);
		}else{
			returnJson.setReason("内部错误");
		}		
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}
	
	
	public void buildReturnStr(int flag,String errorStr){
		ReturnJson returnJson = new ReturnJson();
		returnJson.setFlag(flag);			
		returnJson.setReason(errorStr);
		
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
//		return SUCCESS;
	}
	
	public boolean addCheck(){
		Computerorderclassrule t = computerorderclassrule;
		if(t.getName()== null || t.getName().trim().length() == 0){
			return false;
		}
		if(t.getOrderstarttime()== null){
			return false;
		}
		
		if(t.getOrderendtime()== null){
			return false;
		}

		if(allowborrowpcids== null || allowborrowpcids.trim().length() == 0){
			return false;
		}	
//		if(t.get== null || t.getName().trim().length() == 0){
//			return false;
//		}
		
		return true;
	}

	//del entityfull Ajax
	public String deleteComputerorderclassruleFullAjax( ){
		
		log.info(logprefix + "deleteComputerorderclassruleFullAjax");
        if(!passCheckDelParm()){
        	buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
        	return SUCCESS;
        }
		
		
		try{
			String ids[] = computerorderclassruleIdsForDel.split(";");
			for(int i=0; i < ids.length;i++){
                                
				Integer tempDelId = Integer.valueOf(ids[i]);                       
			
				// 检查id
				if (tempDelId == null || tempDelId < 0) {				
					returnInfo = "删除的id不规范";
					log.info(returnInfo);
					buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
					return SUCCESS;
				}
				
//				查询规则
				Computerorderclassrule temp = computerorderclassruleService.selectComputerorderclassruleById(tempDelId);
				
				if(temp == null){
					returnInfo = "删除的规则id"+tempDelId+"不存在";
					log.info(returnInfo);
					buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
					return SUCCESS;
				}
				//规则是否使用
				if(isComputerorderclassruleUsed(tempDelId)){
					returnInfo = "作业"+computerhomeworkList.get(0).getName()+"使用了规则"+temp.getName();
					log.info(returnInfo);
					buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
		        	return SUCCESS;
				}
				
				// del
				
				if (temp != null) {
					computerorderclassruleService
							.deleteComputerorderclassrule(tempDelId);
				} 
			}
			
			returnInfo = "删除成功";
			log.info(returnInfo);
			buildReturnStr(ComputerConfig.ajaxsuccessreturn,returnInfo);
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

//	检查删除参数
//	检查del参数
	public boolean passCheckDelParm(){
		if(computerorderclassruleIdsForDel==null || computerorderclassruleIdsForDel.length() == 0){
			returnInfo = "没有选择删除项";
			return false;
		}
		
		String ids[] = computerorderclassruleIdsForDel.split(";");
		for(int i=0; i < ids.length;i++){
			if(!NumberUtils.isNumber(ids[i])){
				returnInfo = "删除参数不正确";
				return false;
			}
		}
		
		
		return true;
	}

	
//	检查某个规则是否已经使用
	public boolean isComputerorderclassruleUsed(int id){
		
		String testusesql = " where computerorderclassruleid = "+id;
		computerhomeworkList  =  computerhomeworkService.selectComputerhomeworkByCondition(testusesql);
		
		if(computerhomeworkList == null || computerhomeworkList.size() == 0){
			return false;
		}
		return true;
	}
	
	
	//修改
	public String updateComputerorderclassrule(){
		try {
			if(computerorderclassrule.getId() != null && computerorderclassrule.getId() > 0){				
				Computerorderclassrule tempComputerorderclassrule = computerorderclassruleService.selectComputerorderclassruleById(computerorderclassrule.getId());
																				  								
												  								
												  								
												  								
												  								
												  								
												  								
												  								
												  								
								actionMsg = getText("viewComputerorderclassruleSuccess");
			}else{
				actionMsg = getText("viewComputerorderclassruleFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderclassruleAction的方法：viewComputerorderclassrule错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateComputerorderclassruleAjax(){
		log.info(logprefix + "updateComputerorderclassruleAjax,id="+computerorderclassrule.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(computerorderclassrule.getId() != null && computerorderclassrule.getId() > 0){				
				Computerorderclassrule tempComputerorderclassrule = computerorderclassruleService.selectComputerorderclassruleById(computerorderclassrule.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempComputerorderclassrule.setName(computerorderclassrule.getName());
  				tempComputerorderclassrule.setClassname(computerorderclassrule.getClassname());
  				tempComputerorderclassrule.setClassid(computerorderclassrule.getClassid());
  				tempComputerorderclassrule.setOrderstarttime(computerorderclassrule.getOrderstarttime());
  				tempComputerorderclassrule.setOrderendtime(computerorderclassrule.getOrderendtime());
  				tempComputerorderclassrule.setAvailableordertime(computerorderclassrule.getAvailableordertime());
  				tempComputerorderclassrule.setCreateuserid(computerorderclassrule.getCreateuserid());
  				tempComputerorderclassrule.setCreatetime(computerorderclassrule.getCreatetime());
  				tempComputerorderclassrule.setStatus(computerorderclassrule.getStatus());
 
				
				computerorderclassruleService.updateComputerorderclassrule(tempComputerorderclassrule);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewComputerorderclassruleSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewComputerorderclassruleFail");
				log.info(logprefix + "updateComputerorderclassruleAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderclassruleAction的方法：viewComputerorderclassrule错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editComputerorderclassrule(){
		log.info(logprefix + "editComputerorderclassrule");
			
		try {
			//实体的id可以为0
			if(computerorderclassrule.getId() != null && computerorderclassrule.getId() >= 0){				
				Computerorderclassrule temComputerorderclassrule = computerorderclassruleService.selectComputerorderclassruleById(computerorderclassrule.getId());
				if(temComputerorderclassrule != null){
					BeanUtils.copyProperties(computerorderclassruleModel,temComputerorderclassrule);	
					//actionMsg = getText("selectComputerorderclassruleByIdSuccess");
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
			log.error("类ComputerorderclassruleAction的方法：selectComputerorderclassruleById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editComputerorderclassruleFull(){
		
		log.info(logprefix + "viewComputerorderclassrule");
		
		try {
			if(computerorderclassrule.getId() != null && computerorderclassrule.getId() > 0){				
				ComputerorderclassruleFull temComputerorderclassruleFull = computerorderclassruleService.selectComputerorderclassruleFullById(computerorderclassrule.getId());
				BeanUtils.copyProperties(computerorderclassruleFull,temComputerorderclassruleFull);	
				actionMsg = getText("selectComputerorderclassruleByIdSuccess");
			}else{
				actionMsg = getText("selectComputerorderclassruleByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderclassruleAction的方法：selectComputerorderclassruleFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewComputerorderclassrule(){
		log.info("viewComputerorderclassrule");
		try {
			if(computerorderclassrule.getId() != null && computerorderclassrule.getId() > 0){				
				Computerorderclassrule temComputerorderclassrule = computerorderclassruleService.selectComputerorderclassruleById(computerorderclassrule.getId());
				BeanUtils.copyProperties(computerorderclassruleModel,temComputerorderclassrule);	
				actionMsg = getText("selectComputerorderclassruleByIdSuccess");
			}else{
				actionMsg = getText("selectComputerorderclassruleByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderclassruleAction的方法：selectComputerorderclassruleById错误"+e);
		}


		return "error";

	}	

/**
 * view ComputerorderclassruleFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewComputerorderclassruleFull() {
				
		try {
			int getId = computerorderclassrule.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			ComputerorderclassruleFull temComputerorderclassruleFull = computerorderclassruleService.selectComputerorderclassruleFullById(getId);				
			if(temComputerorderclassruleFull!=null){				
				BeanUtils.copyProperties(computerorderclassruleFull,temComputerorderclassruleFull);
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
/*
*/
	//get set
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	    this.session = session;
	}
	
	@Override
	public Computerorderclassrule getModel() {
		// TODO Auto-generated method stub
		return computerorderclassrule;
	}

//  
	public Computerorderclassrule getComputerorderclassrule() {
		return computerorderclassrule;
	}
	
	public void setComputerorderclassrule(Computerorderclassrule computerorderclassrule) {
		this.computerorderclassrule = computerorderclassrule;
	}
//  entityModel
	public Computerorderclassrule getComputerorderclassruleModel() {
		return computerorderclassruleModel;
	}
	
	public void setComputerorderclassruleModel(Computerorderclassrule computerorderclassruleModel) {
		this.computerorderclassruleModel = computerorderclassruleModel;
	}
	
	public ComputerorderclassruleFull getComputerorderclassruleFull() {
		return computerorderclassruleFull;
	}
	
	public void setComputerorderclassruleFull(ComputerorderclassruleFull computerorderclassruleFull) {
		this.computerorderclassruleFull = computerorderclassruleFull;
	}
	
	public List<Computerorderclassrule> getComputerorderclassruleList() {
		return computerorderclassruleList;
	}


	public void setComputerorderclassruleList(List<Computerorderclassrule> computerorderclassruleList) {
		this.computerorderclassruleList = computerorderclassruleList;
	}

	public List<ComputerorderclassruleFull> getComputerorderclassruleFullList() {
		return computerorderclassruleFullList;
	}


	public void setComputerorderclassruleFullList(List<ComputerorderclassruleFull> computerorderclassruleFullList) {
		this.computerorderclassruleFullList = computerorderclassruleFullList;
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
	
	public int getComputerorderclassruleid() {
		return computerorderclassruleid;
	}

	public void setComputerorderclassruleid(int computerorderclassruleid) {
		this.computerorderclassruleid = computerorderclassruleid;
	}
		public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
	
	public String getComputerorderclassruleIdsForDel() {
                return computerorderclassruleIdsForDel;
        }

        public void setComputerorderclassruleIdsForDel(String computerorderclassruleIdsForDel) {
                this.computerorderclassruleIdsForDel = computerorderclassruleIdsForDel;
        }

		public ComputerorderclassruleService getComputerorderclassruleService() {
			return computerorderclassruleService;
		}

		public void setComputerorderclassruleService(
				ComputerorderclassruleService computerorderclassruleService) {
			this.computerorderclassruleService = computerorderclassruleService;
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

		public ComputerorderclassruledetailService getComputerorderclassruledetailService() {
			return computerorderclassruledetailService;
		}

		public void setComputerorderclassruledetailService(
				ComputerorderclassruledetailService computerorderclassruledetailService) {
			this.computerorderclassruledetailService = computerorderclassruledetailService;
		}

		public ReturnJson getReturnJson() {
			return returnJson;
		}

		public void setReturnJson(ReturnJson returnJson) {
			this.returnJson = returnJson;
		}


		public String getAllowborrowpcids() {
			return allowborrowpcids;
		}

		public void setAllowborrowpcids(String allowborrowpcids) {
			this.allowborrowpcids = allowborrowpcids;
		}

		public static Log getLog() {
			return log;
		}

		public Map<String, Object> getSession() {
			return session;
		}

		public void setComputerorderclassruleid(Integer computerorderclassruleid) {
			this.computerorderclassruleid = computerorderclassruleid;
		}

		
        
}
