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
	

	List<Computerhomework> computerhomeworkList = new ArrayList<Computerhomework>();
	List<ComputerhomeworkFull> computerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();
	private Integer computerhomeworkid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";		
	Page page = new Page();
	Integer pageNo=1;	
	
	private List<ComputerhomeworkFull> newComputerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();
	private List<ComputerhomeworkFull> finishComputerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();

	
//	add homework	
	private String receiverids;//接收者


// del 
	String computerhomeworkIdsForDel;
	
	
	
	
	@Resource
	private ComputerhomeworkreceiverService computerhomeworkreceiverService;
	private Computerhomeworkreceiver computerhomeworkreceiver = new Computerhomeworkreceiver();
	private ComputerhomeworkreceiverFull computerhomeworkreceiverFull = new ComputerhomeworkreceiverFull();
	private List<Computerhomeworkreceiver> computerhomeworkreceiverList = new ArrayList<Computerhomeworkreceiver>();
	private List<ComputerhomeworkreceiverFull> computerhomeworkreceiverFullList = new ArrayList<ComputerhomeworkreceiverFull>();
	
	
	
	@Resource
	private ComputerorderclassruledetailService computerorderclassruledetailService;	
	private Computerorderclassruledetail computerorderclassruledetail = new Computerorderclassruledetail();//实例化一个模型
	private Computerorderclassruledetail computerorderclassruledetailModel = new Computerorderclassruledetail();//实例化一个模型
	private ComputerorderclassruledetailFull computerorderclassruledetailFull = new ComputerorderclassruledetailFull();//实例化一个模型
	List<Computerorderclassruledetail> computerorderclassruledetailList = new ArrayList<Computerorderclassruledetail>();
	List<ComputerorderclassruledetailFull> computerorderclassruledetailFullList = new ArrayList<ComputerorderclassruledetailFull>();
	
	
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	private String returnInfo;
	private String actionMsg; // Action间传递的消息参数
	
	
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
	
//	学生查看作业收件箱
	public String toComputerhomeworkInboxPage(){
		log.info(logprefix +" toComputerhomeworkInboxPage");
		
		Integer uid = checkUserLogin();
		log.info("login user id "+ uid);
		if(uid < 0){
			returnInfo = "用户未登录";
			buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
			return SUCCESS;
		}
		
		String receivesql = " where a.userid ="+uid ;
		computerhomeworkreceiverList = computerhomeworkreceiverService.selectComputerhomeworkreceiverByCondition(receivesql);
		
		String newhomeworksql =  "";
		String finishehomeworksql = "";
		if(computerhomeworkreceiverList == null || computerhomeworkreceiverList.size() == 0){
			if(newComputerhomeworkFullList == null){
				newComputerhomeworkFullList =  new ArrayList<ComputerhomeworkFull>();
			}
			if(finishComputerhomeworkFullList == null){
				finishComputerhomeworkFullList= new ArrayList<ComputerhomeworkFull>();
			}
			
			return SUCCESS;
		}
			
		for(int i=0; i<computerhomeworkreceiverList.size();i++){
			if(computerhomeworkreceiverList.get(i).getHasorder()==null || computerhomeworkreceiverList.get(i).getHasorder() != 1){
					newhomeworksql += computerhomeworkreceiverList.get(i).getComputerhomeworkid()+",";
			}else{
				finishehomeworksql += computerhomeworkreceiverList.get(i).getComputerhomeworkid()+",";
			}
		}
		
			if(finishehomeworksql.length() > 1){
				finishehomeworksql = finishehomeworksql.substring(0,finishehomeworksql.length()-1);
				finishehomeworksql = " where a.id in (" +finishehomeworksql+") "  + " order by computerhomeworkcreatetime desc ";
				finishComputerhomeworkFullList = computerhomeworkService.selectComputerhomeworkFullByCondition(finishehomeworksql);
			}
			
			if(newhomeworksql.length() > 1){
				newhomeworksql = newhomeworksql.substring(0,newhomeworksql.length()-1);
				newhomeworksql = " where a.id in (" +newhomeworksql+") "  + " order by computerhomeworkcreatetime desc ";
				newComputerhomeworkFullList = computerhomeworkService.selectComputerhomeworkFullByCondition(newhomeworksql);
			}
			
			
			if(newComputerhomeworkFullList == null){
				newComputerhomeworkFullList =  new ArrayList<ComputerhomeworkFull>();
			}
			if(finishComputerhomeworkFullList == null){
				finishComputerhomeworkFullList= new ArrayList<ComputerhomeworkFull>();
			}
		
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
				chr.setHasorder(0);
				chr.setStatus(0);
				chr.setHasview(0);
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


	//del entityfull Ajax
	public String deleteComputerhomeworkFullAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
		ReturnJson returnJson = new ReturnJson();
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


	/**
	 * 学生查看作业信息
	 * view ComputerhomeworkFull
	 * need give parmeter id
	 * get id from modle,
	 * @return
	 */
		
	public String checkComputerhomework() {
					
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
//				System.out.println(tempList.size() + " "+computerhomeworkFull.getComputerorderclassruleid());
				
//				查询作业可以借的PC
				int ruleId = computerhomeworkFull.getComputerorderclassruleid();
				if(ruleId > 0){
					String borrowPcSql  = " where a.computerorderclassruleid = "+ ruleId+ " and b.languagetype = 0";
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


	/**
	 * view ComputerhomeworkFull
	 * need give parmeter id
	 * get id from modle,
	 * @return
	 */
	public String viewComputerhomeworkFull() {
				
		try {
			String condition = " where a.id = "+computerhomeworkid;
			List<ComputerhomeworkFull> tempList = computerhomeworkService.selectComputerhomeworkFullByCondition(condition );
			
			if(tempList!=null && tempList.size() >0){
				
			}else{
				actionMsg = "访问的PC作业不存在";
				return ComputerConfig.pagenotfound;
			}
			
			computerhomeworkFull = tempList.get(0);
//			System.out.println(tempList.size() + " "+computerhomeworkFull.getComputerorderclassruleid());
			
//			查询作业可以借的PC
			int ruleId = computerhomeworkFull.getComputerorderclassruleid();
			String borrowPcSql  = " where a.computerorderclassruleid = "+ ruleId + " and b.languagetype = "+this.getCurrentLanguage();
			computerorderclassruledetailFullList = computerorderclassruledetailService.selectComputerorderclassruledetailFullByCondition(borrowPcSql);				

			if(computerorderclassruledetailFullList == null){
				actionMsg = "获取作业规则错误";
				return ComputerConfig.pagenotfound;
			}
			
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		actionMsg = "系统内部发生错误";
		return ComputerConfig.innererror;
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



		public List<ComputerhomeworkFull> getNewComputerhomeworkFullList() {
			return newComputerhomeworkFullList;
		}



		public void setNewComputerhomeworkFullList(
				List<ComputerhomeworkFull> newComputerhomeworkFullList) {
			this.newComputerhomeworkFullList = newComputerhomeworkFullList;
		}



		public List<ComputerhomeworkFull> getFinishComputerhomeworkFullList() {
			return finishComputerhomeworkFullList;
		}



		public void setFinishComputerhomeworkFullList(
				List<ComputerhomeworkFull> finishComputerhomeworkFullList) {
			this.finishComputerhomeworkFullList = finishComputerhomeworkFullList;
		}

		public String getReturnInfo() {
			return returnInfo;
		}

		public void setReturnInfo(String returnInfo) {
			this.returnInfo = returnInfo;
		}
		
		
        
}
