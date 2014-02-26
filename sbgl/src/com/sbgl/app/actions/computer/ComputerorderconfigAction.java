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
import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.computer.ComputerorderconfigService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputerorderconfigAction")
public class ComputerorderconfigAction extends BaseAction implements ModelDriven<Computerorderconfig>{
	
	private static final Log log = LogFactory.getLog(ComputerorderconfigAction.class);


	//Service	
	@Resource
	private ComputerorderconfigService computerorderconfigService;
	
	private Computerorderconfig computerorderconfig = new Computerorderconfig();//实例化一个模型
	private Computerorderconfig computerorderconfigModel = new Computerorderconfig();//实例化一个模型
	private ComputerorderconfigFull computerorderconfigFull = new ComputerorderconfigFull();//实例化一个模型

	List<Computerorderconfig> computerorderconfigList = new ArrayList<Computerorderconfig>();
	List<ComputerorderconfigFull> computerorderconfigFullList = new ArrayList<ComputerorderconfigFull>();
	private Integer computerorderconfigid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";
	

	private String computerorderconfigIdsForDel;
	
	
	public String toSetComputerorderconfig(){
		String sql = " where currentconfig = 1";
		computerorderconfigList = computerorderconfigService.selectComputerorderconfigByCondition(sql);
		
		if(computerorderconfigList == null || computerorderconfigList.size()==0){
			computerorderconfig.setOpenorder(1);
			computerorderconfig.setOrderperiod("111");
			computerorderconfig.setMaxorderday(14);
			this.actionMsg = "默认配置";
		}else{
			computerorderconfig = computerorderconfigList.get(0);
		}
		
		return SUCCESS;
	}
			
	//管理 查询
	public String manageComputerorderconfigFull(){
		log.info("exec action method:manageComputerorderconfigFull");
                
//      分页查询        
        if(pageNo <= 0){
			pageNo =1;
        }
        page.setTotalCount(computerorderconfigService.countComputerorderconfigRow());
        if(page.getTotalpage() < pageNo){
			pageNo = page.getTotalpage();
        }
        page.setPageNo(pageNo);
                
        computerorderconfigFullList  = computerorderconfigService.selectComputerorderconfigFullByConditionAndPage("", page);
               

        if(computerorderconfigFullList == null){
			computerorderconfigFullList = new ArrayList<ComputerorderconfigFull>();
        }
//      for(int i = 0; i < computerhomeworkFullList.size(); i++){
//      	System.out.println("id=");
//      }
        if(callType!=null&&callType.equals("ajaxType")){
			return "success2";
        }else{
           return "success1";
        }	
	}			

			

//  ajax add	
	public String addComputerorderconfigAjax(){	
		log.info("Add Entity Ajax Manner");
		
		try {
			Computerorderconfig temp = new Computerorderconfig();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerorderconfig);			

			int bookperiodnum = 0;
			for (int i = 0; i < temp.getOrderperiod().length(); i++) {
				char c = temp.getOrderperiod().charAt(i);
				if(c=='1'){
					bookperiodnum++;
				}				
			}
			
			temp.setOrderperiodnum(bookperiodnum);
			temp.setCreateuserid(this.getCurrentUserId());
			temp.setCreatetime(DateUtil.currentDate());
			temp.setCurrentconfig(1);
			
			computerorderconfigService.addComputerorderconfig(temp);
		
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, "设置成功");
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderconfigAction的方法：addBbstagfavourite错误"+e);
		}
		
		this.returnInfo = "内部错误";
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		return SUCCESS;
	}

//删除
	public String deleteComputerorderconfig( ){
		try{
			if(computerorderconfig.getId() != null && computerorderconfig.getId() > 0){
				computerorderconfigService.deleteComputerorderconfig(computerorderconfig.getId());
				actionMsg = getText("deleteComputerorderconfigSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteComputerorderconfigFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderconfigAction的方法：deleteComputerorderconfig错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteComputerorderconfigAjax( ){
		try{
			if(computerorderconfig.getId() != null && computerorderconfig.getId() >= 0){
				computerorderconfigService.deleteComputerorderconfig(computerorderconfig.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderconfigAction的方法：deleteComputerorderconfig错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteComputerorderconfigFull(){
		try {
		
			Integer getId = computerorderconfig.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Computerorderconfig temp = computerorderconfigService.selectComputerorderconfigById(getId);
			if (temp != null) {
				computerorderconfigService.deleteComputerorderconfig(getId);
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
	public String deleteComputerorderconfigFullAjax( ){
		/*
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			String ids[] = computerorderconfigIdsForDel.split(";");
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
                                Computerorderconfig temp = computerorderconfigService.selectComputerorderconfigById(tempDelId);                        
                                if (temp != null) {                        
                                        //其他操作                                        
                                        computerorderconfigService.deleteComputerorderconfig(tempDelId);                                        
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
                        returnJson.setReason("删除成功");
                        JSONObject jo = JSONObject.fromObject(returnJson);
                        this.returnStr = jo.toString();
                        return SUCCESS;
                        
                } catch (Exception e) {
                        e.printStackTrace();
                }
                
                returnJson.setFlag(0);
                returnJson.setReason("删除的内部错误");
                JSONObject jo = JSONObject.fromObject(returnJson);
                this.returnStr = jo.toString();*/
                return SUCCESS;
        }

//修改
	public String updateComputerorderconfig(){
		try {
			if(computerorderconfig.getId() != null && computerorderconfig.getId() > 0){				
				Computerorderconfig tempComputerorderconfig = computerorderconfigService.selectComputerorderconfigById(computerorderconfig.getId());
																				  								
												  								
												  								
												  								
												  								
												  								
												  								
												  								
												  								
								actionMsg = getText("viewComputerorderconfigSuccess");
			}else{
				actionMsg = getText("viewComputerorderconfigFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderconfigAction的方法：viewComputerorderconfig错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateComputerorderconfigAjax(){
		log.info(logprefix + "updateComputerorderconfigAjax,id="+computerorderconfig.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(computerorderconfig.getId() != null && computerorderconfig.getId() > 0){				
				Computerorderconfig tempComputerorderconfig = computerorderconfigService.selectComputerorderconfigById(computerorderconfig.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempComputerorderconfig.setOpenorder(computerorderconfig.getOpenorder());
  				tempComputerorderconfig.setOrderperiod(computerorderconfig.getOrderperiod());
  				tempComputerorderconfig.setOrderperiodnum(computerorderconfig.getOrderperiodnum());
  				tempComputerorderconfig.setMaxorderday(computerorderconfig.getMaxorderday());
  				tempComputerorderconfig.setOrderintroduction(computerorderconfig.getOrderintroduction());
  				tempComputerorderconfig.setCreateuserid(computerorderconfig.getCreateuserid());
  				tempComputerorderconfig.setCreatetime(computerorderconfig.getCreatetime());
  				tempComputerorderconfig.setCurrentconfig(computerorderconfig.getCurrentconfig());
  				tempComputerorderconfig.setStatus(computerorderconfig.getStatus());
 
				
				computerorderconfigService.updateComputerorderconfig(tempComputerorderconfig);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewComputerorderconfigSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewComputerorderconfigFail");
				log.info(logprefix + "updateComputerorderconfigAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderconfigAction的方法：viewComputerorderconfig错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editComputerorderconfig(){
		log.info(logprefix + "editComputerorderconfig");
			
		try {
			//实体的id可以为0
			if(computerorderconfig.getId() != null && computerorderconfig.getId() >= 0){				
				Computerorderconfig temComputerorderconfig = computerorderconfigService.selectComputerorderconfigById(computerorderconfig.getId());
				if(temComputerorderconfig != null){
					BeanUtils.copyProperties(computerorderconfigModel,temComputerorderconfig);	
					//actionMsg = getText("selectComputerorderconfigByIdSuccess");
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
			log.error("类ComputerorderconfigAction的方法：selectComputerorderconfigById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editComputerorderconfigFull(){
		
		log.info(logprefix + "viewComputerorderconfig");
		
		try {
			if(computerorderconfig.getId() != null && computerorderconfig.getId() > 0){				
				ComputerorderconfigFull temComputerorderconfigFull = computerorderconfigService.selectComputerorderconfigFullById(computerorderconfig.getId());
				BeanUtils.copyProperties(computerorderconfigFull,temComputerorderconfigFull);	
				actionMsg = getText("selectComputerorderconfigByIdSuccess");
			}else{
				actionMsg = getText("selectComputerorderconfigByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderconfigAction的方法：selectComputerorderconfigFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewComputerorderconfig(){
		log.info("viewComputerorderconfig");
		try {
			if(computerorderconfig.getId() != null && computerorderconfig.getId() > 0){				
				Computerorderconfig temComputerorderconfig = computerorderconfigService.selectComputerorderconfigById(computerorderconfig.getId());
				BeanUtils.copyProperties(computerorderconfigModel,temComputerorderconfig);	
				actionMsg = getText("selectComputerorderconfigByIdSuccess");
			}else{
				actionMsg = getText("selectComputerorderconfigByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderconfigAction的方法：selectComputerorderconfigById错误"+e);
		}


		return "error";

	}	

/**
 * view ComputerorderconfigFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewComputerorderconfigFull() {
				
		try {
			int getId = computerorderconfig.getId();
			log.info(this.logprefix + ";id=" + getId);
			
			if (getId < 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			ComputerorderconfigFull temComputerorderconfigFull = computerorderconfigService.selectComputerorderconfigFullById(getId);				
			if(temComputerorderconfigFull!=null){				
				BeanUtils.copyProperties(computerorderconfigFull,temComputerorderconfigFull);
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
	//根据createuserid查询实体
	public String selectComputerorderconfigByLoginuserId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerorderconfigList  = computerorderconfigService.selectComputerorderconfigAll();
		for(int i = 0; i < computerorderconfigList.size(); i++){
			System.out.println("id="+computerorderconfigList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据createuserid查询实体full
	public String selectComputerorderconfigFullByLoginuserId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerorderconfigFullList  = computerorderconfigService.selectComputerorderconfigFullByLoginuserId(userId);
		for(int i = 0; i < computerorderconfigFullList.size(); i++){
			//System.out.println("id="+computerorderconfigFullList.get(i).getLoginusername());
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
	public Computerorderconfig getModel() {
		// TODO Auto-generated method stub
		return computerorderconfig;
	}

//  
	public Computerorderconfig getComputerorderconfig() {
		return computerorderconfig;
	}
	
	public void setComputerorderconfig(Computerorderconfig computerorderconfig) {
		this.computerorderconfig = computerorderconfig;
	}
//  entityModel
	public Computerorderconfig getComputerorderconfigModel() {
		return computerorderconfigModel;
	}
	
	public void setComputerorderconfigModel(Computerorderconfig computerorderconfigModel) {
		this.computerorderconfigModel = computerorderconfigModel;
	}
	
	public ComputerorderconfigFull getComputerorderconfigFull() {
		return computerorderconfigFull;
	}
	
	public void setComputerorderconfigFull(ComputerorderconfigFull computerorderconfigFull) {
		this.computerorderconfigFull = computerorderconfigFull;
	}
	
	public List<Computerorderconfig> getComputerorderconfigList() {
		return computerorderconfigList;
	}


	public void setComputerorderconfigList(List<Computerorderconfig> computerorderconfigList) {
		this.computerorderconfigList = computerorderconfigList;
	}

	public List<ComputerorderconfigFull> getComputerorderconfigFullList() {
		return computerorderconfigFullList;
	}


	public void setComputerorderconfigFullList(List<ComputerorderconfigFull> computerorderconfigFullList) {
		this.computerorderconfigFullList = computerorderconfigFullList;
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
	
	public int getComputerorderconfigid() {
		return computerorderconfigid;
	}

	public void setComputerorderconfigid(int computerorderconfigid) {
		this.computerorderconfigid = computerorderconfigid;
	}
		public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	public String getComputerorderconfigIdsForDel() {
                return computerorderconfigIdsForDel;
        }

        public void setComputerorderconfigIdsForDel(String computerorderconfigIdsForDel) {
                this.computerorderconfigIdsForDel = computerorderconfigIdsForDel;
        }
}
