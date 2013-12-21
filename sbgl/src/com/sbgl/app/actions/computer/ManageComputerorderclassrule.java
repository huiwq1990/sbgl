package com.sbgl.app.actions.computer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.entity.Computerorderclassrule;
import com.sbgl.app.entity.ComputerorderclassruleFull;
import com.sbgl.app.entity.Computerorderclassruledetail;
import com.sbgl.app.entity.ComputerorderclassruledetailFull;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.services.computer.ComputerorderclassruleService;
import com.sbgl.app.services.computer.ComputerorderclassruledetailService;
import com.sbgl.util.Page;
import com.sbgl.util.ReturnJson;

@Scope("prototype") 
@Controller("ManageComputerorderclassrule")
public class ManageComputerorderclassrule extends ActionSupport implements SessionAware{

	private static final Log log = LogFactory.getLog(ManageComputerorderclassrule.class);
	private Map<String, Object> session;
	
	//Service	
	@Resource
	private ComputerorderclassruledetailService computerorderclassruledetailService;
	private Integer computerorderclassruledetailid; //entity full 的id属性名称	
	private Computerorderclassruledetail computerorderclassruledetail = new Computerorderclassruledetail();//实例化一个模型
	private ComputerorderclassruledetailFull computerorderclassruledetailFull = new ComputerorderclassruledetailFull();//实例化一个模型
	List<Computerorderclassruledetail> computerorderclassruledetailList = new ArrayList<Computerorderclassruledetail>();
	List<ComputerorderclassruledetailFull> computerorderclassruledetailFullList = new ArrayList<ComputerorderclassruledetailFull>();
	
	
	@Resource
	private ComputerorderclassruleService computerorderclassruleService;
	
	private Integer computerorderclassruleid; //entity full 的id属性名称		
	private Computerorderclassrule computerorderclassrule = new Computerorderclassrule();//实例化一个模型
	private ComputerorderclassruleFull computerorderclassruleFull = new ComputerorderclassruleFull();//实例化一个模型
	List<Computerorderclassrule> computerorderclassruleList = new ArrayList<Computerorderclassrule>();
	List<ComputerorderclassruleFull> computerorderclassruleFullList = new ArrayList<ComputerorderclassruleFull>();
	
	
	
	private String logprefix = "exec action method:";		
	
	
	Page page = new Page();
	Integer pageNo=1;	
	ReturnJson returnJson = new ReturnJson();
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	
	
	/**
	 * 查看规则详情
	 * @return
	 */
	public String viewComputerorderclassruleFull(){
		
		System.out.println("viewComputerorderclassrule "+ computerorderclassruleid);
//		检查规则是否存在
		computerorderclassruleFull = computerorderclassruleService.selectComputerorderclassruleFullById(computerorderclassruleid);
		//如果找不到相应的预约单，返回错误
		if(computerorderclassruleFull == null){
			log.info("viewComputerorderclassrule 规则不存在");
			return "PageNotFound";
		}
		
		String sql = " where a.computerorderclassruleid = "+computerorderclassruleid +" and  b.languagetype="+ComputerConfig.languagech+" " ;
		computerorderclassruledetailFullList = computerorderclassruledetailService.selectComputerorderclassruledetailFullByCondition(sql);
//		System.out.println("computerorderdetailFullList size:"+computerorderdetailFullList.size());
		
		if(computerorderclassruledetailFullList==null){
			computerorderclassruledetailFullList = new ArrayList<ComputerorderclassruledetailFull>();
		}
//		for (int i = 0; i < computerorderdetailFullList.size(); i++) {
//			int tempComputermodelId = computerorderdetailFullList.get(i).getComputerorderdetailcomputermodelid();
//			if(computerorderdetailFullMapByComputermodelId.containsKey(tempComputermodelId)){
//				computerorderdetailFullMapByComputermodelId.get(tempComputermodelId).add(computerorderdetailFullList.get(i));
//			}else{
//				ArrayList<ComputerorderdetailFull> tempComputerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
//				tempComputerorderdetailFullList.add(computerorderdetailFullList.get(i));
//				computerorderdetailFullMapByComputermodelId.put(tempComputermodelId,tempComputerorderdetailFullList);
//			}
//		}
//		
//		if(computerorderdetailFullMapByComputermodelId == null){
//			computerorderdetailFullMapByComputermodelId = new HashMap<Integer,ArrayList<ComputerorderdetailFull>>();
//		}
//		System.out.println("computerorderdetailFullMapByComputermodelId size:"+computerorderdetailFullMapByComputermodelId.size());
		
		return SUCCESS;
		
	}

	
	
	

	public Map<String, Object> getSession() {
		return session;
	}


	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


	public ComputerorderclassruledetailService getComputerorderclassruledetailService() {
		return computerorderclassruledetailService;
	}


	public void setComputerorderclassruledetailService(
			ComputerorderclassruledetailService computerorderclassruledetailService) {
		this.computerorderclassruledetailService = computerorderclassruledetailService;
	}


	public Integer getComputerorderclassruledetailid() {
		return computerorderclassruledetailid;
	}


	public void setComputerorderclassruledetailid(
			Integer computerorderclassruledetailid) {
		this.computerorderclassruledetailid = computerorderclassruledetailid;
	}


	public Computerorderclassruledetail getComputerorderclassruledetail() {
		return computerorderclassruledetail;
	}


	public void setComputerorderclassruledetail(
			Computerorderclassruledetail computerorderclassruledetail) {
		this.computerorderclassruledetail = computerorderclassruledetail;
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


	public ComputerorderclassruleService getComputerorderclassruleService() {
		return computerorderclassruleService;
	}


	public void setComputerorderclassruleService(
			ComputerorderclassruleService computerorderclassruleService) {
		this.computerorderclassruleService = computerorderclassruleService;
	}


	public Integer getComputerorderclassruleid() {
		return computerorderclassruleid;
	}


	public void setComputerorderclassruleid(Integer computerorderclassruleid) {
		this.computerorderclassruleid = computerorderclassruleid;
	}


	public Computerorderclassrule getComputerorderclassrule() {
		return computerorderclassrule;
	}


	public void setComputerorderclassrule(
			Computerorderclassrule computerorderclassrule) {
		this.computerorderclassrule = computerorderclassrule;
	}


	public ComputerorderclassruleFull getComputerorderclassruleFull() {
		return computerorderclassruleFull;
	}


	public void setComputerorderclassruleFull(
			ComputerorderclassruleFull computerorderclassruleFull) {
		this.computerorderclassruleFull = computerorderclassruleFull;
	}


	public List<Computerorderclassrule> getComputerorderclassruleList() {
		return computerorderclassruleList;
	}


	public void setComputerorderclassruleList(
			List<Computerorderclassrule> computerorderclassruleList) {
		this.computerorderclassruleList = computerorderclassruleList;
	}


	public List<ComputerorderclassruleFull> getComputerorderclassruleFullList() {
		return computerorderclassruleFullList;
	}


	public void setComputerorderclassruleFullList(
			List<ComputerorderclassruleFull> computerorderclassruleFullList) {
		this.computerorderclassruleFullList = computerorderclassruleFullList;
	}


	public String getLogprefix() {
		return logprefix;
	}


	public void setLogprefix(String logprefix) {
		this.logprefix = logprefix;
	}


	public Page getPage() {
		return page;
	}


	public void setPage(Page page) {
		this.page = page;
	}


	public Integer getPageNo() {
		return pageNo;
	}


	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}


	public ReturnJson getReturnJson() {
		return returnJson;
	}


	public void setReturnJson(ReturnJson returnJson) {
		this.returnJson = returnJson;
	}


	public String getReturnStr() {
		return returnStr;
	}


	public void setReturnStr(String returnStr) {
		this.returnStr = returnStr;
	}


	public static Log getLog() {
		return log;
	}
	
	
	
	
	
	
}
