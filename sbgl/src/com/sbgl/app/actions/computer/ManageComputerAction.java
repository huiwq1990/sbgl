package com.sbgl.app.actions.computer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.entity.Computer;
import com.sbgl.app.entity.ComputerFull;
import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.ComputercategoryFull;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.services.computer.ComputerService;
import com.sbgl.app.services.computer.ComputercategoryService;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.util.Page;



@Scope("prototype") 
@Controller("ManageComputerAction")
public class ManageComputerAction extends ActionSupport implements SessionAware {

	private static final Log log = LogFactory.getLog(ManageComputerAction.class);

	private Map<String, Object> session;
	private int pageNo;

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
	
	@Resource
	private ComputermodelService computermodelService;
	@Resource
	private ComputerorderService computerorderService;





	List<ComputerorderFull> computerorderFullList = new ArrayList<ComputerorderFull>();

	private String logprefix = "exec method";
	Page page = new Page();

	
//  manage Computercategory
	public String manageComputercategory(){
		log.info(logprefix+"manageComputercategoryFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computercategoryService.countComputercategoryRow());
		computercategoryList  = computercategoryService.selectComputercategoryByPage(page);
		
//		查询全部
//		computercategoryList  = computercategoryService.selectComputercategoryById();
		
	    if(computercategoryList == null){
			computercategoryList = new ArrayList<Computercategory>();
		}
//		for(int i = 0; i < computercategoryList.size(); i++){
//			System.out.println("id="+computercategoryList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
	//管理 查询
	public String manageComputercategoryFull(){
		log.info("exec action method:manageComputercategoryFull");
		
//      分页查询		
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computercategoryService.countComputercategoryRow());
		computercategoryFullList  = computercategoryService.selectComputercategoryFullByPage(page);
		
//		查询全部
//		computercategoryFullList  = computercategoryService.selectComputercategoryFullAll();

		if(computercategoryFullList == null){
			computercategoryFullList = new ArrayList<ComputercategoryFull>();
		}
		
		parentcomputercategoryList = computercategoryService.selectParentComputercategory();
		if(parentcomputercategoryList == null){
			parentcomputercategoryList = new ArrayList<Computercategory>();
		}
		return SUCCESS;
	}			
			
	
//  manage Computer
	public String manageComputer(){
		log.info(logprefix+"manageComputerFull");
		
//      分页查询	
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computerService.countComputerRow());
		computerList  = computerService.selectComputerByPage(page);
		
//		查询全部
//		computerList  = computerService.selectComputerById();
		
	    if(computerList == null){
			computerList = new ArrayList<Computer>();
		}
//		for(int i = 0; i < computerList.size(); i++){
//			System.out.println("id="+computerList.get(i).getLoginusername());
//		}
		return SUCCESS;
	}		
			
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

	
	
}
