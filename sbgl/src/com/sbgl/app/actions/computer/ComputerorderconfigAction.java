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
	private List<Computerorderconfig> computerorderconfigList = new ArrayList<Computerorderconfig>();
//	private ComputerorderconfigFull computerorderconfigFull = new ComputerorderconfigFull();//实例化一个模型
//	private List<ComputerorderconfigFull> computerorderconfigFullList = new ArrayList<ComputerorderconfigFull>();
	
	
	private String logprefix = "exec action method:";
	
//	private Integer computerorderconfigid; //entity full 的id属性名称	
//	private String computerorderconfigIdsForDel;
	
	
	public String toSetComputerorderconfig(){
		String sql = " where currentconfig = 1";
		computerorderconfigList = computerorderconfigService.selectComputerorderconfigByCondition(sql);
		
		if(computerorderconfigList == null || computerorderconfigList.size()==0){
			computerorderconfig = ComputerActionUtil.getDefaultComputerorderconfig();
			
			this.actionMsg = "默认配置";
		}else{
			computerorderconfig = computerorderconfigList.get(0);
		}
		System.out.println(computerorderconfig.getOrderperiod());
		return SUCCESS;
	}
			
	//管理 查询
	/*
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
*/
			

//  ajax add	
	/**
	 * 保存时需要将其它的配置的当前状态设置为0
	 */
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


	@Override
	public Computerorderconfig getModel() {
		// TODO Auto-generated method stub
		return computerorderconfig;
	}

	public ComputerorderconfigService getComputerorderconfigService() {
		return computerorderconfigService;
	}

	public void setComputerorderconfigService(
			ComputerorderconfigService computerorderconfigService) {
		this.computerorderconfigService = computerorderconfigService;
	}

	public Computerorderconfig getComputerorderconfig() {
		return computerorderconfig;
	}

	public void setComputerorderconfig(Computerorderconfig computerorderconfig) {
		this.computerorderconfig = computerorderconfig;
	}

	public List<Computerorderconfig> getComputerorderconfigList() {
		return computerorderconfigList;
	}

	public void setComputerorderconfigList(
			List<Computerorderconfig> computerorderconfigList) {
		this.computerorderconfigList = computerorderconfigList;
	}

	public String getLogprefix() {
		return logprefix;
	}

	public void setLogprefix(String logprefix) {
		this.logprefix = logprefix;
	}

	public static Log getLog() {
		return log;
	}
	
	
	
	
	
	
}
