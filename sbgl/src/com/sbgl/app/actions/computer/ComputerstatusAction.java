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
import com.sbgl.app.services.computer.ComputerstatusService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputerstatusAction")
public class ComputerstatusAction extends ActionSupport implements SessionAware,ModelDriven<Computerstatus>{
	
	private static final Log log = LogFactory.getLog(ComputerstatusAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private ComputerstatusService computerstatusService;
	
	private Computerstatus computerstatus = new Computerstatus();//实例化一个模型
	private Computerstatus computerstatusModel = new Computerstatus();//实例化一个模型
	private ComputerstatusFull computerstatusFull = new ComputerstatusFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Computerstatus> computerstatusList = new ArrayList<Computerstatus>();
	List<ComputerstatusFull> computerstatusFullList = new ArrayList<ComputerstatusFull>();
	private Integer computerstatusid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";		
	Page page = new Page();
	Integer pageNo=1;	
	ReturnJson returnJson = new ReturnJson();
	String computerstatusIdsForDel;
	
//  manage Computerstatus
	public String manageComputerstatus(){
		log.info(logprefix+"manageComputerstatusFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computerstatusService.countComputerstatusRow());
		computerstatusList  = computerstatusService.selectComputerstatusByPage(page);
		
//		查询全部
//		computerstatusList  = computerstatusService.selectComputerstatusById();
		
	    if(computerstatusList == null){
			computerstatusList = new ArrayList<Computerstatus>();
		}
//		for(int i = 0; i < computerstatusList.size(); i++){
//			System.out.println("id="+computerstatusList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageComputerstatusFull(){
		log.info("exec action method:manageComputerstatusFull");
		
//      分页查询		
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computerstatusService.countComputerstatusRow());
		computerstatusFullList  = computerstatusService.selectComputerstatusFullByPage(page);
		
//		查询全部
//		computerstatusFullList  = computerstatusService.selectComputerstatusFullAll();

		if(computerstatusFullList == null){
			computerstatusFullList = new ArrayList<ComputerstatusFull>();
		}
//		for(int i = 0; i < computerstatusFullList.size(); i++){
//			System.out.println("id="+computerstatusFullList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}			
			
		
	//管理
	public String manageComputerstatusInfo(){
		log.info(logprefix +" manageComputerstatus");
		//Page page = new Page();
		//if()
		computerstatusList  = computerstatusService.selectComputerstatusByPage(page);
		for(int i = 0; i < computerstatusList.size(); i++){
		//	System.out.println("id="+computerstatusList.get(i).getLoginusername());
		}
		return SUCCESS;
	}	
			
	public String addComputerstatus(){	
		log.info("Add Entity");

		try {
			Computerstatus temp = new Computerstatus();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerstatus);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			computerstatusService.addComputerstatus(temp);
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerstatusAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addComputerstatusAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Computerstatus temp = new Computerstatus();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerstatus);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			computerstatusService.addComputerstatus(temp);
			
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
			log.error("类ComputerstatusAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteComputerstatus( ){
		try{
			if(computerstatus.getId() != null && computerstatus.getId() > 0){
				computerstatusService.deleteComputerstatus(computerstatus.getId());
				actionMsg = getText("deleteComputerstatusSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteComputerstatusFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerstatusAction的方法：deleteComputerstatus错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteComputerstatusAjax( ){
		try{
			if(computerstatus.getId() != null && computerstatus.getId() >= 0){
				computerstatusService.deleteComputerstatus(computerstatus.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerstatusAction的方法：deleteComputerstatus错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteComputerstatusFull(){
		try {
		
			Integer getId = computerstatus.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Computerstatus temp = computerstatusService.selectComputerstatusById(getId);
			if (temp != null) {
				computerstatusService.deleteComputerstatus(getId);
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
	public String deleteComputerstatusFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			String ids[] = computerstatusIdsForDel.split(";");
			for(int i=0; i < ids.length;i++){
                                
				Integer tempDelId = Integer.valueOf(ids[i]);                        
				log.info(tempDelId);
                                //检查id
                                if(tempDelId == null || tempDelId < 0){
                                        returnJson.setFlag(0);
                                        returnJson.setReason("删除的id不规范");
                                        log.info("删除的id不规范");
                                        JSONObject jo = JSONObject.fromObject(returnJson);
                                        this.returnStr = jo.toString();
                                        return SUCCESS;
                                }        
                                //del
                                Computerstatus temp = computerstatusService.selectComputerstatusById(tempDelId);                        
                                if (temp != null) {                        
                                        //其他操作                                        
                                        computerstatusService.deleteComputerstatus(tempDelId);                                        
                                } else {
                                        log.info("删除的id不存在");                
                                        returnJson.setFlag(0);
                                        returnJson.setReason("删除的id不存在");
                                        JSONObject jo = JSONObject.fromObject(returnJson);
                                        this.returnStr = jo.toString();
                                        return SUCCESS;
                                }
                        }
                        returnJson.setFlag(1);
//                        returnJson.setReason("删除的id不存在");
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
	public String updateComputerstatus(){
		try {
			if(computerstatus.getId() != null && computerstatus.getId() > 0){				
				Computerstatus tempComputerstatus = computerstatusService.selectComputerstatusById(computerstatus.getId());
																				  								
								actionMsg = getText("viewComputerstatusSuccess");
			}else{
				actionMsg = getText("viewComputerstatusFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerstatusAction的方法：viewComputerstatus错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateComputerstatusAjax(){
		log.info(logprefix + "updateComputerstatusAjax,id="+computerstatus.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(computerstatus.getId() != null && computerstatus.getId() > 0){				
				Computerstatus tempComputerstatus = computerstatusService.selectComputerstatusById(computerstatus.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempComputerstatus.setName(computerstatus.getName());
 
				
				computerstatusService.updateComputerstatus(tempComputerstatus);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewComputerstatusSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewComputerstatusFail");
				log.info(logprefix + "updateComputerstatusAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerstatusAction的方法：viewComputerstatus错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editComputerstatus(){
		log.info(logprefix + "editComputerstatus");
			
		try {
			//实体的id可以为0
			if(computerstatus.getId() != null && computerstatus.getId() >= 0){				
				Computerstatus temComputerstatus = computerstatusService.selectComputerstatusById(computerstatus.getId());
				if(temComputerstatus != null){
					BeanUtils.copyProperties(computerstatusModel,temComputerstatus);	
					//actionMsg = getText("selectComputerstatusByIdSuccess");
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
			log.error("类ComputerstatusAction的方法：selectComputerstatusById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editComputerstatusFull(){
		
		log.info(logprefix + "viewComputerstatus");
		
		try {
			if(computerstatus.getId() != null && computerstatus.getId() > 0){				
				ComputerstatusFull temComputerstatusFull = computerstatusService.selectComputerstatusFullById(computerstatus.getId());
				BeanUtils.copyProperties(computerstatusFull,temComputerstatusFull);	
				actionMsg = getText("selectComputerstatusByIdSuccess");
			}else{
				actionMsg = getText("selectComputerstatusByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerstatusAction的方法：selectComputerstatusFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewComputerstatus(){
		log.info("viewComputerstatus");
		try {
			if(computerstatus.getId() != null && computerstatus.getId() > 0){				
				Computerstatus temComputerstatus = computerstatusService.selectComputerstatusById(computerstatus.getId());
				BeanUtils.copyProperties(computerstatusModel,temComputerstatus);	
				actionMsg = getText("selectComputerstatusByIdSuccess");
			}else{
				actionMsg = getText("selectComputerstatusByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerstatusAction的方法：selectComputerstatusById错误"+e);
		}


		return "error";

	}	

/**
 * view ComputerstatusFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewComputerstatusFull() {
				
		try {
			int getId = computerstatus.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			ComputerstatusFull temComputerstatusFull = computerstatusService.selectComputerstatusFullById(getId);				
			if(temComputerstatusFull!=null){				
				BeanUtils.copyProperties(computerstatusFull,temComputerstatusFull);
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
	public Computerstatus getModel() {
		// TODO Auto-generated method stub
		return computerstatus;
	}

//  
	public Computerstatus getComputerstatus() {
		return computerstatus;
	}
	
	public void setComputerstatus(Computerstatus computerstatus) {
		this.computerstatus = computerstatus;
	}
//  entityModel
	public Computerstatus getComputerstatusModel() {
		return computerstatusModel;
	}
	
	public void setComputerstatusModel(Computerstatus computerstatusModel) {
		this.computerstatusModel = computerstatusModel;
	}
	
	public ComputerstatusFull getComputerstatusFull() {
		return computerstatusFull;
	}
	
	public void setComputerstatusFull(ComputerstatusFull computerstatusFull) {
		this.computerstatusFull = computerstatusFull;
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


	public void setComputerstatusFullList(List<ComputerstatusFull> computerstatusFullList) {
		this.computerstatusFullList = computerstatusFullList;
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
	
	public int getComputerstatusid() {
		return computerstatusid;
	}

	public void setComputerstatusid(int computerstatusid) {
		this.computerstatusid = computerstatusid;
	}
		public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
	
	public String getComputerstatusIdsForDel() {
                return computerstatusIdsForDel;
        }

        public void setComputerstatusIdsForDel(String computerstatusIdsForDel) {
                this.computerstatusIdsForDel = computerstatusIdsForDel;
        }
}
