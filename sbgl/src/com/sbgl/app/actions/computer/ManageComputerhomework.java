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
import com.opensymphony.xwork2.ModelDriven;
import com.sbgl.app.entity.Computerhomework;
import com.sbgl.app.entity.ComputerhomeworkFull;
import com.sbgl.app.entity.Computerhomeworkreceiver;
import com.sbgl.app.entity.ComputerhomeworkreceiverFull;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.services.computer.ComputerhomeworkService;
import com.sbgl.app.services.computer.ComputerhomeworkreceiverService;


@Scope("prototype") 
@Controller("ManageComputerhomework")
public class ManageComputerhomework extends ActionSupport implements SessionAware{
	
	private static final Log log = LogFactory.getLog(ManageComputerhomework.class);
	private Map<String, Object> session;


	@Resource
	private ComputerhomeworkService computerhomeworkService;	
	private Computerhomework computerhomework = new Computerhomework();//实例化一个模型
	private ComputerhomeworkFull computerhomeworkFull = new ComputerhomeworkFull();//实例化一个模型
	private List<Computerhomework> computerhomeworkList = new ArrayList<Computerhomework>();
	private List<ComputerhomeworkFull> computerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();
	
	
	@Resource
	private ComputerhomeworkreceiverService computerhomeworkreceiverService;
	private Computerhomeworkreceiver computerhomeworkreceiver = new Computerhomeworkreceiver();//实例化一个模型
	private ComputerhomeworkreceiverFull computerhomeworkreceiverFull = new ComputerhomeworkreceiverFull();//实例化一个模型
	private List<Computerhomeworkreceiver> computerhomeworkreceiverList = new ArrayList<Computerhomeworkreceiver>();
	private List<ComputerhomeworkreceiverFull> computerhomeworkreceiverFullList = new ArrayList<ComputerhomeworkreceiverFull>();
	
	//作业收
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
				homeworkids += computerhomeworkreceiverList.get(i)+",";
			}
			homeworkids = homeworkids.substring(0,homeworkids.length()-1);
			
			homeworksql = homeworksql + " in (" +homeworkids+") ";
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

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public ComputerhomeworkService getComputerhomeworkService() {
		return computerhomeworkService;
	}

	public void setComputerhomeworkService(
			ComputerhomeworkService computerhomeworkService) {
		this.computerhomeworkService = computerhomeworkService;
	}

	public Computerhomework getComputerhomework() {
		return computerhomework;
	}

	public void setComputerhomework(Computerhomework computerhomework) {
		this.computerhomework = computerhomework;
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

	public void setComputerhomeworkFullList(
			List<ComputerhomeworkFull> computerhomeworkFullList) {
		this.computerhomeworkFullList = computerhomeworkFullList;
	}

	public ComputerhomeworkreceiverService getComputerhomeworkreceiverService() {
		return computerhomeworkreceiverService;
	}

	public void setComputerhomeworkreceiverService(
			ComputerhomeworkreceiverService computerhomeworkreceiverService) {
		this.computerhomeworkreceiverService = computerhomeworkreceiverService;
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

	public static Log getLog() {
		return log;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	
	
}
