package com.sbgl.app.actions.order;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.entity.Equipmenborrow;
import com.sbgl.app.services.order.OrderFinishService;
import com.sbgl.common.DataError;

@Scope("prototype") 
@Controller("OrderFinishAction")
public class OrderFinishAction  extends ActionSupport {
	private static final Log log = LogFactory.getLog(OrderMainAction.class);
	
	@Resource
	private OrderFinishService orderFinishService;
	
	
	
	private List<EquipmentFull> equipmentList;
	private Integer borrowId;
	private Equipmenborrow equipmenborrow;
	private EquipmenborrowFull equipmenborrowFull;
	private EquipmentFull equipmentFull;
	private String tag;
	private String message;
	private String equtitle;
	private String equremark;
	private Integer equipmentId;
	
	//进入完成订单页面
	public String equipmentFinish(){	
		return SUCCESS;
	}
	
	//进入查看订单确认页面
	public String equipmentConfirm(){
		equipmentList = orderFinishService.findListBorrow(borrowId);
		equipmenborrowFull = orderFinishService.findEquipmenborrow(borrowId);
		return SUCCESS;
	}
	
	//提交订单备注
	public String finishorder(){
		try{
			boolean flag = false;
			flag = orderFinishService.finishorder(borrowId, equtitle, equremark);
			if(flag){
				tag = "1";
			}else{
				tag = "2";
				message = "提交订单失败";
			}
		}catch(Exception e){
			tag = "2";
			message = "提交订单失败";
			log.error(e);
		}
		return SUCCESS;
	}
	
	public String deleteorder(){
		try{
			boolean flag = false;
			flag = orderFinishService.deleteorder(borrowId);
			if(flag){
				tag = "1";
			}else{
				tag = "2";
				message = "订单删除失败";
			}
		}catch(Exception e){
			tag = "2";
			message = "订单删除失败";
			log.error(e);
		}
		return SUCCESS;
	}

	public String equipConfirmContent(){
		equipmentFull = orderFinishService.findEquipmentById(equipmentId);
		return SUCCESS;
	}
	
	public String equipOrderContent(){
		equipmentList = orderFinishService.findListBorrow(borrowId);
		equipmenborrowFull = orderFinishService.findEquipmenborrow(borrowId);
		return SUCCESS;
	}
	
	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Equipmenborrow getEquipmenborrow() {
		return equipmenborrow;
	}

	public void setEquipmenborrow(Equipmenborrow equipmenborrow) {
		this.equipmenborrow = equipmenborrow;
	}

	public List<EquipmentFull> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<EquipmentFull> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public EquipmenborrowFull getEquipmenborrowFull() {
		return equipmenborrowFull;
	}

	public void setEquipmenborrowFull(EquipmenborrowFull equipmenborrowFull) {
		this.equipmenborrowFull = equipmenborrowFull;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getEqutitle() {
		return equtitle;
	}

	public void setEqutitle(String equtitle) {
		this.equtitle = equtitle;
	}

	public String getEquremark() {
		return equremark;
	}

	public void setEquremark(String equremark) {
		this.equremark = equremark;
	}

	public EquipmentFull getEquipmentFull() {
		return equipmentFull;
	}

	public void setEquipmentFull(EquipmentFull equipmentFull) {
		this.equipmentFull = equipmentFull;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}


	
}
