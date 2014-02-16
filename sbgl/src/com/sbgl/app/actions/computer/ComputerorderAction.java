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
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.common.computer.ComputerorderdetailInfo;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.services.computer.ComputerorderdetailService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputerorderAction")
public class ComputerorderAction extends ActionSupport implements SessionAware,ModelDriven<Computerorder>{
	
	private static final Log log = LogFactory.getLog(ComputerorderAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private ComputerorderService computerorderService;	
	private Computerorder computerorder = new Computerorder();//实例化一个模型
	private Computerorder computerorderModel= new Computerorder();//实例化一个模型
	private ComputerorderFull computerorderFull = new ComputerorderFull();//实例化一个模型

	List<Computerorder> computerorderList = new ArrayList<Computerorder>();
	List<ComputerorderFull> computerorderFullList = new ArrayList<ComputerorderFull>();
	private Integer computerorderid; //entity full 的id属性名称		
	
	
	@Resource
	private ComputerorderdetailService computerorderdetailService;	
	private Computerorderdetail computerorderdetail = new Computerorderdetail();//实例化一个模型
	private ComputerorderdetailFull computerorderdetailFull = new ComputerorderdetailFull();//实例化一个模型	
	List<Computerorderdetail> computerorderdetailList = new ArrayList<Computerorderdetail>();
	List<ComputerorderdetailFull> computerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
	private Integer computerorderdetailid; //entity full 的id属性名称		
	
	
	
	
	
	private int ComputerorderStatusAduitAll = ComputerorderInfo.ComputerorderStatusAduitAll;
	private int ComputerorderStatusAduitPass = ComputerorderInfo.ComputerorderStatusAduitPass;
	private int ComputerorderStatusAduitReject = ComputerorderInfo.ComputerorderStatusAduitReject;
	private int ComputerorderStatusAduitDel = ComputerorderInfo.ComputerorderStatusAduitDel;
	private int ComputerorderStatusAduitWait = ComputerorderInfo.ComputerorderStatusAduitWait;
	private int IndividualOrder = ComputerorderInfo.IndividualOrder;
	private int ClassOrder = ComputerorderInfo.IndividualOrder;
	
	
	
	private String logprefix = "exec action method:";		
	Page page = new Page();
	Integer pageNo=1;	
	

	
	public String computerorderIdsForDel;

//	return info
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	private String returnInfo;
	private String actionMsg; // Action间传递的消息参数
	
//	public int auditStatus;//审核结果
	

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
	
	
	//del entityfull Ajax
	public String deleteComputerorderFullAjax( ){
		
		log.info(logprefix + "deleteComputerorderFullAjax");
		ReturnJson returnJson = new ReturnJson();
		try{
			String ids[] = computerorderIdsForDel.split(";");
			for(int i=0; i < ids.length;i++){				
				Integer delId = Integer.valueOf(ids[i]);
//				computerorderService.deleteComputerorder(delId);
//				computerorderdetailService.deleteComputerorderdetailByCondition(" where computerorderid = "+delId);
				Computerorder tempCo = computerorderService.selectComputerorderById(delId);
				tempCo.setStatus(ComputerorderInfo.ComputerorderStatusAduitDel);
				computerorderService.updateComputerorder(tempCo);
				computerorderdetailService.execSql(" update Computerorderdetail set status="+ComputerorderInfo.ComputerorderStatusAduitDel+" where computerorderid = "+delId);
//				log.info(delId);				
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

//修改
	public String updateComputerorder(){
		try {
			if(computerorder.getId() != null && computerorder.getId() > 0){				
				Computerorder tempComputerorder = computerorderService.selectComputerorderById(computerorder.getId());
																				  								
												  								
												  								
												  								
								actionMsg = getText("viewComputerorderSuccess");
			}else{
				actionMsg = getText("viewComputerorderFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderAction的方法：viewComputerorder错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateComputerorderAjax(){
		log.info(logprefix + "updateComputerorderAjax,id="+computerorder.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(computerorder.getId() != null && computerorder.getId() > 0){				
				Computerorder tempComputerorder = computerorderService.selectComputerorderById(computerorder.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempComputerorder.setSerialnumber(computerorder.getSerialnumber());
  				tempComputerorder.setUserid(computerorder.getUserid());
  				tempComputerorder.setCreatetime(computerorder.getCreatetime());
  				tempComputerorder.setStatus(computerorder.getStatus());
 
				
				computerorderService.updateComputerorder(tempComputerorder);		
				returnJson.setFlag(1);		
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewComputerorderSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewComputerorderFail");
				log.info(logprefix + "updateComputerorderAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderAction的方法：viewComputerorder错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	//审核表单，修改表单状态
	public String auditComputerorderAjax(){
		log.info(logprefix + "auditComputerorderAjax,id="+computerorder.getId());
		
		try {
				
				Computerorder tempComputerorder = computerorderService.selectComputerorderById(computerorder.getId());
				if(tempComputerorder==null){
					returnInfo = "获取审核表单失败";
					buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
					return SUCCESS;
				}
				
//              修改状态				
  				tempComputerorder.setStatus(computerorder.getStatus());
			
				computerorderService.execSql("  update Computerorder set status="+computerorder.getStatus()+" where id = "+computerorder.getId());

				computerorderdetailService.execSql(" update Computerorderdetail set status="+computerorder.getStatus()+" where computerorderid = "+computerorder.getId());

				
				returnInfo = "审核完成";
				buildReturnStr(ComputerConfig.ajaxsuccessreturn,returnInfo);
				return SUCCESS;
				
				
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderAction的方法：viewComputerorder错误"+e);
		}

			returnInfo = "审核失败,发生内部错误";
			buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
			return SUCCESS;
	}
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editComputerorder(){
		log.info(logprefix + "editComputerorder");
			
		try {
			//实体的id可以为0
			if(computerorder.getId() != null && computerorder.getId() >= 0){				
				Computerorder temComputerorder = computerorderService.selectComputerorderById(computerorder.getId());
				if(temComputerorder != null){
					BeanUtils.copyProperties(computerorderModel,temComputerorder);	
					//actionMsg = getText("selectComputerorderByIdSuccess");
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
			log.error("类ComputerorderAction的方法：selectComputerorderById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editComputerorderFull(){
		
		log.info(logprefix + "viewComputerorder");
		
		try {
			if(computerorder.getId() != null && computerorder.getId() > 0){				
				ComputerorderFull temComputerorderFull = computerorderService.selectComputerorderFullById(computerorder.getId());
				BeanUtils.copyProperties(computerorderFull,temComputerorderFull);	
				actionMsg = getText("selectComputerorderByIdSuccess");
			}else{
				actionMsg = getText("selectComputerorderByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderAction的方法：selectComputerorderFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewComputerorder(){
		log.info("viewComputerorder");
		try {
			if(computerorder.getId() != null && computerorder.getId() > 0){				
				Computerorder temComputerorder = computerorderService.selectComputerorderById(computerorder.getId());
				BeanUtils.copyProperties(computerorderModel,temComputerorder);	
				actionMsg = getText("selectComputerorderByIdSuccess");
			}else{
				actionMsg = getText("selectComputerorderByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderAction的方法：selectComputerorderById错误"+e);
		}


		return "error";

	}	

/**
 * view ComputerorderFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewComputerorderFull() {
				
		try {
			int getId = computerorder.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			ComputerorderFull temComputerorderFull = computerorderService.selectComputerorderFullById(getId);				
			if(temComputerorderFull!=null){				
				BeanUtils.copyProperties(computerorderFull,temComputerorderFull);
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
	public Computerorder getModel() {
		// TODO Auto-generated method stub
		return computerorder;
	}

//  
	public Computerorder getComputerorder() {
		return computerorder;
	}
	
	public void setComputerorder(Computerorder computerorder) {
		this.computerorder = computerorder;
	}
//  entityModel
	public Computerorder getComputerorderModel() {
		return computerorderModel;
	}
	
	public void setComputerorderModel(Computerorder computerorderModel) {
		this.computerorderModel = computerorderModel;
	}
	
	public ComputerorderFull getComputerorderFull() {
		return computerorderFull;
	}
	
	public void setComputerorderFull(ComputerorderFull computerorderFull) {
		this.computerorderFull = computerorderFull;
	}
	
	public List<Computerorder> getComputerorderList() {
		return computerorderList;
	}


	public void setComputerorderList(List<Computerorder> computerorderList) {
		this.computerorderList = computerorderList;
	}

	public List<ComputerorderFull> getComputerorderFullList() {
		return computerorderFullList;
	}


	public void setComputerorderFullList(List<ComputerorderFull> computerorderFullList) {
		this.computerorderFullList = computerorderFullList;
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
	
	public int getComputerorderid() {
		return computerorderid;
	}

	public void setComputerorderid(int computerorderid) {
		this.computerorderid = computerorderid;
	}
		public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public ComputerorderService getComputerorderService() {
		return computerorderService;
	}

	public void setComputerorderService(ComputerorderService computerorderService) {
		this.computerorderService = computerorderService;
	}

	public String getActionMsg() {
		return actionMsg;
	}

	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}

	public ComputerorderdetailService getComputerorderdetailService() {
		return computerorderdetailService;
	}

	public void setComputerorderdetailService(
			ComputerorderdetailService computerorderdetailService) {
		this.computerorderdetailService = computerorderdetailService;
	}

	public Computerorderdetail getComputerorderdetail() {
		return computerorderdetail;
	}

	public void setComputerorderdetail(Computerorderdetail computerorderdetail) {
		this.computerorderdetail = computerorderdetail;
	}

	public ComputerorderdetailFull getComputerorderdetailFull() {
		return computerorderdetailFull;
	}

	public void setComputerorderdetailFull(
			ComputerorderdetailFull computerorderdetailFull) {
		this.computerorderdetailFull = computerorderdetailFull;
	}

	public List<Computerorderdetail> getComputerorderdetailList() {
		return computerorderdetailList;
	}

	public void setComputerorderdetailList(
			List<Computerorderdetail> computerorderdetailList) {
		this.computerorderdetailList = computerorderdetailList;
	}

	public List<ComputerorderdetailFull> getComputerorderdetailFullList() {
		return computerorderdetailFullList;
	}

	public void setComputerorderdetailFullList(
			List<ComputerorderdetailFull> computerorderdetailFullList) {
		this.computerorderdetailFullList = computerorderdetailFullList;
	}

	public Integer getComputerorderdetailid() {
		return computerorderdetailid;
	}

	public void setComputerorderdetailid(Integer computerorderdetailid) {
		this.computerorderdetailid = computerorderdetailid;
	}

	public String getLogprefix() {
		return logprefix;
	}

	public void setLogprefix(String logprefix) {
		this.logprefix = logprefix;
	}

	public String getComputerorderIdsForDel() {
		return computerorderIdsForDel;
	}

	public void setComputerorderIdsForDel(String computerorderIdsForDel) {
		this.computerorderIdsForDel = computerorderIdsForDel;
	}

	public static Log getLog() {
		return log;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setComputerorderid(Integer computerorderid) {
		this.computerorderid = computerorderid;
	}

	public int getComputerorderStatusAduitAll() {
		return ComputerorderStatusAduitAll;
	}

	public void setComputerorderStatusAduitAll(int computerorderStatusAduitAll) {
		ComputerorderStatusAduitAll = computerorderStatusAduitAll;
	}

	public int getComputerorderStatusAduitPass() {
		return ComputerorderStatusAduitPass;
	}

	public void setComputerorderStatusAduitPass(int computerorderStatusAduitPass) {
		ComputerorderStatusAduitPass = computerorderStatusAduitPass;
	}

	public int getComputerorderStatusAduitReject() {
		return ComputerorderStatusAduitReject;
	}

	public void setComputerorderStatusAduitReject(int computerorderStatusAduitReject) {
		ComputerorderStatusAduitReject = computerorderStatusAduitReject;
	}

	public int getComputerorderStatusAduitDel() {
		return ComputerorderStatusAduitDel;
	}

	public void setComputerorderStatusAduitDel(int computerorderStatusAduitDel) {
		ComputerorderStatusAduitDel = computerorderStatusAduitDel;
	}

	public int getComputerorderStatusAduitWait() {
		return ComputerorderStatusAduitWait;
	}

	public void setComputerorderStatusAduitWait(int computerorderStatusAduitWait) {
		ComputerorderStatusAduitWait = computerorderStatusAduitWait;
	}

	public int getIndividualOrder() {
		return IndividualOrder;
	}

	public void setIndividualOrder(int individualOrder) {
		IndividualOrder = individualOrder;
	}

	public int getClassOrder() {
		return ClassOrder;
	}

	public void setClassOrder(int classOrder) {
		ClassOrder = classOrder;
	}
	
	
}
