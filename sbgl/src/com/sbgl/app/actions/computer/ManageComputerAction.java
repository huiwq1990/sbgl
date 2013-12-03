package com.sbgl.app.actions.computer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.InternalContextAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.entity.Computer;
import com.sbgl.app.entity.ComputerFull;
import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.ComputercategoryFull;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.ComputermodelFull;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.services.computer.ComputerService;
import com.sbgl.app.services.computer.ComputercategoryService;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.util.ComputerDirective;
import com.sbgl.util.Page;
import com.sbgl.util.SpringContextUtil;
import com.sbgl.util.SpringUtil;



@Scope("prototype") 
@Controller("ManageComputerAction")
public class ManageComputerAction extends ActionSupport implements SessionAware{

	private static final Log log = LogFactory.getLog(ManageComputerAction.class);

	private Map<String, Object> session;
	private int pageNo;
	private String callType;

	// Service
	@Resource
	private ComputerService computerService;
	List<Computer> computerList = new ArrayList<Computer>();
	List<ComputerFull> computerFullList = new ArrayList<ComputerFull>();
	private Integer computerid; //entity full 的id属性名称		
	
	@Resource
	private ComputercategoryService computercategoryService;
	
	//父级分类的list
	List<Computercategory> parentcomputercategoryList = new ArrayList<Computercategory>();
	List<Computercategory> computercategoryList = new ArrayList<Computercategory>();
	List<ComputercategoryFull> computercategoryFullList = new ArrayList<ComputercategoryFull>();
	List<ComputercategoryFull> computercategoryFullListCh = new ArrayList<ComputercategoryFull>();
	List<ComputercategoryFull> computercategoryFullListEn = new ArrayList<ComputercategoryFull>();
	
	@Resource
	private ComputermodelService computermodelService;
	//分
//	List<Computercategory> computermodeComputercategoryList = new ArrayList<Computercategory>();
	List<Computermodel> computermodelList = new ArrayList<Computermodel>();
	List<ComputermodelFull> computermodelFullList = new ArrayList<ComputermodelFull>();
	List<ComputermodelFull> computermodelFullListCh = new ArrayList<ComputermodelFull>();
	List<ComputermodelFull> computermodelFullListEn = new ArrayList<ComputermodelFull>();
	
	
	@Resource
	private ComputerorderService computerorderService;





	List<ComputerorderFull> computerorderFullList = new ArrayList<ComputerorderFull>();

	private String logprefix = "exec method";
	Page page = new Page();

	
	
			
	//管理 查询
	public String manageComputercategoryFull(){
		log.info("exec action method:manageComputercategoryFull");
		
//      分页查询		
		if(pageNo ==0){
			pageNo =1;
		}
		
		
		//设置总数量，由于是双语 除2
		page.setTotalCount(computercategoryService.countComputercategoryRow()/2);
		//如果页码大于总页数，重新设置
		if(pageNo>page.getTotalpage()){
			pageNo = page.getTotalpage();
		}
		page.setPageNo(pageNo);
		
		String sqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";	
//		computercategoryFullList  = computercategoryService.selectShowedComputercategoryFullByPage(page);
		computercategoryFullListCh  = computercategoryService.selectComputercategoryFullByConditionAndPage(sqlch , page);
		
		String sqlen = " where a.languagetype=1 order by a.computercategorytype,a.languagetype";
		computercategoryFullListEn  = computercategoryService.selectComputercategoryFullByConditionAndPage(sqlen , page);
		
//		查询全部
//		computercategoryFullList  = computercategoryService.selectComputercategoryFullAll();

		if(computercategoryFullList == null){
			computercategoryFullList = new ArrayList<ComputercategoryFull>();
		}
		
		parentcomputercategoryList = computercategoryService.selectParentComputercategory();
		if(parentcomputercategoryList == null){
			parentcomputercategoryList = new ArrayList<Computercategory>();
		}
		if(callType!=null&&callType.equals("ajaxType")){
			return "success2";
		}else{
			return "success";
		}
	}			
	
	//管理 查询
/*	public void manageComputercategoryFull(InternalContextAdapter cxt,Map param,VelocityContext context){
		log.info("exec action method:manageComputercategoryFull");
//		ApplicationContext a=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		 WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();  
		ComputercategoryService computercategoryService =(ComputercategoryService)SpringContextUtil.getWebApplicationContext().getBean("computercategoryService");
		if(computercategoryService ==null){
			System.out.println("aaaaaassssssssssssssssssss");
		}
//      分页查询		
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computercategoryService.countComputercategoryRow());
//		computercategoryFullList  = computercategoryService.selectShowedComputercategoryFullByPage(page);
		computercategoryFullList  = computercategoryService.selectComputercategoryFullByConditionAndPage(" order by computercategorytype,languagetype", page);
		
//		查询全部
//		computercategoryFullList  = computercategoryService.selectComputercategoryFullAll();

		if(computercategoryFullList == null){
			computercategoryFullList = new ArrayList<ComputercategoryFull>();
		}
		
		parentcomputercategoryList = computercategoryService.selectParentComputercategory();
		if(parentcomputercategoryList == null){
			parentcomputercategoryList = new ArrayList<Computercategory>();
		}
		context.put("computercategoryFullList", computercategoryFullList);
//		return SUCCESS;
	}
*/	
	
	
			
	//管理 查询
	public String manageComputerFull(){
		log.info("exec action method:manageComputerFull");
		
//      分页查询		
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computerService.countComputerRow());
		computerFullList  = computerService.selectComputerFullByPage(page);
		
//		查询全部
//		computerFullList  = computerService.selectComputerFullAll();

		if(computerFullList == null){
			computerFullList = new ArrayList<ComputerFull>();
		}
//		for(int i = 0; i < computerFullList.size(); i++){
//			System.out.println("id="+computerFullList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}

	
	//管理ComputermodelFull
	public String manageComputermodelFull(){
		log.info("exec action method:manageComputermodelFull");

		
//      分页查询		
		if(pageNo ==0){
			pageNo =1;
		}		
		//设置总数量，由于是双语 除2
		page.setTotalCount(computermodelService.countComputermodelRow()/2);
		//如果页码大于总页数，重新设置
		if(pageNo>page.getTotalpage()){
			pageNo = page.getTotalpage();
		}
		page.setPageNo(pageNo);
		
		
		//查询中文的Model
		String sqlch = " where a.languagetype=0 order by a.computermodeltype,a.languagetype";		
		computermodelFullListCh  = computermodelService.selectComputermodelByConditionAndPage(sqlch , page);
		//查询英文的Model
		String sqlen = " where a.languagetype=1 order by a.computermodeltype,a.languagetype";
		computermodelFullListEn  = computermodelService.selectComputermodelByConditionAndPage(sqlen , page);
		
		
		//model的分类信息，只显示中文的
		String categorysqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";
		computercategoryFullList  = computercategoryService.selectComputercategoryFullByCondition(categorysqlch);
		
		
		if(computermodelFullListCh == null){
			computermodelFullListCh = new ArrayList<ComputermodelFull>();
		}
		if(computermodelFullListEn == null){
			computermodelFullListEn = new ArrayList<ComputermodelFull>();
		}
		if(computercategoryFullList == null){
			computercategoryFullList = new ArrayList<ComputercategoryFull>();
		}

		//进入管理界面直接请求，Ajax请求使用AjaxType
		if(callType!=null&&callType.equals("ajaxType")){
			return "success2";
		}else{
			return "success1";
		}
	}			
	
	
	
	
	
	public String toAddComputerPage(){
		String sqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";
		computercategoryFullList  = computercategoryService.selectComputercategoryFullByConditionAndPage(sqlch , page);
		sqlch = " where a.languagetype=0 order by a.computermodeltype,a.languagetype";		
		computermodelFullList  = computermodelService.selectComputermodelByConditionAndPage(sqlch , page);
		
		return SUCCESS;
	}
	
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	public ComputerService getComputerService() {
		return computerService;
	}

	public void setComputerService(ComputerService computerService) {
		this.computerService = computerService;
	}

	public ComputercategoryService getComputercategoryService() {
		return computercategoryService;
	}

	public void setComputercategoryService(
			ComputercategoryService computercategoryService) {
		this.computercategoryService = computercategoryService;
	}

	public ComputermodelService getComputermodelService() {
		return computermodelService;
	}

	public void setComputermodelService(ComputermodelService computermodelService) {
		this.computermodelService = computermodelService;
	}

	public ComputerorderService getComputerorderService() {
		return computerorderService;
	}

	public void setComputerorderService(ComputerorderService computerorderService) {
		this.computerorderService = computerorderService;
	}

	public List<Computer> getComputerList() {
		return computerList;
	}

	public void setComputerList(List<Computer> computerList) {
		this.computerList = computerList;
	}

	public List<ComputerFull> getComputerFullList() {
		return computerFullList;
	}

	public void setComputerFullList(List<ComputerFull> computerFullList) {
		this.computerFullList = computerFullList;
	}

	public List<Computercategory> getComputercategoryList() {
		return computercategoryList;
	}

	public void setComputercategoryList(List<Computercategory> computercategoryList) {
		this.computercategoryList = computercategoryList;
	}

	public List<ComputercategoryFull> getComputercategoryFullList() {
		return computercategoryFullList;
	}

	public void setComputercategoryFullList(
			List<ComputercategoryFull> computercategoryFullList) {
		this.computercategoryFullList = computercategoryFullList;
	}

	public List<ComputerorderFull> getComputerorderFullList() {
		return computerorderFullList;
	}

	public void setComputerorderFullList(
			List<ComputerorderFull> computerorderFullList) {
		this.computerorderFullList = computerorderFullList;
	}



	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public static Log getLog() {
		return log;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getComputerid() {
		return computerid;
	}

	public void setComputerid(Integer computerid) {
		this.computerid = computerid;
	}

	public String getLogprefix() {
		return logprefix;
	}

	public void setLogprefix(String logprefix) {
		this.logprefix = logprefix;
	}

	public List<Computercategory> getParentcomputercategoryList() {
		return parentcomputercategoryList;
	}

	public void setParentcomputercategoryList(
			List<Computercategory> parentcomputercategoryList) {
		this.parentcomputercategoryList = parentcomputercategoryList;
	}

	public List<Computermodel> getComputermodelList() {
		return computermodelList;
	}

	public void setComputermodelList(List<Computermodel> computermodelList) {
		this.computermodelList = computermodelList;
	}

	public List<ComputermodelFull> getComputermodelFullList() {
		return computermodelFullList;
	}

	public void setComputermodelFullList(
			List<ComputermodelFull> computermodelFullList) {
		this.computermodelFullList = computermodelFullList;
	}



	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public List<ComputercategoryFull> getComputercategoryFullListCh() {
		return computercategoryFullListCh;
	}

	public void setComputercategoryFullListCh(
			List<ComputercategoryFull> computercategoryFullListCh) {
		this.computercategoryFullListCh = computercategoryFullListCh;
	}

	public List<ComputercategoryFull> getComputercategoryFullListEn() {
		return computercategoryFullListEn;
	}

	public void setComputercategoryFullListEn(
			List<ComputercategoryFull> computercategoryFullListEn) {
		this.computercategoryFullListEn = computercategoryFullListEn;
	}

	public List<ComputermodelFull> getComputermodelFullListCh() {
		return computermodelFullListCh;
	}

	public void setComputermodelFullListCh(
			List<ComputermodelFull> computermodelFullListCh) {
		this.computermodelFullListCh = computermodelFullListCh;
	}

	public List<ComputermodelFull> getComputermodelFullListEn() {
		return computermodelFullListEn;
	}

	public void setComputermodelFullListEn(
			List<ComputermodelFull> computermodelFullListEn) {
		this.computermodelFullListEn = computermodelFullListEn;
	}

	
	
}
