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
import com.sbgl.app.services.computer.ComputerorderclassruleService;
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
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Computerorderclassrule> computerorderclassruleList = new ArrayList<Computerorderclassrule>();
	List<ComputerorderclassruleFull> computerorderclassruleFullList = new ArrayList<ComputerorderclassruleFull>();
	private Integer computerorderclassruleid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";		
	Page page = new Page();
	Integer pageNo=1;	
	ReturnJson returnJson = new ReturnJson();
	String computerorderclassruleIdsForDel;
	
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
			
		
	//管理
	public String manageComputerorderclassruleInfo(){
		log.info(logprefix +" manageComputerorderclassrule");
		//Page page = new Page();
		//if()
		computerorderclassruleList  = computerorderclassruleService.selectComputerorderclassruleByPage(page);
		for(int i = 0; i < computerorderclassruleList.size(); i++){
		//	System.out.println("id="+computerorderclassruleList.get(i).getLoginusername());
		}
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
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Computerorderclassrule temp = new Computerorderclassrule();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerorderclassrule);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			computerorderclassruleService.addComputerorderclassrule(temp);
			
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
			log.error("类ComputerorderclassruleAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteComputerorderclassrule( ){
		try{
			if(computerorderclassrule.getId() != null && computerorderclassrule.getId() > 0){
				computerorderclassruleService.deleteComputerorderclassrule(computerorderclassrule.getId());
				actionMsg = getText("deleteComputerorderclassruleSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteComputerorderclassruleFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderclassruleAction的方法：deleteComputerorderclassrule错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteComputerorderclassruleAjax( ){
		try{
			if(computerorderclassrule.getId() != null && computerorderclassrule.getId() >= 0){
				computerorderclassruleService.deleteComputerorderclassrule(computerorderclassrule.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderclassruleAction的方法：deleteComputerorderclassrule错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteComputerorderclassruleFull(){
		try {
		
			Integer getId = computerorderclassrule.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Computerorderclassrule temp = computerorderclassruleService.selectComputerorderclassruleById(getId);
			if (temp != null) {
				computerorderclassruleService.deleteComputerorderclassrule(getId);
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
	public String deleteComputerorderclassruleFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			String ids[] = computerorderclassruleIdsForDel.split(";");
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
                                Computerorderclassrule temp = computerorderclassruleService.selectComputerorderclassruleById(tempDelId);                        
                                if (temp != null) {                        
                                        //其他操作                                        
                                        computerorderclassruleService.deleteComputerorderclassrule(tempDelId);                                        
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
}
