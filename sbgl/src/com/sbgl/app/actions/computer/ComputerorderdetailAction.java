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
import com.sbgl.app.entity.*;
import com.sbgl.app.services.computer.ComputerorderdetailService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputerorderdetailAction")
public class ComputerorderdetailAction extends ActionSupport implements SessionAware,ModelDriven<Computerorderdetail>{
	
	private static final Log log = LogFactory.getLog(ComputerorderdetailAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private ComputerorderdetailService computerorderdetailService;
	
	private Computerorderdetail computerorderdetail = new Computerorderdetail();//实例化一个模型
	private Computerorderdetail computerorderdetailModel = new Computerorderdetail();//实例化一个模型
	private ComputerorderdetailFull computerorderdetailFull = new ComputerorderdetailFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Computerorderdetail> computerorderdetailList = new ArrayList<Computerorderdetail>();
	List<ComputerorderdetailFull> computerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
	private Integer computerorderdetailid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";		
	Page page = new Page();
	Integer pageNo=1;	
	
//  manage Computerorderdetail
	public String manageComputerorderdetail(){
		log.info(logprefix+"manageComputerorderdetailFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computerorderdetailService.countComputerorderdetailRow());
		computerorderdetailList  = computerorderdetailService.selectComputerorderdetailByPage(page);
		
//		查询全部
//		computerorderdetailList  = computerorderdetailService.selectComputerorderdetailById();
		
	    if(computerorderdetailList == null){
			computerorderdetailList = new ArrayList<Computerorderdetail>();
		}
//		for(int i = 0; i < computerorderdetailList.size(); i++){
//			System.out.println("id="+computerorderdetailList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageComputerorderdetailFull(){
		log.info("exec action method:manageComputerorderdetailFull");
		
//      分页查询		
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computerorderdetailService.countComputerorderdetailRow());
		computerorderdetailFullList  = computerorderdetailService.selectComputerorderdetailFullByPage(page);
		
//		查询全部
//		computerorderdetailFullList  = computerorderdetailService.selectComputerorderdetailFullAll();

		if(computerorderdetailFullList == null){
			computerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
		}
//		for(int i = 0; i < computerorderdetailFullList.size(); i++){
//			System.out.println("id="+computerorderdetailFullList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}			
			
		
	//管理
	public String manageComputerorderdetailInfo(){
		log.info(logprefix +" manageComputerorderdetail");
		//Page page = new Page();
		//if()
		computerorderdetailList  = computerorderdetailService.selectComputerorderdetailByPage(page);
		for(int i = 0; i < computerorderdetailList.size(); i++){
		//	System.out.println("id="+computerorderdetailList.get(i).getLoginusername());
		}
		return SUCCESS;
	}	
			
	public String addComputerorderdetail(){	
		log.info("Add Entity");

		try {
			Computerorderdetail temp = new Computerorderdetail();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerorderdetail);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			computerorderdetailService.addComputerorderdetail(temp);
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderdetailAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addComputerorderdetailAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Computerorderdetail temp = new Computerorderdetail();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerorderdetail);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			computerorderdetailService.addComputerorderdetail(temp);
			
			returnJson.setFlag(1);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderdetailAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteComputerorderdetail( ){
		try{
			if(computerorderdetail.getId() != null && computerorderdetail.getId() > 0){
				computerorderdetailService.deleteComputerorderdetail(computerorderdetail.getId());
				actionMsg = getText("deleteComputerorderdetailSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteComputerorderdetailFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderdetailAction的方法：deleteComputerorderdetail错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteComputerorderdetailAjax( ){
		try{
			if(computerorderdetail.getId() != null && computerorderdetail.getId() >= 0){
				computerorderdetailService.deleteComputerorderdetail(computerorderdetail.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderdetailAction的方法：deleteComputerorderdetail错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteComputerorderdetailFull(){
		try {
		
			Integer getId = computerorderdetail.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Computerorderdetail temp = computerorderdetailService.selectComputerorderdetailById(getId);
			if (temp != null) {
				computerorderdetailService.deleteComputerorderdetail(getId);
				return SUCCESS;
			} else {
				log.info("删除的id不存在");
				return "Error";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error";
	}
	
	//del entityfull Ajax
	public String deleteComputerorderdetailFullAjax( ){
		try{
			if(computerorderdetail.getId() != null && computerorderdetail.getId() >= 0){
				computerorderdetailService.deleteComputerorderdetail(computerorderdetail.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderdetailAction的方法：deleteComputerorderdetail错误"+e);
		}
		return "Error";
	}

//修改
	public String updateComputerorderdetail(){
		try {
			if(computerorderdetail.getId() != null && computerorderdetail.getId() > 0){				
				Computerorderdetail tempComputerorderdetail = computerorderdetailService.selectComputerorderdetailById(computerorderdetail.getId());
																				  								
												  								
												  								
												  								
												  								
												  								
												  								
								actionMsg = getText("viewComputerorderdetailSuccess");
			}else{
				actionMsg = getText("viewComputerorderdetailFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderdetailAction的方法：viewComputerorderdetail错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateComputerorderdetailAjax(){
		log.info(logprefix + "updateComputerorderdetailAjax,id="+computerorderdetail.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(computerorderdetail.getId() != null && computerorderdetail.getId() > 0){				
				Computerorderdetail tempComputerorderdetail = computerorderdetailService.selectComputerorderdetailById(computerorderdetail.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempComputerorderdetail.setComputerorderid(computerorderdetail.getComputerorderid());
  				tempComputerorderdetail.setComputerid(computerorderdetail.getComputerid());
  				tempComputerorderdetail.setComputernumber(computerorderdetail.getComputernumber());
  				tempComputerorderdetail.setCreatetime(computerorderdetail.getCreatetime());
  				tempComputerorderdetail.setBorrowday(computerorderdetail.getBorrowday());
  				tempComputerorderdetail.setBorrowperiod(computerorderdetail.getBorrowperiod());
  				tempComputerorderdetail.setStatus(computerorderdetail.getStatus());
 
				
				computerorderdetailService.updateComputerorderdetail(tempComputerorderdetail);		
				returnJson.setFlag(1);		
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewComputerorderdetailSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewComputerorderdetailFail");
				log.info(logprefix + "updateComputerorderdetailAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderdetailAction的方法：viewComputerorderdetail错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editComputerorderdetail(){
		log.info(logprefix + "editComputerorderdetail");
			
		try {
			//实体的id可以为0
			if(computerorderdetail.getId() != null && computerorderdetail.getId() >= 0){				
				Computerorderdetail temComputerorderdetail = computerorderdetailService.selectComputerorderdetailById(computerorderdetail.getId());
				if(temComputerorderdetail != null){
					BeanUtils.copyProperties(computerorderdetailModel,temComputerorderdetail);	
					//actionMsg = getText("selectComputerorderdetailByIdSuccess");
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
			log.error("类ComputerorderdetailAction的方法：selectComputerorderdetailById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editComputerorderdetailFull(){
		
		log.info(logprefix + "viewComputerorderdetail");
		
		try {
			if(computerorderdetail.getId() != null && computerorderdetail.getId() > 0){				
				ComputerorderdetailFull temComputerorderdetailFull = computerorderdetailService.selectComputerorderdetailFullById(computerorderdetail.getId());
				BeanUtils.copyProperties(computerorderdetailFull,temComputerorderdetailFull);	
				actionMsg = getText("selectComputerorderdetailByIdSuccess");
			}else{
				actionMsg = getText("selectComputerorderdetailByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderdetailAction的方法：selectComputerorderdetailFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewComputerorderdetail(){
		log.info("viewComputerorderdetail");
		try {
			if(computerorderdetail.getId() != null && computerorderdetail.getId() > 0){				
				Computerorderdetail temComputerorderdetail = computerorderdetailService.selectComputerorderdetailById(computerorderdetail.getId());
				BeanUtils.copyProperties(computerorderdetailModel,temComputerorderdetail);	
				actionMsg = getText("selectComputerorderdetailByIdSuccess");
			}else{
				actionMsg = getText("selectComputerorderdetailByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderdetailAction的方法：selectComputerorderdetailById错误"+e);
		}


		return "error";

	}	

/**
 * view ComputerorderdetailFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewComputerorderdetailFull() {
				
		try {
			int getId = computerorderdetail.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			ComputerorderdetailFull temComputerorderdetailFull = computerorderdetailService.selectComputerorderdetailFullById(getId);				
			if(temComputerorderdetailFull!=null){				
				BeanUtils.copyProperties(computerorderdetailFull,temComputerorderdetailFull);
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
	public String selectComputerorderdetailById(){
		log.info("selectComputerorderdetailById");
		try {
			if(computerorderdetail.getId() != null && computerorderdetail.getId() > 0){				
				Computerorderdetail temComputerorderdetail = computerorderdetailService.selectComputerorderdetailById(computerorderdetail.getId());
				BeanUtils.copyProperties(computerorderdetailModel,temComputerorderdetail);	
				actionMsg = getText("selectComputerorderdetailByIdSuccess");
			}else{
				actionMsg = getText("selectComputerorderdetailByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderdetailAction的方法：selectComputerorderdetailById错误"+e);
		}


		return "error";

	}	
	

	

	//查询全部
	public String selectComputerorderdetailAll(){
		
		computerorderdetailList  = computerorderdetailService.selectComputerorderdetailAll();
		for(int i = 0; i < computerorderdetailList.size(); i++){
			System.out.println("id="+computerorderdetailList.get(i).getId());
		}
		return SUCCESS;
	}



	
	//根据对象Id查询Full
	public String selectComputerorderdetailFullById(){
		System.out.println("selectComputerorderdetailFullById");
			try {
				if(computerorderdetail.getId() != null && computerorderdetail.getId() >= 0){				
				ComputerorderdetailFull temComputerorderdetailFull = computerorderdetailService.selectComputerorderdetailFullById(computerorderdetail.getId());
				BeanUtils.copyProperties(computerorderdetailFull,temComputerorderdetailFull);	
				actionMsg = getText("selectComputerorderdetailByIdSuccess");
			}else{
				actionMsg = getText("selectComputerorderdetailByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderdetailAction的方法：selectComputerorderdetailFullById错误"+e);
		}
		
		return "error";

	}	

	
	//查询全部Full
	public String selectComputerorderdetailFullAll(){
		log.info("exec selectComputerorderdetailFullAll");
		computerorderdetailFullList  = computerorderdetailService.selectComputerorderdetailFullAll();
		for(int i = 0; i < computerorderdetailFullList.size(); i++){
		//	System.out.println("id="+computerorderdetailFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}



	//get set
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	    this.session = session;
	}
	
	@Override
	public Computerorderdetail getModel() {
		// TODO Auto-generated method stub
		return computerorderdetail;
	}

//  
	public Computerorderdetail getComputerorderdetail() {
		return computerorderdetail;
	}
	
	public void setComputerorderdetail(Computerorderdetail computerorderdetail) {
		this.computerorderdetail = computerorderdetail;
	}
//  entityModel
	public Computerorderdetail getComputerorderdetailModel() {
		return computerorderdetailModel;
	}
	
	public void setComputerorderdetailModel(Computerorderdetail computerorderdetailModel) {
		this.computerorderdetailModel = computerorderdetailModel;
	}
	
	public ComputerorderdetailFull getComputerorderdetailFull() {
		return computerorderdetailFull;
	}
	
	public void setComputerorderdetailFull(ComputerorderdetailFull computerorderdetailFull) {
		this.computerorderdetailFull = computerorderdetailFull;
	}
	
	public List<Computerorderdetail> getComputerorderdetailList() {
		return computerorderdetailList;
	}


	public void setComputerorderdetailList(List<Computerorderdetail> computerorderdetailList) {
		this.computerorderdetailList = computerorderdetailList;
	}

	public List<ComputerorderdetailFull> getComputerorderdetailFullList() {
		return computerorderdetailFullList;
	}


	public void setComputerorderdetailFullList(List<ComputerorderdetailFull> computerorderdetailFullList) {
		this.computerorderdetailFullList = computerorderdetailFullList;
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
	
	public int getComputerorderdetailid() {
		return computerorderdetailid;
	}

	public void setComputerorderdetailid(int computerorderdetailid) {
		this.computerorderdetailid = computerorderdetailid;
	}
		public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
}
