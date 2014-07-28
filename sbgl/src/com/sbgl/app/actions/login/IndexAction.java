package com.sbgl.app.actions.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.actions.common.CommonActionUtil;
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.computer.ComputerAction;
import com.sbgl.app.actions.computer.ComputerorderActionUtil;
import com.sbgl.app.actions.computer.ComputerorderEntity;
import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.entity.ComputerhomeworkFull;
import com.sbgl.app.entity.Computerhomeworkreceiver;
import com.sbgl.app.entity.ComputerhomeworkreceiverFull;
import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.services.computer.ComputerhomeworkService;
import com.sbgl.app.services.computer.ComputerhomeworkreceiverService;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.services.computer.ComputerorderdetailService;
import com.sbgl.app.services.order.OrderMainService;


@Scope("prototype") 
@Controller("IndexAction")
public class IndexAction extends BaseAction{
	
	private static final Log log = LogFactory.getLog(IndexAction.class);

	
	@Resource
	private ComputerorderService computerorderService;	

	
	@Resource
	private OrderMainService orderMainService;
	
	
	private List<EquipmenborrowFull> equipmenborrowFullList;

	
	List<ComputerorderEntity> computerorderEntityList = new ArrayList<ComputerorderEntity>();//进行中的预约
	
//	订单状态
	private int ComputerorderStatusAduitAll = ComputerorderInfo.ComputerorderStatusAduitAll;
	private int ComputerorderStatusAduitPass = ComputerorderInfo.ComputerorderStatusAduitPass;
	private int ComputerorderStatusAduitReject = ComputerorderInfo.ComputerorderStatusAduitReject;
	private int ComputerorderStatusAduitDel = ComputerorderInfo.ComputerorderStatusAduitDel;
	private int ComputerorderStatusAduitWait = ComputerorderInfo.ComputerorderStatusAduitWait;
	private int IndividualOrder = ComputerorderInfo.IndividualOrder;
	private int ClassOrder = ComputerorderInfo.ClassOrder;
	
	public String languagetype;
	
	public String index(){
		
//		System.out.println("sss");
			
		try{
			Integer userid = this.getCurrentUserId();
	
			if(userid < 0){		
				actionMsg = "用户未登录";
				return ComputerConfig.usernotloginreturnstr;
			}
			
	
	//		进行中的预约
			computerorderEntityList = computerorderService.getUnderwayComputerorder(userid);
			
			equipmenborrowFullList = orderMainService.findUnderWayOrder(userid);
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return "404";
		
	}


	public String changeLanguage(){
		try{
		ServletActionContext.getRequest().getSession().setAttribute(CommonConfig.sessionLanguagetype,languagetype);
		this.forwardurl =  this.getActionUrl();
		System.out.println("forwardurl" +forwardurl + " "+ this.getUrl());
		return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();			
		}
		return "404";
	}
	

	public ComputerorderService getComputerorderService() {
		return computerorderService;
	}


	public void setComputerorderService(ComputerorderService computerorderService) {
		this.computerorderService = computerorderService;
	}


	public OrderMainService getOrderMainService() {
		return orderMainService;
	}


	public void setOrderMainService(OrderMainService orderMainService) {
		this.orderMainService = orderMainService;
	}


	public List<EquipmenborrowFull> getEquipmenborrowFullList() {
		return equipmenborrowFullList;
	}


	public void setEquipmenborrowFullList(
			List<EquipmenborrowFull> equipmenborrowFullList) {
		this.equipmenborrowFullList = equipmenborrowFullList;
	}


	public List<ComputerorderEntity> getComputerorderEntityList() {
		return computerorderEntityList;
	}


	public void setComputerorderEntityList(
			List<ComputerorderEntity> computerorderEntityList) {
		this.computerorderEntityList = computerorderEntityList;
	}


	public int getComputerorderStatusAduitAll() {
		return ComputerorderStatusAduitAll;
	}


	public void setComputerorderStatusAduitAll(int computerorderStatusAduitAll) {
		ComputerorderStatusAduitAll = computerorderStatusAduitAll;
	}


	public int getComputerorderStatusAduitPass() {
		return ComputerorderStatusAduitPass;
	}


	public void setComputerorderStatusAduitPass(int computerorderStatusAduitPass) {
		ComputerorderStatusAduitPass = computerorderStatusAduitPass;
	}


	public int getComputerorderStatusAduitReject() {
		return ComputerorderStatusAduitReject;
	}


	public void setComputerorderStatusAduitReject(int computerorderStatusAduitReject) {
		ComputerorderStatusAduitReject = computerorderStatusAduitReject;
	}


	public int getComputerorderStatusAduitDel() {
		return ComputerorderStatusAduitDel;
	}


	public void setComputerorderStatusAduitDel(int computerorderStatusAduitDel) {
		ComputerorderStatusAduitDel = computerorderStatusAduitDel;
	}


	public int getComputerorderStatusAduitWait() {
		return ComputerorderStatusAduitWait;
	}


	public void setComputerorderStatusAduitWait(int computerorderStatusAduitWait) {
		ComputerorderStatusAduitWait = computerorderStatusAduitWait;
	}


	public int getIndividualOrder() {
		return IndividualOrder;
	}


	public void setIndividualOrder(int individualOrder) {
		IndividualOrder = individualOrder;
	}


	public int getClassOrder() {
		return ClassOrder;
	}


	public void setClassOrder(int classOrder) {
		ClassOrder = classOrder;
	}


	public static Log getLog() {
		return log;
	}


	public String getLanguagetype() {
		return languagetype;
	}


	public void setLanguagetype(String languagetype) {
		this.languagetype = languagetype;
	}


	
}
