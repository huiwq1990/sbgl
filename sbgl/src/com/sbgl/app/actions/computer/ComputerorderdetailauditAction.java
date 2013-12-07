package com.sbgl.app.actions.computer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.services.computer.ComputerorderdetailService;

@Scope("prototype") 
@Controller("ComputerorderdetailauditAction")
public class ComputerorderdetailauditAction extends ActionSupport implements SessionAware,ModelDriven<Computerorderdetail> {

	@Resource
	private ComputerorderService computerorderService;
	
	@Resource
	private ComputerorderdetailService computerorderdetailService;
	
	int computerorderId;//订单的id
	//获取订单信息

	ComputerorderFull computerorderFull;
	
	List<Computerorderdetail> computerorderdetailList = new ArrayList<Computerorderdetail>();
	List<ComputerorderdetailFull> computerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
	
	public String auditComputerorderdetail(){
		
		computerorderFull = computerorderService.selectComputerorderFullById(computerorderId);
		String sql = " where a.computerorderid = "+computerorderId;
		computerorderdetailFullList = computerorderdetailService.selectComputerorderdetailFullByCondition(sql);
		
		if(computerorderdetailFullList==null){
			computerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
		}
		return SUCCESS;
	}
	
	
	
	@Override
	public Computerorderdetail getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}



	public ComputerorderService getComputerorderService() {
		return computerorderService;
	}



	public void setComputerorderService(ComputerorderService computerorderService) {
		this.computerorderService = computerorderService;
	}



	public ComputerorderdetailService getComputerorderdetailService() {
		return computerorderdetailService;
	}



	public void setComputerorderdetailService(
			ComputerorderdetailService computerorderdetailService) {
		this.computerorderdetailService = computerorderdetailService;
	}



	public int getComputerorderId() {
		return computerorderId;
	}



	public void setComputerorderId(int computerorderId) {
		this.computerorderId = computerorderId;
	}



	public ComputerorderFull getComputerorderFull() {
		return computerorderFull;
	}



	public void setComputerorderFull(ComputerorderFull computerorderFull) {
		this.computerorderFull = computerorderFull;
	}



	public List<Computerorderdetail> getComputerorderdetailList() {
		return computerorderdetailList;
	}



	public void setComputerorderdetailList(
			List<Computerorderdetail> computerorderdetailList) {
		this.computerorderdetailList = computerorderdetailList;
	}



	public List<ComputerorderdetailFull> getComputerorderdetailFullList() {
		return computerorderdetailFullList;
	}



	public void setComputerorderdetailFullList(
			List<ComputerorderdetailFull> computerorderdetailFullList) {
		this.computerorderdetailFullList = computerorderdetailFullList;
	}

	
}
