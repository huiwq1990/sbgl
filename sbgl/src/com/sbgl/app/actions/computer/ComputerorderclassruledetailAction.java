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
import com.sbgl.app.services.computer.ComputerorderclassruledetailService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputerorderclassruledetailAction")
public class ComputerorderclassruledetailAction extends ActionSupport implements SessionAware,ModelDriven<Computerorderclassruledetail>{
	
	private static final Log log = LogFactory.getLog(ComputerorderclassruledetailAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private ComputerorderclassruledetailService computerorderclassruledetailService;
	
	private Computerorderclassruledetail computerorderclassruledetail = new Computerorderclassruledetail();//实例化一个模型
	private Computerorderclassruledetail computerorderclassruledetailModel = new Computerorderclassruledetail();//实例化一个模型
	private ComputerorderclassruledetailFull computerorderclassruledetailFull = new ComputerorderclassruledetailFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Computerorderclassruledetail> computerorderclassruledetailList = new ArrayList<Computerorderclassruledetail>();
	List<ComputerorderclassruledetailFull> computerorderclassruledetailFullList = new ArrayList<ComputerorderclassruledetailFull>();
	private Integer computerorderclassruledetailid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";		
	Page page = new Page();
	Integer pageNo=1;	
	ReturnJson returnJson = new ReturnJson();
	String computerorderclassruledetailIdsForDel;
	
//  manage Computerorderclassruledetail
	public String manageComputerorderclassruledetail(){
		log.info(logprefix+"manageComputerorderclassruledetailFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computerorderclassruledetailService.countComputerorderclassruledetailRow());
		computerorderclassruledetailList  = computerorderclassruledetailService.selectComputerorderclassruledetailByPage(page);
		
//		查询全部
//		computerorderclassruledetailList  = computerorderclassruledetailService.selectComputerorderclassruledetailById();
		
	    if(computerorderclassruledetailList == null){
			computerorderclassruledetailList = new ArrayList<Computerorderclassruledetail>();
		}
//		for(int i = 0; i < computerorderclassruledetailList.size(); i++){
//			System.out.println("id="+computerorderclassruledetailList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageComputerorderclassruledetailFull(){
		log.info("exec action method:manageComputerorderclassruledetailFull");
		
//      分页查询		
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computerorderclassruledetailService.countComputerorderclassruledetailRow());
		computerorderclassruledetailFullList  = computerorderclassruledetailService.selectComputerorderclassruledetailFullByPage(page);
		
//		查询全部
//		computerorderclassruledetailFullList  = computerorderclassruledetailService.selectComputerorderclassruledetailFullAll();

		if(computerorderclassruledetailFullList == null){
			computerorderclassruledetailFullList = new ArrayList<ComputerorderclassruledetailFull>();
		}
//		for(int i = 0; i < computerorderclassruledetailFullList.size(); i++){
//			System.out.println("id="+computerorderclassruledetailFullList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}			
			
		
	//管理
	public String manageComputerorderclassruledetailInfo(){
		log.info(logprefix +" manageComputerorderclassruledetail");
		//Page page = new Page();
		//if()
		computerorderclassruledetailList  = computerorderclassruledetailService.selectComputerorderclassruledetailByPage(page);
		for(int i = 0; i < computerorderclassruledetailList.size(); i++){
		//	System.out.println("id="+computerorderclassruledetailList.get(i).getLoginusername());
		}
		return SUCCESS;
	}	
			
	public String addComputerorderclassruledetail(){	
		log.info("Add Entity");

		try {
			Computerorderclassruledetail temp = new Computerorderclassruledetail();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerorderclassruledetail);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			computerorderclassruledetailService.addComputerorderclassruledetail(temp);
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderclassruledetailAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addComputerorderclassruledetailAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Computerorderclassruledetail temp = new Computerorderclassruledetail();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerorderclassruledetail);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
			
			computerorderclassruledetailService.addComputerorderclassruledetail(temp);
			
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
			log.error("类ComputerorderclassruledetailAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteComputerorderclassruledetail( ){
		try{
			if(computerorderclassruledetail.getId() != null && computerorderclassruledetail.getId() > 0){
				computerorderclassruledetailService.deleteComputerorderclassruledetail(computerorderclassruledetail.getId());
				actionMsg = getText("deleteComputerorderclassruledetailSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteComputerorderclassruledetailFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderclassruledetailAction的方法：deleteComputerorderclassruledetail错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteComputerorderclassruledetailAjax( ){
		try{
			if(computerorderclassruledetail.getId() != null && computerorderclassruledetail.getId() >= 0){
				computerorderclassruledetailService.deleteComputerorderclassruledetail(computerorderclassruledetail.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderclassruledetailAction的方法：deleteComputerorderclassruledetail错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteComputerorderclassruledetailFull(){
		try {
		
			Integer getId = computerorderclassruledetail.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Computerorderclassruledetail temp = computerorderclassruledetailService.selectComputerorderclassruledetailById(getId);
			if (temp != null) {
				computerorderclassruledetailService.deleteComputerorderclassruledetail(getId);
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
	public String deleteComputerorderclassruledetailFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			String ids[] = computerorderclassruledetailIdsForDel.split(";");
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
                                Computerorderclassruledetail temp = computerorderclassruledetailService.selectComputerorderclassruledetailById(tempDelId);                        
                                if (temp != null) {                        
                                        //其他操作                                        
                                        computerorderclassruledetailService.deleteComputerorderclassruledetail(tempDelId);                                        
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
	public String updateComputerorderclassruledetail(){
		try {
			if(computerorderclassruledetail.getId() != null && computerorderclassruledetail.getId() > 0){				
				Computerorderclassruledetail tempComputerorderclassruledetail = computerorderclassruledetailService.selectComputerorderclassruledetailById(computerorderclassruledetail.getId());
																				  								
												  								
								actionMsg = getText("viewComputerorderclassruledetailSuccess");
			}else{
				actionMsg = getText("viewComputerorderclassruledetailFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderclassruledetailAction的方法：viewComputerorderclassruledetail错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateComputerorderclassruledetailAjax(){
		log.info(logprefix + "updateComputerorderclassruledetailAjax,id="+computerorderclassruledetail.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(computerorderclassruledetail.getId() != null && computerorderclassruledetail.getId() > 0){				
				Computerorderclassruledetail tempComputerorderclassruledetail = computerorderclassruledetailService.selectComputerorderclassruledetailById(computerorderclassruledetail.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempComputerorderclassruledetail.setComputerorderclassruleid(computerorderclassruledetail.getComputerorderclassruleid());
  				tempComputerorderclassruledetail.setAllowedcomputermodelid(computerorderclassruledetail.getAllowedcomputermodelid());
 
				
				computerorderclassruledetailService.updateComputerorderclassruledetail(tempComputerorderclassruledetail);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewComputerorderclassruledetailSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewComputerorderclassruledetailFail");
				log.info(logprefix + "updateComputerorderclassruledetailAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderclassruledetailAction的方法：viewComputerorderclassruledetail错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editComputerorderclassruledetail(){
		log.info(logprefix + "editComputerorderclassruledetail");
			
		try {
			//实体的id可以为0
			if(computerorderclassruledetail.getId() != null && computerorderclassruledetail.getId() >= 0){				
				Computerorderclassruledetail temComputerorderclassruledetail = computerorderclassruledetailService.selectComputerorderclassruledetailById(computerorderclassruledetail.getId());
				if(temComputerorderclassruledetail != null){
					BeanUtils.copyProperties(computerorderclassruledetailModel,temComputerorderclassruledetail);	
					//actionMsg = getText("selectComputerorderclassruledetailByIdSuccess");
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
			log.error("类ComputerorderclassruledetailAction的方法：selectComputerorderclassruledetailById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editComputerorderclassruledetailFull(){
		
		log.info(logprefix + "viewComputerorderclassruledetail");
		
		try {
			if(computerorderclassruledetail.getId() != null && computerorderclassruledetail.getId() > 0){				
				ComputerorderclassruledetailFull temComputerorderclassruledetailFull = computerorderclassruledetailService.selectComputerorderclassruledetailFullById(computerorderclassruledetail.getId());
				BeanUtils.copyProperties(computerorderclassruledetailFull,temComputerorderclassruledetailFull);	
				actionMsg = getText("selectComputerorderclassruledetailByIdSuccess");
			}else{
				actionMsg = getText("selectComputerorderclassruledetailByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderclassruledetailAction的方法：selectComputerorderclassruledetailFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewComputerorderclassruledetail(){
		log.info("viewComputerorderclassruledetail");
		try {
			if(computerorderclassruledetail.getId() != null && computerorderclassruledetail.getId() > 0){				
				Computerorderclassruledetail temComputerorderclassruledetail = computerorderclassruledetailService.selectComputerorderclassruledetailById(computerorderclassruledetail.getId());
				BeanUtils.copyProperties(computerorderclassruledetailModel,temComputerorderclassruledetail);	
				actionMsg = getText("selectComputerorderclassruledetailByIdSuccess");
			}else{
				actionMsg = getText("selectComputerorderclassruledetailByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderclassruledetailAction的方法：selectComputerorderclassruledetailById错误"+e);
		}


		return "error";

	}	

/**
 * view ComputerorderclassruledetailFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewComputerorderclassruledetailFull() {
				
		try {
			int getId = computerorderclassruledetail.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			ComputerorderclassruledetailFull temComputerorderclassruledetailFull = computerorderclassruledetailService.selectComputerorderclassruledetailFullById(getId);				
			if(temComputerorderclassruledetailFull!=null){				
				BeanUtils.copyProperties(computerorderclassruledetailFull,temComputerorderclassruledetailFull);
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
	//根据allowedcomputermodelid 查询实体
	public String selectComputerorderclassruledetailByComputermodelId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerorderclassruledetailList  = computerorderclassruledetailService.selectComputerorderclassruledetailAll();
		for(int i = 0; i < computerorderclassruledetailList.size(); i++){
			System.out.println("id="+computerorderclassruledetailList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据allowedcomputermodelid 查询实体full
	public String selectComputerorderclassruledetailFullByComputermodelId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerorderclassruledetailFullList  = computerorderclassruledetailService.selectComputerorderclassruledetailFullByComputermodelId(userId);
		for(int i = 0; i < computerorderclassruledetailFullList.size(); i++){
			//System.out.println("id="+computerorderclassruledetailFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}
*/
	//get set
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	    this.session = session;
	}
	
	@Override
	public Computerorderclassruledetail getModel() {
		// TODO Auto-generated method stub
		return computerorderclassruledetail;
	}

//  
	public Computerorderclassruledetail getComputerorderclassruledetail() {
		return computerorderclassruledetail;
	}
	
	public void setComputerorderclassruledetail(Computerorderclassruledetail computerorderclassruledetail) {
		this.computerorderclassruledetail = computerorderclassruledetail;
	}
//  entityModel
	public Computerorderclassruledetail getComputerorderclassruledetailModel() {
		return computerorderclassruledetailModel;
	}
	
	public void setComputerorderclassruledetailModel(Computerorderclassruledetail computerorderclassruledetailModel) {
		this.computerorderclassruledetailModel = computerorderclassruledetailModel;
	}
	
	public ComputerorderclassruledetailFull getComputerorderclassruledetailFull() {
		return computerorderclassruledetailFull;
	}
	
	public void setComputerorderclassruledetailFull(ComputerorderclassruledetailFull computerorderclassruledetailFull) {
		this.computerorderclassruledetailFull = computerorderclassruledetailFull;
	}
	
	public List<Computerorderclassruledetail> getComputerorderclassruledetailList() {
		return computerorderclassruledetailList;
	}


	public void setComputerorderclassruledetailList(List<Computerorderclassruledetail> computerorderclassruledetailList) {
		this.computerorderclassruledetailList = computerorderclassruledetailList;
	}

	public List<ComputerorderclassruledetailFull> getComputerorderclassruledetailFullList() {
		return computerorderclassruledetailFullList;
	}


	public void setComputerorderclassruledetailFullList(List<ComputerorderclassruledetailFull> computerorderclassruledetailFullList) {
		this.computerorderclassruledetailFullList = computerorderclassruledetailFullList;
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
	
	public int getComputerorderclassruledetailid() {
		return computerorderclassruledetailid;
	}

	public void setComputerorderclassruledetailid(int computerorderclassruledetailid) {
		this.computerorderclassruledetailid = computerorderclassruledetailid;
	}
		public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
	
	public String getComputerorderclassruledetailIdsForDel() {
                return computerorderclassruledetailIdsForDel;
        }

        public void setComputerorderclassruledetailIdsForDel(String computerorderclassruledetailIdsForDel) {
                this.computerorderclassruledetailIdsForDel = computerorderclassruledetailIdsForDel;
        }
}
