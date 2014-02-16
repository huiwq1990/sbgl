package com.sbgl.app.actions.order;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.services.order.OrderExamService;
import com.sbgl.app.services.order.OrderFinishService;

@Scope("prototype") 
@Controller("OrderExamAction")
public class OrderExamAction  extends ActionSupport{
	private static final Log log = LogFactory.getLog(OrderMainAction.class);
	
	private List<EquipmentFull> equipmentList;
	private Integer borrowId;
	private EquipmenborrowFull equipmenborrowFull;

	@Resource
	private OrderExamService orderExamService;
	
	@Resource
	private OrderFinishService orderFinishService;
	
//	进入查看订单确认页面
	public String equipmentConfirm(){
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

	public EquipmenborrowFull getEquipmenborrowFull() {
		return equipmenborrowFull;
	}

	public void setEquipmenborrowFull(EquipmenborrowFull equipmenborrowFull) {
		this.equipmenborrowFull = equipmenborrowFull;
	}

	public List<EquipmentFull> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<EquipmentFull> equipmentList) {
		this.equipmentList = equipmentList;
	}
}
