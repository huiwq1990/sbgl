package com.sbgl.app.actions.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.services.computer.ComputerorderdetailService;


@Scope("prototype") 
@Controller("AdminIndexAction")
public class AdminIndexAction extends BaseAction{
	
	
	@Resource
	private ComputerorderService computerorderService;	
	private Computerorder computerorder = new Computerorder();//实例化一个模型
	private ComputerorderFull computerorderFull = new ComputerorderFull();//实例化一个模型
	List<Computerorder> computerorderList = new ArrayList<Computerorder>();

	
	@Resource
	private ComputerorderdetailService computerorderdetailService;	
	private Computerorderdetail computerorderdetail = new Computerorderdetail();//实例化一个模型
	private ComputerorderdetailFull computerorderdetailFull = new ComputerorderdetailFull();//实例化一个模型	
	List<Computerorderdetail> computerorderdetailList = new ArrayList<Computerorderdetail>();

	int computerorderWaitAuditNum;
	
	
	public String adminIndex(){
		
//		统计待审核的预约数量
		
		computerorderList = computerorderService.selectComputerorderByCondition(" where status = "+ComputerorderInfo.ComputerorderStatusAduitWait);
		if(computerorderList == null){
			computerorderWaitAuditNum = 0;
		}else{
			computerorderWaitAuditNum = computerorderList.size();
		}
		
		
		return SUCCESS;
	}


	public ComputerorderService getComputerorderService() {
		return computerorderService;
	}


	public void setComputerorderService(ComputerorderService computerorderService) {
		this.computerorderService = computerorderService;
	}


	public Computerorder getComputerorder() {
		return computerorder;
	}


	public void setComputerorder(Computerorder computerorder) {
		this.computerorder = computerorder;
	}


	public ComputerorderFull getComputerorderFull() {
		return computerorderFull;
	}


	public void setComputerorderFull(ComputerorderFull computerorderFull) {
		this.computerorderFull = computerorderFull;
	}


	public List<Computerorder> getComputerorderList() {
		return computerorderList;
	}


	public void setComputerorderList(List<Computerorder> computerorderList) {
		this.computerorderList = computerorderList;
	}


	public ComputerorderdetailService getComputerorderdetailService() {
		return computerorderdetailService;
	}


	public void setComputerorderdetailService(
			ComputerorderdetailService computerorderdetailService) {
		this.computerorderdetailService = computerorderdetailService;
	}


	public Computerorderdetail getComputerorderdetail() {
		return computerorderdetail;
	}


	public void setComputerorderdetail(Computerorderdetail computerorderdetail) {
		this.computerorderdetail = computerorderdetail;
	}


	public ComputerorderdetailFull getComputerorderdetailFull() {
		return computerorderdetailFull;
	}


	public void setComputerorderdetailFull(
			ComputerorderdetailFull computerorderdetailFull) {
		this.computerorderdetailFull = computerorderdetailFull;
	}


	public List<Computerorderdetail> getComputerorderdetailList() {
		return computerorderdetailList;
	}


	public void setComputerorderdetailList(
			List<Computerorderdetail> computerorderdetailList) {
		this.computerorderdetailList = computerorderdetailList;
	}


	public int getComputerorderWaitAuditNum() {
		return computerorderWaitAuditNum;
	}


	public void setComputerorderWaitAuditNum(int computerorderWaitAuditNum) {
		this.computerorderWaitAuditNum = computerorderWaitAuditNum;
	}

	
	
	
	
}
