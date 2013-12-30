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
import com.sbgl.app.services.computer.ComputerhomeworkService;
import com.sbgl.app.services.computer.ComputerhomeworkreceiverService;
import com.sbgl.app.services.computer.ComputerorderclassruledetailService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputerhomeworkAction")
public class ComputerhomeworkAction extends ActionSupport implements SessionAware,ModelDriven<Computerhomework>{
	
	private static final Log log = LogFactory.getLog(ComputerhomeworkAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private ComputerhomeworkService computerhomeworkService;
	
	private Computerhomework computerhomework = new Computerhomework();//实例化一个模型
	private Computerhomework computerhomeworkModel = new Computerhomework();//实例化一个模型
	private ComputerhomeworkFull computerhomeworkFull = new ComputerhomeworkFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Computerhomework> computerhomeworkList = new ArrayList<Computerhomework>();
	List<ComputerhomeworkFull> computerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();
	private Integer computerhomeworkid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";		
	Page page = new Page();
	Integer pageNo=1;	
	
	

	
//	add homework	
	private String receiverids;//接收者


// del 
	String computerhomeworkIdsForDel;
	
	
	
	
	@Resource
	private ComputerhomeworkreceiverService computerhomeworkreceiverService;
	private Computerhomeworkreceiver computerhomeworkreceiver = new Computerhomeworkreceiver();//实例化一个模型
	private ComputerhomeworkreceiverFull computerhomeworkreceiverFull = new ComputerhomeworkreceiverFull();//实例化一个模型
	private List<Computerhomeworkreceiver> computerhomeworkreceiverList = new ArrayList<Computerhomeworkreceiver>();
	private List<ComputerhomeworkreceiverFull> computerhomeworkreceiverFullList = new ArrayList<ComputerhomeworkreceiverFull>();
	
	@Resource
	private ComputerorderclassruledetailService computerorderclassruledetailService;	
	private Computerorderclassruledetail computerorderclassruledetail = new Computerorderclassruledetail();//实例化一个模型
	private Computerorderclassruledetail computerorderclassruledetailModel = new Computerorderclassruledetail();//实例化一个模型
	private ComputerorderclassruledetailFull computerorderclassruledetailFull = new ComputerorderclassruledetailFull();//实例化一个模型
	List<Computerorderclassruledetail> computerorderclassruledetailList = new ArrayList<Computerorderclassruledetail>();
	List<ComputerorderclassruledetailFull> computerorderclassruledetailFullList = new ArrayList<ComputerorderclassruledetailFull>();
	
	ReturnJson returnJson = new ReturnJson();
	
	//作业收件箱
	public String toComputerhomeworkInboxPage(){
//		log.info(logprefix +" toComputerhomeworkInboxPage");
		int userid = 1;
		
		String receivesql = " where a.userid ="+userid;
		computerhomeworkreceiverList = computerhomeworkreceiverService.selectComputerhomeworkreceiverByCondition(receivesql);
		
		if(computerhomeworkreceiverList!= null && computerhomeworkreceiverList.size() > 0){
//			select * from computerhomework as a where a.id in (1,2)
			String homeworksql = " where a.id ";
			String homeworkids = "";
			for(int i=0; i<computerhomeworkreceiverList.size();i++){
				homeworkids += computerhomeworkreceiverList.get(i).getComputerhomeworkid()+",";
			}
			homeworkids = homeworkids.substring(0,homeworkids.length()-1);
			
			homeworksql = homeworksql + " in (" +homeworkids+") ";
			System.out.println(homeworksql);
			computerhomeworkFullList  = computerhomeworkService.selectComputerhomeworkFullByCondition(homeworksql);
		}

		
		
		if(computerhomeworkreceiverList == null){
			computerhomeworkreceiverList = new ArrayList<Computerhomeworkreceiver>();
		}
		if(computerhomeworkFullList == null){
			 computerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();
		}
		
		return SUCCESS;
	}
	
	
	
//  manage Computerhomework
	public String manageComputerhomework(){
		log.info(logprefix+"manageComputerhomeworkFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computerhomeworkService.countComputerhomeworkRow());
		computerhomeworkList  = computerhomeworkService.selectComputerhomeworkByPage(page);
		
//		查询全部
//		computerhomeworkList  = computerhomeworkService.selectComputerhomeworkById();
		
	    if(computerhomeworkList == null){
			computerhomeworkList = new ArrayList<Computerhomework>();
		}
//		for(int i = 0; i < computerhomeworkList.size(); i++){
//			System.out.println("id="+computerhomeworkList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageComputerhomeworkFull(){
		log.info("exec action method:manageComputerhomeworkFull");
		
//      分页查询		
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computerhomeworkService.countComputerhomeworkRow());
		computerhomeworkFullList  = computerhomeworkService.selectComputerhomeworkFullByPage(page);
		
//		查询全部
//		computerhomeworkFullList  = computerhomeworkService.selectComputerhomeworkFullAll();

		if(computerhomeworkFullList == null){
			computerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();
		}
//		for(int i = 0; i < computerhomeworkFullList.size(); i++){
//			System.out.println("id="+computerhomeworkFullList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}			
			
		

			
	public String addComputerhomework(){	
		log.info("Add Entity");

		try {
			Computerhomework temp = new Computerhomework();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerhomework);			
			//add your code here.
			temp.setCreateuserid(0);
//			temp.setCreatetime(DateUtil.currentDate());
//			
//			computerhomeworkService.addComputerhomework(temp);
//			
//			String[] userIds = receiverUserIds.split(";");
//			for (int i = 0; i < userIds.length; i++) {
//				Computerhomeworkreceiver chr = new Computerhomeworkreceiver();
//				chr.setComputerhomeworkid(temp.getId());
//				chr.setUserid(Integer.valueOf(userIds[i]));
//				computerhomeworkreceiverService.addComputerhomeworkreceiver(chr);
//			}
//			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkAction的方法：addBbstagfavourite错误"+e);
		}
		return "Error";
	}
	
//  ajax add	
	public String addComputerhomeworkAjax(){	
		log.info("Add Entity Ajax Manner");
		
		ReturnJson returnJson = new ReturnJson();
		
		try {
			Computerhomework temp = new Computerhomework();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerhomework);			
			temp.setCreatetime(DateUtil.currentDate());
			
//			先添加homework，再添加接收者
			computerhomeworkService.addComputerhomework(temp);
			
			String[] userIds = receiverids.split(";");
			for (int i = 0; i < userIds.length; i++) {
				Computerhomeworkreceiver chr = new Computerhomeworkreceiver();
				chr.setComputerhomeworkid(temp.getId());
				chr.setUserid(Integer.valueOf(userIds[i]));
				computerhomeworkreceiverService.addComputerhomeworkreceiver(chr);
			}
			
			
			returnJson.setFlag(1);
			returnJson.setReason("添加成功");
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);
		returnJson.setReason("添加失败");
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}

//删除
	public String deleteComputerhomework( ){
		try{
			if(computerhomework.getId() != null && computerhomework.getId() > 0){
				computerhomeworkService.deleteComputerhomework(computerhomework.getId());
				actionMsg = getText("deleteComputerhomeworkSuccess");
			}else{
				System.out.println("删除的id不存在");
				actionMsg = getText("deleteComputerhomeworkFail");
			}
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkAction的方法：deleteComputerhomework错误"+e);
		}
		return "Error";
	}
	
//删除Ajax
	public String deleteComputerhomeworkAjax( ){
		try{
			if(computerhomework.getId() != null && computerhomework.getId() >= 0){
				computerhomeworkService.deleteComputerhomework(computerhomework.getId());				
			}
			
			return "IdNotExist";
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkAction的方法：deleteComputerhomework错误"+e);
		}
		return "Error";
	}

	
//	del entityfull
	public String deleteComputerhomeworkFull(){
		try {
		
			Integer getId = computerhomework.getId();
			if(getId != null && getId < 0){
				log.info("删除的id不规范");
				return "Error";
			}
		
		
			Computerhomework temp = computerhomeworkService.selectComputerhomeworkById(getId);
			if (temp != null) {
				computerhomeworkService.deleteComputerhomework(getId);
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
	public String deleteComputerhomeworkFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
             
		try{
			String ids[] = computerhomeworkIdsForDel.split(";");
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
                                Computerhomework temp = computerhomeworkService.selectComputerhomeworkById(tempDelId);                        
                                if (temp != null) {                        
                                        //其他操作                                        
                                        computerhomeworkService.deleteComputerhomework(tempDelId);                                        
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
	public String updateComputerhomework(){
		try {
			if(computerhomework.getId() != null && computerhomework.getId() > 0){				
				Computerhomework tempComputerhomework = computerhomeworkService.selectComputerhomeworkById(computerhomework.getId());
																				  								
												  								
												  								
												  								
												  								
												  								
												  								
								actionMsg = getText("viewComputerhomeworkSuccess");
			}else{
				actionMsg = getText("viewComputerhomeworkFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkAction的方法：viewComputerhomework错误"+e);
		}

		return "error";
	}
	
	
	//ajax 修改
	public String updateComputerhomeworkAjax(){
		log.info(logprefix + "updateComputerhomeworkAjax,id="+computerhomework.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(computerhomework.getId() != null && computerhomework.getId() > 0){				
				Computerhomework tempComputerhomework = computerhomeworkService.selectComputerhomeworkById(computerhomework.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempComputerhomework.setName(computerhomework.getName());
  				tempComputerhomework.setComputerorderclassruleid(computerhomework.getComputerorderclassruleid());
  				tempComputerhomework.setContent(computerhomework.getContent());
  				tempComputerhomework.setCreateuserid(computerhomework.getCreateuserid());
  				tempComputerhomework.setAttachment(computerhomework.getAttachment());
  				tempComputerhomework.setStatus(computerhomework.getStatus());
  				tempComputerhomework.setCreatetime(computerhomework.getCreatetime());
 
				
				computerhomeworkService.updateComputerhomework(tempComputerhomework);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewComputerhomeworkSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewComputerhomeworkFail");
				log.info(logprefix + "updateComputerhomeworkAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkAction的方法：viewComputerhomework错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	
	/**
	
	编辑实体 action的方法，首先获取entity的信息，返回到编辑页面
	
	*/
	public String editComputerhomework(){
		log.info(logprefix + "editComputerhomework");
			
		try {
			//实体的id可以为0
			if(computerhomework.getId() != null && computerhomework.getId() >= 0){				
				Computerhomework temComputerhomework = computerhomeworkService.selectComputerhomeworkById(computerhomework.getId());
				if(temComputerhomework != null){
					BeanUtils.copyProperties(computerhomeworkModel,temComputerhomework);	
					//actionMsg = getText("selectComputerhomeworkByIdSuccess");
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
			log.error("类ComputerhomeworkAction的方法：selectComputerhomeworkById错误"+e);
		}


		return "error";
	}
	

	/**
	编辑实体Full action的方法，首先获取entityfull的信息，返回到编辑页面
	
	*/
	public String editComputerhomeworkFull(){
		
		log.info(logprefix + "viewComputerhomework");
		
		try {
			if(computerhomework.getId() != null && computerhomework.getId() > 0){				
				ComputerhomeworkFull temComputerhomeworkFull = computerhomeworkService.selectComputerhomeworkFullById(computerhomework.getId());
				BeanUtils.copyProperties(computerhomeworkFull,temComputerhomeworkFull);	
				actionMsg = getText("selectComputerhomeworkByIdSuccess");
			}else{
				actionMsg = getText("selectComputerhomeworkByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkAction的方法：selectComputerhomeworkFullById错误"+e);
		}
		
		return "error";
	}

	
	// 查看实体 根据对象Id查询
	public String viewComputerhomework(){
		log.info("viewComputerhomework");
		try {
			if(computerhomework.getId() != null && computerhomework.getId() > 0){				
				Computerhomework temComputerhomework = computerhomeworkService.selectComputerhomeworkById(computerhomework.getId());
				BeanUtils.copyProperties(computerhomeworkModel,temComputerhomework);	
				actionMsg = getText("selectComputerhomeworkByIdSuccess");
			}else{
				actionMsg = getText("selectComputerhomeworkByIdFail");
				System.out.println(actionMsg);
			}			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkAction的方法：selectComputerhomeworkById错误"+e);
		}


		return "error";

	}	

/**
 * view ComputerhomeworkFull
 * need give parmeter id
 * get id from modle,
 * @return
 */
	public String viewComputerhomeworkFull() {
				
		try {
			
			if (computerhomeworkid <= 0) {
				log.error("error,id小于0不规范");
				return "error";
			}	
			
			String condition = " where a.id = "+computerhomeworkid;
			List<ComputerhomeworkFull> tempList = computerhomeworkService.selectComputerhomeworkFullByCondition(condition );
			
			if(tempList!=null && tempList.size() >0){
				
			}else{
				return "error";
			}
			
			computerhomeworkFull = tempList.get(0);
//			System.out.println(tempList.size() + " "+computerhomeworkFull.getComputerorderclassruleid());
			
//			查询作业可以借的PC
			int ruleId = computerhomeworkFull.getComputerorderclassruleid();
			if(ruleId > 0){
				String borrowPcSql  = " where a.id = "+ ruleId;
				computerorderclassruledetailFullList = computerorderclassruledetailService.selectComputerorderclassruledetailFullByCondition(borrowPcSql);				
			}
			
			if(computerorderclassruledetailFullList == null){
				computerorderclassruledetailFullList = new ArrayList<ComputerorderclassruledetailFull>();
			}
			
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();			
		}
		return "Error";
	}
/*
	//根据computerorderclassruleid 查询实体
	public String selectComputerhomeworkByComputerorderclassruleId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerhomeworkList  = computerhomeworkService.selectComputerhomeworkAll();
		for(int i = 0; i < computerhomeworkList.size(); i++){
			System.out.println("id="+computerhomeworkList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据computerorderclassruleid 查询实体full
	public String selectComputerhomeworkFullByComputerorderclassruleId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerhomeworkFullList  = computerhomeworkService.selectComputerhomeworkFullByComputerorderclassruleId(userId);
		for(int i = 0; i < computerhomeworkFullList.size(); i++){
			//System.out.println("id="+computerhomeworkFullList.get(i).getLoginusername());
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
	public Computerhomework getModel() {
		// TODO Auto-generated method stub
		return computerhomework;
	}

//  
	public Computerhomework getComputerhomework() {
		return computerhomework;
	}
	
	public void setComputerhomework(Computerhomework computerhomework) {
		this.computerhomework = computerhomework;
	}
//  entityModel
	public Computerhomework getComputerhomeworkModel() {
		return computerhomeworkModel;
	}
	
	public void setComputerhomeworkModel(Computerhomework computerhomeworkModel) {
		this.computerhomeworkModel = computerhomeworkModel;
	}
	
	public ComputerhomeworkFull getComputerhomeworkFull() {
		return computerhomeworkFull;
	}
	
	public void setComputerhomeworkFull(ComputerhomeworkFull computerhomeworkFull) {
		this.computerhomeworkFull = computerhomeworkFull;
	}
	
	public List<Computerhomework> getComputerhomeworkList() {
		return computerhomeworkList;
	}


	public void setComputerhomeworkList(List<Computerhomework> computerhomeworkList) {
		this.computerhomeworkList = computerhomeworkList;
	}

	public List<ComputerhomeworkFull> getComputerhomeworkFullList() {
		return computerhomeworkFullList;
	}


	public void setComputerhomeworkFullList(List<ComputerhomeworkFull> computerhomeworkFullList) {
		this.computerhomeworkFullList = computerhomeworkFullList;
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
	
	public int getComputerhomeworkid() {
		return computerhomeworkid;
	}

	public void setComputerhomeworkid(int computerhomeworkid) {
		this.computerhomeworkid = computerhomeworkid;
	}
		public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
	
	public String getComputerhomeworkIdsForDel() {
                return computerhomeworkIdsForDel;
        }

        public void setComputerhomeworkIdsForDel(String computerhomeworkIdsForDel) {
                this.computerhomeworkIdsForDel = computerhomeworkIdsForDel;
        }

		public ComputerhomeworkService getComputerhomeworkService() {
			return computerhomeworkService;
		}

		public void setComputerhomeworkService(
				ComputerhomeworkService computerhomeworkService) {
			this.computerhomeworkService = computerhomeworkService;
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

		public ComputerhomeworkreceiverService getComputerhomeworkreceiverService() {
			return computerhomeworkreceiverService;
		}

		public void setComputerhomeworkreceiverService(
				ComputerhomeworkreceiverService computerhomeworkreceiverService) {
			this.computerhomeworkreceiverService = computerhomeworkreceiverService;
		}

	

		public String getReceiverids() {
			return receiverids;
		}



		public void setReceiverids(String receiverids) {
			this.receiverids = receiverids;
		}



		public ReturnJson getReturnJson() {
			return returnJson;
		}

		public void setReturnJson(ReturnJson returnJson) {
			this.returnJson = returnJson;
		}

		public static Log getLog() {
			return log;
		}

		public Map<String, Object> getSession() {
			return session;
		}

		public void setComputerhomeworkid(Integer computerhomeworkid) {
			this.computerhomeworkid = computerhomeworkid;
		}



		public Computerhomeworkreceiver getComputerhomeworkreceiver() {
			return computerhomeworkreceiver;
		}



		public void setComputerhomeworkreceiver(
				Computerhomeworkreceiver computerhomeworkreceiver) {
			this.computerhomeworkreceiver = computerhomeworkreceiver;
		}



		public ComputerhomeworkreceiverFull getComputerhomeworkreceiverFull() {
			return computerhomeworkreceiverFull;
		}



		public void setComputerhomeworkreceiverFull(
				ComputerhomeworkreceiverFull computerhomeworkreceiverFull) {
			this.computerhomeworkreceiverFull = computerhomeworkreceiverFull;
		}



		public List<Computerhomeworkreceiver> getComputerhomeworkreceiverList() {
			return computerhomeworkreceiverList;
		}



		public void setComputerhomeworkreceiverList(
				List<Computerhomeworkreceiver> computerhomeworkreceiverList) {
			this.computerhomeworkreceiverList = computerhomeworkreceiverList;
		}



		public List<ComputerhomeworkreceiverFull> getComputerhomeworkreceiverFullList() {
			return computerhomeworkreceiverFullList;
		}



		public void setComputerhomeworkreceiverFullList(
				List<ComputerhomeworkreceiverFull> computerhomeworkreceiverFullList) {
			this.computerhomeworkreceiverFullList = computerhomeworkreceiverFullList;
		}



		public ComputerorderclassruledetailService getComputerorderclassruledetailService() {
			return computerorderclassruledetailService;
		}



		public void setComputerorderclassruledetailService(
				ComputerorderclassruledetailService computerorderclassruledetailService) {
			this.computerorderclassruledetailService = computerorderclassruledetailService;
		}



		public Computerorderclassruledetail getComputerorderclassruledetail() {
			return computerorderclassruledetail;
		}



		public void setComputerorderclassruledetail(
				Computerorderclassruledetail computerorderclassruledetail) {
			this.computerorderclassruledetail = computerorderclassruledetail;
		}



		public Computerorderclassruledetail getComputerorderclassruledetailModel() {
			return computerorderclassruledetailModel;
		}



		public void setComputerorderclassruledetailModel(
				Computerorderclassruledetail computerorderclassruledetailModel) {
			this.computerorderclassruledetailModel = computerorderclassruledetailModel;
		}



		public ComputerorderclassruledetailFull getComputerorderclassruledetailFull() {
			return computerorderclassruledetailFull;
		}



		public void setComputerorderclassruledetailFull(
				ComputerorderclassruledetailFull computerorderclassruledetailFull) {
			this.computerorderclassruledetailFull = computerorderclassruledetailFull;
		}



		public List<Computerorderclassruledetail> getComputerorderclassruledetailList() {
			return computerorderclassruledetailList;
		}



		public void setComputerorderclassruledetailList(
				List<Computerorderclassruledetail> computerorderclassruledetailList) {
			this.computerorderclassruledetailList = computerorderclassruledetailList;
		}



		public List<ComputerorderclassruledetailFull> getComputerorderclassruledetailFullList() {
			return computerorderclassruledetailFullList;
		}



		public void setComputerorderclassruledetailFullList(
				List<ComputerorderclassruledetailFull> computerorderclassruledetailFullList) {
			this.computerorderclassruledetailFullList = computerorderclassruledetailFullList;
		}
		
		
        
}
